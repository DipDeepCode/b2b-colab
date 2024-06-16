package model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Brand {
    private long id; // Уникальный идентификатор бренда
    private String name; // Название бренда
    private String social_media_link; // Ссылка на профиль бренда в социальных сетях
    private String brand_values_character; // Описание ценностей и характера бренда
    private String target_audience; // Описание целевой аудитории бренда
    private String contact_person_name; // Имя контактного лица бренда
    private String founder_interests; // Описание интересов основателя, связанных с брендом
    private String category; // Категория или тип бренда (например, мода, технология)
    private int subscriber_count; // Количество подписчиков или последователей бренда
    private String geo; // Географическое местоположение или фокус бренда
    private int count_ball; // Количество чего-то связанного с брендом, возможно, показатели вовлеченности
    private long count_like; // Общее количество лайков, полученных брендом
    private String username_id; // Уникальный идентификатор имени пользователя бренда на платформе
    private long tariff_id; // Идентификатор подписки или тарифного плана бренда
    private Customer customer; // Ссылка на объект Customer, указывающая на связь с клиентом
    private Set<Collaba> collabas = new HashSet<>(); // Множество объектов Collaba, представляющих собой коллаборации, в которых участвует бренд
    private Set<Like> likesGiven = new HashSet<>(); // Множество лайков, которые бренд поставил
    private Set<Like> likesReceived = new HashSet<>(); // Множество лайков, которые бренд получил
}
