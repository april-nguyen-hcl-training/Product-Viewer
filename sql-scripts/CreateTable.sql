CREATE DATABASE IF NOT EXISTS `ecommerce`;

CREATE TABLE IF NOT EXISTS `ecommerce`.`product` (
	`id` bigint PRIMARY KEY AUTO_INCREMENT, 
	`name` varchar(100), 
	`price` decimal(10,2), 
	`date_added` timestamp default now()
);