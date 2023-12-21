create table ubicacion(
    id serial not null primary key,
    direccion varchar(250) not null,
    departamento varchar(50) not null,
    provincia varchar(50),
    distrito varchar(50) not null
)