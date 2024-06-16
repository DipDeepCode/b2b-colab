package ru.ddc.b2bcolab.model;

import lombok.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Collaba {
    private long id; // Уникальный идентификатор коллаборации
    private String with_whom; // С кем проводится коллаборация
    private Brand brand; // Ссылка на объект Brand, с которым связана коллаборация
    private LocalDateTime created_at; // Дата и время создания коллаборации
    private LocalDateTime updated_at; // Последнее обновление коллаборации
}
