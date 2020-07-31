/*
 Navicat Premium Data Transfer

 Source Server         : 腾讯云服务器
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 111.230.9.54:3306
 Source Schema         : coal_management

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 31/07/2020 14:16:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for tb_assessment
-- ----------------------------
DROP TABLE IF EXISTS `tb_assessment`;
CREATE TABLE `tb_assessment` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `company_id` int(11) DEFAULT NULL COMMENT '公司id',
  `headquarters_rating` varchar(255) DEFAULT NULL COMMENT '总集团给分',
  `branch_rating` varchar(255) DEFAULT NULL COMMENT '分公司评分',
  `power_rating` varchar(255) DEFAULT NULL COMMENT '电厂评分',
  `grade` varchar(255) DEFAULT NULL COMMENT '评级',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_assessment
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_audit
-- ----------------------------
DROP TABLE IF EXISTS `tb_audit`;
CREATE TABLE `tb_audit` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `purchase_id` int(11) NOT NULL COMMENT '采购单id',
  `reviewer_id` int(11) NOT NULL COMMENT '审核人id',
  `operation` varchar(255) NOT NULL COMMENT '操作',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `audit_time` datetime DEFAULT NULL COMMENT '审核日期',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_audit
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_authorization
-- ----------------------------
DROP TABLE IF EXISTS `tb_authorization`;
CREATE TABLE `tb_authorization` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `roleid` int(11) NOT NULL COMMENT '角色id',
  `permissionid` int(11) NOT NULL COMMENT '权限id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_authorization
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_coal
-- ----------------------------
DROP TABLE IF EXISTS `tb_coal`;
CREATE TABLE `tb_coal` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_coal
-- ----------------------------
BEGIN;
INSERT INTO `tb_coal` VALUES (1, '无烟煤');
INSERT INTO `tb_coal` VALUES (2, '贫煤');
INSERT INTO `tb_coal` VALUES (3, '贫瘦煤');
INSERT INTO `tb_coal` VALUES (4, '瘦煤');
INSERT INTO `tb_coal` VALUES (5, '焦煤');
INSERT INTO `tb_coal` VALUES (6, '肥煤');
INSERT INTO `tb_coal` VALUES (7, '1/3焦煤');
INSERT INTO `tb_coal` VALUES (8, '气肥煤');
INSERT INTO `tb_coal` VALUES (9, '气煤');
INSERT INTO `tb_coal` VALUES (10, '1/2中粘煤');
INSERT INTO `tb_coal` VALUES (11, '弱粘煤');
INSERT INTO `tb_coal` VALUES (12, '不粘煤');
INSERT INTO `tb_coal` VALUES (13, '长焰煤');
INSERT INTO `tb_coal` VALUES (14, '褐煤');
COMMIT;

-- ----------------------------
-- Table structure for tb_company
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '供应商名称',
  `organization_code` varchar(255) NOT NULL COMMENT '组织机构代码',
  `supplier_abbreviation` varchar(255) NOT NULL COMMENT '供应商简称',
  `legal_representative` varchar(255) NOT NULL COMMENT '法人代表',
  `id_card_legal_representative` varchar(255) NOT NULL COMMENT '法人代表身份证号',
  `registered_capital` double(20,4) NOT NULL COMMENT '注册资金',
  `tax_registration_certificate_code` varchar(255) NOT NULL COMMENT '税务登记证代码',
  `business_license_number` varchar(255) NOT NULL COMMENT '经营执照编号',
  `bank_of_deposit` varchar(255) NOT NULL COMMENT '开户银行',
  `bank_account` varchar(255) NOT NULL COMMENT '开户银行账号',
  `address` varchar(255) NOT NULL COMMENT '注册地址',
  `contacts` varchar(255) NOT NULL COMMENT '联系人',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `phone` varchar(255) NOT NULL COMMENT '联系人电话',
  `contacts_address` varchar(255) NOT NULL COMMENT '联系地址',
  `postal_code` varchar(255) NOT NULL COMMENT '邮政编号',
  `contacts_email` varchar(255) NOT NULL COMMENT '联系人邮箱',
  `storage_location` varchar(255) NOT NULL COMMENT '煤炭存放地点',
  `storage_capacity` double(20,4) NOT NULL COMMENT '煤炭存放量',
  `status` int(1) NOT NULL COMMENT '状态',
  `parentid` int(11) NOT NULL COMMENT '父公司，上级机构的id',
  `type` int(1) NOT NULL COMMENT '公司类型【1:供应商，2:电厂，0:分（子）公司】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_company
-- ----------------------------
BEGIN;
INSERT INTO `tb_company` VALUES (1, '桂林电子科技大学', '123456', '桂电', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (2, '桂林电子科技大学信息科技学院', '123456', '信科', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (3, '西安电子科技大学', '123456', '西电', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (4, '成都电子科技大学', '123456', '成电', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (5, '杭州电子科技大学', '123456', '杭电', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (6, '中国电子科技大学', '123456', '中科大', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
INSERT INTO `tb_company` VALUES (7, '苏州电子科技大学', '123456', '苏电', '123456', '370982199911053073', 5000.0000, '00852XXXXXXX', '6666666666', '中国农业银行', '888888', '桂林', '赵迪', '7684578', '13006930979', '山东省新泰市', '271200', '308501273@qq.com', '广东', 500.0000, 1, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `title` varchar(255) NOT NULL COMMENT '标题',
  `publisher_id` int(11) NOT NULL COMMENT '发布人id',
  `content` varchar(2555) NOT NULL COMMENT '内容',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_update_time` datetime DEFAULT NULL COMMENT '上次更新时间',
  `status` int(1) NOT NULL COMMENT '状态',
  `reviewer_id` int(11) DEFAULT NULL COMMENT '审核人id',
  `remark` varchar(255) DEFAULT NULL COMMENT '审核备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
BEGIN;
INSERT INTO `tb_notice` VALUES (1, '桂林电子科技大学成功研制新冠肺炎检测试剂', 2, '2020年4月，位于广西桂林电子科技大学的研发团队成功研制出了新冠肺炎的检测试剂，只需要是十五分钟就能检测出结果', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (2, '生产实习', 2, '2020年7月桂林电子科技大学进行了生产实习', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (3, '企业合作', 2, '桂林电子科大学与力港网络进行合作', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (4, '校庆', 2, '2020年是桂电的60周年校庆', '2020-07-28 16:31:29', '2020-07-28 16:31:29', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (5, 'test1', 2, '测试', '2020-07-29 16:55:05', '2020-07-29 16:55:05', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (6, '桂林电子科技大学成功研制新冠肺炎检测试剂', 2, '2020年4月，位于广西桂林电子科技大学的研发团队成功研制出了新冠肺炎的检测试剂，只需要是十五分钟就能检测出结果', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (7, '生产实习', 2, '2020年7月桂林电子科技大学进行了生产实习', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 2, 2, NULL);
INSERT INTO `tb_notice` VALUES (8, '企业合作', 2, '桂林电子科大学与力港网络进行合作', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 2, 2, NULL);
INSERT INTO `tb_notice` VALUES (9, '校庆', 2, '2020年是桂电的60周年校庆', '2020-07-28 16:31:29', '2020-07-28 16:31:29', 2, NULL, NULL);
INSERT INTO `tb_notice` VALUES (10, 'test1', 2, '测试', '2020-07-29 16:55:05', '2020-07-29 16:55:05', 2, NULL, NULL);
INSERT INTO `tb_notice` VALUES (11, '桂林电子科技大学成功研制新冠肺炎检测试剂', 2, '2020年4月，位于广西桂林电子科技大学的研发团队成功研制出了新冠肺炎的检测试剂，只需要是十五分钟就能检测出结果', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 2, 2, NULL);
INSERT INTO `tb_notice` VALUES (12, '生产实习', 2, '2020年7月桂林电子科技大学进行了生产实习', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (13, '企业合作', 2, '桂林电子科大学与力港网络进行合作', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (14, '校庆', 2, '2020年是桂电的60周年校庆', '2020-07-28 16:31:29', '2020-07-28 16:31:29', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (15, 'test1', 2, '测试', '2020-07-29 16:55:05', '2020-07-29 16:55:05', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (16, '桂林电子科技大学成功研制新冠肺炎检测试剂', 2, '2020年4月，位于广西桂林电子科技大学的研发团队成功研制出了新冠肺炎的检测试剂，只需要是十五分钟就能检测出结果', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (17, '生产实习', 2, '2020年7月桂林电子科技大学进行了生产实习', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (18, '企业合作', 2, '桂林电子科大学与力港网络进行合作', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (19, '校庆', 2, '2020年是桂电的60周年校庆', '2020-07-28 16:31:29', '2020-07-28 16:31:29', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (20, 'test1', 2, '测试', '2020-07-29 16:55:05', '2020-07-29 16:55:05', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (21, '桂林电子科技大学成功研制新冠肺炎检测试剂', 2, '2020年4月，位于广西桂林电子科技大学的研发团队成功研制出了新冠肺炎的检测试剂，只需要是十五分钟就能检测出结果', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (22, '生产实习', 2, '2020年7月桂林电子科技大学进行了生产实习', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (23, '企业合作', 2, '桂林电子科大学与力港网络进行合作', '2020-07-28 16:18:23', '2020-07-28 16:18:27', 1, 2, NULL);
INSERT INTO `tb_notice` VALUES (24, '校庆', 2, '2020年是桂电的60周年校庆', '2020-07-28 16:31:29', '2020-07-28 16:31:29', 0, NULL, NULL);
INSERT INTO `tb_notice` VALUES (25, 'test1', 2, '测试', '2020-07-29 16:55:05', '2020-07-29 16:55:05', 0, NULL, NULL);
COMMIT;

-- ----------------------------
-- Table structure for tb_permission
-- ----------------------------
DROP TABLE IF EXISTS `tb_permission`;
CREATE TABLE `tb_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) NOT NULL COMMENT '权限名',
  `url` varchar(255) NOT NULL COMMENT '资源路径',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for tb_purchase_application
-- ----------------------------
DROP TABLE IF EXISTS `tb_purchase_application`;
CREATE TABLE `tb_purchase_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `applicant_company_id` int(11) NOT NULL COMMENT '申请单位ID',
  `applicant_id` int(11) NOT NULL COMMENT '申请人ID',
  `issuer_id` int(11) NOT NULL COMMENT '签发人ID',
  `application_date` datetime NOT NULL COMMENT '申请日期',
  `delivery_start_time` datetime NOT NULL COMMENT '缴费开始时间',
  `delivery_end_time` datetime NOT NULL COMMENT '缴费截止时间',
  `coal_variety` int(3) NOT NULL COMMENT '煤种id',
  `quantity` double(20,4) NOT NULL COMMENT '采购数量(单位:万吨)',
  `transport` int(1) NOT NULL COMMENT '运输方式(1.海运2.陆运3.其他)',
  `destination` varchar(255) NOT NULL COMMENT '交货地点',
  `settlement` int(1) NOT NULL COMMENT '结算方式(1.现金2.支票3.其他)',
  `Inspection` int(1) NOT NULL COMMENT '验货方式(1.现场核验2.第三方核验3.其他)',
  `price_ceiling` double(20,4) DEFAULT NULL COMMENT '最高限价',
  `floor_price` double(20,4) DEFAULT NULL COMMENT '最低限价',
  `payment` varchar(255) NOT NULL COMMENT '结算方式',
  `calorific` double(11,4) DEFAULT NULL COMMENT '收到基单位发热量',
  `base_total_sulfur` double(11,4) DEFAULT NULL COMMENT '收到基全硫',
  `water_content` double(11,4) DEFAULT NULL COMMENT '全水分',
  `basis_ash` double(11,4) DEFAULT NULL COMMENT '收到基灰分',
  `basis_volatile` double(11,4) DEFAULT NULL COMMENT '收到基挥发分',
  `airdry_basis_moisture` double(11,4) DEFAULT NULL COMMENT '空干基水分',
  `airdry_basis_total_sulfur` double(11,4) DEFAULT NULL COMMENT '空干基全硫',
  `airdry_basis_volatile` double(11,4) DEFAULT NULL COMMENT '空干基挥发分',
  `dry_basis_calorific` double(11,4) DEFAULT NULL COMMENT '干基高位发热量',
  `dry_basis_total_sulfur` double(11,4) DEFAULT NULL COMMENT '干基全硫',
  `dry_basis_volatile` double(11,4) DEFAULT NULL COMMENT '干基挥发分',
  `granularity` double(11,4) DEFAULT NULL COMMENT '粒度',
  `ash_melting_point` double(11,4) DEFAULT NULL COMMENT '灰熔点',
  `hardy_grindability_coefficient` double(11,4) DEFAULT NULL COMMENT '哈式可磨系数',
  `remarks` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` int(1) NOT NULL COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_purchase_application
-- ----------------------------
BEGIN;
INSERT INTO `tb_purchase_application` VALUES (1, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 1, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (2, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 1, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (3, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 2, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (4, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 2, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (5, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 2, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (6, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 3, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (7, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 3, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
INSERT INTO `tb_purchase_application` VALUES (8, 1, 1, 2, '2020-07-31 13:50:41', '2020-08-01 13:50:45', '2020-08-05 13:50:50', 3, 500.0000, 1, '山东省青岛市', 1, 1, 5.0000, 4.3000, '1', 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, 10.0000, '10', 1);
COMMIT;

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色',
  `remarks` varchar(255) DEFAULT NULL COMMENT '描述',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role
-- ----------------------------
BEGIN;
INSERT INTO `tb_role` VALUES (1, '普通用户', '平台的普通用户');
INSERT INTO `tb_role` VALUES (2, '系统管理员', '本平台的超级管理员，直属国家电网，负责所有用户管理和公司管理');
INSERT INTO `tb_role` VALUES (3, '审核员', '负责采购清单的审核');
INSERT INTO `tb_role` VALUES (4, '公告发布人员', '编辑公告并发布');
INSERT INTO `tb_role` VALUES (5, '公告审核人员', '审核公告发布人的公告');
INSERT INTO `tb_role` VALUES (6, '燃料业务员', '创建采购中标清单');
INSERT INTO `tb_role` VALUES (7, '采购审核人员', '审核采购中标清单');
INSERT INTO `tb_role` VALUES (8, '分公司管理员', '分公司的管理员，负责分公司及其下属电厂管理员管理，以及合作方的审核管理');
COMMIT;

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_name` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `email` varchar(255) NOT NULL COMMENT '邮箱',
  `role_id` int(11) NOT NULL COMMENT '所属角色id',
  `company_id` int(11) NOT NULL COMMENT '所属公司或者电厂的id',
  `department` varchar(255) DEFAULT NULL COMMENT '公司部门',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '用户状态',
  `salt` varchar(255) NOT NULL COMMENT '加密盐值',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
BEGIN;
INSERT INTO `tb_user` VALUES (2, 'zhaodi', '8347614cef9dbd0696187c88ee7ff44d', '赵迪', '13006930979', '308501273@qq.com', 1, 1, '技术研发部', 1, 'a609b7bad2ed46699fb840a7c3537ee3', '2020-07-23 16:44:18', '2020-07-31 14:02:22');
INSERT INTO `tb_user` VALUES (3, 'zhaodi', '5ec777cf2ea2799e69fa588163239ad9', '赵迪', '13006930979', '308501273@qq.com', 1, 1, '技术研发部', 1, '174def50813245bb9a9b9836da908dbd', '2020-07-24 11:24:13', NULL);
INSERT INTO `tb_user` VALUES (4, 'zhaodi', 'cd0f20bab79c22025a4e6ecee264e126', '赵迪', '13006930979', '308501273@qq.com', 1, 1, '技术研发部', 1, '75fe8f64e1b341719f1cf23c98d4f3a7', '2020-07-24 14:23:20', NULL);
INSERT INTO `tb_user` VALUES (5, 'zhaodi', '714772cc52e6a7bcb5393bd4373d6624', 'fengenchun', '12345678912', '123456789@qq.com', 1, 1, '12', 1, '5b05dcde5d814fb0a65aebb19b959231', '2020-07-24 17:10:37', NULL);
INSERT INTO `tb_user` VALUES (6, 'fen', '195beb3ec076861e5365135bc51cecf1', 'fen', '12345678912', '123456789@qq.com', 1, 1, '12', 1, 'dbcd57cae90a476bba1b3f7edb50cf46', '2020-07-25 10:52:34', NULL);
INSERT INTO `tb_user` VALUES (7, 'yi', '4837567363124cc9fe09bba81df7c60f', 'yi', '12345678912', '123456789@qq.com', 1, 1, '12', 1, '7c0caa0727cb48f09a44a662470b002c', '2020-07-25 10:56:23', NULL);
INSERT INTO `tb_user` VALUES (8, 'zhaodi', '8088edc53470332494e32d323034bddc', '123', '12345678912', '123456789@qq.com', 1, 1, '12', 1, '07267a0193c545aeafe8ca09d4fb8fe6', '2020-07-25 11:14:17', NULL);
INSERT INTO `tb_user` VALUES (9, 'yi', '292f2c451cc9303f096401ac90419307', 'yi', '12345678913', '1234567895@qq.com', 1, 1, '12', 1, 'a117ab529fc14217b846c87b9b1b6275', '2020-07-25 11:18:21', NULL);
INSERT INTO `tb_user` VALUES (10, 'er', '5cce49754a3735ec1829057d3bbb8c35', 'er', '12345678912', '123456789@qq.com', 1, 1, '12', 1, 'fbef16145f8b48a3bc51417bbcd4516d', '2020-07-25 11:20:50', NULL);
INSERT INTO `tb_user` VALUES (11, 'san', '452b91187558430967f9e095137c2e06', 'san', '12345678912', '123456@qq.com', 1, 1, '12', 1, '48930c9ece4447a2bcd592373def4352', '2020-07-25 12:11:03', NULL);
INSERT INTO `tb_user` VALUES (12, 'wu', 'be5cddb6bb4d5a12386626665f0acd47', 'wu', '123456', '123456@qq.com', 1, 1, '123', 1, '8c8e3564b93f4920b091851712280e11', '2020-07-25 12:11:50', NULL);
INSERT INTO `tb_user` VALUES (13, 'six', '6d1f86ef4c25d8c5f8f807ca288092a3', 'six', '123456', '123456@qq.com', 1, 1, '1', 1, '6fc7ffe038284fa7877362022c66cef8', '2020-07-25 12:13:00', NULL);
INSERT INTO `tb_user` VALUES (14, 'lili', '497deccb38b18bd2157c3c9c254afe38', '123', '123', '13', 1, 7, '23', 1, '14428c71e9a046bdbf3635ab0d12f7bb', '2020-07-28 17:43:31', '2020-07-28 17:43:40');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
