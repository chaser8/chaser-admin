/*
 Navicat Premium Data Transfer

 Source Server         : 
 Source Server Type    : MySQL
 Source Server Version : 80020
 Source Host           : 3307
 Source Schema         : chaser_admin

 Target Server Type    : MySQL
 Target Server Version : 80020
 File Encoding         : 65001

 Date: 22/11/2021 11:14:17
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for ums_func
-- ----------------------------
DROP TABLE IF EXISTS `ums_func`;
CREATE TABLE `ums_func` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '功能点编码',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_unique_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='后台用户权限表';

-- ----------------------------
-- Records of ums_func
-- ----------------------------
BEGIN;
INSERT INTO `ums_func` VALUES (1, '角色列表', 'system:role:list', '2021-10-29 10:30:25', 3, 3, '2021-10-29 10:30:25');
INSERT INTO `ums_func` VALUES (2, '新增角色', 'system:role:add', '2021-10-29 11:15:41', 3, 3, '2021-10-29 11:15:41');
INSERT INTO `ums_func` VALUES (3, '编辑角色', 'system:role:update', '2021-10-29 11:24:40', 3, 3, '2021-10-29 11:24:40');
INSERT INTO `ums_func` VALUES (4, '删除角色', 'system:role:delete', '2021-10-29 11:25:35', 3, 3, '2021-10-29 11:25:35');
INSERT INTO `ums_func` VALUES (5, '配置权限', 'system:role:permission', '2021-10-29 11:26:49', 3, 3, '2021-11-17 15:47:53');
INSERT INTO `ums_func` VALUES (6, '新增用户', 'system:user:add', '2021-10-29 11:35:46', 3, 3, '2021-10-29 11:35:46');
INSERT INTO `ums_func` VALUES (7, '冻结用户', 'system:user:freeze', '2021-10-29 11:37:36', 3, 3, '2021-10-29 11:37:36');
INSERT INTO `ums_func` VALUES (8, '解冻用户', 'system:user:unfreeze', '2021-10-29 11:37:59', 3, 3, '2021-10-29 11:37:59');
INSERT INTO `ums_func` VALUES (9, '列表查询', 'system:user:list', '2021-10-29 11:38:20', 3, 3, '2021-10-29 11:38:20');
INSERT INTO `ums_func` VALUES (10, '编辑用户', 'system:user:update', '2021-10-29 11:38:50', 3, 3, '2021-10-29 11:38:50');
INSERT INTO `ums_func` VALUES (11, '修改用户角色', 'system:user:role', '2021-10-29 11:39:40', 3, 3, '2021-10-29 11:39:40');
INSERT INTO `ums_func` VALUES (12, '新增服务', 'system:resource:add', '2021-10-29 14:25:39', 3, 3, '2021-10-29 14:25:39');
INSERT INTO `ums_func` VALUES (13, '删除服务', 'system:resource:delete', '2021-10-29 14:25:57', 3, 3, '2021-10-29 14:25:57');
INSERT INTO `ums_func` VALUES (14, '服务列表查询', 'system:resource:list', '2021-10-29 14:26:14', 3, 3, '2021-10-29 14:26:14');
INSERT INTO `ums_func` VALUES (15, '编辑服务', 'system:resource:update', '2021-10-29 14:26:31', 3, 3, '2021-10-29 14:26:31');
INSERT INTO `ums_func` VALUES (16, '菜单树查询', 'system:menu:tree', '2021-10-29 14:27:59', 3, 3, '2021-10-29 14:27:59');
INSERT INTO `ums_func` VALUES (17, '新增菜单', 'system:menu:add', '2021-10-29 14:28:52', 3, 3, '2021-10-29 14:28:52');
INSERT INTO `ums_func` VALUES (18, '删除菜单', 'system:menu:delete', '2021-10-29 14:29:08', 3, 3, '2021-10-29 14:29:08');
INSERT INTO `ums_func` VALUES (19, '编辑菜单', 'system:menu:update', '2021-10-29 14:29:33', 3, 3, '2021-10-29 14:29:33');
INSERT INTO `ums_func` VALUES (20, '新增功能项', 'system:func:add', '2021-10-29 14:30:32', 3, 3, '2021-10-29 14:30:32');
INSERT INTO `ums_func` VALUES (21, '删除功能项', 'system:func:delete', '2021-10-29 14:31:55', 3, 3, '2021-10-29 14:31:55');
INSERT INTO `ums_func` VALUES (22, '编辑功能项', 'system:func:update', '2021-10-29 14:32:11', 3, 3, '2021-10-29 14:32:11');
INSERT INTO `ums_func` VALUES (23, '功能项关联服务', 'system:func:resource', '2021-10-29 14:32:37', 3, 3, '2021-10-29 14:32:37');
COMMIT;

-- ----------------------------
-- Table structure for ums_func_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_func_resource_relation`;
CREATE TABLE `ums_func_resource_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `resource_id` bigint DEFAULT NULL COMMENT '资源标识',
  `func_id` bigint DEFAULT NULL COMMENT '功能点标识',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='后台功能点和资源关系表';

-- ----------------------------
-- Records of ums_func_resource_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_func_resource_relation` VALUES (2, 13, 2, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (3, 13, 3, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (4, 14, 4, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (6, 28, 5, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (7, 10, 5, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (8, 11, 5, NULL, 3);
INSERT INTO `ums_func_resource_relation` VALUES (10, 7, 7, '2021-10-29 11:37:46', 3);
INSERT INTO `ums_func_resource_relation` VALUES (11, 8, 8, '2021-10-29 11:38:05', 3);
INSERT INTO `ums_func_resource_relation` VALUES (13, 1, 6, '2021-10-29 11:38:33', 3);
INSERT INTO `ums_func_resource_relation` VALUES (14, 3, 6, '2021-10-29 11:38:33', 3);
INSERT INTO `ums_func_resource_relation` VALUES (15, 4, 10, '2021-10-29 11:39:06', 3);
INSERT INTO `ums_func_resource_relation` VALUES (16, 5, 11, '2021-10-29 11:39:52', 3);
INSERT INTO `ums_func_resource_relation` VALUES (17, 6, 11, '2021-10-29 11:39:52', 3);
INSERT INTO `ums_func_resource_relation` VALUES (19, 26, 15, '2021-10-29 14:26:53', 3);
INSERT INTO `ums_func_resource_relation` VALUES (20, 24, 14, '2021-10-29 14:27:03', 3);
INSERT INTO `ums_func_resource_relation` VALUES (21, 27, 13, '2021-10-29 14:27:07', 3);
INSERT INTO `ums_func_resource_relation` VALUES (22, 26, 12, '2021-10-29 14:27:13', 3);
INSERT INTO `ums_func_resource_relation` VALUES (23, 15, 16, '2021-10-29 14:28:27', 3);
INSERT INTO `ums_func_resource_relation` VALUES (24, 16, 16, '2021-10-29 14:28:27', 3);
INSERT INTO `ums_func_resource_relation` VALUES (25, 17, 16, '2021-10-29 14:28:27', 3);
INSERT INTO `ums_func_resource_relation` VALUES (27, 20, 22, '2021-10-29 14:32:58', 3);
INSERT INTO `ums_func_resource_relation` VALUES (28, 21, 21, '2021-10-29 14:33:10', 3);
INSERT INTO `ums_func_resource_relation` VALUES (29, 23, 23, '2021-10-29 14:33:37', 3);
INSERT INTO `ums_func_resource_relation` VALUES (30, 22, 23, '2021-10-29 14:33:37', 3);
INSERT INTO `ums_func_resource_relation` VALUES (31, 24, 23, '2021-10-29 14:33:37', 3);
INSERT INTO `ums_func_resource_relation` VALUES (33, 20, 20, '2021-10-29 14:34:10', 3);
INSERT INTO `ums_func_resource_relation` VALUES (38, 12, 1, '2021-11-17 14:46:17', 3);
INSERT INTO `ums_func_resource_relation` VALUES (39, 28, 1, '2021-11-17 14:46:17', 3);
INSERT INTO `ums_func_resource_relation` VALUES (40, 2, 9, '2021-11-17 14:58:56', 3);
INSERT INTO `ums_func_resource_relation` VALUES (41, 9, 9, '2021-11-17 14:58:56', 3);
COMMIT;

-- ----------------------------
-- Table structure for ums_menu
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu`;
CREATE TABLE `ums_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint NOT NULL COMMENT '父级ID',
  `name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '菜单名称',
  `level` int DEFAULT NULL COMMENT '菜单级数',
  `sort` int DEFAULT NULL COMMENT '菜单排序',
  `code` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '编码',
  `icon` varchar(200) DEFAULT NULL COMMENT '前端图标',
  `hidden` int DEFAULT NULL COMMENT '前端隐藏',
  `url` varchar(255) DEFAULT NULL COMMENT '菜单地址',
  `path` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_unique_code` (`code`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='后台菜单表';

-- ----------------------------
-- Records of ums_menu
-- ----------------------------
BEGIN;
INSERT INTO `ums_menu` VALUES (1, 0, '首页', 1, 0, 'dashboard', 'product', 0, NULL, NULL, '2020-02-02 14:50:36', NULL, NULL, '2021-10-27 17:22:29');
INSERT INTO `ums_menu` VALUES (2, 0, '系统管理', 1, 1, 'system', 'product', 0, NULL, NULL, '2020-02-02 14:50:36', NULL, NULL, '2021-10-27 17:22:29');
INSERT INTO `ums_menu` VALUES (3, 2, '角色管理', 2, 2, 'system:role', 'product', 0, '/system/role', NULL, '2020-02-02 14:50:36', NULL, 3, '2021-10-28 18:05:01');
INSERT INTO `ums_menu` VALUES (4, 2, '用户管理', 2, 3, 'system:user', 'product', 0, '/system/user', NULL, '2020-02-02 14:50:36', NULL, 3, '2021-10-28 18:04:47');
INSERT INTO `ums_menu` VALUES (13, 2, '菜单权限管理', 2, 1, 'system:menu', '1', NULL, '/system/menu', NULL, '2021-10-28 18:17:56', 3, 3, '2021-10-29 14:22:46');
INSERT INTO `ums_menu` VALUES (14, 2, '服务管理', 2, 0, 'system:resource', '1', NULL, '/system/resource', NULL, '2021-10-29 14:23:39', 3, 3, '2021-10-29 14:23:39');
COMMIT;

-- ----------------------------
-- Table structure for ums_menu_func_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_menu_func_relation`;
CREATE TABLE `ums_menu_func_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  `func_id` bigint DEFAULT NULL COMMENT '功能点标识',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of ums_menu_func_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_menu_func_relation` VALUES (6, 3, 1, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (7, 3, 2, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (8, 3, 3, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (9, 3, 4, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (10, 3, 5, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (11, 4, 6, NULL, NULL);
INSERT INTO `ums_menu_func_relation` VALUES (12, 4, 7, '2021-10-29 11:37:36', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (13, 4, 8, '2021-10-29 11:37:59', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (14, 4, 9, '2021-10-29 11:38:20', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (15, 4, 10, '2021-10-29 11:38:50', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (16, 4, 11, '2021-10-29 11:39:40', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (17, 14, 12, '2021-10-29 14:25:39', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (18, 14, 13, '2021-10-29 14:25:57', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (19, 14, 14, '2021-10-29 14:26:14', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (20, 14, 15, '2021-10-29 14:26:31', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (21, 13, 16, '2021-10-29 14:27:59', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (22, 13, 17, '2021-10-29 14:28:52', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (23, 13, 18, '2021-10-29 14:29:08', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (24, 13, 19, '2021-10-29 14:29:33', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (25, 13, 20, '2021-10-29 14:30:32', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (26, 13, 21, '2021-10-29 14:31:55', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (27, 13, 22, '2021-10-29 14:32:11', NULL);
INSERT INTO `ums_menu_func_relation` VALUES (28, 13, 23, '2021-10-29 14:32:37', NULL);
COMMIT;

-- ----------------------------
-- Table structure for ums_resource
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource`;
CREATE TABLE `ums_resource` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(200) DEFAULT NULL COMMENT '资源名称',
  `url` varchar(200) DEFAULT NULL COMMENT '资源URL',
  `method` varchar(10) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `category_id` bigint DEFAULT NULL COMMENT '资源分类ID',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8 COMMENT='后台资源表';

-- ----------------------------
-- Records of ums_resource
-- ----------------------------
BEGIN;
INSERT INTO `ums_resource` VALUES (1, '系统管理-用户管理-新增用户', '/user/add', 'POST', '', NULL, '2021-10-28 18:21:00', 3, 3, '2021-10-28 18:21:00');
INSERT INTO `ums_resource` VALUES (2, '系统管理-用户管理-列表查询', '/user/page', 'POST', '', NULL, '2021-10-28 18:21:28', 3, 3, '2021-10-28 18:21:28');
INSERT INTO `ums_resource` VALUES (3, '系统管理-用户管理-检查用户名', '/user/usernameCheck', 'POST', '', NULL, '2021-10-28 18:22:05', 3, 3, '2021-10-28 18:22:05');
INSERT INTO `ums_resource` VALUES (4, '系统管理-用户管理-更新用户', '/user', 'PATCH', '', NULL, '2021-10-28 18:23:22', 3, 3, '2021-10-28 18:23:22');
INSERT INTO `ums_resource` VALUES (5, '系统管理-用户管理-获取角色', '/user/getUserRoles', 'POST', '', NULL, '2021-10-28 18:23:58', 3, 3, '2021-10-28 18:30:35');
INSERT INTO `ums_resource` VALUES (6, '系统管理-用户管理-更新角色', '/user/updateUserRoles', 'POST', '', NULL, '2021-10-28 18:24:29', 3, 3, '2021-10-28 18:30:27');
INSERT INTO `ums_resource` VALUES (7, '系统管理-用户管理-冻结', '/user/freeze', 'POST', '', NULL, '2021-10-28 18:25:06', 3, 3, '2021-10-28 18:30:01');
INSERT INTO `ums_resource` VALUES (8, '系统管理-用户管理-解冻', '/user/unfreeze', 'POST', '', NULL, '2021-10-28 18:25:34', 3, 3, '2021-10-28 18:30:06');
INSERT INTO `ums_resource` VALUES (9, '系统管理-角色管理-查询有效', '/role/getRoles', 'POST', '', NULL, '2021-10-28 18:26:53', 3, 3, '2021-10-28 18:30:13');
INSERT INTO `ums_resource` VALUES (10, '系统管理-角色管理-查询关联权限', '/role/getRoleMenus', 'POST', '', NULL, '2021-10-28 18:27:41', 3, 3, '2021-10-28 18:30:46');
INSERT INTO `ums_resource` VALUES (11, '系统管理-角色管理-更新权限', '/role/updatePermission', 'POST', '', NULL, '2021-10-28 18:28:09', 3, 3, '2021-10-28 18:28:09');
INSERT INTO `ums_resource` VALUES (12, '系统管理-角色管理-分页查询', '/role/getRolePage', 'POST', '', NULL, '2021-10-28 18:28:43', 3, 3, '2021-10-28 18:28:43');
INSERT INTO `ums_resource` VALUES (13, '系统管理-角色管理-新增/更新', '/role/merge', 'POST', '', NULL, '2021-10-28 18:29:21', 3, 3, '2021-10-28 18:29:21');
INSERT INTO `ums_resource` VALUES (14, '系统管理-角色管理-删除', '/role', 'DELETE', '', NULL, '2021-10-28 18:29:51', 3, 3, '2021-10-28 18:29:51');
INSERT INTO `ums_resource` VALUES (15, '系统管理-菜单管理-查询按层级', '/menu/getLevelMenus', 'POST', '', NULL, '2021-10-29 10:22:14', 3, 3, '2021-10-29 10:22:14');
INSERT INTO `ums_resource` VALUES (16, '系统管理-菜单管理-查询下级', '/menu/getChildren/*', 'GET', '', NULL, '2021-10-29 10:22:45', 3, 3, '2021-10-29 10:22:45');
INSERT INTO `ums_resource` VALUES (17, '系统管理-菜单-查询功能项', '/menu/getMenuFunc/*', 'GET', '', NULL, '2021-10-29 10:24:00', 3, 3, '2021-10-29 10:24:00');
INSERT INTO `ums_resource` VALUES (18, '系统管理-菜单-新增/更新', '/menu', 'PUT', '', NULL, '2021-10-29 10:24:27', 3, 3, '2021-10-29 10:24:27');
INSERT INTO `ums_resource` VALUES (19, '系统管理-菜单-删除', '/menu/*', 'DELETE', '', NULL, '2021-10-29 10:24:50', 3, 3, '2021-10-29 10:25:01');
INSERT INTO `ums_resource` VALUES (20, '系统管理-功能项-新增/修改', '/func', 'PUT', '', NULL, '2021-10-29 10:26:09', 3, 3, '2021-10-29 10:26:09');
INSERT INTO `ums_resource` VALUES (21, '系统管理-功能项-删除', '/func/*', 'DELETE', '', NULL, '2021-10-29 10:26:32', 3, 3, '2021-10-29 10:26:32');
INSERT INTO `ums_resource` VALUES (22, '系统管理-功能项-服务', '/func/*/resources', 'GET', '', NULL, '2021-10-29 10:27:05', 3, 3, '2021-10-29 10:27:05');
INSERT INTO `ums_resource` VALUES (23, '系统管理-功能项-更新关联服务', '/func/resource', 'PUT', '', NULL, '2021-10-29 10:27:33', 3, 3, '2021-10-29 10:27:33');
INSERT INTO `ums_resource` VALUES (24, '系统管理-服务-查询所有', '/resource', 'GET', '', NULL, '2021-10-29 10:28:06', 3, 3, '2021-10-29 10:28:06');
INSERT INTO `ums_resource` VALUES (25, '系统管理-服务-分页查询', '/resource', 'POST', '', NULL, '2021-10-29 10:28:28', 3, 3, '2021-10-29 10:28:28');
INSERT INTO `ums_resource` VALUES (26, '系统管理-服务-新增更新', '/resource', 'PUT', '', NULL, '2021-10-29 10:28:50', 3, 3, '2021-10-29 10:28:50');
INSERT INTO `ums_resource` VALUES (27, '系统管理-服务-删除', '/resource/*', 'DELETE', '', NULL, '2021-10-29 10:29:06', 3, 3, '2021-10-29 10:29:06');
INSERT INTO `ums_resource` VALUES (28, '系统管理-角色管理-查询按层级功能项', '/menu/getLevelMenuFuncs', 'POST', '', NULL, '2021-10-29 11:29:20', 3, 3, '2021-11-17 14:44:59');
COMMIT;

-- ----------------------------
-- Table structure for ums_resource_category
-- ----------------------------
DROP TABLE IF EXISTS `ums_resource_category`;
CREATE TABLE `ums_resource_category` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `name` varchar(200) DEFAULT NULL COMMENT '分类名称',
  `sort` int DEFAULT NULL COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='资源分类表';

-- ----------------------------
-- Records of ums_resource_category
-- ----------------------------
BEGIN;
INSERT INTO `ums_resource_category` VALUES (1, '2020-02-05 10:21:44', '商品模块', 0);
INSERT INTO `ums_resource_category` VALUES (2, '2020-02-05 10:22:34', '订单模块', 0);
INSERT INTO `ums_resource_category` VALUES (3, '2020-02-05 10:22:48', '营销模块', 0);
INSERT INTO `ums_resource_category` VALUES (4, '2020-02-05 10:23:04', '权限模块', 0);
INSERT INTO `ums_resource_category` VALUES (5, '2020-02-07 16:34:27', '内容模块', 0);
INSERT INTO `ums_resource_category` VALUES (6, '2020-02-07 16:35:49', '其他模块', 0);
COMMIT;

-- ----------------------------
-- Table structure for ums_role
-- ----------------------------
DROP TABLE IF EXISTS `ums_role`;
CREATE TABLE `ums_role` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL COMMENT '名称',
  `code` varchar(255) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `admin_count` int DEFAULT NULL COMMENT '后台用户数量',
  `status` char(10) DEFAULT '1' COMMENT '启用状态：0->禁用；1->启用',
  `sort` int DEFAULT '0',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COMMENT='后台用户角色表';

-- ----------------------------
-- Records of ums_role
-- ----------------------------
BEGIN;
INSERT INTO `ums_role` VALUES (2, '普通管理员', NULL, '普通管理员', 0, '1000', 0, '2021-10-25 14:50:35', NULL, 3, '2021-11-17 09:28:58');
INSERT INTO `ums_role` VALUES (5, '超级管理员', NULL, '拥有所有查看和操作功能', 0, '1000', 0, '2021-10-25 14:50:35', 3, 3, '2021-10-28 15:49:16');
COMMIT;

-- ----------------------------
-- Table structure for ums_role_func_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_func_relation`;
CREATE TABLE `ums_role_func_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL,
  `func_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116 DEFAULT CHARSET=utf8 COMMENT='后台用户角色和权限关系表';

-- ----------------------------
-- Records of ums_role_func_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_role_func_relation` VALUES (76, 5, 15, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (77, 5, 14, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (78, 5, 13, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (79, 5, 12, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (80, 5, 23, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (81, 5, 22, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (82, 5, 21, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (83, 5, 20, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (84, 5, 19, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (85, 5, 18, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (86, 5, 17, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (87, 5, 16, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (88, 5, 5, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (89, 5, 4, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (90, 5, 3, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (91, 5, 2, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (92, 5, 1, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (93, 5, 11, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (94, 5, 10, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (95, 5, 9, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (96, 5, 8, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (97, 5, 7, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (98, 5, 6, '2021-11-17 14:19:26', 3);
INSERT INTO `ums_role_func_relation` VALUES (108, 2, 14, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (109, 2, 13, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (110, 2, 12, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (111, 2, 5, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (112, 2, 4, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (113, 2, 3, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (114, 2, 2, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_func_relation` VALUES (115, 2, 1, '2021-11-17 15:54:10', 3);
COMMIT;

-- ----------------------------
-- Table structure for ums_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_menu_relation`;
CREATE TABLE `ums_role_menu_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint DEFAULT NULL COMMENT '菜单ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=237 DEFAULT CHARSET=utf8 COMMENT='后台角色菜单关系表';

-- ----------------------------
-- Records of ums_role_menu_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_role_menu_relation` VALUES (109, 12, 1, '2021-10-26 14:35:35', 3);
INSERT INTO `ums_role_menu_relation` VALUES (110, 12, 3, '2021-10-26 14:35:35', 3);
INSERT INTO `ums_role_menu_relation` VALUES (183, 1, 1, '2021-10-28 17:56:54', 3);
INSERT INTO `ums_role_menu_relation` VALUES (218, 5, 1, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (219, 5, 2, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (220, 5, 14, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (221, 5, 13, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (222, 5, 3, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (223, 5, 4, '2021-11-17 14:19:25', 3);
INSERT INTO `ums_role_menu_relation` VALUES (233, 2, 1, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_menu_relation` VALUES (234, 2, 3, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_menu_relation` VALUES (235, 2, 2, '2021-11-17 15:54:10', 3);
INSERT INTO `ums_role_menu_relation` VALUES (236, 2, 14, '2021-11-17 15:54:10', 3);
COMMIT;

-- ----------------------------
-- Table structure for ums_role_resource_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_role_resource_relation`;
CREATE TABLE `ums_role_resource_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint DEFAULT NULL COMMENT '角色ID',
  `resource_id` bigint DEFAULT NULL COMMENT '资源ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='后台角色资源关系表';

-- ----------------------------
-- Records of ums_role_resource_relation
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for ums_user
-- ----------------------------
DROP TABLE IF EXISTS `ums_user`;
CREATE TABLE `ums_user` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_phone` varchar(11) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `icon` varchar(500) DEFAULT NULL COMMENT '头像',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `nick_name` varchar(200) DEFAULT NULL COMMENT '昵称',
  `note` varchar(500) DEFAULT NULL COMMENT '备注信息',
  `pwd_error_cnt` int DEFAULT '0',
  `login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `status` char(10) DEFAULT '1000' COMMENT '帐号启用状态：0->禁用；1->启用',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  `update_user` bigint DEFAULT NULL,
  `update_date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='后台用户表';

-- ----------------------------
-- Records of ums_user
-- ----------------------------
BEGIN;
INSERT INTO `ums_user` VALUES (3, '13000000001', 'admin', '111111', 'http://macro-oss.oss-cn-shenzhen.aliyuncs.com/mall/images/20180607/timg.jpg', 'admin@163.com', '系统管理员', '系统管理员', 0, '2021-11-17 16:40:00', '1000', '2018-10-08 13:32:47', NULL, NULL, '2021-11-17 16:39:59');
INSERT INTO `ums_user` VALUES (16, '13000000002', '13000000002', '13000000002', NULL, '13000000002', '13000000002', NULL, 0, '2021-11-17 15:54:16', '1000', '2021-11-17 09:28:25', NULL, NULL, '2021-11-17 15:54:16');
COMMIT;

-- ----------------------------
-- Table structure for ums_user_role_relation
-- ----------------------------
DROP TABLE IF EXISTS `ums_user_role_relation`;
CREATE TABLE `ums_user_role_relation` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint DEFAULT NULL,
  `role_id` bigint DEFAULT NULL,
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8 COMMENT='后台用户和角色关系表';

-- ----------------------------
-- Records of ums_user_role_relation
-- ----------------------------
BEGIN;
INSERT INTO `ums_user_role_relation` VALUES (66, 3, 5, NULL, 3);
INSERT INTO `ums_user_role_relation` VALUES (67, 16, 2, NULL, 3);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
