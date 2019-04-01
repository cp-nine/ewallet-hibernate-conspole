-- Adminer 4.7.1 MySQL dump

SET NAMES utf8;
SET time_zone = '+00:00';
SET foreign_key_checks = 0;
SET sql_mode = 'NO_AUTO_VALUE_ON_ZERO';

DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `account_number` bigint(20) NOT NULL,
  `account_name` varchar(45) DEFAULT NULL,
  `open_date` timestamp NULL DEFAULT current_timestamp(),
  `balance` bigint(20) DEFAULT 0,
  `cif` varchar(15) DEFAULT NULL,
  `username` varchar(45) DEFAULT NULL,
  `password` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`account_number`),
  KEY `FKcrkt5p53pvo1m90w3xq6bqqg3` (`cif`),
  CONSTRAINT `FKcrkt5p53pvo1m90w3xq6bqqg3` FOREIGN KEY (`cif`) REFERENCES `tb_customer` (`cif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_customer`;
CREATE TABLE `tb_customer` (
  `cif` varchar(15) NOT NULL,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `brith_date` varchar(45) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`cif`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_transaction`;
CREATE TABLE `tb_transaction` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transaction_type` varchar(5) DEFAULT NULL,
  `acn_debet` bigint(20) DEFAULT 0,
  `acn_credit` bigint(20) DEFAULT 0,
  `amount` bigint(20) DEFAULT 0,
  `date_transaction` timestamp NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_transaction_type`;
CREATE TABLE `tb_transaction_type` (
  `code` varchar(5) NOT NULL,
  `description` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_wallet`;
CREATE TABLE `tb_wallet` (
  `wallet_id` int(11) NOT NULL,
  `description` varchar(225) DEFAULT NULL,
  `active_balance` int(225) NOT NULL DEFAULT 0,
  `created_at` timestamp NULL DEFAULT current_timestamp(),
  `type` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `tb_wallet_account`;
CREATE TABLE `tb_wallet_account` (
  `wa_id` int(11) NOT NULL AUTO_INCREMENT,
  `wallet_id` int(11) NOT NULL,
  `account_number` bigint(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`wa_id`),
  KEY `FKhlrpa2axcmb5ktw2np77jo35a` (`wallet_id`),
  CONSTRAINT `FKhlrpa2axcmb5ktw2np77jo35a` FOREIGN KEY (`wallet_id`) REFERENCES `tb_wallet` (`wallet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 2019-03-31 15:56:17