CREATE TABLE goal_collaborations (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    brand_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE goal_collaborations IS 'Таблица целей коллабораций';
COMMENT ON COLUMN goal_collaborations.id IS 'Уникальный идентификатор цели коллаборации';
COMMENT ON COLUMN goal_collaborations.name IS 'Название цели коллаборации';
COMMENT ON COLUMN goal_collaborations.brand_id IS 'Идентификатор бренда, связанного с целью коллаборации';