create table if not exists users (
    id serial primary key,
    login text not null,
    password text not null,
    unique (login,password)
);

create table if not exists chatroom (
    id serial primary key,
    name text not null,
    owner bigint references Users(id),
    unique (name)
);

create table if not exists message (
    id serial primary key,
    author bigint references Users(id),
    room bigint references Chatroom(id),
    text text,
    date_time timestamp
);