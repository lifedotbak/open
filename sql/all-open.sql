/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3307
 Source Schema         : open_os

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/05/2024 22:32:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for open_user_role
-- ----------------------------
DROP TABLE IF EXISTS `open_user_role`;
CREATE TABLE `open_user_role`  (
                                      `id` varchar(64) NOT NULL COMMENT 'id',
                                      `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                      `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                      `user_id` varchar(64)  NOT NULL COMMENT '用户id',
                                      `role_id`varchar(64)  NOT NULL COMMENT '角色id',
                                      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_user_role
-- ----------------------------
INSERT INTO `open_user_role` VALUES (30, 0, '2023-06-08 23:10:30', '2023-06-08 23:10:33', '13', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (31, 0, '2023-06-10 16:22:03', '2023-06-10 16:22:03', '14', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (37, 0, '2023-06-17 12:54:04', '2023-06-17 12:54:04', '1', 'ROOT', NULL);
INSERT INTO `open_user_role` VALUES (39, 0, '2023-06-20 21:59:54', '2023-06-20 21:59:54', '10', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (40, 0, '2023-06-20 22:00:05', '2023-06-20 22:00:05', '9', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (41, 0, '2023-06-20 22:00:22', '2023-06-20 22:00:22', '6', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (42, 0, '2023-06-20 22:00:40', '2023-06-20 22:00:40', '5', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (43, 0, '2023-06-20 22:00:54', '2023-06-20 22:00:54', '4', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (44, 0, '2023-06-20 22:01:09', '2023-06-20 22:01:09', '2', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (65, 0, '2023-07-30 00:51:57', '2023-07-30 00:51:57', '12', 'ts', NULL);
INSERT INTO `open_user_role` VALUES (67, 0, '2023-08-13 20:54:06', '2023-08-13 20:54:06', '15', 'ts', NULL);

-- ----------------------------
-- Table structure for open_user
-- ----------------------------
DROP TABLE IF EXISTS `open_user`;
CREATE TABLE `open_user`  (
                                 `id` varchar(64) NOT NULL COMMENT 'id',
                                 `name`varchar(64)  NOT NULL COMMENT '用户名',
                                 `pinyin`varchar(64)  NULL DEFAULT NULL COMMENT '拼音  全拼',
                                 `py`varchar(64)  NULL DEFAULT NULL COMMENT '拼音, 首字母缩写',
                                 `avatar_url` varchar(1024)  NULL DEFAULT NULL COMMENT '头像url',
                                 `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `password` varchar(255)  NULL DEFAULT NULL COMMENT '登录密码',
                                 `phone` varchar(18)  NULL DEFAULT NULL COMMENT '手机号',
                                 `status` int NOT NULL DEFAULT 0,
                                 `parent_id` bigint NULL DEFAULT NULL COMMENT '直属领导id',
                                 `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_user
-- ----------------------------
INSERT INTO `open_user` VALUES (1, '用户1', 'yonghu1', 'yh1', NULL, 0, '2023-05-05 15:23:40', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15584589856', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (2, '大幅度', 'dafudu', 'dfd', NULL, 0, '2023-05-05 21:02:30', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '13241528569', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (3, '山东省', NULL, NULL, NULL, 0, '2023-05-05 21:03:32', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '18754215896', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (4, '地方', 'difang', 'df', NULL, 0, '2023-05-05 21:04:33', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '13325635214', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (5, '地方', 'difang', 'df', NULL, 0, '2023-05-05 21:06:11', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '13552635263', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (6, '水电费', 'shuidianfei', 'sdf', NULL, 0, '2023-05-05 22:25:45', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15885968526', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (7, '电饭锅', 'dianfanguo', 'dfg', NULL, 0, '2023-05-05 22:26:27', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15265265238', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (9, 'dddd', 'dddd', 'dddd', NULL, 0, '2023-05-07 11:40:55', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '18755289563', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (10, 'ttttt', 'ttttt', 'ttttt', NULL, 0, '2023-05-07 11:41:32', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15748568596', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (11, 'tttew23', 'tttew23', 'tttew23', NULL, 0, '2023-05-07 11:42:31', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15269852365', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (12, '测试2', 'ceshi2', 'cs2', NULL, 0, '2023-05-07 11:50:51', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '18752859635', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (13, '超级管理员', 'ceshi111', 'cs111', NULL, 0, '2023-05-07 11:53:14', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '18888888888', 1, NULL, NULL);
INSERT INTO `open_user` VALUES (15, '张三', 'zhangsan', 'zs', NULL, 0, '2023-06-10 16:35:40', '2024-04-23 15:22:13', 'e10adc3949ba59abbe56e057f20f883e', '15265235896', 1, NULL, NULL);

-- ----------------------------
-- Table structure for open_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `open_role_menu`;
CREATE TABLE `open_role_menu`  (
                                      `id` varchar(64) NOT NULL COMMENT 'id',
                                      `role_id`varchar(64)  NOT NULL COMMENT '角色ID',
                                      `menu_id` varchar(64) NOT NULL COMMENT '菜单ID',
                                      `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                      `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_role_menu
-- ----------------------------
INSERT INTO `open_role_menu` VALUES (445, 'ADMIN', 95, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (446, 'ADMIN', 96, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (447, 'ADMIN', 97, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (448, 'ADMIN', 98, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (449, 'ADMIN', 99, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (450, 'ADMIN', 89, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (451, 'ADMIN', 91, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (452, 'ADMIN', 94, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (453, 'ADMIN', 90, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (454, 'ADMIN', 1, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (455, 'ADMIN', 2, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (456, 'ADMIN', 31, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (457, 'ADMIN', 32, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (458, 'ADMIN', 33, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (459, 'ADMIN', 88, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (460, 'ADMIN', 101, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (461, 'ADMIN', 100, 1, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (462, 'ADMIN', 3, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (463, 'ADMIN', 70, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (464, 'ADMIN', 71, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (465, 'ADMIN', 72, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (466, 'ADMIN', 4, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (467, 'ADMIN', 73, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (468, 'ADMIN', 75, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (469, 'ADMIN', 74, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (470, 'ADMIN', 5, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (471, 'ADMIN', 76, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (472, 'ADMIN', 77, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (473, 'ADMIN', 78, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (474, 'ADMIN', 6, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (475, 'ADMIN', 79, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (476, 'ADMIN', 81, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (477, 'ADMIN', 84, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (478, 'ADMIN', 85, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (479, 'ADMIN', 86, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (480, 'ADMIN', 87, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (481, 'ADMIN', 40, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (482, 'ADMIN', 41, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (483, 'ADMIN', 26, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (484, 'ADMIN', 30, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (485, 'ADMIN', 36, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (486, 'ADMIN', 102, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (487, 'ADMIN', 37, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (488, 'ADMIN', 38, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (489, 'ADMIN', 39, 0, '2023-07-25 22:16:07', '2023-07-25 22:16:07', NULL);
INSERT INTO `open_role_menu` VALUES (1706144314223669259, 'ADMIN', 1706144197164838914, 0, '2023-09-25 11:11:18', '2023-09-25 11:11:18', NULL);
INSERT INTO `open_role_menu` VALUES (1716270422453620740, 'ADMIN', 1716270348700979202, 0, '2023-10-23 09:48:50', '2023-10-23 09:48:50', NULL);
INSERT INTO `open_role_menu` VALUES (1722242511636373511, 'ADMIN', 1722242469999517698, 1, '2023-11-08 21:19:47', '2023-11-08 21:19:47', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304962, 'ROOT', 95, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304963, 'ROOT', 96, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304964, 'ROOT', 97, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304965, 'ROOT', 98, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304966, 'ROOT', 99, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304967, 'ROOT', 89, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304968, 'ROOT', 1722242469999517698, 1, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304969, 'ROOT', 91, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304970, 'ROOT', 94, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304971, 'ROOT', 90, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304972, 'ROOT', 1, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304973, 'ROOT', 2, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304974, 'ROOT', 31, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304975, 'ROOT', 32, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304976, 'ROOT', 33, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304977, 'ROOT', 88, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263947304978, 'ROOT', 1716270348700979202, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442306, 'ROOT', 101, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442307, 'ROOT', 100, 1, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442308, 'ROOT', 3, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442309, 'ROOT', 70, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442310, 'ROOT', 71, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442311, 'ROOT', 72, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442312, 'ROOT', 4, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442313, 'ROOT', 73, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442314, 'ROOT', 75, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442315, 'ROOT', 74, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442316, 'ROOT', 5, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442317, 'ROOT', 76, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442318, 'ROOT', 77, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1731321263993442319, 'ROOT', 78, 0, '2023-12-03 22:35:30', '2023-12-03 22:35:30', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803842, 'ts', 1, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803843, 'ts', 2, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803844, 'ts', 31, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803845, 'ts', 32, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803846, 'ts', 100, 1, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803847, 'ts', 101, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803848, 'ts', 3, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803849, 'ts', 70, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803850, 'ts', 71, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803851, 'ts', 5, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803852, 'ts', 76, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803853, 'ts', 77, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803854, 'ts', 89, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803855, 'ts', 1722242469999517698, 1, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803856, 'ts', 91, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803857, 'ts', 94, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803858, 'ts', 90, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803859, 'ts', 95, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803860, 'ts', 96, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803861, 'ts', 97, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803862, 'ts', 98, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);
INSERT INTO `open_role_menu` VALUES (1732311877680803863, 'ts', 99, 0, '2023-12-06 16:11:51', '2023-12-06 16:11:51', NULL);

-- ----------------------------
-- Table structure for open_role
-- ----------------------------
DROP TABLE IF EXISTS `open_role`;
CREATE TABLE `open_role`  (
                                 `id` varchar(64) NOT NULL COMMENT 'id',
                                 `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 `name`varchar(64)  NULL DEFAULT NULL COMMENT '角色名字',
                                 `user_id`  varchar(64) NULL DEFAULT NULL COMMENT '创建人',
                                 `role_key`varchar(64)  NULL DEFAULT  NULL,
                                 `status` int NULL DEFAULT 1,
                                 `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_role
-- ----------------------------
INSERT INTO `open_role` VALUES (1, 0, '2023-06-08 22:49:41', '2023-06-08 22:49:43', '超级管理员', 13, 'ROOT', 1, NULL);
INSERT INTO `open_role` VALUES (2, 1, '2023-06-08 23:01:30', '2023-12-03 22:31:39', '系统管理员', NULL, 'ADMIN', 1, NULL);
INSERT INTO `open_role` VALUES (32, 1, '2023-06-10 23:39:23', '2023-06-10 23:41:17', '测试角色', 13, 'test', 1, NULL);
INSERT INTO `open_role` VALUES (33, 0, '2023-06-10 23:44:17', '2023-06-10 23:44:17', '测试', 13, 'ts', 1, NULL);

-- ----------------------------
-- Table structure for open_process_starter
-- ----------------------------
DROP TABLE IF EXISTS `open_process_starter`;
CREATE TABLE `open_process_starter`  (
                                            `id` varchar(64) NOT NULL COMMENT 'id',
                                            `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                            `create_time` datetime NOT NULL COMMENT '创建时间',
                                            `update_time` datetime NOT NULL COMMENT '更新时间',
                                            `type_id`varchar(64)  NOT NULL COMMENT '用户id或者部门id',
                                            `contain_children_dept` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否包含下级部门',
                                            `type_name`varchar(64)  NOT
                                            NULL COMMENT ' 类型 user dept',
                                            `process_id` bigint NOT NULL COMMENT '流程表id',
                                            `data` varchar(500)  NULL DEFAULT NULL COMMENT '数据',
                                            `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                            `flow_id` varchar(200)  NOT NULL DEFAULT '' COMMENT '流程id',
                                            PRIMARY KEY (`id`) USING BTREE,
                                            INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程发起人范围' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_starter
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_node_data
-- ----------------------------
DROP TABLE IF EXISTS `open_process_node_data`;
CREATE TABLE `open_process_node_data`  (
                                              `id` varchar(64) NOT NULL COMMENT 'id',
                                              `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                              `create_time` datetime NOT NULL COMMENT '创建时间',
                                              `update_time` datetime NOT NULL COMMENT '更新时间',
                                              `flow_id`varchar(64)  NOT NULL COMMENT '流程id',
                                              `data` longtext  NOT NULL COMMENT '表单数据',
                                              `node_id`varchar(64)  NOT NULL,
                                              `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                              PRIMARY KEY (`id`) USING BTREE,
                                              INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程节点数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_node_data
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_main
-- ----------------------------
DROP TABLE IF EXISTS `open_process_main`;
CREATE TABLE `open_process_main`  (
                                         `id` varchar(64) NOT NULL COMMENT 'id',
                                         `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `name`varchar(64)  NOT NULL COMMENT '表单名称',
                                         `logo` varchar(200)  NOT NULL COMMENT '图标配置',
                                         `group_id` bigint NOT NULL COMMENT '分组ID',
                                         `sort_value` int NOT NULL,
                                         `unique_id`varchar(64)  NULL DEFAULT NULL COMMENT '唯一性id',
                                         `range_show` varchar(255)  NULL DEFAULT NULL COMMENT '范围描述显示',
                                         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                         PRIMARY KEY (`id`) USING BTREE,
                                         INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_main
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_user_copy`;
CREATE TABLE `open_process_instance_user_copy`  (
                                                       `id` varchar(64) NOT NULL COMMENT 'id',
                                                       `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                       `create_time` datetime NOT NULL COMMENT '创建时间',
                                                       `update_time` datetime NOT NULL COMMENT '更新时间',
                                                       `start_user_id`varchar(64)  NOT NULL COMMENT '发起人',
                                                       `flow_id` varchar(255)  NOT NULL COMMENT '流程id',
                                                       `process_instance_id` varchar(255)  NOT NULL COMMENT '实例id',
                                                       `group_id` bigint NOT NULL COMMENT '分组id',
                                                       `group_name` varchar(255)  NOT NULL COMMENT '分组名称',
                                                       `process_name` varchar(255)  NOT NULL COMMENT '流程名称',
                                                       `user_id` varchar(64)  NOT NULL COMMENT '抄送人id',
                                                       `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                       PRIMARY KEY (`id`) USING BTREE,
                                                       INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程抄送数据--用户和实例唯一值' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_user_copy
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_record
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_record`;
CREATE TABLE `open_process_instance_record`  (
                                                    `id` varchar(64) NOT NULL COMMENT 'id',
                                                    `name`varchar(64)  NOT NULL COMMENT '流程名字',
                                                    `logo` varchar(200)  NOT NULL COMMENT '头像',
                                                    `user_id` varchar(64)  NOT NULL COMMENT '发起人的用户id',
                                                    `main_dept_id`varchar(64)  NULL DEFAULT NULL COMMENT '发起人主部门id',
                                                    `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                    `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                    `flow_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程id',
                                                    `process_instance_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例id',
                                                    `process_instance_biz_code`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例业务编码',
                                                    `process_instance_biz_key`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例业务key',
                                                    `form_data` longtext  NULL COMMENT '表单数据',
                                                    `group_id` bigint NULL DEFAULT NULL COMMENT '组id',
                                                    `group_name` varchar(100)  NULL DEFAULT NULL COMMENT '组名称',
                                                    `status` int NULL DEFAULT 1 COMMENT '状态',
                                                    `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
                                                    `parent_process_instance_id`varchar(64)  NULL DEFAULT NULL COMMENT '上级流程实例id',
                                                    `process` json NULL,
                                                    `result` int NULL DEFAULT NULL COMMENT '结果',
                                                    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                    `parent_process_node_execution_id`varchar(64)  NULL DEFAULT NULL COMMENT '主流程的节点执行id',
                                                    PRIMARY KEY (`id`) USING BTREE,
                                                    INDEX `idx_id`(`id` ASC) USING BTREE,
                                                    INDEX `idx_dep_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_record
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_oper_record
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_oper_record`;
CREATE TABLE `open_process_instance_oper_record`  (
                                                         `id` varchar(64) NOT NULL COMMENT 'id',
                                                         `user_id` varchar(64)  NOT NULL COMMENT '用户id',
                                                         `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                                         `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                                         `flow_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程id',
                                                         `node_id`varchar(64)  NULL DEFAULT NULL COMMENT '节点id',
                                                         `node_name` varchar(255)  NULL DEFAULT NULL COMMENT '节点名称',
                                                         `process_instance_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例id',
                                                         `comment` varchar(500)  NULL DEFAULT NULL COMMENT '评论',
                                                         `oper_type` varchar(20)  NULL DEFAULT NULL COMMENT '操作类型',
                                                         `oper_desc` varchar(500)  NULL DEFAULT NULL COMMENT '操作描述',
                                                         `image_list` varchar(2000)  NULL DEFAULT NULL COMMENT '图片列表',
                                                         `file_list` varchar(2000)  NULL DEFAULT NULL COMMENT '文件列表',
                                                         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                         PRIMARY KEY (`id`) USING BTREE,
                                                         INDEX `idx_id`(`id` ASC) USING BTREE,
                                                         INDEX `idx_dep_id`(`user_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_oper_record
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_node_record
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_node_record`;
CREATE TABLE `open_process_instance_node_record`  (
                                                         `id` varchar(64) NOT NULL COMMENT 'id',
                                                         `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                         `create_time` datetime NOT NULL COMMENT '创建时间',
                                                         `update_time` datetime NOT NULL COMMENT '更新时间',
                                                         `flow_id`varchar(64)  NOT NULL COMMENT '流程id',
                                                         `process_instance_id`varchar(64)  NOT NULL COMMENT '流程实例id',
                                                         `data` longtext  NULL COMMENT '表单数据',
                                                         `node_id`varchar(64)  NOT NULL,
                                                         `node_type`varchar(64)  NULL DEFAULT NULL COMMENT '节点类型',
                                                         `node_name`varchar(64)  NOT NULL COMMENT '节点名字',
                                                         `status` int NOT NULL COMMENT '节点状态',
                                                         `start_time` datetime NOT NULL COMMENT '开始时间',
                                                         `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
                                                         `execution_id` varchar(255)  NULL DEFAULT NULL COMMENT '执行id',
                                                         `parent_node_id` varchar(255)  NULL DEFAULT NULL COMMENT '上一层级id',
                                                         `flow_unique_id` varchar(255)  NULL DEFAULT NULL COMMENT '流转唯一标识',
                                                         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                         PRIMARY KEY (`id`) USING BTREE,
                                                         INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程节点记录' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_node_record
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_execution
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_execution`;
CREATE TABLE `open_process_instance_execution`  (
                                                       `id` varchar(64) NOT NULL COMMENT 'id',
                                                       `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                       `create_time` datetime NOT NULL COMMENT '创建时间',
                                                       `update_time` datetime NOT NULL COMMENT '更新时间',
                                                       `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                       `process_instance_id`varchar(64)  NOT NULL COMMENT '流程实例id',
                                                       `node_id`varchar(64)  NOT NULL COMMENT '节点id',
                                                       `execution_id` varchar(255)  NOT NULL COMMENT '执行id',
                                                       `parent_execution_id` varchar(255)  NULL DEFAULT NULL COMMENT '上级执行id',
                                                       `flow_id` varchar(40)  NOT NULL DEFAULT '' COMMENT '流程id',
                                                       PRIMARY KEY (`id`) USING BTREE,
                                                       INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程执行id数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_execution
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_copy
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_copy`;
CREATE TABLE `open_process_instance_copy`  (
                                                  `id` varchar(64) NOT NULL COMMENT 'id',
                                                  `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                  `create_time` datetime NOT NULL COMMENT '创建时间',
                                                  `update_time` datetime NOT NULL COMMENT '更新时间',
                                                  `start_time` datetime NOT NULL COMMENT ' 流程发起时间',
                                                  `node_time` datetime NOT NULL COMMENT '当前节点时间',
                                                  `start_user_id`varchar(64)  NOT NULL COMMENT '发起人',
                                                  `flow_id` varchar(255)  NOT NULL COMMENT '流程id',
                                                  `process_instance_id` varchar(255)  NOT NULL COMMENT '实例id',
                                                  `node_id` varchar(255)  NOT NULL COMMENT '节点id',
                                                  `group_id` bigint NOT NULL COMMENT '分组id',
                                                  `group_name` varchar(255)  NOT NULL COMMENT '分组名称',
                                                  `process_name` varchar(255)  NOT NULL COMMENT '流程名称',
                                                  `node_name` varchar(255)  NOT NULL COMMENT '节点 名称',
                                                  `form_data` longtext  NOT NULL COMMENT '表单数据',
                                                  `user_id` varchar(64)  NOT NULL COMMENT '抄送人id',
                                                  `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                  PRIMARY KEY (`id`) USING BTREE,
                                                  INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程抄送数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_copy
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_instance_assign_user_record
-- ----------------------------
DROP TABLE IF EXISTS `open_process_instance_assign_user_record`;
CREATE TABLE `open_process_instance_assign_user_record`  (
                                                                `id` varchar(64) NOT NULL COMMENT 'id',
                                                                `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                                                `create_time` datetime NOT NULL COMMENT '创建时间',
                                                                `update_time` datetime NOT NULL COMMENT '更新时间',
                                                                `flow_id`varchar(64)  NOT NULL COMMENT '流程id',
                                                                `process_instance_id`varchar(64)  NOT NULL COMMENT '流程实例id',
                                                                `data` longtext  NULL COMMENT '表单数据',
                                                                `node_id`varchar(64)  NOT NULL,
                                                                `user_id` varchar(64)  NOT NULL COMMENT ' 用户id',
                                                                `status` int NOT NULL COMMENT '节点状态',
                                                                `start_time` datetime NOT NULL COMMENT '开始时间',
                                                                `end_time` datetime NULL DEFAULT NULL COMMENT '结束时间',
                                                                `execution_id` varchar(255)  NULL DEFAULT NULL COMMENT '执行id',
                                                                `task_id` varchar(255)  NULL DEFAULT NULL COMMENT ' 任务id',
                                                                `approve_desc` varchar(1000)  NULL DEFAULT NULL COMMENT '审批意见',
                                                                `node_name` varchar(255)  NULL DEFAULT NULL COMMENT ' 节点名称',
                                                                `task_type` varchar(255)  NULL DEFAULT NULL COMMENT '任务类型',
                                                                `local_data` longtext  NULL COMMENT '表单本地数据',
                                                                `flow_unique_id` varchar(255)  NULL DEFAULT NULL COMMENT '流转唯一标识',
                                                                `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                                                `auto` tinyint(1) NULL DEFAULT 0 COMMENT '是否是自动完成',
                                                                `parent_execution_id` varchar(255)  NULL DEFAULT NULL COMMENT '节点执行id',
                                                                PRIMARY KEY (`id`) USING BTREE,
                                                                INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程节点记录-执行人' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_instance_assign_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_group
-- ----------------------------
DROP TABLE IF EXISTS `open_process_group`;
CREATE TABLE `open_process_group`  (
                                          `id` varchar(64) NOT NULL COMMENT 'id',
                                          `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                          `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                          `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                          `group_name`varchar(64)  NOT NULL COMMENT '分组名',
                                          `sort_value` int NOT NULL DEFAULT 0 COMMENT '排序',
                                          `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                          PRIMARY KEY (`id`) USING BTREE,
                                          INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_group
-- ----------------------------

-- ----------------------------
-- Table structure for open_process_form
-- ----------------------------
DROP TABLE IF EXISTS `open_process_form`;
CREATE TABLE `open_process_form`  (
                                         `id` varchar(64) NOT NULL COMMENT 'id',
                                         `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                         `unique_id` varchar(200)  NOT NULL DEFAULT '' COMMENT '流程唯一id',
                                         `form_name` varchar(200)  NOT NULL COMMENT '表单名称',
                                         `form_id` varchar(200)  NOT NULL COMMENT '表单id',
                                         `form_type` varchar(20)  NULL DEFAULT NULL COMMENT '表单类型',
                                         `props` json NOT NULL COMMENT '表单属性',
                                         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                         `flow_id` varchar(200)  NOT NULL DEFAULT '' COMMENT '流程id',
                                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程表单' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process_form
-- ----------------------------

-- ----------------------------
-- Table structure for open_process
-- ----------------------------
DROP TABLE IF EXISTS `open_process`;
CREATE TABLE `open_process`  (
                                    `id` varchar(64) NOT NULL COMMENT 'id',
                                    `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `flow_id` varchar(40)  NOT NULL DEFAULT '' COMMENT '表单ID',
                                    `name`varchar(64)  NOT NULL COMMENT '表单名称',
                                    `logo` varchar(200)  NOT NULL COMMENT '图标配置',
                                    `settings` json NULL COMMENT '设置项',
                                    `group_id` bigint NOT NULL COMMENT '分组ID',
                                    `form_items` json NOT NULL COMMENT '表单设置内容',
                                    `process` json NOT NULL COMMENT '流程设置内容',
                                    `remark` varchar(125)  NULL DEFAULT NULL COMMENT '备注',
                                    `sort_value` int NOT NULL,
                                    `is_hidden` tinyint(1) NOT NULL COMMENT '0 正常 1=隐藏',
                                    `is_stop` tinyint(1) NOT NULL COMMENT '0 正常 1=停用 ',
                                    `admin_id` varchar(100)  NULL DEFAULT NULL COMMENT '流程管理员',
                                    `unique_id`varchar(64)  NULL DEFAULT NULL COMMENT '唯一性id',
                                    `admin` varchar(255)  NULL DEFAULT NULL COMMENT '管理员',
                                    `range_show` varchar(255)  NULL DEFAULT NULL COMMENT '范围描述显示',
                                    `version` int NULL DEFAULT NULL COMMENT '版本',
                                    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                    `form_items_pc` json NOT NULL COMMENT '表单设置内容pc',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    UNIQUE INDEX `idx_form_id`(`flow_id` ASC) USING BTREE,
                                    INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_process
-- ----------------------------

-- ----------------------------
-- Table structure for open_message
-- ----------------------------
DROP TABLE IF EXISTS `open_message`;
CREATE TABLE `open_message`  (
                                    `id` varchar(64) NOT NULL COMMENT 'id',
                                    `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                    `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                    `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                    `message_type` varchar(100)  NOT
                                    NULL COMMENT '类型',
                                    `readed` tinyint(1) NOT NULL COMMENT '是否已读',
                                    `user_id` varchar(64)  NOT NULL COMMENT '用户id',
                                    `biz_unique_id` varchar(100)  NULL DEFAULT NULL COMMENT '业务唯一id',
                                    `param` longtext  NULL COMMENT '消息参数',
                                    `content` varchar(1000)  NULL DEFAULT NULL COMMENT '消息内容',
                                    `title`varchar(64)  NULL DEFAULT NULL COMMENT '消息头',
                                    `flow_id`varchar(64)  NOT NULL COMMENT '流程id',
                                    `process_instance_id`varchar(64)  NOT NULL COMMENT '流程实例id',
                                    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                    PRIMARY KEY (`id`) USING BTREE,
                                    INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '通知消息' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_message
-- ----------------------------

-- ----------------------------
-- Table structure for open_menu
-- ----------------------------
DROP TABLE IF EXISTS `open_menu`;
CREATE TABLE `open_menu`  (
                                 `id` varchar(64) NOT NULL,
                                 `parent_id` bigint NOT NULL COMMENT '父菜单ID',
                                 `tree_path` varchar(255)  NULL DEFAULT NULL COMMENT '父节点ID路径',
                                 `name` varchar(64)  NOT NULL DEFAULT '' COMMENT '菜单名称',
                                 `menu_type` tinyint NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
                                 `path` varchar(128)  NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
                                 `component` varchar(128)  NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
                                 `perm` varchar(128)  NULL DEFAULT NULL COMMENT '权限标识',
                                 `visible` tinyint(1) NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
                                 `sort_value` int NULL DEFAULT 0 COMMENT '排序',
                                 `icon` varchar(64)  NULL DEFAULT '' COMMENT '菜单图标',
                                 `redirect` varchar(128)  NULL DEFAULT NULL COMMENT '跳转路径',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
                                 `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                 `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_menu
-- ----------------------------
INSERT INTO `open_menu` VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21', '2021-08-28 09:12:21', 0, NULL);
INSERT INTO `open_menu` VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 0, NULL);
INSERT INTO `open_menu` VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 0, NULL);
INSERT INTO `open_menu` VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 0, NULL);
INSERT INTO `open_menu` VALUES (5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21', 0, NULL);
INSERT INTO `open_menu` VALUES (6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 0, 5, 'dict', NULL, '2021-08-28 09:12:21', '2023-06-11 08:59:40', 0, NULL);
INSERT INTO `open_menu` VALUES (20, 0, '0', '多级菜单', 2, '/multi-level', 'Layout', NULL, 1, 9, 'multi_level', '/multi-level/multi-level1', '2022-02-16 23:11:00', '2023-06-11 09:00:09', 1, NULL);
INSERT INTO `open_menu` VALUES (21, 20, '0,20', '菜单一级', 2, 'multi-level1', 'demo/multi-level/level1', NULL, 1, 1, '', '/multi-level/multi-level2', '2022-02-16 23:13:38', '2023-06-11 09:00:09', 1, NULL);
INSERT INTO `open_menu` VALUES (22, 21, '0,20,21', '菜单二级', 2, 'multi-level2', 'demo/multi-level/children/level2', NULL, 1, 1, '', '/multi-level/multi-level2/multi-level3-1', '2022-02-16 23:14:23', '2023-06-11 09:00:09', 1, NULL);
INSERT INTO `open_menu` VALUES (23, 22, '0,20,21,22', '菜单三级-1', 1, 'multi-level3-1', 'demo/multi-level/children/children/level3-1', NULL, 1, 1, '', '', '2022-02-16 23:14:51', '2023-06-11 09:00:09', 1, NULL);
INSERT INTO `open_menu` VALUES (24, 22, '0,20,21,22', '菜单三级-2', 1, 'multi-level3-2', 'demo/multi-level/children/children/level3-2', NULL, 1, 2, '', '', '2022-02-16 23:15:08', '2023-06-11 09:00:09', 1, NULL);
INSERT INTO `open_menu` VALUES (26, 0, '0', '外部链接', 2, '/external-link', 'Layout', NULL, 0, 8, 'link', 'noredirect', '2022-02-17 22:51:20', '2023-06-11 09:03:20', 0, NULL);
INSERT INTO `open_menu` VALUES (30, 26, '0,26', 'document', 3, 'https://juejin.cn/post/7228990409909108793', '', NULL, 1, 1, 'document', '', '2022-02-18 00:01:40', '2022-02-18 00:01:40', 0, NULL);
INSERT INTO `open_menu` VALUES (31, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', 0, NULL);
INSERT INTO `open_menu` VALUES (32, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', 0, NULL);
INSERT INTO `open_menu` VALUES (33, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11', 0, NULL);
INSERT INTO `open_menu` VALUES (36, 0, '0', '组件封装', 2, '/component', 'Layout', NULL, 1, 10, 'menu', '', '2022-10-31 09:18:44', '2023-07-25 22:15:15', 0, NULL);
INSERT INTO `open_menu` VALUES (37, 36, '0,36', '富文本编辑器', 1, 'wang-editor', 'demo/wang-editor', NULL, 1, 1, '', '', NULL, NULL, 0, NULL);
INSERT INTO `open_menu` VALUES (38, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', 0, NULL);
INSERT INTO `open_menu` VALUES (39, 36, '0,36', '图标选择器', 1, 'icon-selector', 'demo/icon-selector', NULL, 1, 3, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32', 0, NULL);
INSERT INTO `open_menu` VALUES (40, 0, '0', '接口', 2, '/api', 'Layout', NULL, 0, 7, 'api', '', '2022-02-17 22:51:20', '2023-06-11 09:02:45', 0, NULL);
INSERT INTO `open_menu` VALUES (41, 40, '0,40', '接口文档', 1, 'apidoc', 'demo/api-doc', NULL, 1, 1, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20', 0, NULL);
INSERT INTO `open_menu` VALUES (70, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-05-20 23:39:09', '2023-05-20 23:39:09', 0, NULL);
INSERT INTO `open_menu` VALUES (71, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-05-20 23:40:31', '2023-05-20 23:40:31', 0, NULL);
INSERT INTO `open_menu` VALUES (72, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-05-20 23:41:08', '2023-05-20 23:41:08', 0, NULL);
INSERT INTO `open_menu` VALUES (73, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-05-20 23:41:35', '2023-05-20 23:41:35', 0, NULL);
INSERT INTO `open_menu` VALUES (74, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-05-20 23:41:58', '2023-05-20 23:41:58', 0, NULL);
INSERT INTO `open_menu` VALUES (75, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-05-20 23:44:18', '2023-05-20 23:44:18', 0, NULL);
INSERT INTO `open_menu` VALUES (76, 5, '0,1,5', '部门新增', 4, '', NULL, 'sys:dept:add', 1, 1, '', NULL, '2023-05-20 23:45:00', '2023-05-20 23:45:00', 0, NULL);
INSERT INTO `open_menu` VALUES (77, 5, '0,1,5', '部门编辑', 4, '', NULL, 'sys:dept:edit', 1, 2, '', NULL, '2023-05-20 23:46:16', '2023-05-20 23:46:16', 0, NULL);
INSERT INTO `open_menu` VALUES (78, 5, '0,1,5', '部门删除', 4, '', NULL, 'sys:dept:delete', 1, 3, '', NULL, '2023-05-20 23:46:36', '2023-05-20 23:46:36', 0, NULL);
INSERT INTO `open_menu` VALUES (79, 6, '0,1,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-05-21 00:16:06', '2023-05-21 00:16:06', 0, NULL);
INSERT INTO `open_menu` VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-05-21 00:27:37', '2023-05-21 00:27:37', 0, NULL);
INSERT INTO `open_menu` VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-05-21 00:29:39', '2023-05-21 00:29:39', 0, NULL);
INSERT INTO `open_menu` VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-05-21 00:46:56', '2023-05-21 00:47:06', 0, NULL);
INSERT INTO `open_menu` VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-05-21 00:47:36', '2023-05-21 00:47:36', 0, NULL);
INSERT INTO `open_menu` VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-05-21 00:48:10', '2023-05-21 00:48:20', 0, NULL);
INSERT INTO `open_menu` VALUES (88, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-05-21 00:49:18', '2023-05-21 00:49:18', 0, NULL);
INSERT INTO `open_menu` VALUES (89, 0, '0', '流程管理', 2, '/flow', 'Layout', NULL, 1, 1, 'multi_level', NULL, '2023-06-09 23:33:13', '2023-06-11 08:37:00', 0, NULL);
INSERT INTO `open_menu` VALUES (90, 89, '0,89', '流程列表', 1, '/flow/list', 'open/views/flow/list', NULL, 1, 3, 'menu', '', '2023-06-09 23:35:14', '2023-09-03 00:08:49', 0, NULL);
INSERT INTO `open_menu` VALUES (91, 89, '0,89', '流程组', 1, '/flow/group', 'open/views/flow/group', NULL, 1, 1, 'redis', NULL, '2023-06-09 23:37:38', '2023-09-03 00:08:32', 0, NULL);
INSERT INTO `open_menu` VALUES (92, 0, '0', '订单的1', 1, 'test', 'test/index', NULL, 1, 1, 'cascader', NULL, '2023-06-11 08:08:24', '2023-06-11 08:46:28', 1, NULL);
INSERT INTO `open_menu` VALUES (93, 92, '0,92', 'asadfffff', 1, 'dept1', 'system/dept/index1', NULL, 1, 1, 'cascader', NULL, '2023-06-11 08:46:12', '2023-06-11 08:46:28', 1, NULL);
INSERT INTO `open_menu` VALUES (94, 89, '0,89', '创建流程', 1, '/flow/create', 'open/views/flow/create', NULL, 1, 2, 'tree', NULL, '2023-06-11 12:57:28', '2023-09-03 00:08:42', 0, NULL);
INSERT INTO `open_menu` VALUES (95, 0, '0', '任务管理', 2, '/task', 'Layout', NULL, 1, 1, 'number', '', '2023-06-17 23:14:09', '2023-06-17 23:16:53', 0, NULL);
INSERT INTO `open_menu` VALUES (96, 95, '0,95', '待办任务', 1, '/task/pending', 'open/views/task/pending', NULL, 1, 1, 'edit', NULL, '2023-06-18 15:01:34', '2023-09-03 00:07:56', 0, NULL);
INSERT INTO `open_menu` VALUES (97, 95, '0,95', '我的发起', 1, 'task/started', 'open/views/task/started', NULL, 1, 2, 'menu', NULL, '2023-06-18 22:07:38', '2023-09-03 00:08:04', 0, NULL);
INSERT INTO `open_menu` VALUES (98, 95, '0,95', '抄送给我', 1, '/task/cc', 'open/views/task/cc', NULL, 1, 3, 'dashboard', NULL, '2023-06-22 23:38:52', '2023-09-03 00:08:12', 0, NULL);
INSERT INTO `open_menu` VALUES (99, 95, '0,95', '我的已办', 1, '/task/completed', 'open/views/task/completed', NULL, 1, 4, 'skill', NULL, '2023-06-23 10:19:04', '2023-09-03 00:08:21', 0, NULL);
INSERT INTO `open_menu` VALUES (100, 1, '0,1', '自定义用户字段', 1, 'prop', 'open/views/system/prop/index', NULL, 1, 1, 'bug', NULL, '2023-07-17 22:16:44', '2024-04-22 10:15:23', 1, NULL);
INSERT INTO `open_menu` VALUES (101, 1, '0,1', '消息列表', 1, 'message', 'open/views/system/message/index', NULL, 1, 1, 'message', NULL, '2023-07-25 20:37:35', '2023-07-25 20:37:35', 0, NULL);
INSERT INTO `open_menu` VALUES (102, 36, '0,36', '签名', 1, 'signature', 'demo/signature', NULL, 1, 1, 'menu', '', '2023-07-25 22:15:47', '2023-07-25 22:15:47', 0, NULL);
INSERT INTO `open_menu` VALUES (1706144197164838914, 89, '0,89', '流程报表', 1, '/flow/data', 'open/views/flow/flowdata', NULL, 0, 1, 'document', NULL, '2023-09-25 11:10:50', '2023-09-25 11:10:50', 0, NULL);
INSERT INTO `open_menu` VALUES (1716270348700979202, 1, '0,1', '前端版本维护', 1, 'system/version', 'open/views/system/version', NULL, 1, 1, 'language', NULL, '2023-10-23 09:48:33', '2023-10-23 09:48:33', 0, NULL);
INSERT INTO `open_menu` VALUES (1722242469999517698, 89, '0,89', '数据管理', 1, '/flow/datamanage', 'open/views/flow/data', NULL, 1, 1, 'cascader', NULL, '2023-11-08 21:19:37', '2024-04-22 10:15:12', 1, NULL);
INSERT INTO `open_menu` VALUES (1732310200470892545, 3, '0,1,3', '分配权限', 4, '', NULL, 'sys:role:perm', 1, 1, '', NULL, '2023-12-06 16:05:11', '2023-12-06 16:05:11', 0, NULL);

-- ----------------------------
-- Table structure for open_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `open_dept_user`;
CREATE TABLE `open_dept_user`  (
                                      `id` varchar(64) NOT NULL COMMENT '部门id',
                                      `dept_id`varchar(64)  NOT NULL DEFAULT '0' COMMENT '部门id',
                                      `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                      `user_id` varchar(64)  NOT NULL COMMENT 'user_id',
                                      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                      `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                      PRIMARY KEY (`id`) USING BTREE,
                                      INDEX `idx_id`(`id` ASC) USING BTREE,
                                      INDEX `idx_parent_id`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门-主管表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_dept_user
-- ----------------------------
INSERT INTO `open_dept_user` VALUES (1, '1', 0, '1', '2023-05-05 15:23:40', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (2, '2', 0, '2', '2023-05-05 21:02:30', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (3, '1', 0, '3', '2023-05-05 21:03:32', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (4, '2', 0, '4', '2023-05-05 21:04:33', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (5, '2', 0, '5', '2023-05-05 21:06:11', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (6, '2', 0, '6', '2023-05-05 22:25:45', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (7, '5', 0, '7', '2023-05-05 22:26:27', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (9, '5', 0, '9', '2023-05-07 11:40:55', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (10, '1', 0, '10', '2023-05-07 11:41:32', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (11, '1', 0, '11', '2023-05-07 11:42:31', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (12, '1', 0, '12', '2023-05-07 11:50:51', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (13, '8', 0, '13', '2023-05-07 11:53:14', '2024-03-16 12:47:22', NULL);
INSERT INTO `open_dept_user` VALUES (15, '2', 0, '15', '2023-06-10 16:35:40', '2024-03-16 12:47:22', NULL);

-- ----------------------------
-- Table structure for open_dept_leader
-- ----------------------------
DROP TABLE IF EXISTS `open_dept_leader`;
CREATE TABLE `open_dept_leader`  (
                                        `id` varchar(64) NOT NULL COMMENT '部门id',
                                        `dept_id`varchar(64)  NOT NULL DEFAULT '0' COMMENT '部门id',
                                        `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                        `user_id` varchar(64)  NOT NULL COMMENT '主管user_id',
                                        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                        `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                        `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                        PRIMARY KEY (`id`) USING BTREE,
                                        INDEX `idx_id`(`id` ASC) USING BTREE,
                                        INDEX `idx_parent_id`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门-主管表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_dept_leader
-- ----------------------------
INSERT INTO `open_dept_leader` VALUES (1, '1', 0, '1', '2024-01-04 08:16:35', '2024-01-04 08:16:35', NULL);
INSERT INTO `open_dept_leader` VALUES (2, '2', 0, '1', '2024-01-04 08:16:35', '2024-01-04 08:16:35', NULL);
INSERT INTO `open_dept_leader` VALUES (5, '5', 0, '2', '2024-01-04 08:16:35', '2024-01-04 08:16:35', NULL);
INSERT INTO `open_dept_leader` VALUES (6, '6', 0, '3', '2024-01-04 08:16:35', '2024-01-04 08:16:35', NULL);
INSERT INTO `open_dept_leader` VALUES (8, '8', 0, '3', '2024-01-04 08:16:35', '2024-01-04 08:16:35', NULL);

-- ----------------------------
-- Table structure for open_dept
-- ----------------------------
DROP TABLE IF EXISTS `open_dept`;
CREATE TABLE `open_dept`  (
                                 `id` varchar(64) NOT NULL COMMENT '部门id',
                                 `name`varchar(64)  NOT NULL COMMENT '部门名',
                                 `parent_id` bigint NOT NULL DEFAULT 0 COMMENT '上级部门id',
                                 `del_flag` tinyint(1) NOT NULL COMMENT '逻辑删除字段',
                                 `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
                                 `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                                 `status` int NOT NULL DEFAULT 1,
                                 `sort_value` int NOT NULL DEFAULT 1,
                                 `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
                                 PRIMARY KEY (`id`) USING BTREE,
                                 INDEX `idx_id`(`id` ASC) USING BTREE,
                                 INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of open_dept
-- ----------------------------
INSERT INTO `open_dept` VALUES (1, '第一个部门', 0, 0, '2023-05-05 15:22:42', '2023-05-05 15:22:43', 1, 1, NULL);
INSERT INTO `open_dept` VALUES (2, '刚刚1', 1, 0, '2023-05-05 15:30:10', '2023-05-05 15:50:30', 1, 1, NULL);
INSERT INTO `open_dept` VALUES (3, '对对对', 2, 1, '2023-05-05 15:32:17', '2023-05-05 15:45:50', 1, 1, NULL);
INSERT INTO `open_dept` VALUES (4, '后面', 1, 1, '2023-05-05 15:32:26', '2023-05-05 15:45:52', 1, 1, NULL);
INSERT INTO `open_dept` VALUES (5, '技术部1', 2, 0, '2023-05-05 22:26:07', '2023-08-13 20:52:02', 1, 2, NULL);
INSERT INTO `open_dept` VALUES (6, '后端组', 5, 0, '2023-05-05 23:07:20', '2023-05-05 23:07:20', 1, 1, NULL);
INSERT INTO `open_dept` VALUES (7, '阿斯蒂芬', 5, 1, '2023-06-10 23:13:14', '2023-06-10 23:16:00', 1, 2, NULL);
INSERT INTO `open_dept` VALUES (8, '发发发', 5, 0, '2023-07-07 00:01:54', '2023-07-07 00:01:54', 1, 3, NULL);

SET FOREIGN_KEY_CHECKS = 1;