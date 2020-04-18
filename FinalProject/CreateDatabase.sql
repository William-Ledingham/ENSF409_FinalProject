-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.19

-- Table structure for table `courses`
--

CREATE DATABASE records;
USE records;

DROP TABLE IF EXISTS `courses`;
CREATE TABLE `courses` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `courseNum` int NOT NULL,
  `sections` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `courses`
--

LOCK TABLES `courses` WRITE;
INSERT INTO `courses` VALUES (1,'ENCM',511,'1');
INSERT INTO `courses` VALUES (2,'ENSF',409,'1,2');
INSERT INTO `courses` VALUES (3,'CPSC',319,'1,2');
INSERT INTO `courses` VALUES (4,'CHEM',209,'1,2');
INSERT INTO `courses` VALUES (5,'ENGG',201,'1,2,3,4');
UNLOCK TABLES;

--
-- Table structure for table `registrations`
--

DROP TABLE IF EXISTS `registrations`;
CREATE TABLE `registrations` (
  `id` int NOT NULL,
  `studentID` int NOT NULL,
  `courseID` int NOT NULL,
  `section` int NOT NULL,
  `grade` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `registrations`
--

LOCK TABLES `registrations` WRITE;
INSERT INTO `registrations` VALUES (1,1,1,1,'A');
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
CREATE TABLE `students` (
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
INSERT INTO `students` VALUES (1,'Will'), (2,'Parker'), (3,'Michaela');
UNLOCK TABLES;

-- Dump completed on 2020-04-17 20:59:20
