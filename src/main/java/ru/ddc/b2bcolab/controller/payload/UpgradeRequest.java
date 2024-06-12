package ru.ddc.b2bcolab.controller.payload;

import lombok.Data;

@Data
public class UpgradeRequest {

    public enum Currency {
        RUB, USD;
    }
    private String userEmail;
    private String currentPlan;
    private String newPlan;
    private String stripeToken;
    private int amount;
    private String description;
    private Currency currency;
}
