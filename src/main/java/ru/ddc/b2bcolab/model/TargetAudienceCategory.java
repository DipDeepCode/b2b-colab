package ru.ddc.b2bcolab.model;

import lombok.*;

@Description("Класс, представляющий категории целевой аудитории")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TargetAudienceCategory {

    private Long id; // Уникальный идентификатор категории целевой аудитории
    private String category; // Категория целевой аудитории
    private Long brandId; // Идентификатор бренда, с которым связана категория целевой аудитории

}