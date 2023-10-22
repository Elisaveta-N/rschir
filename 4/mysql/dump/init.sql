CREATE DATABASE IF NOT EXISTS appDB;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';
GRANT SELECT,UPDATE,INSERT ON appDB.* TO 'user'@'%';
FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- Table structure for table `employees`
--
USE appDB;
DROP TABLE IF EXISTS goods;
CREATE TABLE IF NOT EXISTS goods (
  id int(11) NOT NULL AUTO_INCREMENT,
  name varchar(32) NOT NULL,
  cost int(4) NOT NULL,
  PRIMARY KEY (id)
);

--
-- Dumping data for table `employees`
--

INSERT INTO goods (id, name, cost) VALUES
(1, N'Шорты женские', 1000),
(2, N'Рубашка мужская', 3000),
(3, N'Платье в цветочек', 2400),
(4, N'Футболка красная', 2000);