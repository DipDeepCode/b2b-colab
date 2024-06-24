create table likes
(
    id           bigserial primary key,
    timestamp    timestamp not null,
    frombrand_id bigint,
    tobrand_id   bigint,
    foreign key (frombrand_id) references brands (id),
    foreign key (tobrand_id) references brands (id)
);

comment on table likes is 'Таблица хранения информации о лайках';
comment on column likes.id is 'Уникальный идентификатор лайка';
comment on column likes.fromBrand_id is 'От кого лайк';
comment on column likes.toBrand_id is 'Кому лайк';
