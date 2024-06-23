CREATE TABLE tariff_plan
(
    id              BIGSERIAL PRIMARY KEY,
    name            VARCHAR(255)   NOT NULL,
    description     TEXT,
    price_per_month DECIMAL(10, 2) NOT NULL,
    start_date      TIMESTAMP,
    end_date        TIMESTAMP,
    created_at      TIMESTAMP,
    updated_at      TIMESTAMP
);

COMMENT ON TABLE tariff_plan IS 'Таблица хранения информации о тарифах';
COMMENT ON COLUMN tariff_plan.id IS 'Первичный ключ';
COMMENT ON COLUMN tariff_plan.name IS 'Название тарифа';
COMMENT ON COLUMN tariff_plan.description IS 'Описание тарифа';
COMMENT ON COLUMN tariff_plan.price_per_month IS 'Стоимость тарифа рублей в месяц';
COMMENT ON COLUMN tariff_plan.start_date IS 'Дата начала действия тарифа';
COMMENT ON COLUMN tariff_plan.end_date IS 'Дата окончания действия тарифа';
COMMENT ON COLUMN tariff_plan.created_at IS 'Дата и время создания записи о тарифе';
COMMENT ON COLUMN tariff_plan.updated_at IS 'Дата и время последнего обновления записи о тарифе';

create table tariff_extra_description
(
    id bigserial primary key,
    tariff_plan_id bigint not null,
    description varchar(255) not null,
    order_by bigint,
    is_active      boolean not null,
    is_highlighted boolean not null,
    foreign key (tariff_plan_id) references tariff_plan (id)
);
comment on table tariff_extra_description is 'Дополнительные характеристики тарифа';
comment on column tariff_extra_description.id is 'Первичный ключ';
comment on column tariff_extra_description.tariff_plan_id is 'Внешний ключ на tariff_plan';
comment on column tariff_extra_description.description is 'Характеристика тарифа';
comment on column tariff_extra_description.order_by is 'Столбец для сортировки';
comment on column tariff_extra_description.is_active is 'Указатель что позиция активна в тарифе';
comment on column tariff_extra_description.is_highlighted is 'Указатель что позиция подсвечена';

