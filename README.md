# e-commerece-interface

### Prerequisites

Make sure you have the following installed on your machine:

- Java Development Kit (JDK)
- Maven
- Your favorite IDE (e.g., IntelliJ IDEA, Eclipse)
- Java version 17
- Database MySql

### Installation

1. Clone the repository:

   ```bash
   git clone https://github.com/alokvrm02/e-commerece-interface.git

### Databse Table Schema
Database Name : e_commerce_interface

```bash
CREATE TABLE `product_relevancy` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) DEFAULT NULL,
  `relevance_score` double DEFAULT NULL,
  `product_metadata_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `products` (
  `id` int NOT NULL AUTO_INCREMENT,
  `product_id` varchar(45) NOT NULL,
  `category` varchar(45) DEFAULT NULL,
  `brand` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product_id_UNIQUE` (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci


CREATE TABLE `shopper_product_mapping` (
  `id` int NOT NULL AUTO_INCREMENT,
  `shopper_id` varchar(45) NOT NULL,
  `product_relevancy_id` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
   

