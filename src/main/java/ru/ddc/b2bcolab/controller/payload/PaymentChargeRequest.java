package ru.ddc.b2bcolab.controller.payload;

import lombok.Data;

@Data
public class PaymentChargeRequest {
    private String description;
    private int amount;
    private PaymentCurrency paymentCurrency;
    private String stripeEmail;
    private String stripeToken;
}