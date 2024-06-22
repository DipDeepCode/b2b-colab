package ru.ddc.b2bcolab.model;

import lombok.*;

@Description("Класс, представляющий ключевые ценности бренда")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BrandValue {

    private Long id; // Уникальный идентификатор ключевой ценности
    private String value; // Ключевая ценность бренда
    private Long brandId; // Идентификатор бренда, с которым связана ключевая ценность

}
