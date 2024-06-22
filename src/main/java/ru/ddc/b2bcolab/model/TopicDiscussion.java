package ru.ddc.b2bcolab.model;

import lombok.*;

@Description("Класс, представляющий тему для обсуждения или рекомендации")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TopicDiscussion {

    private Long id; // Уникальный идентификатор темы обсуждения
    private String name; // Название обсуждаемой темы
    private Long brandId; // Идентификатор бренда, связанного с темой обсуждения

}
