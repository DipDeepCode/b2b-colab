CREATE TABLE interaction_formats
(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE interaction_formats IS 'Таблица интересующих форматов взаимодействия';
COMMENT ON COLUMN interaction_formats.id IS 'Уникальный идентификатор формата взаимодействия';
COMMENT ON COLUMN interaction_formats.name IS 'Название формата взаимодействия';
COMMENT ON COLUMN interaction_formats.brand_id IS 'Идентификатор бренда, связанного с форматом взаимодействия';