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
-- Table structure for table `rout`
--

DROP TABLE IF EXISTS `rout`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rout` (
  `idRout` int(11) NOT NULL AUTO_INCREMENT,
  `startPoint` varchar(45) NOT NULL,
  `finalPoint` varchar(45) NOT NULL,
  `cost` int(11) NOT NULL,
  `hoursOfFlight` int(11) NOT NULL,
  `minutesOfFlight` int(11) DEFAULT NULL,
  PRIMARY KEY (`idRout`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rout`
--

LOCK TABLES `rout` WRITE;
/*!40000 ALTER TABLE `rout` DISABLE KEYS */;
INSERT INTO `rout` VALUES (21,'Беларусь(аэропорт Минск Национальный)','Болгария (аэропорт Бургас)',300000,1,45),(23,'Беларусь (аэропорт Минск-1)','Боливия (аэропорт Ла-Пас Эль Альто)',700000,8,14),(24,'Россия (аэропорт Абакан)','Беларусь (аэропорт Минск Национальный)',500000,3,16),(25,'Россия (аэропорт Абакан)','Беларусь (аэропорт Минск Национальный)',500000,3,16),(26,'Беларусь (аэропорт Минск Национальный)','Россия (аэропорт Екатеринбург Кольцово)',500000,4,14),(27,'Россия (аэропорт Ижевск)','Беларусь (аэропорт Минск Национальный)',600000,3,50),(28,'Беларусь (аэропорт Минск Национальный)','США (аэропорт Аниак)',670000,6,20),(29,'Беларусь (аэропорт Минск Национальный)','Великобритания (аэропорт Белфаст)',890000,4,18),(30,'Беларусь (аэропорт Минск-1)','Великобритания (аэропорт Минск Бирмингем)',560000,5,13),(31,'Беларусь (аэропорт Брест)','Великобритания (аэропорт Гернси)',400000,7,16),(32,'Италия (аэропорт Милан)','Великобритания (аэропорт Кембридж)',60000,8,16),(33,'Беларусь (аэропорт Минск-1)','Италия (аэропорт Палермо)',700000,6,16),(34,'Италия (аэропорт Палермо)','Беларусь (аэропорт Минск Национальный)',500000,3,16),(35,'Италия (аэропорт Неаполь)','Беларусь (аэропорт Минск Национальный)',500000,7,30),(36,'Россия (аэропорт Абакан)','Италия (аэропорт Неаполь)',500000,5,16),(37,'Беларусь (аэропорт Минск Национальный)','Беларусь (аэропорт Минск Национальный)',500000,9,15),(38,'Беларусь (аэропорт Минск Национальный)','Испания (аэропорт Барселона)',680000,7,16),(39,'Россия (аэропорт Казань)','Испания (аэропорт Бильбао)',564000,9,16),(40,'Италия (аэропорт Бильбао)','Россия (аэропорт Кемерево)',890000,8,16),(41,'Великобритания (аэропорт Ландс Энд)','Беларусь (аэропорт Минск Национальный)',550000,8,46),(42,'США (аэропорт Бойсе)','Беларусь (аэропорт Минск Национальный)',770000,8,26),(43,'Беларусь (аэропорт Минск-1)','США (аэропорт Бойсе)',500000,7,40);
/*!40000 ALTER TABLE `rout` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-30 22:41:05
