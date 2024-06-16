package ru.ddc.b2bcolab.model;

import lombok.*;
import java.sql.Timestamp;

@Description(value = "Класс Collaboration представляет информацию о коллаборации, включая уникальный идентификатор, " +
        "идентификаторы двух брендов, участвующих в коллаборации, а также даты создания и последнего обновления.")

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class Collaboration {

    private long id; // Уникальный идентификатор коллаборации
    private long brandId1; // Идентификатор первого бренда, участвующего в коллаборации
    private long brandId2; // Идентификатор второго бренда, участвующего в коллаборации
    private Timestamp createdAt; // Дата и время создания коллаборации
    private Timestamp updatedAt; // Последнее обновление коллаборации

}
