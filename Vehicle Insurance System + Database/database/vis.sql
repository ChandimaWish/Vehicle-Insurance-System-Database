-- MySQL dump 10.10
--
-- Host: localhost    Database: vis
-- ------------------------------------------------------
-- Server version	5.0.22-community-nt

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` int(11) NOT NULL auto_increment,
  `authority` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--


/*!40000 ALTER TABLE `authorities` DISABLE KEYS */;
LOCK TABLES `authorities` WRITE;
INSERT INTO `authorities` VALUES (1,'ROLE_ADMIN'),(2,'ROLE_USER');
UNLOCK TABLES;
/*!40000 ALTER TABLE `authorities` ENABLE KEYS */;

--
-- Table structure for table `tbl_insurance`
--

DROP TABLE IF EXISTS `tbl_insurance`;
CREATE TABLE `tbl_insurance` (
  `id` int(11) NOT NULL auto_increment,
  `provider` varchar(255) NOT NULL,
  `insurance_number` varchar(255) NOT NULL,
  `valid_date` varchar(255) NOT NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tbl_insurance_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_insurance`
--


/*!40000 ALTER TABLE `tbl_insurance` DISABLE KEYS */;
LOCK TABLES `tbl_insurance` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbl_insurance` ENABLE KEYS */;

--
-- Table structure for table `tbl_user`
--

DROP TABLE IF EXISTS `tbl_user`;
CREATE TABLE `tbl_user` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `gender` varchar(255) NOT NULL,
  `nationality` varchar(255) NOT NULL,
  `licence` varchar(255) NOT NULL,
  `authority_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `authority_id` (`authority_id`),
  CONSTRAINT `tbl_user_ibfk_1` FOREIGN KEY (`authority_id`) REFERENCES `authorities` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_user`
--


/*!40000 ALTER TABLE `tbl_user` DISABLE KEYS */;
LOCK TABLES `tbl_user` WRITE;
INSERT INTO `tbl_user` VALUES (1,'admin','$2a$10$sY.BERHNP50snZgNaGnKyemq3ey7gvjtJ0kp7QhCYgzdyFhxAk0G6','Male','Indian','LIC123',1),(2,'kishor','$2a$10$cAoBKMzzLs0rl8Luox5mz.Tp3o4GiZK3VvntNaKQyu.CZgMPHTybi','Male','Indian','LIC253',2);
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbl_user` ENABLE KEYS */;

--
-- Table structure for table `tbl_vehicles`
--

DROP TABLE IF EXISTS `tbl_vehicles`;
CREATE TABLE `tbl_vehicles` (
  `id` int(11) NOT NULL auto_increment,
  `source` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `plate_number` varchar(255) NOT NULL,
  `manufacture` varchar(255) NOT NULL,
  `type` varchar(255) NOT NULL,
  `color` varchar(255) NOT NULL,
  `registration_date` varchar(255) NOT NULL,
  `pending_fines` varchar(255) NOT NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `tbl_vehicles_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `tbl_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_vehicles`
--


/*!40000 ALTER TABLE `tbl_vehicles` DISABLE KEYS */;
LOCK TABLES `tbl_vehicles` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `tbl_vehicles` ENABLE KEYS */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

