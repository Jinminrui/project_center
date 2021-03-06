-- MySQL dump 10.13  Distrib 8.0.19, for macos10.15 (x86_64)
--
-- Host: localhost    Database: project_center
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `activity`
--

DROP TABLE IF EXISTS `activity`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `activity` (
  `pk_id` varchar(35) NOT NULL,
  `title` varchar(256) NOT NULL,
  `creator_id` varchar(35) NOT NULL,
  `content` varchar(1000) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `action` varchar(100) NOT NULL,
  `team_id` varchar(35) NOT NULL,
  `project_id` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='项目动态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `activity`
--

LOCK TABLES `activity` WRITE;
/*!40000 ALTER TABLE `activity` DISABLE KEYS */;
INSERT INTO `activity` VALUES ('038ee545cc2443408939894f50c4d069','金敏睿 创建项目 毕业设计','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-03-21 12:43:35','2020-03-21 12:43:35','create-project','b907f9ec7c6146d49587ca98f9a60a90','e75c488d9ac54e6bb837681502c09025'),('31f72b43509e4229951e1c4e3bd74d16','杨志云 新建项目 杨志云的毕业设计','8d68826010d54b418fa4192571135bd6',NULL,'2020-04-18 15:05:05','2020-04-18 15:05:05','create-project','b907f9ec7c6146d49587ca98f9a60a90','e8e69b7a046b41c1833e5053ba2c5d0e'),('360d4a1f06294d7f8087bb8273095127','金敏睿 新建项目  测试项目5','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-04-18 11:40:44','2020-04-18 11:40:44','create-project','b907f9ec7c6146d49587ca98f9a60a90','d4b2c52343e849ea841c36d415de6be7'),('4ba96743e9324e42b649bcac9799d03e','金敏睿 删除项目  测试项目5','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-04-18 13:56:09','2020-04-18 13:56:09','delete-project','b907f9ec7c6146d49587ca98f9a60a90','d4b2c52343e849ea841c36d415de6be7'),('93c9876361524bd2a8b560c5eaf74c34','金敏睿 新建项目 杨志云的毕业设计','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-04-18 16:18:52','2020-04-18 16:18:52','create-project','b907f9ec7c6146d49587ca98f9a60a90','a81e65e5a5dc42348bba181545b1fdf6'),('baebdf47c30740f5ad04b06e9f31776a','金敏睿 删除项目 杨志云的毕业设计','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-04-18 16:21:07','2020-04-18 16:21:07','delete-project','b907f9ec7c6146d49587ca98f9a60a90','a81e65e5a5dc42348bba181545b1fdf6'),('c40fb442fc824287a6974fbf5744a57e','金敏睿 创建任务 在线富文本编辑和多维度的查询功能','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-03-30 14:34:42','2020-03-30 14:34:42','create-task','b907f9ec7c6146d49587ca98f9a60a90','e75c488d9ac54e6bb837681502c09025'),('c857bf71d6314597a045aaa2567ba369','金敏睿 在项目 毕业设计 创建迭代 Sprint 003','efa5a42313ca4f3b9570e9a1c95871bd',NULL,'2020-05-14 17:06:26','2020-05-14 17:06:26','create-sprint','b907f9ec7c6146d49587ca98f9a60a90','e75c488d9ac54e6bb837681502c09025');
/*!40000 ALTER TABLE `activity` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project`
--

DROP TABLE IF EXISTS `project`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `project` (
  `pk_id` varchar(35) NOT NULL,
  `id` varchar(10) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(140) DEFAULT NULL,
  `cover` varchar(500) DEFAULT 'http://oss.jinminrui.cn/defaultCover.png',
  `group_id` varchar(45) DEFAULT NULL,
  `status` int DEFAULT NULL COMMENT '项目状态\n1-进度正常\n2-存在风险\n3-进度失控',
  `team_id` varchar(35) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pk_id`),
  UNIQUE KEY `pk_id_UNIQUE` (`pk_id`),
  UNIQUE KEY `id_UNIQUE` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project`
--

LOCK TABLES `project` WRITE;
/*!40000 ALTER TABLE `project` DISABLE KEYS */;
INSERT INTO `project` VALUES ('46602ae7bcf2465a9a6c4410b8e1dc76','CS2','测试项目2','测试2测试2测试2测试2测试2测试2','http://oss.jinminrui.cn/defaultCover.png',NULL,1,'b907f9ec7c6146d49587ca98f9a60a90','2020-03-22 21:29:53','2020-03-22 21:29:53'),('a4c13c06d8ec47bda62b0bfbba67bc92','CS4','测试项目4','测试项目4测试项目4测试项目4测试项目4','http://oss.jinminrui.cn/defaultCover.png',NULL,1,'b907f9ec7c6146d49587ca98f9a60a90','2020-04-18 11:33:52','2020-04-18 11:33:52'),('e75c488d9ac54e6bb837681502c09025','BYSJ','毕业设计','面向敏捷开发的高效团队协作平台','http://oss.jinminrui.cn/defaultCover.png',NULL,1,'b907f9ec7c6146d49587ca98f9a60a90','2020-03-21 12:43:35','2020-03-21 12:43:35'),('f3321099d7354055b24631e5400c6581','CS3','测试项目3','测试项目3测试项目3测试项目3测试项目3','http://oss.jinminrui.cn/defaultCover.png',NULL,1,'b907f9ec7c6146d49587ca98f9a60a90','2020-04-16 17:20:43','2020-04-16 17:20:43');
/*!40000 ALTER TABLE `project` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sprint`
--

DROP TABLE IF EXISTS `sprint`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sprint` (
  `pk_id` varchar(35) NOT NULL,
  `project_id` varchar(35) NOT NULL COMMENT '所属项目',
  `title` varchar(100) DEFAULT NULL COMMENT '标题',
  `status` int NOT NULL DEFAULT '1' COMMENT '1-待开始、2-已开始、3-已结束',
  `director` varchar(35) DEFAULT 'none' COMMENT '负责人',
  `start_time` date DEFAULT NULL COMMENT '开始日期',
  `end_time` date DEFAULT NULL COMMENT '截止日期',
  `description` varchar(256) DEFAULT NULL COMMENT '描述',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator_id` varchar(35) NOT NULL,
  `team_id` varchar(35) NOT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='迭代表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sprint`
--

LOCK TABLES `sprint` WRITE;
/*!40000 ALTER TABLE `sprint` DISABLE KEYS */;
INSERT INTO `sprint` VALUES ('1ce060786c6a471381db07c50d29d81c','e75c488d9ac54e6bb837681502c09025','Sprint 002',1,'none','2020-04-08','2020-05-25',NULL,'2020-04-07 20:15:51','2020-04-07 20:15:51','efa5a42313ca4f3b9570e9a1c95871bd','b907f9ec7c6146d49587ca98f9a60a90'),('418bf2de5cb844a4a7e59a4e9b1a3bc4','e75c488d9ac54e6bb837681502c09025','Sprint 001',3,'efa5a42313ca4f3b9570e9a1c95871bd','2020-04-07','2020-04-20',NULL,'2020-04-07 18:04:37','2020-04-09 19:50:43','efa5a42313ca4f3b9570e9a1c95871bd','b907f9ec7c6146d49587ca98f9a60a90'),('5d8dc72ca8a0476d8e3bf1706c0fbd12','e75c488d9ac54e6bb837681502c09025','Sprint 003',1,'none','2020-05-14','2020-05-21',NULL,'2020-05-14 17:06:26','2020-05-14 17:06:26','efa5a42313ca4f3b9570e9a1c95871bd','b907f9ec7c6146d49587ca98f9a60a90');
/*!40000 ALTER TABLE `sprint` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task`
--

DROP TABLE IF EXISTS `task`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task` (
  `pk_Id` varchar(35) NOT NULL,
  `title` varchar(100) NOT NULL,
  `note` varchar(500) DEFAULT NULL COMMENT '备注',
  `type` int NOT NULL COMMENT '任务类型：1-需求 2-缺陷',
  `stage` varchar(45) DEFAULT NULL COMMENT '任务所在阶段',
  `start_time` date DEFAULT NULL COMMENT '开始时间',
  `end_time` date DEFAULT NULL COMMENT '截止时间',
  `project_id` varchar(35) DEFAULT NULL COMMENT '所属项目',
  `executor` varchar(35) DEFAULT 'none' COMMENT '执行者id',
  `story_points` int DEFAULT NULL,
  `priority` int DEFAULT '1' COMMENT '优先级\n1-普通\n2-紧急\n3-非常紧急',
  `sprint` varchar(35) DEFAULT 'default' COMMENT '该任务所属的迭代周期id',
  `task_class` varchar(35) DEFAULT 'default' COMMENT '需求分类和缺陷分类',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `creator_id` varchar(35) NOT NULL,
  `finish_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pk_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task`
--

LOCK TABLES `task` WRITE;
/*!40000 ALTER TABLE `task` DISABLE KEYS */;
INSERT INTO `task` VALUES ('17539d7784af4f9d8701b6e4cd58ccbd','发现一个bug',NULL,2,'关闭',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','2fa6becfa9714043be78d64d2f589d12',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-04-02 16:34:28','2020-04-14 10:14:07','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-12 16:43:19'),('2d6ab6df07af4e96b8b685cfbc066126','新bug',NULL,2,'已解决',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','2fa6becfa9714043be78d64d2f589d12',2,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-04-13 17:50:31','2020-04-29 23:30:51','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-29 23:30:51'),('49fb31b1b70141509aca3322943a5c56','这是什么鬼需求！！',NULL,1,'需求制定',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','2fa6becfa9714043be78d64d2f589d12',NULL,1,'5d8dc72ca8a0476d8e3bf1706c0fbd12','e6462c13fa404098bc5bc0e7377568cd','2020-03-22 19:55:00','2020-05-14 17:19:21','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-14 11:50:01'),('4ad9dee3053042448fe66f87fca55dc6','需求1','123',1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','e6462c13fa404098bc5bc0e7377568cd','2020-03-22 19:30:22','2020-04-08 10:35:34','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-08 10:35:34'),('5936bcc322cf4dd1b154162e9634a471','需求需求',NULL,1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-03-27 15:51:50','2020-04-09 09:54:48','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-08 10:36:12'),('5edce1b8ec1544008c326e1d47aa6c24','123',NULL,1,'已完成','2020-03-27','2020-04-01','e75c488d9ac54e6bb837681502c09025','8d68826010d54b418fa4192571135bd6',1,2,'418bf2de5cb844a4a7e59a4e9b1a3bc4','798045c7f70d4b95bfe1dd62c9b408fe','2020-03-27 15:06:28','2020-04-14 10:18:19','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-14 10:18:19'),('652dc864167f437cac808f18d5cdbcdd','重新打开一个bug',NULL,2,'已解决','2020-04-01','2020-05-05','e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-04-02 17:03:48','2020-04-14 10:14:01','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-12 22:05:43'),('65694149dde84a968e4f40d3d57a1924','啊哈哈哈哈哈哈哈哈','<p>啊哈哈哈哈哈哈哈哈哈哈哈</p>',1,'需求制定',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','none',0,2,'1ce060786c6a471381db07c50d29d81c','0d5c51903a2c4d9b9aae98ed8e7a2c2c','2020-03-29 14:08:38','2020-03-29 14:08:38','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('65cd4f3bb04e44d6bed3493355d84ed8','需求3',NULL,1,'测试中',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',5,2,'default','default','2020-03-22 19:31:03','2020-04-01 18:41:15','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('709a84db968547258700b2155d576ff4','用户可通过手机号码直接登录或注册账户',NULL,1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',NULL,2,'1ce060786c6a471381db07c50d29d81c','default','2020-03-27 15:38:50','2020-04-08 10:35:58','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-08 10:35:58'),('74a77af112c7470285dffc0d68e43428','需求2',NULL,1,'开发中',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,2,'default','default','2020-03-22 19:30:58','2020-05-10 18:29:09','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('77ef7e50df0343b18a15630fc54c1c1f','啊哈哈','<p>啊哈哈哈啊哈哈哈啊哈哈哈</p>',1,'已完成','2020-03-29','2020-03-30','e75c488d9ac54e6bb837681502c09025','8d68826010d54b418fa4192571135bd6',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','798045c7f70d4b95bfe1dd62c9b408fe','2020-03-29 15:22:21','2020-04-14 10:18:28','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-14 10:18:28'),('7afb02797f8a40a7af9acd768aa452dd','在线富文本编辑和多维度的查询功能','<p><em><u><strong>在线富文本编辑和多维度的查询功能</strong></u></em></p>',1,'开发中','2020-04-01','2020-04-02','e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,2,'default','d277c0f72a494e45a1046699beb29646','2020-03-30 14:34:42','2020-04-01 17:06:38','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('8f0ec24ed53a42abbdeabb149ecad3a9','需求6',NULL,1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','8d68826010d54b418fa4192571135bd6',1,3,'418bf2de5cb844a4a7e59a4e9b1a3bc4','e6462c13fa404098bc5bc0e7377568cd','2020-03-22 20:39:21','2020-04-29 23:30:43','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-29 23:30:43'),('a37559593a0b4596b1b48660bc81dd99','123123','<p>123</p>',1,'需求制定',NULL,NULL,'46602ae7bcf2465a9a6c4410b8e1dc76','none',NULL,1,'default','default','2020-03-30 15:49:06','2020-03-30 15:49:06','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('a519630174bf4245b0e1d3df96bf958c','又发现一个bug','<ol><li>复现步骤</li><li>测试结果</li><li>期望结果</li></ol>',2,'已解决','2020-04-01','2020-05-24','e75c488d9ac54e6bb837681502c09025','efa5a42313ca4f3b9570e9a1c95871bd',1,2,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-04-02 16:59:29','2020-04-12 15:39:39','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-08 16:42:16'),('a705ca2a7efe4703bef3223d69e83935','需求5',NULL,1,'开发中',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','none',NULL,3,'5d8dc72ca8a0476d8e3bf1706c0fbd12','e6462c13fa404098bc5bc0e7377568cd','2020-03-22 19:55:04','2020-05-14 17:18:59','efa5a42313ca4f3b9570e9a1c95871bd',NULL),('a7c2519e1be7469c96546e5d53208043','12345',NULL,1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','8d68826010d54b418fa4192571135bd6',3,1,'1ce060786c6a471381db07c50d29d81c','default','2020-03-27 15:39:09','2020-05-14 15:37:12','efa5a42313ca4f3b9570e9a1c95871bd','2020-05-14 15:37:12'),('c5f0c1eb4cb44d789a03716795202496','哈哈哈哈哈哈','<p>哈哈哈哈哈</p>',1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','8d68826010d54b418fa4192571135bd6',NULL,1,'1ce060786c6a471381db07c50d29d81c','default','2020-03-29 17:09:46','2020-05-14 15:38:07','efa5a42313ca4f3b9570e9a1c95871bd','2020-05-14 15:38:07'),('c8e5c9e13ade44b18777913d8fd4101c','哈哈哈哈哈哈hahahaha','123',1,'已完成',NULL,NULL,'e75c488d9ac54e6bb837681502c09025','71022b4908a54243b4ac352003b06950',1,1,'418bf2de5cb844a4a7e59a4e9b1a3bc4','default','2020-03-27 15:53:15','2020-04-14 11:50:19','efa5a42313ca4f3b9570e9a1c95871bd','2020-04-14 11:50:19');
/*!40000 ALTER TABLE `task` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `task_class`
--

DROP TABLE IF EXISTS `task_class`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `task_class` (
  `pk_id` varchar(35) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '1-需求 2-缺陷',
  `project_id` varchar(35) DEFAULT NULL COMMENT '所属项目id',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务类别表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `task_class`
--

LOCK TABLES `task_class` WRITE;
/*!40000 ALTER TABLE `task_class` DISABLE KEYS */;
INSERT INTO `task_class` VALUES ('0d5c51903a2c4d9b9aae98ed8e7a2c2c','团队相关模块',NULL,1,'e75c488d9ac54e6bb837681502c09025','2020-03-25 21:02:56','2020-03-25 21:02:56'),('2bc81e75af1c4be291bb97759ea47f63','Bug分类2',NULL,2,'e75c488d9ac54e6bb837681502c09025','2020-04-02 17:40:04','2020-04-02 17:40:04'),('798045c7f70d4b95bfe1dd62c9b408fe','项目相关模块',NULL,1,'e75c488d9ac54e6bb837681502c09025','2020-03-29 15:24:58','2020-03-29 15:24:58'),('d277c0f72a494e45a1046699beb29646','文档相关模块',NULL,1,'e75c488d9ac54e6bb837681502c09025','2020-03-29 17:46:34','2020-03-29 17:46:34'),('e6462c13fa404098bc5bc0e7377568cd','用户相关模块','',1,'e75c488d9ac54e6bb837681502c09025','2020-03-22 18:40:08','2020-03-22 18:40:08'),('ec9a45f737864f7daea601168418d3fb','Bug分类',NULL,2,'e75c488d9ac54e6bb837681502c09025','2020-04-02 17:39:53','2020-04-02 17:39:53');
/*!40000 ALTER TABLE `task_class` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_project_relation`
--

DROP TABLE IF EXISTS `user_project_relation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_project_relation` (
  `pk_id` varchar(35) NOT NULL,
  `user_id` varchar(35) NOT NULL,
  `project_id` varchar(45) NOT NULL,
  `role` int NOT NULL COMMENT '用户和项目的对应关系\n1-创建者\n2-管理者\n3-参与者',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`pk_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_project_relation`
--

LOCK TABLES `user_project_relation` WRITE;
/*!40000 ALTER TABLE `user_project_relation` DISABLE KEYS */;
INSERT INTO `user_project_relation` VALUES ('196652621306464aaa4f5f88321d7753','efa5a42313ca4f3b9570e9a1c95871bd','a4c13c06d8ec47bda62b0bfbba67bc92',1,'2020-04-18 11:33:52','2020-04-18 11:33:52'),('3faa64a104b445b188de3ba1f9a52230','efa5a42313ca4f3b9570e9a1c95871bd','f3321099d7354055b24631e5400c6581',1,'2020-04-16 17:20:43','2020-04-16 17:20:43'),('4d9d5460d645461ea9756652f92b0a65','8d68826010d54b418fa4192571135bd6','e75c488d9ac54e6bb837681502c09025',2,'2020-03-31 17:17:51','2020-03-31 17:17:51'),('529a81131d7843b2ad4dfa59d9ea36c5','efa5a42313ca4f3b9570e9a1c95871bd','e75c488d9ac54e6bb837681502c09025',1,'2020-03-21 12:43:35','2020-03-21 12:43:35'),('5c241006810e4813ae71fc3a316e3420','71022b4908a54243b4ac352003b06950','e75c488d9ac54e6bb837681502c09025',2,'2020-04-01 14:09:49','2020-04-01 14:09:49'),('c488375767694aa18f7dbcda902ac9ce','2fa6becfa9714043be78d64d2f589d12','e75c488d9ac54e6bb837681502c09025',2,'2020-04-14 10:13:39','2020-04-14 10:13:39'),('f92c215a071e496c9ab72cb2fcc3ac00','efa5a42313ca4f3b9570e9a1c95871bd','46602ae7bcf2465a9a6c4410b8e1dc76',1,'2020-03-22 21:29:53','2020-03-22 21:29:53');
/*!40000 ALTER TABLE `user_project_relation` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-14 19:17:27
