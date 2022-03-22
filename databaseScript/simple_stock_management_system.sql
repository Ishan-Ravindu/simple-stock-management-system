--
--Database creation
--

DROP DATABASE IF EXISTS simple_stock_management_system;
CREATE DATABASE IF NOT EXISTS simple_stock_management_system;
USE simple_stock_management_system;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `user_id` int AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO user(name,email,password) VALUES('group11','group11@gmail.com','123@group11');
INSERT INTO user(name,email,password) VALUES('group','group@gmail.com','123@group');

--
-- Table structure for table `class`
--

DROP TABLE IF EXISTS `electronic`;
CREATE TABLE `electronic` (
  `electronic_id` int AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `power_type` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`electronic_id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO electronic(name,description,power_type,count) VALUES('table fan 1','fan description 1','AC',12);
INSERT INTO electronic(name,description,power_type,count) VALUES('table fan 2','fan description 2','AC',15);
INSERT INTO electronic(name,description,power_type,count) VALUES('table fan 3','fan description 3','DC',100);

--
-- Table structure for table `furniture`
--

DROP TABLE IF EXISTS `furniture`;
CREATE TABLE `furniture` (
  `furniture_id` int AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `material` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`furniture_id`)  
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO furniture(name,description,count,material) VALUES('teacher chair 1','description 1',10,'wood');
INSERT INTO furniture(name,description,count,material) VALUES('teacher chair 2','description 2',15,'wood');
INSERT INTO furniture(name,description,count,material) VALUES('teacher chair 3','description 3',20,'wood');
