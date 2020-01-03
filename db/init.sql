-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.7.28-log - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.2.0.5723
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 inc 的数据库结构
CREATE DATABASE IF NOT EXISTS `inc` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `inc`;

-- 导出  表 inc.oauth_approvals 结构
CREATE TABLE IF NOT EXISTS `oauth_approvals` (
  `userId` varchar(256) DEFAULT NULL,
  `clientId` varchar(256) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  `expiresAt` datetime DEFAULT NULL,
  `lastModifiedAt` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  inc.oauth_approvals 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `oauth_approvals` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_approvals` ENABLE KEYS */;

-- 导出  表 inc.oauth_client_details 结构
CREATE TABLE IF NOT EXISTS `oauth_client_details` (
  `client_id` varchar(255) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(255) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` text,
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `archived` tinyint(1) DEFAULT '0',
  `trusted` tinyint(1) DEFAULT '0',
  `autoapprove` varchar(255) DEFAULT 'false',
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  inc.oauth_client_details 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` (`client_id`, `resource_ids`, `client_secret`, `scope`, `authorized_grant_types`, `web_server_redirect_uri`, `authorities`, `access_token_validity`, `refresh_token_validity`, `additional_information`, `create_time`, `archived`, `trusted`, `autoapprove`) VALUES
	('client_2', '', '$2a$10$Kbhr9YqVzNOD2sCtTTrBzO8csiNkscUZNFh6eMenOgg9eR.OsANqW', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, '', NULL, NULL, NULL, '2019-12-10 13:52:49', 0, 0, 'false'),
	('ics-api', 'ics-uaa', '$2a$10$Kbhr9YqVzNOD2sCtTTrBzO8csiNkscUZNFh6eMenOgg9eR.OsANqW', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, '', NULL, NULL, NULL, '2019-12-10 13:52:49', 0, 0, 'false'),
	('ics-uaa', 'ics-api', '$2a$10$Kbhr9YqVzNOD2sCtTTrBzO8csiNkscUZNFh6eMenOgg9eR.OsANqW', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, '', NULL, NULL, NULL, '2019-12-10 13:52:49', 0, 0, 'false'),
	('inc-api', '', '$2a$10$Kbhr9YqVzNOD2sCtTTrBzO8csiNkscUZNFh6eMenOgg9eR.OsANqW', 'all', 'password,client_credentials,refresh_token,authorization_code', NULL, '', NULL, NULL, NULL, '2019-12-10 13:52:49', 0, 0, 'false');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;

-- 导出  表 inc.oauth_code 结构
CREATE TABLE IF NOT EXISTS `oauth_code` (
  `code` varchar(256) DEFAULT NULL,
  `authentication` blob
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  inc.oauth_code 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `oauth_code` DISABLE KEYS */;
/*!40000 ALTER TABLE `oauth_code` ENABLE KEYS */;

-- 导出  表 inc.sys_access_log 结构
CREATE TABLE IF NOT EXISTS `sys_access_log` (
  `rid` varchar(36) NOT NULL,
  `optclasses` varchar(100) DEFAULT NULL,
  `optmethod` varchar(100) DEFAULT NULL,
  `optkey` varchar(100) DEFAULT NULL,
  `createtime` datetime DEFAULT NULL,
  `complettime` datetime DEFAULT NULL,
  `opthostip` varchar(100) DEFAULT NULL,
  `opthostname` varchar(20) DEFAULT NULL,
  `optusername` varchar(50) DEFAULT NULL,
  `optdeptname` varchar(50) DEFAULT NULL,
  `optcontent` text,
  `optuserid` varchar(36) DEFAULT NULL,
  `optdeptid` varchar(36) DEFAULT NULL,
  `sessionid` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='日志表';

-- 导出  表 inc.sys_authlogin 结构
CREATE TABLE IF NOT EXISTS `sys_authlogin` (
  `id` varchar(36) NOT NULL,
  `userid` varchar(32) DEFAULT NULL COMMENT '用户id',
  `wechat_unionid` varchar(60) DEFAULT NULL COMMENT '微信unionid',
  `wechat_openid` varchar(50) DEFAULT NULL COMMENT '公众号openid',
  `miniprog_unionid` varchar(20) DEFAULT NULL COMMENT '小程序unioinid',
  `miniprog_openid` varchar(20) DEFAULT NULL COMMENT '小程序openid',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户第三方授权登录表';

-- 正在导出表  inc.sys_authlogin 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_authlogin` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_authlogin` ENABLE KEYS */;

-- 导出  表 inc.sys_organization 结构
CREATE TABLE IF NOT EXISTS `sys_organization` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '组织名称',
  `parent_id` varchar(36) DEFAULT NULL COMMENT '父级id',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织表';

-- 正在导出表  inc.sys_organization 的数据：~4 rows (大约)
/*!40000 ALTER TABLE `sys_organization` DISABLE KEYS */;
INSERT INTO `sys_organization` (`id`, `name`, `parent_id`, `description`, `create_date`, `create_userid`, `del_date`, `del_uerid`, `status`) VALUES
	('56b69b9c-a08f-4aad-b9a0-68bda41e996f', '总局', 'system', '', '2019-12-26 16:54:41', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('573b98e6-2ed7-4980-9973-5e7b365c89d8', '分局', '56b69b9c-a08f-4aad-b9a0-68bda41e996f', '', '2019-12-26 17:43:15', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '2019-12-26 17:55:56', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 0),
	('593b3f77-5b41-4c41-95a2-d50fd1e49d19', '支行', 'system', '', '2019-12-26 17:39:50', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('9493ef12-99bb-444e-98f2-4f9f11e13869', '子行', '56b69b9c-a08f-4aad-b9a0-68bda41e996f', '', '2019-12-26 17:58:04', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1);
/*!40000 ALTER TABLE `sys_organization` ENABLE KEYS */;

-- 导出  表 inc.sys_organization_user 结构
CREATE TABLE IF NOT EXISTS `sys_organization_user` (
  `id` varchar(36) NOT NULL,
  `organization_id` varchar(36) DEFAULT NULL COMMENT '组织id',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='组织用户表';

-- 正在导出表  inc.sys_organization_user 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_organization_user` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_organization_user` ENABLE KEYS */;

-- 导出  表 inc.sys_permission 结构
CREATE TABLE IF NOT EXISTS `sys_permission` (
  `id` varchar(36) NOT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '权限名称',
  `description` varchar(256) DEFAULT NULL COMMENT '权限描述',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='权限表';

-- 正在导出表  inc.sys_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_permission` ENABLE KEYS */;

-- 导出  表 inc.sys_role 结构
CREATE TABLE IF NOT EXISTS `sys_role` (
  `id` varchar(36) NOT NULL,
  `role` varchar(20) DEFAULT NULL COMMENT '角色标记',
  `role_name` varchar(32) DEFAULT NULL COMMENT '角色名称',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色表';

-- 正在导出表  inc.sys_role 的数据：~10 rows (大约)
/*!40000 ALTER TABLE `sys_role` DISABLE KEYS */;
INSERT INTO `sys_role` (`id`, `role`, `role_name`, `description`, `create_date`, `create_userid`, `del_date`, `del_uerid`, `status`) VALUES
	('10260637-7248-4541-a2c8-80a3abd12a40', 'son', '儿子', '', '2019-12-26 13:53:07', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('582346ac-136d-4f3f-9d4a-8dc276d24047', 'sister', '姐姐', '', '2019-12-26 13:53:22', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('610ff5c5-a040-4126-ac8a-68a87af49408', 'user', '用户', '普通用户', '2019-12-26 13:52:04', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('a651c006-fcf1-45bf-a9e9-9aff5e02b51e', 'worker', '工人', '', '2019-12-26 13:52:30', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('aedbadc4-d9b4-43af-a202-cff9d9b38a26', 'admin', '管理员', '最高权限', '2019-12-26 13:48:19', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('cda279ff-090d-450b-865c-abc594193d65', 'teacher', '老师', '', '2019-12-26 13:52:21', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('d3010915-5910-49dd-adee-5d1a142491aa', 'student', '学生', '', '2019-12-26 13:52:14', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('e186df0d-bdb0-4138-a7ad-7b5acc778f4c', 'father', '爸爸', '', '2019-12-26 13:53:01', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '2019-12-26 13:57:20', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 0),
	('e657026c-512b-473f-bfe0-1122b7dfcc17', 'leader', '工头', '', '2019-12-26 13:52:52', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('fdb83c88-c76b-40d5-a891-1b4e401a0ba1', 'father', '爸爸', '', '2019-12-26 13:57:39', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1);
/*!40000 ALTER TABLE `sys_role` ENABLE KEYS */;

-- 导出  表 inc.sys_role_permission 结构
CREATE TABLE IF NOT EXISTS `sys_role_permission` (
  `id` varchar(36) NOT NULL,
  `role_id` varchar(36) DEFAULT NULL COMMENT '角色id',
  `permission_id` varchar(36) DEFAULT NULL COMMENT '权限id',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色权限表';

-- 正在导出表  inc.sys_role_permission 的数据：~0 rows (大约)
/*!40000 ALTER TABLE `sys_role_permission` DISABLE KEYS */;
/*!40000 ALTER TABLE `sys_role_permission` ENABLE KEYS */;

-- 导出  表 inc.sys_user 结构
CREATE TABLE IF NOT EXISTS `sys_user` (
  `id` varchar(36) NOT NULL,
  `username` varchar(32) DEFAULT NULL COMMENT '用户名',
  `password` varchar(60) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '别名',
  `mobile` varchar(20) DEFAULT NULL COMMENT '电话',
  `email` varchar(50) DEFAULT NULL COMMENT 'email',
  `access_token` varchar(255) DEFAULT NULL COMMENT '认证token',
  `refresh_token` varchar(255) DEFAULT NULL COMMENT '刷新token',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- 正在导出表  inc.sys_user 的数据：~16 rows (大约)
/*!40000 ALTER TABLE `sys_user` DISABLE KEYS */;
INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `mobile`, `email`, `access_token`, `refresh_token`, `create_date`, `create_userid`, `del_date`, `del_uerid`, `status`) VALUES
	('1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'admin', '$2a$10$i4SLFLadsDh3m1/LOdLvQuBv80fc6Atyg1zW8os/Y0Fkfc9bJu56C', '管理员', '', '', NULL, NULL, '2019-12-18 14:11:03', NULL, NULL, NULL, 1),
	('2bb253bb-2dc1-4d95-acdb-c3c7f2485cd9', 'user_1', '$2a$10$sTK1LwCvu8NwGbVQACG69.PcNzt1IitXo4WkXKtBx4sOHHb8RGoXq', '测试用户', '', '', NULL, NULL, '2019-12-16 12:49:47', NULL, NULL, NULL, 1),
	('452e2df0-9957-4568-9cd7-56690af772f1', '啊啊倒萨', '$2a$10$qT2bCbWKgnSua7GHhkiemeu4I1YafPJlLbJ7r9w4AYMHDe1bNjf02', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:16', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('6888b293-8139-4533-a192-5b6de0014266', 'ss', '$2a$10$z0T4ei1x2ZceYvVcedNEAO274KeXJfhK/2Ec/YJiOxMujmh5PqVE.', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:40', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '啊啊倒萨', '$2a$10$hzJ9Qxy.B4tflnWfQfPreuZFxfWX63KESKNBFOS9GSD8R7qB5QRG2', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:10', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('8a1e1227-90da-4838-8efa-6c7c81a895e1', 'ss', '$2a$10$F7rj6tK5cjyqWtkLzu.1S.GmwiPVN8Hx3j32Rz5nKZmYuztBcKinu', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:33', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('9658e7b9-0163-44aa-8e4d-ffb53bbe9083', 'ss', '$2a$10$O1ELaL4y4IZ5LIi9lHI9..X5.xaK.moa9tTU.JEK8uowiaPivtFzK', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:30', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('a4d2f765-8d18-4e5e-bffa-5b29075bf183', 'ssss', '$2a$10$XqpTHfQLHSbvn8rPZmQdMu2o17i4sG5uRNoz4hhm91idgle/NLqt.', '张三', '13521789759', '暗室逢灯但是', NULL, NULL, '2019-12-26 12:55:57', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '2019-12-26 13:15:26', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 0),
	('aa3319f8-98ab-4d8a-b0a2-6ae66d457c9c', '啊啊倒萨', '$2a$10$8dvIvB6sY5V3P7YYuLfjse1hjDIioW1.gZ1WDptgvFwn8fQpy14tm', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:15', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('b782ab6f-9f12-4fc0-88cd-f1b265e3b197', '啊啊倒萨', '$2a$10$1TEyq61N7nZB5Z7I2SvUM.pUDPkWZaxpXgDIsvvnSjE5ST2Lxgpxu', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:14', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('c70fa118-d3d0-4df9-82f7-9f8e74275ccc', 'ss', '$2a$10$cNl8nYnIhjL0A.EVKItIZewhShAsQ0bHnWvb8K87MZzitHaAoz4py', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:35', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('cd1dbe72-efe0-455b-9b1b-0115711929ed', 'ss', '$2a$10$c8BggXY1Lnru8FgzCOIQSOaHRSTqABDcGN5VFBDX12FWuEUYK9WYK', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:38', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('d5fcf54e-1319-48d0-a7db-ffe54ec76bd3', '啊啊倒萨', '$2a$10$0ijKWuJCs2kgLA6fCV.NFeUdvQYVUAtnz7pnitmKFkdL1QV1L/kti', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:09', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('dd1e13e1-1588-4bb8-8dda-529f279d3c43', 'ss', '$2a$10$PlSYcR.ZKI.5claimaj8nO0b9dceWewe7zSmUJadZnmfiEXrkClMu', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:36', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('f13b6344-aea4-4e85-82c9-96f3c5fbf9a5', 'ss', '$2a$10$wmi9iOas6AVfN3mlTOYO0eKhh6jo/Lf.MdMvqufcZJ3bK./D6ANFu', 'sssss', 'sss', 'ssss', NULL, NULL, '2019-12-26 13:16:39', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1),
	('f8dd1fab-88fb-4c08-b77f-79b19703018f', '啊啊倒萨', '$2a$10$enMKW/Nqyl8QnTWDP2gmVuf0SLQBmDNfOmi15LV4cUjmoq9OvC3uu', '萨达', '啊飒飒的', '阿斯顿啊', NULL, NULL, '2019-12-27 10:55:12', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', NULL, NULL, 1);
/*!40000 ALTER TABLE `sys_user` ENABLE KEYS */;

-- 导出  表 inc.sys_user_role 结构
CREATE TABLE IF NOT EXISTS `sys_user_role` (
  `id` varchar(36) NOT NULL,
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户id',
  `role_id` varchar(36) DEFAULT NULL COMMENT '角色id',
  `create_date` datetime DEFAULT NULL,
  `create_userid` varchar(36) DEFAULT NULL,
  `del_date` datetime DEFAULT NULL,
  `del_uerid` varchar(36) DEFAULT NULL,
  `status` int(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

-- 正在导出表  inc.sys_user_role 的数据：~35 rows (大约)
/*!40000 ALTER TABLE `sys_user_role` DISABLE KEYS */;
INSERT INTO `sys_user_role` (`id`, `user_id`, `role_id`, `create_date`, `create_userid`, `del_date`, `del_uerid`, `status`) VALUES
	('052d74bc-33c0-4da9-8c36-2b8660125f22', '452e2df0-9957-4568-9cd7-56690af772f1', 'cda279ff-090d-450b-865c-abc594193d65', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('07164947-66b3-4b25-849a-e052ec805e2c', '452e2df0-9957-4568-9cd7-56690af772f1', 'a651c006-fcf1-45bf-a9e9-9aff5e02b51e', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('1089d072-d683-47f4-aada-ad8a46eecceb', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'aedbadc4-d9b4-43af-a202-cff9d9b38a26', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('179b52e3-1451-4b53-abb2-0d45edf78b32', '452e2df0-9957-4568-9cd7-56690af772f1', '582346ac-136d-4f3f-9d4a-8dc276d24047', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('1ba5460a-22d1-42c5-b5d5-56176204b5ea', '452e2df0-9957-4568-9cd7-56690af772f1', '', '2019-12-27 16:32:44', NULL, NULL, NULL, 1),
	('220a35b4-04e0-4252-b84d-7a4ad9b4fe12', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'cda279ff-090d-450b-865c-abc594193d65', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('2366368d-28e7-4ea9-90d9-5540ac87d2aa', '452e2df0-9957-4568-9cd7-56690af772f1', 'aedbadc4-d9b4-43af-a202-cff9d9b38a26', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('2c4a7696-c4a8-4b65-bb64-1c59159f6a1c', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'a651c006-fcf1-45bf-a9e9-9aff5e02b51e', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('31ff14cb-58d5-443a-95ac-cf2579fe87c9', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '10260637-7248-4541-a2c8-80a3abd12a40', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('33478f8c-e672-44e9-a8df-20cb74ea9aa8', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'fdb83c88-c76b-40d5-a891-1b4e401a0ba1', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('3804d1b4-1360-4330-bd5e-c75ddc60fb0d', '452e2df0-9957-4568-9cd7-56690af772f1', 'e657026c-512b-473f-bfe0-1122b7dfcc17', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('40bc1a69-f0d3-443d-88a5-063215be8919', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'e657026c-512b-473f-bfe0-1122b7dfcc17', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('42a707ac-1eab-49de-8a16-63da5c3ad79c', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'd3010915-5910-49dd-adee-5d1a142491aa', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('450a003d-ddd7-4aa1-90a2-e20b2e14e200', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'aedbadc4-d9b4-43af-a202-cff9d9b38a26', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('46dfb6f9-22ba-43a4-a657-3c99be7096a8', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '582346ac-136d-4f3f-9d4a-8dc276d24047', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('47afc8d8-a98d-422a-8350-509097a9745b', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '610ff5c5-a040-4126-ac8a-68a87af49408', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('4e2bb38f-1093-452f-b81d-71418ccbe642', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '', '2019-12-27 16:33:02', NULL, NULL, NULL, 0),
	('5020dfd4-3e8d-4968-8707-19811ec9c513', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'aedbadc4-d9b4-43af-a202-cff9d9b38a26', '2019-12-27 16:26:54', NULL, NULL, NULL, 1),
	('6353f5fd-f7d4-4644-b3e0-a47845c76888', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', '', '2019-12-27 16:33:07', NULL, NULL, NULL, 1),
	('63bb57f2-7cc5-4f1e-9a9e-8e3b12ecb111', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '610ff5c5-a040-4126-ac8a-68a87af49408', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('6beb676b-b7a1-4aee-a457-0e18af618418', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'a651c006-fcf1-45bf-a9e9-9aff5e02b51e', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('775753e1-6ee3-405f-8368-8d759cfb61bf', '452e2df0-9957-4568-9cd7-56690af772f1', 'd3010915-5910-49dd-adee-5d1a142491aa', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('79542e8d-2402-41f3-a7c4-6e80318c6707', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'd3010915-5910-49dd-adee-5d1a142491aa', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('7e5a3aaf-9210-4440-8447-a05b79dea277', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '582346ac-136d-4f3f-9d4a-8dc276d24047', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('85306dc6-e1bc-4118-9dc0-8eb344a4154f', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'cda279ff-090d-450b-865c-abc594193d65', '2019-12-27 16:33:05', NULL, NULL, NULL, 0),
	('8a61e26f-ade4-451f-83d1-54ba5e48fe1c', '452e2df0-9957-4568-9cd7-56690af772f1', 'fdb83c88-c76b-40d5-a891-1b4e401a0ba1', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('8d40e866-ddce-4538-8e06-cb745a0b06f0', '452e2df0-9957-4568-9cd7-56690af772f1', '10260637-7248-4541-a2c8-80a3abd12a40', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('b4f6b1a4-9693-4143-868c-dc3048c2825b', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'cda279ff-090d-450b-865c-abc594193d65', '2019-12-27 16:26:54', NULL, NULL, NULL, 1),
	('b58078e1-440e-41b3-8431-9a8dde899f4f', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '10260637-7248-4541-a2c8-80a3abd12a40', '2019-12-27 16:26:54', NULL, NULL, NULL, 1),
	('b8dd12af-d6f1-47a5-aacb-be7659bbced3', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '10260637-7248-4541-a2c8-80a3abd12a40', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('d66f8c50-6494-4446-9db3-b683b8bdc8e9', '1d364321-a7e5-4345-9850-8d6ee7b86dcd', 'aedbadc4-d9b4-43af-a202-cff9d9b38a26', '2019-12-27 16:32:59', NULL, NULL, NULL, 0),
	('d9ce7432-c4dc-4fcd-a1ac-ae49f7b547a1', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', '610ff5c5-a040-4126-ac8a-68a87af49408', '2019-12-27 16:26:54', NULL, NULL, NULL, 1),
	('e222e4ed-b34c-4715-b744-d1cb84c866b4', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'e657026c-512b-473f-bfe0-1122b7dfcc17', '2019-12-27 15:40:49', NULL, NULL, NULL, 0),
	('e42d356e-33cb-4513-ae74-e61ae6c0ab6c', '452e2df0-9957-4568-9cd7-56690af772f1', '610ff5c5-a040-4126-ac8a-68a87af49408', '2019-12-27 15:48:04', NULL, NULL, NULL, 0),
	('f619cc2b-1a38-4705-9833-ef51500c3e0a', '7ecdf4e1-eff2-4b60-a5ea-a0373fdfd650', 'fdb83c88-c76b-40d5-a891-1b4e401a0ba1', '2019-12-27 15:40:49', NULL, NULL, NULL, 0);
/*!40000 ALTER TABLE `sys_user_role` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
