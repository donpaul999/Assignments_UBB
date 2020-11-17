create table Categories
(
	id int identity
		primary key,
	name varchar(200) not null,
	description varchar(400)
)
go

create table Clients
(
	id int identity
		primary key,
	name varchar(250) not null,
	email varchar(100) not null,
	phone varchar(200)
)
go

create table Equipment
(
	id int identity
		primary key,
	name varchar(300) not null
)
go

create table MSreplication_options
(
	optname sysname not null,
	value bit not null,
	major_version int not null,
	minor_version int not null,
	revision int not null,
	install_failures int not null
)
go

create table Programmers
(
	id int identity
		primary key,
	name varchar(200) not null,
	email varchar(200) not null,
	phone varchar(15),
	birthday date
)
go

create table EquipmentProgrammers
(
	id int identity
		constraint PK__Equipmen__3213E83FC7E3F382
			primary key,
	programmer_id int
		constraint TEquipmentProgrammers_Technologies_id_fk
			references Programmers,
	equipment_id int
		constraint EquipmentProgrammers_Equipment_id_fk
			references Equipment,
	constraint EquipmentProgrammers_pk
		unique (programmer_id, equipment_id)
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
	name varchar(200) not null,
	description varchar(300) not null,
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
			references Programmers,
	constraint ProjectsProgrammers_pk
		unique (project_id, programmer_id)
)
go

create table Tables
(
	TableID int identity
		constraint PK_Tables
			primary key,
	Name nvarchar(50) not null
)
go

create table Technologies
(
	id int identity
		primary key,
	name varchar(100) not null
)
go

create table TechnologiesProjects
(
	id int identity
		primary key
		constraint TechnologiesProjects_Technologies_id_fk_2
			references Technologies,
	project_id int
		constraint TechnologiesProjects_Technologies_id_fk
			references Projects,
	technology_id int
)
go

create table TestRuns
(
	TestRunID int identity
		constraint PK_TestRuns
			primary key,
	Description nvarchar(2000),
	StartAt datetime,
	EndAt datetime
)
go

create table TestRunTables
(
	TestRunID int not null
		constraint FK_TestRunTables_TestRuns
			references TestRuns
				on update cascade on delete cascade,
	TableID int not null
		constraint FK_TestRunTables_Tables
			references Tables
				on update cascade on delete cascade,
	StartAt datetime not null,
	EndAt datetime not null,
	constraint PK_TestRunTables
		primary key (TestRunID, TableID)
)
go

create table Tests
(
	TestID int identity
		constraint PK_Tests
			primary key,
	Name nvarchar(50) not null
)
go

create table TestTables
(
	TestID int not null
		constraint FK_TestTables_Tests
			references Tests
				on update cascade on delete cascade,
	TableID int not null
		constraint FK_TestTables_Tables
			references Tables
				on update cascade on delete cascade,
	NoOfRows int not null,
	Position int not null,
	constraint PK_TestTables
		primary key (TestID, TableID)
)
go

create table Version
(
	version int
)
go

create table VersionLog
(
	id int identity
		primary key,
	oldVersion int,
	newVersion int,
	changeTime datetime
)
go

create table Views
(
	ViewID int identity
		constraint PK_Views
			primary key,
	Name nvarchar(50) not null
)
go

create table TestRunViews
(
	TestRunID int not null
		constraint FK_TestRunViews_TestRuns
			references TestRuns
				on update cascade on delete cascade,
	ViewID int not null
		constraint FK_TestRunViews_Views
			references Views
				on update cascade on delete cascade,
	StartAt datetime not null,
	EndAt datetime not null,
	constraint PK_TestRunViews
		primary key (TestRunID, ViewID)
)
go

create table TestViews
(
	TestID int not null
		constraint FK_TestViews_Tests
			references Tests,
	ViewID int not null
		constraint FK_TestViews_Views
			references Views,
	constraint PK_TestViews
		primary key (TestID, ViewID)
)
go

create table spt_fallback_db
(
	xserver_name varchar(30) not null,
	xdttm_ins datetime not null,
	xdttm_last_ins_upd datetime not null,
	xfallback_dbid smallint,
	name varchar(30) not null,
	dbid smallint not null,
	status smallint not null,
	version smallint not null
)
go

create table spt_fallback_dev
(
	xserver_name varchar(30) not null,
	xdttm_ins datetime not null,
	xdttm_last_ins_upd datetime not null,
	xfallback_low int,
	xfallback_drive char(2),
	low int not null,
	high int not null,
	status smallint not null,
	name varchar(30) not null,
	phyname varchar(127) not null
)
go

create table spt_fallback_usg
(
	xserver_name varchar(30) not null,
	xdttm_ins datetime not null,
	xdttm_last_ins_upd datetime not null,
	xfallback_vstart int,
	dbid smallint not null,
	segmap int not null,
	lstart int not null,
	sizepg int not null,
	vstart int not null
)
go

create table spt_monitor
(
	lastrun datetime not null,
	cpu_busy int not null,
	io_busy int not null,
	idle int not null,
	pack_received int not null,
	pack_sent int not null,
	connections int not null,
	pack_errors int not null,
	total_read int not null,
	total_write int not null,
	total_errors int not null
)
go

CREATE VIEW View_1 AS
SELECT id, name
FROM dbo.Equipment
go

CREATE VIEW View_2 AS
SELECT H.id, P.name, H.start_date
FROM dbo.Holidays H
INNER JOIN dbo.Programmers P ON P.id = H.programmer_id
go

CREATE VIEW View_3 AS
SELECT Equipment = E.name, Programmer = P.name
FROM dbo.Equipment E
INNER JOIN dbo.EquipmentProgrammers EP ON EP.equipment_id = E.id
INNER JOIN dbo.Programmers P ON EP.programmer_id = P.id
GROUP BY E.name, P.name
go


create view spt_values as
select name collate database_default as name,
	number,
	type collate database_default as type,
	low, high, status
from sys.spt_values
go

CREATE procedure dbo_changeVersion @tv smallint
as
    if @tv < 0 or @tv > 7
        RAISERROR('Invalid version', 16, 1);
	declare @actualVersion smallint = 1;
	exec dbo_getCurrentVersion @vr = @actualVersion output
	declare @source smallint = @actualVersion;
	if @source < @tv
		while @source < @tv
		begin
			exec dbo_goToNextVersion @v = @source;
			set @source = @source + 1
		end
	else if @source > @tv
		while @source > @tv
		begin
			exec dbo_goToPrevVersion @v = @source;
			set @source = @source - 1
		end
	exec dbo_updateCurrentVersion @v = @tv
	exec dbo_updateLog @old = @actualVersion, @new = @tv
go

CREATE proc dbo_deleteAll @tableName varchar(50)
as
	declare @query varchar(100)
	set @query = 'Delete from ' + @tableName
	exec(@query)
go

CREATE procedure dbo_delete_rows @nb_of_rows int, @table_name varchar(50) as
    if @nb_of_rows < 1
        RAISERROR('Invalid number of rows', 16, 1);
    declare @last_row int
    if @table_name = 'Equipment'
    begin
        set @last_row = (select max(id) from dbo.Equipment) - @nb_of_rows
        delete from dbo.EquipmentProgrammers where equipment_id > @last_row
        delete from dbo.Equipment where id > @last_row
    end

    if @table_name = 'Holidays'
    begin
        set @last_row = (select max(id) from dbo.Holidays) - @nb_of_rows
        delete from dbo.Holidays where id > @last_row
    end

    if @table_name = 'EquipmentProgrammers'
    begin
        set @last_row = (select max(id) from dbo.EquipmentProgrammers) - @nb_of_rows
        delete from dbo.EquipmentProgrammers where id > @last_row
    end
go

CREATE procedure dbo_getCurrentVersion @vr smallint output
as
	select @vr = ver.version
	from version ver;
go

CREATE procedure dbo_goToNextVersion @v smallint
as
	if @v = 0
		exec dbo_version1;
	else if @v = 1
		exec dbo_version2;
	else if @v = 2
		exec dbo_version3;
	else if @v = 3
		exec dbo_version4;
	else if @v = 4
		exec dbo_version5;
	else if @v = 5
		exec dbo_version6;
	else if @v = 6
		exec dbo_version7;
go

CREATE procedure dbo_goToPrevVersion @v smallint
as
	if @v = 1
		exec dbo_version1undo;
	else if @v = 2
		exec dbo_version2undo;
	else if @v = 3
		exec dbo_version3undo;
	else if @v = 4
		exec dbo_version4undo;
	else if @v = 5
		exec dbo_version5undo;
	else if @v = 6
		exec dbo_version6undo;
	else if @v = 7
		exec dbo_version7undo;
go

CREATE procedure dbo_insert_elements @nb_of_rows int, @table_name varchar(50) as
    if @nb_of_rows < 1
        RAISERROR('Invalid number of rows', 16, 1);

    declare @i int
    declare @name varchar(50)
    declare @email varchar(50)
    declare @phone varchar(15)
    declare @birthday date
    declare @p_id int
    declare @e_id int

    set @i = 0
    while @i < @nb_of_rows
    begin
        if @table_name = 'Equipment'
        begin
            set @name = 'Equipment number' + convert(varchar(10), @i)
            insert into dbo.Equipment(name) values (@name)
        end
        if @table_name = 'Holidays'
        begin
            set @name = 'Name ' + convert(varchar(10), @i)
            set @email = 'Email ' + convert(varchar(10), @i)
            set @phone = 'Tel ' + convert(varchar(10), @i)
            set @birthday = convert(datetime, (convert (int, @i)), 6)
            insert into dbo.Programmers(name, email, phone, birthday) values (@name, @email, @phone, @birthday)

            select @p_id = id from dbo.Programmers where email = @email

            insert into dbo.Holidays(programmer_id, start_date, end_date) values (@p_id, GETDATE(), GETDATE())
        end
        if @table_name = 'EquipmentProgrammers'
        begin
            set @name = 'Equipment number' + convert(varchar(10), @i)
            insert into dbo.Equipment(name) values (@name)
            select @e_id = id from dbo.Equipment where name = @name

            set @name = 'Name ' + convert(varchar(10), @i)
            set @email = 'Email ' + convert(varchar(10), @i)
            set @phone = 'Tel ' + convert(varchar(10), @i)
            set @birthday = convert(datetime, (convert (int, @i)), 6)
            insert into dbo.Programmers(name, email, phone, birthday) values (@name, @email, @phone, @birthday)

            select @p_id = id from dbo.Programmers where email = @email

            insert into dbo.EquipmentProgrammers(programmer_id, equipment_id) values (@p_id, @e_id)
        end
		set @i = @i + 1
    end
go

CREATE procedure dbo_select_view @view smallint as
    if @view < 1
        RAISERROR('Invalid view', 16, 1);
    if @view = 'View_1'
        select * from View_1
    if @view = 'View_2'
        select * from View_2
    if @view = 'View_3'
        select * from View_3
go

CREATE procedure dbo_updateCurrentVersion @v int
as
	update dbo.Version
	set version = @v;
go

CREATE procedure dbo_updateLog @old smallint, @new smallint
as
	insert into dbo.VersionLog
	values (@old, @new, SYSDATETIME());
go

CREATE procedure dbo_version1
as
    alter table dbo.Programmers
    alter column phone int;
    print N'Changed phone column to int';
go

CREATE procedure dbo_version1undo
as
    alter table dbo.Programmers
    alter column phone varchar(15);
    print N'Changed phone column to varchar';
go

CREATE procedure dbo_version2
as
    alter table dbo.Programmers
    add height int;
    print N'Add height column in Programmers table';
go

CREATE procedure dbo_version2undo
as
    alter table dbo.Programmers
    drop column height;
    print N'Remove height column from Programmers table';
go

CREATE procedure dbo_version3
as
	alter table dbo.Projects
	add constraint DEF_ProjectsDesc default 'Default description' for description;
    print N'Set default description in Projects table';
go

CREATE procedure dbo_version3undo
as
	alter table dbo.Projects
	drop constraint DEF_ProjectsDesc;
    print N'Remove height column from Programmers table';
go

CREATE procedure dbo_version4
as
	alter table dbo.EquipmentProgrammers
	drop constraint PK__Equipmen__3213E83FC7E3F382;
    print N'Remove PrimaryKey from EquipmentProgrammers table';
go

CREATE procedure dbo_version4undo
as
	alter table dbo.EquipmentProgrammers
	add constraint PK__Equipmen__3213E83FC7E3F382 primary key (id);
    print N'Add PrimaryKey from EquipmentProgrammers table';
go

CREATE procedure dbo_version5
as
	alter table dbo.Technologies
	add constraint CK_TechnologiesName unique (name);
    print N'Add Candidate Key from Technologies table';
go

CREATE procedure dbo_version5undo
as
	alter table dbo.Technologies
	drop constraint CK_TechnologiesName;
    print N'Remove Candidate Key from Technologies table';
go

CREATE procedure dbo_version6
as
	alter table dbo.TechnologiesProjects
	drop constraint TechnologiesProjects_Technologies_id_fk_2;
    print N'Remove Foreign Key for technology_id from TechnologiesProjects table';
go

CREATE procedure dbo_version6undo
as
	alter table dbo.TechnologiesProjects
    add constraint TechnologiesProjects_Technologies_id_fk_2 foreign key (id) references dbo.Technologies(id);
    print N'Add Foreign Key for technology_id from TechnologiesProjects table';
go

CREATE procedure dbo_version7
as
	create table dbo.Staff (id int primary key identity(1, 1),
						  name varchar(15),
						  department varchar(15),
						  birthdate date);
    print N'Add Staff table';
go

CREATE procedure dbo_version7undo
as
	drop table dbo.Staff;
    print N'Remove Staff table';
go

create procedure dbo.sp_MScleanupmergepublisher
as
    exec sys.sp_MScleanupmergepublisher_internal
go


create procedure dbo.sp_MSrepl_startup
as
    exec sys.sp_MSrepl_startup_internal
go

