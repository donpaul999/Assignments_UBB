use master;

go

create procedure dbo_version1 as
-- todo: implement;
go

create procedure dbo_version1undo as
-- todo: implement;
go

create procedure dbo_version2 as
-- todo: implement;
go

create procedure dbo_version2undo as
-- todo: implement;
go

create procedure dbo_version3 as
-- todo: implement;
go

create procedure dbo_version3undo as
-- todo: implement;
go

create procedure dbo_version4 as
-- todo: implement;
go

create procedure dbo_version4undo as
-- todo: implement;
go

create procedure dbo_version5 as
-- todo: implement;
go

create procedure dbo_version5undo as
-- todo: implement;
go

create procedure dbo_version6 as
-- todo: implement;
go

create procedure dbo_version6undo as
-- todo: implement;
go

create procedure dbo_version7 as
-- todo: implement;
go

create procedure dbo_version7undo as
-- todo: implement;
go

create procedure dbo_getCurrentVersion @tv smallint as
-- todo: implement;
go

create procedure dbo_goToNextVersion @v smallint as
-- todo: implement;
go

create procedure dbo_updateCurrentVersion @v smallint as
-- todo: implement;
go

create procedure dbo_goToPrevVersion @v smallint as
-- todo: implement;
go

create procedure dbo_updateLog @old smallint, @new smallint as
-- todo: implement;
go

create procedure dbo_changeVersion @tv smallint as
-- todo: implement;
go

alter procedure dbo_version1
as
    alter table dbo.Programmers
    alter column birthday int;
go

alter procedure dbo_version1undo
as
    alter table dbo.Programmers
    alter column birthday date;
go

alter procedure dbo_version2
as
    alter table dbo.Programmers
    add height int;
go

alter procedure dbo_version2undo
as
    alter table dbo.Programmers
    drop column height;
go

alter procedure dbo_version3
as
	alter table dbo.Projects
	add constraint DEF_ProjectsDesc default 'Default description' for description;
go

alter procedure dbo_version3undo
as
	alter table dbo.Projects
	drop constraint DEF_ProjectsDesc
go

alter procedure dbo_version4
as
	alter table dbo.EquipmentProgrammers
	drop constraint PK__Equipmen__3213E83F20773670;
go

alter procedure dbo_version4undo
as
	alter table dbo.EquipmentProgrammers
	add constraint PK__Equipmen__3213E83F20773670 primary key (id);
go

alter procedure dbo_version5
as
	alter table dbo.Technologies
	add constraint CK_TechnologiesName unique (name);
go

alter procedure dbo_version5undo
as
	alter table dbo.Technologies
	drop constraint CK_TechnologiesName;
go

alter procedure dbo_version6
as
	alter table dbo.TechnologiesProjects
	drop constraint TechnologiesProjects_Technologies_id_fk_2;
go

alter procedure dbo_version6undo
as
	alter table dbo.TechnologiesProjects
    add constraint TechnologiesProjects_Technologies_id_fk_2 foreign key (id) references dbo.Technologies(id);
go

alter procedure dbo_version7
as
	create table dbo.Staff (id int primary key identity(1, 1),
						  name varchar(15),
						  department varchar(15),
						  birthdate date);
go

alter procedure dbo_version7undo
as
	drop table dbo.Staff;
go

alter procedure dbo_getCurrentVersion @vr smallint output
as
	select @vr = ver.version
	from version ver;
go

alter procedure dbo_goToNextVersion @v smallint
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

alter procedure dbo_goToPrevVersion @v smallint
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

alter procedure dbo_updateCurrentVersion @v int
as
	update dbo.Version
	set version = @v;
go

alter procedure dbo_updateLog @old smallint, @new smallint
as
	insert into dbo.VersionLog
	values (@old, @new, SYSDATETIME());
go

alter procedure dbo_changeVersion @tv smallint
as
	declare @currentVersion smallint = 1;
	exec dbo_getCurrentVersion @vr = @currentVersion output
	declare @source smallint = @currentVersion;
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
	exec dbo_updateLog @old = @currentVersion, @new = @tv
go

exec dbo_changeVersion @tv = 7;

select * from dbo.Version;
select * from dbo.VersionLog;
--delete from versionLog;
