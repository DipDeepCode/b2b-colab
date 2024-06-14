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

CREATE TABLE authority (
   phone_number VARCHAR(50) NOT NULL, -- Идентификатор клиента
   role VARCHAR(50) NOT NULL, -- Роль клиента
   FOREIGN KEY (phone_number) REFERENCES customers (phone_number) -- Внешний ключ на таблицу customers
);

COMMENT ON TABLE authority IS 'Таблица ролей пользователя';
COMMENT ON COLUMN authority.phone_number IS 'Внешний ключ на таблицу customers';
COMMENT ON COLUMN authority.role IS 'Роль пользователя';

CREATE TABLE tariffs (
    tariff_id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор тарифа
    name VARCHAR(255) NOT NULL, -- Название тарифа
    description TEXT, -- Описание тарифа
    price DECIMAL(10, 2) NOT NULL, -- Цена тарифа
    start_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Дата начала действия тарифа
    end_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP + INTERVAL '1 year', -- Дата окончания действия тарифа
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Дата и время создания записи о тарифе
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- Дата и время последнего обновления записи о тарифе
);

COMMENT ON TABLE tariffs IS 'Таблица хранения информации о тарифах';
COMMENT ON COLUMN tariffs.tariff_id IS 'Уникальный идентификатор тарифа';
COMMENT ON COLUMN tariffs.name IS 'Название тарифа';
COMMENT ON COLUMN tariffs.description IS 'Описание тарифа';
COMMENT ON COLUMN tariffs.price IS 'Цена тарифа';
COMMENT ON COLUMN tariffs.start_date IS 'Дата начала действия тарифа';
COMMENT ON COLUMN tariffs.end_date IS 'Дата окончания действия тарифа';
COMMENT ON COLUMN tariffs.created_at IS 'Дата и время создания записи о тарифе';
COMMENT ON COLUMN tariffs.updated_at IS 'Дата и время последнего обновления записи о тарифе';

-- Функция update_updated_at_column() обновляет поле updated_at в строке, которая подвергается изменению,
-- устанавливая его в текущее время и дату. Это нужно для отслеживания последнего времени обновления записи.
CREATE OR REPLACE FUNCTION update_updated_at_column()
    RETURNS TRIGGER AS $$
BEGIN
    NEW.updated_at = CURRENT_TIMESTAMP;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Триггер update_tariffs_updated_at вызывает функцию update_updated_at_column() перед каждым обновлением строки
-- в таблице tariffs. Это позволяет автоматически обновлять поле updated_at при любом изменении данных в таблице.
-- Действие триггера происходит до выполнения операции обновления (BEFORE UPDATE), что гарантирует обновление
-- поля updated_at с текущим временем и датой непосредственно перед сохранением изменений в базе данных.
CREATE TRIGGER update_tariffs_updated_at
    BEFORE UPDATE ON tariffs
    FOR EACH ROW
EXECUTE FUNCTION update_updated_at_column();

CREATE TABLE Brand (
    id BIGSERIAL PRIMARY KEY, -- Первичный ключ
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
    tariff_id BIGINT REFERENCES tariffs(tariff_id), -- Внешний ключ на таблицу tariffs
    customer_phoneNumber VARCHAR(50) REFERENCES customers(phone_number) -- Внешний ключ на таблицу customers
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
    id BIGSERIAL PRIMARY KEY, -- Первичный ключ
    with_whom VARCHAR(255), -- С кем коллаборация
    brand_id BIGINT REFERENCES Brand(id) -- Внешний ключ на Brand
);

COMMENT ON TABLE Collaba IS 'Таблица хранения информации о коллаборациях';
COMMENT ON COLUMN Collaba.id IS 'Уникальный идентификатор коллаборации';
COMMENT ON COLUMN Collaba.with_whom IS 'С кем коллаборация';
COMMENT ON COLUMN Collaba.brand_id IS 'Ссылка на бренд';

CREATE TABLE Likes (
    id BIGINT PRIMARY KEY, -- Уникальный идентификатор лайка
    timestamp TIMESTAMP NOT NULL, -- Временная метка, когда был поставлен лайк
    fromBrand_id BIGINT, -- Идентификатор бренда, который поставил лайк
    toBrand_id BIGINT, -- Идентификатор бренда, которому был поставлен лайк
    FOREIGN KEY (fromBrand_id) REFERENCES Brand(id), -- Внешний ключ, ссылающийся на идентификатор бренда, который поставил лайк
    FOREIGN KEY (toBrand_id) REFERENCES Brand(id) -- Внешний ключ, ссылающийся на идентификатор бренда, которому был поставлен лайк
);

COMMENT ON TABLE Likes IS 'Таблица хранения информации о лайках';
COMMENT ON COLUMN Likes.id IS 'Уникальный идентификатор лайка';
COMMENT ON COLUMN Likes.fromBrand_id IS 'От кого лайк';
COMMENT ON COLUMN Likes.toBrand_id IS 'Кому лайк';

CREATE TABLE questionnaire (
    id BIGSERIAL PRIMARY KEY, -- Уникальный идентификатор анкеты
    telegram_nickname VARCHAR(255), -- Никнейм в Telegram
    birth_date DATE, -- Дата рождения
    position VARCHAR(255), -- Должность
    communication_topics TEXT, -- Темы для общения
    is_speaker BOOLEAN, -- Является ли спикером
    community VARCHAR(255), -- Сообщество
    logo_path VARCHAR(255), -- Путь к логотипу
    product_photo_path VARCHAR(255), -- Путь к фотографии продукта
    brand_name VARCHAR(255), -- Название бренда
    business_category VARCHAR(255), -- Категория бизнеса
    brand_type VARCHAR(255), -- Тип бренда
    forbidden_social_media_link VARCHAR(255), -- Запрещенная ссылка на социальные медиа
    website_or_marketplace_link VARCHAR(255), -- Ссылка на сайт или маркетплейс
    subscriber_count INT, -- Количество подписчиков
    average_check DOUBLE, -- Средний чек
    product_uniqueness TEXT, -- Уникальность продукта
    problem_solved TEXT, -- Решаемая проблема
    interaction_formats TEXT, -- Форматы взаимодействия
    collaboration_goal VARCHAR(255), -- Цель сотрудничества
    interested_categories TEXT, -- Интересные категории
    brand_territory VARCHAR(255), -- Территория бренда
    business_essence TEXT, -- Сущность бизнеса
    brand_values TEXT, -- Ценности бренда
    selected_brand_values TEXT, -- Выбранные ценности бренда
    target_audience_description TEXT, -- Описание целевой аудитории
    selected_target_audience_categories TEXT, -- Выбранные категории целевой аудитории
    customer_id VARCHAR(255), -- Идентификатор клиента
    brand_id BIGINT, -- Идентификатор бренда
    FOREIGN KEY (customer_id) REFERENCES customers(phone_number), -- Внешний ключ на таблицу customers по номеру телефона
    FOREIGN KEY (brand_id) REFERENCES brand(id) -- Внешний ключ на таблицу brand по идентификатору
);

COMMENT ON TABLE questionnaire IS 'Таблица анкеты, содержащая подробную информацию о бренде.';
COMMENT ON COLUMN questionnaire.id IS 'Уникальный идентификатор анкеты.';
COMMENT ON COLUMN questionnaire.telegram_nickname IS 'Ник в Telegram представителя бренда.';
COMMENT ON COLUMN questionnaire.birth_date IS 'Дата рождения представителя бренда.';
COMMENT ON COLUMN questionnaire.position IS 'Должность представителя бренда.';
COMMENT ON COLUMN questionnaire.communication_topics IS 'Темы для общения и рекомендаций, представляющие интерес для бренда.';
COMMENT ON COLUMN questionnaire.is_speaker IS 'Флаг, указывающий на готовность представителя быть спикером или участвовать в публичных выступлениях.';
COMMENT ON COLUMN questionnaire.community IS 'Название или ссылка на сообщество/комьюнити предпринимателей, связанное с брендом.';
COMMENT ON COLUMN questionnaire.logo_path IS 'Путь к логотипу бренда.';
COMMENT ON COLUMN questionnaire.product_photo_path IS 'Путь к фотографии продукта бренда.';
COMMENT ON COLUMN questionnaire.brand_name IS 'Название бренда.';
COMMENT ON COLUMN questionnaire.business_category IS 'Категория бизнеса, к которой относится бренд.';
COMMENT ON COLUMN questionnaire.brand_type IS 'Тип бренда (online или offline).';
COMMENT ON COLUMN questionnaire.forbidden_social_media_link IS 'Ссылка на страницу бренда в запрещенной социальной сети.';
COMMENT ON COLUMN questionnaire.website_or_marketplace_link IS 'Ссылка на сайт бренда или маркетплейс.';
COMMENT ON COLUMN questionnaire.subscriber_count IS 'Количество подписчиков бренда в запрещенной сети.';
COMMENT ON COLUMN questionnaire.average_check IS 'Средний чек бренда.';
COMMENT ON COLUMN questionnaire.product_uniqueness IS 'Описание уникальности продукта бренда.';
COMMENT ON COLUMN questionnaire.problem_solved IS 'Описание проблемы, которую решает продукт для клиента.';
COMMENT ON COLUMN questionnaire.interaction_formats IS 'Интересующие форматы для взаимодействия с брендом.';
COMMENT ON COLUMN questionnaire.collaboration_goal IS 'Цель коллаборации, которую преследует бренд.';
COMMENT ON COLUMN questionnaire.interested_categories IS 'Категории бизнеса, представляющие интерес для потенциальных коллабораций.';
COMMENT ON COLUMN questionnaire.brand_territory IS 'Территория представленности бренда.';
COMMENT ON COLUMN questionnaire.business_essence IS 'Суть и ключевая миссия бизнеса бренда.';
COMMENT ON COLUMN questionnaire.brand_values IS 'Описание ключевых ценностей бренда.';
COMMENT ON COLUMN questionnaire.selected_brand_values IS 'Выбранные ценности бренда для мэтчинга.';
COMMENT ON COLUMN questionnaire.target_audience_description IS 'Описание целевой аудитории бренда.';
COMMENT ON COLUMN questionnaire.selected_target_audience_categories IS 'Выбранные категории целевой аудитории для мэтчинга.';
COMMENT ON COLUMN questionnaire.customer_id IS 'Внешний ключ на таблицу customers, представляющий клиента бренда.';
COMMENT ON COLUMN questionnaire.brand_id IS 'Внешний ключ на таблицу Brand, представляющий бренд.';