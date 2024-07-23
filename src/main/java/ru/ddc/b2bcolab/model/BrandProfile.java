package ru.ddc.b2bcolab.model;

import lombok.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Description("Класс, представляющий бренд с уникальным идентификатором, номером телефона и названием.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BrandProfile {
    // Информация о бренде
    private long id; // Уникальный идентификатор бренда
    private String customerPhoneNumber; // Номер телефона клиента, связанного с брендом
    private long tariffId; // Ссылка на тариф, который использует бренд
    private Customer customer; // Ссылка на объект Customer, указывающая на связь с клиентом


    // Анкета
    private List<Image> photo; // Фото бренда
    private String lastName; // Фамилия
    private String firstName; // Имя
    private String telegramNick; // Никнейм в Telegram
    private Date birthDate; // Дата рождения
    private String position; // Должность
    private Set<TopicDiscussion> discussionTopics; // Темы для обсуждения или рекомендации
    private String publicSpeakingWillingness; // Готовность к публичным выступлениям
    private String entrepreneurCommunity; // Название или ссылка на сообщество предпринимателей
    private String brandName; // Название бренда
    private String businessCategory; // Категория бизнеса
    private String brandType; // Тип бренда (онлайн/офлайн)
    private String brandedSocialLink; // Ссылка на страницу бренда соц. сети
    private String brandWebsiteLink; // Ссылка на сайт бренда или маркетплейс
    private long followersCount; // Количество подписчиков
    private long averageCheck; // Средний чек
    private String productUniqueness; // Уникальность продукта
    private String customerProblemSolved; // Проблема, решаемая для клиента
    private Set<InteractionFormat> interactionFormats; // Интересующие форматы взаимодействия
    private Set<GoalCollaboration> collaborationGoal; // Цель коллаборации
    private String preferredBusinessCategory; // Предпочитаемая категория для коллаборации
    private String brandPresenceTerritory; // Территория представленности бренда
    private String businessEssence; // Суть и ключевая миссия бизнеса
    private Set<BrandValue> brandValues; // Ключевые ценности бренда
    private String targetAudienceDescription; // Описание целевой аудитории
    private Set<TargetAudienceCategory> targetAudienceCategories; // Категории целевой аудитории

}
