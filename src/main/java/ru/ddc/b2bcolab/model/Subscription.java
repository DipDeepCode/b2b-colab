package ru.ddc.b2bcolab.model;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class Subscription {

    private String customerEmail;
    private String currentPlan;
    private LocalDate subscriptionStartDate;
    private LocalDate subscriptionEndDate;
    private boolean isActive;


    public Subscription(String customerEmail, String currentPlan, LocalDate subscriptionStartDate, LocalDate subscriptionEndDate, boolean isActive) {
        this.customerEmail = customerEmail;
        this.currentPlan = currentPlan;
        this.subscriptionStartDate = subscriptionStartDate;
        this.subscriptionEndDate = subscriptionEndDate;
        this.isActive = isActive;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

}
