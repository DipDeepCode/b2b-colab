package ru.ddc.b2bcolab.controller;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.ddc.b2bcolab.controller.payload.ChargeRequest;
import ru.ddc.b2bcolab.controller.payload.ChargeResponse;
import ru.ddc.b2bcolab.controller.payload.UpgradeRequest;
import ru.ddc.b2bcolab.model.Plan;
import ru.ddc.b2bcolab.model.Subscription;
import ru.ddc.b2bcolab.service.StripeService;
import ru.ddc.b2bcolab.service.SubscriptionService;


@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private SubscriptionService subscriptionService;

    @GetMapping("/plans/lite")
    public ResponseEntity<Plan> getLitePlan() {
        Plan plan = new Plan("Lite", 1200000, stripePublicKey);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/plans/comfort")
    public ResponseEntity<Plan> getComfortPlan() {
        Plan plan = new Plan("Comfort", 2400000, stripePublicKey);
        return ResponseEntity.ok(plan);
    }

    @GetMapping("/plans/business")
    public ResponseEntity<Plan> getBusinnesPlan() {
        Plan plan = new Plan("Business", 6000000, stripePublicKey);
        return ResponseEntity.ok(plan);
    }

    @PostMapping("/charge")
    public ResponseEntity<?> createCharge(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.RUB);
        Charge charge = paymentsService.charge(chargeRequest);
        return ResponseEntity.ok(new ChargeResponse(
                charge.getId(),
                charge.getStatus(),
                charge.getId(),
                charge.getBalanceTransaction()
        ));
    }


    @PostMapping("/upgrade")
    public ResponseEntity<Charge> upgradePlan(@RequestBody UpgradeRequest upgradeRequest) throws StripeException {

        Subscription subscription = subscriptionService.getSubscriptionByUserId(upgradeRequest.getUserId());
        if (subscription == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Charge charge = paymentsService.upgradePlan(subscription, upgradeRequest);
        return ResponseEntity.ok(charge);
    }

}