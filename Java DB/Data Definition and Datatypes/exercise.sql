CREATE DATABASE `minions`;

USE `minions`;

CREATE TABLE `minions` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255),
`age` INT,
PRIMARY KEY (`id`)
);

CREATE TABLE `towns` (
`town_id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(255),
PRIMARY KEY (`town_id`)
);

ALTER TABLE `towns` 
CHANGE COLUMN `town_id` `id` INT NOT NULL AUTO_INCREMENT;

ALTER TABLE `minions`
ADD COLUMN `town_id` INT NOT NULL,
ADD CONSTRAINT `fk_key`
FOREIGN KEY (`town_id`)
REFERENCES `towns` (`id`);

INSERT INTO `towns` (`id`, `name`)
VALUES 
    (1, "Sofia"),
    (2, "Plovdiv"),
	(3, "Varna");

INSERT INTO `minions` (`id`, `name`, `age`, `town_id`)
VALUES 
(1, "Kevin", 22, 1),
(2, "Bob", 15, 3),
(3, "Steward", NULL, 2);

TRUNCATE TABLE `minions`;

DROP TABLE `minions`;
DROP TABLE `towns`;

CREATE TABLE `people` (
`id` INT NOT NULL AUTO_INCREMENT,
`name` VARCHAR(200) NOT NULL,
`picture` BLOB,
`height` DOUBLE(10,2),
`weight` DOUBLE(10,2),
`gender` ENUM('m', 'f') NOT NULL,
`birthdate` DATE NOT NULL,
`biography` TEXT,
PRIMARY KEY (`id`)
);

INSERT INTO `people` (`name`, `height`, `weight`, `gender`, `birthdate`, `biography`)
VALUES 
("vlado", 194.35, 60.5, 'm', '1996-07-26', "asdasdasdsadasdsadasd"),
("asdas", 194.35, 60.5, 'm', '1996-07-26', "asdasdasdsadasdsadasd"),
("ddd", 194.35, 60.5, 'f', '1996-07-26', "asdasdasdsadasdsadasd"),
("dd", 194.35, 60.5, 'f', '1996-07-26', "asdasdasdsadasdsadasd"),
("gosho", 194.35, 60.5, 'm', '1996-07-26', "asdasdasdsadasdsadasd");

CREATE TABLE `users` (
`id` INT NOT NULL AUTO_INCREMENT,
`username` VARCHAR(30) NOT NULL,
`password` VARCHAR(26) NOT NULL,
`profile_picture` BLOB,
`last_login_time` TIME,
`is_deleted` BOOLEAN,
PRIMARY KEY (`id`)
);

INSERT INTO `users` (`username`, `password`, `is_deleted`)
VALUES 
("test6" , "dads", 1),
("test1" , "dads", 0),
("test2" , "dads", 1),
("test3" , "dads", 1),
("test4" , "dads", 0);


ALTER TABLE `users`
DROP PRIMARY KEY,
ADD PRIMARY KEY `pk_users` (`id`, `username`);

ALTER TABLE `users`
MODIFY COLUMN `last_login_time` DATETIME DEFAULT NOW();

ALTER TABLE `users`
DROP PRIMARY KEY,
ADD PRIMARY KEY pk_users (`id`),
MODIFY COLUMN `username` VARCHAR(30) NOT NULL UNIQUE;

CREATE DATABASE `movies`;

USE `movies`;

CREATE TABLE `directors` (
`id` INT NOT NULL AUTO_INCREMENT,
`director_name` VARCHAR(255) NOT NULL,
`notes` TEXT,
PRIMARY KEY (`id`)
);

CREATE TABLE `genres` (
`id` INT NOT NULL AUTO_INCREMENT,
`genre_name` VARCHAR(255) NOT NULL,
`notes` TEXT,
PRIMARY KEY (`id`)
);

CREATE TABLE `categories` (
`id` INT NOT NULL AUTO_INCREMENT,
`category_name` VARCHAR(255) NOT NULL,
`notes` TEXT,
PRIMARY KEY (`id`)
);

CREATE TABLE `movies` (
`id` INT NOT NULL AUTO_INCREMENT,
`title` VARCHAR(255) NOT NULL,
`director_id` INT NOT NULL,
`copyright_year` DATE,
`length` DECIMAL,
`genre_id` INT NOT NULL,
`category_id` INT NOT NULL,
`rating` DECIMAL,
`notes` TEXT,
PRIMARY KEY (`id`)
);

ALTER TABLE `movies` 
ADD CONSTRAINT `fk_director_id`
FOREIGN KEY (`director_id`)
REFERENCES `directors` (`id`),
ADD CONSTRAINT `fk_genre_id`
FOREIGN KEY (`genre_id`)
REFERENCES `genres` (`id`),
ADD CONSTRAINT `fk_category_id`
FOREIGN KEY (`category_id`)
REFERENCES `categories` (`id`);


INSERT INTO `directors` (`director_name`)
VALUES 
("director1"),
("director2"),
("director3"),
("director4"),
("director5");

INSERT INTO `genres` (`genre_name`)
VALUES 
("genre1"),
("genre2"),
("genre3"),
("genre4"),
("genre5");

INSERT INTO `categories` (`category_name`)
VALUES 
("category1"),
("category2"),
("category3"),
("category4"),
("category5");

INSERT INTO `movies` (`title`, `copyright_year`, `length`)
VALUES 
("movie1", NOW(), 12.55),
("movie2", NOW(), 13.55),
("movie3", NOW(), 125),
("movie4", NOW(), 120.55534);


















