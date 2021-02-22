

create table dbo.Categories
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	description varchar(400)
)
go

create table dbo.Clients
(
	id int identity
		primary key,
	name varchar(250) NOT NULL,
	email varchar(100) NOT NULL,
	phone varchar(200)
)
go

create table dbo.Equipment
(
	id int identity
		primary key,
	name varchar(300) NOT NULL
)
go

create table dbo.Programmers
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	email varchar(200) NOT NULL,
	phone varchar(200) NOT NULL,
	birthday date
)
go

create table dbo.EquipmentProgrammers
(
	id int identity
		primary key,
	programmer_id int
		constraint TEquipmentProgrammers_Technologies_id_fk
			references dbo.Programmers,
	equipment_id int
		constraint EquipmentProgrammers_Equipment_id_fk
			foreign key references dbo.Equipment
)
go

create table dbo.Holidays
(
	id int identity
		primary key,
	programmer_id int
		constraint Holidays_Programmers_id_fk
			foreign key references dbo.Programmers,
	start_date date,
	end_date date
)
go

create table dbo.Projects
(
	id int identity
		primary key,
	name varchar(200) NOT NULL,
	description varchar(300) NOT NULL,
	client_id int
		constraint client_id
			foreign key references dbo.Clients,
	category_id int
		constraint Projects_Categories_id_fk
			foreign key references dbo.Categories
)
go

create table dbo.ProjectsProgrammers
(
	id int identity
		primary key,
	project_id int
		constraint ProjectsProgrammers_Projects_id_fk
			foreign key references dbo.Projects,
	programmer_id int
		constraint ProjectsProgrammers_Programmers_id_fk_2
			foreign key references dbo.Programmers
)
go

create table dbo.Technologies
(
	id int identity
		primary key,
	name varchar(100) NOT NULL
)
go

create table dbo.TechnologiesProjects
(
	id int identity
		primary key,
	project_id int
		constraint TechnologiesProjects_Technologies_id_fk
			foreign key references dbo.Projects,
	technology_id int
		constraint TechnologiesProjects_Technologies_id_fk_2
			foreign key references dbo.Technologies
)
go
