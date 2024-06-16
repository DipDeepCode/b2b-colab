CREATE TABLE topic_discussions (
   id BIGSERIAL PRIMARY KEY,
   name VARCHAR(100) NOT NULL,
   brand_id BIGINT NOT NULL,
   FOREIGN KEY (brand_id) REFERENCES brands(id)
);

COMMENT ON TABLE topic_discussions IS 'Таблица тем для обсуждения или рекомендации';
COMMENT ON COLUMN topic_discussions.id IS 'Уникальный идентификатор обсуждения темы';
COMMENT ON COLUMN topic_discussions.name IS 'Название обсуждаемой темы';
COMMENT ON COLUMN topic_discussions.brand_id IS 'Идентификатор бренда, связанного с темой обсуждения';