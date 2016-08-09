/*
SQLyog Ultimate v9.02 
MySQL - 5.6.17 : Database - fucsia
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`fucsia` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `fucsia`;

/*Table structure for table `magnifyingglass` */

DROP TABLE IF EXISTS `magnifyingglass`;

CREATE TABLE `magnifyingglass` (
  `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `magnifyingglass` */

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `image` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `level` int(255) unsigned NOT NULL,
  `presentation` varchar(255) NOT NULL,
  `telephone` int(255) unsigned NOT NULL,
  `type` varchar(255) NOT NULL,
  `userName` varchar(255) NOT NULL,
  `state` varchar(255) NOT NULL,
  `idMagnifyingGlass` bigint(255) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user` (`idMagnifyingGlass`),
  CONSTRAINT `FK_user` FOREIGN KEY (`idMagnifyingGlass`) REFERENCES `magnifyingglass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
