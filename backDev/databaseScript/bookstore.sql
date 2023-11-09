CREATE USER 'bookstoredb'@'localhost' IDENTIFIED BY 'bookstoredb';

CREATE DATABASE  IF NOT EXISTS `bookstore`;


GRANT ALL PRIVILEGES ON * . * TO 'bookstoredb'@'localhost';

USE `bookstore`;


DROP TABLE IF EXISTS `authors`;

CREATE TABLE `authors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `birth_date` date not NULL,
  `biography` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

INSERT INTO `authors` VALUES 
	(1,'Leslie','Andrews','1985-01-16','she was famious who studied world war'),
	(2,'Emma','Baumgarten','1970-10-25','she was famious who studied world war'),
	(3,'Avani','Gupta','1965-09-16','she was famious who studied world war'),
	(4,'Yuri','Petrov','1948-02-19','she was famious who studied world war'),
	(5,'Juan','Vega','1965-03-17','she was famious who studied world war');
#
# Starting with MySQL 8.0.4, the MySQL team changed the 
# default authentication plugin for MySQL server 
# from mysql_native_password to caching_sha2_password.
#
# The command below will make the appropriate updates for your user account.
#
# See the MySQL Reference Manual for details: 
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#
ALTER USER 'shopmedb'@'localhost' IDENTIFIED WITH mysql_native_password BY 'shopmedb';