package ru.ddc.b2bcolab.model;

import lombok.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Description("Класс, представляющий бренд с уникальным идентификатором, номером телефона и названием.")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class Brand {
    // Информация о бренде
    private long id; // Уникальный идентификатор бренда
    private String customerPhoneNumber; // Номер телефона клиента, связанного с брендом
    private int countBall; // Количество баллов, набранных брендом
    private long countLike; // Количество лайков, полученных брендом
    private long tariffId; // Ссылка на тариф, который использует бренд
    private Customer customer; // Ссылка на объект Customer, указывающая на связь с клиентом
    private Set<Collaboration> collaborations = new HashSet<>(); // Множество объектов Collaboration, представляющих собой коллаборации, в которых участвует бренд
    private Set<Like> likesGiven = new HashSet<>(); // Множество лайков, которые бренд поставил
    private Set<Like> likesReceived = new HashSet<>(); // Множество лайков, которые бренд получил



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


    public Brand(Long id, String customerPhoneNumber, int countBall, long countLike, long tariffId, Customer customer,
                 Set<Collaboration> collaborations, Set<Like> likesGiven, Set<Like> likesReceived,
                 List<Image> photo, String lastName, String firstName, String telegramNick, Date birthDate,
                 String position, Set<TopicDiscussion> discussionTopics, String publicSpeakingWillingness,
                 String entrepreneurCommunity, String brandName, String businessCategory, String brandType,
                 String brandedSocialLink, String brandWebsiteLink, long followersCount, long averageCheck,
                 String productUniqueness, String customerProblemSolved, Set<InteractionFormat> interactionFormats,
                 Set<GoalCollaboration> collaborationGoal, String preferredBusinessCategory, String brandPresenceTerritory,
                 String businessEssence, Set<BrandValue> brandValues, String targetAudienceDescription,
                 Set<TargetAudienceCategory> targetAudienceCategories) {
        this.id = id;
        this.customerPhoneNumber = customerPhoneNumber;
        this.countBall = 0;
        this.countLike = 0;
        this.tariffId = tariffId;
        this.customer = customer;
        this.collaborations = collaborations;
        this.likesGiven = likesGiven;
        this.likesReceived = likesReceived;
        this.photo = photo;
        this.lastName = lastName;
        this.firstName = firstName;
        this.telegramNick = telegramNick;
        this.birthDate = birthDate;
        this.position = position;
        this.discussionTopics = discussionTopics;
        this.publicSpeakingWillingness = publicSpeakingWillingness;
        this.entrepreneurCommunity = entrepreneurCommunity;
        this.brandName = brandName;
        this.businessCategory = businessCategory;
        this.brandType = brandType;
        this.brandedSocialLink = brandedSocialLink;
        this.brandWebsiteLink = brandWebsiteLink;
        this.followersCount = followersCount;
        this.averageCheck = averageCheck;
        this.productUniqueness = productUniqueness;
        this.customerProblemSolved = customerProblemSolved;
        this.interactionFormats = interactionFormats;
        this.collaborationGoal = collaborationGoal;
        this.preferredBusinessCategory = preferredBusinessCategory;
        this.brandPresenceTerritory = brandPresenceTerritory;
        this.businessEssence = businessEssence;
        this.brandValues = brandValues;
        this.targetAudienceDescription = targetAudienceDescription;
        this.targetAudienceCategories = targetAudienceCategories;
    }
}
