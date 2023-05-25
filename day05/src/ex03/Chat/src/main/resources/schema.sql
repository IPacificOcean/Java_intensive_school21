drop schema if exists chat cascade;

create schema if not exists chat;

create table if not exists chat.users (
    id serial primary key,
    login text not null,
    password text not null,
    unique (login,password)
);

create table if not exists chat.chatroom (
    id serial primary key,
    name text not null,
    owner bigint references chat.users(id),
    unique (name)
);

create table if not exists chat.message (
    id serial primary key,
    author bigint references chat.users(id),
    room bigint references chat.chatroom(id),
    text text,
    date_time timestamp default CURRENT_TIMESTAMP
);