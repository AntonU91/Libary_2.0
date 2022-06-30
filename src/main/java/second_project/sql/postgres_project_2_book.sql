create table book
(
    id                  serial,
    title               varchar(200),
    author              varchar(100),
    year_of_publication integer,
    person_id           integer,
    taken_at            timestamp
)
    using ???;

alter table book
    owner to postgres;

INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (1, 'Pride and Prejudice ', 'Jane Austen', 1998, 23, null);
INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (2, 'Anna Karenina', 'Lev Tolstoy', 1980, 24, null);
INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (9, 'Fahrenheit 451', 'Ray Bradbury', 1953, null, null);
INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (8, 'Jane Eyre', 'Charlotte Bronte', 1847, 27, '2022-06-01 15:43:11.000000');
INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (4, 'Преступление и наказание ', 'Ф. Достоевский', 1975, 22, '2022-06-14 16:02:53.000000');
INSERT INTO project_2.book (id, title, author, year_of_publication, person_id, taken_at) VALUES (10, 'Crime and Punishment', ' Fyodor Dostoevsky', 1866, 27, '2022-06-27 16:15:30.695000');