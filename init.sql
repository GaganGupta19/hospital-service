create schema hostpital;

use hostpital;

CREATE TABLE `hospitals` (
  `hospital_id` varchar(256) NOT NULL,
  `hospital_name` varchar(256) DEFAULT NULL,
  `location` varchar(256) DEFAULT NULL,
  `city` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`hospital_id`)
) ENGINE=InnoDB;

CREATE TABLE `hospital_records` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `patient_phone` varchar(255) DEFAULT NULL,
  `vaccine_name` varchar(255) DEFAULT NULL,
  `vendor_id` varchar(255) DEFAULT NULL,
  `hospital_id` varchar(255)  DEFAULT NULL,
  `created_at` datetime(6) NOT NULL,
  `updated_at` datetime(6) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

