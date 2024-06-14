package model;

import lombok.*;
import java.time.LocalDateTime;

@Description("Класс Like представляет собой модель данных для лайка между брендами. " +
        "Он содержит информацию о лайке, включая идентификатор лайка, время, когда был поставлен лайк, " +
        "и связи с брендами, которые участвуют в лайке.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Like {
    private long id; // Уникальный идентификатор лайка
    private LocalDateTime timestamp; // Время, когда был поставлен лайк
    private Brand from_the_brand; // Бренд, который поставил лайк
    private Brand to_the_brand; // Бренд, которому был поставлен лайк
}
