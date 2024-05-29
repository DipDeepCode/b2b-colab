-- drop table if exists chat_message;
-- drop table if exists users_chat_room;
-- drop table if exists chat_room;

create table chat_room
(
    id         bigint generated by default as identity not null primary key,
    name       varchar(50) unique,
    created_at timestamp with time zone                not null,
    is_private boolean                                 not null
);
comment on table chat_room is 'Таблица для хранения ChatRoom';
comment on column chat_room.name is 'Название ChatRoom, необязательно';
comment on column chat_room.created_at is 'Timestamp создания';

create table users_chat_room
(
    user_username varchar(50) not null,
    chat_room_id  bigint      not null,
    primary key (user_username, chat_room_id),
    foreign key (user_username) references users (username),
    foreign key (chat_room_id) references chat_room (id)
);
comment on table users_chat_room is 'Таблица для объединения users и chat_room';
comment on column users_chat_room.user_username is 'Внешний ключ на users';
comment on column users_chat_room.chat_room_id is 'Внешний ключ на chat_room';

create table chat_message
(
    id           bigint generated by default as identity not null primary key,
    chat_room_id bigint                                  not null,
    username     varchar(50)                             not null,
    content      varchar(511)                            not null,
    created_at   timestamp with time zone                not null,
    foreign key (chat_room_id) references chat_room (id),
    foreign key (username) references users (username)
);
comment on table chat_message is 'Таблица для хранения ChatMessage';
comment on column chat_message.chat_room_id is 'Внешний ключ на chat_room';
comment on column chat_message.username is 'Username отправителя';
comment on column chat_message.content is 'Содержимое сообщения';
comment on column chat_message.created_at is 'Timestamp создания';
