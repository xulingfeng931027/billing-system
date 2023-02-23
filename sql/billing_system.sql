/*
 Navicat MySQL Data Transfer

 Source Server         : mysql57
 Source Server Type    : MySQL
 Source Server Version : 50734
 Source Host           : localhost:3307
 Source Schema         : billing_system

 Target Server Type    : MySQL
 Target Server Version : 50734
 File Encoding         : 65001

 Date: 12/09/2022 10:14:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for billing_record
-- ----------------------------
DROP TABLE IF EXISTS `billing_record`;
CREATE TABLE `billing_record`  (
  `id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '账单ID',
  `start_time` datetime NULL DEFAULT NULL COMMENT '计费开始时间',
  `end_time` datetime NULL DEFAULT NULL COMMENT '计费结束时间',
  `calling_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主叫号',
  `called_number` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '被叫号',
  `calling_cost` decimal(10, 0) NULL DEFAULT NULL COMMENT '主叫号费用',
  `called_cost` decimal(10, 2) NULL DEFAULT NULL COMMENT '被加号费用',
  `billing_status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '计费状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '账单记录' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
