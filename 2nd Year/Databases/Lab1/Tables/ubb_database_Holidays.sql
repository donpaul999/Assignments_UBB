create table Holidays
(
    id            int auto_increment
        primary key,
    programmer_id int  null,
    start_date    date null,
    end_date      date null,
    constraint Holidays_Programmers_id_fk
        foreign key (programmer_id) references Programmers (id)
);

