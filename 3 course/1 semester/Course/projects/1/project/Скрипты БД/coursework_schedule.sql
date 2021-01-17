-- MySQL dump 10.13  Distrib 5.7.9, for Win64 (x86_64)
--
-- Host: localhost    Database: coursework
-- ------------------------------------------------------
-- Server version	5.7.9-log

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
-- Table structure for table `schedule`
--

DROP TABLE IF EXISTS `schedule`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule` (
  `idSchedule` int(11) NOT NULL AUTO_INCREMENT,
  `flight` varchar(45) NOT NULL,
  `typeClass` varchar(45) NOT NULL,
  `idPlane` int(11) NOT NULL,
  `idRout` int(11) NOT NULL,
  `idPriceIndex` int(11) NOT NULL,
  `idDate` int(11) NOT NULL,
  PRIMARY KEY (`idSchedule`),
  KEY `idDate_idx` (`idDate`),
  KEY `idPlane_idx` (`idPlane`),
  KEY `idIndexOfPrice_idx` (`idPriceIndex`),
  KEY `idRoute_idx` (`idRout`),
  CONSTRAINT `idDate` FOREIGN KEY (`idDate`) REFERENCES `date` (`idDate`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idIndexOfPrice` FOREIGN KEY (`idPriceIndex`) REFERENCES `indexofprice` (`idIndexOfPrice`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idPlane` FOREIGN KEY (`idPlane`) REFERENCES `plane` (`idPlane`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRoute` FOREIGN KEY (`idRout`) REFERENCES `rout` (`idRout`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule`
--

LOCK TABLES `schedule` WRITE;
/*!40000 ALTER TABLE `schedule` DISABLE KEYS */;
INSERT INTO `schedule` VALUES (14,'234ER','эконом',8,21,12,16),(16,'ERA43','эконом',5,23,12,18),(18,'EWD45','бизнес',5,23,1,16),(20,'HFY76','эконом',6,23,2,18),(21,'UJF76','эконом',7,24,3,18),(22,'KIU64','эконом',8,25,4,19),(23,'JFH63','бизнес',9,26,5,20),(24,'PLO98','эконом',10,27,6,21),(25,'GHY09','эконом',11,28,1,22),(26,'JYS43','эконом',12,29,1,23),(27,'PLK76','эконом',13,30,7,24),(28,'SEW43','эконом',14,31,4,25),(29,'MKJ89','эконом',15,32,3,26),(30,'NGT75','первый',14,33,1,27),(31,'PLI78','эконом',13,34,2,28),(32,'JCH87','бизнес',12,35,9,29),(33,'UJI76','эконом',11,36,10,30),(34,'YFD67','эконом',10,37,11,31),(35,'MJH78','бизнес',9,38,12,32),(36,'LKI98','первый',8,39,5,33),(37,'DEJ40','бизнес',7,40,4,20),(38,'OI976','эконом',6,41,4,21),(39,'BHC34','эконом',5,42,3,22),(41,'PLI78','эконом',10,34,2,28),(42,'JCH87','бизнес',12,35,9,29),(43,'UJI76','эконом',11,36,10,30),(44,'YFD67','эконом',10,37,11,31),(45,'MJH78','бизнес',9,38,12,32),(46,'LKI98','первый',8,39,5,33),(47,'DEJ40','бизнес',7,40,4,20),(48,'OI976','эконом',6,41,4,21),(49,'BHC34','эконом',5,42,3,22);
/*!40000 ALTER TABLE `schedule` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-30 22:41:04
