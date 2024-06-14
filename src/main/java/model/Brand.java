package model;

import lombok.*;
import java.util.HashSet;
import java.util.Set;
@Description("Класс Brand представляет собой модель данных для бренда. Он содержит информацию о бренде," +
        " такую как его название, социальные медиа ссылки, целевая аудитория, контактные данные, " +
        "а также статистику и взаимосвязи с другими сущностями в системе, такими как коллаборации, " +
        "   лайки и клиенты.")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Brand {
    private long id; // Уникальный идентификатор бренда
    private String name; // Название бренда
    private String social_media_link; // Ссылка на социальные медиа бренда
    private String brand_values_character; // Характеристика ценностей бренда
    private String target_audience; // Целевая аудитория бренда
    private String contact_person_name; // Имя контактного лица бренда
    private String founder_interests; // Интересы основателя бренда
    private String category; // Категория бренда
    private int subscriber_count; // Количество подписчиков бренда
    private String geo; // Географическое положение бренда
    private int count_ball; // Количество баллов бренда
    private long count_like; // Количество лайков бренда
    private String username_id; // Идентификатор пользователя бренда
    private long tariff_id; // Идентификатор тарифа бренда
    private Customer customer; // Связь с классом Customer, представляющим клиента бренда
    private Set<Collaba> collabas = new HashSet<>(); // Множество коллабораций, в которых участвует бренд
    private Set<Like> likesGiven = new HashSet<>(); // Множество лайков, которые бренд поставил другим
    private Set<Like> likesReceived = new HashSet<>(); // Множество лайков, которые бренд получил от других
}
