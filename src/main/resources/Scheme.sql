CREATE DATABASE  IF NOT EXISTS `uczelnia` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `uczelnia`;
-- MySQL dump 10.13  Distrib 8.0.40, for Win64 (x86_64)
--
-- Host: localhost    Database: uczelnia
-- ------------------------------------------------------
-- Server version	8.0.40

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `kodowanie`
--

DROP TABLE IF EXISTS `kodowanie`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `kodowanie` (
  `id` int NOT NULL AUTO_INCREMENT,
  `index_number` int DEFAULT NULL,
  `id_zajec` int DEFAULT NULL,
  `grupa` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `index_number` (`index_number`),
  KEY `id_zajec` (`id_zajec`),
  CONSTRAINT `kodowanie_ibfk_1` FOREIGN KEY (`index_number`) REFERENCES `student` (`index_number`),
  CONSTRAINT `kodowanie_ibfk_2` FOREIGN KEY (`id_zajec`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kodowanie`
--

LOCK TABLES `kodowanie` WRITE;
/*!40000 ALTER TABLE `kodowanie` DISABLE KEYS */;
INSERT INTO `kodowanie` VALUES (1,1002,2,3),(3,1003,2,2),(4,1004,2,2),(5,1005,3,1),(6,1006,3,2),(7,1007,4,1),(8,1008,4,2),(9,1009,5,1),(10,1010,5,2),(11,162553,3,2),(12,62687,3,2);
/*!40000 ALTER TABLE `kodowanie` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lesson`
--

DROP TABLE IF EXISTS `lesson`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lesson` (
  `id` int NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `semester` int NOT NULL,
  `year` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lesson`
--

LOCK TABLES `lesson` WRITE;
/*!40000 ALTER TABLE `lesson` DISABLE KEYS */;
INSERT INTO `lesson` VALUES (2,'Fizyka',1,2024),(3,'Informatyka',2,2024),(4,'Biologia',2,2024),(5,'Chemia',3,2024),(6,'Historia',3,2023),(7,'Geografia',4,2023),(8,'Język Angielski',4,2023),(9,'Fotografia',5,2024),(11,'Programowanie',5,2025),(12,'siwydym',6,2025),(13,'ja ovse ',7,2025),(14,'ja ovse ',7,2025);
/*!40000 ALTER TABLE `lesson` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lista_obecnosci`
--

DROP TABLE IF EXISTS `lista_obecnosci`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `lista_obecnosci` (
  `id` int NOT NULL AUTO_INCREMENT,
  `data` date NOT NULL,
  `id_zajec` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_zajec` (`id_zajec`),
  CONSTRAINT `lista_obecnosci_ibfk_1` FOREIGN KEY (`id_zajec`) REFERENCES `lesson` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_obecnosci`
--

LOCK TABLES `lista_obecnosci` WRITE;
/*!40000 ALTER TABLE `lista_obecnosci` DISABLE KEYS */;
INSERT INTO `lista_obecnosci` VALUES (3,'2024-01-12',3),(4,'2024-02-13',4),(5,'2024-02-14',5),(23,'2025-02-19',5),(24,'2025-02-19',5),(25,'2025-02-19',5),(26,'2024-02-03',2),(27,'2024-02-03',2),(28,'2025-03-03',6),(29,'2025-03-03',6),(30,'2025-03-03',6),(31,'2025-03-03',6),(32,'2025-05-04',4),(33,'2025-05-04',4);
/*!40000 ALTER TABLE `lista_obecnosci` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `obecnosc`
--

DROP TABLE IF EXISTS `obecnosc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `obecnosc` (
  `id` int NOT NULL AUTO_INCREMENT,
  `student_index_number` int DEFAULT NULL,
  `czy_obecny` varchar(255) NOT NULL,
  `Aktywnosc` int DEFAULT NULL,
  `lista_obecnosci_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jindex` (`student_index_number`),
  KEY `obecnosc_ibfk_2` (`lista_obecnosci_id`),
  CONSTRAINT `obecnosc_ibfk_1` FOREIGN KEY (`student_index_number`) REFERENCES `student` (`index_number`),
  CONSTRAINT `obecnosc_ibfk_2` FOREIGN KEY (`lista_obecnosci_id`) REFERENCES `lista_obecnosci` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obecnosc`
--

LOCK TABLES `obecnosc` WRITE;
/*!40000 ALTER TABLE `obecnosc` DISABLE KEYS */;
INSERT INTO `obecnosc` VALUES (5,1005,'NOT_PRESENT',1,3),(6,1006,'PRESENT',3,3),(8,1008,'NOT_PRESENT',2,4),(9,1009,'EXEMPT',4,5),(10,1010,'NOT_PRESENT',3,5),(18,1009,'EXEMPT',3,4),(20,162553,'EXEMPT',2,23),(21,1009,'PRESENT',0,23),(22,1008,'EXEMPT',2,24),(23,62687,'NOT_PRESENT',0,24),(24,1008,'EXEMPT',4,25),(25,62687,'PRESENT',0,25),(26,1001,'EXEMPT',2,26),(27,1002,'PRESENT',0,26),(28,1001,'EXEMPT',5,27),(29,1002,'NOT_PRESENT',0,27),(30,1004,'NOT_PRESENT',4,28),(31,1005,'PRESENT',0,28),(32,1009,'NOT_PRESENT',0,33),(33,1008,'EXEMPT',3,33),(36,1009,'NOT_PRESENT',0,33);
/*!40000 ALTER TABLE `obecnosc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `student` (
  `index_number` int NOT NULL,
  `nazwisko` varchar(255) NOT NULL,
  `imie` varchar(255) NOT NULL,
  PRIMARY KEY (`index_number`),
  UNIQUE KEY `index_number_UNIQUE` (`index_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1001,'Kowalski','Jan'),(1002,'Nowak','Anna'),(1003,'Wiśniewski','Piotr'),(1004,'Dąbrowski','Ewa'),(1005,'Lewandowski','Kamil'),(1006,'Wójcik','Monika'),(1007,'Kamiński','Paweł'),(1008,'Zielińska','Karolina'),(1009,'Szymański','Tomasz'),(1010,'Woźniak','Marta'),(3562,'Lewandowski','Robert'),(62687,'Gryczka','Łukasz'),(162553,'Kwiatkowski','Mateusz');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-26 11:09:29
