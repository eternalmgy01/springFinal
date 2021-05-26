create table users
(
    id          serial not null,
    nickname    varchar(255),
    email       varchar(255),
    password    varchar(255),
    cash        double precision
);

alter table users owner to postgres;

create table games
(
    id              serial not null,
    title           varchar(255),
    description     varchar(255),
    price           double precision
);

alter table games owner to postgres;