/*
SQLyog Enterprise - MySQL GUI v8.18 
MySQL - 5.1.51-community : Database - fucsia
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `magnifyingglass` */

insert  into `magnifyingglass`(`id`,`name`,`quantity`) values (1,'Grupo 1',15);

/*Table structure for table `profile` */

DROP TABLE IF EXISTS `profile`;

CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `dateC` datetime NOT NULL,
  `dateD` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `profile` */

insert  into `profile`(`id`,`name`,`dateC`,`dateD`) values (1,'Admin','2016-09-07 11:59:56',NULL),(2,'User','2016-09-07 12:00:24',NULL);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint(255) unsigned NOT NULL AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `level` int(255) unsigned DEFAULT NULL,
  `presentation` varchar(255) DEFAULT NULL,
  `telephone` int(255) unsigned DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` varchar(255) DEFAULT NULL,
  `dateC` datetime NOT NULL,
  `dateD` datetime DEFAULT NULL,
  `idMagnifyingGlass` bigint(255) unsigned DEFAULT NULL,
  `idProfile` bigint(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_user` (`idMagnifyingGlass`),
  KEY `idProfile` (`idProfile`),
  CONSTRAINT `FK_user` FOREIGN KEY (`idMagnifyingGlass`) REFERENCES `magnifyingglass` (`id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`idProfile`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert  into `user`(`id`,`image`,`name`,`lastName`,`level`,`presentation`,`telephone`,`type`,`email`,`userName`,`password`,`state`,`dateC`,`dateD`,`idMagnifyingGlass`,`idProfile`) values (1,NULL,'fede','fede',NULL,NULL,123123,NULL,'fede','fede','fede',NULL,'2016-09-07 12:05:17','2016-09-07 13:02:35',NULL,1),(2,'fa.jpg','f','f',1,'ede',232,'fef','f','f','f','efef','2016-09-07 12:05:17',NULL,1,2),(3,'fa.jpg','l','l',2,'j',2323,'j','l','l','l','j','2016-09-07 18:09:51',NULL,1,2),(4,'','Raul','Alfonzo',3,NULL,NULL,NULL,NULL,'r','r',NULL,'2016-09-07 18:09:51',NULL,1,2),(5,'','g','g',4,NULL,NULL,NULL,NULL,'g','g',NULL,'2016-09-07 18:09:51',NULL,1,2),(6,NULL,'r','r',5,NULL,NULL,NULL,NULL,'r','r',NULL,'2016-09-07 18:09:51',NULL,1,2),(7,NULL,'q','q',6,NULL,NULL,NULL,NULL,'q','q',NULL,'2016-09-07 18:09:51',NULL,1,2),(8,NULL,'t','t',7,NULL,NULL,NULL,NULL,'t','t',NULL,'2016-09-07 18:09:51',NULL,1,2),(9,NULL,'y','y',8,NULL,NULL,NULL,NULL,'y','y',NULL,'2016-09-07 18:09:51',NULL,1,2),(10,NULL,'v','v',9,NULL,NULL,NULL,NULL,'v','v',NULL,'2016-09-07 18:09:51',NULL,1,2),(11,NULL,'x','x',10,NULL,NULL,NULL,NULL,'x','x',NULL,'2016-09-07 18:09:51',NULL,1,2),(12,NULL,'z','z',11,NULL,NULL,NULL,NULL,'z','z',NULL,'2016-09-07 18:09:51',NULL,1,2),(13,NULL,'o','o',12,NULL,NULL,NULL,NULL,'o','o',NULL,'2016-09-07 18:09:51',NULL,1,2),(14,NULL,'p','p',13,NULL,NULL,NULL,NULL,'p','p',NULL,'2016-09-07 18:09:51',NULL,1,2),(15,NULL,'b','b',14,NULL,NULL,NULL,NULL,'b','b',NULL,'2016-09-07 18:09:51',NULL,1,2),(16,NULL,'n','n',15,NULL,NULL,NULL,NULL,'n','n',NULL,'2016-09-07 18:09:51',NULL,1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
