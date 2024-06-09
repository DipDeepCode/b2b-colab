package ru.ddc.b2bcolab.controller.payload;

import lombok.Data;

@Data
public class ChargeResponse {
    private String id;
    private String status;
    private String chargeId;
    private String balanceTransaction;

    public ChargeResponse(String id, String status, String chargeId, String balanceTransaction) {
        this.id = id;
        this.status = status;
        this.chargeId = chargeId;
        this.balanceTransaction = balanceTransaction;
    }


}