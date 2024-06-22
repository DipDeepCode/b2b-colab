CREATE TABLE brand_values
(
    id BIGSERIAL PRIMARY KEY,
    value VARCHAR(255) NOT NULL,
    brand_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);

COMMENT ON TABLE brand_values IS 'Таблица ключевых ценностей бренда';
COMMENT ON COLUMN brand_values.id IS 'Уникальный идентификатор ключевой ценности';
COMMENT ON COLUMN brand_values.value IS 'Ключевая ценность бренда';
COMMENT ON COLUMN brand_values.brand_id IS 'Идентификатор бренда, с которым связана ключевая ценность';