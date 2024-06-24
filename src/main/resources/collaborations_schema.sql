create table collaborations
(
    id         bigserial primary key,
    brand_id1  bigint    not null,
    brand_id2  bigint    not null,
    created_at timestamp not null,
    updated_at timestamp,
    foreign key (brand_id1) references brands (id),
    foreign key (brand_id2) references brands (id)
);

comment on table collaborations is 'Таблица хранения информации о коллаборациях';
comment on column collaborations.id is 'Уникальный идентификатор коллаборации';
comment on column collaborations.brand_id1 is 'Идентификатор первой бренда, участвующего в коллаборации';
comment on column collaborations.brand_id2 is 'Идентификатор второй бренда, участвующего в коллаборации';
comment on column collaborations.created_at is 'Дата и время создания записи о коллаборации';
comment on column collaborations.updated_at is 'Дата и время последнего обновления записи о коллаборации';

