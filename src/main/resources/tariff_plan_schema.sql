create table tariff_plan
(
    id              bigserial primary key,
    name            varchar(255)   not null,
    description     text,
    price_per_month decimal(10, 2) not null,
    start_date      timestamp,
    end_date        timestamp,
    created_at      timestamp,
    updated_at      timestamp
);

comment on table tariff_plan is 'Таблица хранения информации о тарифах';
comment on column tariff_plan.id is 'Первичный ключ';
comment on column tariff_plan.name is 'Название тарифа';
comment on column tariff_plan.description is 'Описание тарифа';
comment on column tariff_plan.price_per_month is 'Стоимость тарифа рублей в месяц';
comment on column tariff_plan.start_date is 'Дата начала действия тарифа';
comment on column tariff_plan.end_date is 'Дата окончания действия тарифа';
comment on column tariff_plan.created_at is 'Дата и время создания записи о тарифе';
comment on column tariff_plan.updated_at is 'Дата и время последнего обновления записи о тарифе';

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

