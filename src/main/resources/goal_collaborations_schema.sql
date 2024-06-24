create table goal_collaborations
(
    id       bigserial primary key,
    name     varchar(255) not null,
    brand_id bigint       not null,
    foreign key (brand_id) references brands (id)
);

comment on table goal_collaborations is 'Таблица целей коллабораций';
comment on column goal_collaborations.id is 'Уникальный идентификатор цели коллаборации';
comment on column goal_collaborations.name is 'Название цели коллаборации';
comment on column goal_collaborations.brand_id is 'Идентификатор бренда, связанного с целью коллаборации';