create table Categories
(
	id int identity
		primary key,
	name varchar(200),
	description varchar(400)
)
go

create table Clients
(
	id int identity
		primary key,
	name varchar(250),
	email varchar(100),
	phone varchar(200)
)
go

create table Equipment
(
	id int identity
		primary key,
	name varchar(300)
)
go

create table Programmers
(
	id int identity
		primary key,
	name varchar(200),
	email varchar(200),
	phone varchar(200),
	birthday date
)
go

create table EquipmentProgrammers
(
	id int identity
		primary key,
	programmer_id int
		constraint TEquipmentProgrammers_Technologies_id_fk
			references Programmers,
	equipment_id int
		constraint EquipmentProgrammers_Equipment_id_fk
			references Equipment
)
go

create table Holidays
(
	id int identity
		primary key,
	programmer_id int
		constraint Holidays_Programmers_id_fk
			references Programmers,
	start_date date,
	end_date date
)
go

create table Projects
(
	id int identity
		primary key,
	name varchar(200),
	description varchar(300),
	client_id int
		constraint client_id
			references Clients,
	category_id int
		constraint Projects_Categories_id_fk
			references Categories
)
go

create table ProjectsProgrammers
(
	id int identity
		primary key,
	project_id int
		constraint ProjectsProgrammers_Projects_id_fk
			references Projects,
	programmer_id int
		constraint ProjectsProgrammers_Programmers_id_fk_2
			references Programmers
)
go

create table Technologies
(
	id int identity
		primary key,
	name varchar(100)
)
go

create table TechnologiesProjects
(
	id int identity
		primary key,
	project_id int
		constraint TechnologiesProjects_Technologies_id_fk
			references Projects,
	technology_id int
		constraint TechnologiesProjects_Technologies_id_fk_2
			references Technologies
)
go

