CREATE TABLE customers (
   phone_number VARCHAR(50) NOT NULL PRIMARY KEY,
   email VARCHAR(500) NOT NULL UNIQUE,
   password VARCHAR(500) NOT NULL,
   enabled BOOLEAN NOT NULL
);

COMMENT ON TABLE customers IS 'Таблица пользователей';
COMMENT ON COLUMN customers.phone_number IS 'Номер телефона пользователя';
COMMENT ON COLUMN customers.email IS 'Почта пользователя';
COMMENT ON COLUMN customers.password IS 'Пароль пользователя';
COMMENT ON COLUMN customers.enabled IS 'Пользователь активирован';

CREATE TABLE authority (
   phone_number VARCHAR(50) NOT NULL,
   role VARCHAR(50) NOT NULL,
   FOREIGN KEY (phone_number) REFERENCES customers (phone_number)
);

COMMENT ON TABLE authority IS 'Таблица ролей пользователя';
COMMENT ON COLUMN authority.phone_number IS 'Внешний ключ на таблицу customers';
COMMENT ON COLUMN authority.role IS 'Роль пользователя';

CREATE TABLE Tariff (
    id BIGINT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL
);

COMMENT ON TABLE Tariff IS 'Таблица тарифов';
COMMENT ON COLUMN Tariff.id IS 'Уникальный идентификатор тарифа';
COMMENT ON COLUMN Tariff.type IS 'Тип тарифа';
COMMENT ON COLUMN Tariff.startDate IS 'Дата начала действия тарифа';
COMMENT ON COLUMN Tariff.endDate IS 'Дата окончания действия тарифа';

CREATE TABLE Brand (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(255) NOT NULL,
   social_media_link VARCHAR(255),
   brand_values_character VARCHAR(255),
   target_audience VARCHAR(255),
   contact_person_name VARCHAR(255),
   founder_interests VARCHAR(255),
   category VARCHAR(255),
   subscriber_count INT,
   geo VARCHAR(255),
   count_ball INT,
   count_like BIGINT,
   username_id VARCHAR(255),
   tariff_id BIGINT,
   customer_phoneNumber VARCHAR(50) REFERENCES customers(phone_number)
);

COMMENT ON TABLE Brand IS 'Таблица хранения информации о брендах';
COMMENT ON COLUMN Brand.id IS 'Уникальный идентификатор бренда';
COMMENT ON COLUMN Brand.name IS 'Название бренда';
COMMENT ON COLUMN Brand.social_media_link IS 'Ссылка на соц. сети';
COMMENT ON COLUMN Brand.brand_values_character IS 'Ценности и характер бренда';
COMMENT ON COLUMN Brand.target_audience IS 'Целевая аудитория';
COMMENT ON COLUMN Brand.contact_person_name IS 'Контактное лицо';
COMMENT ON COLUMN Brand.founder_interests IS 'Интересы основателя';
COMMENT ON COLUMN Brand.category IS 'Категория';
COMMENT ON COLUMN Brand.subscriber_count IS 'Количество подписчиков';
COMMENT ON COLUMN Brand.geo IS 'Гео';
COMMENT ON COLUMN Brand.count_ball IS 'Количество баллов';
COMMENT ON COLUMN Brand.count_like IS 'Количество лайков';
COMMENT ON COLUMN Brand.username_id IS 'Идентификатор пользователя';
COMMENT ON COLUMN Brand.tariff_id IS 'Идентификатор тарифа';
COMMENT ON COLUMN Brand.customer_phoneNumber IS 'Ссылка на клиента';

CREATE TABLE Collaba (
    id BIGSERIAL PRIMARY KEY,
    with_whom VARCHAR(100) NOT NULL,
    brand_id BIGINT NOT NULL REFERENCES Brand(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

COMMENT ON TABLE Collaba IS 'Таблица хранения информации о коллаборациях';
COMMENT ON COLUMN Collaba.id IS 'Уникальный идентификатор коллаборации';
COMMENT ON COLUMN Collaba.with_whom IS 'С кем коллаборация';
COMMENT ON COLUMN Collaba.brand_id IS 'Ссылка на бренд';

CREATE TABLE Likes (
   id BIGINT PRIMARY KEY,
   timestamp TIMESTAMP NOT NULL,
   fromBrand_id BIGINT,
   toBrand_id BIGINT,
   FOREIGN KEY (fromBrand_id) REFERENCES Brand(id),
   FOREIGN KEY (toBrand_id) REFERENCES Brand(id)
);

COMMENT ON TABLE Likes IS 'Таблица хранения информации о лайках';
COMMENT ON COLUMN Likes.id IS 'Уникальный идентификатор лайка';
COMMENT ON COLUMN Likes.fromBrand_id IS 'От кого лайк';
COMMENT ON COLUMN Likes.toBrand_id IS 'Кому лайк';

CREATE TABLE Questionnaire (
    id BIGSERIAL PRIMARY KEY,
    favorite_photo TEXT,
    telegram_nick TEXT,
    birth_date DATE,
    position TEXT,
    discussion_topics TEXT,
    public_speaking_willingness BOOLEAN,
    entrepreneur_community TEXT,
    brand_logo TEXT,
    product_illustration TEXT,
    brand_name TEXT,
    business_category TEXT,
    brand_type TEXT,
    banned_social_link TEXT,
    brand_website_link TEXT,
    followers_count INTEGER,
    average_check INTEGER,
    product_uniqueness TEXT,
    customer_problem_solved TEXT,
    interaction_formats TEXT,
    collaboration_goal TEXT,
    preferred_business_category TEXT,
    brand_presence_territory TEXT,
    business_essence TEXT,
    brand_values TEXT,
    target_audience_description TEXT,
    target_audience_categories TEXT,
    brand_id BIGINT REFERENCES Brand(id)
);

COMMENT ON TABLE Questionnaire IS 'Таблица анкеты';
COMMENT ON COLUMN Questionnaire.id IS 'Уникальный идентификатор анкеты';
COMMENT ON COLUMN Questionnaire.favorite_photo IS 'Ссылка на любимое фото участника';
COMMENT ON COLUMN Questionnaire.telegram_nick IS 'Никнейм участника в Telegram';
COMMENT ON COLUMN Questionnaire.birth_date IS 'Дата рождения участника';
COMMENT ON COLUMN Questionnaire.position IS 'Должность участника';
COMMENT ON COLUMN Questionnaire.discussion_topics IS 'Темы для обсуждения или рекомендации от участника';
COMMENT ON COLUMN Questionnaire.public_speaking_willingness IS 'Готовность участника к публичным выступлениям';
COMMENT ON COLUMN Questionnaire.entrepreneur_community IS 'Название или ссылка на сообщество предпринимателей, в котором участвует';
COMMENT ON COLUMN Questionnaire.brand_logo IS 'Ссылка на логотип бренда участника';
COMMENT ON COLUMN Questionnaire.product_illustration IS 'Фотография продукта, предлагаемого участником';
COMMENT ON COLUMN Questionnaire.brand_name IS 'Название бренда участника';
COMMENT ON COLUMN Questionnaire.business_category IS 'Категория бизнеса участника';
COMMENT ON COLUMN Questionnaire.brand_type IS 'Тип бренда участника (онлайн/оффлайн)';
COMMENT ON COLUMN Questionnaire.banned_social_link IS 'Ссылка на страницу бренда в запрещенной соц. сети';
COMMENT ON COLUMN Questionnaire.brand_website_link IS 'Ссылка на сайт бренда или маркетплейс участника';
COMMENT ON COLUMN Questionnaire.followers_count IS 'Количество подписчиков в запрещенной сети';
COMMENT ON COLUMN Questionnaire.average_check IS 'Средний чек покупок в бизнесе участника';
COMMENT ON COLUMN Questionnaire.product_uniqueness IS 'Уникальность продукта, предлагаемого участником';
COMMENT ON COLUMN Questionnaire.customer_problem_solved IS 'Проблема, решаемая продуктом для клиента';
COMMENT ON COLUMN Questionnaire.interaction_formats IS 'Форматы взаимодействия с клиентами';
COMMENT ON COLUMN Questionnaire.collaboration_goal IS 'Цель коллаборации участника';
COMMENT ON COLUMN Questionnaire.preferred_business_category IS 'Предпочитаемая категория для коллаборации';
COMMENT ON COLUMN Questionnaire.brand_presence_territory IS 'Территория представленности бренда';
COMMENT ON COLUMN Questionnaire.business_essence IS 'Суть и ключевая миссия бизнеса участника';
COMMENT ON COLUMN Questionnaire.brand_values IS 'Ключевые ценности бренда участника';
COMMENT ON COLUMN Questionnaire.target_audience_description IS 'Описание целевой аудитории бренда';
COMMENT ON COLUMN Questionnaire.target_audience_categories IS 'Категории целевой аудитории бренда';