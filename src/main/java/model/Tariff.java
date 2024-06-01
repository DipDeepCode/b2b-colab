package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Tariff {
    private long id;
    private Role name_tariff;
    private boolean telegram;
    private boolean access_to_lc;
    private boolean brand_catalog;
    private boolean telegram_tags;
    private boolean telegram_likes;
    private boolean modern_collab;
    private boolean selection_brandsOT;
    private boolean message;
    private double price;
    private String period;

    @Override
    public String toString() {
        return "Tariff{" +
                "id=" + id +
                ", name_tariff=" + name_tariff +
                ", telegram=" + telegram +
                ", access_to_lc=" + access_to_lc +
                ", brand_catalog=" + brand_catalog +
                ", telegram_tags=" + telegram_tags +
                ", telegram_likes=" + telegram_likes +
                ", modern_collab=" + modern_collab +
                ", selection_brandsOT=" + selection_brandsOT +
                ", message=" + message +
                ", price=" + price +
                ", period='" + period + '\'' +
                '}';
    }
}
