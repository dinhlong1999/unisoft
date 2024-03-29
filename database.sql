-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: localhost    Database: project1_intern
-- ------------------------------------------------------
-- Server version	8.0.34

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
  `role_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKd4vb66o896tay3yy52oqxr9w0` (`role_id`),
  CONSTRAINT `FKd4vb66o896tay3yy52oqxr9w0` FOREIGN KEY (`role_id`) REFERENCES `role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=46 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `account`
--

LOCK TABLES `account` WRITE;
/*!40000 ALTER TABLE `account` DISABLE KEYS */;
INSERT INTO `account` VALUES (1,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','dinhlong1110',1),(2,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','nhavan03052001',2),(3,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','dinhlan0511',2),(4,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','honghanh2710',2),(5,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','hongduyen3110',2),(6,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','xuanthanh0707',2),(16,_binary '\0','Minh123456','quangminh2010',2),(17,_binary '\0','Quanghai12345','quanghai123456',2),(35,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','toanmai11100',2),(42,_binary '\0','$2a$12$K7BI/1dFyeZ9Nk/vK6POJOadcSIlFJXzi5wKCAxLjmd0UhFj5444m','viethung1119',2),(45,_binary '\0','$2a$12$R.u5lNS.akZvetFOMLQPIuWHJVoAKLEqUuMWc.kOY6Cfo5uxPElh6','beckham1110',2);
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
  `phone_number` varchar(255) NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `employee_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKiv4yye896nrvevqc0vpmmskmn` (`employee_id`),
  CONSTRAINT `FKiv4yye896nrvevqc0vpmmskmn` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Portugal',_binary '\0','Ronaldo','0935545709',1,1),(2,'Brazil',_binary '\0','Neymar','01229417136',1,1),(3,'Brazil',_binary '\0','Kaka','0908789789',0,2),(4,'Brazil',_binary '\0','Zinadine Zidane','012012012012',2,2),(6,'Sài Gòn',_binary '\0','Đăng Tsai','0932544443',2,2),(7,'Spain',_binary '\0','De Gea','0908777888',2,2);
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
  `phone_number` varchar(255) NOT NULL,
  `account_id` int DEFAULT NULL,
  `version` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `FKcfg6ajo8oske94exynxpf7tf9` (`account_id`),
  CONSTRAINT `FKcfg6ajo8oske94exynxpf7tf9` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,_binary '\0','Tsai Nhã Văn','01283685099',2,18),(2,_binary '\0','Dinh Long','0932533481',1,0),(3,_binary '\0','Dinh Lan','0905273809',3,1),(4,_binary '\0','Hong Hanh','0905346958',4,1),(11,_binary '\0','Hong Duyen','0935545709',5,1),(12,_binary '\0','Xuan Thanh','0905545709',6,1),(13,_binary '\0','Quang Minh','0906567789',16,0),(14,_binary '\0','Quang Hải','0912345678',17,0),(18,_binary '\0','Mai Quốc Toà','0906777888',35,0),(22,_binary '\0','Đỗ Viết Hưng','0906765765',42,1),(25,_binary '\0','David Beckham','0905666777',45,1);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `order_detail`
--

DROP TABLE IF EXISTS `order_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `order_detail` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date_start` datetime(6) NOT NULL,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `quantity_book` int NOT NULL,
  `customer_id` int DEFAULT NULL,
  `employee_id` int DEFAULT NULL,
  `product_id` int DEFAULT NULL,
  `status_id` int DEFAULT NULL,
  `date_allocation` datetime(6) DEFAULT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKkefm6js2d9us8ulug8jsy1jnd` (`customer_id`),
  KEY `FKs4xk29erxqvp6ro5wk6xjospf` (`employee_id`),
  KEY `FKb8bg2bkty0oksa3wiq5mp5qnc` (`product_id`),
  KEY `FKqjhywygh4p672llnhmer87cqj` (`status_id`),
  CONSTRAINT `FKb8bg2bkty0oksa3wiq5mp5qnc` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  CONSTRAINT `FKkefm6js2d9us8ulug8jsy1jnd` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `FKqjhywygh4p672llnhmer87cqj` FOREIGN KEY (`status_id`) REFERENCES `status` (`id`),
  CONSTRAINT `FKs4xk29erxqvp6ro5wk6xjospf` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `order_detail`
--

LOCK TABLES `order_detail` WRITE;
/*!40000 ALTER TABLE `order_detail` DISABLE KEYS */;
INSERT INTO `order_detail` VALUES (1,'2023-02-24 00:00:00.000000',_binary '\0',2,4,1,1,1,NULL,1800000),(2,'2024-01-01 00:00:00.000000',_binary '\0',1,1,1,2,2,'2024-02-23 09:02:24.000000',1500000),(14,'2024-02-19 13:24:26.990307',_binary '\0',1,3,1,1,2,'2024-02-23 09:25:23.000000',1600000),(16,'2024-02-19 13:24:26.990307',_binary '\0',1,4,3,2,2,'2024-02-19 15:49:42.000000',1500000),(17,'2024-02-19 16:15:45.705447',_binary '\0',1,4,1,1,2,'2024-02-19 16:18:37.000000',1600000),(18,'2024-02-20 09:06:06.153408',_binary '\0',1,3,1,5,2,'2024-02-20 09:11:20.000000',1600000),(19,'2024-02-20 09:26:51.807548',_binary '\0',1,3,1,1,2,'2024-02-20 09:27:07.000000',1600000),(22,'2024-03-11 00:00:00.000000',_binary '\0',1,1,2,1,1,NULL,1300000),(23,'2024-03-11 00:00:00.000000',_binary '\0',1,1,2,2,1,NULL,1500000);
/*!40000 ALTER TABLE `order_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code_product` varchar(255) NOT NULL,
  `flag` bit(1) NOT NULL DEFAULT b'0',
  `name_product` varchar(255) NOT NULL,
  `price_buy` double NOT NULL,
  `price_sell` double NOT NULL,
  `version` int NOT NULL DEFAULT '1',
  `inventory` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (1,'PR-0001',_binary '\0','NIKE JORDAN',1300000,1600000,3,0),(2,'PR-0002',_binary '\0','NIKE STAN SMITH',1100000,1300000,2,2),(3,'PR-0003',_binary '\0','NIKE AIR ',2100000,2400000,2,0),(4,'PR-0004',_binary '\0','ADIDAS ULTRABOOK',3100000,3500000,2,0),(5,'PR-0005',_binary '\0','ADIDAS EQUIPMENT',2500000,2700000,2,0),(6,'PR-0006',_binary '\0','DIOR LOOP',2300000,2600000,2,0),(7,'PR-0007',_binary '\0','GUCCI ACE Blade',2400000,2700000,2,0),(8,'PR-0008',_binary '\0','AIR MAX',400000,600000,2,0),(9,'PR-0009',_binary '\0','MLB NEW',400000,600000,2,0),(10,'PR-0010',_binary '\0','ADIDAS STAN SMITH',1500000,1800000,0,0),(11,'PR-0011',_binary '\0','MLB USED',400000,600000,2,0),(12,'PR-0012',_binary '\0','NIKE AIR FORCE 1',1500000,1900000,0,0),(13,'PR-0013',_binary '\0','SANDAL SHONDOO',500000,700000,3,0),(15,'PR-0014',_binary '\0','CHELSEA BOOOT',2000000,1500000,2,0);
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
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

--
-- Dumping routines for database 'project1_intern'
--
/*!50003 DROP PROCEDURE IF EXISTS `import_product` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `import_product`(IN id_product int,IN quantity int)
BEGIN
	DECLARE done     		INT DEFAULT FALSE;
	DECLARE order_id 		INT			     ;
	DECLARE book     		INT              ;
	DECLARE update_quantity INT 			 ;
-- Mở 1 con trỏ source_cursor để lưu kết quả của mổi record
	DECLARE source_cursor CURSOR FOR 
    SELECT 
		id, quantity_book 
    FROM
		order_detail 
	WHERE
		product_id = id_product 
	AND 
		status_id = 1
	ORDER BY 
		date_start;
        
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = true;
-- Lấy tồn kho của sản phẩm
	SELECT 
		inventory INTO update_quantity
	FROM 
		product
	WHERE 
		id = id_product;
-- Cập nhật vào biến update_quantity
	SET update_quantity = update_quantity + quantity;


	OPEN source_cursor;
	read_loop: LOOP
		FETCH source_cursor INTO order_id, book;
	IF done THEN 
		LEAVE read_loop;
    END IF;
    
	IF book <= update_quantity THEN
		UPDATE 
			order_detail
		SET 
			status_id = 2, 
            date_allocation = now() 
		WHERE 
			id = order_id;
		SET 
			update_quantity = update_quantity - book;
	ELSE 
		ITERATE read_loop;
	END IF;
    
	IF update_quantity <= 0 THEN
            LEAVE read_loop;
	END IF;
	END LOOP;
	UPDATE 
		product 
	SET 
		inventory = update_quantity 
    WHERE
		id = id_product;
	CLOSE source_cursor;
END ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-03-26 14:39:31
