use learning;

create table if not exists person (
    id bigint auto_increment not null primary key,
    firstName varchar(50) not null,
    lastName varchar(50) not null,
    address varchar(50) not null,
    email varchar(50) not null unique,
    password varchar(24) not null
);