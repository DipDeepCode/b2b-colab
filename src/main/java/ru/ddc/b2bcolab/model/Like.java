package ru.ddc.b2bcolab.model;

import lombok.*;
import java.time.LocalDateTime;

@Description("Класс, представляющий лайк от одного бренда к другому с уникальным идентификатором и временем лайка.")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Like {

    private long id; // Уникальный идентификатор лайка
    private LocalDateTime timestamp; // Время, когда был поставлен лайк
    private Brand fromBrand; // Бренд, который поставил лайк
    private Brand toBrand; // Бренд, которому был поставлен лайк

}