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
  CONSTRAINT `kodowanie_ibfk_2` FOREIGN KEY (`id_zajec`) REFERENCES `zajecia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kodowanie`
--

LOCK TABLES `kodowanie` WRITE;
/*!40000 ALTER TABLE `kodowanie` DISABLE KEYS */;
/*!40000 ALTER TABLE `kodowanie` ENABLE KEYS */;
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
  CONSTRAINT `lista_obecnosci_ibfk_1` FOREIGN KEY (`id_zajec`) REFERENCES `zajecia` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lista_obecnosci`
--

LOCK TABLES `lista_obecnosci` WRITE;
/*!40000 ALTER TABLE `lista_obecnosci` DISABLE KEYS */;
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
  `student_id` int DEFAULT NULL,
  `czy_obecny` tinyint(1) NOT NULL,
  `Aktywnosc` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `jindex` (`student_id`),
  CONSTRAINT `obecnosc_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`index_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `obecnosc`
--

LOCK TABLES `obecnosc` WRITE;
/*!40000 ALTER TABLE `obecnosc` DISABLE KEYS */;
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
  PRIMARY KEY (`index_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `zajecia`
--

DROP TABLE IF EXISTS `zajecia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `zajecia` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nazwa` varchar(255) NOT NULL,
  `semestr` int NOT NULL,
  `Rok` int NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `zajecia`
--

LOCK TABLES `zajecia` WRITE;
/*!40000 ALTER TABLE `zajecia` DISABLE KEYS */;
/*!40000 ALTER TABLE `zajecia` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-02-14  1:49:23
