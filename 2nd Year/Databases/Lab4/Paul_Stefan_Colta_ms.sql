use master;

go

SET IDENTITY_INSERT dbo.Categories ON

INSERT INTO dbo.Categories(id, name, description) VALUES (2, 'tech', 'Tech Resources')

SET IDENTITY_INSERT dbo.Categories OFF


SET IDENTITY_INSERT dbo.Clients ON

INSERT INTO dbo.Clients(id, name, email, phone) VALUES ('1', 'Telekom', 'client@telekom.ro', '0712312312')

SET IDENTITY_INSERT dbo.Clients OFF


SET IDENTITY_INSERT dbo.Projects ON

--correct client_id
INSERT INTO dbo.Projects(id, name, description, client_id, category_id) VALUES ('1', 'First Project', 'This is the first project', '1', '2')

SET IDENTITY_INSERT dbo.Projects OFF


SET IDENTITY_INSERT dbo.Programmers ON

INSERT INTO dbo.Programmers(id, name, email, phone, birthday) VALUES ('1', 'Paul', 'paul@infonow.ro', '0799265027', '20010726')

SET IDENTITY_INSERT dbo.Programmers OFF


CREATE VIEW View_1 AS
SELECT id, name
FROM dbo.Equipment

CREATE VIEW View_2 AS
SELECT H.id, P.name, H.start_date
FROM dbo.Holidays H
INNER JOIN dbo.Programmers P ON P.id = H.programmer_id
GO


CREATE VIEW View_3 AS
SELECT Equipment = E.name, Programmer = P.name
FROM dbo.Equipment E
INNER JOIN dbo.EquipmentProgrammers EP ON EP.equipment_id = E.id
INNER JOIN dbo.Programmers P ON EP.programmer_id = P.id
GROUP BY E.name, P.name
GO


create procedure dbo_deleteAll @tableName varchar(50) as
-- todo: implement;
go

alter proc dbo_deleteAll @tableName varchar(50)
as
	declare @query varchar(100)
	set @query = 'Delete from ' + @tableName
	exec(@query)
go

create procedure dbo_insert_elements @nb_of_rows int, @table_name varchar(50) as
-- todo: implement;
go

alter procedure dbo_insert_elements @nb_of_rows int, @table_name varchar(50) as
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

create procedure dbo_delete_rows @nb_of_rows int, @table_name varchar(50) as
-- todo: implement;
go

alter procedure dbo_delete_rows @nb_of_rows int, @table_name varchar(50) as
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

create procedure dbo_select_view @view varchar(10) as
-- todo: implement;
go

alter procedure dbo_select_view @view varchar(10) as
    if @view = 'View_1'
        select * from View_1
    if @view = 'View_2'
        select * from View_2
    if @view = 'View_3'
        select * from View_3
go

create procedure dbo_run_tests @nb_of_rows int as
-- todo: implement;
go

alter procedure dbo_run_tests @nb_of_rows int as
    if @nb_of_rows < 1
        RAISERROR('Invalid number of rows', 16, 1);

    declare @all_start datetime
    set @all_start = getdate()

    declare @equipment_insert_start datetime
    set @equipment_insert_start = getdate()
    exec dbo_insert_elements @nb_of_rows, 'Equipment'
    declare @equipment_insert_end datetime
    set @equipment_insert_end = getdate()

    declare @holiday_insert_start datetime
    set @holiday_insert_start = getdate()
    exec dbo_insert_elements @nb_of_rows, 'Holidays'
    declare @holiday_insert_end datetime
    set @holiday_insert_end = getdate()

    declare @equipment_programmers_insert_start datetime
    set @equipment_programmers_insert_start = getdate()
    exec dbo_insert_elements @nb_of_rows, 'EquipmentProgrammers'
    declare @equipment_programmers_insert_end datetime
    set @equipment_programmers_insert_end = getdate()

    declare @equipment_programmers_delete_start datetime
    set @equipment_programmers_delete_start = getdate()
    exec dbo_delete_rows @nb_of_rows, 'EquipmentProgrammers'
    declare @equipment_programmers_delete_end datetime
    set @equipment_programmers_delete_end = getdate()

    declare @holiday_delete_start datetime
    set @holiday_delete_start = getdate()
    exec dbo_delete_rows @nb_of_rows, 'Holiday'
    declare @holiday_delete_end datetime
    set @holiday_delete_end = getdate()

    declare @equipment_delete_start datetime
    set @equipment_delete_start = getdate()
    exec dbo_delete_rows @nb_of_rows, 'Equipment'
    declare @equipment_delete_end datetime
    set @equipment_delete_end = getdate()

    declare @view_1_start datetime
	set @view_1_start = getdate()
	execute dbo_select_view 'View_1'
	declare @view_1_end datetime
	set @view_1_end = getdate()

	declare @view_2_start datetime
	set @view_2_start = getdate()
	execute dbo_select_view 'View_2'
	declare @view_2_end datetime
	set @view_2_end = getdate()

    declare @view_3_start datetime
	set @view_3_start = getdate()
	execute dbo_select_view 'View_3'
	declare @view_3_end datetime
	set @view_3_end = getdate()

	declare @all_stop datetime
	set @all_stop = getdate()

    declare @description varchar(100)

	set @description = 'TestRun ' + convert(varchar(7), (select max(TestRunID) from TestRuns)) + 'delete, insert' + convert(varchar(10), @nb_of_rows) + 'rows, select all views'
    print(@description)
    insert into TestRuns(Description, StartAt, EndAt)
	values(@description, @all_start, @all_stop);

    declare @lastTestRunID int;
	set @lastTestRunID = (select max(TestRunID) from TestRuns);

	insert into TestRunTables
	values(@lastTestRunID, 1, @equipment_insert_start, @equipment_insert_end)

	insert into TestRunTables
	values(@lastTestRunID, 2, @holiday_insert_start, @holiday_insert_end)

	insert into TestRunTables
	values(@lastTestRunID, 3, @equipment_programmers_insert_start, @equipment_programmers_insert_end)

	insert into TestRunTables
	values(@lastTestRunID, 3, @equipment_programmers_delete_start, @equipment_programmers_delete_end)

	insert into TestRunTables
	values(@lastTestRunID, 2, @holiday_delete_start, @holiday_delete_end)

	insert into TestRunTables
	values(@lastTestRunID, 1, @equipment_delete_start, @equipment_delete_end)

	insert into TestRunViews
	values(@lastTestRunID, 1, @view_1_start, @view_1_end)

	insert into TestRunViews
	values(@lastTestRunID, 2, @view_2_start, @view_2_end)

	insert into TestRunViews
	values(@lastTestRunID, 3, @view_3_start, @view_3_end)

    exec dbo_deleteAll @tableName = 'EquipmentProgrammers'
    exec dbo_deleteAll @tableName = 'Equipment'
    exec dbo_deleteAll @tableName = 'Holidays'
go
exec dbo_run_tests @nb_of_rows = 10
    declare @all_start datetime
    set @all_start = getdate()
    declare @all_stop datetime
    set @all_stop = getdate()
    declare @nb_of_rows int
    set @nb_of_rows = 10
    declare @description varchar(100)
	set @description = 'TestRun' + convert(varchar(7), (select max(TestRunID) from TestRuns)) + 'delete, insert' + convert(varchar(10), @nb_of_rows) + 'rows, select all views'

    insert into TestRuns(Description, StartAt, EndAt)
	values(@description, @all_start, @all_stop);

exec dbo_insert_elements  @nb_of_rows = 10, @table_name = 'EquipmentProgrammers'
exec dbo_delete_rows  @nb_of_rows = 5, @table_name = 'Holidays'

exec dbo_deleteAll @tableName = 'EquipmentProgrammers'
exec dbo_deleteAll @tableName = 'Equipment'
exec dbo_deleteAll @tableName = 'Holidays'
exec dbo_deleteAll @tableName = 'TestRunTables'
exec dbo_deleteAll @tableName = 'TestRunViews'
exec dbo_deleteAll @tableName = 'TestRuns'
exec dbo_deleteAll @tableName = 'TestRunTables'



--
if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_Tables

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tables]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tables

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunTables_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunTables] DROP CONSTRAINT FK_TestRunTables_TestRuns

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_TestRuns]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_TestRuns

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestTables_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestTables] DROP CONSTRAINT FK_TestTables_Tests

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Tests]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Tests

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestRunViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestRunViews] DROP CONSTRAINT FK_TestRunViews_Views

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[FK_TestViews_Views]') and OBJECTPROPERTY(id, N'IsForeignKey') = 1)

ALTER TABLE [TestViews] DROP CONSTRAINT FK_TestViews_Views

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Tables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Tables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRunTables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRunViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRunViews]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestRuns]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestRuns]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestTables]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestTables]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[TestViews]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [TestViews]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Tests]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Tests]

GO



if exists (select * from dbo.sysobjects where id = object_id(N'[Views]') and OBJECTPROPERTY(id, N'IsUserTable') = 1)

drop table [Views]

GO



CREATE TABLE [Tables] (

	[TableID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [TestRunTables] (

	[TestRunID] [int] NOT NULL ,

	[TableID] [int] NOT NULL ,

	[StartAt] [datetime] NOT NULL ,

	[EndAt] [datetime] NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [TestRunViews] (

	[TestRunID] [int] NOT NULL ,

	[ViewID] [int] NOT NULL ,

	[StartAt] [datetime] NOT NULL ,

	[EndAt] [datetime] NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [TestRuns] (

	[TestRunID] [int] IDENTITY (1, 1) NOT NULL ,

	[Description] [nvarchar] (2000) COLLATE SQL_Latin1_General_CP1_CI_AS NULL ,

	[StartAt] [datetime] NULL ,

	[EndAt] [datetime] NULL

) ON [PRIMARY]

GO



CREATE TABLE [TestTables] (

	[TestID] [int] NOT NULL ,

	[TableID] [int] NOT NULL ,

	[NoOfRows] [int] NOT NULL ,

	[Position] [int] NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [TestViews] (

	[TestID] [int] NOT NULL ,

	[ViewID] [int] NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [Tests] (

	[TestID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL

) ON [PRIMARY]

GO



CREATE TABLE [Views] (

	[ViewID] [int] IDENTITY (1, 1) NOT NULL ,

	[Name] [nvarchar] (50) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL

) ON [PRIMARY]

GO



ALTER TABLE [Tables] WITH NOCHECK ADD

	CONSTRAINT [PK_Tables] PRIMARY KEY  CLUSTERED

	(

		[TableID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestRunTables] WITH NOCHECK ADD

	CONSTRAINT [PK_TestRunTables] PRIMARY KEY  CLUSTERED

	(

		[TestRunID],

		[TableID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestRunViews] WITH NOCHECK ADD

	CONSTRAINT [PK_TestRunViews] PRIMARY KEY  CLUSTERED

	(

		[TestRunID],

		[ViewID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestRuns] WITH NOCHECK ADD

	CONSTRAINT [PK_TestRuns] PRIMARY KEY  CLUSTERED

	(

		[TestRunID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestTables] WITH NOCHECK ADD

	CONSTRAINT [PK_TestTables] PRIMARY KEY  CLUSTERED

	(

		[TestID],

		[TableID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestViews] WITH NOCHECK ADD

	CONSTRAINT [PK_TestViews] PRIMARY KEY  CLUSTERED

	(

		[TestID],

		[ViewID]

	)  ON [PRIMARY]

GO



ALTER TABLE [Tests] WITH NOCHECK ADD

	CONSTRAINT [PK_Tests] PRIMARY KEY  CLUSTERED

	(

		[TestID]

	)  ON [PRIMARY]

GO



ALTER TABLE [Views] WITH NOCHECK ADD

	CONSTRAINT [PK_Views] PRIMARY KEY  CLUSTERED

	(

		[ViewID]

	)  ON [PRIMARY]

GO



ALTER TABLE [TestRunTables] ADD

	CONSTRAINT [FK_TestRunTables_Tables] FOREIGN KEY

	(

		[TableID]

	) REFERENCES [Tables] (

		[TableID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestRunTables_TestRuns] FOREIGN KEY

	(

		[TestRunID]

	) REFERENCES [TestRuns] (

		[TestRunID]

	) ON DELETE CASCADE  ON UPDATE CASCADE

GO



ALTER TABLE [TestRunViews] ADD

	CONSTRAINT [FK_TestRunViews_TestRuns] FOREIGN KEY

	(

		[TestRunID]

	) REFERENCES [TestRuns] (

		[TestRunID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestRunViews_Views] FOREIGN KEY

	(

		[ViewID]

	) REFERENCES [Views] (

		[ViewID]

	) ON DELETE CASCADE  ON UPDATE CASCADE

GO



ALTER TABLE [TestTables] ADD

	CONSTRAINT [FK_TestTables_Tables] FOREIGN KEY

	(

		[TableID]

	) REFERENCES [Tables] (

		[TableID]

	) ON DELETE CASCADE  ON UPDATE CASCADE ,

	CONSTRAINT [FK_TestTables_Tests] FOREIGN KEY

	(

		[TestID]

	) REFERENCES [Tests] (

		[TestID]

	) ON DELETE CASCADE  ON UPDATE CASCADE

GO



ALTER TABLE [TestViews] ADD

	CONSTRAINT [FK_TestViews_Tests] FOREIGN KEY

	(

		[TestID]

	) REFERENCES [Tests] (

		[TestID]

	),

	CONSTRAINT [FK_TestViews_Views] FOREIGN KEY

	(

		[ViewID]

	) REFERENCES [Views] (

		[ViewID]

	)

GO


