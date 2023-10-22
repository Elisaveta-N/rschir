CREATE DATABASE IF NOT EXISTS rest_api_demo;
CREATE USER IF NOT EXISTS 'user'@'%' IDENTIFIED BY 'password';
GRANT SELECT,UPDATE,INSERT,DELETE ON rest_api_demo.* TO 'user'@'%';
FLUSH PRIVILEGES;
-- --------------------------------------------------------

--
-- Table structure for table `users`
--
USE rest_api_demo;
DROP TABLE IF EXISTS users;
CREATE TABLE IF NOT EXISTS users (
  user_id int(11) NOT NULL AUTO_INCREMENT,
  username varchar(32) NOT NULL DEFAULT '',
  user_email varchar(64) NOT NULL DEFAULT '',
  user_status int(8) NOT NULL DEFAULT '0',
  PRIMARY KEY (user_id)
);

--
-- Dumping data for table `employees`
--

INSERT INTO users (user_id, username, user_email, user_status) VALUES
(1, N'user1', N'user1@sample.com', '2264'),
(2, N'user2', N'user2@sample.com', '2265'),
(3, N'user3', N'user3@sample.com', '2266');
