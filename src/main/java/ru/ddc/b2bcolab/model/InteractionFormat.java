package ru.ddc.b2bcolab.model;

import lombok.*;

@Description("Класс, представляющий форматы взаимодействия")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InteractionFormat {

    private Long id; // Уникальный идентификатор формата взаимодействия
    private String name; // Название формата взаимодействия
    private Long brandId; // Идентификатор бренда, связанного с форматом взаимодействия

}
