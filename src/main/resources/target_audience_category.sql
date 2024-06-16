CREATE TABLE target_audience_categories
(
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(255) NOT NULL,
    brand_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);

COMMENT ON TABLE target_audience_categories IS 'Таблица категорий целевой аудитории';
COMMENT ON COLUMN target_audience_categories.id IS 'Уникальный идентификатор категории целевой аудитории';
COMMENT ON COLUMN target_audience_categories.category IS 'Категория целевой аудитории';
COMMENT ON COLUMN target_audience_categories.brand_id IS 'Идентификатор бренда, с которым связана категория целевой аудитории';