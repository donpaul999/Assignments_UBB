create table ProjectsProgrammers
(
    id            int auto_increment
        primary key,
    project_id    int null,
    programmer_id int null,
    constraint ProjectsProgrammers_Programmers_id_fk_2
        foreign key (programmer_id) references Programmers (id),
    constraint ProjectsProgrammers_Projects_id_fk
        foreign key (project_id) references Projects (id)
);

