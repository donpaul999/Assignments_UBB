create table if not exists Categories
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	description varchar(400) null
);

create table if not exists Clients
(
	id int auto_increment
		primary key,
	name varchar(250) null,
	email varchar(100) null,
	phone varchar(200) null
);

create table if not exists Equipment
(
	id int auto_increment
		primary key,
	name varchar(300) null
);

create table if not exists Programmers
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	email varchar(200) null,
	phone varchar(200) null,
	birthday date null
);

create table if not exists EquipmentProgrammers
(
	id int auto_increment
		primary key,
	programmer_id int null,
	equipment_id int null,
	constraint EquipmentProgrammers_Equipment_id_fk
		foreign key (equipment_id) references Equipment (id),
	constraint TEquipmentProgrammers_Technologies_id_fk
		foreign key (programmer_id) references Programmers (id)
);

create table if not exists Holidays
(
	id int auto_increment
		primary key,
	programmer_id int null,
	start_date date null,
	end_date date null,
	constraint Holidays_Programmers_id_fk
		foreign key (programmer_id) references Programmers (id)
);

create table if not exists Projects
(
	id int auto_increment
		primary key,
	name varchar(200) null,
	description varchar(300) null,
	client_id int null,
	category_id int null,
	constraint Projects_Categories_id_fk
		foreign key (category_id) references Categories (id),
	constraint client_id
		foreign key (client_id) references Clients (id)
);

create table if not exists ProjectsProgrammers
(
	id int auto_increment
		primary key,
	project_id int null,
	programmer_id int null,
	constraint ProjectsProgrammers_Programmers_id_fk_2
		foreign key (programmer_id) references Programmers (id),
	constraint ProjectsProgrammers_Projects_id_fk
		foreign key (project_id) references Projects (id)
);

create table if not exists Technologies
(
	id int auto_increment
		primary key,
	name varchar(100) null
);

create table if not exists TechnologiesProjects
(
	id int auto_increment
		primary key,
	project_id int null,
	technology_id int null,
	constraint TechnologiesProjects_Technologies_id_fk
		foreign key (project_id) references Projects (id),
	constraint TechnologiesProjects_Technologies_id_fk_2
		foreign key (technology_id) references Technologies (id)
);

