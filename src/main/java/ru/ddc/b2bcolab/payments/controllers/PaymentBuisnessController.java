package ru.ddc.b2bcolab.payments.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.ddc.b2bcolab.payments.ChargeRequest;

@Controller
public class PaymentBuisnessController {

    @Value("${STRIPE_PUBLIC_KEY}")
    private String stripePublicKey;

    @GetMapping("/payments/plans/buisness")
    public String checkout(Model model) {
        model.addAttribute("amount", 6000000);
        model.addAttribute("stripePublicKey", stripePublicKey);
        model.addAttribute("currency", ChargeRequest.Currency.RUB);
        return "payments";
    }

}
