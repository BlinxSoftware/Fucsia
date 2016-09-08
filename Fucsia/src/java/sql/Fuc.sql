/*
SQLyog - Free MySQL GUI v4.1
Host - 5.0.15-nt : Database - fucsia
*********************************************************************
Server version : 5.0.15-nt
*/

SET FOREIGN_KEY_CHECKS=0;

create database if not exists `fucsia`;

USE `fucsia`;

/*Table structure for table `magnifyingglass` */

drop table if exists `magnifyingglass`;

CREATE TABLE `magnifyingglass` (
  `id` bigint(255) unsigned NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `quantity` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `magnifyingglass` */

insert into `magnifyingglass` values (1,'fefe',1);

/*Table structure for table `profile` */

drop table if exists `profile`;

CREATE TABLE `profile` (
  `id` bigint(20) NOT NULL auto_increment,
  `name` varchar(50) NOT NULL,
  `dateC` datetime NOT NULL,
  `dateD` datetime default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `profile` */

insert into `profile` values (1,'Admin','2016-09-07 11:59:56',NULL),(2,'User','2016-09-07 12:00:24',NULL);

/*Table structure for table `user` */

drop table if exists `user`;

CREATE TABLE `user` (
  `id` bigint(255) unsigned NOT NULL auto_increment,
  `image` varchar(255) default NULL,
  `name` varchar(255) NOT NULL,
  `lastName` varchar(255) NOT NULL,
  `level` int(255) unsigned default NULL,
  `presentation` varchar(255) default NULL,
  `telephone` int(255) unsigned default NULL,
  `type` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `userName` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `state` varchar(255) default NULL,
  `dateC` datetime NOT NULL,
  `dateD` datetime default NULL,
  `idMagnifyingGlass` bigint(255) unsigned default NULL,
  `idProfile` bigint(255) NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_user` (`idMagnifyingGlass`),
  KEY `idProfile` (`idProfile`),
  CONSTRAINT `FK_user` FOREIGN KEY (`idMagnifyingGlass`) REFERENCES `magnifyingglass` (`id`),
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`idProfile`) REFERENCES `profile` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert into `user` values (1,NULL,'fede','fede',NULL,NULL,123123,NULL,'fede','fede','fede',NULL,'2016-09-07 12:05:17','2016-09-07 13:02:35',NULL,1),(2,'fa.jpg','f','f',3,'ede',232,'fef','f','f','f','efef','2016-09-07 12:05:17',NULL,NULL,2),(3,'fa.jpg','l','l',6,'j',2323,'j','l','l','l','j','2016-09-07 18:09:51',NULL,NULL,2);

SET FOREIGN_KEY_CHECKS=1;
