package model;

import lombok.*;

import java.time.LocalDateTime;

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
