package ru.ddc.b2bcolab.payments.controllers;

import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.ddc.b2bcolab.payments.entity.ChargeRequest;
import ru.ddc.b2bcolab.payments.services.StripeService;

@RestController
public class ChargeController {

    @Autowired
    private StripeService paymentsService;

    @PostMapping("/api/payments/charge")
    public ResponseEntity<Charge> createCharge(@RequestBody ChargeRequest chargeRequest) throws StripeException {
        chargeRequest.setDescription("Example charge");
        chargeRequest.setCurrency(ChargeRequest.Currency.RUB);
        Charge charge = paymentsService.charge(chargeRequest);
        return ResponseEntity.ok(charge);
    }

    @ExceptionHandler(StripeException.class)
    public ResponseEntity<String> handleError(StripeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}