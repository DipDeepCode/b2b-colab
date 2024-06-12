package ru.ddc.b2bcolab.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.ChargeRequest;
import ru.ddc.b2bcolab.model.Tariff;
import ru.ddc.b2bcolab.model.Subscription;
import ru.ddc.b2bcolab.controller.payload.UpgradeRequest;
import ru.ddc.b2bcolab.repository.TariffRepository;
import org.slf4j.Logger;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class StripeService {

    private static final Logger logger = LoggerFactory.getLogger(StripeService.class);

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    private final SubscriptionService subscriptionService;
    private final TariffRepository tariffRepository;

    @Autowired
    public StripeService(SubscriptionService subscriptionService, TariffRepository tariffRepository) {
        this.subscriptionService = subscriptionService;
        this.tariffRepository = tariffRepository;
    }

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge createCharge(ChargeRequest chargeRequest) throws StripeException {
        logger.info("Starting charge creation process...");
        logger.debug("ChargeRequest: {}", chargeRequest);

        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency().name().toLowerCase());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        chargeParams.put("receipt_email", chargeRequest.getStripeEmail());

        Charge charge;
        try {
            charge = Charge.create(chargeParams);
            logger.info("Charge created successfully: {}", charge);
        } catch (StripeException e) {
            logger.error("Error creating charge", e);
            throw e;
        }

        return charge;
    }

    public Charge handleChargeAndSubscription(ChargeRequest chargeRequest) throws StripeException {
        // Создаем платеж
        Charge charge = createCharge(chargeRequest);

        // Проверяем, есть ли у пользователя подписка
        Optional<Subscription> existingSubscription = subscriptionService.getSubscriptionByEmail(chargeRequest.getStripeEmail());
        if (existingSubscription.isPresent()) {
            // Если подписка существует, обновляем её
            UpgradeRequest upgradeRequest = new UpgradeRequest();
            upgradeRequest.setUserEmail(chargeRequest.getStripeEmail());
            upgradeRequest.setNewPlan(chargeRequest.getDescription());
            upgradeRequest.setStripeToken(chargeRequest.getStripeToken());
            upgradeSubscription(existingSubscription.get(), upgradeRequest);
        } else {
            // Если подписки нет, создаем новую
            createSubscription(chargeRequest);
        }

        return charge;
    }

    private void createSubscription(ChargeRequest chargeRequest) {
        // Создаем новую подписку и сохраняем её в базу данных
        Subscription subscription = new Subscription(
                chargeRequest.getStripeEmail(), // Используем email в качестве идентификатора пользователя
                chargeRequest.getDescription(), // Используем описание как текущий план
                LocalDate.now(),
                LocalDate.now().plusMonths(12), // Предполагаем, что подписка действует на 12 месяцев
                true
        );
        try {
            subscriptionService.saveSubscription(subscription);
            logger.info("Subscription saved successfully: {}", subscription);
        } catch (Exception e) {
            logger.error("Error saving subscription", e);
            throw e;
        }
    }

    public void upgradeSubscription(Subscription subscription, UpgradeRequest upgradeRequest) throws StripeException {
        logger.info("Starting upgrade plan process...");
        logger.debug("UpgradeRequest: {}", upgradeRequest);

        Tariff currentTariff = getPlanByName(subscription.getCurrentPlan());
        Tariff newTariff = getPlanByName(upgradeRequest.getNewPlan());

        if (newTariff == null || currentTariff == null) {
            logger.error("Invalid plan name provided: newPlan={}, currentPlan={}", upgradeRequest.getNewPlan(), subscription.getCurrentPlan());
            throw new IllegalArgumentException("Invalid plan name provided");
        }

        LocalDate subscriptionStartDate = subscription.getSubscriptionStartDate();
        LocalDate currentDate = LocalDate.now();
        long remainingMonths = ChronoUnit.MONTHS.between(currentDate, subscriptionStartDate.plusYears(1));

        int newPlanCost = newTariff.getPrice();
        int currentPlanCost = currentTariff.getPrice();
        int upgradeCost = (newPlanCost - currentPlanCost) * (int) remainingMonths / 12;

        if (upgradeCost > 0) {
            // Создаем платеж для апгрейда
            ChargeRequest chargeRequest = new ChargeRequest();
            chargeRequest.setDescription(upgradeRequest.getNewPlan());
            chargeRequest.setCurrency(ChargeRequest.Currency.RUB);
            chargeRequest.setAmount(upgradeCost);
            chargeRequest.setStripeToken(upgradeRequest.getStripeToken());
            chargeRequest.setStripeEmail(upgradeRequest.getUserEmail());

            try {
                createCharge(chargeRequest);
                logger.info("Upgrade charge created successfully");
            } catch (StripeException e) {
                logger.error("Error creating upgrade charge", e);
                throw e;
            }

        }

            // Обновляем подписку и сохраняем её в базу данных
            subscription.setCurrentPlan(upgradeRequest.getNewPlan());


            try {
                subscriptionService.updateSubscription(subscription);
                logger.info("Subscription updated successfully: {}", subscription);
            } catch (Exception e) {
                logger.error("Error updating subscription", e);
                throw e;
            }
        }


    private Tariff getPlanByName(String planName) {
        logger.debug("Fetching plan by name: {}", planName);
        return tariffRepository.findByName(planName)
                .orElseThrow(() -> new IllegalArgumentException("Invalid plan name provided"));
    }
}
