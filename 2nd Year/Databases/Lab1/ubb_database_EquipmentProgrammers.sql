create table EquipmentProgrammers
(
    id            int auto_increment
        primary key,
    programmer_id int null,
    equipment_id  int null,
    constraint EquipmentProgrammers_Equipment_id_fk
        foreign key (equipment_id) references Equipment (id),
    constraint TEquipmentProgrammers_Technologies_id_fk
        foreign key (programmer_id) references Programmers (id)
);

