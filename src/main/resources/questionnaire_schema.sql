CREATE TABLE questionnaires
(
   id BIGSERIAL PRIMARY KEY,
   favorite_photo TEXT,
   full_name VARCHAR(255) NOT NULL,
   telegram_nickname TEXT,
   instagram_nickname VARCHAR(255),
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
   branded_social_link TEXT,
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
   brand_id BIGINT REFERENCES brands(id)
);

COMMENT ON TABLE questionnaires IS 'Таблица анкеты';
COMMENT ON COLUMN questionnaires.id IS 'Уникальный идентификатор анкеты';
COMMENT ON COLUMN questionnaires.favorite_photo IS 'Ссылка на любимое фото участника';
COMMENT ON COLUMN questionnaires.telegram_nickname IS 'Никнейм участника в Telegram';
COMMENT ON COLUMN questionnaires.birth_date IS 'Дата рождения участника';
COMMENT ON COLUMN questionnaires.position IS 'Должность участника';
COMMENT ON COLUMN questionnaires.discussion_topics IS 'Темы для обсуждения или рекомендации от участника';
COMMENT ON COLUMN questionnaires.public_speaking_willingness IS 'Готовность участника к публичным выступлениям';
COMMENT ON COLUMN questionnaires.entrepreneur_community IS 'Название или ссылка на сообщество предпринимателей, в котором участвует';
COMMENT ON COLUMN questionnaires.brand_logo IS 'Ссылка на логотип бренда участника';
COMMENT ON COLUMN questionnaires.product_illustration IS 'Фотография продукта, предлагаемого участником';
COMMENT ON COLUMN questionnaires.brand_name IS 'Название бренда участника';
COMMENT ON COLUMN questionnaires.business_category IS 'Категория бизнеса участника';
COMMENT ON COLUMN questionnaires.brand_type IS 'Тип бренда участника (онлайн/оффлайн)';
COMMENT ON COLUMN questionnaires.branded_social_link IS 'Ссылка на соц. сети';
COMMENT ON COLUMN questionnaires.brand_website_link IS 'Ссылка на сайт бренда или маркетплейс участника';
COMMENT ON COLUMN questionnaires.followers_count IS 'Количество подписчиков';
COMMENT ON COLUMN questionnaires.average_check IS 'Средний чек покупок в бизнесе участника';
COMMENT ON COLUMN questionnaires.product_uniqueness IS 'Уникальность продукта, предлагаемого участником';
COMMENT ON COLUMN questionnaires.customer_problem_solved IS 'Проблема, решаемая продуктом для клиента';
COMMENT ON COLUMN questionnaires.interaction_formats IS 'Форматы взаимодействия с клиентами';
COMMENT ON COLUMN questionnaires.collaboration_goal IS 'Цель коллаборации участника';
COMMENT ON COLUMN questionnaires.preferred_business_category IS 'Предпочитаемая категория для коллаборации';
COMMENT ON COLUMN questionnaires.brand_presence_territory IS 'Территория представленности бренда';
COMMENT ON COLUMN questionnaires.business_essence IS 'Суть и ключевая миссия бизнеса участника';
COMMENT ON COLUMN questionnaires.brand_values IS 'Ключевые ценности бренда участника';
COMMENT ON COLUMN questionnaires.target_audience_description IS 'Описание целевой аудитории бренда';
COMMENT ON COLUMN questionnaires.target_audience_categories IS 'Категории целевой аудитории бренда';