package ru.ddc.b2bcolab.payments;

import lombok.Data;

@Data
public class ChargeRequest {

    public enum Currency {
        RUB, USD;
    }
    private String description;
    private int amount;
    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
}