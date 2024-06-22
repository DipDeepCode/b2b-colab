package ru.ddc.b2bcolab.model;

import lombok.*;

@Description("Класс, представляющий цели коллабораций")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GoalCollaboration {

    private Long id; // Уникальный идентификатор цели коллаборации
    private String name; // Название цели коллаборации
    private Long brandId; // Идентификатор бренда, связанного с целью коллаборации

}