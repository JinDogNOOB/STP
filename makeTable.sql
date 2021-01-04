-- MySQL dump 10.13  Distrib 5.7.26, for Linux (x86_64)
--
-- Host: localhost    Database: db_board
-- ------------------------------------------------------
-- Server version	5.7.26-0ubuntu0.16.04.1

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
-- Table structure for table `BOARDXCSGO`
--

DROP TABLE IF EXISTS `BOARDXCSGO`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDXCSGO` (
  `BNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BOARDXFREE`
--

DROP TABLE IF EXISTS `BOARDXFREE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDXFREE` (
  `BNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BOARDXHOTS`
--

DROP TABLE IF EXISTS `BOARDXHOTS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDXHOTS` (
  `BNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BOARDXNOTICE`
--

DROP TABLE IF EXISTS `BOARDXNOTICE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDXNOTICE` (
  `BNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `BOARDXPUBG`
--

DROP TABLE IF EXISTS `BOARDXPUBG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `BOARDXPUBG` (
  `BNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `SUBJECT` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `VIEWCOUNT` int(11) DEFAULT NULL,
  `FLAG` int(11) DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`BNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `COMMENT`
--

DROP TABLE IF EXISTS `COMMENT`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `COMMENT` (
  `CNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `BNO` int(11) unsigned DEFAULT NULL,
  `BOARDNAME` varchar(100) DEFAULT NULL,
  `CONTENT` varchar(500) DEFAULT NULL,
  `WRITER` varchar(100) DEFAULT NULL,
  `UID` varchar(100) DEFAULT NULL,
  `REG_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`CNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `EMAILAUTHURL`
--

DROP TABLE IF EXISTS `EMAILAUTHURL`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `EMAILAUTHURL` (
  `ANO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `UID` varchar(100) DEFAULT NULL,
  `AUTHTYPE` varchar(100) DEFAULT NULL,
  `URL` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`ANO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `FILES`
--

DROP TABLE IF EXISTS `FILES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `FILES` (
  `FNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `BNO` int(11) DEFAULT NULL,
  `FILENAME` varchar(200) DEFAULT NULL,
  `FILEORINAME` varchar(300) DEFAULT NULL,
  `FILEURL` varchar(500) DEFAULT NULL,
  `BOARDNAME` char(100) DEFAULT NULL,
  PRIMARY KEY (`FNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `INVENTORY`
--

DROP TABLE IF EXISTS `INVENTORY`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `INVENTORY` (
  `INO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `UNO` int(11) DEFAULT NULL,
  `PRICE` int(11) DEFAULT NULL,
  `ITEMNAME` varchar(100) DEFAULT NULL,
  `IMGNAME` varchar(500) DEFAULT NULL,
  `SHOPID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`INO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SHOPXCLOTH`
--

DROP TABLE IF EXISTS `SHOPXCLOTH`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SHOPXCLOTH` (
  `SNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PRICE` int(11) unsigned NOT NULL,
  `ITEMNAME` varchar(100) DEFAULT NULL,
  `IMGNAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`SNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SHOPXSALE`
--

DROP TABLE IF EXISTS `SHOPXSALE`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SHOPXSALE` (
  `SNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PRICE` int(11) unsigned NOT NULL,
  `ITEMNAME` varchar(100) DEFAULT NULL,
  `IMGNAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`SNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `SHOPXSKIN`
--

DROP TABLE IF EXISTS `SHOPXSKIN`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SHOPXSKIN` (
  `SNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `PRICE` int(11) unsigned NOT NULL,
  `ITEMNAME` varchar(100) DEFAULT NULL,
  `IMGNAME` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`SNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `USERS`
--

DROP TABLE IF EXISTS `USERS`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `USERS` (
  `UNO` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `UID` varchar(100) DEFAULT NULL,
  `UPASSWORD` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(200) DEFAULT NULL,
  `UPDATE_LOG` datetime DEFAULT NULL,
  `UNAME` varchar(100) DEFAULT NULL,
  `AUTH` int(11) unsigned DEFAULT NULL,
  `MONEY` int(11) unsigned DEFAULT NULL,
  `THUMBNAILNAME` varchar(200) DEFAULT NULL,
  `JSESSION` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`UNO`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2019-05-23  0:24:26
