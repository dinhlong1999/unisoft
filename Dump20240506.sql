-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: spring_mvc_unisoft
-- ------------------------------------------------------
-- Server version	8.3.0

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
-- Table structure for table `account`
--

DROP TABLE IF EXISTS `account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `account` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `password` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `roleId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4vb66o896tay3yy52oqxr9w0` (`roleId`),
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,_binary '\0','$2a$10$IRF1o/9wBlkhs1ev5NzKlu0EwpRWQ2ITfx/3MXJ7DLe/DuLWfOMqC','dinhlong1110',2,1),(2,_binary '\0','$2a$12$TZrGVAseq8VAoJPa4yUm4./5jaNVmYJK/8yfxme8gQSDR8vApVhq.','nhavan0305',1,2),(3,_binary '\0','$2a$12$TZrGVAseq8VAoJPa4yUm4./5jaNVmYJK/8yfxme8gQSDR8vApVhq.','honghanh2710',1,2),(4,_binary '\0','$2a$12$TZrGVAseq8VAoJPa4yUm4./5jaNVmYJK/8yfxme8gQSDR8vApVhq.','dinhlan0511',1,1),(5,_binary '\0','$2a$10$DFPfaZwHv0o0e0YAdwjg0ODcmbP1ml0aWLM7.JxZ2uKzX.kiF5wqe','hongduyen1992',5,2),(6,_binary '\0','$2a$10$Yq1DgclUYt/LzpXn91ebe.nqDRoO8KYbLeo3i2uavkaiwcmWtRn9S','xuanthanh1107',2,2),(13,_binary '\0','$2a$12$LfS2IK9UDLnDQrQ4frmSO.UfdOHmJq3ycLHM1Ms4N5AWnRjtSAw6m','hoangphong2010',1,2),(14,_binary '\0','$2a$10$Yg1v5.pxOx5bVOMcKNhcAe2pC0FNiMNyBwkd6ItCJI7FM0pzGpWxW','thanhduy2405',6,2),(16,_binary '\0','$2a$10$l6Qo/eso6/7DtNJOXOLc.OK3ZVkHZ20IKK24Ec4E4TTAyUgTt5rrm','AnKy123456',3,2),(17,_binary '\0','$2a$10$DhhX/KQhMhVr3IXiG9x27eSpIDeN6L4RRI1Rz.rTSURTHrikR84zy','quangminh2010',1,2),(18,_binary '\0','$2a$10$l2EzfctWvVefvYUCfXa9Mufytez05nqXy/9aO16yCq5BPsZjx2y62','quoctoan1011',2,2),(19,_binary '\0','$2a$10$83bgA9uCb5UU8hVN2o/5veqxX9OpMq8I6em3Zp2juWU82Cuk85ZVi','hungmap12345',2,2),(20,_binary '\0','$2a$10$Uw3tdGE2XTzYmyjgj4a71O8EPK47yFb1M6PE1P7TISE0Cwhs1npW6','toden1234',3,2),(21,_binary '\0','$2a$10$5Boaoon4pSHyhRLWBTyQtOdDQePK7TzxcZSJkWkz8s.WRmzyvSLVC','quangthanh2020',2,2),(22,_binary '\0','$2a$10$vWIeFJF8tkF9B4gh4MLI3eujjutnOuDTn8L7RE3jN/aMqjpyWseQ.','nhuthuy123',1,2),(23,_binary '\0','$2a$10$gpErPJpxsdE7./0K7rK7fe8DGNYlUFkSQLB8uwC7L3/25ZnIAgES6','giapphuong0305',1,2);
/*!40000 ALTER TABLE `account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `customer` (
  `id` int NOT NULL AUTO_INCREMENT,
  `address` varchar(255) NOT NULL,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `employeeId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiv4yye896nrvevqc0vpmmskmn` (`employeeId`),
  CONSTRAINT `FKiv4yye896nrvevqc0vpmmskmn` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (5,'Brazill',_binary '\0','Ronaldo Delima','0905555999',3,4),(6,'Bồ Đào Nha',_binary '\0','Cristiano Ronaldo','0905666777',5,5),(7,'Spain',_binary '\0','Torres','0905333222',3,6),(8,'France',_binary '\0','MBapee','0905444777',2,7),(9,'Sài Gòn',_binary '\0','Messi','0906555999',2,14),(10,'France',_binary '\0','Zidane','0912369789',4,4),(11,'England',_binary '\0','Belingham','0936363636',1,14),(12,'Đông Anh, Hà Nội',_binary '\0','Quang Hải','0124567980',1,14),(13,'Brazil',_binary '\0','Thiago Silva','0919191919',1,4),(14,'Belgium',_binary '\0','E.Hazard','0987878987',4,14),(15,'Portugal',_binary '\0','Luis Figo','0905555990',1,14);
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) NOT NULL,
  `phoneNumber` varchar(255) NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `accountId` int NOT NULL,
  `isOnline` bit(1) NOT NULL DEFAULT b'0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_lsnx7na4u8ohrhoeag7un4wh3` (`accountId`),
  CONSTRAINT `FKcfg6ajo8oske94exynxpf7tf9` FOREIGN KEY (`accountId`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (4,_binary '\0','Tsai Nhã Văn','0783685099',6,2,_binary '\0'),(5,_binary '\0','Hồng Hạnh','0905346958',1,3,_binary '\0'),(6,_binary '\0','Hồng Duyên','0935545709',5,5,_binary '\0'),(7,_binary '\0','Trịnh Xuân Thành','0905545709',2,6,_binary '\0'),(10,_binary '\0','Trịnh Hoàng Phong','0905666888',1,13,_binary '\0'),(11,_binary '\0','Trịnh Thanh Duy','0905686868',5,14,_binary '\0'),(13,_binary '','An kỳ','0905346959',3,16,_binary '\0'),(14,_binary '\0','Trương Nguyễn Đình Long','0932533481',2,1,_binary '\0'),(15,_binary '\0','Trương Đình lân','0905273809',1,4,_binary '\0'),(16,_binary '','Nguyễn Quang Minh','0987654321',1,17,_binary '\0'),(17,_binary '\0','Mai Quốc Toàn','0123456987',2,18,_binary '\0'),(18,_binary '\0','Đỗ Viết Hưng','0905969969',2,19,_binary '\0'),(19,_binary '\0','Trần Phước Gia Huy','0969787879',3,20,_binary '\0'),(20,_binary '','Nguyễn Quang Thanh','0906636363',2,21,_binary '\0'),(21,_binary '\0','Huỳnh Thị Như Thủy','0905535471',1,22,_binary '\0'),(22,_binary '\0','Thái Gia Phương','0906787899',1,23,_binary '\0');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` int NOT NULL AUTO_INCREMENT,
  `dateAllocation` datetime(6) DEFAULT NULL,
  `dateStart` datetime(6) NOT NULL,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `price` double NOT NULL,
  `quantityBook` int NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `customerId` int DEFAULT NULL,
  `employeeId` int DEFAULT NULL,
  `productId` int DEFAULT NULL,
  `statusId` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK624gtjin3po807j3vix093tlf` (`customerId`),
  KEY `FKnoettwqr56yaai4i8nwxg4huo` (`statusId`),
  KEY `FKog5v9ga2g2ukytypn4ocip6b4` (`employeeId`),
  KEY `FK787ibr3guwp6xobrpbofnv7le` (`productId`),
  CONSTRAINT `FK624gtjin3po807j3vix093tlf` FOREIGN KEY (`customerId`) REFERENCES `customer` (`id`),
  CONSTRAINT `FK787ibr3guwp6xobrpbofnv7le` FOREIGN KEY (`productId`) REFERENCES `product` (`id`),
  CONSTRAINT `FKnoettwqr56yaai4i8nwxg4huo` FOREIGN KEY (`statusId`) REFERENCES `status` (`id`),
  CONSTRAINT `FKog5v9ga2g2ukytypn4ocip6b4` FOREIGN KEY (`employeeId`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (15,'2024-04-15 00:00:00.000000','2024-04-01 00:00:00.000000',_binary '\0',8500000,1,1,5,4,1,2),(16,'2024-04-24 14:01:45.000000','2024-04-10 00:00:00.000000',_binary '\0',9800000,1,3,6,5,12,2),(17,'2024-04-10 00:00:00.000000','2024-04-01 00:00:00.000000',_binary '\0',7500000,1,1,7,6,3,2),(18,'2024-04-24 13:15:47.000000','2024-04-10 00:00:00.000000',_binary '\0',7500000,2,2,8,7,4,2),(19,'2024-05-06 09:43:34.000000','2024-04-09 09:14:36.596967',_binary '\0',9800000,2,5,10,14,12,2),(20,'2024-04-24 14:01:45.000000','2024-04-08 09:18:44.485968',_binary '\0',9800000,2,5,10,14,12,2),(21,NULL,'2024-04-23 09:25:24.351347',_binary '\0',8500000,2,2,9,14,9,1),(22,'2024-05-06 08:42:58.000000','2024-04-23 09:38:59.430090',_binary '\0',6500000,1,5,6,14,18,2),(23,'2024-04-25 09:09:53.000000','2024-04-23 09:48:09.092118',_binary '\0',5200000,2,2,7,14,5,2),(25,'2024-04-25 09:09:41.000000','2024-04-23 10:56:15.328416',_binary '\0',9000000,1,4,9,4,4,2),(26,'2024-04-25 09:09:41.000000','2024-04-23 11:49:09.105137',_binary '\0',7500000,1,4,10,14,4,2),(27,'2024-04-23 16:27:06.000000','2024-04-23 13:16:16.547378',_binary '\0',9000000,1,4,7,5,1,2),(28,'2024-04-23 16:27:06.000000','2024-04-23 13:29:48.794802',_binary '\0',9800000,2,1,6,14,1,2),(29,'2024-04-24 14:06:05.000000','2024-04-24 14:04:11.891458',_binary '\0',3500000,2,1,7,14,15,2),(30,'2024-04-24 14:07:18.000000','2024-04-24 14:05:21.380319',_binary '\0',3500000,2,1,9,14,15,2),(31,'2024-04-25 08:25:04.000000','2024-04-25 08:23:51.293011',_binary '\0',8500000,1,1,10,4,1,2),(32,NULL,'2024-04-26 13:55:30.408325',_binary '\0',5990000,5,2,10,14,19,1),(33,NULL,'2024-05-02 09:47:23.417191',_binary '\0',5990000,3,7,12,13,19,1),(34,'2024-05-06 09:35:29.000000','2024-05-02 15:48:27.611111',_binary '\0',5990000,3,3,12,4,19,2),(35,'2024-05-02 15:52:41.000000','2024-05-02 15:49:51.979793',_binary '\0',7500000,1,1,11,4,16,2),(36,'2024-05-06 08:39:21.000000','2024-05-03 16:25:13.143168',_binary '\0',8500000,1,1,12,14,2,2),(37,NULL,'2024-05-06 14:52:51.976744',_binary '\0',5500000,2,3,10,14,22,1),(38,NULL,'2024-05-06 15:08:46.508180',_binary '\0',5500000,2,4,8,14,20,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `codeProduct` varchar(255) NOT NULL,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `inventory` int NOT NULL DEFAULT '0',
  `nameProduct` varchar(255) NOT NULL,
  `priceBuy` double NOT NULL,
  `priceSell` double NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'PR-0001',_binary '\0',0,'Dong ho Nam Orient Bambino Small Second IIIII',8000000,8500000,2),(2,'PR-0002',_binary '\0',2,'Dong ho Nam Orient BAMBINO CLASSIC BLACK',7600000,8500000,1),(3,'PR-0003',_binary '\0',0,'Dong ho Nam Orient Bambino Version 2',6500000,7500000,1),(4,'PR-0004',_binary '\0',0,'Dong ho Nam Orient FAC00007W0',7000000,7500000,1),(5,'PR-0005',_binary '\0',0,'Dong ho Nam Orient RA-AP0003S11B',6500000,9000000,3),(6,'PR-0006',_binary '\0',0,'Dong ho Nam Orient RA-AG0016B10B',5000000,6000000,1),(7,'PR-0007',_binary '\0',0,'Dong ho Nam Orient CONTEMPORARY',4500000,6000000,2),(8,'PR-0008',_binary '',0,'Dong ho Nam Orient Ray Raven IIII',6500000,7600000,6),(9,'PR-0009',_binary '\0',0,'Dong ho Nam Orient Mako Pepsi III',7700000,8500000,2),(12,'PR-0011',_binary '\0',0,'APPLE WATCH SERIES 8 41MM',9600000,9800000,5),(13,'PR-0012',_binary '\0',0,'IPHONE 15 PRO MAX 218GB',24000000,26000000,6),(14,'PR-0100',_binary '\0',0,'AirTag Gen 17',4500000,5200000,18),(15,'PR-0200',_binary '\0',0,'AIRTAG SERIES 9',3000000,3500000,13),(16,'PR-0020',_binary '\0',3,'APPLE WATCH SE',7000000,7500000,1),(17,'PR-0022',_binary '\0',0,'APPLE WATCH SE11',7000000,7500000,1),(18,'PR-0025',_binary '\0',0,'SONY WH1000-XM5',6200000,6500000,2),(19,'PR-0030',_binary '\0',2,'Màn hình LG 27UP600',5000000,5990000,2),(20,'PR-0040',_binary '\0',1,'Màn hình Dell P2422H',5000000,5500000,2),(21,'PR-0021',_binary '\0',1,'SAMSUNG GALAXY S21+',24000000,26000000,2),(22,'PR-0041',_binary '\0',1,'Màn hình Dell P2722H',5000000,5500000,2),(23,'PR-0042',_binary '\0',1,'Màn hình LG 27UP850',7700000,8500000,1);
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES (1,_binary '\0','ROLE_ADMIN'),(2,_binary '\0','ROLE_USER');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `status`
--

DROP TABLE IF EXISTS `status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `status` (
  `id` int NOT NULL AUTO_INCREMENT,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `status`
--

LOCK TABLES `status` WRITE;
/*!40000 ALTER TABLE `status` DISABLE KEYS */;
INSERT INTO `status` VALUES (1,_binary '\0','Đã đặt hàng'),(2,_binary '\0','Đã phân bổ');
/*!40000 ALTER TABLE `status` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-06 17:15:27
