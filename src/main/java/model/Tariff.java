package model;

import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Description("Класс Tariff представляет собой модель данных для тарифного плана, предлагаемого системой. " +
        "Он содержит информацию о различных функциях и возможностях, доступных в рамках тарифа, " +
        "а также о цене и периоде действия тарифа.")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Tariff {
    private Long tariffId; // Уникальный идентификатор тарифа.
    private String name;  // Описание тарифа, предоставляющее более подробную информацию о его функциях и возможностях.
    private String description; // Описание тарифа, предоставляющее более подробную информацию о его функциях и возможностях.
    private BigDecimal price;  // Цена тарифа, выраженная в десятичном формате с двумя знаками после запятой.
    private LocalDateTime startDate;  // Дата начала действия тарифа.
    private LocalDateTime endDate; // Дата окончания действия тарифа.
    private LocalDateTime createdAt; // Дата и время создания записи о тарифе в системе.
    private LocalDateTime updatedAt; // Дата и время последнего обновления записи о тарифе в системе.
}
