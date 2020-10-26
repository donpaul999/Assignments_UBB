--INSERT

SET IDENTITY_INSERT ubb_schema.Categories ON

INSERT INTO ubb_schema.Categories(id, name, description) VALUES (2, 'tech', 'Tech Resources')

SET IDENTITY_INSERT ubb_schema.Categories OFF


SET IDENTITY_INSERT ubb_schema.Clients ON

INSERT INTO ubb_schema.Clients(id, name, email, phone) VALUES ('1', 'Telekom', 'client@telekom.ro', '0712312312')

SET IDENTITY_INSERT ubb_schema.Clients OFF


SET IDENTITY_INSERT ubb_schema.Projects ON

--wrong client_id
INSERT INTO ubb_schema.Projects(id, name, description, client_id, category_id) VALUES ('1', 'First Project', 'This is the first project', '12', '1')
--[2020-10-12 16:05:54] [23000][547] The INSERT statement conflicted with the FOREIGN KEY constraint "client_id". The conflict occurred in database "ubb_db", table "ubb_schema.Clients", column 'id'.


--correct client_id
INSERT INTO ubb_schema.Projects(id, name, description, client_id, category_id) VALUES ('1', 'First Project', 'This is the first project', '1', '1')

SET IDENTITY_INSERT ubb_schema.Projects OFF


SET IDENTITY_INSERT ubb_schema.Programmers ON

INSERT INTO ubb_schema.Programmers(id, name, email, phone, birthday) VALUES ('1', 'Paul', 'paul@infonow.ro', '0799265027', '20010726')

SET IDENTITY_INSERT ubb_schema.Programmers OFF


SET IDENTITY_INSERT ubb_schema.ProjectsProgrammers ON

--wrong programmer_id
INSERT INTO ubb_schema.ProjectsProgrammers(id, project_id, programmer_id) VALUES ('1', '1', '2')

--correct programmer_id
INSERT INTO ubb_schema.ProjectsProgrammers(id, project_id, programmer_id) VALUES ('1', '1', '1')





--UPDATE

UPDATE ubb_schema.Projects
SET description='Telekom Projects' WHERE client_id IN(1,3,5,7)

UPDATE ubb_schema.Categories
SET description='This is one of the first 3 categories of our projects' WHERE id BETWEEN 1 AND 3

UPDATE ubb_schema.Programmers
SET name='Stefan' WHERE name LIKE 'Paul%'




--DELETE

--I insert a new category
SET IDENTITY_INSERT ubb_schema.Categories ON

INSERT INTO ubb_schema.Categories(id, name) VALUES (3, 'cars')

SET IDENTITY_INSERT ubb_schema.Categories OFF

DELETE ubb_schema.Categories
WHERE description IS NULL


--I inserted 2 Holiday dates
SET IDENTITY_INSERT ubb_schema.Holidays ON

INSERT INTO ubb_schema.Holidays(id, programmer_id, start_date, end_date) VALUES (1, 1, '20050720', '20050725')
INSERT INTO ubb_schema.Holidays(id, programmer_id, start_date, end_date) VALUES (2, 1, '20250720', '20250725')
SET IDENTITY_INSERT ubb_schema.Holidays OFF

DELETE FROM ubb_schema.Holidays
WHERE start_date <= GETDATE()






--UNION

SELECT id FROM ubb_schema.Projects
WHERE name LIKE '%project%' OR name LIKE 'First%'
UNION
SELECT id FROM ubb_schema.Clients


SELECT id, birthday FROM ubb_schema.Programmers
WHERE name LIKE '%Paul%' OR name LIKE 'Stefan%'
UNION
SELECT id, start_date FROM ubb_schema.Holidays



--INTERSEC

INSERT INTO ubb_schema.Programmers(name, email, phone, birthday) VALUES ('Alex', 'a@infonow.ro', '0712312312', '20000523')

SELECT id, name FROM ubb_schema.Clients
WHERE id IN (2,4,6,8)
INTERSECT
SELECT id, name FROM ubb_schema.Programmers

SELECT name FROM ubb_schema.Technologies
INTERSECT
SELECT name FROM ubb_schema.Projects
WHERE id IN (1, 4)



--EXCEPT


SELECT id, name FROM ubb_schema.Projects
EXCEPT
SELECT id, name FROM ubb_schema.Technologies
WHERE id NOT IN (1,3,5)

SELECT id, name FROM ubb_schema.Clients
EXCEPT
SELECT id, name FROM ubb_schema.Programmers
WHERE id NOT IN (2,4,6,8)


--JOIN

SELECT P.name, E.name
FROM ubb_schema.Programmers P
RIGHT OUTER JOIN ubb_schema.EquipmentProgrammers EP ON P.id = EP.programmer_id
RIGHT OUTER JOIN ubb_schema.Equipment E ON EP.equipment_id = E.id


SELECT P.name, Pr.name
FROM ubb_schema.Programmers P
LEFT OUTER JOIN ubb_schema.ProjectsProgrammers PP ON P.id = PP.programmer_id
LEFT OUTER JOIN ubb_schema.Projects Pr ON Pr.id = PP.project_id

-- m:n
SELECT T.name, Pr.name
FROM ubb_schema.Technologies T
INNER JOIN ubb_schema.TechnologiesProjects TP ON T.id = TP.technology_id
INNER JOIN ubb_schema.Projects Pr ON Pr.id = TP.project_id
INNER JOIN ubb_schema.Clients C ON Pr.client_id = C.id
WHERE C.name='Telekom'

SELECT * FROM ubb_schema.Programmers P 
FULL JOIN ubb_schema.ProjectsProgrammers PP on P.id = PP.programmer_id
FULL JOIN ubb_schema.Projects Pr on Pr.id = PP.project_id 

--IN

SELECT name FROM ubb_schema.Programmers WHERE id IN (SELECT programmer_id from ubb_schema.ProjectsProgrammers)


SELECT name FROM ubb_schema.Technologies WHERE id IN (SELECT technology_id from ubb_schema.TechnologiesProjects WHERE project_id IN (SELECT id FROM Projects WHERE name LIKE '%Project'))


--FROM

SELECT DISTINCT * 
FROM ubb_schema.Technologies t INNER JOIN (SELECT * from ubb_schema.Technologies) a on a.technology_id = t.id

SELECT * FROM (SELECT name FROM ubb_schema.Clients);

--GROUP BY

SELECT COUNT(equipment_id) FROM ubb_schema.EquipmentProgrammers
GROUP BY programmer_id HAVING COUNT(*) > 1

select Pr.*, T.max_programmers
from ubb_schema.Projects Pr inner join (select Pp.project_id, count(*) max_programmers
						 from ubb_schema.ProjectsProgrammers Pp
						 group by Pp.project_id
						 having count(*) = (select max(numberOfProgrammers)
											from (select count(*) numberOfProgrammers
												  from ubb_schema.ProjectsProgrammers Pp2
												  group by Pp2.project_id) t) ) T on Pr.id = T.project_id;


--ANY

SELECT * FROM ubb_schema.Programmers WHERE id = ANY(SELECT programmer_id FROM ubb_schema.ProgrammersEquipment)


SELECT * FROM ubb_schema.Projects WHERE id = ANY(SELECT project_id FROM ubb_schema.ProjectsTechnologies INNER JOIN ubb_schema.Technolgy T on T.id = technology_id WHERE T.name='C++')

--ALL

SELECT * FROM ubb_schema.Programmers P WHERE birthdate > ALL(SELECT birthdate FROM ubb_schema.Programmers WHERE id <> P.id)





