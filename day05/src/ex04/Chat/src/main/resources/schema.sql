drop schema if exists chat cascade;

create schema if not exists chat;

create table if not exists chat.users (
    id bigserial primary key,
    login text not null unique,
    password text not null,
    unique (login, password)
);

create table if not exists chat.chatroom (
    id bigserial primary key,
    name text not null,
    owner bigint references chat.users(id),
    unique (name)
);

create table if not exists chat.message (
    id bigserial primary key,
    author bigint references chat.users(id),
    room bigint references chat.chatroom(id),
    text text,
    date_time timestamp default CURRENT_TIMESTAMP
);

create table if not exists chat.user_x_chatroom (
    user_id bigint not null references chat.users(id),
    chatroom_id bigint not null references chat.chatroom(id),
    unique (user_id, chatroom_id)
);