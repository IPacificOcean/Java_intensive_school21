insert into chat.users (login, password)
values ('Nielsen', 'EOI88QYB3HF'),
       ('Joyce', 'XJW83FCK1SF'),
       ('Mcdowell', 'FUP45LSU4RX'),
       ('Burgess', 'EEP89PJL3DC'),
       ('Jacobs', 'SXS92LCM8ZO'),
       ('Bianca','FHG41BRN1KT'),
       ('Oleg','MLT64SRR1KU'),
       ('Audra','WUZ52OHY5JY'),
       ('Alexis','XOV60KAT4KY'),
       ('Clark','UNI45PCX8IE'),
       ('Nelle','END58YCD5HW'),
       ('Magee','AJL86XJV6FZ'),
       ('Alyssa','RWU02PZH9OW'),
       ('Lani','RVC36OEC7ZJ'),
       ('Indira','EUY65XLF0QF'),
       ('Emery','MHY82DOU5KO'),
       ('Charles','ASD94KRL8HU'),
       ('Candace','FGK68UEB0KF'),
       ('Steven','TQH75EEY1LI'),
       ('Herman','VVB48JHC2VU');

insert into chat.chatroom (name, owner)
values ('Nielsens_room', '1'),
       ('Joyces_room', '2'),
       ('Mcdowells_room', '3'),
       ('Burgesses_room', '4'),
       ('Jacobses_room', '5'),
       ('Bianca_room', '6'),
       ('Oleg_room', '7'),
       ('Audra_room', '8'),
       ('Alexis_room', '9'),
       ('Clark_room', '10'),
       ('Nelle_room', '11'),
       ('Magee_room', '12'),
       ('Alyssa_room', '13'),
       ('Lani_room', '14'),
       ('Indira_room', '15'),
       ('Emery_room', '16'),
       ('Charles_room', '17'),
       ('Candace_room', '18'),
       ('Steven_room', '19'),
       ('Herman_room', '20');

insert into chat.message (author, room, text, date_time)
values (1, 1, 'Quisque imperdiet, erat nonummy ultricies ornare', '2022-12-05'),
       (2, 1, 'ante lectus convallis est', '2022-09-19'),
       (3, 1, 'Aliquam ornare, libero at auctor ullamcorper', '2021-05-14'),
       (4, 1, 'Curabitur consequat, lectus sit amet luctus', '2022-04-20'),
       (5, 1, 'Quisque nonummy ipsum non arcu.', '2021-05-05');

insert into chat.user_x_chatroom
values (1, 1), (2, 4), (1, 3), (4, 2), (1, 5),
        (12, 1), (7, 2), (8, 3), (6, 4), (7, 5),
        (9, 10), (10, 15), (15, 18), (11, 8), (14, 5),
        (16, 12), (12, 7), (19, 9), (13, 4), (20, 5);