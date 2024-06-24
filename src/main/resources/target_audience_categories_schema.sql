create table target_audience_categories
(
    id       bigserial primary key,
    category varchar(255) not null,
    brand_id bigint       not null,
    foreign key (brand_id) references brands (id)
);

comment on table target_audience_categories is 'Таблица категорий целевой аудитории';
comment on column target_audience_categories.id is 'Уникальный идентификатор категории целевой аудитории';
comment on column target_audience_categories.category is 'Категория целевой аудитории';
comment on column target_audience_categories.brand_id is 'Идентификатор бренда, с которым связана категория целевой аудитории';