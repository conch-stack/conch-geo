/*
 Navicat Premium Data Transfer

 Source Server         : Haina-Dev-Mysql
 Source Server Type    : MySQL
 Source Server Version : 80017
 Source Host           : 192.168.2.44:3307
 Source Schema         : org_management

 Target Server Type    : MySQL
 Target Server Version : 80017
 File Encoding         : 65001

 Date: 05/08/2019 10:59:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

create database if not exists `beihu-geo` character set utf8mb4 collate utf8mb4_bin;
use `beihu-geo`;

-- ----------------------------
-- 城市位点
-- ----------------------------
DROP TABLE IF EXISTS `area_point`;
CREATE TABLE `area_point` (
  `id` varchar(255) NOT NULL COMMENT '主键ID',
  `prov` varchar(255) NOT NULL COMMENT '省',
  `city` varchar(255) NULL COMMENT '市',
  `district` varchar(255) NULL COMMENT '区',
  `lat` double NOT NULL COMMENT '纬度',
  `lng` double NOT NULL COMMENT '经度',
  `ct` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `area_point_prov` (`prov`) USING BTREE COMMENT '省索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT '城市位点';

SET FOREIGN_KEY_CHECKS = 1;