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
    private String socialMediaLink; // Ссылка на социальные сети бренда
    private String brandValuesCharacter; // Характеристика ценностей бренда
    private String targetAudience; // Целевая аудитория бренда
    private String contactPersonName; // Имя контактного лица бренда
    private String founderInterests; // Интересы основателя бренда
    private String category; // Категория бизнеса бренда
    private Integer subscriberCount; // Количество подписчиков бренда
    private String geo; // Геопозиционирование бренда
    private Integer countBall; // Количество баллов, набранных брендом
    private Long countLike; // Количество лайков, полученных брендом
    private String usernameId; // Идентификатор пользователя в системе
    private Long tariffId; // Ссылка на тариф, который использует бренд
    private Customer customer; // Ссылка на объект Customer, указывающая на связь с клиентом
    private Set<Collaba> collabas = new HashSet<>(); // Множество объектов Collaba, представляющих собой коллаборации, в которых участвует бренд
    private Set<Like> likesGiven = new HashSet<>(); // Множество лайков, которые бренд поставил
    private Set<Like> likesReceived = new HashSet<>(); // Множество лайков, которые бренд получил


}
