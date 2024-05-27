create table users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    email VARCHAR(500) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    enabled BOOLEAN NOT NULL,
    is_moderation BOOLEAN NOT NULL,
);

COMMENT ON TABLE users IS 'Таблица хранения пользователей';
COMMENT ON COLUMN users.username IS 'Имя пользователя';
COMMENT ON COLUMN users.password IS 'Пароль пользователя';
COMMENT ON COLUMN users.email IS 'Почта пользователя';
COMMENT ON COLUMN users.phone IS 'Номер телефона пользователя';
COMMENT ON COLUMN users.enabled IS 'Проверка существует ли пользователь';
COMMENT ON COLUMN users.is_moderation IS 'Проверка модерации';


create table authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
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
    username_id INT,
    tariff_id INT,
    FOREIGN KEY (tariff_id) REFERENCES tariffs (id),
    FOREIGN KEY (username_id) REFERENCES users (username)
);

COMMENT ON TABLE brands IS 'Таблица для хранения карточки бренда';
COMMENT ON COLUMN brands.name IS 'Название бренда';
COMMENT ON COLUMN brands.social_media_link IS 'Соцсети';
COMMENT ON COLUMN brands.brand_values_character IS 'Характер и ценности бренда';
COMMENT ON COLUMN brands.target_audience IS 'Целевая аудитория';
COMMENT ON COLUMN brands.contact_person_name IS 'Контактное лицо бренда';
COMMENT ON COLUMN brands.founder_interests IS 'Интересы основателя бренда';
COMMENT ON COLUMN brands.category IS 'Категория бренда';
COMMENT ON COLUMN brands.subscriber_count IS 'Количество подписчиков';
COMMENT ON COLUMN brands.geo IS 'ГЕО';
COMMENT ON COLUMN brands.count_ball IS 'Количество баллов';
COMMENT ON COLUMN brands.count_like IS 'Количество лайков';

CREATE TABLE collaba (
    id SERIAL PRIMARY KEY,
    with_whom VARCHAR(255) NOT NULL,
    brand_id INT,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE collaba IS 'Таблица хранения коллаб';
COMMENT ON COLUMN collaba.with_whom IS 'С кем заключена коллаба';

CREATE TABLE likes (
    id SERIAL PRIMARY KEY,
    from_the_brand VARCHAR(255) NOT NULL,
    to_the_brand VARCHAR(255) NOT NULL,
    brand_id INT,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE likes IS 'Таблица хранения лайков';
COMMENT ON COLUMN likes.from_the_brand IS 'От кого поставлен лайк';
COMMENT ON COLUMN likes.to_the_brand IS 'Кому поставлен лайк';

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
    period VARCHAR(255) NOT NULL
);

COMMENT ON TABLE tariffs IS 'Таблица хранения доступных тарифов';
COMMENT ON COLUMN tariffs.name_tariff IS 'Название тарифа';
COMMENT ON COLUMN tariffs.telegram IS 'Доступ к чату в Telegram';
COMMENT ON COLUMN tariffs.access_to_lc IS 'Доступ к ЛК с каталогом брендов';
COMMENT ON COLUMN tariffs.brand_catalog IS 'Размещение в каталоге брендов';
COMMENT ON COLUMN tariffs.telegram_tags IS 'Оповещение в каталогах о метчах';
COMMENT ON COLUMN tariffs.telegram_likes IS 'Оповещение в Telegram о лайках';
COMMENT ON COLUMN tariffs.modern_collab IS 'Модерация и фасилитация ваших коллабораций';
COMMENT ON COLUMN tariffs.selection_brands IS 'Индивидуальная подборка брендов для коллаб и помощь с выходом на ЛПР (до 3 брендов)';
COMMENT ON COLUMN tariffs.message IS 'Возможность отправлять сопроводительное сообщение на тарифе комфорт и бизнес (+чтобы эта опция продавалась лайт тарифу с возможностью перехода на комфорт и бизнес)';
COMMENT ON COLUMN tariffs.price IS 'Цена тарифа';
COMMENT ON COLUMN tariffs.period IS 'Время доступа к сервису';
