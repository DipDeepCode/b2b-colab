create table users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    email VARCHAR(500) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL,
    is_registration BOOLEAN NOT NULL,
    is_collaboration BOOLEAN NOT NULL,
    brand_id INT,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE users IS "Таблица хранения пользователей";
COMMENT ON COLUMN users.username IS "Имя пользователя";
COMMENT ON COLUMN users.password IS "Пароль пользователя";
COMMENT ON COLUMN users.email IS "Почта пользователя";
COMMENT ON COLUMN users.phone IS "Номер телефона пользователя";
COMMENT ON COLUMN users.enabled IS "Проверка существует ли пользователь";
COMMENT ON COLUMN users.is_registration IS "Проверка регистрации";
COMMENT ON COLUMN users.is_collaboration IS "Проверка коллаборации между брендами";


create table authorities (
    username  varchar_ignorecase(50) NOT NULL,
    authority varchar_ignorecase(50) NOT NULL,
    constraint fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);
create unique index ix_auth_username on authorities (username, authority);

CREATE TABLE brands (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    social_media_link VARCHAR(255) NOT NULL,
    brand_values_character TEXT,
    target_audience TEXT,
    contact_person_name VARCHAR(255),
    founder_interests TEXT,
    category VARCHAR(255) NOT NULL,
    subscriber_count INT NOT NULL,
    geo VARCHAR(255) NOT NULL,
    count_ball INT NOT NULL,
    count_like DECIMAL NOT NULL,
    brand_value_id INT,
    brand_character_id INT,
    target_audience_id INT,
    interests_id INT,
    category_id INT,
    FOREIGN KEY (brand_value_id) REFERENCES brand_value (id),
    FOREIGN KEY (brand_character_id) REFERENCES brand_character (id),
    FOREIGN KEY (target_audience_id) REFERENCES target_audience_description (id),
    FOREIGN KEY (interests_id) REFERENCES interests (id),
    FOREIGN KEY (category_id) REFERENCES category (id),
    FOREIGN KEY (save_tariffs_id) REFERENCES save_tariffs (id)
);

COMMENT ON TABLE brands IS "Таблица для хранения карточки бренда";
COMMENT ON COLUMN brands.name IS "Название бренда";
COMMENT ON COLUMN brands.social_media_link IS "Соцсети";
COMMENT ON COLUMN brands.brand_values_character IS "Характер и ценности бренда";
COMMENT ON COLUMN brands.target_audience IS "Целевая аудитория";
COMMENT ON COLUMN brands.contact_person_name IS "Контактное лицо бренда";
COMMENT ON COLUMN brands.founder_interests IS "Интересы основателя бренда";
COMMENT ON COLUMN brands.category IS "Категория бренда";
COMMENT ON COLUMN brands.subscriber_count IS "Количество подписчиков";
COMMENT ON COLUMN brands.geo IS "ГЕО";
COMMENT ON COLUMN brands.count_ball IS "Количество баллов";
COMMENT ON COLUMN brands.count_like IS "Количество лайков";


CREATE TABLE brand_value (
    id SERIAL PRIMARY KEY,
    valueBrand TEXT
);

comment on table brand_value is "Таблица хранения возможных ценностей бренда";
comment on column brand_value.valueBrand is "Список ценностей";

CREATE TABLE brand_character (
    id SERIAL PRIMARY KEY,
    nameValue VARCHAR(255)
);

comment on table brand_character is "Таблица хранения возможных характеров бренда";
comment on column brand_character.nameValue is "Список характеров";

CREATE TABLE target_audience_description (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    startup TEXT,
    wives_of_rich_husbands TEXT,
    salaried_employees TEXT,
    agencies_and_freelance TEXT
);

comment on table target_audience_description is "Таблица хранения допустимого выбора целевой аудитории продукта";
comment on column target_audience_description.name is "Название направления";
comment on column target_audience_description.startup is "Стартап с первыми продажами";
comment on column target_audience_description.wives_of_rich_husbands is "Жены богатых мужей";
comment on column target_audience_description.salaried_employees is "Наемные сотрудники, которые руководят коммуникационны ми блоками";
comment on column target_audience_description.agencies_and_freelance is "Агентства и фрилансеры-эксперты";

CREATE TABLE interests (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

comment on table interests is "Таблица хранения допустимых интересов основателя";
comment on column interests.name is "Интерес";

CREATE TABLE category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

comment on table category is "Таблица хранения допустимых категорий";
comment on column category.name is "Название категории";

CREATE TABLE collaba (
    id SERIAL PRIMARY KEY,
    with_whom VARCHAR(255) NOT NULL,
    FOREIGN KEY (brand_id) INT REFERENCES brands (id)
);

comment on table collaba is "Таблица хранения коллаб";
comment on column collaba.with_whom is "С кем заключена коллаба"

CREATE TABLE likes (
    id SERIAL PRIMARY KEY,
    from_the_brand VARCHAR(255) NOT NULL,
    to_the_brand VARCHAR(255) NOT NULL,
    FOREIGN KEY (brand_id) INT REFERENCES brands (id)
);

comment on table likes is "Таблица хранения лайков";
comment on column likes.from_the_brand is "От кого поставлен лайк";
comment on column likes.to_the_brand is "Кому поставлен лайк";

CREATE TABLE tariffs (
    id SERIAL PRIMARY KEY,
    name_tariff VARCHAR(50) NOT NULL,
    telegram BOOLEAN NOT NULL,
    access_to_lc BOOLEAN NOT NULL,
    brand_catalog BOOLEAN NOT NULL,
    telegram_tags BOOLEAN NOT NULL,
    telegram_likes BOOLEAN NOT NULL,
    modern_collab BOOLEAN NOT NULL,
    selection_brands BOOLEAN NOT NULL,
    message BOOLEAN NOT NULL,
    price INT NOT NULL,
    period VARCHAR(255)
);
comment on table tariffs is "Таблица хранения доступных тарифов";
comment on column tariffs.name_tariff is "Название тарифа";
comment on column tariffs.telegram is "Доступ к чату в Telegram";
comment on column tariffs.access_to_lc is "Доступ к ЛК с каталогом брендов";
comment on column tariffs.brand_catalog is "Размещение в каталоге брендов";
comment on column tariffs.telegram_tags is "Оповещение в каталогах о метчах";
comment on column tariffs.telegram_likes is "Оповещение в Telegram о лайках";
comment on column tariffs.modern_collab is "Модерация и фасилитация ваших коллабораций";
comment on column tariffs.selection_brands is "Индивидуальная подборка брендов для коллаб и помощь с выходом на ЛПР (до 3 брендов)";
comment on column tariffs.message is "Возможность отправлять сопроводительное сообщение на тарифе комфорт и бизнес (+чтобы эта опция продавалась лайт тарифу с возможностью перехода на комфорт и бизнес)";
comment on column tariffs.price is "Цена тарифа";
comment on column tariffs.period is "Время доступа к сервису";

CREATE TABLE save_tariffs (
    id SERIAL PRIMARY KEY,
    date_active DATE NOT NULL,
    FOREIGN KEY (tariffs_id) INT REFERENCES tariffs (id),
    FOREIGN KEY (brand_id) INT REFERENCES brands (id)
);

comment on table save_tariffs is "Таблица хранения тарифов пользователей";
comment on column id_brands is "Дата активации тарифа"