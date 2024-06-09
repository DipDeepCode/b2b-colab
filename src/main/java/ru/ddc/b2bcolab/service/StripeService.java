package ru.ddc.b2bcolab.service;

import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.ddc.b2bcolab.controller.payload.ChargeRequest;
import ru.ddc.b2bcolab.model.Plan;
import ru.ddc.b2bcolab.model.Subscription;
import ru.ddc.b2bcolab.controller.payload.UpgradeRequest;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    @Value("${STRIPE_SECRET_KEY}")
    private String secretKey;

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @PostConstruct
    public void init() {
        Stripe.apiKey = secretKey;
    }

    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
        Map<String, Object> chargeParams = new HashMap<>();
        chargeParams.put("amount", chargeRequest.getAmount());
        chargeParams.put("currency", chargeRequest.getCurrency().name().toLowerCase());
        chargeParams.put("description", chargeRequest.getDescription());
        chargeParams.put("source", chargeRequest.getStripeToken());
        chargeParams.put("receipt_email", chargeRequest.getStripeEmail());
        return Charge.create(chargeParams);
    }

    public Charge upgradePlan(Subscription subscription, UpgradeRequest upgradeRequest) throws StripeException {
        Plan currentPlan = getPlanByName(subscription.getCurrentPlan());
        Plan newPlan = getPlanByName(upgradeRequest.getNewPlan());

        if (newPlan == null || currentPlan == null) {
            throw new IllegalArgumentException("Invalid plan name provided");
        }

        LocalDate subscriptionStartDate = subscription.getSubscriptionStartDate();
        LocalDate currentDate = LocalDate.now();
        long remainingMonths = ChronoUnit.MONTHS.between(currentDate, subscriptionStartDate.plusYears(1));

        int newPlanCost = newPlan.getAmount();
        int currentPlanCost = currentPlan.getAmount();
        int upgradeCost = (newPlanCost - currentPlanCost) * (int) remainingMonths / 12;

        ChargeRequest chargeRequest = new ChargeRequest();
        chargeRequest.setDescription("Upgrade to " + upgradeRequest.getNewPlan());
        chargeRequest.setCurrency(ChargeRequest.Currency.RUB);
        chargeRequest.setAmount(upgradeCost);

        return charge(chargeRequest);
    }

    private Plan getPlanByName(String planName) {
        switch (planName) {
            case "Lite":
                return new Plan("Lite", 1200000, stripePublicKey);
            case "Comfort":
                return new Plan("Comfort", 2400000, stripePublicKey);
            case "Business":
                return new Plan("Business", 6000000, stripePublicKey);
            default:
                return null;
        }
    }

}
