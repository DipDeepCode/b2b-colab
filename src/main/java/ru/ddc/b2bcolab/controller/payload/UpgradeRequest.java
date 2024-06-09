package ru.ddc.b2bcolab.controller.payload;

import lombok.Data;

@Data
public class UpgradeRequest {
    private String currentPlan;
    private String newPlan;
    private String stripeCustomerId;
    private String userId;
}
