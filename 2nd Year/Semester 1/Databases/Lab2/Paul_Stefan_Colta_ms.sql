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

SELECT id, name FROM ubb_schema.Projects
WHERE name LIKE '%project%' OR name LIKE 'First%'
UNION
SELECT id, name FROM ubb_schema.Clients


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


SELECT name FROM ubb_schema.Technologies
EXCEPT
SELECT name FROM ubb_schema.Projects
WHERE id NOT IN (3,5) ORDER BY name


SELECT name FROM ubb_schema.Projects
EXCEPT
SELECT name FROM ubb_schema.Technologies
WHERE id NOT IN (3,5) ORDER BY name


--JOIN

--Display all programmers & equipments and unused equipments.
SELECT P.name, E.name
FROM ubb_schema.Programmers P
RIGHT OUTER JOIN ubb_schema.EquipmentProgrammers EP ON P.id = EP.programmer_id
RIGHT OUTER JOIN ubb_schema.Equipment E ON EP.equipment_id = E.id


--Display all programmers & projects and programmers with no project.
SELECT P.name, Pr.name
FROM ubb_schema.Programmers P
LEFT OUTER JOIN ubb_schema.ProjectsProgrammers PP ON P.id = PP.programmer_id
LEFT OUTER JOIN ubb_schema.Projects Pr ON Pr.id = PP.project_id

-- m:n
--Display projects & technologies where project's client is telekom
SELECT T.name, Pr.name
FROM ubb_schema.Technologies T
INNER JOIN ubb_schema.TechnologiesProjects TP ON T.id = TP.technology_id
INNER JOIN ubb_schema.Projects Pr ON Pr.id = TP.project_id
INNER JOIN ubb_schema.Clients C ON Pr.client_id = C.id
WHERE C.name='Telekom'

--Display all programmers & projects even if there are projects with no programmers or programmers with no projects.
SELECT * FROM ubb_schema.Programmers P 
FULL JOIN ubb_schema.ProjectsProgrammers PP on P.id = PP.programmer_id
FULL JOIN ubb_schema.Projects Pr on Pr.id = PP.project_id 

--IN

--Display programmers with projects.
SELECT name FROM ubb_schema.Programmers WHERE id IN (SELECT programmer_id from ubb_schema.ProjectsProgrammers)


--Display technologies used for all the projects.
SELECT name FROM ubb_schema.Technologies WHERE id IN (
    SELECT technology_id from ubb_schema.TechnologiesProjects WHERE project_id IN (
        SELECT id FROM ubb_schema.Projects WHERE name LIKE '%Project'))


--FROM
--Display technologies used for all the projects.
SELECT DISTINCT T.name
FROM ubb_schema.Technologies T INNER JOIN (
    SELECT * FROM ubb_schema.TechnologiesProjects) AS A ON A.technology_id = T.id

--Display the clients.
SELECT * FROM (SELECT name FROM ubb_schema.Clients) as N ORDER BY name;

--GROUP BY

--Display clients that requested projects
SELECT name FROM ubb_schema.Clients WHERE id IN (SELECT client_id FROM ubb_schema.Projects)
GROUP BY name

--Display programmers which are using equipment.
SELECT name FROM ubb_schema.Programmers WHERE id IN (
    SELECT programmer_id FROM ubb_schema.EquipmentProgrammers
GROUP BY programmer_id HAVING COUNT(*) > 0)


--Display projects with the maximum number of programmers working on them.
SELECT Pr.*, T.max_programmers
FROM ubb_schema.Projects Pr INNER JOIN (SELECT Pp.project_id, COUNT(*) max_programmers
						 FROM ubb_schema.ProjectsProgrammers Pp
						 GROUP BY Pp.project_id
						 HAVING COUNT(*) = (SELECT MAX(A.numberOfProgrammers)
											FROM (SELECT COUNT(*) numberOfProgrammers
												  FROM ubb_schema.ProjectsProgrammers Pp2
												  GROUP BY Pp2.project_id) A)
    ) T ON Pr.id = T.project_id;

--Display the client that requested the most projects
SELECT * FROM ubb_schema.Clients WHERE id IN (SELECT client_id FROM ubb_schema.Projects
GROUP BY client_id HAVING COUNT(*) = (SELECT MAX(A.clients) FROM (SELECT COUNT(*) clients
												  FROM ubb_schema.Projects Pr
												  GROUP BY Pr.client_id) A))


--EXISTS
--Display all projects with programmers 
SELECT * FROM ubb_schema.Projects P WHERE EXISTS(
    SELECT * FROM ubb_schema.ProjectsProgrammers Pp WHERE P.id = Pp.project_id)

--Display all the programmers with more than 1 equipment borrowed.
SELECT * FROM ubb_schema.Programmers P WHERE EXISTS(
    SELECT programmer_id FROM ubb_schema.EquipmentProgrammers EP WHERE P.id = EP.programmer_id
    GROUP BY programmer_id HAVING COUNT(*) > 1)

--ANY
--Display programmers that use equipment.
SELECT * FROM ubb_schema.Programmers 
WHERE id = ANY(SELECT programmer_id FROM ubb_schema.EquipmentProgrammers)

--Display the projects where C++ is used.
SELECT * FROM ubb_schema.Projects
WHERE id = ANY(SELECT project_id FROM ubb_schema.TechnologiesProjects
    INNER JOIN ubb_schema.Technologies T on T.id = technology_id WHERE T.name='C++')

--ALL
--Display the youngest programmer.
SELECT * FROM ubb_schema.Programmers P WHERE P.birthday > ALL(
    SELECT birthday FROM ubb_schema.Programmers WHERE id <> P.id)

--Display the project with the shortest name
SELECT * FROM ubb_schema.Projects P WHERE LEN(P.name) < ALL(SELECT LEN(name)
FROM ubb_schema.Projects WHERE id <> P.id)

--Aggregation
SELECT * FROM ubb_schema.Programmers WHERE birthday =
                                           (SELECT MAX(birthday) FROM ubb_schema.Programmers)

SELECT * FROM ubb_schema.Projects WHERE name = (SELECT MIN(name) FROM ubb_schema.Projects)

--[NOT] IN
SELECT * FROM ubb_schema.Programmers
WHERE id IN (SELECT programmer_id FROM ubb_schema.EquipmentProgrammers)

SELECT * FROM ubb_schema.Projects
WHERE id NOT IN (SELECT project_id FROM ubb_schema.TechnologiesProjects
    INNER JOIN ubb_schema.Technologies T on T.id = technology_id WHERE T.name <> 'C++')



