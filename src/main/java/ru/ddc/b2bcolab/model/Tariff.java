package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tariff {
    private String nameTariff;
    private boolean telegram;
    private boolean accessToLc;
    private boolean brandCatalog;
    private boolean telegramTags;
    private boolean telegramLikes;
    private boolean modernCollab;
    private boolean selectionBrands;
    private boolean message;
    private int price;
    private String period;
}