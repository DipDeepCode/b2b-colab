CREATE TABLE tariffs
(
    id BIGSERIAL PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    brand_id BIGINT NOT NULL,
    FOREIGN KEY (brand_id) REFERENCES brands (id)
);

COMMENT ON TABLE tariffs IS 'Таблица тарифов';
COMMENT ON COLUMN tariffs.id IS 'Уникальный идентификатор тарифа';
COMMENT ON COLUMN tariffs.type IS 'Тип тарифа';
COMMENT ON COLUMN tariffs.start_date IS 'Дата начала действия тарифа';
COMMENT ON COLUMN tariffs.end_date IS 'Дата окончания действия тарифа';
