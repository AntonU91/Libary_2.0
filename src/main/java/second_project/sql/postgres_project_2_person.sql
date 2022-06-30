create table person
(
    id               serial,
    full_name        varchar(64),
    year_of_birthday integer
)
    using ???;

alter table person
    owner to postgres;

INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (22, 'Anton Uzhva', 1991);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (23, 'Petrov Petr', 1994);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (6, 'Ivanov Ivan', 1979);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (24, 'Sidorov Nikolay', 1978);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (26, 'Stepanova Olya', 1984);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (27, 'Karpenko Alisa', 2002);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (1, 'Zaur', 2007);
INSERT INTO project_2.person (id, full_name, year_of_birthday) VALUES (7, 'Albert', 46);