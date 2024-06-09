package ru.ddc.b2bcolab.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Subscription {

    private String customerUsername;
    private String currentPlan;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private boolean isActive;

    public Subscription(String customerUsername, String currentPlan, LocalDate subscriptionStartDate, LocalDate subscriptionEndDate, boolean isActive) {
        this.customerUsername = customerUsername;
        this.currentPlan = currentPlan;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionStartDate.plusMonths(12);
        this.isActive = isActive;

    }
}
