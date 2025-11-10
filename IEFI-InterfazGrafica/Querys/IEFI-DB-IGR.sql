CREATE DATABASE  IF NOT EXISTS `juego_batallas` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `juego_batallas`;
-- MySQL dump 10.13  Distrib 8.0.44, for Win64 (x86_64)
--
-- Host: localhost    Database: juego_batallas
-- ------------------------------------------------------
-- Server version	8.0.44

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
-- Table structure for table `batallas`
--

DROP TABLE IF EXISTS `batallas`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `batallas` (
  `id_batalla` int NOT NULL AUTO_INCREMENT,
  `fecha` datetime DEFAULT CURRENT_TIMESTAMP,
  `heroe_id` int NOT NULL,
  `villano_id` int NOT NULL,
  `ganador_id` int DEFAULT NULL,
  `turnos` int DEFAULT '0',
  `mayor_ataque` int DEFAULT '0',
  `id_jugador_mayor_ataque` int DEFAULT NULL,
  PRIMARY KEY (`id_batalla`),
  KEY `heroe_id` (`heroe_id`),
  KEY `villano_id` (`villano_id`),
  KEY `ganador_id` (`ganador_id`),
  CONSTRAINT `batallas_ibfk_1` FOREIGN KEY (`heroe_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `batallas_ibfk_2` FOREIGN KEY (`villano_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `batallas_ibfk_3` FOREIGN KEY (`ganador_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `batallas`
--

LOCK TABLES `batallas` WRITE;
/*!40000 ALTER TABLE `batallas` DISABLE KEYS */;
INSERT INTO `batallas` VALUES (50,'2025-11-10 03:10:24',65,66,65,8,59,66),(51,'2025-11-10 03:12:54',65,66,NULL,1,0,NULL),(52,'2025-11-10 03:14:57',65,66,NULL,1,0,NULL);
/*!40000 ALTER TABLE `batallas` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `estado_partida`
--

DROP TABLE IF EXISTS `estado_partida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `estado_partida` (
  `id_estado` int NOT NULL AUTO_INCREMENT,
  `id_batalla` int DEFAULT NULL,
  `fecha_guardado` datetime DEFAULT CURRENT_TIMESTAMP,
  `heroe_id` int NOT NULL,
  `villano_id` int NOT NULL,
  `vida_heroe` int DEFAULT '0',
  `vida_villano` int DEFAULT '0',
  `bendicion_heroe` int DEFAULT '0',
  `ataque_heroe` int DEFAULT '0',
  `defensa_heroe` int DEFAULT '0',
  `bendicion_villano` int DEFAULT '0',
  `ataque_villano` int DEFAULT '0',
  `defensa_villano` int DEFAULT '0',
  `turno_actual` int DEFAULT '1',
  `finalizada` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id_estado`),
  KEY `heroe_id` (`heroe_id`),
  KEY `villano_id` (`villano_id`),
  KEY `fk_estado_batalla` (`id_batalla`),
  CONSTRAINT `estado_partida_ibfk_1` FOREIGN KEY (`heroe_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `estado_partida_ibfk_2` FOREIGN KEY (`villano_id`) REFERENCES `personajes` (`id_personaje`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_estado_batalla` FOREIGN KEY (`id_batalla`) REFERENCES `batallas` (`id_batalla`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=47 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `estado_partida`
--

LOCK TABLES `estado_partida` WRITE;
/*!40000 ALTER TABLE `estado_partida` DISABLE KEYS */;
INSERT INTO `estado_partida` VALUES (44,50,'2025-11-10 03:11:28',65,66,45,0,50,37,13,96,59,8,8,1),(45,51,'2025-11-10 03:12:54',65,66,45,0,50,0,0,96,0,0,1,0),(46,52,'2025-11-10 03:15:00',65,66,45,0,50,0,0,96,0,0,0,0);
/*!40000 ALTER TABLE `estado_partida` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `personajes`
--

DROP TABLE IF EXISTS `personajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `personajes` (
  `id_personaje` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `apodo` varchar(50) NOT NULL,
  `tipo` enum('Héroe','Villano') NOT NULL,
  `vida_final` int DEFAULT '0',
  `ataque` int DEFAULT '0',
  `defensa` int DEFAULT '0',
  `victorias` int DEFAULT '0',
  `supremos_usados` int DEFAULT '0',
  `armas_invocadas` int DEFAULT '0',
  PRIMARY KEY (`id_personaje`)
) ENGINE=InnoDB AUTO_INCREMENT=67 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `personajes`
--

LOCK TABLES `personajes` WRITE;
/*!40000 ALTER TABLE `personajes` DISABLE KEYS */;
INSERT INTO `personajes` VALUES (65,'batman','batman','Héroe',45,37,13,1,1,3),(66,'Joker','Joker','Villano',123,18,8,0,0,0);
/*!40000 ALTER TABLE `personajes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-11-10  3:16:28
