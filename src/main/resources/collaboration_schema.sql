CREATE TABLE collaborations
(
    id BIGSERIAL PRIMARY KEY,
    brand_id1 BIGINT NOT NULL,
    brand_id2 BIGINT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP,
    FOREIGN KEY (brand_id1) REFERENCES brands(id),
    FOREIGN KEY (brand_id2) REFERENCES brands(id)
);

COMMENT ON TABLE collaborations IS 'Таблица хранения информации о коллаборациях';
COMMENT ON COLUMN collaborations.id IS 'Уникальный идентификатор коллаборации';
COMMENT ON COLUMN collaborations.brand_id1 IS 'Идентификатор первой бренда, участвующего в коллаборации';
COMMENT ON COLUMN collaborations.brand_id2 IS 'Идентификатор второй бренда, участвующего в коллаборации';
COMMENT ON COLUMN collaborations.created_at IS 'Дата и время создания записи о коллаборации';
COMMENT ON COLUMN collaborations.updated_at IS 'Дата и время последнего обновления записи о коллаборации';

