CREATE TABLE collaborations
(
    id BIGSERIAL PRIMARY KEY,
    with_whom VARCHAR(100) NOT NULL,
    brand_id BIGINT NOT NULL REFERENCES brands(id),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

COMMENT ON TABLE collaborations IS 'Таблица хранения информации о коллаборациях';
COMMENT ON COLUMN collaborations.id IS 'Уникальный идентификатор коллаборации';
COMMENT ON COLUMN collaborations.with_whom IS 'С кем коллаборация';
COMMENT ON COLUMN collaborations.brand_id IS 'Ссылка на бренд';
