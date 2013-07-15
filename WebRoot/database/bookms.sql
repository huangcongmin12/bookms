/*
Navicat MySQL Data Transfer
Source Host     : localhost:3306
Source Database : bookms
Target Host     : localhost:3306
Target Database : bookms
Date: 2012-12-16 00:28:38
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for amercement
-- ----------------------------
DROP TABLE IF EXISTS `amercement`;
CREATE TABLE `amercement` (
  `id` int(11) NOT NULL auto_increment,
  `detail` varchar(50) default NULL,
  `mulct` double default NULL,
  `pay` int(11) default NULL,
  `payTime` varchar(50) default NULL,
  `borrow_id` int(11) default NULL,
  `student_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKEA0657D93358F448` (`student_id`),
  KEY `FKEA0657D9E9897B6C` (`borrow_id`),
  CONSTRAINT `FKEA0657D93358F448` FOREIGN KEY (`student_id`) REFERENCES `studnet` (`id`),
  CONSTRAINT `FKEA0657D9E9897B6C` FOREIGN KEY (`borrow_id`) REFERENCES `borrow` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of amercement
-- ----------------------------
INSERT INTO `amercement` VALUES ('6', '丢书', '100', '1', '2012-09-24', '8', '10');
INSERT INTO `amercement` VALUES ('7', '丢书', '90', '1', '2012-09-24', '12', '1');
INSERT INTO `amercement` VALUES ('8', '丢书', '80', '1', '2012-09-24', '17', '1');
INSERT INTO `amercement` VALUES ('11', '超期', '0.1', '1', '2012-09-24', '24', '23');
INSERT INTO `amercement` VALUES ('15', '丢书', '204', '1', '2012-09-24', '7', '9');
INSERT INTO `amercement` VALUES ('16', '超期', '1.9', '0', null, '11', '1');

-- ----------------------------
-- Table structure for appointment
-- ----------------------------
DROP TABLE IF EXISTS `appointment`;
CREATE TABLE `appointment` (
  `id` int(11) NOT NULL auto_increment,
  `endTime` varchar(50) default NULL,
  `book_id` int(11) default NULL,
  `student_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKA8155B9F49DAB36C` (`book_id`),
  KEY `FKA8155B9F3358F448` (`student_id`),
  CONSTRAINT `FKA8155B9F3358F448` FOREIGN KEY (`student_id`) REFERENCES `studnet` (`id`),
  CONSTRAINT `FKA8155B9F49DAB36C` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of appointment
-- ----------------------------
INSERT INTO `appointment` VALUES ('2', '2012-09-26', '17', '20');
INSERT INTO `appointment` VALUES ('3', '2012-09-26', '20', '23');
INSERT INTO `appointment` VALUES ('5', '2012-10-15', '4', '1');

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` int(11) NOT NULL auto_increment,
  `author` varchar(50) default NULL,
  `bookISBN` varchar(50) default NULL,
  `bookNumber` varchar(50) default NULL,
  `bookPrice` double default NULL,
  `bookStatus` int(11) default NULL,
  `bookName` varchar(50) default NULL,
  `buyTime` varchar(50) default NULL,
  `publish` varchar(50) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `bookNumber` (`bookNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('1', 'pro', '978-7-121-15213-19-98', '10000002', '100', '1', '计算机图形学', '2012-09-16 ', '广西大学出版社');
INSERT INTO `book` VALUES ('3', 'pro', '97823-7-121-15213-19 ', '10000003', '34', '1', 'ZigBee开发3', '2012-09-16 ', '广西大学出版社');
INSERT INTO `book` VALUES ('4', 'pto', '978-7-121-15213-1932', '10000004', '55', '0', 'php实战', '2012-09-16 ', '广西大学出版社');
INSERT INTO `book` VALUES ('5', 'jack', '978-7-1221-15213-1932', '10000005', '34', '1', 'Mybites', '2012-09-16 ', '广西大学出版社');
INSERT INTO `book` VALUES ('8', 'jack', '978-376-1-15213-1932', '1000000110', '34', '1', 'Pro SPring3.x', '2012-09-16 ', '华中科技大学出版社');
INSERT INTO `book` VALUES ('9', 'jack', '978-7-121-1523213-1932', '100000090', '34', '0', 'Tkink IN Java', '2012-09-17 ', '广西大学出版社');
INSERT INTO `book` VALUES ('13', 'as', '978-71-121-15213-1932', '12312', '34', '1', '汇编', '2012-09-11', '清华大学出版社');
INSERT INTO `book` VALUES ('14', '请问', '978-7-11221-15213-1932', '1223', '36', '1', '计算机网络', '2012-09-15', '浙江大学出版社');
INSERT INTO `book` VALUES ('15', '阿萨德', '978-7-121-152213-1932', '231312', '36', '1', '操作系统', '2012-09-11', '北京大学出版社');
INSERT INTO `book` VALUES ('16', 'think', '978-7-1234-15213-1932', '122321', '68', '2', 'JAVA', '2012-09-11', '北京大学出版社');
INSERT INTO `book` VALUES ('17', '陈雄华', '978-7-121-11252013-9', '2012092300001', '90', '0', 'Spring应用实战', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('18', 'pro', '978-7-121-1520913-10', '2012092300002', '80', '0', 'EJB3.0  In Action', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('19', '飘', '100-101-191980-1', '2012092300004', '20', '0', '飘', '2012-09-01', '电子科技大学出版社');
INSERT INTO `book` VALUES ('20', 'pro', '978-7-121-15213-19', '2012092300005', '80', '0', '太极', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('21', 'pro', '111-12121-121-2', '100001000', '89', '1', 'Hibernate3.0', '2012-09-24', '电子工业出版社');
INSERT INTO `book` VALUES ('22', 'pro', '1-1212-12-12-12', '101', '40', '0', 'struts2', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('23', 'pro', '1-1-1-11-1-1', '2012092601', '35', '0', '.net 程序设计', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('24', 'pro', '1-1-1-11-1-1-0', '2012092602', '35', '0', 'ASP程序设计', '2012-09-01', '电子工业出版社');
INSERT INTO `book` VALUES ('25', 'pro', '1-1-1-9-1-1-0', '2012092603', '35', '0', 'C#程序设计', '2012-09-01', '电子工业出版社');

-- ----------------------------
-- Table structure for borrow
-- ----------------------------
DROP TABLE IF EXISTS `borrow`;
CREATE TABLE `borrow` (
  `id` int(11) NOT NULL auto_increment,
  `deal` int(11) default NULL,
  `endTime` varchar(50) default NULL,
  `renew` int(11) default NULL,
  `startTime` varchar(50) default NULL,
  `book_id` int(11) default NULL,
  `student_id` int(11) default NULL,
  PRIMARY KEY  (`id`),
  KEY `FKAD8CA9F549DAB36C` (`book_id`),
  KEY `FKAD8CA9F53358F448` (`student_id`),
  CONSTRAINT `FKAD8CA9F53358F448` FOREIGN KEY (`student_id`) REFERENCES `studnet` (`id`),
  CONSTRAINT `FKAD8CA9F549DAB36C` FOREIGN KEY (`book_id`) REFERENCES `book` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of borrow
-- ----------------------------
INSERT INTO `borrow` VALUES ('2', '0', '2012-10-10', '1', '2012-09-16 ', '3', '1');
INSERT INTO `borrow` VALUES ('6', '0', '2012-09-16 ', '0', '2012-09-16 ', '3', '3');
INSERT INTO `borrow` VALUES ('7', '2', '2012-09-22', '0', '2012-09-22', '16', '9');
INSERT INTO `borrow` VALUES ('8', '0', '2012-09-22 ', '0', '2012-09-22 ', '13', '10');
INSERT INTO `borrow` VALUES ('10', '0', '2012-10-07', '0', '2012-09-22 ', '15', '1');
INSERT INTO `borrow` VALUES ('11', '1', '2012-09-23', '0', '2012-09-22 ', '9', '1');
INSERT INTO `borrow` VALUES ('12', '0', '2012-10-07', '1', '2012-09-22', '14', '1');
INSERT INTO `borrow` VALUES ('15', '0', '2012-10-17', '1', '2012-09-22', '8', '1');
INSERT INTO `borrow` VALUES ('16', '0', '2012-10-16', '1', '2012-09-22', '3', '1');
INSERT INTO `borrow` VALUES ('17', '0', '2012-10-16', '1', '2012-09-22', '1', '1');
INSERT INTO `borrow` VALUES ('18', '0', '2012-10-08', '0', '2012-09-23', '3', '3');
INSERT INTO `borrow` VALUES ('19', '0', '2012-10-08', '0', '2012-09-23', '3', '3');
INSERT INTO `borrow` VALUES ('21', '0', '2012-10-07', '1', '2012-09-23 ', '9', '1');
INSERT INTO `borrow` VALUES ('24', '1', '2012-09-23', '0', '2012-09-01', '19', '23');
INSERT INTO `borrow` VALUES ('26', '0', '2012-10-09', '0', '2012-09-24', '22', '23');
INSERT INTO `borrow` VALUES ('27', '0', '2012-10-09', '0', '2012-09-24', '21', '9');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL auto_increment,
  `level` int(11) default NULL,
  `managerID` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `managerID` (`managerID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('6', '1', 'admin', '123456');
INSERT INTO `manager` VALUES ('10', '0', 'admin2', '123456');
INSERT INTO `manager` VALUES ('18', '1', 'manager', '123456');
INSERT INTO `manager` VALUES ('22', '0', 'qweqwe', '123132');
INSERT INTO `manager` VALUES ('23', '0', 'wqqe', '123456');
INSERT INTO `manager` VALUES ('25', '0', '123456', '123456');
INSERT INTO `manager` VALUES ('26', '0', 'manager2', '123456');
INSERT INTO `manager` VALUES ('28', '0', 'asa', '123456');
INSERT INTO `manager` VALUES ('31', '0', 'werwe', 'werwe');
INSERT INTO `manager` VALUES ('32', '0', 'werwwe', '1231');
INSERT INTO `manager` VALUES ('33', '0', 'wsdfs', '1231');
INSERT INTO `manager` VALUES ('34', '0', 'weuu', '123');
INSERT INTO `manager` VALUES ('37', '0', '1231', 'weweweqw');
INSERT INTO `manager` VALUES ('38', '0', 'eriou', '123131');
INSERT INTO `manager` VALUES ('39', '0', 'retret', '3453');
INSERT INTO `manager` VALUES ('40', '0', 'u1231', '1231');
INSERT INTO `manager` VALUES ('41', '0', '2313', '1231231');
INSERT INTO `manager` VALUES ('42', '0', 'ewr3u', '2342');
INSERT INTO `manager` VALUES ('45', '0', 'qwqe', '1231231');
INSERT INTO `manager` VALUES ('46', '0', 'asdas', 'sa');
INSERT INTO `manager` VALUES ('47', '0', 'asdasa', 'asa');
INSERT INTO `manager` VALUES ('50', '0', 'asasas', 'asas');
INSERT INTO `manager` VALUES ('51', '0', 'asas', 'asas');
INSERT INTO `manager` VALUES ('53', '0', 'asaw', 'q211');
INSERT INTO `manager` VALUES ('55', '0', 'aasa', 'asa');
INSERT INTO `manager` VALUES ('57', '0', 'fsdf', 'a');
INSERT INTO `manager` VALUES ('58', '0', 'admin5', '123456');
INSERT INTO `manager` VALUES ('59', '0', 'admin7', '123456');
INSERT INTO `manager` VALUES ('60', '0', 'admin9', '123456');
INSERT INTO `manager` VALUES ('61', '0', 'admin8', '123456');
INSERT INTO `manager` VALUES ('62', '0', 'aasdasasd', 'asdsad');
INSERT INTO `manager` VALUES ('63', '0', 'manager3', '123456');
INSERT INTO `manager` VALUES ('64', '0', 'manager4', '123456');
INSERT INTO `manager` VALUES ('65', '0', 'manager5', '123456');
INSERT INTO `manager` VALUES ('66', '0', '哈哈加密了', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `manager` VALUES ('67', '0', '看不懂了吧', 'E99A18C428CB38D5F260853678922E03');
INSERT INTO `manager` VALUES ('68', '0', '没用的', 'E10ADC3949BA59ABBE56E057F20F883E ');
INSERT INTO `manager` VALUES ('69', '1', '用的是MD5哦', 'E10ADC3949BA59ABBE56E057F20F883E');
INSERT INTO `manager` VALUES ('70', '0', 'yy', 'E10ADC3949BA59ABBE56E057F20F883E');

-- ----------------------------
-- Table structure for studnet
-- ----------------------------
DROP TABLE IF EXISTS `studnet`;
CREATE TABLE `studnet` (
  `id` int(11) NOT NULL auto_increment,
  `department` varchar(50) default NULL,
  `password` varchar(50) NOT NULL,
  `permitted` int(11) NOT NULL default '1',
  `age` int(11) default NULL,
  `class` varchar(50) default NULL,
  `studentName` varchar(50) default NULL,
  `studentNumber` varchar(50) NOT NULL,
  `sex` varchar(2) default NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `studentNumber` (`studentNumber`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of studnet
-- ----------------------------
INSERT INTO `studnet` VALUES ('1', '计算机系', '123456', '1', '22', '计算机科学与技术101班', '刘德华', '10073002010', '男');
INSERT INTO `studnet` VALUES ('3', '计算机系', '1007300251 ', '1', '21', '计算机科学与技术101班', '张羽', '1007300251', '男');
INSERT INTO `studnet` VALUES ('9', '计算机系', '1007300202', '1', '21', '计算机科学与技术101班', '曹备', '1007300209', '男');
INSERT INTO `studnet` VALUES ('10', '计算机系', '1007300202', '1', '21', '计算机科学与技术101班', '李华藤', '100730020220', '男');
INSERT INTO `studnet` VALUES ('11', '计算机科学系', '1007300202', '1', '21', '计算机科学与技术101班 ', '诸葛瑜', '100730020230', '男');
INSERT INTO `studnet` VALUES ('12', '计算机科学系', '1007300202', '1', '21', '计算机科学与技术101班 ', '西门修', '100730020233', '男');
INSERT INTO `studnet` VALUES ('14', '计算机科学系', '1007300202', '1', '21', '计算机科学与技术101班 ', '黄名', '10073003', '男');
INSERT INTO `studnet` VALUES ('16', '计算机科学系', '123456', '0', '22', '计算机科学与技术101班 ', '华山松', '1222322', '女');
INSERT INTO `studnet` VALUES ('18', '计算机科学系', '121212', '1', '23', '计算机科学与技术102班 ', '阿斯达', '1231232', '男');
INSERT INTO `studnet` VALUES ('19', '计算机科学系', '123456', '1', '21', '计算机科学与技术102班 ', '阿卡佳', '10072022022', '女');
INSERT INTO `studnet` VALUES ('20', '计算机科学系', '10073002101', '1', '18', '计算机科学与技术102班 ', '刘丽', '10073002101', '女');
INSERT INTO `studnet` VALUES ('21', '计算机系', '10073002102', '1', '21', '计科102班', '英雄', '10073002102', '男');
INSERT INTO `studnet` VALUES ('22', '计算机系', '10073002103', '1', '19', '计科102班', '猴酒戒', '10073002103', '男');
INSERT INTO `studnet` VALUES ('23', '艺术系', '10073002104', '1', '21', '艺术101班', '刘亦非', '10073002104', '女');
INSERT INTO `studnet` VALUES ('24', '计算机科学系', '123456', '1', '21', '计算机科学与技术102班', '学生', '10000', '男');
INSERT INTO `studnet` VALUES ('25', '计算机系', '10086', '1', '21', '计算机科学与技术102班', '中国移动', '10086', '男');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL auto_increment,
  `pwd` varchar(50) default NULL,
  `uame` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uame` (`uame`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '21211', '理论');
INSERT INTO `user` VALUES ('2', '21211', '理hh ');
