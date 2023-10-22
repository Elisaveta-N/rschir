CREATE DATABASE IF NOT EXISTS appDB;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';
GRANT SELECT,UPDATE,INSERT ON appDB.* TO 'user'@'%';
FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- Table structure for table `employees`
--
USE appDB;
DROP TABLE IF EXISTS students;
CREATE TABLE IF NOT EXISTS students (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32) NOT NULL,
  lastname varchar(64) NOT NULL,
  groupnum varchar(10) NOT NULL,
  coursenum int(4) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Dumping data for table `employees`
--

INSERT INTO students (id, name, lastname, groupnum, coursenum) VALUES
(1, N'Степан', N'Поплавский', '2264', 1),
(2, N'Мария', N'Складовская', '1154', 3),
(3, N'Дмитрий', N'Менделеев', '6584', 4),
(4, N'Федор', N'Разумовский', '5471', 2),
(5, N'Наталья', N'Романова', '2541', 5);

