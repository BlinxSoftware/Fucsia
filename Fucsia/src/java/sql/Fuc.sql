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
  `idMagnifyingGlass` bigint(255) unsigned default NULL,
  PRIMARY KEY  (`id`),
  KEY `FK_user` (`idMagnifyingGlass`),
  CONSTRAINT `FK_user` FOREIGN KEY (`idMagnifyingGlass`) REFERENCES `magnifyingglass` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user` */

insert into `user` values (2,'jk','fefef','efefefef',78,'kljkl',678,'kjljkl','jkljkl','jkljkl','jkljkl','jkl',NULL),(3,'fefe','fgrgr','thyjyju',768,'kjhhjk',7089789,'jkljkljk','kljkl','jkljkl','jkl','jkljkl',NULL),(4,'sdf','sdf','sdf',0,'sdf',0,'sdf','fdgdf','gdfg','dfg','dfg',NULL),(5,NULL,'2r432','sdfsdf',0,NULL,435,NULL,'dfgdfg',' sadasd','asdasd',NULL,NULL),(6,NULL,'fede','fede',0,NULL,1,NULL,'fefef','fede','fede',NULL,NULL);

SET FOREIGN_KEY_CHECKS=1;
