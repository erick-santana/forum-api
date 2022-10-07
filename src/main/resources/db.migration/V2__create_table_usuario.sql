create table usuario(
    id bigint not null auto_increment,
    name varchar(50) not null,
    email varchar(50) not null,
    primary key(id)
);

insert into usuario values(1, 'Erick', 'erick@email.com');