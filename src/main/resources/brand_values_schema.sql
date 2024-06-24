create table brand_values
(
    id bigserial primary key,
    value varchar(255) not null,
    brand_id bigint not null,
    foreign key (brand_id) references brands(id)
);

comment on table brand_values is 'Таблица ключевых ценностей бренда';
comment on column brand_values.id is 'Первичный ключ';
comment on column brand_values.value is 'Ключевая ценность бренда';
comment on column brand_values.brand_id is 'Внешний ключ на brands';