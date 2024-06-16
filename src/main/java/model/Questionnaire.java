package model;

import lombok.*;
import java.time.LocalDate;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
public class Questionnaire {
    private int id; // Уникальный идентификатор записи
    private String favoritePhoto; // Ссылка на любимое фото
    private String telegramNick; // Никнейм в Telegram
    private Date birthDate; // Дата рождения
    private String position; // Должность
    private String discussionTopics; // Темы для обсуждения или рекомендации
    private boolean publicSpeakingWillingness; // Готовность к публичным выступлениям
    private String entrepreneurCommunity; // Название или ссылка на сообщество предпринимателей
    private String brandLogo; // Ссылка на логотип бренда
    private String productIllustration; // Фотография продукта
    private String brandName; // Название бренда
    private String businessCategory; // Категория бизнеса
    private String brandType; // Тип бренда (онлайн/оффлайн)
    private String bannedSocialLink; // Ссылка на страницу бренда в запрещенной соц. сети
    private String brandWebsiteLink; // Ссылка на сайт бренда или маркетплейс
    private int followersCount; // Количество подписчиков в запрещенной сети
    private int averageCheck; // Средний чек
    private String productUniqueness; // Уникальность продукта
    private String customerProblemSolved; // Проблема, решаемая для клиента
    private String interactionFormats; // Форматы взаимодействия
    private String collaborationGoal; // Цель коллаборации
    private String preferredBusinessCategory; // Предпочитаемая категория для коллаборации
    private String brandPresenceTerritory; // Территория представленности бренда
    private String businessEssence; // Суть и ключевая миссия бизнеса
    private String brandValues; // Ключевые ценности бренда
    private String targetAudienceDescription; // Описание целевой аудитории
    private String targetAudienceCategories; // Категории целевой аудитории
    private Customer customer; // Ссылка на Customer
    private Brand brand; // Ссылка на Brand
}
