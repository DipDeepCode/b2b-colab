package model;

import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Description("Класс Questionnaire представляет собой анкету, " +
        "заполняемую брендом для предоставления подробной информации о себе. " +
        "Он содержит различные поля, такие как имя, дата рождения, ссылки на социальные сети, " +
        "описание целевой аудитории, информацию о продуктах и услугах, а также связи с другими сущностями в " +
        "системе.")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Questionnaire {
    private String telegramNickname; // Ник в Telegram
    private LocalDate birthDate; // Дата рождения
    private String position; // Должность
    private List<String> communicationTopics; // Темы для общения и рекомендаций
    private boolean isSpeaker; // Готовность быть спикером или участвовать в публичном выступлении
    private String community; // Название или ссылка на сообщество/комьюнити предпринимателей
    private String logoPath; // Путь к логотипу
    private String productPhotoPath; // Путь к фотографии продукта
    private String brandName; // Название бренда
    private String businessCategory; // Категория бизнеса
    private String brandType; // Тип бренда (online или offline)
    private String forbiddenSocialMediaLink; // Ссылка на страницу бренда в запрещенной соц. сети
    private String websiteOrMarketplaceLink; // Ссылка на сайт бренда или маркетплейс
    private int subscriberCount; // Количество подписчиков бренда в запрещенной сети
    private double averageCheck; // Средний чек бренда
    private String productUniqueness; // Уникальность продукта
    private String problemSolved; // Проблема, которую решает продукт для клиента
    private List<String> interactionFormats; // Интересующие форматы для взаимодействия
    private String collaborationGoal; // Цель коллаборации
    private List<String> interestedCategories; // Категории бизнеса для потенциальных коллабораций
    private String brandTerritory; // Территория представленности бренда
    private String businessEssence; // Суть и ключевая миссия бизнеса
    private String brandValues; // Описание ключевых ценностей бренда
    private List<String> selectedBrandValues; // Выбранные ценности бренда для мэтчинга
    private String targetAudienceDescription; // Описание целевой аудитории бренда
    private List<String> selectedTargetAudienceCategories; // Выбранные категории целевой аудитории для мэтчинга
    private Customer customer; // Ссылка на Customer, представляющий клиента бренда
    private Brand brand; // Ссылка на Brand, представляющий бренд
}
