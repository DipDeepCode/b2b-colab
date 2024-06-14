package model;

import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Like {
    private long id; // Уникальный идентификатор лайка
    private LocalDateTime timestamp; // Время, когда был поставлен лайк
    private Brand from_the_brand; // Связь с Brand
    private Brand to_the_brand; // Связь с Brand
}
