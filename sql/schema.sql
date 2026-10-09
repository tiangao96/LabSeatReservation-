-- ============================================================================
-- lab-reservation-system schema (DDL only)
-- 由线上 MariaDB 10.5 实例导出 (mysqldump --no-data) 并清理, 与生产库一致。
-- 兼容: MySQL 8.x / MariaDB 10.5  (字符集 utf8mb4, 排序规则 utf8mb4_unicode_ci / utf8mb4_bin,
--       未使用 MySQL 8 专有的 utf8mb4_0900_* 规则)
-- 用法: mysql -u<user> -p <db> < sql/schema.sql   然后导入 sql/data.sql 初始数据
-- ============================================================================

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
DROP TABLE IF EXISTS `audit_log`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `audit_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `user_id` bigint(20) DEFAULT NULL COMMENT '操作人ID(登录失败等无principal时为NULL)',
  `username` varchar(64) DEFAULT NULL COMMENT '操作人账号(冗余快照;登录失败记输入名)',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '操作人部门ID(冗余快照)',
  `module` varchar(32) NOT NULL COMMENT '模块: user/role/menu/dept/permission/lab/workstation/equipment/auth',
  `action` varchar(32) NOT NULL COMMENT '动作: create/update/delete/assign/occupy/release/login/logout/refresh/import',
  `description` varchar(128) DEFAULT NULL COMMENT '操作描述',
  `target_id` varchar(64) DEFAULT NULL COMMENT '目标资源nanoId(路径参数)',
  `http_method` varchar(10) DEFAULT NULL COMMENT 'HTTP方法: GET/POST/PUT/DELETE',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `ip` varchar(64) DEFAULT NULL COMMENT '客户端IP(解析代理链)',
  `user_agent` varchar(255) DEFAULT NULL COMMENT 'User-Agent',
  `params` text DEFAULT NULL COMMENT '请求参数JSON(敏感字段password/token等已脱敏,截断2000字符)',
  `result` varchar(16) NOT NULL COMMENT '结果: SUCCESS/FAIL',
  `error_msg` text DEFAULT NULL COMMENT '失败异常信息(result=FAIL时)',
  `duration_ms` int(11) DEFAULT NULL COMMENT '耗时毫秒',
  `create_time` datetime NOT NULL COMMENT '记录时间(应用层设值)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_audit_log_nano_id` (`nano_id`),
  KEY `idx_audit_log_user_id` (`user_id`),
  KEY `idx_audit_log_module` (`module`),
  KEY `idx_audit_log_create_time` (`create_time`),
  KEY `idx_audit_log_result` (`result`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='操作审计日志(append-only)';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_dept`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_dept` (
  `name` varchar(64) NOT NULL COMMENT '部门名称',
  `code` varchar(32) DEFAULT NULL COMMENT '部门编码',
  `dept_type` varchar(20) DEFAULT NULL COMMENT '部门类型',
  `phone` varchar(20) DEFAULT NULL COMMENT '部门电话',
  `email` varchar(64) DEFAULT NULL COMMENT '部门邮箱',
  `status` tinyint(1) DEFAULT NULL COMMENT '部门状态',
  `description` text DEFAULT NULL COMMENT '部门描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父部门ID',
  `lead_id` bigint(20) DEFAULT NULL COMMENT '部门领导ID',
  `level` int(11) DEFAULT NULL COMMENT '部门层级',
  `path` varchar(500) DEFAULT NULL COMMENT '部门路径',
  `dingtalk_dept_id` varchar(64) DEFAULT NULL COMMENT '钉钉部门ID',
  `wecom_dept_id` varchar(64) DEFAULT NULL COMMENT '企业微信部门ID',
  `feishu_dept_id` varchar(128) DEFAULT NULL COMMENT '飞书部门ID',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_core_dept_nano_id` (`nano_id`),
  UNIQUE KEY `uk_core_dept_code` (`code`,`is_deleted`),
  KEY `idx_core_dept_parent_id` (`parent_id`),
  KEY `idx_core_dept_path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_mentor_group`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_mentor_group` (
  `name` varchar(64) NOT NULL COMMENT '导师组名称',
  `code` varchar(32) DEFAULT NULL COMMENT '导师组编码',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(64) DEFAULT NULL COMMENT '邮箱',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `description` text DEFAULT NULL COMMENT '描述',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父节点ID',
  `lead_id` bigint(20) DEFAULT NULL COMMENT '负责人ID',
  `level` int(11) DEFAULT NULL COMMENT '层级(1学院/2系所/3导师组,最多3层)',
  `path` varchar(500) DEFAULT NULL COMMENT '路径',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_core_mentor_group_nano_id` (`nano_id`),
  UNIQUE KEY `uk_core_mentor_group_code` (`code`,`is_deleted`),
  KEY `idx_core_mentor_group_parent_id` (`parent_id`),
  KEY `idx_core_mentor_group_path` (`path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_menu` (
  `application_id` bigint(20) DEFAULT NULL COMMENT '所属应用ID',
  `is_system` tinyint(1) DEFAULT NULL COMMENT '是否为系统菜单',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID',
  `name` varchar(100) NOT NULL COMMENT '菜单名称（路由名称）',
  `title` varchar(100) DEFAULT NULL COMMENT '菜单标题（显示名称）',
  `auth_code` varchar(100) DEFAULT NULL COMMENT '后端权限标识',
  `path` varchar(200) NOT NULL COMMENT '路由路径',
  `type` varchar(20) DEFAULT NULL COMMENT '菜单类型: catalog/menu/external/online_form/online_page/agent',
  `component` varchar(100) DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(200) DEFAULT NULL COMMENT '重定向路径',
  `active_path` varchar(200) DEFAULT NULL COMMENT '激活路径',
  `query` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '额外路由参数' CHECK (json_valid(`query`)),
  `no_basic_layout` tinyint(1) DEFAULT NULL COMMENT '无需基础布局',
  `icon` varchar(100) DEFAULT NULL COMMENT '菜单图标',
  `active_icon` varchar(100) DEFAULT NULL COMMENT '激活图标',
  `order` int(11) DEFAULT NULL COMMENT '菜单排序',
  `hide_in_menu` tinyint(1) DEFAULT NULL COMMENT '在菜单中隐藏',
  `hide_children_in_menu` tinyint(1) DEFAULT NULL COMMENT '在菜单中隐藏下级',
  `hide_in_breadcrumb` tinyint(1) DEFAULT NULL COMMENT '在面包屑中隐藏',
  `hide_in_tab` tinyint(1) DEFAULT NULL COMMENT '在标签栏中隐藏',
  `affix_tab` tinyint(1) DEFAULT NULL COMMENT '固定在标签栏',
  `affix_tab_order` int(11) DEFAULT NULL COMMENT '标签栏固定顺序',
  `keep_alive` tinyint(1) DEFAULT NULL COMMENT '缓存页面',
  `max_num_of_open_tab` int(11) DEFAULT NULL COMMENT '最大打开标签数',
  `full_path_key` tinyint(1) DEFAULT NULL COMMENT '路由完整路径作为key（设为false时路径参数变化不刷新组件）',
  `link` varchar(500) DEFAULT NULL COMMENT '外链URL',
  `iframe_src` varchar(500) DEFAULT NULL COMMENT '内嵌iframe URL',
  `open_in_new_window` tinyint(1) DEFAULT NULL COMMENT '在新窗口打开',
  `badge` varchar(20) DEFAULT NULL COMMENT '徽标内容',
  `badge_type` varchar(20) DEFAULT NULL COMMENT '徽标类型: dot/normal',
  `badge_variants` varchar(20) DEFAULT NULL COMMENT '徽标颜色',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_core_menu_nano_id` (`nano_id`),
  KEY `idx_core_menu_parent_id` (`parent_id`),
  KEY `idx_core_menu_application_id` (`application_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_permission` (
  `menu_id` bigint(20) NOT NULL COMMENT '关联菜单ID（逻辑外键关联core_menu）',
  `name` varchar(64) NOT NULL COMMENT '权限名称',
  `code` varchar(64) NOT NULL COMMENT '权限编码',
  `permission_type` int(11) DEFAULT NULL COMMENT '权限类型（1-API权限',
  `api_path` varchar(200) DEFAULT NULL COMMENT 'API路径',
  `http_method` int(11) DEFAULT NULL COMMENT 'HTTP方法（0-GET, 1-POST, 2-PUT, 3-DELETE, 4-PATCH, 5-ALL）',
  `description` text DEFAULT NULL COMMENT '权限描述',
  `is_active` tinyint(1) DEFAULT NULL COMMENT '是否启用',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_permission_menu_code` (`menu_id`,`code`),
  UNIQUE KEY `uk_core_permission_nano_id` (`nano_id`),
  KEY `idx_core_permission_code` (`code`),
  KEY `idx_core_permission_name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role` (
  `name` varchar(64) NOT NULL COMMENT '角色名称',
  `code` varchar(64) NOT NULL COMMENT '角色编码',
  `role_type` int(11) DEFAULT NULL COMMENT '角色类型（0-系统角色, 1-自定义角色）',
  `status` tinyint(1) DEFAULT NULL COMMENT '角色状态（启用/禁用）',
  `priority` int(11) DEFAULT NULL COMMENT '角色优先级（数字越大优先级越高）',
  `description` text DEFAULT NULL COMMENT '角色描述',
  `remark` text DEFAULT NULL COMMENT '备注信息',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_core_role_nano_id` (`nano_id`),
  UNIQUE KEY `uk_core_role_code` (`code`,`is_deleted`),
  KEY `idx_core_role_name` (`name`),
  KEY `idx_core_role_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_role_menu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role_menu` (
  `role_id` bigint(20) NOT NULL,
  `menu_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_role_permission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_role_permission` (
  `role_id` bigint(20) NOT NULL,
  `permission_id` bigint(20) NOT NULL,
  PRIMARY KEY (`role_id`,`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_user` (
  `username` varchar(150) NOT NULL COMMENT '用户名',
  `password` varchar(128) DEFAULT NULL COMMENT '密码',
  `is_superuser` tinyint(1) DEFAULT NULL COMMENT '是否超级管理员',
  `last_login` datetime DEFAULT NULL COMMENT '最后登录时间',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(11) DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(36) DEFAULT NULL COMMENT '头像UUID',
  `name` varchar(64) DEFAULT NULL COMMENT '真实姓名',
  `gender` int(11) DEFAULT NULL COMMENT '性别',
  `user_type` int(11) DEFAULT NULL COMMENT '用户类型',
  `user_status` int(11) DEFAULT NULL COMMENT '用户状态',
  `birthday` date DEFAULT NULL COMMENT '生日',
  `city` varchar(100) DEFAULT NULL COMMENT '所在城市',
  `address` varchar(200) DEFAULT NULL COMMENT '详细地址',
  `bio` text DEFAULT NULL COMMENT '个人简介',
  `last_login_ip` varchar(45) DEFAULT NULL COMMENT '最后登录IP',
  `last_login_type` varchar(20) DEFAULT NULL COMMENT '最后登录方式',
  `is_active` tinyint(1) DEFAULT NULL COMMENT '是否激活',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属部门ID',
  `mentor_group_id` bigint(20) DEFAULT NULL COMMENT '所属导师组ID(逻辑外键 core_mentor_group)',
  `post_id` bigint(20) DEFAULT NULL COMMENT '所属岗位ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '所属角色ID(已废弃)',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '直属上级ID',
  `oauth_provider` varchar(50) DEFAULT NULL COMMENT 'OAuth提供商',
  `gitee_id` varchar(200) DEFAULT NULL COMMENT 'Gitee用户ID',
  `github_id` varchar(200) DEFAULT NULL COMMENT 'GitHub用户ID',
  `qq_id` varchar(200) DEFAULT NULL COMMENT 'QQ用户openid',
  `google_id` varchar(200) DEFAULT NULL COMMENT 'Google用户ID',
  `wechat_unionid` varchar(200) DEFAULT NULL COMMENT '微信UnionID',
  `wechat_openid` varchar(200) DEFAULT NULL COMMENT '微信OpenID',
  `microsoft_id` varchar(200) DEFAULT NULL COMMENT 'Microsoft用户ID',
  `dingtalk_unionid` varchar(200) DEFAULT NULL COMMENT '钉钉UnionID',
  `dingtalk_userid` varchar(200) DEFAULT NULL COMMENT '钉钉用户UserID',
  `feishu_union_id` varchar(200) DEFAULT NULL COMMENT '飞书UnionID',
  `feishu_userid` varchar(200) DEFAULT NULL COMMENT '飞书用户OpenID',
  `wecom_userid` varchar(200) DEFAULT NULL COMMENT '企业微信UserID',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_core_user_nano_id` (`nano_id`),
  UNIQUE KEY `uk_core_user_username` (`username`,`is_deleted`),
  KEY `idx_core_user_email` (`email`),
  KEY `idx_core_user_mobile` (`mobile`),
  KEY `idx_core_user_dept_id` (`dept_id`),
  KEY `idx_core_user_mentor_group_id` (`mentor_group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `core_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `core_user_role` (
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
  `sys_create_datetime` datetime DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID（逻辑外键关联core_user）',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID（逻辑外键关联core_user）',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID（逻辑外键关联core_dept）',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `idx_core_user_role_role_id` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `equipment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `equipment` (
  `name` varchar(100) NOT NULL COMMENT '设备名称',
  `code` varchar(64) DEFAULT NULL COMMENT '设备编码',
  `lab_id` bigint(20) DEFAULT NULL COMMENT '所属实验室ID(逻辑外键 lab)',
  `category` varchar(64) DEFAULT NULL COMMENT '设备分类',
  `model` varchar(100) DEFAULT NULL COMMENT '型号',
  `serial_number` varchar(100) DEFAULT NULL COMMENT '序列号',
  `status` varchar(20) NOT NULL DEFAULT 'NORMAL' COMMENT '状态: NORMAL/REPAIR/SCRAPPED',
  `custodian_id` bigint(20) DEFAULT NULL COMMENT '保管人ID(逻辑外键 core_user)',
  `purchased_at` date DEFAULT NULL COMMENT '购置日期',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID(逻辑外键 core_user)',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID(逻辑外键 core_user)',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID(逻辑外键 core_dept)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_equipment_nano_id` (`nano_id`),
  UNIQUE KEY `uk_equipment_code` (`code`,`is_deleted`),
  KEY `idx_equipment_lab_id` (`lab_id`),
  KEY `idx_equipment_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='设备';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `lab`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lab` (
  `name` varchar(100) NOT NULL COMMENT '实验室名称',
  `code` varchar(64) DEFAULT NULL COMMENT '实验室编码',
  `location` varchar(200) DEFAULT NULL COMMENT '位置(楼栋/房间)',
  `capacity` int(11) DEFAULT NULL COMMENT '工位容量',
  `status` varchar(20) NOT NULL DEFAULT 'OPEN' COMMENT '状态: OPEN/CLOSED/MAINTENANCE',
  `manager_id` bigint(20) DEFAULT NULL COMMENT '管理员ID(逻辑外键 core_user)',
  `mentor_group_id` bigint(20) DEFAULT NULL COMMENT '所属导师组ID(逻辑外键 core_mentor_group)',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '所属二级部门ID(逻辑外键 core_dept)',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `area` decimal(10,2) DEFAULT NULL COMMENT '实验室面积(m2),单个工位面积=area/capacity',
  `canvas_width` int(11) DEFAULT 1000 COMMENT 'SVG画布宽(单位)',
  `canvas_height` int(11) DEFAULT 600 COMMENT 'SVG画布高(单位)',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID(逻辑外键 core_user)',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID(逻辑外键 core_user)',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID(逻辑外键 core_dept)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_lab_nano_id` (`nano_id`),
  UNIQUE KEY `uk_lab_code` (`code`,`is_deleted`),
  KEY `idx_lab_mentor_group_id` (`mentor_group_id`),
  KEY `idx_lab_manager_id` (`manager_id`),
  KEY `idx_lab_dept_id` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='实验室';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `usage_manual_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usage_manual_record` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `nano_id` varchar(21) NOT NULL,
  `sort` int(11) DEFAULT NULL,
  `is_deleted` int(11) DEFAULT 0,
  `sys_create_datetime` datetime DEFAULT NULL,
  `sys_update_datetime` datetime DEFAULT NULL,
  `sys_creator_id` bigint(20) DEFAULT NULL,
  `sys_modifier_id` bigint(20) DEFAULT NULL,
  `sys_dept_id` bigint(20) DEFAULT NULL,
  `teacher_name` varchar(100) DEFAULT NULL,
  `lab_code` varchar(50) NOT NULL,
  `quantity` int(11) DEFAULT NULL,
  `start_month` varchar(7) NOT NULL,
  `end_month` varchar(7) NOT NULL,
  `area` decimal(10,2) DEFAULT NULL,
  `remark` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_nano_id` (`nano_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `workstation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workstation` (
  `code` varchar(64) NOT NULL COMMENT '工位编号',
  `lab_id` bigint(20) NOT NULL COMMENT '所属实验室ID(逻辑外键 lab)',
  `status` varchar(20) NOT NULL DEFAULT 'IDLE' COMMENT '状态: IDLE/PRE_RESERVED/OCCUPIED/MAINTENANCE/DISABLED',
  `type` varchar(20) NOT NULL DEFAULT 'SEAT' COMMENT '类型: SEAT/DOOR/WINDOW',
  `occupant_mentor_group_id` bigint(20) DEFAULT NULL COMMENT '占用组ID(导师组,逻辑外键 core_mentor_group);预占/占领时有值,释放回IDLE时清空',
  `current_user_id` bigint(20) DEFAULT NULL COMMENT '当前占用者ID(逻辑外键 core_user);预占态=null待分配学生',
  `occupied_at` datetime DEFAULT NULL COMMENT '占用开始时间',
  `description` varchar(500) DEFAULT NULL COMMENT '描述',
  `pos_x` int(11) DEFAULT NULL COMMENT 'SVG x坐标',
  `pos_y` int(11) DEFAULT NULL COMMENT 'SVG y坐标',
  `width` int(11) DEFAULT NULL COMMENT '元素宽(SVG单位)',
  `height` int(11) DEFAULT NULL COMMENT '元素高',
  `z_index` int(11) DEFAULT 0 COMMENT '层级',
  `rotation` int(11) DEFAULT 0 COMMENT '旋转角度',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID(逻辑外键 core_user)',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID(逻辑外键 core_user)',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID(逻辑外键 core_dept)',
  `occupant_mentor_group_name` varchar(100) DEFAULT NULL,
  `manual_occupant_name` varchar(100) DEFAULT NULL COMMENT 'manual occupant name',
  `grade_name` varchar(20) DEFAULT NULL,
  `degree` varchar(20) DEFAULT NULL,
  `expected_release_month` varchar(7) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_workstation_nano_id` (`nano_id`),
  UNIQUE KEY `uk_workstation_lab_code` (`lab_id`,`code`,`is_deleted`),
  KEY `idx_workstation_status` (`status`),
  KEY `idx_workstation_current_user_id` (`current_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工位';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `workstation_occupancy_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workstation_occupancy_history` (
  `workstation_id` bigint(20) NOT NULL COMMENT '工位ID(逻辑外键 workstation)',
  `occupancy_type` varchar(20) NOT NULL COMMENT '占用类型: PRE_RESERVED(预占段) / OCCUPIED(占领段)',
  `source_type` varchar(20) NOT NULL COMMENT '占用来源: RENTAL(月租申请,含学生seat)',
  `source_id` bigint(20) DEFAULT NULL COMMENT '来源ID: RENTAL=workstation_rental_request.id',
  `occupant_mentor_group_id` bigint(20) DEFAULT NULL COMMENT '占用组ID(导师组,逻辑外键 core_mentor_group);RENTAL段有值',
  `occupant_user_id` bigint(20) DEFAULT NULL COMMENT '占领者ID(逻辑外键 core_user); 预占段=NULL; 占领段=学生',
  `occupied_at` datetime NOT NULL COMMENT '本段开始时间',
  `released_at` datetime DEFAULT NULL COMMENT '本段结束时间(NULL=当前占用中)',
  `duration_months` int(11) DEFAULT NULL COMMENT '本段时长(月,闭区间含首尾月,不足一月算一月;释放时回填)',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID(逻辑外键 core_user)',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID(逻辑外键 core_user)',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID(逻辑外键 core_dept)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_oh_nano_id` (`nano_id`),
  KEY `idx_oh_workstation_time` (`workstation_id`,`is_deleted`,`occupied_at`),
  KEY `idx_oh_occupant` (`occupant_user_id`),
  KEY `idx_oh_source` (`source_type`,`source_id`),
  KEY `idx_oh_open` (`workstation_id`,`released_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工位占用历史(分段记录)';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `workstation_rental_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workstation_rental_request` (
  `requester_mentor_group_id` bigint(20) DEFAULT NULL COMMENT '申请导师组ID(逻辑外键 core_mentor_group; 学生申请未匹配导师时为空, 管理员后台确认)',
  `lab_id` bigint(20) NOT NULL COMMENT '实验室ID(逻辑外键 lab)',
  `start_month` varchar(7) NOT NULL COMMENT '起始年月 YYYY-MM(字典序=时间序,可直接比较)',
  `end_month` varchar(7) NOT NULL COMMENT '结束年月 YYYY-MM',
  `workstation_area` decimal(10,2) DEFAULT NULL COMMENT '申请时单个工位面积快照(m2)=lab.area/lab.capacity',
  `month_count` int(11) NOT NULL COMMENT '月数(含起止)',
  `workstation_count` int(11) NOT NULL COMMENT '工位数',
  `status` varchar(20) NOT NULL DEFAULT 'PENDING' COMMENT '状态: PENDING/APPROVED/REJECTED/CANCELLED',
  `reason` varchar(500) DEFAULT NULL COMMENT '申请理由',
  `approver_user_id` bigint(20) DEFAULT NULL COMMENT '审批人ID(逻辑外键 core_user)',
  `reject_reason` varchar(500) DEFAULT NULL COMMENT '拒绝原因',
  `approved_at` datetime DEFAULT NULL COMMENT '审批时间',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID(逻辑外键 core_user)',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID(逻辑外键 core_user)',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID(逻辑外键 core_dept)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_rental_request_nano_id` (`nano_id`),
  KEY `idx_rr_group_status` (`requester_mentor_group_id`,`status`,`is_deleted`),
  KEY `idx_rr_lab` (`lab_id`),
  KEY `idx_rr_status` (`status`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工位申请';
/*!40101 SET character_set_client = @saved_cs_client */;
DROP TABLE IF EXISTS `workstation_rental_request_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `workstation_rental_request_item` (
  `rental_request_id` bigint(20) NOT NULL COMMENT '月租申请ID(逻辑外键 workstation_rental_request)',
  `workstation_id` bigint(20) NOT NULL COMMENT '工位ID(逻辑外键 workstation)',
  `end_month` varchar(7) DEFAULT NULL COMMENT '该工位实际租期结束月YYYY-MM(提前释放时收缩;NULL=沿用申请end_month;可早于start_month=整段作废)',
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID(自增)',
  `nano_id` varchar(21) NOT NULL COMMENT '对外业务ID(NanoId)',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `is_deleted` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否删除',
  `sys_create_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '创建时间',
  `sys_update_datetime` datetime NOT NULL DEFAULT current_timestamp() COMMENT '更新时间',
  `sys_creator_id` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `sys_modifier_id` bigint(20) DEFAULT NULL COMMENT '修改人ID',
  `sys_dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`),
  KEY `idx_rri_request` (`rental_request_id`),
  KEY `idx_rri_workstation` (`workstation_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='工位申请明细(多工位)';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

