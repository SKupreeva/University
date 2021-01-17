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
-- Table structure for table `passengers`
--

DROP TABLE IF EXISTS `passengers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `passengers` (
  `idPassenger` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `secondName` varchar(45) NOT NULL,
  `patronymic` varchar(45) NOT NULL,
  `passportSeries` varchar(45) NOT NULL,
  `passportNumber` int(11) NOT NULL,
  PRIMARY KEY (`idPassenger`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passengers`
--

LOCK TABLES `passengers` WRITE;
/*!40000 ALTER TABLE `passengers` DISABLE KEYS */;
INSERT INTO `passengers` VALUES (6,'Анна','Петрова','Викторовна','КВ',1288364),(8,'Вадим','Птичкин','Викторович','КВ',12334232),(9,'Сергей','Сушко','Андреевич','КВ',1768945),(10,'Татьяна','Белова','Александровна','КВ',14578964),(11,'Анна','','Викторовна','КВ',1288364),(12,'Екатерина','Белова','Валерьевна','КВ',1645374),(13,'Сергей','Полено','Владимирович','КВ',18892384),(14,'Татьяна','Бегунова','Александровна','КВ',173665463),(15,'Марина','Сорокина','Эдуардовна','КВ',1850934),(16,'Вениамин','Бачков','Святославович','КВ',1850934),(17,'Александр','Белый','Петрович','КВ',1847563),(18,'Олег','Серов','Антонович','КВ',1354673),(19,'Антон','Качалов','Олегович','КВ',1849563),(20,'Мария','Антонова','Алексеевна','КВ',1047586),(21,'Анна','Король','Вадимовна','КВ',1465739),(22,'Валерия','Цезарь','Романовна','КВ',1475869),(23,'Олимпиада','Зондова','Викторовна','КВ',1274568),(24,'Виктория','Попова','Матвеевна','КВ',1856475),(25,'Елизавета','Вторая','Львовна','КВ',1056344),(26,'Екатерина','Белая','Эдуардовна','КВ',1265748),(27,'Эдуард','Асадов','Эдуардович','КВ',1058647),(28,'Святослав','Мягенький','Александрович','КВ',1094673),(29,'Екатерина','Бесстрашная','Всеслвовна','КВ',1084675);
/*!40000 ALTER TABLE `passengers` ENABLE KEYS */;
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
