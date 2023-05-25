insert into chat.users (login, password)
values ('Nielsen', 'EOI88QYB3HF'),
       ('Joyce', 'XJW83FCK1SF'),
       ('Mcdowell', 'FUP45LSU4RX'),
       ('Burgess', 'EEP89PJL3DC'),
       ('Jacobs', 'SXS92LCM8ZO');

insert into chat.chatroom (name, owner)
values ('Nielsens_room', '1'),
       ('Joyces_room', '2'),
       ('Mcdowells_room', '3'),
       ('Burgesses_room', '4'),
       ('Jacobses_room', '5');

insert into chat.message (author, room, text, date_time)
values (1, 1, 'Quisque imperdiet, erat nonummy ultricies ornare', '2022-12-05'),
       (2, 1, 'ante lectus convallis est', '2022-09-19'),
       (3, 1, 'Aliquam ornare, libero at auctor ullamcorper', '2021-05-14'),
       (4, 1, 'Curabitur consequat, lectus sit amet luctus', '2022-04-20'),
       (5, 1, 'Quisque nonummy ipsum non arcu.', '2021-05-05');