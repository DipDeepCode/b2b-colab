CREATE TABLE tariffs
(
    id BIGINT PRIMARY KEY,
    type VARCHAR(255) NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL
);

COMMENT ON TABLE tariffs IS 'Таблица тарифов';
COMMENT ON COLUMN tariffs.id IS 'Уникальный идентификатор тарифа';
COMMENT ON COLUMN tariffs.type IS 'Тип тарифа';
COMMENT ON COLUMN tariffs.startDate IS 'Дата начала действия тарифа';
COMMENT ON COLUMN tariffs.endDate IS 'Дата окончания действия тарифа';
