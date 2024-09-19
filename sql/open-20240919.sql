/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1-123456
 Source Server Type    : MySQL
 Source Server Version : 80100
 Source Host           : 127.0.0.1:3306
 Source Schema         : open

 Target Server Type    : MySQL
 Target Server Version : 80100
 File Encoding         : 65001

 Date: 19/09/2024 10:44:50
*/

SET NAMES utf8mb4;
SET
FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for act_app_appdef
-- ----------------------------
DROP TABLE IF EXISTS `act_app_appdef`;
CREATE TABLE `act_app_appdef`
(
    `ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`           int(0) NOT NULL,
    `NAME_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `KEY_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `VERSION_`       int(0) NOT NULL,
    `CATEGORY_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_NAME_` varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION_`   varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_IDX_APP_DEF_UNIQ`(`KEY_`, `VERSION_`, `TENANT_ID_`) USING BTREE,
    INDEX            `ACT_IDX_APP_DEF_DPLY`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_APP_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_app_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_app_databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `act_app_databasechangelog`;
CREATE TABLE `act_app_databasechangelog`
(
    `ID`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `AUTHOR`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `FILENAME`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `DATEEXECUTED`  datetime(0) NULL,
    `ORDEREXECUTED` int(0) NOT NULL,
    `EXECTYPE`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `MD5SUM`        varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `COMMENTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TAG`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LIQUIBASE`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CONTEXTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LABELS`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_app_databasechangelog
-- ----------------------------
INSERT INTO `act_app_databasechangelog`
VALUES ('1', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2024-07-23 13:15:45', 1,
        'EXECUTED', '9:959783069c0c7ce80320a0617aa48969',
        'createTable tableName=ACT_APP_DEPLOYMENT; createTable tableName=ACT_APP_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_APP_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_APP_RSRC_DPL, referencedTableName=ACT_APP_DEPLOYMENT; createIndex...',
        '', NULL, '4.24.0', NULL, NULL, '1711745527');
INSERT INTO `act_app_databasechangelog`
VALUES ('2', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2024-07-23 13:15:45', 2,
        'EXECUTED', '9:c75407b1c0e16adf2a6db585c05a94c7',
        'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_APP_DEPLOYMENT', '', NULL, '4.24.0', NULL, NULL,
        '1711745527');
INSERT INTO `act_app_databasechangelog`
VALUES ('3', 'flowable', 'org/flowable/app/db/liquibase/flowable-app-db-changelog.xml', '2024-07-23 13:15:45', 3,
        'EXECUTED', '9:c05b79a3b00e95136533085718361208',
        'createIndex indexName=ACT_IDX_APP_DEF_UNIQ, tableName=ACT_APP_APPDEF', '', NULL, '4.24.0', NULL, NULL,
        '1711745527');

-- ----------------------------
-- Table structure for act_app_databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `act_app_databasechangeloglock`;
CREATE TABLE `act_app_databasechangeloglock`
(
    `ID`          int(0) NOT NULL,
    `LOCKED`      tinyint(1) NOT NULL,
    `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
    `LOCKEDBY`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_app_databasechangeloglock
-- ----------------------------
INSERT INTO `act_app_databasechangeloglock`
VALUES (1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for act_app_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_app_deployment`;
CREATE TABLE `act_app_deployment`
(
    `ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `KEY_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOY_TIME_` datetime(3) NULL DEFAULT NULL,
    `TENANT_ID_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_app_deployment_resource
-- ----------------------------
DROP TABLE IF EXISTS `act_app_deployment_resource`;
CREATE TABLE `act_app_deployment_resource`
(
    `ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_BYTES_` longblob NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX             `ACT_IDX_APP_RSRC_DPL`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_APP_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_app_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_casedef
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_casedef`;
CREATE TABLE `act_cmmn_casedef`
(
    `ID_`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`                    int(0) NOT NULL,
    `NAME_`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `KEY_`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `VERSION_`                int(0) NOT NULL,
    `CATEGORY_`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_NAME_`          varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION_`            varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `HAS_GRAPHICAL_NOTATION_` tinyint(1) NULL DEFAULT NULL,
    `TENANT_ID_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `DGRM_RESOURCE_NAME_`     varchar(4000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `HAS_START_FORM_KEY_`     tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_IDX_CASE_DEF_UNIQ`(`KEY_`, `VERSION_`, `TENANT_ID_`) USING BTREE,
    INDEX                     `ACT_IDX_CASE_DEF_DPLY`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_CASE_DEF_DPLY` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_cmmn_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_databasechangelog`;
CREATE TABLE `act_cmmn_databasechangelog`
(
    `ID`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `AUTHOR`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `FILENAME`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `DATEEXECUTED`  datetime(0) NULL,
    `ORDEREXECUTED` int(0) NOT NULL,
    `EXECTYPE`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `MD5SUM`        varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `COMMENTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TAG`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LIQUIBASE`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CONTEXTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LABELS`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_cmmn_databasechangelog
-- ----------------------------
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('1', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:43', 1,
        'EXECUTED', '9:d0cc0aaadf0e4ef70c5b412cd05fadc4',
        'createTable tableName=ACT_CMMN_DEPLOYMENT; createTable tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addForeignKeyConstraint baseTableName=ACT_CMMN_DEPLOYMENT_RESOURCE, constraintName=ACT_FK_CMMN_RSRC_DPL, referencedTableName=ACT_CMMN_DEPLOYMENT; create...',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('2', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:43', 2,
        'EXECUTED', '9:8095a5a8a222a100c2d0310cacbda5e7',
        'addColumn tableName=ACT_CMMN_CASEDEF; addColumn tableName=ACT_CMMN_DEPLOYMENT_RESOURCE; addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('3', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 3,
        'EXECUTED', '9:f031b4f0ae67bc5a640736b379049b12',
        'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_PLAN_ITEM_STAGE_INST, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableNam...',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('4', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 4,
        'EXECUTED', '9:c484ecfb08719feccac2f80fc962dda9',
        'createTable tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_MIL_INST; addColumn tableName=ACT_CMMN_HI_MIL_INST',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('5', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 5,
        'EXECUTED', '9:e6a67f8f0d16cd72117900442acfe6e0',
        'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_CMMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_CASE_INST; modifyDataType columnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; modifyDataType columnName=T...',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('6', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 6,
        'EXECUTED', '9:7343ab247d959e5add9278b5386de833',
        'createIndex indexName=ACT_IDX_CASE_DEF_UNIQ, tableName=ACT_CMMN_CASEDEF', '', NULL, '4.24.0', NULL, NULL,
        '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('7', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 7,
        'EXECUTED', '9:d73200db684b6cdb748cc03570d5d2e9',
        'renameColumn newColumnName=CREATE_TIME_, oldColumnName=START_TIME_, tableName=ACT_CMMN_RU_PLAN_ITEM_INST; renameColumn newColumnName=CREATE_TIME_, oldColumnName=CREATED_TIME_, tableName=ACT_CMMN_HI_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_RU_P...',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('8', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:44', 8,
        'EXECUTED', '9:eda5e43816221f2d8554bfcc90f1c37e', 'addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('9', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 9,
        'EXECUTED', '9:c34685611779075a73caf8c380f078ea',
        'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('10', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 10,
        'EXECUTED', '9:368e9472ad2348206205170d6c52d58e',
        'addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_RU_CASE_INST; createIndex indexName=ACT_IDX_CASE_INST_REF_ID_, tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE...',
        '', NULL, '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('11', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 11,
        'EXECUTED', '9:e54b50ceb2bcd5355ae4dfb56d9ff3ad',
        'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('12', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 12,
        'EXECUTED', '9:f53f262768d04e74529f43fcd93429b0', 'addColumn tableName=ACT_CMMN_RU_CASE_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('13', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 13,
        'EXECUTED', '9:64e7eafbe97997094654e83caea99895',
        'addColumn tableName=ACT_CMMN_RU_PLAN_ITEM_INST; addColumn tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('14', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 14,
        'EXECUTED', '9:ab7d934abde497eac034701542e0a281',
        'addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST', '', NULL, '4.24.0',
        NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('16', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 15,
        'EXECUTED', '9:03928d422e510959770e7a9daa5a993f',
        'addColumn tableName=ACT_CMMN_RU_CASE_INST; addColumn tableName=ACT_CMMN_HI_CASE_INST', '', NULL, '4.24.0',
        NULL, NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('17', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 16,
        'EXECUTED', '9:f30304cf001d6eac78c793ea88cd5781',
        'createIndex indexName=ACT_IDX_HI_CASE_INST_END, tableName=ACT_CMMN_HI_CASE_INST', '', NULL, '4.24.0', NULL,
        NULL, '1711743012');
INSERT INTO `act_cmmn_databasechangelog`
VALUES ('18', 'flowable', 'org/flowable/cmmn/db/liquibase/flowable-cmmn-db-changelog.xml', '2024-07-23 13:15:45', 17,
        'EXECUTED', '9:d782865087d6c0c3dc033ac20e783008',
        'createIndex indexName=ACT_IDX_HI_PLAN_ITEM_INST_CASE, tableName=ACT_CMMN_HI_PLAN_ITEM_INST', '', NULL,
        '4.24.0', NULL, NULL, '1711743012');

-- ----------------------------
-- Table structure for act_cmmn_databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_databasechangeloglock`;
CREATE TABLE `act_cmmn_databasechangeloglock`
(
    `ID`          int(0) NOT NULL,
    `LOCKED`      tinyint(1) NOT NULL,
    `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
    `LOCKEDBY`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_cmmn_databasechangeloglock
-- ----------------------------
INSERT INTO `act_cmmn_databasechangeloglock`
VALUES (1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for act_cmmn_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_deployment`;
CREATE TABLE `act_cmmn_deployment`
(
    `ID_`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `KEY_`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOY_TIME_`          datetime(3) NULL DEFAULT NULL,
    `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_deployment_resource
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_deployment_resource`;
CREATE TABLE `act_cmmn_deployment_resource`
(
    `ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_BYTES_` longblob NULL,
    `GENERATED_`      tinyint(1) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX             `ACT_IDX_CMMN_RSRC_DPL`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_CMMN_RSRC_DPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_cmmn_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_hi_case_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_hi_case_inst`;
CREATE TABLE `act_cmmn_hi_case_inst`
(
    `ID_`                        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`                       int(0) NOT NULL,
    `BUSINESS_KEY_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `NAME_`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `PARENT_ID_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_DEF_ID_`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STATE_`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `START_TIME_`                datetime(3) NULL DEFAULT NULL,
    `END_TIME_`                  datetime(3) NULL DEFAULT NULL,
    `START_USER_ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CALLBACK_ID_`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CALLBACK_TYPE_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `REFERENCE_ID_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_TYPE_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LAST_REACTIVATION_TIME_`    datetime(3) NULL DEFAULT NULL,
    `LAST_REACTIVATION_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `BUSINESS_STATUS_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                        `ACT_IDX_HI_CASE_INST_END`(`END_TIME_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_hi_mil_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_hi_mil_inst`;
CREATE TABLE `act_cmmn_hi_mil_inst`
(
    `ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`          int(0) NOT NULL,
    `NAME_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `TIME_STAMP_`   datetime(3) NULL DEFAULT NULL,
    `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `CASE_DEF_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `ELEMENT_ID_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `TENANT_ID_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_hi_plan_item_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_hi_plan_item_inst`;
CREATE TABLE `act_cmmn_hi_plan_item_inst`
(
    `ID_`                    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`                   int(0) NOT NULL,
    `NAME_`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STATE_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_DEF_ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_INST_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STAGE_INST_ID_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `IS_STAGE_`              tinyint(1) NULL DEFAULT NULL,
    `ELEMENT_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ITEM_DEFINITION_ID_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ITEM_DEFINITION_TYPE_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CREATE_TIME_`           datetime(3) NULL DEFAULT NULL,
    `LAST_AVAILABLE_TIME_`   datetime(3) NULL DEFAULT NULL,
    `LAST_ENABLED_TIME_`     datetime(3) NULL DEFAULT NULL,
    `LAST_DISABLED_TIME_`    datetime(3) NULL DEFAULT NULL,
    `LAST_STARTED_TIME_`     datetime(3) NULL DEFAULT NULL,
    `LAST_SUSPENDED_TIME_`   datetime(3) NULL DEFAULT NULL,
    `COMPLETED_TIME_`        datetime(3) NULL DEFAULT NULL,
    `OCCURRED_TIME_`         datetime(3) NULL DEFAULT NULL,
    `TERMINATED_TIME_`       datetime(3) NULL DEFAULT NULL,
    `EXIT_TIME_`             datetime(3) NULL DEFAULT NULL,
    `ENDED_TIME_`            datetime(3) NULL DEFAULT NULL,
    `LAST_UPDATED_TIME_`     datetime(3) NULL DEFAULT NULL,
    `START_USER_ID_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_TYPE_`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `ENTRY_CRITERION_ID_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `EXIT_CRITERION_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `SHOW_IN_OVERVIEW_`      tinyint(1) NULL DEFAULT NULL,
    `EXTRA_VALUE_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DERIVED_CASE_DEF_ID_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LAST_UNAVAILABLE_TIME_` datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                    `ACT_IDX_HI_PLAN_ITEM_INST_CASE`(`CASE_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_ru_case_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_ru_case_inst`;
CREATE TABLE `act_cmmn_ru_case_inst`
(
    `ID_`                        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`                       int(0) NOT NULL,
    `BUSINESS_KEY_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `NAME_`                      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `PARENT_ID_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_DEF_ID_`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STATE_`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `START_TIME_`                datetime(3) NULL DEFAULT NULL,
    `START_USER_ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CALLBACK_ID_`               varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CALLBACK_TYPE_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `LOCK_TIME_`                 datetime(3) NULL DEFAULT NULL,
    `IS_COMPLETEABLE_`           tinyint(1) NULL DEFAULT NULL,
    `REFERENCE_ID_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_TYPE_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LOCK_OWNER_`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LAST_REACTIVATION_TIME_`    datetime(3) NULL DEFAULT NULL,
    `LAST_REACTIVATION_USER_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `BUSINESS_STATUS_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                        `ACT_IDX_CASE_INST_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
    INDEX                        `ACT_IDX_CASE_INST_PARENT`(`PARENT_ID_`) USING BTREE,
    INDEX                        `ACT_IDX_CASE_INST_REF_ID_`(`REFERENCE_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_CASE_INST_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_ru_mil_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_ru_mil_inst`;
CREATE TABLE `act_cmmn_ru_mil_inst`
(
    `ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `TIME_STAMP_`   datetime(3) NULL DEFAULT NULL,
    `CASE_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `CASE_DEF_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `ELEMENT_ID_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `TENANT_ID_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX           `ACT_IDX_MIL_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
    INDEX           `ACT_IDX_MIL_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_MIL_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_MIL_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_ru_plan_item_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_ru_plan_item_inst`;
CREATE TABLE `act_cmmn_ru_plan_item_inst`
(
    `ID_`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`                    int(0) NOT NULL,
    `CASE_DEF_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_INST_ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STAGE_INST_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `IS_STAGE_`               tinyint(1) NULL DEFAULT NULL,
    `ELEMENT_ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `NAME_`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `STATE_`                  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CREATE_TIME_`            datetime(3) NULL DEFAULT NULL,
    `START_USER_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `REFERENCE_TYPE_`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '',
    `ITEM_DEFINITION_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ITEM_DEFINITION_TYPE_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `IS_COMPLETEABLE_`        tinyint(1) NULL DEFAULT NULL,
    `IS_COUNT_ENABLED_`       tinyint(1) NULL DEFAULT NULL,
    `VAR_COUNT_`              int(0) NULL DEFAULT NULL,
    `SENTRY_PART_INST_COUNT_` int(0) NULL DEFAULT NULL,
    `LAST_AVAILABLE_TIME_`    datetime(3) NULL DEFAULT NULL,
    `LAST_ENABLED_TIME_`      datetime(3) NULL DEFAULT NULL,
    `LAST_DISABLED_TIME_`     datetime(3) NULL DEFAULT NULL,
    `LAST_STARTED_TIME_`      datetime(3) NULL DEFAULT NULL,
    `LAST_SUSPENDED_TIME_`    datetime(3) NULL DEFAULT NULL,
    `COMPLETED_TIME_`         datetime(3) NULL DEFAULT NULL,
    `OCCURRED_TIME_`          datetime(3) NULL DEFAULT NULL,
    `TERMINATED_TIME_`        datetime(3) NULL DEFAULT NULL,
    `EXIT_TIME_`              datetime(3) NULL DEFAULT NULL,
    `ENDED_TIME_`             datetime(3) NULL DEFAULT NULL,
    `ENTRY_CRITERION_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `EXIT_CRITERION_ID_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `EXTRA_VALUE_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DERIVED_CASE_DEF_ID_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LAST_UNAVAILABLE_TIME_`  datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                     `ACT_IDX_PLAN_ITEM_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
    INDEX                     `ACT_IDX_PLAN_ITEM_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
    INDEX                     `ACT_IDX_PLAN_ITEM_STAGE_INST`(`STAGE_INST_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_PLAN_ITEM_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_cmmn_ru_sentry_part_inst
-- ----------------------------
DROP TABLE IF EXISTS `act_cmmn_ru_sentry_part_inst`;
CREATE TABLE `act_cmmn_ru_sentry_part_inst`
(
    `ID_`                varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `REV_`               int(0) NOT NULL,
    `CASE_DEF_ID_`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CASE_INST_ID_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `PLAN_ITEM_INST_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ON_PART_ID_`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `IF_PART_ID_`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TIME_STAMP_`        datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                `ACT_IDX_SENTRY_CASE_DEF`(`CASE_DEF_ID_`) USING BTREE,
    INDEX                `ACT_IDX_SENTRY_CASE_INST`(`CASE_INST_ID_`) USING BTREE,
    INDEX                `ACT_IDX_SENTRY_PLAN_ITEM`(`PLAN_ITEM_INST_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_SENTRY_CASE_DEF` FOREIGN KEY (`CASE_DEF_ID_`) REFERENCES `act_cmmn_casedef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SENTRY_CASE_INST` FOREIGN KEY (`CASE_INST_ID_`) REFERENCES `act_cmmn_ru_case_inst` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SENTRY_PLAN_ITEM` FOREIGN KEY (`PLAN_ITEM_INST_ID_`) REFERENCES `act_cmmn_ru_plan_item_inst` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_dmn_databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_databasechangelog`;
CREATE TABLE `act_dmn_databasechangelog`
(
    `ID`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `AUTHOR`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `FILENAME`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `DATEEXECUTED`  datetime(0) NULL,
    `ORDEREXECUTED` int(0) NOT NULL,
    `EXECTYPE`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `MD5SUM`        varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `COMMENTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TAG`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LIQUIBASE`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CONTEXTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LABELS`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_dmn_databasechangelog
-- ----------------------------
INSERT INTO `act_dmn_databasechangelog`
VALUES ('1', 'activiti', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 1,
        'EXECUTED', '9:5b36e70aee5a2e42f6e7a62ea5fa681b',
        'createTable tableName=ACT_DMN_DEPLOYMENT; createTable tableName=ACT_DMN_DEPLOYMENT_RESOURCE; createTable tableName=ACT_DMN_DECISION_TABLE',
        '', NULL, '4.24.0', NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('2', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 2,
        'EXECUTED', '9:fd13fa3f7af55d2b72f763fc261da30d', 'createTable tableName=ACT_DMN_HI_DECISION_EXECUTION', '',
        NULL, '4.24.0', NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('3', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 3,
        'EXECUTED', '9:9f30e6a3557d4b4c713dbb2dcc141782', 'addColumn tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL,
        '4.24.0', NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('4', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 4,
        'EXECUTED', '9:41085fbde807dba96104ee75a2fcc4cc',
        'dropColumn columnName=PARENT_DEPLOYMENT_ID_, tableName=ACT_DMN_DECISION_TABLE', '', NULL, '4.24.0', NULL, NULL,
        '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('5', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 5,
        'EXECUTED', '9:169d906b6503ad6907b7e5cd0d70d004',
        'modifyDataType columnName=DEPLOY_TIME_, tableName=ACT_DMN_DEPLOYMENT; modifyDataType columnName=START_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION; modifyDataType columnName=END_TIME_, tableName=ACT_DMN_HI_DECISION_EXECUTION',
        '', NULL, '4.24.0', NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('6', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 6,
        'EXECUTED', '9:f00f92f3ef1af3fc1604f0323630f9b1',
        'createIndex indexName=ACT_IDX_DEC_TBL_UNIQ, tableName=ACT_DMN_DECISION_TABLE', '', NULL, '4.24.0', NULL, NULL,
        '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('7', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 7,
        'EXECUTED', '9:d24d4c5f44083b4edf1231a7a682a2cd',
        'dropIndex indexName=ACT_IDX_DEC_TBL_UNIQ, tableName=ACT_DMN_DECISION_TABLE; renameTable newTableName=ACT_DMN_DECISION, oldTableName=ACT_DMN_DECISION_TABLE; createIndex indexName=ACT_IDX_DMN_DEC_UNIQ, tableName=ACT_DMN_DECISION',
        '', NULL, '4.24.0', NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('8', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 8,
        'EXECUTED', '9:3998ef0958b46fe9c19458183952d2a0', 'addColumn tableName=ACT_DMN_DECISION', '', NULL, '4.24.0',
        NULL, NULL, '1711741952');
INSERT INTO `act_dmn_databasechangelog`
VALUES ('9', 'flowable', 'org/flowable/dmn/db/liquibase/flowable-dmn-db-changelog.xml', '2024-07-23 13:15:42', 9,
        'EXECUTED', '9:5c9dc65601456faa1aa12f8d3afe0e9e',
        'createIndex indexName=ACT_IDX_DMN_INSTANCE_ID, tableName=ACT_DMN_HI_DECISION_EXECUTION', '', NULL, '4.24.0',
        NULL, NULL, '1711741952');

-- ----------------------------
-- Table structure for act_dmn_databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_databasechangeloglock`;
CREATE TABLE `act_dmn_databasechangeloglock`
(
    `ID`          int(0) NOT NULL,
    `LOCKED`      tinyint(1) NOT NULL,
    `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
    `LOCKEDBY`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_dmn_databasechangeloglock
-- ----------------------------
INSERT INTO `act_dmn_databasechangeloglock`
VALUES (1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for act_dmn_decision
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_decision`;
CREATE TABLE `act_dmn_decision`
(
    `ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `VERSION_`       int(0) NULL DEFAULT NULL,
    `KEY_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DECISION_TYPE_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_IDX_DMN_DEC_UNIQ`(`KEY_`, `VERSION_`, `TENANT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_dmn_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_deployment`;
CREATE TABLE `act_dmn_deployment`
(
    `ID_`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOY_TIME_`          datetime(3) NULL DEFAULT NULL,
    `TENANT_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_dmn_deployment_resource
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_deployment_resource`;
CREATE TABLE `act_dmn_deployment_resource`
(
    `ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_BYTES_` longblob NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_dmn_hi_decision_execution
-- ----------------------------
DROP TABLE IF EXISTS `act_dmn_hi_decision_execution`;
CREATE TABLE `act_dmn_hi_decision_execution`
(
    `ID_`                     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `DECISION_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `START_TIME_`             datetime(3) NULL DEFAULT NULL,
    `END_TIME_`               datetime(3) NULL DEFAULT NULL,
    `INSTANCE_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `EXECUTION_ID_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `ACTIVITY_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `FAILED_`                 tinyint(1) NULL DEFAULT 0,
    `TENANT_ID_`              varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `EXECUTION_JSON_`         longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL,
    `SCOPE_TYPE_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                     `ACT_IDX_DMN_INSTANCE_ID`(`INSTANCE_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_evt_log
-- ----------------------------
DROP TABLE IF EXISTS `act_evt_log`;
CREATE TABLE `act_evt_log`
(
    `LOG_NR_`       bigint(0) NOT NULL AUTO_INCREMENT,
    `TYPE_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TIME_STAMP_`   timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `USER_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DATA_`         longblob NULL,
    `LOCK_OWNER_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `LOCK_TIME_`    timestamp(3) NULL DEFAULT NULL,
    `IS_PROCESSED_` tinyint(0) NULL DEFAULT 0,
    PRIMARY KEY (`LOG_NR_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ge_bytearray
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_bytearray`;
CREATE TABLE `act_ge_bytearray`
(
    `ID_`            varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`           int(0) NULL DEFAULT NULL,
    `NAME_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DEPLOYMENT_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BYTES_`         longblob NULL,
    `GENERATED_`     tinyint(0) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX            `ACT_FK_BYTEARR_DEPL`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_BYTEARR_DEPL` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ge_property
-- ----------------------------
DROP TABLE IF EXISTS `act_ge_property`;
CREATE TABLE `act_ge_property`
(
    `NAME_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `VALUE_` varchar(300) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REV_`   int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`NAME_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of act_ge_property
-- ----------------------------
INSERT INTO `act_ge_property`
VALUES ('batch.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('cfg.execution-related-entities-count', 'true', 1);
INSERT INTO `act_ge_property`
VALUES ('cfg.task-related-entities-count', 'true', 1);
INSERT INTO `act_ge_property`
VALUES ('common.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('entitylink.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('eventsubscription.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('identitylink.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('job.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('next.dbid', '1', 1);
INSERT INTO `act_ge_property`
VALUES ('schema.history', 'create(7.0.1.1)', 1);
INSERT INTO `act_ge_property`
VALUES ('schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('task.schema.version', '7.0.1.1', 1);
INSERT INTO `act_ge_property`
VALUES ('variable.schema.version', '7.0.1.1', 1);

-- ----------------------------
-- Table structure for act_hi_actinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_actinst`;
CREATE TABLE `act_hi_actinst`
(
    `ID_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`               int(0) NULL DEFAULT 1,
    `PROC_DEF_ID_`       varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `PROC_INST_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `EXECUTION_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `ACT_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `TASK_ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CALL_PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_NAME_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `ASSIGNEE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_TIME_`        datetime(3) NULL,
    `END_TIME_`          datetime(3) NULL DEFAULT NULL,
    `TRANSACTION_ORDER_` int(0) NULL DEFAULT NULL,
    `DURATION_`          bigint(0) NULL DEFAULT NULL,
    `DELETE_REASON_`     varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                `ACT_IDX_HI_ACT_INST_START`(`START_TIME_`) USING BTREE,
    INDEX                `ACT_IDX_HI_ACT_INST_END`(`END_TIME_`) USING BTREE,
    INDEX                `ACT_IDX_HI_ACT_INST_PROCINST`(`PROC_INST_ID_`, `ACT_ID_`) USING BTREE,
    INDEX                `ACT_IDX_HI_ACT_INST_EXEC`(`EXECUTION_ID_`, `ACT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_attachment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_attachment`;
CREATE TABLE `act_hi_attachment`
(
    `ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`          int(0) NULL DEFAULT NULL,
    `USER_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DESCRIPTION_`  varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `URL_`          varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CONTENT_ID_`   varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TIME_`         datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_comment
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_comment`;
CREATE TABLE `act_hi_comment`
(
    `ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TIME_`         datetime(3) NULL,
    `USER_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACTION_`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `MESSAGE_`      varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `FULL_MSG_`     longblob NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_detail
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_detail`;
CREATE TABLE `act_hi_detail`
(
    `ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_INST_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `VAR_TYPE_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REV_`          int(0) NULL DEFAULT NULL,
    `TIME_`         datetime(3) NULL,
    `BYTEARRAY_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DOUBLE_` double NULL DEFAULT NULL,
    `LONG_`         bigint(0) NULL DEFAULT NULL,
    `TEXT_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TEXT2_`        varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX           `ACT_IDX_HI_DETAIL_PROC_INST`(`PROC_INST_ID_`) USING BTREE,
    INDEX           `ACT_IDX_HI_DETAIL_ACT_INST`(`ACT_INST_ID_`) USING BTREE,
    INDEX           `ACT_IDX_HI_DETAIL_TIME`(`TIME_`) USING BTREE,
    INDEX           `ACT_IDX_HI_DETAIL_NAME`(`NAME_`) USING BTREE,
    INDEX           `ACT_IDX_HI_DETAIL_TASK_ID`(`TASK_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_entitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_entitylink`;
CREATE TABLE `act_hi_entitylink`
(
    `ID_`                      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `LINK_TYPE_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`             datetime(3) NULL DEFAULT NULL,
    `SCOPE_ID_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_ELEMENT_ID_`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ROOT_SCOPE_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ROOT_SCOPE_TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HIERARCHY_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                      `ACT_IDX_HI_ENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_HI_ENT_LNK_REF_SCOPE`(`REF_SCOPE_ID_`, `REF_SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_HI_ENT_LNK_ROOT_SCOPE`(`ROOT_SCOPE_ID_`, `ROOT_SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_HI_ENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_identitylink`;
CREATE TABLE `act_hi_identitylink`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `GROUP_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `USER_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         datetime(3) NULL DEFAULT NULL,
    `PROC_INST_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_USER`(`USER_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_TASK`(`TASK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_HI_IDENT_LNK_PROCINST`(`PROC_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_procinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_procinst`;
CREATE TABLE `act_hi_procinst`
(
    `ID_`                        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                       int(0) NULL DEFAULT 1,
    `PROC_INST_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `BUSINESS_KEY_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`               varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `START_TIME_`                datetime(3) NULL,
    `END_TIME_`                  datetime(3) NULL DEFAULT NULL,
    `DURATION_`                  bigint(0) NULL DEFAULT NULL,
    `START_USER_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_ACT_ID_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `END_ACT_ID_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUPER_PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DELETE_REASON_`             varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `NAME_`                      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CALLBACK_ID_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CALLBACK_TYPE_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REFERENCE_ID_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REFERENCE_TYPE_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROPAGATED_STAGE_INST_ID_`  varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BUSINESS_STATUS_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `PROC_INST_ID_`(`PROC_INST_ID_`) USING BTREE,
    INDEX                        `ACT_IDX_HI_PRO_INST_END`(`END_TIME_`) USING BTREE,
    INDEX                        `ACT_IDX_HI_PRO_I_BUSKEY`(`BUSINESS_KEY_`) USING BTREE,
    INDEX                        `ACT_IDX_HI_PRO_SUPER_PROCINST`(`SUPER_PROCESS_INSTANCE_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_taskinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_taskinst`;
CREATE TABLE `act_hi_taskinst`
(
    `ID_`                       varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                      int(0) NULL DEFAULT 1,
    `PROC_DEF_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_DEF_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_DEF_KEY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROPAGATED_STAGE_INST_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `STATE_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`                     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_TASK_ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DESCRIPTION_`              varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `OWNER_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ASSIGNEE_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_TIME_`               datetime(3) NULL,
    `IN_PROGRESS_TIME_`         datetime(3) NULL DEFAULT NULL,
    `IN_PROGRESS_STARTED_BY_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CLAIM_TIME_`               datetime(3) NULL DEFAULT NULL,
    `CLAIMED_BY_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUSPENDED_TIME_`           datetime(3) NULL DEFAULT NULL,
    `SUSPENDED_BY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `END_TIME_`                 datetime(3) NULL DEFAULT NULL,
    `COMPLETED_BY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DURATION_`                 bigint(0) NULL DEFAULT NULL,
    `DELETE_REASON_`            varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PRIORITY_`                 int(0) NULL DEFAULT NULL,
    `IN_PROGRESS_DUE_DATE_`     datetime(3) NULL DEFAULT NULL,
    `DUE_DATE_`                 datetime(3) NULL DEFAULT NULL,
    `FORM_KEY_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CATEGORY_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `LAST_UPDATED_TIME_`        datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                       `ACT_IDX_HI_TASK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_IDX_HI_TASK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_IDX_HI_TASK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_IDX_HI_TASK_INST_PROCINST`(`PROC_INST_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_tsk_log
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_tsk_log`;
CREATE TABLE `act_hi_tsk_log`
(
    `ID_`                  bigint(0) NOT NULL AUTO_INCREMENT,
    `TYPE_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `TIME_STAMP_`          timestamp(3) NULL,
    `USER_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DATA_`                varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_hi_varinst
-- ----------------------------
DROP TABLE IF EXISTS `act_hi_varinst`;
CREATE TABLE `act_hi_varinst`
(
    `ID_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`               int(0) NULL DEFAULT 1,
    `PROC_INST_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `VAR_TYPE_`          varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BYTEARRAY_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DOUBLE_` double NULL DEFAULT NULL,
    `LONG_`              bigint(0) NULL DEFAULT NULL,
    `TEXT_`              varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TEXT2_`             varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `META_INFO_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`       datetime(3) NULL DEFAULT NULL,
    `LAST_UPDATED_TIME_` datetime(3) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                `ACT_IDX_HI_PROCVAR_NAME_TYPE`(`NAME_`, `VAR_TYPE_`) USING BTREE,
    INDEX                `ACT_IDX_HI_VAR_SCOPE_ID_TYPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                `ACT_IDX_HI_VAR_SUB_ID_TYPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                `ACT_IDX_HI_PROCVAR_PROC_INST`(`PROC_INST_ID_`) USING BTREE,
    INDEX                `ACT_IDX_HI_PROCVAR_TASK_ID`(`TASK_ID_`) USING BTREE,
    INDEX                `ACT_IDX_HI_PROCVAR_EXE`(`EXECUTION_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_procdef_info
-- ----------------------------
DROP TABLE IF EXISTS `act_procdef_info`;
CREATE TABLE `act_procdef_info`
(
    `ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `PROC_DEF_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`          int(0) NULL DEFAULT NULL,
    `INFO_JSON_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_UNIQ_INFO_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
    INDEX           `ACT_IDX_INFO_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
    INDEX           `ACT_FK_INFO_JSON_BA`(`INFO_JSON_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_INFO_JSON_BA` FOREIGN KEY (`INFO_JSON_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_INFO_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_deployment
-- ----------------------------
DROP TABLE IF EXISTS `act_re_deployment`;
CREATE TABLE `act_re_deployment`
(
    `ID_`                   varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `NAME_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CATEGORY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `KEY_`                  varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `DEPLOY_TIME_`          timestamp(3) NULL DEFAULT NULL,
    `DERIVED_FROM_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DERIVED_FROM_ROOT_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ENGINE_VERSION_`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_model
-- ----------------------------
DROP TABLE IF EXISTS `act_re_model`;
CREATE TABLE `act_re_model`
(
    `ID_`                           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                          int(0) NULL DEFAULT NULL,
    `NAME_`                         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `KEY_`                          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CATEGORY_`                     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`                  timestamp(3) NULL DEFAULT NULL,
    `LAST_UPDATE_TIME_`             timestamp(3) NULL DEFAULT NULL,
    `VERSION_`                      int(0) NULL DEFAULT NULL,
    `META_INFO_`                    varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EDITOR_SOURCE_VALUE_ID_`       varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EDITOR_SOURCE_EXTRA_VALUE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                           `ACT_FK_MODEL_SOURCE`(`EDITOR_SOURCE_VALUE_ID_`) USING BTREE,
    INDEX                           `ACT_FK_MODEL_SOURCE_EXTRA`(`EDITOR_SOURCE_EXTRA_VALUE_ID_`) USING BTREE,
    INDEX                           `ACT_FK_MODEL_DEPLOYMENT`(`DEPLOYMENT_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_MODEL_DEPLOYMENT` FOREIGN KEY (`DEPLOYMENT_ID_`) REFERENCES `act_re_deployment` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_MODEL_SOURCE` FOREIGN KEY (`EDITOR_SOURCE_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_MODEL_SOURCE_EXTRA` FOREIGN KEY (`EDITOR_SOURCE_EXTRA_VALUE_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_re_procdef
-- ----------------------------
DROP TABLE IF EXISTS `act_re_procdef`;
CREATE TABLE `act_re_procdef`
(
    `ID_`                     varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                    int(0) NULL DEFAULT NULL,
    `CATEGORY_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`                   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `KEY_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `VERSION_`                int(0) NOT NULL,
    `DEPLOYMENT_ID_`          varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RESOURCE_NAME_`          varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DGRM_RESOURCE_NAME_`     varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DESCRIPTION_`            varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HAS_START_FORM_KEY_`     tinyint(0) NULL DEFAULT NULL,
    `HAS_GRAPHICAL_NOTATION_` tinyint(0) NULL DEFAULT NULL,
    `SUSPENSION_STATE_`       int(0) NULL DEFAULT NULL,
    `TENANT_ID_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `ENGINE_VERSION_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DERIVED_FROM_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DERIVED_FROM_ROOT_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DERIVED_VERSION_`        int(0) NOT NULL DEFAULT 0,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_UNIQ_PROCDEF`(`KEY_`, `VERSION_`, `DERIVED_VERSION_`, `TENANT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_actinst
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_actinst`;
CREATE TABLE `act_ru_actinst`
(
    `ID_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`               int(0) NULL DEFAULT 1,
    `PROC_DEF_ID_`       varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `PROC_INST_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `EXECUTION_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `ACT_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `TASK_ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CALL_PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_NAME_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `ASSIGNEE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_TIME_`        datetime(3) NULL,
    `END_TIME_`          datetime(3) NULL DEFAULT NULL,
    `DURATION_`          bigint(0) NULL DEFAULT NULL,
    `TRANSACTION_ORDER_` int(0) NULL DEFAULT NULL,
    `DELETE_REASON_`     varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_START`(`START_TIME_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_END`(`END_TIME_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_PROC`(`PROC_INST_ID_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_PROC_ACT`(`PROC_INST_ID_`, `ACT_ID_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_EXEC`(`EXECUTION_ID_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_EXEC_ACT`(`EXECUTION_ID_`, `ACT_ID_`) USING BTREE,
    INDEX                `ACT_IDX_RU_ACTI_TASK`(`TASK_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_deadletter_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_deadletter_job`;
CREATE TABLE `act_ru_deadletter_job`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `CATEGORY_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `EXCLUSIVE_`           tinyint(1) NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_NAME_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CORRELATION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DUEDATE_`             timestamp(3) NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         timestamp(3) NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_DEADLETTER_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_DEADLETTER_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_DEADLETTER_JOB_CORRELATION_ID`(`CORRELATION_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_DJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_DJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_DJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_FK_DEADLETTER_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
    INDEX                  `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
    INDEX                  `ACT_FK_DEADLETTER_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_DEADLETTER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_entitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_entitylink`;
CREATE TABLE `act_ru_entitylink`
(
    `ID_`                      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                     int(0) NULL DEFAULT NULL,
    `CREATE_TIME_`             datetime(3) NULL DEFAULT NULL,
    `LINK_TYPE_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_ELEMENT_ID_`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REF_SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ROOT_SCOPE_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ROOT_SCOPE_TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HIERARCHY_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                      `ACT_IDX_ENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_ENT_LNK_REF_SCOPE`(`REF_SCOPE_ID_`, `REF_SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_ENT_LNK_ROOT_SCOPE`(`ROOT_SCOPE_ID_`, `ROOT_SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE,
    INDEX                      `ACT_IDX_ENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`, `LINK_TYPE_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_event_subscr
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_event_subscr`;
CREATE TABLE `act_ru_event_subscr`
(
    `ID_`                   varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                  int(0) NULL DEFAULT NULL,
    `EVENT_TYPE_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `EVENT_NAME_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXECUTION_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACTIVITY_ID_`          varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CONFIGURATION_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATED_`              timestamp(3)                                           NOT NULL DEFAULT CURRENT_TIMESTAMP(3),
    `PROC_DEF_ID_`          varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_KEY_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `LOCK_TIME_`            timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                   `ACT_IDX_EVENT_SUBSCR_CONFIG_`(`CONFIGURATION_`) USING BTREE,
    INDEX                   `ACT_IDX_EVENT_SUBSCR_SCOPEREF_`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                   `ACT_FK_EVENT_EXEC`(`EXECUTION_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_EVENT_EXEC` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_execution
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_execution`;
CREATE TABLE `act_ru_execution`
(
    `ID_`                        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                       int(0) NULL DEFAULT NULL,
    `PROC_INST_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BUSINESS_KEY_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_ID_`                 varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`               varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUPER_EXEC_`                varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ROOT_PROC_INST_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ACT_ID_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `IS_ACTIVE_`                 tinyint(0) NULL DEFAULT NULL,
    `IS_CONCURRENT_`             tinyint(0) NULL DEFAULT NULL,
    `IS_SCOPE_`                  tinyint(0) NULL DEFAULT NULL,
    `IS_EVENT_SCOPE_`            tinyint(0) NULL DEFAULT NULL,
    `IS_MI_ROOT_`                tinyint(0) NULL DEFAULT NULL,
    `SUSPENSION_STATE_`          int(0) NULL DEFAULT NULL,
    `CACHED_ENT_STATE_`          int(0) NULL DEFAULT NULL,
    `TENANT_ID_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `NAME_`                      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_ACT_ID_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `START_TIME_`                datetime(3) NULL DEFAULT NULL,
    `START_USER_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `LOCK_TIME_`                 timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `IS_COUNT_ENABLED_`          tinyint(0) NULL DEFAULT NULL,
    `EVT_SUBSCR_COUNT_`          int(0) NULL DEFAULT NULL,
    `TASK_COUNT_`                int(0) NULL DEFAULT NULL,
    `JOB_COUNT_`                 int(0) NULL DEFAULT NULL,
    `TIMER_JOB_COUNT_`           int(0) NULL DEFAULT NULL,
    `SUSP_JOB_COUNT_`            int(0) NULL DEFAULT NULL,
    `DEADLETTER_JOB_COUNT_`      int(0) NULL DEFAULT NULL,
    `EXTERNAL_WORKER_JOB_COUNT_` int(0) NULL DEFAULT NULL,
    `VAR_COUNT_`                 int(0) NULL DEFAULT NULL,
    `ID_LINK_COUNT_`             int(0) NULL DEFAULT NULL,
    `CALLBACK_ID_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CALLBACK_TYPE_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REFERENCE_ID_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `REFERENCE_TYPE_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROPAGATED_STAGE_INST_ID_`  varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BUSINESS_STATUS_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                        `ACT_IDX_EXEC_BUSKEY`(`BUSINESS_KEY_`) USING BTREE,
    INDEX                        `ACT_IDC_EXEC_ROOT`(`ROOT_PROC_INST_ID_`) USING BTREE,
    INDEX                        `ACT_IDX_EXEC_REF_ID_`(`REFERENCE_ID_`) USING BTREE,
    INDEX                        `ACT_FK_EXE_PROCINST`(`PROC_INST_ID_`) USING BTREE,
    INDEX                        `ACT_FK_EXE_PARENT`(`PARENT_ID_`) USING BTREE,
    INDEX                        `ACT_FK_EXE_SUPER`(`SUPER_EXEC_`) USING BTREE,
    INDEX                        `ACT_FK_EXE_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_EXE_PARENT` FOREIGN KEY (`PARENT_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_EXE_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_EXE_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT `ACT_FK_EXE_SUPER` FOREIGN KEY (`SUPER_EXEC_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_external_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_external_job`;
CREATE TABLE `act_ru_external_job`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `CATEGORY_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `LOCK_EXP_TIME_`       timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCLUSIVE_`           tinyint(1) NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_NAME_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CORRELATION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RETRIES_`             int(0) NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DUEDATE_`             timestamp(3) NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         timestamp(3) NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_EXTERNAL_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_EXTERNAL_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_EXTERNAL_JOB_CORRELATION_ID`(`CORRELATION_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_EJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_EJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_EJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    CONSTRAINT `ACT_FK_EXTERNAL_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_EXTERNAL_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_history_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_history_job`;
CREATE TABLE `act_ru_history_job`
(
    `ID_`                 varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                int(0) NULL DEFAULT NULL,
    `LOCK_EXP_TIME_`      timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RETRIES_`            int(0) NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`      varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`       varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`        varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`   varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ADV_HANDLER_CFG_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`        timestamp(3) NULL DEFAULT NULL,
    `SCOPE_TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_identitylink
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_identitylink`;
CREATE TABLE `act_ru_identitylink`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `GROUP_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `USER_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_IDENT_LNK_USER`(`USER_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_IDENT_LNK_GROUP`(`GROUP_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_IDENT_LNK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_IDENT_LNK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_IDENT_LNK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_ATHRZ_PROCEDEF`(`PROC_DEF_ID_`) USING BTREE,
    INDEX                  `ACT_FK_TSKASS_TASK`(`TASK_ID_`) USING BTREE,
    INDEX                  `ACT_FK_IDL_PROCINST`(`PROC_INST_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_ATHRZ_PROCEDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_IDL_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TSKASS_TASK` FOREIGN KEY (`TASK_ID_`) REFERENCES `act_ru_task` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_job`;
CREATE TABLE `act_ru_job`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `CATEGORY_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `LOCK_EXP_TIME_`       timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCLUSIVE_`           tinyint(1) NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_NAME_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CORRELATION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RETRIES_`             int(0) NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DUEDATE_`             timestamp(3) NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         timestamp(3) NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_CORRELATION_ID`(`CORRELATION_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_JOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_FK_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
    INDEX                  `ACT_FK_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
    INDEX                  `ACT_FK_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_suspended_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_suspended_job`;
CREATE TABLE `act_ru_suspended_job`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `CATEGORY_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `EXCLUSIVE_`           tinyint(1) NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_NAME_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CORRELATION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RETRIES_`             int(0) NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DUEDATE_`             timestamp(3) NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         timestamp(3) NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_SUSPENDED_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_SUSPENDED_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_SUSPENDED_JOB_CORRELATION_ID`(`CORRELATION_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_SJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_SJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_SJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_FK_SUSPENDED_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
    INDEX                  `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
    INDEX                  `ACT_FK_SUSPENDED_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_SUSPENDED_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_task
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_task`;
CREATE TABLE `act_ru_task`
(
    `ID_`                       varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`                      int(0) NULL DEFAULT NULL,
    `EXECUTION_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_`             varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_DEF_ID_`              varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROPAGATED_STAGE_INST_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `STATE_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `NAME_`                     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PARENT_TASK_ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DESCRIPTION_`              varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_DEF_KEY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `OWNER_`                    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ASSIGNEE_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DELEGATION_`               varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PRIORITY_`                 int(0) NULL DEFAULT NULL,
    `CREATE_TIME_`              timestamp(3) NULL DEFAULT NULL,
    `IN_PROGRESS_TIME_`         datetime(3) NULL DEFAULT NULL,
    `IN_PROGRESS_STARTED_BY_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CLAIM_TIME_`               datetime(3) NULL DEFAULT NULL,
    `CLAIMED_BY_`               varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUSPENDED_TIME_`           datetime(3) NULL DEFAULT NULL,
    `SUSPENDED_BY_`             varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `IN_PROGRESS_DUE_DATE_`     datetime(3) NULL DEFAULT NULL,
    `DUE_DATE_`                 datetime(3) NULL DEFAULT NULL,
    `CATEGORY_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUSPENSION_STATE_`         int(0) NULL DEFAULT NULL,
    `TENANT_ID_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    `FORM_KEY_`                 varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `IS_COUNT_ENABLED_`         tinyint(0) NULL DEFAULT NULL,
    `VAR_COUNT_`                int(0) NULL DEFAULT NULL,
    `ID_LINK_COUNT_`            int(0) NULL DEFAULT NULL,
    `SUB_TASK_COUNT_`           int(0) NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                       `ACT_IDX_TASK_CREATE`(`CREATE_TIME_`) USING BTREE,
    INDEX                       `ACT_IDX_TASK_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_IDX_TASK_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_IDX_TASK_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                       `ACT_FK_TASK_EXE`(`EXECUTION_ID_`) USING BTREE,
    INDEX                       `ACT_FK_TASK_PROCINST`(`PROC_INST_ID_`) USING BTREE,
    INDEX                       `ACT_FK_TASK_PROCDEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_TASK_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TASK_PROCDEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TASK_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_timer_job
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_timer_job`;
CREATE TABLE `act_ru_timer_job`
(
    `ID_`                  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`                 int(0) NULL DEFAULT NULL,
    `CATEGORY_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`                varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `LOCK_EXP_TIME_`       timestamp(3) NULL DEFAULT NULL,
    `LOCK_OWNER_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCLUSIVE_`           tinyint(1) NULL DEFAULT NULL,
    `EXECUTION_ID_`        varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROCESS_INSTANCE_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_DEF_ID_`         varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_ID_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `ELEMENT_NAME_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`            varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`          varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_DEFINITION_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CORRELATION_ID_`      varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RETRIES_`             int(0) NULL DEFAULT NULL,
    `EXCEPTION_STACK_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `EXCEPTION_MSG_`       varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DUEDATE_`             timestamp(3) NULL DEFAULT NULL,
    `REPEAT_`              varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_TYPE_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `HANDLER_CFG_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CUSTOM_VALUES_ID_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`         timestamp(3) NULL DEFAULT NULL,
    `TENANT_ID_`           varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX                  `ACT_IDX_TIMER_JOB_EXCEPTION_STACK_ID`(`EXCEPTION_STACK_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_TIMER_JOB_CUSTOM_VALUES_ID`(`CUSTOM_VALUES_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_TIMER_JOB_CORRELATION_ID`(`CORRELATION_ID_`) USING BTREE,
    INDEX                  `ACT_IDX_TIMER_JOB_DUEDATE`(`DUEDATE_`) USING BTREE,
    INDEX                  `ACT_IDX_TJOB_SCOPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_TJOB_SUB_SCOPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_IDX_TJOB_SCOPE_DEF`(`SCOPE_DEFINITION_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX                  `ACT_FK_TIMER_JOB_EXECUTION`(`EXECUTION_ID_`) USING BTREE,
    INDEX                  `ACT_FK_TIMER_JOB_PROCESS_INSTANCE`(`PROCESS_INSTANCE_ID_`) USING BTREE,
    INDEX                  `ACT_FK_TIMER_JOB_PROC_DEF`(`PROC_DEF_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_TIMER_JOB_CUSTOM_VALUES` FOREIGN KEY (`CUSTOM_VALUES_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TIMER_JOB_EXCEPTION` FOREIGN KEY (`EXCEPTION_STACK_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TIMER_JOB_EXECUTION` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TIMER_JOB_PROC_DEF` FOREIGN KEY (`PROC_DEF_ID_`) REFERENCES `act_re_procdef` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_TIMER_JOB_PROCESS_INSTANCE` FOREIGN KEY (`PROCESS_INSTANCE_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for act_ru_variable
-- ----------------------------
DROP TABLE IF EXISTS `act_ru_variable`;
CREATE TABLE `act_ru_variable`
(
    `ID_`           varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin  NOT NULL,
    `REV_`          int(0) NULL DEFAULT NULL,
    `TYPE_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `NAME_`         varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `EXECUTION_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `PROC_INST_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TASK_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_ID_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BYTEARRAY_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `DOUBLE_` double NULL DEFAULT NULL,
    `LONG_`         bigint(0) NULL DEFAULT NULL,
    `TEXT_`         varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TEXT2_`        varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `META_INFO_`    varchar(4000) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX           `ACT_IDX_RU_VAR_SCOPE_ID_TYPE`(`SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX           `ACT_IDX_RU_VAR_SUB_ID_TYPE`(`SUB_SCOPE_ID_`, `SCOPE_TYPE_`) USING BTREE,
    INDEX           `ACT_FK_VAR_BYTEARRAY`(`BYTEARRAY_ID_`) USING BTREE,
    INDEX           `ACT_IDX_VARIABLE_TASK_ID`(`TASK_ID_`) USING BTREE,
    INDEX           `ACT_FK_VAR_EXE`(`EXECUTION_ID_`) USING BTREE,
    INDEX           `ACT_FK_VAR_PROCINST`(`PROC_INST_ID_`) USING BTREE,
    CONSTRAINT `ACT_FK_VAR_BYTEARRAY` FOREIGN KEY (`BYTEARRAY_ID_`) REFERENCES `act_ge_bytearray` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_VAR_EXE` FOREIGN KEY (`EXECUTION_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT,
    CONSTRAINT `ACT_FK_VAR_PROCINST` FOREIGN KEY (`PROC_INST_ID_`) REFERENCES `act_ru_execution` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_channel_definition
-- ----------------------------
DROP TABLE IF EXISTS `flw_channel_definition`;
CREATE TABLE `flw_channel_definition`
(
    `ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `VERSION_`        int(0) NULL DEFAULT NULL,
    `KEY_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CREATE_TIME_`    datetime(3) NULL DEFAULT NULL,
    `TENANT_ID_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_NAME_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION_`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TYPE_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `IMPLEMENTATION_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_IDX_CHANNEL_DEF_UNIQ`(`KEY_`, `VERSION_`, `TENANT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ev_databasechangelog
-- ----------------------------
DROP TABLE IF EXISTS `flw_ev_databasechangelog`;
CREATE TABLE `flw_ev_databasechangelog`
(
    `ID`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `AUTHOR`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `FILENAME`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `DATEEXECUTED`  datetime(0) NULL,
    `ORDEREXECUTED` int(0) NOT NULL,
    `EXECTYPE`      varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `MD5SUM`        varchar(35) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `COMMENTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TAG`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LIQUIBASE`     varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CONTEXTS`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `LABELS`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flw_ev_databasechangelog
-- ----------------------------
INSERT INTO `flw_ev_databasechangelog`
VALUES ('1', 'flowable', 'org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml',
        '2024-07-23 13:15:41', 1, 'EXECUTED', '9:63268f536c469325acef35970312551b',
        'createTable tableName=FLW_EVENT_DEPLOYMENT; createTable tableName=FLW_EVENT_RESOURCE; createTable tableName=FLW_EVENT_DEFINITION; createIndex indexName=ACT_IDX_EVENT_DEF_UNIQ, tableName=FLW_EVENT_DEFINITION; createTable tableName=FLW_CHANNEL_DEFIN...',
        '', NULL, '4.24.0', NULL, NULL, '1711741442');
INSERT INTO `flw_ev_databasechangelog`
VALUES ('2', 'flowable', 'org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml',
        '2024-07-23 13:15:41', 2, 'EXECUTED', '9:dcb58b7dfd6dbda66939123a96985536',
        'addColumn tableName=FLW_CHANNEL_DEFINITION; addColumn tableName=FLW_CHANNEL_DEFINITION', '', NULL, '4.24.0',
        NULL, NULL, '1711741442');
INSERT INTO `flw_ev_databasechangelog`
VALUES ('3', 'flowable', 'org/flowable/eventregistry/db/liquibase/flowable-eventregistry-db-changelog.xml',
        '2024-07-23 13:15:41', 3, 'EXECUTED', '9:d0c05678d57af23ad93699991e3bf4f6', 'customChange', '', NULL, '4.24.0',
        NULL, NULL, '1711741442');

-- ----------------------------
-- Table structure for flw_ev_databasechangeloglock
-- ----------------------------
DROP TABLE IF EXISTS `flw_ev_databasechangeloglock`;
CREATE TABLE `flw_ev_databasechangeloglock`
(
    `ID`          int(0) NOT NULL,
    `LOCKED`      tinyint(1) NOT NULL,
    `LOCKGRANTED` datetime(0) NULL DEFAULT NULL,
    `LOCKEDBY`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flw_ev_databasechangeloglock
-- ----------------------------
INSERT INTO `flw_ev_databasechangeloglock`
VALUES (1, 0, NULL, NULL);

-- ----------------------------
-- Table structure for flw_event_definition
-- ----------------------------
DROP TABLE IF EXISTS `flw_event_definition`;
CREATE TABLE `flw_event_definition`
(
    `ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `VERSION_`       int(0) NULL DEFAULT NULL,
    `KEY_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `TENANT_ID_`     varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_NAME_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DESCRIPTION_`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE,
    UNIQUE INDEX `ACT_IDX_EVENT_DEF_UNIQ`(`KEY_`, `VERSION_`, `TENANT_ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_event_deployment
-- ----------------------------
DROP TABLE IF EXISTS `flw_event_deployment`;
CREATE TABLE `flw_event_deployment`
(
    `ID_`                   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`                 varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `CATEGORY_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOY_TIME_`          datetime(3) NULL DEFAULT NULL,
    `TENANT_ID_`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `PARENT_DEPLOYMENT_ID_` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_event_resource
-- ----------------------------
DROP TABLE IF EXISTS `flw_event_resource`;
CREATE TABLE `flw_event_resource`
(
    `ID_`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `NAME_`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `DEPLOYMENT_ID_`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `RESOURCE_BYTES_` longblob NULL,
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ru_batch
-- ----------------------------
DROP TABLE IF EXISTS `flw_ru_batch`;
CREATE TABLE `flw_ru_batch`
(
    `ID_`            varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`           int(0) NULL DEFAULT NULL,
    `TYPE_`          varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `SEARCH_KEY_`    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SEARCH_KEY2_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`   datetime(3) NULL,
    `COMPLETE_TIME_` datetime(3) NULL DEFAULT NULL,
    `STATUS_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `BATCH_DOC_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flw_ru_batch_part
-- ----------------------------
DROP TABLE IF EXISTS `flw_ru_batch_part`;
CREATE TABLE `flw_ru_batch_part`
(
    `ID_`            varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `REV_`           int(0) NULL DEFAULT NULL,
    `BATCH_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TYPE_`          varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NOT NULL,
    `SCOPE_ID_`      varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SUB_SCOPE_ID_`  varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SCOPE_TYPE_`    varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SEARCH_KEY_`    varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `SEARCH_KEY2_`   varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `CREATE_TIME_`   datetime(3) NULL,
    `COMPLETE_TIME_` datetime(3) NULL DEFAULT NULL,
    `STATUS_`        varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `RESULT_DOC_ID_` varchar(64) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT NULL,
    `TENANT_ID_`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_bin NULL DEFAULT '',
    PRIMARY KEY (`ID_`) USING BTREE,
    INDEX            `FLW_IDX_BATCH_PART`(`BATCH_ID_`) USING BTREE,
    CONSTRAINT `FLW_FK_BATCH_PART_PARENT` FOREIGN KEY (`BATCH_ID_`) REFERENCES `flw_ru_batch` (`ID_`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for flyway_schema_history
-- ----------------------------
DROP TABLE IF EXISTS `flyway_schema_history`;
CREATE TABLE `flyway_schema_history`
(
    `installed_rank` int(0) NOT NULL,
    `version`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `description`    varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `type`           varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL,
    `script`         varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `checksum`       int(0) NULL DEFAULT NULL,
    `installed_by`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL,
    `installed_on`   timestamp(0)                                                   NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `execution_time` int(0) NOT NULL,
    `success`        tinyint(1) NOT NULL,
    PRIMARY KEY (`installed_rank`) USING BTREE,
    INDEX            `flyway_schema_history_s_idx`(`success`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of flyway_schema_history
-- ----------------------------
INSERT INTO `flyway_schema_history`
VALUES (1, '1', '<< Flyway Baseline >>', 'BASELINE', '<< Flyway Baseline >>', NULL, 'root', '2024-07-23 13:15:28', 0,
        1);

-- ----------------------------
-- Table structure for sys_company
-- ----------------------------
DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`
(
    `id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公司id',
    `company_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '公司名称',
    `company_sort` int(0) NULL DEFAULT 0 COMMENT '显示顺序',
    `status`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公司状态（0正常 1停用）',
    `del_flag`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数主键',
    `config_name`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '参数配置表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门id',
    `name`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门名',
    `parent_id`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '上级部门id',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `status`      int(0) NOT NULL DEFAULT 1,
    `dept_sort`   int(0) NOT NULL DEFAULT 1,
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `ancestors`   varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '祖级列表',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE,
    INDEX         `idx_parent_id`(`parent_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept_leader
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_leader`;
CREATE TABLE `sys_dept_leader`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门id',
    `dept_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '部门id',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '主管user_id',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE,
    INDEX         `idx_parent_id`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-主管表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `dept_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '部门id',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'user_id',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE,
    INDEX         `idx_parent_id`(`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '部门-主管表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数主键',
    `dict_code`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典编码',
    `dict_sort`   int(0) NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典数据表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '字典主键',
    `dict_name`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '字典类型',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `dict_type`(`dict_type`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '字典类型表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_exception_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log`
(
    `id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci   NOT NULL COMMENT '参数主键',
    `exp_url`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '异常url',
    `exp_params`     varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '异常参数',
    `exp_type`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常类型',
    `exp_controller` varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常controller',
    `exp_method`     varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '异常方法',
    `exp_detail`     text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '异常详情',
    `create_by`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`    datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '异常日志表' ROW_FORMAT = Compact;

-- ----------------------------
-- Records of sys_exception_log
-- ----------------------------
INSERT INTO `sys_exception_log`
VALUES ('491f43bd5aed9ad7a0200f3bddfe556c', '/open/commons/sys-user/1', '',
        'cn.dev33.satoken.exception.NotLoginException', 'cn.dev33.satoken.exception.NotLoginException', 'newInstance',
        'cn.dev33.satoken.exception.NotLoginException: 未能读取到有效 token\r\n	at cn.dev33.satoken.exception.NotLoginException.newInstance(NotLoginException.java:134)\r\n	at cn.dev33.satoken.stp.StpLogic.getLoginId(StpLogic.java:941)\r\n	at cn.dev33.satoken.stp.StpLogic.checkLogin(StpLogic.java:923)\r\n	at cn.dev33.satoken.stp.StpLogic.checkByAnnotation(StpLogic.java:2246)\r\n	at cn.dev33.satoken.strategy.SaStrategy.lambda$new$4(SaStrategy.java:156)\r\n	at cn.dev33.satoken.strategy.SaStrategy.lambda$new$3(SaStrategy.java:142)\r\n	at cn.dev33.satoken.interceptor.SaInterceptor.preHandle(SaInterceptor.java:110)\r\n	at org.springframework.web.servlet.HandlerExecutionChain.applyPreHandle(HandlerExecutionChain.java:146)\r\n	at org.springframework.web.servlet.DispatcherServlet.doDispatch(DispatcherServlet.java:1084)\r\n	at org.springframework.web.servlet.DispatcherServlet.doService(DispatcherServlet.java:979)\r\n	at org.springframework.web.servlet.FrameworkServlet.processRequest(FrameworkServlet.java:1014)\r\n	at org.springframework.web.servlet.FrameworkServlet.doGet(FrameworkServlet.java:903)\r\n	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:564)\r\n	at org.springframework.web.servlet.FrameworkServlet.service(FrameworkServlet.java:885)\r\n	at jakarta.servlet.http.HttpServlet.service(HttpServlet.java:658)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:195)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:51)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.CorsFilter.doFilterInternal(CorsFilter.java:91)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.RequestContextFilter.doFilterInternal(RequestContextFilter.java:100)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at cn.dev33.satoken.filter.SaPathCheckFilterForJakartaServlet.doFilter(SaPathCheckFilterForJakartaServlet.java:55)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.FormContentFilter.doFilterInternal(FormContentFilter.java:93)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.ServerHttpObservationFilter.doFilterInternal(ServerHttpObservationFilter.java:113)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.springframework.web.filter.CharacterEncodingFilter.doFilterInternal(CharacterEncodingFilter.java:201)\r\n	at org.springframework.web.filter.OncePerRequestFilter.doFilter(OncePerRequestFilter.java:116)\r\n	at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:164)\r\n	at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:140)\r\n	at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)\r\n	at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)\r\n	at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:483)\r\n	at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:115)\r\n	at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)\r\n	at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)\r\n	at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:344)\r\n	at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)\r\n	at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)\r\n	at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:904)\r\n	at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1741)\r\n	at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:52)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1190)\r\n	at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)\r\n	at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:63)\r\n	at java.base/java.lang.Thread.run(Thread.java:833)\r\n',
        '', '2024-09-11 13:56:59', '', '2024-09-11 13:56:59', NULL);

-- ----------------------------
-- Table structure for sys_login_info
-- ----------------------------
DROP TABLE IF EXISTS `sys_login_info`;
CREATE TABLE `sys_login_info`
(
    `id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `user_name`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户账号',
    `ip_address`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作系统',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime(0) NULL DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `idx_sys_logininfor_s`(`status`) USING BTREE,
    INDEX            `idx_sys_logininfor_lt`(`login_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '系统访问记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `parent_id`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '父菜单ID',
    `tree_path`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父节点ID路径',
    `name`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
    `menu_type`    int(0) NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
    `path`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
    `component`    varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
    `perm`         varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限标识',
    `visible`      int(0) NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
    `menu_sort`    int(0) NULL DEFAULT 0 COMMENT '排序',
    `icon`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '菜单图标',
    `redirect`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '跳转路径',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `del_flag`     int(0) NOT NULL COMMENT '逻辑删除字段',
    `tenant_id`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `ancestors`    varchar(600) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '祖级id路径(parentid/sonid/id)',
    `query_params` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '路由参数',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单管理' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `message_type`        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '类型',
    `readed`              int(0) NOT NULL COMMENT '是否已读',
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户id',
    `biz_unique_id`       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务唯一id',
    `param`               longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '消息参数',
    `content`             varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息内容',
    `title`               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '消息头',
    `flow_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '流程id',
    `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '流程实例id',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知消息' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告ID',
    `notice_title`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '公告标题',
    `notice_type`    char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob NULL COMMENT '公告内容',
    `status`         char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`    datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`    datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '通知公告表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '日志主键',
    `title`          varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '模块标题',
    `business_type`  int(0) NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(0) NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '返回参数',
    `status`         int(0) NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
    `cost_time`      bigint(0) NULL DEFAULT 0 COMMENT '消耗时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX            `idx_sys_oper_log_bt`(`business_type`) USING BTREE,
    INDEX            `idx_sys_oper_log_s`(`status`) USING BTREE,
    INDEX            `idx_sys_oper_log_ot`(`oper_time`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '操作日志记录' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`
(
    `id`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '主建',
    `config_key`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL DEFAULT '' COMMENT '配置key',
    `access_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT 'accessKey',
    `secret_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '秘钥',
    `bucket_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '桶名称',
    `prefix`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '前缀',
    `endpoint`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '访问站点',
    `domain`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '自定义域名',
    `is_https`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
    `region`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '域',
    `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin     NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
    `status`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '1' COMMENT '状态（0=正常,1=停用）',
    `ext1`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '扩展字段',
    `create_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '创建者',
    `create_time`   datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '更新者',
    `update_time`   datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '对象存储配置表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位ID',
    `post_code`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位名称',
    `post_sort`   int(0) NOT NULL COMMENT '显示顺序',
    `status`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci     NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '岗位信息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_process
-- ----------------------------
DROP TABLE IF EXISTS `sys_process`;
CREATE TABLE `sys_process`
(
    `id`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`      int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`   datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`   datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `flow_id`       varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '表单ID',
    `name`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '表单名称',
    `logo`          varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标配置',
    `settings`      json NULL COMMENT '设置项',
    `group_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '分组ID',
    `form_items`    json                                                          NOT NULL COMMENT '表单设置内容',
    `process`       json                                                          NOT NULL COMMENT '流程设置内容',
    `remark`        varchar(125) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `process_sort`  int(0) NOT NULL,
    `is_hidden`     int(0) NOT NULL COMMENT '0 正常 1=隐藏',
    `is_stop`       int(0) NOT NULL COMMENT '0 正常 1=停用 ',
    `admin_id`      varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程管理员',
    `unique_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一性id',
    `admin`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '管理员',
    `range_show`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '范围描述显示',
    `version`       int(0) NULL DEFAULT NULL COMMENT '版本',
    `tenant_id`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `form_items_pc` json                                                          NOT NULL COMMENT '表单设置内容pc',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `idx_form_id`(`flow_id`) USING BTREE,
    INDEX           `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_form
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_form`;
CREATE TABLE `sys_process_form`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `unique_id`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '流程唯一id',
    `form_name`   varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单名称',
    `form_id`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单id',
    `form_type`   varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '表单类型',
    `props`       json                                                          NOT NULL COMMENT '表单属性',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `flow_id`     varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '流程id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程表单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_group`;
CREATE TABLE `sys_process_group`
(
    `id`                 varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`           int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`        datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`        datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `group_name`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名',
    `process_group_sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
    `tenant_id`          varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_assign_user_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_assign_user_record`;
CREATE TABLE `sys_process_instance_assign_user_record`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NOT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NOT NULL COMMENT '更新时间',
    `flow_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程id',
    `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例id',
    `data`                longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单数据',
    `node_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT ' 用户id',
    `status`              int(0) NOT NULL COMMENT '节点状态',
    `start_time`          datetime(0) NOT NULL COMMENT '开始时间',
    `end_time`            datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `execution_id`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行id',
    `task_id`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 任务id',
    `approve_desc`        varchar(1000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '审批意见',
    `node_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT ' 节点名称',
    `task_type`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务类型',
    `local_data`          longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单本地数据',
    `flow_unique_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流转唯一标识',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `auto`                int(0) NULL DEFAULT 0 COMMENT '是否是自动完成',
    `parent_execution_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点执行id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程节点记录-执行人' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_copy`;
CREATE TABLE `sys_process_instance_copy`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NOT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NOT NULL COMMENT '更新时间',
    `start_time`          datetime(0) NOT NULL COMMENT ' 流程发起时间',
    `node_time`           datetime(0) NOT NULL COMMENT '当前节点时间',
    `start_user_id`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '发起人',
    `flow_id`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程id',
    `process_instance_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例id',
    `node_id`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点id',
    `group_id`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '分组id',
    `group_name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
    `process_name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程名称',
    `node_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点 名称',
    `form_data`           longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单数据',
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '抄送人id',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程抄送数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_execution
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_execution`;
CREATE TABLE `sys_process_instance_execution`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NOT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NOT NULL COMMENT '更新时间',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '流程实例id',
    `node_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '节点id',
    `execution_id`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '执行id',
    `parent_execution_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级执行id',
    `flow_id`             varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL DEFAULT '' COMMENT '流程id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程执行id数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_node_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_node_record`;
CREATE TABLE `sys_process_instance_node_record`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NOT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NOT NULL COMMENT '更新时间',
    `flow_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程id',
    `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程实例id',
    `data`                longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单数据',
    `node_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `node_type`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点类型',
    `node_name`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '节点名字',
    `status`              int(0) NOT NULL COMMENT '节点状态',
    `start_time`          datetime(0) NOT NULL COMMENT '开始时间',
    `end_time`            datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `execution_id`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行id',
    `parent_node_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上一层级id',
    `flow_unique_id`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流转唯一标识',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程节点记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_oper_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_oper_record`;
CREATE TABLE `sys_process_instance_oper_record`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`         datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `flow_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程id',
    `node_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点id',
    `node_name`           varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '节点名称',
    `process_instance_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例id',
    `comment`             varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '评论',
    `oper_type`           varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作类型',
    `oper_desc`           varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '操作描述',
    `image_list`          varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片列表',
    `file_list`           varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '文件列表',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE,
    INDEX                 `idx_dep_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_record`;
CREATE TABLE `sys_process_instance_record`
(
    `id`                               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `name`                             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '流程名字',
    `logo`                             varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '头像',
    `user_id`                          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '发起人的用户id',
    `main_dept_id`                     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '发起人主部门id',
    `del_flag`                         int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`                        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `update_by`                        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `create_time`                      datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_time`                      datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    `flow_id`                          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程id',
    `process_instance_id`              varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例id',
    `process_instance_biz_code`        varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例业务编码',
    `process_instance_biz_key`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '流程实例业务key',
    `form_data`                        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL COMMENT '表单数据',
    `group_id`                         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组id',
    `group_name`                       varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组名称',
    `status`                           int(0) NULL DEFAULT 1 COMMENT '状态',
    `end_time`                         datetime(0) NULL DEFAULT NULL COMMENT '结束时间',
    `parent_process_instance_id`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '上级流程实例id',
    `process`                          json NULL,
    `result`                           int(0) NULL DEFAULT NULL COMMENT '结果',
    `tenant_id`                        varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `parent_process_node_execution_id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '主流程的节点执行id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                              `idx_id`(`id`) USING BTREE,
    INDEX                              `idx_dep_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程记录' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_instance_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_user_copy`;
CREATE TABLE `sys_process_instance_user_copy`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`         datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `start_user_id`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '发起人',
    `flow_id`             varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程id',
    `process_instance_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '实例id',
    `group_id`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '分组id',
    `group_name`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '分组名称',
    `process_name`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程名称',
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '抄送人id',
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程抄送数据--用户和实例唯一值' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_main
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_main`;
CREATE TABLE `sys_process_main`
(
    `id`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`          int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`       datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`       datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `name`              varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '表单名称',
    `logo`              varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '图标配置',
    `group_id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '分组ID',
    `process_main_sort` int(0) NOT NULL,
    `unique_id`         varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '唯一性id',
    `range_show`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '范围描述显示',
    `tenant_id`         varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX               `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程主表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_node_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_node_data`;
CREATE TABLE `sys_process_node_data`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `flow_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '流程id',
    `data`        longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '表单数据',
    `node_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程节点数据' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_process_starter
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_starter`;
CREATE TABLE `sys_process_starter`
(
    `id`                    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT 'PK',
    `del_flag`              int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`           datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`           datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `type_id`               varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '用户id或者部门id',
    `contain_children_dept` int(0) NOT NULL DEFAULT 0 COMMENT '是否包含下级部门',
    `type_name`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT ' 类型 user dept',
    `process_id`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci  NOT NULL COMMENT '流程表id',
    `data`                  varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '数据',
    `tenant_id`             varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `flow_id`               varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '' COMMENT '流程id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                   `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '流程发起人范围' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`                  varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`            int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`         datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`         datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `name`                varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名字',
    `user_id`             varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人',
    `role_key`            varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
    `status`              int(0) NULL DEFAULT 1,
    `tenant_id`           varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `role_sort`           int(0) NOT NULL COMMENT '显示顺序',
    `data_scope`          int(0) NULL DEFAULT 1 COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `menu_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
    `dept_check_strictly` tinyint(1) NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX                 `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `role_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `dept_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '部门ID',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和部门关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `role_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色ID',
    `menu_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '菜单ID',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色和菜单关联表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'pk',
    `dept_id`      varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门ID',
    `user_name`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户账号',
    `nick_name`    varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户昵称',
    `pinyin`       varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼音  全拼',
    `py`           varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拼音, 首字母缩写',
    `user_type`    varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`        varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '用户邮箱',
    `phone_number` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '手机号码',
    `sex`          char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar_url`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '头像地址',
    `password`     varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '密码',
    `status`       char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`     char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`     varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`   datetime(0) NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
    `parent_id`    varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '直属领导id',
    `tenant_id`    varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'pk',
    `user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户ID',
    `post_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '岗位ID',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户与岗位关联表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `id`          varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK',
    `del_flag`    int(0) NOT NULL COMMENT '逻辑删除字段',
    `create_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
    `user_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户id',
    `role_id`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
    `tenant_id`   varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX         `idx_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户-角色' ROW_FORMAT = Dynamic;

SET
FOREIGN_KEY_CHECKS = 1;