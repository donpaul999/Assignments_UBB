create database it_company_f
go

use it_company_f
go

create schema ubb_schema
go

create table ubb_schema.Categories
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	description varchar(400)
)
go

create table ubb_schema.Clients
(
	id int identity
		primary key,
	name varchar(250) NOT NULL,
	email varchar(100) NOT NULL,
	phone varchar(200)
)
go

create table ubb_schema.Equipment
(
	id int identity
		primary key,
	name varchar(300) NOT NULL
)
go

create table ubb_schema.Programmers
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	email varchar(200) NOT NULL,
	phone varchar(200) NOT NULL,
	birthday date
)
go

create table ubb_schema.EquipmentProgrammers
(
	id int identity
		primary key,
	programmer_id int
		constraint TEquipmentProgrammers_Technologies_id_fk
			references ubb_schema.Programmers,
	equipment_id int
		constraint EquipmentProgrammers_Equipment_id_fk
			foreign key references ubb_schema.Equipment
)
go

create table ubb_schema.Holidays
(
	id int identity
		primary key,
	programmer_id int
		constraint Holidays_Programmers_id_fk
			foreign key references ubb_schema.Programmers,
	start_date date,
	end_date date
)
go

create table ubb_schema.Projects
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	description varchar(300) NOT NULL,
	client_id int
		constraint client_id
			foreign key references ubb_schema.Clients,
	category_id int
		constraint Projects_Categories_id_fk
			foreign key references ubb_schema.Categories
)
go

create table ubb_schema.ProjectsProgrammers
(
	id int identity
		primary key,
	project_id int
		constraint ProjectsProgrammers_Projects_id_fk
			foreign key references ubb_schema.Projects,
	programmer_id int
		constraint ProjectsProgrammers_Programmers_id_fk_2
			foreign key references ubb_schema.Programmers
)
go

create table ubb_schema.Technologies
(
	id int identity
		primary key,
	name varchar(100) NOT NULL
)
go

create table ubb_schema.TechnologiesProjects
(
	id int identity
		primary key,
	project_id int
		constraint TechnologiesProjects_Technologies_id_fk
			foreign key references ubb_schema.Projects,
	technology_id int
		constraint TechnologiesProjects_Technologies_id_fk_2
			foreign key references ubb_schema.Technologies
)
go

