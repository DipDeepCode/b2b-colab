package ru.ddc.b2bcolab.payments.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Subscription {

    private String userId;
    private String currentPlan;
    private LocalDate subscriptionStartDate;

}
