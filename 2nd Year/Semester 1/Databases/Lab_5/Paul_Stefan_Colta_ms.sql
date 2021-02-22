use master;

go

create table dbo.Ta (
	aid int primary key identity(1, 1),
	a2 int unique,
	a3 int
)

create table dbo.Tb (
	bid int primary key identity(1, 1),
	b2 int,
	b3 int
)

create table dbo.Tc (
	cid int primary key identity(1, 1),
	aid int,
	bid int
)

--a)
--clustered index scan
select aid from dbo.Ta

--clustered index seek
select aid from dbo.Ta where aid > 5

--nonclustered index scan
select a2 from dbo.Ta

--nonclustered index seek
select a2 from dbo.Ta where a2 > 1

--key lookup
select * from Ta

--b)
select b2 from dbo.Tb where b2 = 5 --clustered index scan - 20 ms

if exists(select * from sys.indexes where name = 'index_b2')
	drop index index_b2 on dbo.Tb
create nonclustered index index_b2 on dbo.Tb(b2)

select b2 from dbo.Tb where b2 = 5 --nonclustered index scan - 19 ms

--c)

CREATE VIEW View_TaTc AS
    SELECT Tc.cid, Tc.aid
    FROM dbo.Tc inner join dbo.Ta T on T.aid = dbo.Tc.aid
    WHERE dbo.Tc.aid between 1 and 100
go

select * from View_TaTc --clustered index scan on Tc and clustered index seek on Ta - 21 ms

if exists(select * from sys.indexes where name = 'index_aid')
	drop index index_aid on dbo.Tc
create nonclustered index index_aid on dbo.Tc(aid)

select * from View_TaTc --nonclustered index scan on Tc and clustered index seek on Ta - 20 ms