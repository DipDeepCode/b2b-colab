create table customers
(
    phone_number varchar(50)  not null primary key,
    email        varchar(500) not null unique,
    password     varchar(500) not null,
    enabled      boolean      not null
);

comment on table customers is 'Таблица пользователей';
comment on column customers.phone_number is 'Номер телефона пользователя';
comment on column customers.email is 'Почта пользователя';
comment on column customers.password is 'Пароль пользователя';
comment on column customers.enabled is 'Пользователь активирован';

create table authority
(
    phone_number varchar(50) not null,
    role         varchar(50) not null,
    foreign key (phone_number) references customers (phone_number)
);

comment on table authority is 'Таблица ролей пользователя';
comment on column authority.phone_number is 'Внешний ключ на таблицу customers';
comment on column authority.role is 'Роль пользователя';
