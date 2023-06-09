CREATE SCHEMA football;
USE football;

CREATE TABLE countries (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL
);

CREATE TABLE towns (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL,
country_id INT NOT NULL,
CONSTRAINT fk_towns_countries
FOREIGN KEY (country_id)
REFERENCES countries (id)
);

CREATE TABLE stadiums (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL,
capacity INT NOT NULL,
town_id INT NOT NULL,
CONSTRAINT fk_stadiums_towns
FOREIGN KEY (town_id)
REFERENCES towns (id)
);

CREATE TABLE teams (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
name VARCHAR(45) NOT NULL,
established DATE NOT NULL,
fan_base BIGINT NOT NULL DEFAULT(0),
stadium_id INT NOT NULL,
CONSTRAINT fk_teams_stadiums
FOREIGN KEY (stadium_id)
REFERENCES stadiums (id)
);

CREATE TABLE coaches (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
salary DECIMAL(10,2) NOT NULL DEFAULT(0),
coach_level INT NOT NULL DEFAULT(0)
);

CREATE TABLE skills_data (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
dribbling INT DEFAULT(0),
pace INT DEFAULT(0),
passing INT DEFAULT(0),
shooting INT DEFAULT(0),
speed INT DEFAULT(0),
strength INT DEFAULT(0)
);

CREATE TABLE players (
id INT AUTO_INCREMENT NOT NULL PRIMARY KEY,
first_name VARCHAR(10) NOT NULL,
last_name VARCHAR(20) NOT NULL,
age INT NOT NULL DEFAULT(0),
position CHAR(1) NOT NULL,
salary DECIMAL(10,2) NOT NULL DEFAULT(0),
hire_date DATETIME,
skills_data_id INT NOT NULL,
team_id INT,
CONSTRAINT fk_players_skills_data
FOREIGN KEY (skills_data_id)
REFERENCES skills_data (id),
CONSTRAINT fk_players_teams
FOREIGN KEY (team_id)
REFERENCES teams (id)
);

CREATE TABLE players_coaches (
player_id INT,
coach_id INT,
PRIMARY KEY (player_id, coach_id),
CONSTRAINT fk_players_coaches_coaches
FOREIGN KEY (coach_id)
REFERENCES coaches (id),
CONSTRAINT fk_players_coaches_players
FOREIGN KEY (player_id)
REFERENCES players (id)
);


INSERT INTO coaches(first_name, last_name, salary, coach_level)
SELECT first_name, last_name, (salary * 2) as salary, char_length(first_name) AS coach_level
FROM players
WHERE age >= 45;

UPDATE coaches AS c 
SET 
    c.coach_level = c.coach_level + 1
WHERE
    c.id IN (SELECT 
            coach_id
        FROM
            players_coaches)
        AND first_name LIKE 'A%';


DELETE FROM players as p
WHERE p.age >= 45;


###########################

SELECT 
    first_name, age, salary
FROM
    players
ORDER BY salary DESC;

SELECT 
    p.id,
    CONCAT(p.first_name, ' ', p.last_name) AS full_name,
    p.age,
    p.position,
    p.hire_date
FROM
    players AS p
        JOIN
    skills_data AS sd ON p.skills_data_id = sd.id
WHERE
    position = 'A' AND hire_date IS NULL
        AND sd.strength > 50
ORDER BY salary , age;


SELECT t.name AS team_name,
t.established,
t.fan_base,
COUNT(p.id) as players_count
 FROM teams as t
 LEFT JOIN players as p ON t.id = p.team_id
 GROUP BY t.id
 ORDER BY players_count DESC, fan_base DESC;


SELECT 
MAX(sd.speed) as max_speed,
tw.name as town_name 
FROM
skills_data as sd
RIGHT JOIN players as p ON sd.id = p.skills_data_id
RIGHT JOIN teams as t ON t.id = p.team_id 
RIGHT JOIN stadiums as s ON s.id = t.stadium_id
RIGHT JOIN towns as tw ON tw.id = s.town_id 
WHERE t.name != 'Devify'
GROUP BY town_name
ORDER BY max_speed DESC, town_name;


SELECT 
c.name,
COUNT(p.id) as total_count_of_players,
SUM(p.salary) as total_sum_of_salaries
 FROM
 countries as c
 LEFT JOIN towns as tw ON c.id = tw.country_id
 LEFT JOIN stadiums as s ON s.town_id = tw.id
 LEFT JOIN teams as t ON t.stadium_id = s.id
 LEFT JOIN players as p ON p.team_id = t.id
 GROUP BY c.id
 ORDER BY total_count_of_players DESC, c.name;


DELIMITER $$
CREATE FUNCTION udf_stadium_players_count(stadium_name VARCHAR(30))
RETURNS INT
DETERMINISTIC
BEGIN
RETURN (SELECT COUNT(p.id) as count
FROM stadiums as s 
JOIN teams as t ON t.stadium_id = s.id
JOIN players as p ON p.team_id = t.id
WHERE s.name = stadium_name);
END$$

DELIMITER $$
CREATE PROCEDURE udp_find_playmaker(min_dribble_points INT, team_name VARCHAR(45))
BEGIN
SELECT 
CONCAT(p.first_name, ' ', p.last_name) AS full_name,
p.age,
p.salary,
sd.dribbling,
sd.speed,
t.name as team_name
FROM teams as t
JOIN players as p ON p.team_id = t.id
JOIN skills_data as sd ON sd.id = p.skills_data_id
WHERE sd.dribbling > min_dribble_points AND t.name = team_name AND sd.speed > (SELECT AVG(speed) FROM skills_data)
ORDER BY sd.speed DESC
LIMIT 1;
END$$



