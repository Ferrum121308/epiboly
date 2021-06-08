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

 Date: 07/06/2021 12:00:23
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of good
-- ----------------------------
INSERT INTO `good` VALUES (13, '123456', '测试数据', '佳能', '投影仪', -1, 6500);
INSERT INTO `good` VALUES (14, '456789', '测试数据', '惠普', '电脑', -1, 7900);
INSERT INTO `good` VALUES (15, '123789', '测试数据', '惠普', '鼠标', -1, 29.9);
INSERT INTO `good` VALUES (16, '1237891', '测试数据', '罗技', '键盘', -1, 400);
INSERT INTO `good` VALUES (24, 'da6aac59-47b8-4d35-f61e-e1d0f07d76f5', '123', 'Simple123', '2132', -1, 123);
INSERT INTO `good` VALUES (25, '71d03bd9-82c4-5688-1f0b-ace916e89217', '123', 'Simple123', 'as', -1, 123);
INSERT INTO `good` VALUES (26, '7a4308bb-faa2-9d27-f1ad-82add706154d', '2', 'Simple123', '223', 2, 123);
INSERT INTO `good` VALUES (27, 'b3704076-3961-3c47-0607-13b36ca7ddf0', 'asds', 'Simple123', '32', -1, 222);

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist
-- ----------------------------
INSERT INTO `orderlist` VALUES (32, 9, '547a246c-8a50-3239-137c-8e6f6c47749e', '2021-6-1', 7929.9);
INSERT INTO `orderlist` VALUES (33, 9, '5be51437-9c38-9fca-77f3-34425b0d2d51', '2021-6-7', 22);
INSERT INTO `orderlist` VALUES (34, 9, '2dfdce09-3f55-9167-6cf3-3e7aca00075e', '2021-6-7', 6900);
INSERT INTO `orderlist` VALUES (35, 9, '376f4491-d179-e93a-9232-c747b2de94f8', '2021-6-7', 222);
INSERT INTO `orderlist` VALUES (36, 9, '02f0c302-3294-8e3f-fd91-3ea21f1d366d', '2021-6-7', 468);

-- ----------------------------
-- Table structure for orderlist_good
-- ----------------------------
DROP TABLE IF EXISTS `orderlist_good`;
CREATE TABLE `orderlist_good`  (
  `goodId` bigint(0) NOT NULL,
  `orderId` bigint(0) NOT NULL,
  PRIMARY KEY (`goodId`, `orderId`) USING BTREE,
  INDEX `FK16r3651o7cisb0ydecd7pixxi`(`orderId`) USING BTREE,
  CONSTRAINT `FK_orderList_good` FOREIGN KEY (`goodId`) REFERENCES `good` (`goodId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_orderList_good2` FOREIGN KEY (`orderId`) REFERENCES `orderlist` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK16r3651o7cisb0ydecd7pixxi` FOREIGN KEY (`orderId`) REFERENCES `orderlist` (`orderId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orderlist_good
-- ----------------------------
INSERT INTO `orderlist_good` VALUES (14, 32);
INSERT INTO `orderlist_good` VALUES (15, 32);
INSERT INTO `orderlist_good` VALUES (13, 34);
INSERT INTO `orderlist_good` VALUES (16, 34);
INSERT INTO `orderlist_good` VALUES (27, 35);
INSERT INTO `orderlist_good` VALUES (24, 36);
INSERT INTO `orderlist_good` VALUES (25, 36);
INSERT INTO `orderlist_good` VALUES (27, 36);

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
INSERT INTO `role` VALUES (1, '商家', '游客性质', 1);
INSERT INTO `role` VALUES (2, '管理员', '管理数据库', 1);
INSERT INTO `role` VALUES (4, '顾客', '测试数据', 1);

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sellrecord
-- ----------------------------
INSERT INTO `sellrecord` VALUES (47, '电脑', '惠普', -1, 1, '2021-6-1', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (48, '鼠标', '惠普', -1, 1, '2021-6-1', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (50, '键盘', '罗技', -1, 1, '2021-6-7', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (51, '投影仪', '佳能', -1, 1, '2021-6-7', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (52, '222', 'Simple123', -1, 1, '2021-6-7', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (53, '2132', 'Simple123', -1, 1, '2021-6-7', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (54, 'as', 'Simple123', -1, 1, '2021-6-7', 9, 'Simple123');
INSERT INTO `sellrecord` VALUES (55, '32', 'Simple123', -1, 1, '2021-6-7', 9, 'Simple123');

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
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (8, 'Ferrum', '121308', '18368802918');
INSERT INTO `user` VALUES (9, 'Simple123', '111', '13506870000');
INSERT INTO `user` VALUES (13, 'xiadayu', '123456', '12345678910');
INSERT INTO `user` VALUES (14, 'zhanghongbin', '123456', '12345678910');

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
INSERT INTO `user_role` VALUES (9, 1);
INSERT INTO `user_role` VALUES (13, 1);
INSERT INTO `user_role` VALUES (8, 2);
INSERT INTO `user_role` VALUES (9, 2);
INSERT INTO `user_role` VALUES (8, 4);
INSERT INTO `user_role` VALUES (13, 4);
INSERT INTO `user_role` VALUES (14, 4);

SET FOREIGN_KEY_CHECKS = 1;
