package ru.ddc.b2bcolab.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.ChargeRequest;
import ru.ddc.b2bcolab.controller.payload.ChargeResponse;
import ru.ddc.b2bcolab.controller.payload.UpgradeRequest;
import ru.ddc.b2bcolab.model.Tariff;
import ru.ddc.b2bcolab.model.Subscription;
import ru.ddc.b2bcolab.service.StripeService;
import ru.ddc.b2bcolab.service.SubscriptionService;

import java.util.Map;
import java.util.Optional;



@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    Logger logger = LoggerFactory.getLogger(PaymentController.class);

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/plans/lite")
    public ResponseEntity<Tariff> getLitePlan() {
        Tariff liteTariff = new Tariff( "Lite Match",
                true,
                true,
                true,
                true,
                false,
                false,
                false,
                false,
                12000,
                "1 год");
        return ResponseEntity.ok(liteTariff);
    }

    @GetMapping("/plans/comfort")
    public ResponseEntity<Tariff> getComfortPlan() {
        Tariff comfortTariff = new Tariff(
                "Comfort Match",
                true,
                true,
                true,
                true,
                true,
                false,
                false,
                true,
                24000,
                "1 год"
        );
        return ResponseEntity.ok(comfortTariff);
    }

    @GetMapping("/plans/business")
    public ResponseEntity<Tariff> getBusinessPlan() {
        Tariff businessTariff = new Tariff(
                "Business Match",
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                true,
                60000,
                "1 год"
        );
        return ResponseEntity.ok(businessTariff);
    }

    @PostMapping("/charge")
    public ResponseEntity<?> createCharge(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        chargeRequest.setCurrency(ChargeRequest.Currency.RUB);
        Charge charge = paymentsService.handleChargeAndSubscription(chargeRequest);
        return ResponseEntity.ok(new ChargeResponse(
                charge.getId(),
                charge.getStatus(),
                charge.getId(),
                charge.getBalanceTransaction()
        ));
    }

    @PostMapping("/upgrade")
    public ResponseEntity<?> upgradePlan(@RequestBody UpgradeRequest upgradeRequest) throws StripeException {
        logger.info("Received upgrade request: {}", upgradeRequest);
        Optional<Subscription> subscriptionOpt = subscriptionService.getSubscriptionByEmail(upgradeRequest.getUserEmail());
        if (subscriptionOpt.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        Subscription subscription = subscriptionOpt.get();
        paymentsService.upgradeSubscription(subscription, upgradeRequest);
        return ResponseEntity.ok("Subscription upgraded successfully");
    }

    @ExceptionHandler({StripeException.class})
    public ResponseEntity<?> handleStripeException(StripeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Error", ex.getMessage()));
    }

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of("Некорректный ввод", ex.getMessage()));
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<?> handleGeneralException(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("Внутренняя ошибка сервера", ex.getMessage()));
    }


}

