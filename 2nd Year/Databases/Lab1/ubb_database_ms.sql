create table ubb_schema.Categories
(
	id int identity
		primary key,
	name varchar(200),
	description varchar(400)
)
go

create table ubb_schema.Clients
(
	id int identity
		primary key,
	name varchar(250),
	email varchar(100),
	phone varchar(200)
)
go

create table ubb_schema.Equipment
(
	id int identity
		primary key,
	name varchar(300)
)
go

create table ubb_schema.Programmers
(
	id int identity
		primary key,
	name varchar(200),
	email varchar(200),
	phone varchar(200),
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
			references ubb_schema.Equipment
)
go

create table ubb_schema.Holidays
(
	id int identity
		primary key,
	programmer_id int
		constraint Holidays_Programmers_id_fk
			references ubb_schema.Programmers,
	start_date date,
	end_date date
)
go

create table ubb_schema.Projects
(
	id int identity
		primary key,
	name varchar(200),
	description varchar(300),
	client_id int
		constraint client_id
			references ubb_schema.Clients,
	category_id int
		constraint Projects_Categories_id_fk
			references ubb_schema.Categories
)
go

create table ubb_schema.ProjectsProgrammers
(
	id int identity
		primary key,
	project_id int
		constraint ProjectsProgrammers_Projects_id_fk
			references ubb_schema.Projects,
	programmer_id int
		constraint ProjectsProgrammers_Programmers_id_fk_2
			references ubb_schema.Programmers
)
go

create table ubb_schema.Technologies
(
	id int identity
		primary key,
	name varchar(100)
)
go

create table ubb_schema.TechnologiesProjects
(
	id int identity
		primary key,
	project_id int
		constraint TechnologiesProjects_Technologies_id_fk
			references ubb_schema.Projects,
	technology_id int
		constraint TechnologiesProjects_Technologies_id_fk_2
			references ubb_schema.Technologies
)
go

