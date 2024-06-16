package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.ddc.b2bcolab.repository.TariffFeatures;
import ru.ddc.b2bcolab.repository.TariffType;
import java.time.LocalDate;

@Description("Класс, представляющий тариф с указанием его типа, дат начала и окончания, а также набора функциональных возможностей.")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Tariff {
    private Long id;
    private TariffType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private TariffFeatures features;


    public static class LiteMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            // Реализация возможностей для LITE_MATCH
            System.out.println("Applying Lite Match features");
        }
    }

    public static class ComfortMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            // Реализация возможностей для COMFORT_MATCH
            System.out.println("Applying Comfort Match features");
        }
    }

    public static class BusinessMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            // Реализация возможностей для BUSINESS_MATCH
            System.out.println("Applying Business Match features");
        }
    }
}
