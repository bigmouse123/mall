/*
 Navicat Premium Data Transfer

 Source Server         : 无敌暴龙战士
 Source Server Type    : MySQL
 Source Server Version : 80036 (8.0.36)
 Source Host           : localhost:3306
 Source Schema         : mall

 Target Server Type    : MySQL
 Target Server Version : 80036 (8.0.36)
 File Encoding         : 65001

 Date: 26/03/2025 15:06:49
*/

CREATE DATABASE IF NOT EXISTS `mall`;
USE `mall`;

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`
(
    `id`          int                                                          NOT NULL AUTO_INCREMENT COMMENT '用户表id',
    `name`        varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户名',
    `password`    varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '用户密码，MD5加密',
    `email`       varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL,
    `phone`       varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL,
    `role`        tinyint(3) UNSIGNED ZEROFILL                                 NULL     DEFAULT 002 COMMENT '角色0-管理员,1-普通用户',
    `status`      tinyint                                                      NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`  tinyint                                                      NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `name_unique` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin`
VALUES (5, 'lisi', '123', '123@qq.com', '120', 001, 1, 0, '2025-03-08 23:00:36', '2025-03-08 23:07:06');
INSERT INTO `admin`
VALUES (6, 'zhangsan', '123', '555@126.com', '119', 001, 1, 0, '2025-03-08 23:00:56', '2025-03-08 23:00:56');
INSERT INTO `admin`
VALUES (7, '李建坤', '321', '173293@qq.com', '159', 001, 1, 0, '2025-03-08 23:01:10', '2025-03-18 20:49:22');
INSERT INTO `admin`
VALUES (8, 'asd', '666', '234', '1234', 002, 0, 0, '2025-03-09 23:18:15', '2025-03-09 23:28:15');
INSERT INTO `admin`
VALUES (14, 'sili', '12345678', '111@qq.com', '11111111111', 001, 0, 0, '2025-03-10 09:00:52', '2025-03-18 20:49:33');
INSERT INTO `admin`
VALUES (15, '王', '555', '777', '666', 002, 1, 0, '2025-03-18 20:49:55', '2025-03-18 20:49:55');

-- ----------------------------
-- Table structure for cart
-- ----------------------------
DROP TABLE IF EXISTS `cart`;
CREATE TABLE `cart`
(
    `id`          int      NOT NULL AUTO_INCREMENT,
    `user_id`     int      NOT NULL,
    `product_id`  int      NULL     DEFAULT NULL COMMENT '商品id',
    `quantity`    int      NULL     DEFAULT NULL COMMENT '数量',
    `checked`     int      NULL     DEFAULT 1 COMMENT '是否选择,1=已勾选,0=未勾选',
    `status`      tinyint  NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`  tinyint  NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `user_id_index` (`user_id` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 74
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of cart
-- ----------------------------
INSERT INTO `cart`
VALUES (51, 2, 176, 3, 1, 1, 0, '2025-03-14 20:00:14', '2025-03-14 23:29:34');
INSERT INTO `cart`
VALUES (56, 2, 158, 3, 1, 1, 0, '2025-03-14 22:28:33', '2025-03-14 23:42:20');
INSERT INTO `cart`
VALUES (58, 2, 171, 1, 1, 1, 0, '2025-03-14 23:41:58', '2025-03-14 23:41:58');
INSERT INTO `cart`
VALUES (67, 1, 176, 20, 0, 1, 0, '2025-03-17 17:03:03', '2025-03-18 08:47:34');

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category`
(
    `id`          int                                                          NOT NULL AUTO_INCREMENT COMMENT '类别Id',
    `parent_id`   int                                                          NULL     DEFAULT NULL COMMENT '父类别id当id=0时说明是根节点,一级类别',
    `name`        varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '类别名称',
    `status`      tinyint                                                      NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`  tinyint                                                      NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                     NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 100044
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category`
VALUES (100001, 0, '家用电器', 1, 1, '2017-03-25 16:46:00', '2017-03-25 16:46:00');
INSERT INTO `category`
VALUES (100002, 0, '数码3C', 1, 1, '2017-03-25 16:46:21', '2017-03-25 16:46:21');
INSERT INTO `category`
VALUES (100003, 0, '服装箱包', 1, 1, '2017-03-25 16:49:53', '2017-03-25 16:49:53');
INSERT INTO `category`
VALUES (100004, 0, '食品生鲜', 1, 1, '2017-03-25 16:50:19', '2017-03-25 16:50:19');
INSERT INTO `category`
VALUES (100005, 0, '酒水饮料', 1, 1, '2017-03-25 16:50:29', '2017-03-25 16:50:29');
INSERT INTO `category`
VALUES (100006, 100001, '冰箱11', 1, 1, '2017-03-25 16:52:15', '2017-03-25 16:52:15');
INSERT INTO `category`
VALUES (100007, 100001, '电视', 1, 1, '2017-03-25 16:52:26', '2017-03-25 16:52:26');
INSERT INTO `category`
VALUES (100008, 100001, '洗衣机', 1, 1, '2017-03-25 16:52:39', '2017-03-25 16:52:39');
INSERT INTO `category`
VALUES (100009, 100001, '空调', 1, 1, '2017-03-25 16:52:45', '2017-03-25 16:52:45');
INSERT INTO `category`
VALUES (100010, 100001, '电热水器', 1, 1, '2017-03-25 16:52:54', '2017-03-25 16:52:54');
INSERT INTO `category`
VALUES (100011, 100002, '电脑', 1, 1, '2017-03-25 16:53:18', '2017-03-25 16:53:18');
INSERT INTO `category`
VALUES (100012, 100002, '手机', 1, 1, '2017-03-25 16:53:27', '2017-03-25 16:53:27');
INSERT INTO `category`
VALUES (100013, 100002, '平板电脑', 1, 1, '2017-03-25 16:53:35', '2017-03-25 16:53:35');
INSERT INTO `category`
VALUES (100014, 100002, '数码相机', 1, 1, '2017-03-25 16:53:56', '2017-03-25 16:53:56');
INSERT INTO `category`
VALUES (100015, 100002, '3C配件', 1, 1, '2017-03-25 16:54:07', '2017-03-25 16:54:07');
INSERT INTO `category`
VALUES (100016, 100003, '女装', 1, 1, '2017-03-25 16:54:44', '2017-03-25 16:54:44');
INSERT INTO `category`
VALUES (100017, 100003, '帽子', 1, 1, '2017-03-25 16:54:51', '2017-03-25 16:54:51');
INSERT INTO `category`
VALUES (100018, 100003, '旅行箱', 1, 1, '2017-03-25 16:55:02', '2017-03-25 16:55:02');
INSERT INTO `category`
VALUES (100019, 100003, '手提包', 1, 1, '2017-03-25 16:55:09', '2017-03-25 16:55:09');
INSERT INTO `category`
VALUES (100020, 100003, '保暖内衣', 1, 1, '2017-03-25 16:55:18', '2017-03-25 16:55:18');
INSERT INTO `category`
VALUES (100021, 100004, '零食', 1, 1, '2017-03-25 16:55:30', '2017-03-25 16:55:30');
INSERT INTO `category`
VALUES (100022, 100004, '生鲜', 1, 1, '2017-03-25 16:55:37', '2017-03-25 16:55:37');
INSERT INTO `category`
VALUES (100023, 100004, '半成品菜', 1, 1, '2017-03-25 16:55:47', '2017-03-25 16:55:47');
INSERT INTO `category`
VALUES (100024, 100004, '速冻食品', 1, 1, '2017-03-25 16:55:56', '2017-03-25 16:55:56');
INSERT INTO `category`
VALUES (100025, 100004, '进口食品', 1, 1, '2017-03-25 16:56:06', '2017-03-25 16:56:06');
INSERT INTO `category`
VALUES (100026, 100005, '白酒', 1, 1, '2017-03-25 16:56:22', '2017-03-25 16:56:22');
INSERT INTO `category`
VALUES (100027, 100005, '红酒', 1, 1, '2017-03-25 16:56:30', '2017-03-25 16:56:30');
INSERT INTO `category`
VALUES (100028, 100005, '饮料', 1, 1, '2017-03-25 16:56:37', '2017-03-25 16:56:37');
INSERT INTO `category`
VALUES (100029, 100005, '调制鸡尾酒', 1, 1, '2017-03-25 16:56:45', '2017-03-25 16:56:45');
INSERT INTO `category`
VALUES (100030, 100005, '进口洋酒', 1, 1, '2017-03-25 16:57:05', '2017-03-25 16:57:05');
INSERT INTO `category`
VALUES (100031, 100002, '耳机', 1, 0, '2025-03-11 11:14:11', '2025-03-11 11:14:11');
INSERT INTO `category`
VALUES (100032, 0, '书籍', 1, 0, '2025-03-11 11:18:15', '2025-03-11 11:18:15');
INSERT INTO `category`
VALUES (100033, 100032, '考研图书', 1, 0, '2025-03-11 11:18:48', '2025-03-11 11:18:48');

-- ----------------------------
-- Table structure for login_log
-- ----------------------------
DROP TABLE IF EXISTS `login_log`;
CREATE TABLE `login_log`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT '访问ID',
    `admin_id`    int                                                           NULL     DEFAULT NULL COMMENT '用户id',
    `admin_name`  varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci  NOT NULL COMMENT '用户名',
    `ip`          varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT '' COMMENT '登录IP地址',
    `msg`         varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL     DEFAULT '' COMMENT '提示信息',
    `access_time` datetime                                                      NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '访问时间',
    `status`      tinyint                                                       NOT NULL DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci
  ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of login_log
-- ----------------------------
INSERT INTO `login_log`
VALUES (2, 5, 'lisi', '0:0:0:0:0:0:0:1', '登录成功', '2025-03-18 21:21:56', 1, 0, '2025-03-18 21:21:56',
        '2025-03-18 21:21:56');

-- ----------------------------
-- Table structure for oper_log
-- ----------------------------
DROP TABLE IF EXISTS `oper_log`;
CREATE TABLE `oper_log`
(
    `id`              int                                                           NOT NULL AUTO_INCREMENT,
    `module`          varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '模块标题',
    `log_type`        varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '0' COMMENT '日志类型',
    `admin_id`        int                                                           NULL     DEFAULT NULL COMMENT '操作人员id',
    `admin_name`      varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '操作人员',
    `request_method`  varchar(10) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT '' COMMENT '请求方式GET/POST',
    `request_uri`     varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT '' COMMENT '请求URI',
    `request_params`  text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci         NULL COMMENT '请求参数',
    `response_params` text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci         NULL COMMENT '返回参数',
    `request_ip`      varchar(128) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT '' COMMENT '主机地址',
    `server_address`  varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '请求服务器地址',
    `is_exception`    tinyint                                                       NULL     DEFAULT 0 COMMENT '是否异常 1是异常，0不是',
    `exception_msg`   text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci         NULL COMMENT '异常信息',
    `start_time`      datetime                                                      NOT NULL COMMENT '开始时间',
    `end_time`        datetime                                                      NOT NULL COMMENT '结束时间',
    `execute_time`    int                                                           NULL     DEFAULT NULL COMMENT '执行时间',
    `user_agent`      varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '用户代理',
    `device_name`     varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '操作系统',
    `browser_name`    varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '浏览器名称',
    `is_deleted`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time`     datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci COMMENT = '操作日志记录'
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of oper_log
-- ----------------------------
INSERT INTO `oper_log`
VALUES (1, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":5,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"1732@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1741446353000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1741568452000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-18 20:48:57', '2025-03-18 20:48:58', 498,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:48:57', '2025-03-18 20:48:57');
INSERT INTO `oper_log`
VALUES (2, NULL, 'INFO', NULL, NULL, 'POST', '/admin/selectById', 'id=7',
        '{\"code\":0,\"msg\":null,\"data\":{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"1732@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1741446353000}}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-18 20:49:18', '2025-03-18 20:49:18', 4,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:17', '2025-03-18 20:49:17');
INSERT INTO `oper_log`
VALUES (3, NULL, 'INFO', NULL, NULL, 'POST', '/admin/update',
        'id=7&name=李建坤&password=*&role=1&phone=159&email=173293@qq.com',
        '{\"code\":0,\"msg\":\"更新成功\",\"data\":null}', '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL,
        '2025-03-18 20:49:22', '2025-03-18 20:49:22', 11,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:22', '2025-03-18 20:49:22');
INSERT INTO `oper_log`
VALUES (4, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":5,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1741568452000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-18 20:49:25', '2025-03-18 20:49:25', 14,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:24', '2025-03-18 20:49:24');
INSERT INTO `oper_log`
VALUES (5, NULL, 'INFO', NULL, NULL, 'POST', '/admin/updateStatus', 'id=14&status=0',
        '{\"code\":0,\"msg\":null,\"data\":null}', '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL,
        '2025-03-18 20:49:34', '2025-03-18 20:49:34', 7,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:33', '2025-03-18 20:49:33');
INSERT INTO `oper_log`
VALUES (6, NULL, 'INFO', NULL, NULL, 'POST', '/admin/add', 'name=王&password=*&role=2&phone=666&email=777',
        '{\"code\":0,\"msg\":\"添加成功\",\"data\":null}', '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL,
        '2025-03-18 20:49:55', '2025-03-18 20:49:55', 8,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:55', '2025-03-18 20:49:55');
INSERT INTO `oper_log`
VALUES (7, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-18 20:49:57', '2025-03-18 20:49:58', 18,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:49:57', '2025-03-18 20:49:57');
INSERT INTO `oper_log`
VALUES (8, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-18 20:56:18', '2025-03-18 20:56:18', 566,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 20:56:18', '2025-03-18 20:56:18');
INSERT INTO `oper_log`
VALUES (9, NULL, 'INFO', NULL, NULL, 'POST', '/admin/login', 'name=lisi&password=*&code=5954',
        '{\"code\":0,\"msg\":\"登录成功\",\"data\":null}', '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL,
        '2025-03-18 21:18:30', '2025-03-18 21:18:31', 444,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 21:18:30', '2025-03-18 21:18:30');
INSERT INTO `oper_log`
VALUES (10, NULL, 'INFO', NULL, NULL, 'POST', '/admin/login', 'name=lisi&password=*&code=4934',
        '{\"code\":0,\"msg\":\"登录成功\",\"data\":null}', '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL,
        '2025-03-18 21:21:56', '2025-03-18 21:21:57', 523,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-18 21:21:56', '2025-03-18 21:21:56');
INSERT INTO `oper_log`
VALUES (11, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-20 14:08:46', '2025-03-20 14:08:47', 533,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-20 14:08:46', '2025-03-20 14:08:46');
INSERT INTO `oper_log`
VALUES (12, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-20 17:29:34', '2025-03-20 17:29:34', 396,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-20 17:29:33', '2025-03-20 17:29:33');
INSERT INTO `oper_log`
VALUES (13, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-20 17:30:27', '2025-03-20 17:30:27', 528,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-20 17:30:27', '2025-03-20 17:30:27');
INSERT INTO `oper_log`
VALUES (14, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-20 17:31:37', '2025-03-20 17:31:37', 429,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-20 17:31:37', '2025-03-20 17:31:37');
INSERT INTO `oper_log`
VALUES (15, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-23 12:19:57', '2025-03-23 12:19:57', 461,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-23 12:19:57', '2025-03-23 12:19:57');
INSERT INTO `oper_log`
VALUES (16, NULL, 'INFO', NULL, NULL, 'GET', '/admin/list', 'page=1&limit=10',
        '{\"code\":0,\"msg\":\"\",\"count\":6,\"data\":[{\"id\":5,\"name\":\"lisi\",\"password\":\"123\",\"email\":\"123@qq.com\",\"phone\":\"120\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446036000,\"updateTime\":1741446426000},{\"id\":6,\"name\":\"zhangsan\",\"password\":\"123\",\"email\":\"555@126.com\",\"phone\":\"119\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446056000,\"updateTime\":1741446056000},{\"id\":7,\"name\":\"李建坤\",\"password\":\"321\",\"email\":\"173293@qq.com\",\"phone\":\"159\",\"role\":1,\"status\":1,\"isDeleted\":0,\"createTime\":1741446070000,\"updateTime\":1742302162000},{\"id\":8,\"name\":\"asd\",\"password\":\"666\",\"email\":\"234\",\"phone\":\"1234\",\"role\":2,\"status\":0,\"isDeleted\":0,\"createTime\":1741533495000,\"updateTime\":1741534095000},{\"id\":14,\"name\":\"sili\",\"password\":\"12345678\",\"email\":\"111@qq.com\",\"phone\":\"11111111111\",\"role\":1,\"status\":0,\"isDeleted\":0,\"createTime\":1741568452000,\"updateTime\":1742302173000},{\"id\":15,\"name\":\"王\",\"password\":\"555\",\"email\":\"777\",\"phone\":\"666\",\"role\":2,\"status\":1,\"isDeleted\":0,\"createTime\":1742302195000,\"updateTime\":1742302195000}]}',
        '0:0:0:0:0:0:0:1', 'http://localhost:8080', 0, NULL, '2025-03-24 20:31:19', '2025-03-24 20:31:20', 861,
        'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/134.0.0.0 Safari/537.36',
        'Windows 10 or Windows Server 2016', 'Chrome', 0, '2025-03-24 20:31:19', '2025-03-24 20:31:19');

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`
(
    `order_no`     bigint         NOT NULL COMMENT '订单号',
    `user_id`      int            NULL     DEFAULT NULL COMMENT '用户id',
    `shipping_id`  int            NULL     DEFAULT NULL,
    `payment`      decimal(20, 2) NULL     DEFAULT NULL COMMENT '实际付款金额,单位是元,保留两位小数',
    `payment_type` int            NULL     DEFAULT NULL COMMENT '0:货到付款 1：微信 2：支付宝 3：银联',
    `postage`      int            NULL     DEFAULT NULL COMMENT '运费,单位是元',
    `payment_time` datetime       NULL     DEFAULT NULL COMMENT '支付时间',
    `send_time`    datetime       NULL     DEFAULT NULL COMMENT '发货时间',
    `end_time`     datetime       NULL     DEFAULT NULL COMMENT '交易完成时间',
    `close_time`   datetime       NULL     DEFAULT NULL COMMENT '交易关闭时间',
    `status`       int            NULL     DEFAULT 1 COMMENT '订单状态:0-已取消-1-未付款，2-已付款，3-已发货，4-交易成功，5-交易关闭',
    `is_deleted`   tinyint        NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`  datetime       NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`order_no`) USING BTREE,
    UNIQUE INDEX `order_no_index` (`order_no` ASC) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order`
VALUES (556765188980871168, 1, 9, 71250.00, 2, NULL, NULL, NULL, NULL, NULL, 2, 0, '2025-03-17 17:06:05',
        '2025-03-24 19:08:53');
INSERT INTO `order`
VALUES (556765400524787712, 1, 7, 60800.00, 1, NULL, NULL, NULL, NULL, NULL, 2, 0, '2025-03-17 17:06:55',
        '2025-03-24 19:08:54');
INSERT INTO `order`
VALUES (556768814092652544, 1, 7, 11.00, 0, NULL, NULL, NULL, NULL, NULL, 2, 0, '2025-03-17 17:20:29',
        '2025-03-24 19:08:56');
INSERT INTO `order`
VALUES (557030996353093632, 1, 7, 20000.00, 3, NULL, NULL, NULL, NULL, NULL, 2, 0, '2025-03-18 10:42:18',
        '2025-03-24 19:09:00');
INSERT INTO `order`
VALUES (559353976785604608, 1, 7, 112000.00, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2025-03-24 20:33:00',
        '2025-03-24 20:33:56');
INSERT INTO `order`
VALUES (559563947976560640, 1, 9, 7000.00, 1, NULL, NULL, NULL, NULL, NULL, 1, 0, '2025-03-25 10:27:21',
        '2025-03-25 10:27:21');
INSERT INTO `order`
VALUES (559566147024982016, 1, 7, 7000.00, 1, NULL, NULL, NULL, NULL, NULL, 0, 0, '2025-03-25 10:36:05',
        '2025-03-25 10:38:05');
INSERT INTO `order`
VALUES (559567400979271680, 1, 7, 5000.00, 2, NULL, NULL, NULL, NULL, NULL, 2, 0, '2025-03-25 10:41:04',
        '2025-03-25 10:41:53');

-- ----------------------------
-- Table structure for order_item
-- ----------------------------
DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item`
(
    `id`                 int                                                           NOT NULL AUTO_INCREMENT COMMENT '订单子表id',
    `user_id`            int                                                           NULL     DEFAULT NULL,
    `order_no`           bigint                                                        NULL     DEFAULT NULL,
    `product_id`         int                                                           NULL     DEFAULT NULL COMMENT '商品id',
    `product_name`       varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '商品名称',
    `product_image`      varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '商品图片地址',
    `current_unit_price` decimal(20, 2)                                                NULL     DEFAULT NULL COMMENT '生成订单时的商品单价，单位是元,保留两位小数',
    `quantity`           int                                                           NULL     DEFAULT NULL COMMENT '商品数量',
    `total_price`        decimal(20, 2)                                                NULL     DEFAULT NULL COMMENT '商品总价,单位是元,保留两位小数',
    `status`             tinyint                                                       NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`         tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time`        datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`        datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `order_no_index` (`order_no` ASC) USING BTREE,
    INDEX `order_no_user_id_index` (`user_id` ASC, `order_no` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 33
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of order_item
-- ----------------------------
INSERT INTO `order_item`
VALUES (21, 1, 556765188980871168, 173, 'oppo',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/a33122e46bf1479b966c844025bbbb45.jpg', 3000.00, 7, 21000.00, 1, 0,
        '2025-03-17 17:06:05', '2025-03-17 17:06:05');
INSERT INTO `order_item`
VALUES (22, 1, 556765188980871168, 174, 'vivo',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/16452c03a9e34b8fa43c41e4a1b4d3af.jpg', 2500.00, 6, 15000.00, 1, 0,
        '2025-03-17 17:06:05', '2025-03-17 17:06:05');
INSERT INTO `order_item`
VALUES (23, 1, 556765188980871168, 175, 'realme',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/1ade98d4c03840108c0ae86c8d3c5a36.png', 2350.00, 15, 35250.00, 1,
        0, '2025-03-17 17:06:05', '2025-03-17 17:06:05');
INSERT INTO `order_item`
VALUES (24, 1, 556765400524787712, 171, '基础30讲',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/64ab8ee094664432b35a2ba990bc1b2c.png', 400.00, 2, 800.00, 1, 0,
        '2025-03-17 17:06:55', '2025-03-17 17:06:55');
INSERT INTO `order_item`
VALUES (25, 1, 556765400524787712, 159, '海尔冰箱', '', 5000.00, 12, 60000.00, 1, 0, '2025-03-17 17:06:55',
        '2025-03-17 17:06:55');
INSERT INTO `order_item`
VALUES (26, 1, 556768814092652544, 166, 'ads',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/8b561ebfc40a4bb38e3d643dc5dd5820.jpg', 11.00, 1, 11.00, 1, 0,
        '2025-03-17 17:20:29', '2025-03-17 17:20:29');
INSERT INTO `order_item`
VALUES (27, 1, 557030996353093632, 158, '红米K80', '', 2500.00, 8, 20000.00, 1, 0, '2025-03-18 10:42:18',
        '2025-03-18 10:42:18');
INSERT INTO `order_item`
VALUES (28, 1, 559353976785604608, 172, '华为mate60',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/d5ec02709aa84e2cab24a35f1388e5b4.jpg', 7000.00, 5, 35000.00, 1, 0,
        '2025-03-24 20:33:00', '2025-03-24 20:33:00');
INSERT INTO `order_item`
VALUES (29, 1, 559353976785604608, 157, 'iPhone',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/ae1b49b399d74190b3396b018df97fe2.jpg', 7000.00, 11, 77000.00, 1,
        0, '2025-03-24 20:33:00', '2025-03-24 20:33:00');
INSERT INTO `order_item`
VALUES (30, 1, 559563947976560640, 157, 'iPhone',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/ae1b49b399d74190b3396b018df97fe2.jpg', 7000.00, 1, 7000.00, 1, 0,
        '2025-03-25 10:27:21', '2025-03-25 10:27:21');
INSERT INTO `order_item`
VALUES (31, 1, 559566147024982016, 157, 'iPhone',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/ae1b49b399d74190b3396b018df97fe2.jpg', 7000.00, 1, 7000.00, 1, 0,
        '2025-03-25 10:36:05', '2025-03-25 10:36:05');
INSERT INTO `order_item`
VALUES (32, 1, 559567400979271680, 159, '海尔冰箱', '', 5000.00, 1, 5000.00, 1, 0, '2025-03-25 10:41:04',
        '2025-03-25 10:41:04');

-- ----------------------------
-- Table structure for pay_info
-- ----------------------------
DROP TABLE IF EXISTS `pay_info`;
CREATE TABLE `pay_info`
(
    `id`              int                                                           NOT NULL AUTO_INCREMENT,
    `user_id`         int                                                           NULL     DEFAULT NULL COMMENT '用户id',
    `order_no`        bigint                                                        NULL     DEFAULT NULL COMMENT '订单号',
    `pay_platform`    int                                                           NULL     DEFAULT NULL COMMENT '支付平台:1-支付宝,2-微信',
    `platform_number` varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '支付宝支付流水号',
    `platform_status` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '支付宝支付状态',
    `status`          tinyint                                                       NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`      tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time`     datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`     datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of pay_info
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `category_id` int                                                           NULL     DEFAULT NULL COMMENT '分类id,对应category表的主键',
    `name`        varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '商品名称',
    `subtitle`    varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '商品副标题',
    `main_image`  varchar(500) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '产品主图,url相对地址',
    `sub_images`  text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci         NULL COMMENT '图片地址,json格式,扩展用',
    `detail`      text CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci         NULL COMMENT '商品详情',
    `price`       decimal(20, 2)                                                NOT NULL COMMENT '价格,单位-元保留两位小数',
    `stock`       int                                                           NOT NULL COMMENT '库存数量',
    `status`      int                                                           NULL     DEFAULT 1 COMMENT '商品状态.1-在售 0-下架',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `NewIndex1` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 177
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product`
VALUES (157, 100012, 'iPhone', NULL,
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/ae1b49b399d74190b3396b018df97fe2.jpg', NULL,
        '<p>\n	<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/1.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/2.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/3.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/4.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/13.gif\" border=\"0\" alt=\"\" />\n</p>\n<p>\n	asdfg11\n</p>',
        7000.00, 20, 1, 0, '2025-03-24 20:31:02', '2025-03-24 20:31:48');
INSERT INTO `product`
VALUES (158, 100012, '红米K80', NULL, '', NULL, '4424242', 2500.00, 100, 1, 0, '2025-03-07 22:08:26',
        '2025-03-23 12:21:12');
INSERT INTO `product`
VALUES (159, 100006, '海尔冰箱', NULL, '', NULL, NULL, 5000.00, 10, 1, 0, '2025-03-08 16:29:56', '2025-03-08 16:29:56');
INSERT INTO `product`
VALUES (160, 100013, '华为平板', NULL, '', NULL, NULL, 5500.00, 5, 1, 0, '2025-03-08 16:30:24', '2025-03-08 17:14:30');
INSERT INTO `product`
VALUES (166, 100006, 'ads', NULL, 'https://mall-ljk.oss-cn-beijing.aliyuncs.com/8b561ebfc40a4bb38e3d643dc5dd5820.jpg',
        NULL,
        '<p>\n	中享思途\n</p>\n<p>\n	<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/13.gif\" border=\"0\" alt=\"\" />\n</p>',
        11.00, 11, 1, 0, '2025-03-09 02:14:48', '2025-03-23 12:21:32');
INSERT INTO `product`
VALUES (167, 100026, 'bbb', 'adasdasdsad',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/b7010e63518643d39f5b1a53cae871dd.png', NULL,
        '<p>\n	aaa<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/11.gif\" border=\"0\" alt=\"\" />adadsadasdsadasda\n</p>\n<p>\n	adasdlk<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/10.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/31.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/34.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/42.gif\" border=\"0\" alt=\"\" /><img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/36.gif\" border=\"0\" alt=\"\" />\n</p>',
        30.00, 20, 1, 0, '2025-03-10 10:34:06', '2025-03-11 09:18:31');
INSERT INTO `product`
VALUES (168, 100016, 'abcd', 'asdasdas',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/a7277544360a439ab9e78171bb3cdd8b.jpg', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/10.gif\" border=\"0\" alt=\"\" />',
        30.00, 20, 1, 0, '2025-03-10 21:20:29', '2025-03-10 21:21:00');
INSERT INTO `product`
VALUES (169, 100020, 'asd', 'sdadas',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/7ec24cf3e2ea41a4b7b5a41cbbb1b740.png', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/10.gif\" border=\"0\" alt=\"\" />',
        20.00, 30, 1, 0, '2025-03-11 09:17:31', '2025-03-11 10:37:02');
INSERT INTO `product`
VALUES (170, 100031, '漫步者', 'adasdasdadsa',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/3c16647e9f0c4233b8aa3a44e63a6c82.jpg', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/11.gif\" border=\"0\" alt=\"\" />',
        300.00, 20, 1, 0, '2025-03-11 11:15:52', '2025-03-11 11:15:52');
INSERT INTO `product`
VALUES (171, 100033, '基础30讲', 'SDAASD',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/64ab8ee094664432b35a2ba990bc1b2c.png', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/110.gif\" border=\"0\" alt=\"\" />',
        400.00, 10, 1, 0, '2025-03-11 11:20:33', '2025-03-11 11:20:33');
INSERT INTO `product`
VALUES (172, 100012, '华为mate60', '123545',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/d5ec02709aa84e2cab24a35f1388e5b4.jpg', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/20.gif\" border=\"0\" alt=\"\" />',
        7000.00, 100, 1, 0, '2025-03-14 10:44:34', '2025-03-14 10:44:34');
INSERT INTO `product`
VALUES (173, 100012, 'oppo', 'adsd',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/a33122e46bf1479b966c844025bbbb45.jpg', NULL,
        'adasdsa<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/11.gif\" border=\"0\" alt=\"\" />',
        3000.00, 20, 1, 0, '2025-03-14 10:45:05', '2025-03-14 10:45:05');
INSERT INTO `product`
VALUES (174, 100012, 'vivo', '5464',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/16452c03a9e34b8fa43c41e4a1b4d3af.jpg', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/2.gif\" border=\"0\" alt=\"\" />',
        2500.00, 50, 1, 0, '2025-03-14 10:45:33', '2025-03-14 10:45:33');
INSERT INTO `product`
VALUES (175, 100012, 'realme', 'sfad',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/1ade98d4c03840108c0ae86c8d3c5a36.png', NULL,
        '<img src=\"http://localhost:8080/static/kindeditor/plugins/emoticons/images/10.gif\" border=\"0\" alt=\"\" />',
        2350.00, 35, 1, 0, '2025-03-14 10:46:00', '2025-03-14 10:46:00');
INSERT INTO `product`
VALUES (176, 100012, 'Xiaomi 15',
        '徕卡光学 Summilux 高速镜头｜骁龙®8至尊版移动平台｜5400mAh 小米金沙江电池 小米澎湃OS 2｜全生态AI助手 超级小爱｜低功耗超级阳光屏',
        'https://mall-ljk.oss-cn-beijing.aliyuncs.com/3f3ac94040924d7488f7a8a41b3a917d.png', NULL,
        '<div class=\"ETab\" id=\"detail\" style=\"margin:0px;padding:0px;color:#666666;font-family:tahoma, arial, &quot;background-color:#FFFFFF;\">\n	<div class=\"tab-con\" style=\"margin:0px;padding:10px 0px;\">\n		<div style=\"margin:0px;padding:0px;\">\n			<div class=\"p-parameter\" style=\"margin:0px 0px 10px;padding:0px 10px 10px;\">\n				<ul id=\"parameter-brand\" class=\"p-parameter-list\">\n					<li>\n						品牌：&nbsp;<a href=\"https://list.jd.com/list.html?cat=1315,1342,9738&amp;ev=exbrand_790328\" target=\"_blank\">法蒙泽（FAMENGZE）</a>\n					</li>\n				</ul>\n				<ul class=\"parameter2 p-parameter-list\">\n					<li>\n						商品名称：法蒙泽西服套装男士三件套商务职业正装小西装男结婚新郎上班修身外套男 黑色(西服+西裤+衬衫+大礼包) 3XL【140-155斤】\n					</li>\n					<li class=\"shieldShopInfo\">\n						商品编号：10076667054065\n					</li>\n					<li class=\"shieldShopInfo\">\n						店铺：&nbsp;<a href=\"https://mall.jd.com/index-11933299.html?from=pc\" target=\"_blank\">法蒙泽旗舰店</a>\n					</li>\n					<li>\n						货号：FMZ-879520\n					</li>\n					<li>\n						材质：涤纶(聚酯纤维)78%\n					</li>\n					<li>\n						版型：修身型\n					</li>\n					<li>\n						适用季节：四季通用\n					</li>\n					<li>\n						上市时间：2023年夏季\n					</li>\n					<li>\n						领型：平驳领\n					</li>\n					<li>\n						适用人群：青年\n					</li>\n					<li>\n						适用场景：职场，婚礼\n					</li>\n					<li>\n						衣门襟：单排扣\n					</li>\n					<li>\n						厚度：常规\n					</li>\n					<li>\n						开衩设计：后中开衩\n					</li>\n					<li>\n						风格：通勤风\n					</li>\n					<li>\n						通勤风：商务正装\n					</li>\n				</ul>\n			</div>\n			<div id=\"suyuan-video\" style=\"margin:0px;padding:0px;\">\n			</div>\n			<div id=\"J-detail-banner\" style=\"margin:0px;padding:0px;text-align:center;\">\n			</div>\n			<div id=\"J-detail-pop-poster\" style=\"margin:0px;padding:0px;\">\n			</div>\n			<div id=\"J-detail-pop-tpl-top-new\" style=\"margin:0px;padding:0px;\">\n			</div>\n			<div class=\"detail-content clearfix\" style=\"margin:10px 0px;padding:0px;background:#F7F7F7;\">\n				<div class=\"detail-content-wrap\" style=\"margin:0px;padding:0px;background-color:#FFFFFF;\">\n					<div class=\"detail-content-item\" style=\"margin:0px;padding:0px;\">\n						<div id=\"J-detail-top\" style=\"margin:0px;padding:0px;\">\n						</div>\n						<div id=\"J-detail-content\" style=\"margin:0px;padding:0px;font-family:pingfangSC;font-size:14px;color:#757575;\">\n							<p>\n								<img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/115239/32/36554/136411/6476c235F66103679/a7d592324343d923.jpg.avif\" style=\"width:auto;height:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/150060/40/33739/59431/6476c235F069d9714/53ac39147244d83a.jpg.avif\" style=\"width:auto;height:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/98858/17/40572/68900/6476c235Fcf1129f3/aeb687d4bb21f277.jpg.avif\" style=\"width:auto;height:auto;\" /><img class=\"shop-editor-zone-insert\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/9579/3/24860/94000/66d17c38F52657ac7/3d116df59fe726c6.jpg.avif\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/91533/28/34650/68745/6476c235F1f2b49cb/89ecebac789cb621.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/142540/31/32670/89150/6476c236Fde6d9e05/013126cb2cfa554e.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/73213/8/25258/66113/6476c236Ff4361ae5/ef29c5a3c522e8ae.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/214293/18/31130/42247/6476c236F0e08a759/1595cced9c6bb7be.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/106292/31/26730/102325/6476c236F9ff62319/84db4eabe1eddcdc.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/147308/5/37601/49313/6476c237F007757c4/0589e9e57bb1c2b8.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/115369/15/36270/24022/6476c237F16173bfe/b68b09d78ce1060a.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/207872/5/34253/38487/6476c237F7b0e7b71/a7f9c2409b7d33b3.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/140811/15/36865/22104/6476c237F6331d64a/90ec30e8eeb2d491.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/48933/29/28422/58139/6476c237Fd37c0049/00a66e79c95914fa.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/123628/31/37597/31742/6476c237Fac11d8c5/448ac8974cb5e62c.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/108590/23/38741/55699/6476c238Fb6025e4c/c0aa3137779d5ba4.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/117040/21/37453/29415/6476c238F7f3bc30a/d0aefb35b3467457.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/94459/24/39841/95021/6476c238F1f4faec3/9f6c96d7bd3b0417.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/133237/15/33375/92550/6476c238F6bb559c3/81d60990a2f12b80.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/95425/7/41553/96578/6476c238Fc9130aae/c7623ef76831bc63.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/93073/3/40270/95431/6476c257F61bb800b/70526a21fb062106.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/203221/3/34721/62272/6476c257F96937160/cef83e23bdde9d94.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/118165/1/34787/42202/6476c257Fcf2dcc54/5c679fcc74469516.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/136006/11/35169/48074/6476c257F2a71506b/4f16162f075959e7.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/138161/2/37465/39969/6476c257Fbdf2f9f8/6017a1024e135510.jpg.avif\" style=\"width:auto;\" /><img class=\"\" src=\"https://img10.360buyimg.com/imgzone/jfs/t1/134302/11/33649/24112/6476c257Fcc3959f4/e9fa9d1a7fa9b250.jpg.avif\" style=\"width:auto;\" />\n							</p>\n							<p>\n								<br />\n							</p>\n<br />\n						</div>\n						<div id=\"J-detail-bottom\" style=\"margin:0px;padding:0px;\">\n						</div>\n						<div id=\"activity_footer\" style=\"margin:0px;padding:0px;\">\n						</div>\n					</div>\n				</div>\n			</div>\n			<div id=\"J-detail-pop-tpl-bottom-new\" style=\"margin:0px;padding:0px;\">\n			</div>\n			<div class=\"clb\" style=\"margin:0px;padding:0px;\">\n			</div>\n		</div>\n	</div>\n</div>\n<div class=\"m m-content guarantee\" id=\"guarantee\" style=\"margin:0px 0px 15px;padding:0px;color:#666666;font-family:tahoma, arial, &quot;background-color:#FFFFFF;\">\n	<div class=\"mt\" style=\"margin:0px;padding:10px;background-color:#F7F7F7;border:1px solid #EEEEEE;\">\n		<h3 style=\"font-size:14px;font-family:&quot;\">\n			售后保障\n		</h3>\n	</div>\n	<div class=\"mc\" style=\"margin:0px;padding:0px;\">\n		<div class=\"item-detail item-detail-copyright\" style=\"margin:0px;padding:10px;\">\n			<div class=\"serve-agree-bd\" style=\"margin:0px;padding:20px 20px 20px 62px;\">\n				<span class=\"goods\" style=\"line-height:32px;vertical-align:bottom;\"></span>&nbsp;<strong>京东承诺</strong>京东平台卖家销售并发货的商品，由平台卖家提供发票和相应的售后服务。请您放心购买！<br />\n注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！<span class=\"goods\" style=\"line-height:32px;vertical-align:bottom;\"></span><strong>正品行货</strong>京东商城向您保证所售商品均为正品行货，京东自营商品开具机打发票或电子发票。\n			</div>\n		</div>\n	</div>\n</div>',
        4999.00, 30, 1, 0, '2025-03-14 14:28:01', '2025-03-14 14:28:38');

-- ----------------------------
-- Table structure for shipping
-- ----------------------------
DROP TABLE IF EXISTS `shipping`;
CREATE TABLE `shipping`
(
    `id`                int                                                           NOT NULL AUTO_INCREMENT,
    `user_id`           int                                                           NULL     DEFAULT NULL COMMENT '用户id',
    `receiver_name`     varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '收货姓名',
    `receiver_phone`    varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '收货固定电话',
    `receiver_mobile`   varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '收货移动电话',
    `receiver_province` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '省份',
    `receiver_city`     varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '城市',
    `receiver_district` varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL COMMENT '区/县',
    `receiver_address`  varchar(200) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '详细地址',
    `receiver_zip`      varchar(6) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci   NULL     DEFAULT NULL COMMENT '邮编',
    `status`            tinyint                                                       NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`        tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time`       datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`       datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of shipping
-- ----------------------------
INSERT INTO `shipping`
VALUES (4, 1, 'gao', '010', '15964789965', '北京', '北京', '海淀', '中关村', '100000', 1, 1, '2017-01-22 14:26:25',
        '2025-03-17 08:59:45');
INSERT INTO `shipping`
VALUES (7, 1, 'Rosen', '13800138000', '13800138000', '北京', '北京', '朝阳', '中关村', '100000', 1, 1,
        '2017-03-29 12:11:01', '2025-03-17 09:00:05');
INSERT INTO `shipping`
VALUES (9, 1, 'tom', '18796548547', '18796548547@13.com', '山东', '青岛', '崂山', '海尔路', '100302', 1, 0,
        '2025-03-17 08:58:45', '2025-03-17 08:59:24');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`
(
    `id`          int                                                           NOT NULL AUTO_INCREMENT COMMENT '用户表id',
    `name`        varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL COMMENT '用户名',
    `password`    varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NOT NULL COMMENT '用户密码，MD5加密',
    `email`       varchar(50) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL,
    `phone`       varchar(20) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci  NULL     DEFAULT NULL,
    `question`    varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '找回密码问题',
    `answer`      varchar(100) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL     DEFAULT NULL COMMENT '找回密码答案',
    `status`      tinyint                                                       NULL     DEFAULT 1 COMMENT '状态（1：正常 0：停用）',
    `is_deleted`  tinyint                                                       NOT NULL DEFAULT 0 COMMENT '逻辑删除 1 表示删除，0 表示未删除',
    `create_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` datetime                                                      NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `user_name_unique` (`name` ASC) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 3
  CHARACTER SET = utf8mb3
  COLLATE = utf8mb3_general_ci
  ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user`
VALUES (1, 'zhangsan', '1234', NULL, NULL, NULL, NULL, 1, 0, '2023-05-04 14:33:46', '2025-03-14 23:29:01');
INSERT INTO `user`
VALUES (2, 'lisi', '123', NULL, NULL, NULL, NULL, 1, 0, '2025-03-14 23:28:49', '2025-03-14 23:29:03');

SET
    FOREIGN_KEY_CHECKS = 1;
