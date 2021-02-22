create table Projects
(
    id          int auto_increment
        primary key,
    name        varchar(200) null,
    description varchar(300) null,
    client_id   int          null,
    category_id int          null,
    constraint Projects_Categories_id_fk
        foreign key (category_id) references Categories (id),
    constraint client_id
        foreign key (client_id) references Clients (id)
);

