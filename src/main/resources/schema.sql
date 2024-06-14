-- Создание таблицы Customer
CREATE TABLE customers (
   phone_number VARCHAR(50) NOT NULL PRIMARY KEY, -- Уникальный идентификатор клиента
   email VARCHAR(500) NOT NULL UNIQUE, -- Почта клиента
   password VARCHAR(500) NOT NULL, -- Пароль клиента
   enabled BOOLEAN NOT NULL -- Активация аккаунта
);

COMMENT ON TABLE customers IS 'Таблица пользователей';
COMMENT ON COLUMN customers.phone_number IS 'Номер телефона пользователя';
COMMENT ON COLUMN customers.email IS 'Почта пользователя';
COMMENT ON COLUMN customers.password IS 'Пароль пользователя';
COMMENT ON COLUMN customers.enabled IS 'Пользователь активирован';

-- Создание таблицы Authority
CREATE TABLE authority (
   phone_number VARCHAR(50) NOT NULL, -- Идентификатор клиента
   role VARCHAR(50) NOT NULL, -- Роль клиента
   FOREIGN KEY (phone_number) REFERENCES customers (phone_number) -- Внешний ключ на таблицу customers
);

COMMENT ON TABLE authority IS 'Таблица ролей пользователя';
COMMENT ON COLUMN authority.phone_number IS 'Внешний ключ на таблицу customers';
COMMENT ON COLUMN authority.role IS 'Роль пользователя';

-- Создание таблицы Brand
CREATE TABLE Brand (
   id SERIAL PRIMARY KEY, -- Первичный ключ
   name VARCHAR(255) NOT NULL, -- Название бренда
   social_media_link VARCHAR(255), -- Ссылка на соц. сети
   brand_values_character VARCHAR(255), -- Ценности и характер бренда
   target_audience VARCHAR(255), -- Целевая аудитория
   contact_person_name VARCHAR(255), -- Контактное лицо
   founder_interests VARCHAR(255), -- Интересы основателя
   category VARCHAR(255), -- Категория
   subscriber_count INT, -- Количество подписчиков
   geo VARCHAR(255), -- Гео
   count_ball INT, -- Количество баллов
   count_like BIGINT, -- Количество лайков
   username_id VARCHAR(255), -- Идентификатор пользователя
   tariff_id BIGINT, -- Идентификатор тарифа
   customer_phoneNumber VARCHAR(50) REFERENCES customers(phone_number) -- Внешний ключ на Customer
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

-- Создание таблицы Collaba
CREATE TABLE Collaba (
    id SERIAL PRIMARY KEY, -- Первичный ключ
    with_whom VARCHAR(255), -- С кем коллаборация
    brand_id INTEGER REFERENCES Brand(id) -- Внешний ключ на Brand
);

COMMENT ON TABLE Collaba IS 'Таблица хранения информации о коллаборациях';
COMMENT ON COLUMN Collaba.id IS 'Уникальный идентификатор коллаборации';
COMMENT ON COLUMN Collaba.with_whom IS 'С кем коллаборация';
COMMENT ON COLUMN Collaba.brand_id IS 'Ссылка на бренд';

-- Создание таблицы Likes
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
   id SERIAL PRIMARY KEY, -- Первичный ключ
   full_name VARCHAR(255) NOT NULL, -- ФИО
   birth_date DATE NOT NULL, -- Дата рождения
   telegram_nickname VARCHAR(255), -- Ник в телеграм
   brand_name VARCHAR(255), -- Название бренда
   position VARCHAR(255), -- Должность
   category VARCHAR(255), -- Категория
   brand_instagram_link VARCHAR(255), -- Ссылка на инст бренда
   founder_instagram_link VARCHAR(255), -- Ссылка на инст основателя
   telegram_channel_link VARCHAR(255), -- Ссылка на телеграм-канал личный, либо бренда
   website_or_marketplace_link VARCHAR(255), -- Ссылка на сайт/маркетплэйс
   topics_for_communication_and_recommendations TEXT, -- Темы для общения и рекомендаций
   subscriber_count INT, -- Количество подписчиков
   average_check DECIMAL(10, 2), -- Средний чек
   brand_values TEXT, -- Ценности бренда
   target_audience_description TEXT, -- Описание ЦА
   business_location VARCHAR(255), -- Локация бизнеса
   interested_interaction_formats TEXT, -- Интересующие форматы взаимодействия
   collaboration_goal TEXT, -- Цель коллаб
   interested_categories TEXT, -- Интересующие категории
   logo_path VARCHAR(255), -- Логотип
   representative_photo_path VARCHAR(255), -- Фото представителя
   product_photo_path VARCHAR(255), -- Фото продукта
   customer_phone_number VARCHAR(50), -- Внешний ключ на customers
   brand_id INTEGER, -- Внешний ключ на Brand
   FOREIGN KEY (customer_phone_number) REFERENCES customers(phone_number),
   FOREIGN KEY (brand_id) REFERENCES Brand(id)
);

COMMENT ON TABLE Questionnaire IS 'Таблица анкеты';
COMMENT ON COLUMN Questionnaire.id IS 'Уникальный идентификатор анкеты';
COMMENT ON COLUMN Questionnaire.full_name IS 'ФИО';
COMMENT ON COLUMN Questionnaire.birth_date IS 'Дата рождения';
COMMENT ON COLUMN Questionnaire.telegram_nickname IS 'Ник в телеграм';
COMMENT ON COLUMN Questionnaire.brand_name IS 'Название бренда';
COMMENT ON COLUMN Questionnaire.position IS 'Должность';
COMMENT ON COLUMN Questionnaire.category IS 'Категория';
COMMENT ON COLUMN Questionnaire.brand_instagram_link IS 'Ссылка на инст бренда';
COMMENT ON COLUMN Questionnaire.founder_instagram_link IS 'Ссылка на инст основателя';
COMMENT ON COLUMN Questionnaire.telegram_channel_link IS 'Ссылка на телеграм-канал личный, либо бренда';
COMMENT ON COLUMN Questionnaire.website_or_marketplace_link IS 'Ссылка на сайт/маркетплэйс';
COMMENT ON COLUMN Questionnaire.topics_for_communication_and_recommendations IS 'Темы для общения и рекомендаций';
COMMENT ON COLUMN Questionnaire.subscriber_count IS 'Количество подписчиков';
COMMENT ON COLUMN Questionnaire.average_check IS 'Средний чек';
COMMENT ON COLUMN Questionnaire.brand_values IS 'Ценности бренда';
COMMENT ON COLUMN Questionnaire.target_audience_description IS 'Описание ЦА';
COMMENT ON COLUMN Questionnaire.business_location IS 'Локация бизнеса';
COMMENT ON COLUMN Questionnaire.interested_interaction_formats IS 'Интересующие форматы взаимодействия';
COMMENT ON COLUMN Questionnaire.collaboration_goal IS 'Цель коллаб';
COMMENT ON COLUMN Questionnaire.interested_categories IS 'Интересующие категории';
COMMENT ON COLUMN Questionnaire.logo_path IS 'Логотип';
COMMENT ON COLUMN Questionnaire.representative_photo_path IS 'Фото представителя';
COMMENT ON COLUMN Questionnaire.product_photo_path IS 'Фото продукта';
COMMENT ON COLUMN Questionnaire.customer_phone_number IS 'Внешний ключ на таблицу customers';
COMMENT ON COLUMN Questionnaire.brand_id IS 'Внешний ключ на таблицу Brand';