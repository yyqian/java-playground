DROP DATABASE IF EXISTS test;

CREATE DATABASE `test`
DEFAULT CHARACTER SET utf8
DEFAULT COLLATE utf8_general_ci;

CREATE TABLE IF NOT EXISTS `test`.`user` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `created_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `mobile` VARCHAR(45) NULL,
  `email` VARCHAR(45) NULL,
  `username` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `cell_UNIQUE` (`mobile` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC));

INSERT INTO `test`.user(mobile, email, username) VALUES('13199997777', 'demo1@demo.com', 'demo1');
INSERT INTO `test`.user(mobile, email, username) VALUES('13199998888', 'demo2@demo.com', 'demo2');
