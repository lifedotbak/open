/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : localhost:3307
 Source Schema         : sys_os

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 08/05/2024 22:32:06
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;


DROP TABLE IF EXISTS `sys_company`;
CREATE TABLE `sys_company`
(
    `id`           varchar(64)  NOT NULL COMMENT '公司id',
    `company_name` varchar(30)  NULL DEFAULT '' COMMENT '公司名称',
    `company_sort`    int(0)       NULL DEFAULT 0 COMMENT '显示顺序',
    `status`       char(1)      NULL DEFAULT '0' COMMENT '公司状态（0正常 1停用）',
    `del_flag`     char(1)      NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门表'  ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`    varchar(64)   NOT NULL COMMENT '参数主键',
    `config_name`  varchar(100)  NULL DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100)  NULL DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(500)  NULL DEFAULT '' COMMENT '参数键值',
    `config_type`  char(1)       NULL DEFAULT 'N' COMMENT '系统内置（Y是 N否）',
    `create_by`    varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`       varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '参数配置表'   ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data`
(
    `id`    varchar(64)   NOT NULL COMMENT '参数主键',
    `dict_code`   varchar(64)   NOT NULL COMMENT '字典编码',
    `dict_sort`   int(0)                                                        NULL DEFAULT 0 COMMENT '字典排序',
    `dict_label`  varchar(100)  NULL DEFAULT '' COMMENT '字典标签',
    `dict_value`  varchar(100)  NULL DEFAULT '' COMMENT '字典键值',
    `dict_type`   varchar(100)  NULL DEFAULT '' COMMENT '字典类型',
    `css_class`   varchar(100)  NULL DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
    `list_class`  varchar(100)  NULL DEFAULT NULL COMMENT '表格回显样式',
    `is_default`  char(1)       NULL DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
    `status`      char(1)       NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0)   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0)   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '字典数据表'   ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type`
(
    `id`     varchar(64)   NOT NULL COMMENT '字典主键',
    `dict_name`   varchar(100)  NULL DEFAULT '' COMMENT '字典名称',
    `dict_type`   varchar(100)  NULL DEFAULT '' COMMENT '字典类型',
    `status`      char(1)       NULL DEFAULT '0' COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0)   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0)   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `dict_type` (`dict_type`) USING BTREE
) ENGINE = InnoDB    COMMENT = '字典类型表'   ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_job
-- ----------------------------
DROP TABLE IF EXISTS `sys_job`;
CREATE TABLE `sys_job`
(
    `id`          varchar(64)   NOT NULL COMMENT '任务ID',
    `job_name`        varchar(64)   NOT NULL DEFAULT '' COMMENT '任务名称',
    `job_group`       varchar(64)   NOT NULL DEFAULT 'DEFAULT' COMMENT '任务组名',
    `invoke_target`   varchar(500)  NOT NULL COMMENT '调用目标字符串',
    `cron_expression` varchar(255)  NULL     DEFAULT '' COMMENT 'cron执行表达式',
    `misfire_policy`  varchar(20)   NULL     DEFAULT '3' COMMENT '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
    `concurrent`      char(1)       NULL     DEFAULT '1' COMMENT '是否并发执行（0允许 1禁止）',
    `status`          char(1)       NULL     DEFAULT '0' COMMENT '状态（0正常 1暂停）',
    `create_by`       varchar(64)   NULL     DEFAULT '' COMMENT '创建者',
    `create_time`     datetime(0)   NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`       varchar(64)   NULL     DEFAULT '' COMMENT '更新者',
    `update_time`     datetime(0)   NULL     DEFAULT NULL COMMENT '更新时间',
    `remark`          varchar(500)  NULL     DEFAULT '' COMMENT '备注信息',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '定时任务调度表'   ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_job_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_job_log`;
CREATE TABLE `sys_job_log`
(
    `id`     varchar(64)  NOT NULL COMMENT '任务日志ID',
    `job_name`       varchar(64)  NOT NULL COMMENT '任务名称',
    `job_group`      varchar(64)  NOT NULL COMMENT '任务组名',
    `invoke_target`  text         NOT NULL COMMENT '调用目标字符串',
    `job_message`    text         NULL COMMENT '日志信息',
    `status`         char(1)      NULL DEFAULT '0' COMMENT '执行状态（0正常 1失败）',
    `exception_info` text         NULL COMMENT '异常信息',
    `create_time`    datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '定时任务调度日志表'   ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_logininfor
-- ----------------------------
DROP TABLE IF EXISTS `sys_logininfor`;
CREATE TABLE `sys_logininfor`
(
    `id`        varchar(64)   NOT NULL COMMENT 'PK',
    `user_name`      varchar(64)   NULL DEFAULT '' COMMENT '用户账号',
    `ipaddr`         varchar(128)  NULL DEFAULT '' COMMENT '登录IP地址',
    `login_location` varchar(255)  NULL DEFAULT '' COMMENT '登录地点',
    `browser`        varchar(50)   NULL DEFAULT '' COMMENT '浏览器类型',
    `os`             varchar(50)   NULL DEFAULT '' COMMENT '操作系统',
    `status`         char(1)       NULL DEFAULT '0' COMMENT '登录状态（0成功 1失败）',
    `msg`            varchar(255)  NULL DEFAULT '' COMMENT '提示消息',
    `login_time`     datetime(0)                                                   NULL DEFAULT NULL COMMENT '访问时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_logininfor_s` (`status`) USING BTREE,
    INDEX `idx_sys_logininfor_lt` (`login_time`) USING BTREE
) ENGINE = InnoDB    COMMENT = '系统访问记录'   ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`
(
    `id`      varchar(64)   NOT NULL COMMENT '公告ID',
    `notice_title`   varchar(50)   NOT NULL COMMENT '公告标题',
    `notice_type`    char(1)       NOT NULL COMMENT '公告类型（1通知 2公告）',
    `notice_content` longblob                                                      NULL COMMENT '公告内容',
    `status`         char(1)       NULL DEFAULT '0' COMMENT '公告状态（0正常 1关闭）',
    `create_by`      varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time`    datetime(0)   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`      varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time`    datetime(0)   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`         varchar(255)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '通知公告表'   ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_oper_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_oper_log`;
CREATE TABLE `sys_oper_log`
(
    `id`        varchar(64)    NOT NULL COMMENT '日志主键',
    `title`          varchar(50)    NULL DEFAULT '' COMMENT '模块标题',
    `business_type`  int(0)         NULL DEFAULT 0 COMMENT '业务类型（0其它 1新增 2修改 3删除）',
    `method`         varchar(100)   NULL DEFAULT '' COMMENT '方法名称',
    `request_method` varchar(10)    NULL DEFAULT '' COMMENT '请求方式',
    `operator_type`  int(0)         NULL DEFAULT 0 COMMENT '操作类别（0其它 1后台用户 2手机端用户）',
    `oper_name`      varchar(50)    NULL DEFAULT '' COMMENT '操作人员',
    `dept_name`      varchar(50)    NULL DEFAULT '' COMMENT '部门名称',
    `oper_url`       varchar(255)   NULL DEFAULT '' COMMENT '请求URL',
    `oper_ip`        varchar(128)   NULL DEFAULT '' COMMENT '主机地址',
    `oper_location`  varchar(255)   NULL DEFAULT '' COMMENT '操作地点',
    `oper_param`     varchar(2000)  NULL DEFAULT '' COMMENT '请求参数',
    `json_result`    varchar(2000)  NULL DEFAULT '' COMMENT '返回参数',
    `status`         int(0)         NULL DEFAULT 0 COMMENT '操作状态（0正常 1异常）',
    `error_msg`      varchar(2000)  NULL DEFAULT '' COMMENT '错误消息',
    `oper_time`      datetime(0)    NULL DEFAULT NULL COMMENT '操作时间',
    `cost_time`      bigint(0)      NULL DEFAULT 0 COMMENT '消耗时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_sys_oper_log_bt` (`business_type`) USING BTREE,
    INDEX `idx_sys_oper_log_s` (`status`) USING BTREE,
    INDEX `idx_sys_oper_log_ot` (`oper_time`) USING BTREE
) ENGINE = InnoDB  COMMENT = '操作日志记录'  ROW_FORMAT = Compact;


-- ----------------------------
-- Table structure for sys_oss_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_oss_config`;
CREATE TABLE `sys_oss_config`
(
    `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL COMMENT '主建',
    `config_key`    varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NOT NULL DEFAULT '' COMMENT '配置key',
    `access_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT 'accessKey',
    `secret_key`    varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '秘钥',
    `bucket_name`   varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '桶名称',
    `prefix`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '前缀',
    `endpoint`      varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '访问站点',
    `domain`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '自定义域名',
    `is_https`      char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      NULL     DEFAULT 'N' COMMENT '是否https（Y=是,N=否）',
    `region`        varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '域',
    `access_policy` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      NOT NULL DEFAULT '1' COMMENT '桶权限类型(0=private 1=public 2=custom)',
    `status`        char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin      NULL     DEFAULT '1' COMMENT '状态（0=正常,1=停用）',
    `ext1`          varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT '' COMMENT '扩展字段',
    `create_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL     DEFAULT '' COMMENT '创建者',
    `create_time`   datetime(0)                                            NULL     DEFAULT NULL COMMENT '创建时间',
    `update_by`     varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin  NULL     DEFAULT '' COMMENT '更新者',
    `update_time`   datetime(0)                                            NULL     DEFAULT NULL COMMENT '更新时间',
    `remark`        varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL     DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB COMMENT = '对象存储配置表'  ROW_FORMAT = Dynamic;


-- ----------------------------
-- Table structure for sys_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_post`;
CREATE TABLE `sys_post`
(
    `id`     varchar(64)   NOT NULL COMMENT '岗位ID',
    `post_code`   varchar(64)   NOT NULL COMMENT '岗位编码',
    `post_name`   varchar(50)   NOT NULL COMMENT '岗位名称',
    `post_sort`   int(0)                                                        NOT NULL COMMENT '显示顺序',
    `status`      char(1)       NOT NULL COMMENT '状态（0正常 1停用）',
    `create_by`   varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0)                                                   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB   COMMENT = '岗位信息表'  ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
      `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
      `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
      `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
      `user_id` varchar(64)  NOT NULL COMMENT '用户id',
      `role_id`varchar(64)  NOT NULL COMMENT '角色id',
      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
      PRIMARY KEY (`id`) USING BTREE,
      INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '用户-角色' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`     varchar(64)   NOT NULL COMMENT 'pk',
    `dept_id`     varchar(64)   NULL DEFAULT NULL COMMENT '部门ID',
    `user_name`   varchar(30)   NOT NULL COMMENT '用户账号',
    `nick_name`   varchar(30)   NOT NULL COMMENT '用户昵称',
    `pinyin`varchar(64)  NULL DEFAULT NULL COMMENT '拼音  全拼',
    `py`varchar(64)  NULL DEFAULT NULL COMMENT '拼音, 首字母缩写',
    `user_type`   varchar(2)    NULL DEFAULT '00' COMMENT '用户类型（00系统用户）',
    `email`       varchar(50)   NULL DEFAULT '' COMMENT '用户邮箱',
    `phone_number` varchar(11)   NULL DEFAULT '' COMMENT '手机号码',
    `sex`         char(1)       NULL DEFAULT '0' COMMENT '用户性别（0男 1女 2未知）',
    `avatar_url`      varchar(100)  NULL DEFAULT '' COMMENT '头像地址',
    `password`    varchar(100)  NULL DEFAULT '' COMMENT '密码',
    `status`      char(1)       NULL DEFAULT '0' COMMENT '帐号状态（0正常 1停用）',
    `del_flag`    char(1)       NULL DEFAULT '0' COMMENT '删除标志（0代表存在 2代表删除）',
    `login_ip`    varchar(128)  NULL DEFAULT '' COMMENT '最后登录IP',
    `login_date`  datetime(0)    NULL DEFAULT NULL COMMENT '最后登录时间',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    `parent_id` varchar(64) NULL DEFAULT NULL COMMENT '直属领导id',
    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '用户信息表'  ROW_FORMAT = DYNAMIC;


-- ----------------------------
-- Table structure for sys_user_post
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_post`;
CREATE TABLE `sys_user_post`
(
    `id`     varchar(64)   NOT NULL COMMENT 'pk',
    `user_id` varchar(64)  NOT NULL COMMENT '用户ID',
    `post_id` varchar(64)  NOT NULL COMMENT '岗位ID',
    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB   COMMENT = '用户与岗位关联表'   ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `role_id`varchar(64)  NOT NULL COMMENT '角色ID',
      `menu_id` varchar(64) NOT NULL COMMENT '菜单ID',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
      `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
      `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
      `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色和菜单关联表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
     `id` varchar(64) NOT NULL COMMENT 'PK',
     `del_flag` int NOT NULL COMMENT '逻辑删除字段',
     `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
     `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
     `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
     `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
     `name`varchar(64)  NULL DEFAULT NULL COMMENT '角色名字',
     `user_id`  varchar(64) NULL DEFAULT NULL COMMENT '创建人',
     `role_key`varchar(64)  NULL DEFAULT  NULL,
     `status` int NULL DEFAULT 1,
     `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
     `role_sort`   int   NOT NULL COMMENT '显示顺序',
     `data_scope`    int   NULL DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
     `menu_check_strictly` tinyint(1)     NULL DEFAULT 1 COMMENT '菜单树选择项是否关联显示',
     `dept_check_strictly` tinyint(1)     NULL DEFAULT 1 COMMENT '部门树选择项是否关联显示',
     PRIMARY KEY (`id`) USING BTREE,
     INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '角色' ROW_FORMAT = DYNAMIC;

DROP TABLE IF EXISTS `sys_role_dept`;
CREATE TABLE `sys_role_dept`
(
    `id` varchar(64) NOT NULL COMMENT 'PK',
    `role_id` varchar(64)  NOT NULL COMMENT '角色ID',
    `dept_id` varchar(64)  NOT NULL COMMENT '部门ID',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB   COMMENT = '角色和部门关联表'   ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for sys_process_starter
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_starter`;
CREATE TABLE `sys_process_starter`  (
    `id` varchar(64) NOT NULL COMMENT 'PK',
    `del_flag` int NOT NULL COMMENT '逻辑删除字段',
    `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
    `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
    `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
    `type_id`varchar(64)  NOT NULL COMMENT '用户id或者部门id',
    `contain_children_dept` int NOT NULL DEFAULT 0 COMMENT '是否包含下级部门',
    `type_name`varchar(64)  NOT NULL COMMENT ' 类型 user dept',
    `process_id` varchar(64) NOT NULL COMMENT '流程表id',
    `data` varchar(500)  NULL DEFAULT NULL COMMENT '数据',
    `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
    `flow_id` varchar(200)  NOT NULL DEFAULT '' COMMENT '流程id',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程发起人范围' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_starter
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_node_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_node_data`;
CREATE TABLE `sys_process_node_data`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
`create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
      `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
      `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
      `flow_id`varchar(64)  NOT NULL COMMENT '流程id',
      `data` longtext  NOT NULL COMMENT '表单数据',
      `node_id`varchar(64)  NOT NULL,
      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
      PRIMARY KEY (`id`) USING BTREE,
      INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程节点数据' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_node_data
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_main
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_main`;
CREATE TABLE `sys_process_main`  (
     `id` varchar(64) NOT NULL COMMENT 'PK',
     `del_flag` int NOT NULL COMMENT '逻辑删除字段',
     `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
     `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
     `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
     `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
     `name`varchar(64)  NOT NULL COMMENT '表单名称',
     `logo` varchar(200)  NOT NULL COMMENT '图标配置',
     `group_id` varchar(64) NOT NULL COMMENT '分组ID',
     `process_main_sort` int NOT NULL,
     `unique_id` varchar(64)  NULL DEFAULT NULL COMMENT '唯一性id',
     `range_show` varchar(255)  NULL DEFAULT NULL COMMENT '范围描述显示',
     `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
     PRIMARY KEY (`id`) USING BTREE,
     INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程主表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_main
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_user_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_user_copy`;
CREATE TABLE `sys_process_instance_user_copy`  (
       `id` varchar(64) NOT NULL COMMENT 'PK',
       `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
        `create_time`  datetime(0)  NULL DEFAULT NULL COMMENT '创建时间',
        `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
        `update_time`  datetime(0)  NULL DEFAULT NULL COMMENT '更新时间',
       `start_user_id`varchar(64)  NOT NULL COMMENT '发起人',
       `flow_id` varchar(255)  NOT NULL COMMENT '流程id',
       `process_instance_id` varchar(255)  NOT NULL COMMENT '实例id',
       `group_id` varchar(64) NOT NULL COMMENT '分组id',
       `group_name` varchar(255)  NOT NULL COMMENT '分组名称',
       `process_name` varchar(255)  NOT NULL COMMENT '流程名称',
       `user_id` varchar(64)  NOT NULL COMMENT '抄送人id',
       `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
       PRIMARY KEY (`id`) USING BTREE,
       INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程抄送数据--用户和实例唯一值' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_instance_user_copy
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_record`;
CREATE TABLE `sys_process_instance_record`  (
        `id` varchar(64) NOT NULL COMMENT 'PK',
        `name`varchar(64)  NOT NULL COMMENT '流程名字',
        `logo` varchar(200)  NOT NULL COMMENT '头像',
        `user_id` varchar(64)  NOT NULL COMMENT '发起人的用户id',
        `main_dept_id`varchar(64)  NULL DEFAULT NULL COMMENT '发起人主部门id',
        `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
        `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
        `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
        `flow_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程id',
        `process_instance_id`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例id',
        `process_instance_biz_code`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例业务编码',
        `process_instance_biz_key`varchar(64)  NULL DEFAULT NULL COMMENT '流程实例业务key',
        `form_data` longtext  NULL COMMENT '表单数据',
        `group_id` varchar(64) NULL DEFAULT NULL COMMENT '组id',
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
-- Records of sys_process_instance_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_oper_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_oper_record`;
CREATE TABLE `sys_process_instance_oper_record`  (
         `id` varchar(64) NOT NULL COMMENT 'PK',
         `user_id` varchar(64)  NOT NULL COMMENT '用户id',
         `del_flag` int NOT NULL COMMENT '逻辑删除字段',
         `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
                 `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
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
-- Records of sys_process_instance_oper_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_node_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_node_record`;
CREATE TABLE `sys_process_instance_node_record`  (
     `id` varchar(64) NOT NULL COMMENT 'PK',
     `del_flag` int NOT NULL COMMENT '逻辑删除字段',
     `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
             `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
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
-- Records of sys_process_instance_node_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_execution
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_execution`;
CREATE TABLE `sys_process_instance_execution`  (
       `id` varchar(64) NOT NULL COMMENT 'PK',
       `del_flag` int NOT NULL COMMENT '逻辑删除字段',
       `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
               `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
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
-- Records of sys_process_instance_execution
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_copy
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_copy`;
CREATE TABLE `sys_process_instance_copy`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
      `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
              `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `create_time` datetime NOT NULL COMMENT '创建时间',
      `update_time` datetime NOT NULL COMMENT '更新时间',
      `start_time` datetime NOT NULL COMMENT ' 流程发起时间',
      `node_time` datetime NOT NULL COMMENT '当前节点时间',
      `start_user_id`varchar(64)  NOT NULL COMMENT '发起人',
      `flow_id` varchar(255)  NOT NULL COMMENT '流程id',
      `process_instance_id` varchar(255)  NOT NULL COMMENT '实例id',
      `node_id` varchar(255)  NOT NULL COMMENT '节点id',
      `group_id` varchar(64) NOT NULL COMMENT '分组id',
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
-- Records of sys_process_instance_copy
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_instance_assign_user_record
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_instance_assign_user_record`;
CREATE TABLE `sys_process_instance_assign_user_record`  (
        `id` varchar(64) NOT NULL COMMENT 'PK',
        `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
        `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
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
        `auto` int NULL DEFAULT 0 COMMENT '是否是自动完成',
        `parent_execution_id` varchar(255)  NULL DEFAULT NULL COMMENT '节点执行id',
        PRIMARY KEY (`id`) USING BTREE,
        INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '流程节点记录-执行人' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_instance_assign_user_record
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_group`;
CREATE TABLE `sys_process_group`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
      `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
      `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
      `group_name`varchar(64)  NOT NULL COMMENT '分组名',
      `process_group_sort` int NOT NULL DEFAULT 0 COMMENT '排序',
      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
      PRIMARY KEY (`id`) USING BTREE,
      INDEX `idx_id`(`id` ASC) USING BTREE
) ENGINE = InnoDB  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_process_group
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process_form
-- ----------------------------
DROP TABLE IF EXISTS `sys_process_form`;
CREATE TABLE `sys_process_form`  (
     `id` varchar(64) NOT NULL COMMENT 'PK',
     `del_flag` int NOT NULL COMMENT '逻辑删除字段',
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
-- Records of sys_process_form
-- ----------------------------

-- ----------------------------
-- Table structure for sys_process
-- ----------------------------
DROP TABLE IF EXISTS `sys_process`;
CREATE TABLE `sys_process`  (
        `id` varchar(64) NOT NULL COMMENT 'PK',
        `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
        `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
        `flow_id` varchar(40)  NOT NULL DEFAULT '' COMMENT '表单ID',
        `name`varchar(64)  NOT NULL COMMENT '表单名称',
        `logo` varchar(200)  NOT NULL COMMENT '图标配置',
        `settings` json NULL COMMENT '设置项',
        `group_id` varchar(64) NOT NULL COMMENT '分组ID',
        `form_items` json NOT NULL COMMENT '表单设置内容',
        `process` json NOT NULL COMMENT '流程设置内容',
        `remark` varchar(125)  NULL DEFAULT NULL COMMENT '备注',
        `process_sort` int NOT NULL,
        `is_hidden` int NOT NULL COMMENT '0 正常 1=隐藏',
        `is_stop` int NOT NULL COMMENT '0 正常 1=停用 ',
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
-- Records of sys_process
-- ----------------------------

-- ----------------------------
-- Table structure for sys_message
-- ----------------------------
DROP TABLE IF EXISTS `sys_message`;
CREATE TABLE `sys_message`  (
        `id` varchar(64) NOT NULL COMMENT 'PK',
        `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
         `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
        `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
        `message_type` varchar(100)  NOT   NULL COMMENT '类型',
        `readed` int NOT NULL COMMENT '是否已读',
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
-- Records of sys_message
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
         `id` varchar(64) NOT NULL COMMENT 'PK',
         `parent_id` varchar(64) NOT NULL COMMENT '父菜单ID',
         `tree_path` varchar(255)  NULL DEFAULT NULL COMMENT '父节点ID路径',
         `name` varchar(64)  NOT NULL DEFAULT '' COMMENT '菜单名称',
         `menu_type` int NOT NULL COMMENT '菜单类型(1:菜单；2:目录；3:外链；4:按钮)',
         `path` varchar(128)  NULL DEFAULT '' COMMENT '路由路径(浏览器地址栏路径)',
         `component` varchar(128)  NULL DEFAULT NULL COMMENT '组件路径(vue页面完整路径，省略.vue后缀)',
         `perm` varchar(128)  NULL DEFAULT NULL COMMENT '权限标识',
         `visible` int NOT NULL DEFAULT 1 COMMENT '显示状态(1-显示;0-隐藏)',
         `menu_sort` int NULL DEFAULT 0 COMMENT '排序',
         `icon` varchar(64)  NULL DEFAULT '' COMMENT '菜单图标',
         `redirect` varchar(128)  NULL DEFAULT NULL COMMENT '跳转路径',
         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
         `update_time` datetime NULL DEFAULT NULL COMMENT '更新时间',
         `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
                 `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
         `del_flag` int NOT NULL COMMENT '逻辑删除字段',
         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
         `ancestors` varchar(600)  NULL DEFAULT NULL COMMENT '祖级id路径(parentid/sonid/id)',
         `query_params`  varchar(255)  NULL DEFAULT NULL COMMENT '路由参数',
         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB  COMMENT = '菜单管理' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_dept_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_user`;
CREATE TABLE `sys_dept_user`  (
      `id` varchar(64) NOT NULL COMMENT 'PK',
      `dept_id`varchar(64)  NOT NULL DEFAULT '0' COMMENT '部门id',
      `del_flag` int NOT NULL COMMENT '逻辑删除字段',
      `user_id` varchar(64)  NOT NULL COMMENT 'user_id',
      `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
      `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
      `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
              `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
      `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
      PRIMARY KEY (`id`) USING BTREE,
      INDEX `idx_id`(`id` ASC) USING BTREE,
      INDEX `idx_parent_id`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门-主管表' ROW_FORMAT = DYNAMIC;



-- ----------------------------
-- Table structure for sys_dept_leader
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept_leader`;
CREATE TABLE `sys_dept_leader`  (
        `id` varchar(64) NOT NULL COMMENT '部门id',
        `dept_id`varchar(64)  NOT NULL DEFAULT '0' COMMENT '部门id',
        `del_flag` int NOT NULL COMMENT '逻辑删除字段',
        `user_id` varchar(64)  NOT NULL COMMENT '主管user_id',
        `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
        `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
        `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
                `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
        `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
        PRIMARY KEY (`id`) USING BTREE,
        INDEX `idx_id`(`id` ASC) USING BTREE,
        INDEX `idx_parent_id`(`dept_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门-主管表' ROW_FORMAT = DYNAMIC;



-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
         `id` varchar(64) NOT NULL COMMENT '部门id',
         `name`varchar(64)  NOT NULL COMMENT '部门名',
         `parent_id` varchar(64) NOT NULL DEFAULT 0 COMMENT '上级部门id',
         `del_flag` int NOT NULL COMMENT '逻辑删除字段',
         `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
         `update_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
         `create_by`    varchar(64)  NULL DEFAULT '' COMMENT '创建者',
         `update_by`    varchar(64)  NULL DEFAULT '' COMMENT '更新者',
         `status` int NOT NULL DEFAULT 1,
         `dept_sort` int NOT NULL DEFAULT 1,
         `tenant_id` varchar(100)  NULL DEFAULT NULL COMMENT '租户id',
         `ancestors` varchar(1000)  NULL DEFAULT NULL COMMENT '祖级列表',
         PRIMARY KEY (`id`) USING BTREE,
         INDEX `idx_id`(`id` ASC) USING BTREE,
         INDEX `idx_parent_id`(`parent_id` ASC) USING BTREE
) ENGINE = InnoDB  COMMENT = '部门表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for sys_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `sys_exception_log`;
CREATE TABLE `sys_exception_log`
(
    `id`    varchar(64)   NOT NULL COMMENT '参数主键',
    `exp_url`   varchar(500)   NOT NULL COMMENT '异常url',
    `exp_params`   varchar(1000)   NOT NULL COMMENT '异常参数',
    `exp_type`  varchar(64)  NULL DEFAULT '' COMMENT '异常类型',
    `exp_controller`  varchar(1000)  NULL DEFAULT '' COMMENT '异常controller',
    `exp_method`   varchar(1000)  NULL DEFAULT '' COMMENT '异常方法',
    `exp_detail`   text  NULL DEFAULT NULL COMMENT '异常详情',
    `create_by`   varchar(64)   NULL DEFAULT '' COMMENT '创建者',
    `create_time` datetime(0)   NULL DEFAULT NULL COMMENT '创建时间',
    `update_by`   varchar(64)   NULL DEFAULT '' COMMENT '更新者',
    `update_time` datetime(0)   NULL DEFAULT NULL COMMENT '更新时间',
    `remark`      varchar(500)  NULL DEFAULT NULL COMMENT '备注',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB    COMMENT = '异常日志表'   ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;