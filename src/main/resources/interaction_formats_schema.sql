create table interaction_formats
(
    id       bigserial primary key,
    name     varchar(255) not null,
    brand_id bigint       not null,
    foreign key (brand_id) references brands (id)
);

comment on table interaction_formats is 'Таблица интересующих форматов взаимодействия';
comment on column interaction_formats.id is 'Уникальный идентификатор формата взаимодействия';
comment on column interaction_formats.name is 'Название формата взаимодействия';
comment on column interaction_formats.brand_id is 'Идентификатор бренда, связанного с форматом взаимодействия';