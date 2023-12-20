create table categories(
    id serial not null primary key,
    title varchar(50) not null unique
)