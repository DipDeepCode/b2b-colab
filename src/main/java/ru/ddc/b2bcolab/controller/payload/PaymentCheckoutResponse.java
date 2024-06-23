package ru.ddc.b2bcolab.controller.payload;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentCheckoutResponse {
    private int amount;
    private String stripePublicKey;
    private PaymentCurrency paymentCurrency;
    private Long tariffPlanId;
}
