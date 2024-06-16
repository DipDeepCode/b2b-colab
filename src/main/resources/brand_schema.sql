create table brands
(
    id                    bigint generated by default as identity not null primary key,
    customer_phone_number varchar(50) unique                      not null,
    name                  varchar(255)                            not null,
    foreign key (customer_phone_number) references customers (phone_number)
);
comment on table brands is 'Таблица для хранения карточки бренда';
comment on column brands.id is 'Первичный ключ таблицы';
comment on column brands.customer_phone_number is 'Внешний ключ на customers';
comment on column brands.name is 'Название бренда';
