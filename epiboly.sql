/*
 Navicat Premium Data Transfer

 Source Server         : database
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : epiboly

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 09/06/2021 14:04:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for good
-- ----------------------------
DROP TABLE IF EXISTS `good`;
CREATE TABLE `good`  (
  `goodId` bigint(0) NOT NULL AUTO_INCREMENT,
  `goodNum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `goodDescription` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `goodOwner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodStatus` int(0) NULL DEFAULT NULL,
  `goodPrice` double NULL DEFAULT NULL,
  PRIMARY KEY (`goodId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (31, '123789', '测试数据', '商家1', '键盘', 1, 400);
INSERT INTO `good` VALUES (32, '123123', '测试数据', '商家1', '鼠标', 2, 450);
INSERT INTO `good` VALUES (33, '123456', '测试数据', '商家2', 'Dell Vistro 7521', 3, 6900);
INSERT INTO `good` VALUES (34, '321654', '测试数据', 'Ferrum', '《教父》', 1, 98.6);
INSERT INTO `good` VALUES (35, '3216541', '测试数据', 'Simple', '《网络编程》', 1, 87.2);
INSERT INTO `good` VALUES (36, '316541', '测试数据', '商家3', '《大前端入门教程》', 2, 97.6);
INSERT INTO `good` VALUES (37, '3165884', '测试数据', '商家3', 'JBL Go 2', 3, 128.6);
INSERT INTO `good` VALUES (38, '316588412', '测试数据', '商家2', '投影仪', 3, 1426);
INSERT INTO `good` VALUES (39, '316588', '测试数据', '商家1', '显示屏', 2, 998.8);

-- ----------------------------
-- Table structure for orderlist
-- ----------------------------
DROP TABLE IF EXISTS `orderlist`;
CREATE TABLE `orderlist`  (
  `orderId` bigint(0) NOT NULL AUTO_INCREMENT,
  `userId` bigint(0) NULL DEFAULT NULL,
  `orderNum` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `orderTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `orderPrice` double NULL DEFAULT NULL,
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `FK_user_orderList`(`userId`) USING BTREE,
  CONSTRAINT `FK_user_orderList` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------

-- ----------------------------
-- Table structure for orderlist_good
-- ----------------------------
DROP TABLE IF EXISTS `orderlist_good`;
CREATE TABLE `orderlist_good`  (
  `goodId` bigint(0) NOT NULL,
  `orderId` bigint(0) NOT NULL,
  PRIMARY KEY (`goodId`, `orderId`) USING BTREE,
  INDEX `FK16r3651o7cisb0ydecd7pixxi`(`orderId`) USING BTREE,
  CONSTRAINT `FK16r3651o7cisb0ydecd7pixxi` FOREIGN KEY (`orderId`) REFERENCES `orderlist` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_orderList_good` FOREIGN KEY (`goodId`) REFERENCES `good` (`goodId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_orderList_good2` FOREIGN KEY (`orderId`) REFERENCES `orderlist` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist_good
-- ----------------------------

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `payId` bigint(0) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(0) NULL DEFAULT NULL,
  `payNum` char(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `payAmount` decimal(10, 2) NOT NULL,
  `payTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `payStatus` tinyint(1) NOT NULL,
  PRIMARY KEY (`payId`) USING BTREE,
  INDEX `FK_order_pay2`(`orderId`) USING BTREE,
  CONSTRAINT `FK_order_pay2` FOREIGN KEY (`orderId`) REFERENCES `orderlist` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of payment
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permissionId` int(0) NOT NULL AUTO_INCREMENT,
  `permissionName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `permissionAvailable` tinyint(1) NOT NULL,
  PRIMARY KEY (`permissionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (2, '删除', 1);
INSERT INTO `permission` VALUES (3, '浏览', 1);
INSERT INTO `permission` VALUES (5, '新建', 1);
INSERT INTO `permission` VALUES (6, '修改', 1);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `roleId` int(0) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '??ɫ????????????ʹ?ã??磺????Ա??',
  `roleAvailable` tinyint(1) NOT NULL,
  PRIMARY KEY (`roleId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '用户', '游客性质', 1);
INSERT INTO `role` VALUES (2, '管理员', '管理数据库', 1);
INSERT INTO `role` VALUES (4, '商家', '测试数据', 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `roleId` int(0) NOT NULL,
  `permissionId` int(0) NOT NULL,
  PRIMARY KEY (`roleId`, `permissionId`) USING BTREE,
  INDEX `FK_role_permission2`(`permissionId`) USING BTREE,
  CONSTRAINT `FK_role_permission` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_role_permission2` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`permissionId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (2, 2);
INSERT INTO `role_permission` VALUES (1, 3);
INSERT INTO `role_permission` VALUES (2, 3);
INSERT INTO `role_permission` VALUES (4, 3);
INSERT INTO `role_permission` VALUES (2, 5);
INSERT INTO `role_permission` VALUES (4, 5);
INSERT INTO `role_permission` VALUES (2, 6);

-- ----------------------------
-- Table structure for sellrecord
-- ----------------------------
DROP TABLE IF EXISTS `sellrecord`;
CREATE TABLE `sellrecord`  (
  `recordId` bigint(0) NOT NULL AUTO_INCREMENT,
  `goodName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodOwner` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `goodStatus` int(0) NULL DEFAULT NULL,
  `orderStatus` int(0) NULL DEFAULT NULL,
  `orderTime` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `userId` bigint(0) NULL DEFAULT NULL,
  `userName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`recordId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 62 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sellrecord
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `userId` bigint(0) NOT NULL AUTO_INCREMENT,
  `userName` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `phoneNum` char(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'Ferrum', '111', '123213');
INSERT INTO `user` VALUES (9, 'Simple123', '111', '13506870000');
INSERT INTO `user` VALUES (21, '商家2', '111', '12345678910');
INSERT INTO `user` VALUES (22, '商家1', '111', '12345678910');
INSERT INTO `user` VALUES (23, '顾客1', '111', '12345678910');
INSERT INTO `user` VALUES (24, '顾客2', '111', '12345678910');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role`  (
  `userId` bigint(0) NOT NULL,
  `roleId` int(0) NOT NULL,
  PRIMARY KEY (`userId`, `roleId`) USING BTREE,
  INDEX `FK_user_role2`(`roleId`) USING BTREE,
  CONSTRAINT `FK_user_role` FOREIGN KEY (`userId`) REFERENCES `user` (`userId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_user_role2` FOREIGN KEY (`roleId`) REFERENCES `role` (`roleId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES (8, 1);
INSERT INTO `user_role` VALUES (9, 1);
INSERT INTO `user_role` VALUES (23, 1);
INSERT INTO `user_role` VALUES (24, 1);
INSERT INTO `user_role` VALUES (8, 2);
INSERT INTO `user_role` VALUES (9, 2);
INSERT INTO `user_role` VALUES (21, 4);
INSERT INTO `user_role` VALUES (22, 4);

SET FOREIGN_KEY_CHECKS = 1;
