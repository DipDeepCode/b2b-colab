package ru.ddc.b2bcolab.payments.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.payments.entity.Subscription;
import ru.ddc.b2bcolab.payments.entity.UpgradeRequest;
import ru.ddc.b2bcolab.payments.services.StripeService;
import ru.ddc.b2bcolab.payments.services.SubscriptionService;

@RestController
@RequestMapping("/api/payments")
public class UpgradeController {

    @Autowired
    private StripeService paymentsService;

    @Autowired
    private SubscriptionService subscriptionService;

    @PostMapping("/upgrade")
    public ResponseEntity<Charge> upgradePlan(@RequestBody UpgradeRequest upgradeRequest) throws StripeException {
        Subscription subscription = subscriptionService.getSubscriptionByUserId(upgradeRequest.getUserId());
        if (subscription == null) {
            return ResponseEntity.badRequest().body(null);
        }

        Charge charge = paymentsService.upgradePlan(subscription, upgradeRequest);
        if (charge != null) {
            subscription.setCurrentPlan(upgradeRequest.getNewPlan());
            subscriptionService.saveSubscription(subscription);
        }
        return ResponseEntity.ok(charge);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleError(StripeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

}