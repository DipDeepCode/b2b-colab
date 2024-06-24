create table topic_discussions
(
    id       bigserial primary key,
    name     varchar(100) not null,
    brand_id bigint       not null,
    foreign key (brand_id) references brands (id)
);

comment on table topic_discussions is 'Таблица тем для обсуждения или рекомендации';
comment on column topic_discussions.id is 'Уникальный идентификатор обсуждения темы';
comment on column topic_discussions.name is 'Название обсуждаемой темы';
comment on column topic_discussions.brand_id is 'Идентификатор бренда, связанного с темой обсуждения';