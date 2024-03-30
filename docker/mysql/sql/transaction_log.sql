/*
 Navicat Premium Data Transfer

 Source Server         : springcloud
 Source Server Type    : MySQL
 Source Server Version : 50736 (5.7.36)
 Source Host           : localhost:3307
 Source Schema         : springcloud

 Target Server Type    : MySQL
 Target Server Version : 50736 (5.7.36)
 File Encoding         : 65001

 Date: 16/02/2024 22:17:47
*/

# 创建数据库
CREATE DATABASE IF NOT EXISTS springcloud DEFAULT CHARACTER SET = utf8mb4;

Use springcloud;

SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for transaction_log
-- ----------------------------
DROP TABLE IF EXISTS `transaction_log`;
CREATE TABLE `transaction_log`
(
    `id`          varchar(32) NOT NULL,
    `business`    varchar(32) DEFAULT NULL,
    `foreign_key` varchar(32) DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

SET FOREIGN_KEY_CHECKS = 1;
