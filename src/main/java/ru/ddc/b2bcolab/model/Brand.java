package ru.ddc.b2bcolab.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

@Description("Класс, представляющий бренд с уникальным идентификатором, номером телефона и названием.")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Brand {

    private Long id; // Уникальный идентификатор бренда
    private String customerPhoneNumber; // Номер телефона клиента, связанного с брендом
    private String name; // Название бренда
    private Integer countBall; // Количество баллов, набранных брендом
    private Long countLike; // Количество лайков, полученных брендом
    private Long tariffId; // Ссылка на тариф, который использует бренд
    private Customer customer; // Ссылка на объект Customer, указывающая на связь с клиентом
    private Set<Collaboration> collaborations = new HashSet<>(); // Множество объектов Collaboration, представляющих собой коллаборации, в которых участвует бренд
    private Set<Like> likesGiven = new HashSet<>(); // Множество лайков, которые бренд поставил
    private Set<Like> likesReceived = new HashSet<>(); // Множество лайков, которые бренд получил

}
