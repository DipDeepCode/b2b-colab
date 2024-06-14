package model;

import lombok.*;

@Description("Класс Collaba представляет собой модель данных для коллаборации между брендами. " +
        "Он содержит информацию о коллаборации, включая идентификатор коллаборации, " +
        "с кем происходит коллаборация, идентификатор и ссылку на бренд, участвующий в коллаборации.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Collaba {
    private long id; // Уникальный идентификатор коллаборации
    private String withWhom; // Информация о том, с кем происходит коллаборация
    private long brandId; // Идентификатор бренда, участвующего в коллаборации
    private Brand brand; // Связь с классом Brand, представляющим бренд, участвующий в коллаборации
}