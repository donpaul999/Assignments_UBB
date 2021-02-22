create table TechnologiesProjects
(
    id            int auto_increment
        primary key,
    project_id    int null,
    technology_id int null,
    constraint TechnologiesProjects_Technologies_id_fk
        foreign key (project_id) references Projects (id),
    constraint TechnologiesProjects_Technologies_id_fk_2
        foreign key (technology_id) references Technologies (id)
);

