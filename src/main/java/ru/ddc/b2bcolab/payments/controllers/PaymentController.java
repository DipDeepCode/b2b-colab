package ru.ddc.b2bcolab.payments.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.payments.entity.Plan;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class PaymentController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @GetMapping("/api/payments/plans")
    public ResponseEntity<List<Plan>> getPlans() {
        List<Plan> plans = Arrays.asList(
                new Plan("Lite", 1200000, stripePublicKey),
                new Plan("Comfort", 2400000, stripePublicKey),
                new Plan("Business", 6000000, stripePublicKey)
        );
        return ResponseEntity.ok(plans);
    }


}