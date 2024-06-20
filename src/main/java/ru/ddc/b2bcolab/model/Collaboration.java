package ru.ddc.b2bcolab.model;

import lombok.*;
import java.time.LocalDateTime;

@Description(value = "Класс Collaboration представляет информацию о коллаборации, включая уникальный идентификатор, " +
        "артнера, связанный бренд, а также даты создания и последнего обновления.")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Collaboration {

    private long id; // Уникальный идентификатор коллаборации
    private String with_whom; // С кем проводится коллаборация
    private long brand_id; // Ссылка на объект Brand, с которым связана коллаборация
    private LocalDateTime created_at; // Дата и время создания коллаборации
    private LocalDateTime updated_at; // Последнее обновление коллаборации

}
