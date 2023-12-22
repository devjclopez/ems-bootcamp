create table eventos(
    id serial not null primary key,
    titulo varchar(150) not null,
    titulo_corto varchar(50) not null,
    descripcion varchar(250) not null ,
    artista varchar(50) not null,
    tipo varchar(20) check (tipo in ('Presencial','Virtual')),
    fecha date not null,
    capacidad integer not null,
    banner varchar(250) not null,
    img_p varchar(250) not null,
    img_s varchar(250) not null,
    categoria_id integer,
    constraint fk_categoria foreign key (categoria_id) references categories(id),
    ubicacion_id integer,
    constraint fk_ubicacion foreign key (ubicacion_id) references ubicacion(id) on delete cascade
)