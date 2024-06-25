package ru.ddc.b2bcolab.model;

import lombok.*;
import ru.ddc.b2bcolab.repository.TariffFeatures;
import ru.ddc.b2bcolab.repository.TariffType;
import java.time.LocalDate;

@Description("Класс, представляющий тариф с указанием его типа, дат начала и окончания, " +
        "а также набора функциональных возможностей.")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class Tariff {
    private Long id;
    private TariffType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private long brandId;

    private TariffFeatures features;

    public Tariff(TariffType type, LocalDate startDate, long brandId) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = startDate.plusYears(1); // Пример: тариф на 1 год
        this.features = createFeatures(type);
        this.brandId = brandId;
    }

    public TariffFeatures createFeatures(TariffType type) {
        // Создание функций на основе типа тарифа
        switch (type) {
            case LITE_MATCH:
                return new LiteMatchFeatures();
            case COMFORT_MATCH:
                return new ComfortMatchFeatures();
            case BUSINESS_MATCH:
                return new BusinessMatchFeatures();
            default:
                return null;
        }
    }

    public static class LiteMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            System.out.println("Applying Lite Match features");
        }
    }

    public static class ComfortMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            System.out.println("Applying Comfort Match features");
        }
    }

    public static class BusinessMatchFeatures implements TariffFeatures {
        @Override
        public void applyFeatures() {
            System.out.println("Applying Business Match features");
        }
    }
}
