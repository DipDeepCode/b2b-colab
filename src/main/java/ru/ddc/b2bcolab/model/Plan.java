package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Plan {
    private final String CURRENCY = "RUB";
    private String name;
    private int amount;
    private String stripePublicKey;
}