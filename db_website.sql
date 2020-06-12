/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.29 : Database - db_website
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`db_website` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `db_website`;

/*Table structure for table `chapter` */

DROP TABLE IF EXISTS `chapter`;

CREATE TABLE `chapter` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `chapter_name` varchar(256) DEFAULT NULL COMMENT '章节名',
  `parent_id` int(8) DEFAULT '0' COMMENT '父结点',
  `video_id` int(8) DEFAULT '0' COMMENT '视频id 为0则表示非叶子节点',
  `sequence` int(8) DEFAULT '1' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

/*Data for the table `chapter` */

insert  into `chapter`(`id`,`chapter_name`,`parent_id`,`video_id`,`sequence`) values (1,'毛泽东思想和中国特色社会主义理论体系概论',0,0,1),(2,'马克思主义基本原理概论',0,0,2),(5,'马克思主义中国化的历史进程和伟大成果',1,0,1),(61,'马克思主义中国化及其发展',5,12,1),(62,'导论',2,45,1),(63,'毛泽东思想',5,14,2),(64,'中国特色社会主义理论体系',5,13,3),(65,'思想路线与理论精髓',5,15,4),(66,'新民主主义革命理论',1,0,2),(67,'新民主主义革命理论形成的依据',66,16,1),(68,'新民主主义革命的总路线和基本纲领',66,17,2),(69,'新民主主义革命的道路和基本经验',66,18,3),(70,'社会主义改造理论',1,0,3),(71,'从新民主主义到社会主义的转变',70,19,1),(72,'社会主义改造道路和历史经验',70,20,2),(73,'社会主义制度在中国的确立',70,21,3),(74,'社会主义建设道路初步探索的理论成果',1,0,4),(75,'社会主义建设道路初步探索的重要思想成果',74,22,1),(76,'社会主义建设道路初步探索的意义和经验教训',74,23,2),(77,'建设中国特色社会主义总依据',1,0,5),(78,'社会主义初级阶段理论',77,24,1),(81,'社会主义本质和建设中国特色社会主义总任务',1,0,6),(82,'社会主义的本质',81,25,1),(83,'社会主义的根本任务',81,26,2),(84,'社会主义改革开放理论',1,0,7),(85,'改革开放是决定当代中国命运的关键抉择',84,27,1),(86,'全面深化改革',84,28,2),(87,'扩大对外开放',84,29,3),(88,'建设中国特色社会主义总布局',1,0,8),(89,'建设社会主义市场经济',88,30,1),(90,'建设中国特色社会主义政治',88,31,2),(91,'建设中国特色社会主义文化',88,32,3),(92,'建设社会主义和谐社会',88,33,4),(93,'建设社会主义生态文明',88,34,5),(94,'实现祖国完全统一理论',1,0,9),(95,'实现祖国完全统一是中华民族的根本利益',94,35,1),(96,'中国特色社会主义外交和国际战略',1,0,10),(97,'外交和国际战略理论的形成依据',96,36,1),(98,'坚持走和平发展道路',96,37,2),(99,'建设中国特色社会主义的根本目的和依靠力量理论',1,0,11),(100,'建设中国特色社会主义的根本目的',99,38,1),(101,'建设中国特色社会主义的依靠力量',99,39,2),(102,'巩固和发展爱国统一战线',99,40,3),(103,'建设巩固国防和强大军队',99,41,4),(104,'建设中国特色社会主义的领导核心',1,0,12),(105,'党的领导是社会主义现代化建设的根本保证',104,42,1),(106,'全面提高党的建设科学化水平',104,43,2),(107,'全面从严治党',104,44,3),(108,'马克思主义的创立与发展',62,49,1),(109,'马克思主义的鲜明特征',62,0,2),(110,'马克思主义的当代价值',62,55,3),(111,'自觉学习和运用马克思主义',62,0,4),(112,'马克思主义的创立与发展1',108,46,1),(113,'马克思主义的创立与发展2',108,47,2),(114,'马克思主义的创立与发展3',108,48,3),(116,'马克思主义的鲜明特征',109,50,1),(117,'共产党宣言',109,51,2),(118,'马克思主义的创立与发展4',108,52,4),(127,'中国近现代史纲要',0,0,3),(128,'引言',127,0,1),(129,'学习《中国近现代史纲要》的现实价值与意义',128,53,1),(130,'鸦片战争前的中国与世界',128,54,2);

/*Table structure for table `chapter_closure` */

DROP TABLE IF EXISTS `chapter_closure`;

CREATE TABLE `chapter_closure` (
  `ancestor` int(8) NOT NULL COMMENT '祖先：上级节点的 id',
  `descendant` int(8) NOT NULL COMMENT '子代：下级节点的 id',
  `distance` int(8) NOT NULL COMMENT '距离：子代到祖先中间隔了几级',
  PRIMARY KEY (`ancestor`,`descendant`,`distance`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `chapter_closure` */

insert  into `chapter_closure`(`ancestor`,`descendant`,`distance`) values (1,1,0),(1,5,1),(1,61,2),(1,63,2),(1,64,2),(1,65,2),(1,66,1),(1,67,2),(1,68,2),(1,69,2),(1,70,1),(1,71,2),(1,72,2),(1,73,2),(1,74,1),(1,75,2),(1,76,2),(1,77,1),(1,78,2),(1,81,1),(1,82,2),(1,83,2),(1,84,1),(1,85,2),(1,86,2),(1,87,2),(1,88,1),(1,89,2),(1,90,2),(1,91,2),(1,92,2),(1,93,2),(1,94,1),(1,95,2),(1,96,1),(1,97,2),(1,98,2),(1,99,1),(1,100,2),(1,101,2),(1,102,2),(1,103,2),(1,104,1),(1,105,2),(1,106,2),(1,107,2),(2,2,0),(2,62,1),(2,108,2),(2,109,2),(2,110,2),(2,111,2),(2,112,3),(2,113,3),(2,114,3),(2,116,3),(2,117,3),(2,118,3),(5,5,0),(5,61,1),(5,63,1),(5,64,1),(5,65,1),(61,61,0),(62,62,0),(62,108,1),(62,109,1),(62,110,1),(62,111,1),(62,112,2),(62,113,2),(62,114,2),(62,116,2),(62,117,2),(62,118,2),(63,63,0),(64,64,0),(65,65,0),(66,66,0),(66,67,1),(66,68,1),(66,69,1),(67,67,0),(68,68,0),(69,69,0),(70,70,0),(70,71,1),(70,72,1),(70,73,1),(71,71,0),(72,72,0),(73,73,0),(74,74,0),(74,75,1),(74,76,1),(75,75,0),(76,76,0),(77,77,0),(77,78,1),(78,78,0),(81,81,0),(81,82,1),(81,83,1),(82,82,0),(83,83,0),(84,84,0),(84,85,1),(84,86,1),(84,87,1),(85,85,0),(86,86,0),(87,87,0),(88,88,0),(88,89,1),(88,90,1),(88,91,1),(88,92,1),(88,93,1),(89,89,0),(90,90,0),(91,91,0),(92,92,0),(93,93,0),(94,94,0),(94,95,1),(95,95,0),(96,96,0),(96,97,1),(96,98,1),(97,97,0),(98,98,0),(99,99,0),(99,100,1),(99,101,1),(99,102,1),(99,103,1),(100,100,0),(101,101,0),(102,102,0),(103,103,0),(104,104,0),(104,105,1),(104,106,1),(104,107,1),(105,105,0),(106,106,0),(107,107,0),(108,108,0),(108,112,1),(108,113,1),(108,114,1),(108,118,1),(109,109,0),(109,116,1),(109,117,1),(110,110,0),(111,111,0),(112,112,0),(113,113,0),(114,114,0),(116,116,0),(117,117,0),(118,118,0),(127,127,0),(127,128,1),(127,129,2),(127,130,2),(128,128,0),(128,129,1),(128,130,1),(129,129,0),(130,130,0);

/*Table structure for table `course` */

DROP TABLE IF EXISTS `course`;

CREATE TABLE `course` (
  `id` int(8) NOT NULL AUTO_INCREMENT COMMENT '课程id',
  `name` varchar(256) DEFAULT NULL COMMENT '课程名称',
  `first_class_id` int(8) DEFAULT NULL COMMENT '课程第一个小节的id（指针）',
  `teacher_name` varchar(64) DEFAULT NULL COMMENT '授课教师名字',
  `picture_url` varchar(256) DEFAULT NULL COMMENT '封面url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `first_class_id` (`first_class_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `course` */

insert  into `course`(`id`,`name`,`first_class_id`,`teacher_name`,`picture_url`) values (1,'毛泽东思想和中国特色社会主义理论体系概论',1,'丁俊萍','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/poster/mao.jpg'),(2,'马克思主义基本原理概论',2,'孟锐峰','D://WebsiteResources/course/2_马克思主义基本原理概论/poster/marx.jpg'),(7,'中国近现代史纲要',127,'宋','D://WebsiteResources/course/3_中国近现代史纲要/poster/history.jpg');

/*Table structure for table `file` */

DROP TABLE IF EXISTS `file`;

CREATE TABLE `file` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `size` bigint(64) DEFAULT NULL COMMENT '文件大小',
  `upload_date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '上传时间',
  `chapter_id` int(32) DEFAULT NULL COMMENT '对应章节id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=utf8;

/*Data for the table `file` */

insert  into `file`(`id`,`name`,`url`,`size`,`upload_date`,`chapter_id`) values (17,'(1.1.3)--第一章马克思主义中国化两大理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/1_马克思主义中国化及其发展/(1.1.3)--第一章马克思主义中国化两大理论成果.pdf',4064591,'2020-05-15 21:00:11',61),(18,'(1.1.4)--第一章马克思主义中国化两大理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/1_马克思主义中国化及其发展/(1.1.4)--第一章马克思主义中国化两大理论成果.pdf',103921,'2020-05-15 21:00:15',61),(19,'(1.1.5)--第一章马克思主义中国化两大理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/1_马克思主义中国化及其发展/(1.1.5)--第一章马克思主义中国化两大理论成果.pdf',273415,'2020-05-15 21:00:18',61),(20,'(2.1.1)--第二章新民主主义革命理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/(2.1.1)--第二章新民主主义革命理论.pdf',166249,'2020-05-15 21:01:37',67),(21,'(2.1.2)--第二章新民主主义革命理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/(2.1.2)--第二章新民主主义革命理论.pdf',346107,'2020-05-15 21:01:40',67),(22,'(2.1.3)--第二章新民主主义革命理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/(2.1.3)--第二章新民主主义革命理论.pdf',78179,'2020-05-15 21:01:42',67),(23,'(2.1.4)--第二章新民主主义革命理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/(2.1.4)--第二章新民主主义革命理论.pdf',4732221,'2020-05-15 21:01:44',67),(24,'(2.1.5)--第二章新民主主义革命理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/(2.1.5)--第二章新民主主义革命理论.pdf',270993,'2020-05-15 21:01:46',67),(25,'(3.1.1)--第三章社会主义改造理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/(3.1.1)--第三章社会主义改造理论.pdf',126929,'2020-05-15 21:02:09',71),(26,'(3.1.2)--第三章社会主义改造理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/(3.1.2)--第三章社会主义改造理论.pdf',306726,'2020-05-15 21:02:12',71),(27,'(3.1.3)--第三章社会主义改造理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/(3.1.3)--第三章社会主义改造理论.pdf',3255498,'2020-05-15 21:02:15',71),(28,'(3.1.4)--第三章社会主义改造理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/(3.1.4)--第三章社会主义改造理论.pdf',114363,'2020-05-15 21:02:17',71),(29,'(3.1.5)--第三章社会主义改造理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/(3.1.5)--第三章社会主义改造理论.pdf',186684,'2020-05-15 21:02:19',71),(30,'(4.1.1)--第四章社会主义建设道路初步探索的理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/(4.1.1)--第四章社会主义建设道路初步探索的理论成果.pdf',145698,'2020-05-15 21:02:27',75),(31,'(4.1.2)--第四章社会主义建设道路初步探索的理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/(4.1.2)--第四章社会主义建设道路初步探索的理论成果.pdf',200435,'2020-05-15 21:02:29',75),(32,'(4.1.3)--第四章社会主义建设道路初步探索的理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/(4.1.3)--第四章社会主义建设道路初步探索的理论成果.pdf',115283,'2020-05-15 21:02:32',75),(33,'(4.1.4)--第四章社会主义建设道路初步探索的理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/(4.1.4)--第四章社会主义建设道路初步探索的理论成果.pdf',1788168,'2020-05-15 21:02:34',75),(34,'(4.1.5)--第四章社会主义建设道路初步探索的理论成果.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/(4.1.5)--第四章社会主义建设道路初步探索的理论成果.pdf',118436,'2020-05-15 21:02:36',75),(35,'(5.1.1)--第五章建设中国特色社会主义总依据.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/(5.1.1)--第五章建设中国特色社会主义总依据.pdf',154387,'2020-05-15 21:02:45',78),(36,'(5.1.2)--第五章建设中国特色社会主义总依据.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/(5.1.2)--第五章建设中国特色社会主义总依据.pdf',235755,'2020-05-15 21:02:47',78),(37,'(5.1.3)--第五章建设中国特色社会主义总依据.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/(5.1.3)--第五章建设中国特色社会主义总依据.pdf',80626,'2020-05-15 21:02:49',78),(38,'(5.1.4)--第五章建设中国特色社会主义总依据.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/(5.1.4)--第五章建设中国特色社会主义总依据.pdf',3292423,'2020-05-15 21:02:51',78),(39,'(5.1.5)--第五章建设社会主义的总依据.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/(5.1.5)--第五章建设社会主义的总依据.pdf',254414,'2020-05-15 21:02:54',78),(40,'(6.1.1)--第六章社会主义本质和建设中国特色社会主义总任务.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/(6.1.1)--第六章社会主义本质和建设中国特色社会主义总任务.pdf',161754,'2020-05-15 21:03:03',82),(41,'(6.1.2)--第六章社会主义本质和建设中国特色社会主义总任务.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/(6.1.2)--第六章社会主义本质和建设中国特色社会主义总任务.pdf',260883,'2020-05-15 21:03:06',82),(42,'(6.1.3)--第六章社会主义本质和建设中国特色社会主义总任务.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/(6.1.3)--第六章社会主义本质和建设中国特色社会主义总任务.pdf',94484,'2020-05-15 21:03:08',82),(43,'(6.1.4)--第六章社会主义本质和建设中国特色社会主义总任务.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/(6.1.4)--第六章社会主义本质和建设中国特色社会主义总任务.pdf',1167835,'2020-05-15 21:03:10',82),(44,'(6.1.5)--第六章社会主义本质和建设中国特色社会主义总任务.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/(6.1.5)--第六章社会主义本质和建设中国特色社会主义总任务.pdf',225999,'2020-05-15 21:03:12',82),(45,'(7.1.1)--第七章社会主义改革开放理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/(7.1.1)--第七章社会主义改革开放理论.pdf',156858,'2020-05-15 21:03:21',85),(46,'(7.1.2)--第七章社会主义改革开放理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/(7.1.2)--第七章社会主义改革开放理论.pdf',263733,'2020-05-15 21:03:23',85),(47,'(7.1.3)--第七章社会主义改革开放理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/(7.1.3)--第七章社会主义改革开放理论.pdf',105606,'2020-05-15 21:03:26',85),(48,'(7.1.4)--第七章社会主义改革开放理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/(7.1.4)--第七章社会主义改革开放理论.pdf',1405057,'2020-05-15 21:03:29',85),(49,'(7.1.5)--第七章社会主义改革开放理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/(7.1.5)--第七章社会主义改革开放理论.pdf',217236,'2020-05-15 21:03:31',85),(52,'(8.1.1)--第八章建设中国特色社会主义总布局.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/(8.1.1)--第八章建设中国特色社会主义总布局.pdf',315214,'2020-05-15 21:04:31',89),(53,'(8.1.2)--第八章建设中国特色社会主义总布局第一节.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/(8.1.2)--第八章建设中国特色社会主义总布局第一节.pdf',577651,'2020-05-15 21:04:33',89),(54,'(8.1.3)--第八章建设中国特色社会主义总布局.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/(8.1.3)--第八章建设中国特色社会主义总布局.pdf',359750,'2020-05-15 21:04:36',89),(55,'(8.1.4)--第八章建设中国特色社会主义总布局.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/(8.1.4)--第八章建设中国特色社会主义总布局.pdf',213568,'2020-05-15 21:04:37',89),(56,'(8.1.5)--第八章建设中国特色社会主义总布局第一节.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/(8.1.5)--第八章建设中国特色社会主义总布局第一节.pdf',272830,'2020-05-15 21:04:40',89),(57,'(9.1.1)--第九章实现祖国完全统一的理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/(9.1.1)--第九章实现祖国完全统一的理论.pdf',232788,'2020-05-15 21:04:51',95),(58,'(9.1.2)--第九章实现祖国完全统一理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/(9.1.2)--第九章实现祖国完全统一理论.pdf',3949997,'2020-05-15 21:04:53',95),(59,'(9.1.3)--第九章实现祖国完全统一的理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/(9.1.3)--第九章实现祖国完全统一的理论.pdf',88716,'2020-05-15 21:04:55',95),(60,'(9.1.4)--第九章实现祖国完全统一理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/(9.1.4)--第九章实现祖国完全统一理论.pdf',251523,'2020-05-15 21:04:57',95),(61,'(9.1.5)--第九章实现祖国完全统一的理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/(9.1.5)--第九章实现祖国完全统一的理论.pdf',209265,'2020-05-15 21:04:59',95),(62,'(10.1.1)--第十章中国特色社会主义外交和国际战略.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/(10.1.1)--第十章中国特色社会主义外交和国际战略.pdf',197868,'2020-05-15 21:05:07',97),(63,'(10.1.2)--第十章中国特色社会主义外交和国际战略.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/(10.1.2)--第十章中国特色社会主义外交和国际战略.pdf',110113,'2020-05-15 21:05:11',97),(64,'(10.1.3)--第十章中国特色社会主义外交和国际战略.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/(10.1.3)--第十章中国特色社会主义外交和国际战略.pdf',1664500,'2020-05-15 21:05:13',97),(65,'(10.1.4)--第十章中国特色社会主义外交和国际战略.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/(10.1.4)--第十章中国特色社会主义外交和国际战略.pdf',235342,'2020-05-15 21:05:15',97),(66,'(10.1.5)--第十章中国特色社会主义外交和国际战略.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/(10.1.5)--第十章中国特色社会主义外交和国际战略.pdf',210530,'2020-05-15 21:05:17',97),(67,'(11.1.1)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/(11.1.1)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf',185444,'2020-05-15 21:05:26',100),(68,'(11.1.2)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/(11.1.2)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf',78463,'2020-05-15 21:05:28',100),(69,'(11.1.3)--第十一章建设中国特色社会主义的根本目的和依靠力量理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/(11.1.3)--第十一章建设中国特色社会主义的根本目的和依靠力量理论.pdf',1980860,'2020-05-15 21:05:30',100),(70,'(11.1.4)--第十一章建设中国特色社会主义的根本目的和依靠力量理论.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/(11.1.4)--第十一章建设中国特色社会主义的根本目的和依靠力量理论.pdf',285049,'2020-05-15 21:05:32',100),(71,'(11.1.5)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/(11.1.5)--第十一章建设中国特色社会主义的根本目的和依靠力量.pdf',240136,'2020-05-15 21:05:35',100),(72,'(12.1.1)--第十二章建设中国特色社会主义的领导核心.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/(12.1.1)--第十二章建设中国特色社会主义的领导核心.pdf',161341,'2020-05-15 21:05:43',105),(73,'(12.1.2)--第十二章建设中国特色社会主义的领导核心.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/(12.1.2)--第十二章建设中国特色社会主义的领导核心.pdf',85229,'2020-05-15 21:05:45',105),(74,'(12.1.3)--第十二章建设中国特色社会主义的领导核心.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/(12.1.3)--第十二章建设中国特色社会主义的领导核心.pdf',2047951,'2020-05-15 21:05:47',105),(75,'(12.1.4)--第十二章建设中国特色社会主义的领导核心.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/(12.1.4)--第十二章建设中国特色社会主义的领导核心.pdf',284157,'2020-05-15 21:05:49',105),(76,'(12.1.5)--第十二章建设中国特色社会主义的领导核心.pdf','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/(12.1.5)--第十二章建设中国特色社会主义的领导核心.pdf',275492,'2020-05-15 21:05:51',105),(79,'(1.1.1)--教学录像讲座文本：马克思主义的概念及其产生.pdf','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/1_马克思主义的创立与发展1/(1.1.1)--教学录像讲座文本：马克思主义的概念及其产生.pdf',117980,'2020-05-16 02:34:00',112),(80,'(1.1.2)--教学重点.pdf','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/1_马克思主义的创立与发展1/(1.1.2)--教学重点.pdf',73335,'2020-05-16 02:34:02',112),(81,'(1.1.3)--逻辑示意图.pdf','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/1_马克思主义的创立与发展1/(1.1.3)--逻辑示意图.pdf',89060,'2020-05-16 02:34:05',112),(82,'(1.1.5)--绪论.pdf','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/1_马克思主义的创立与发展1/(1.1.5)--绪论.pdf',411458,'2020-05-16 02:34:07',112),(83,'[1.1.4]--马克思的志向.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/4_马克思主义的创立与发展4/[1.1.4]--马克思的志向.mp4',3056926,'2020-05-16 02:37:04',115),(86,'(1.1)--课程简介.pdf','D://WebsiteResources/course/4_思想道德与法律基础/1_绪论/1_绪论小节/(1.1)--课程简介.pdf',414879,'2020-05-17 15:48:36',160),(87,'(1.1)--课程简介.pdf','D://WebsiteResources/course/4_思想品德与法律基础/1_绪论/1_绪论1234/(1.1)--课程简介.pdf',414879,'2020-05-17 16:05:59',164);

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(32) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(16) NOT NULL COMMENT '用户名',
  `password` varchar(16) DEFAULT NULL COMMENT '密码',
  `role` varchar(16) DEFAULT NULL COMMENT '用户角色',
  `phone_num` varchar(16) DEFAULT NULL COMMENT '手机号',
  `real_name` varchar(16) DEFAULT NULL COMMENT '真实姓名',
  `id_card` varchar(32) DEFAULT NULL COMMENT '身份证号码',
  `college_name` varchar(32) DEFAULT NULL COMMENT '学院/系名',
  `school_num` varchar(32) DEFAULT NULL COMMENT '学号',
  `gender` int(4) DEFAULT NULL COMMENT '性别',
  `avatar_url` varchar(256) DEFAULT NULL COMMENT '用户头像url',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`role`,`phone_num`,`real_name`,`id_card`,`college_name`,`school_num`,`gender`,`avatar_url`) values (1,'admin','123','admin','12345678910','张三','110105199606019130','计算机与信息工程学院','b16041515',1,'D://WebsiteResources/user/1_admin/avatar/Laughing_man_logo.png'),(2,'ben','123','teacher','43545345354','李四','440152100281265412','计算机与信息工程学院','B16041522',0,NULL),(3,'june','123','student','16321235148','小东','440102199909093684','经管学院','b16041511',1,NULL),(4,'test1','123','student','15648912345','李明','440152200281265409','土木学院','B16041581',0,NULL);

/*Table structure for table `video` */

DROP TABLE IF EXISTS `video`;

CREATE TABLE `video` (
  `id` int(32) NOT NULL AUTO_INCREMENT,
  `name` varchar(256) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `size` bigint(32) DEFAULT NULL,
  `upload_date` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8;

/*Data for the table `video` */

insert  into `video`(`id`,`name`,`url`,`size`,`upload_date`) values (12,'[1.1.1]--第一章马克思主义中国化两大理论成果第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/1_马克思主义中国化及其发展/[1.1.1]--第一章马克思主义中国化两大理论成果第一节.mp4',287629319,'2020-05-14 22:13:21'),(13,'[1.3.1]--第一章马克思主义中国化的两大理论成果第三节（1）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/3_中国特色社会主义理论体系/[1.3.1]--第一章马克思主义中国化的两大理论成果第三节（1）.mp4',119774698,'2020-05-14 23:36:51'),(14,'[1.2.1]--第一章马克思主义中国化两大理论成果第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/2_毛泽东思想/[1.2.1]--第一章马克思主义中国化两大理论成果第二节.mp4',172918672,'2020-05-14 23:38:21'),(15,'[1.4.1]--第一章马克思主义中国化两大理论成果第四节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/1_马克思主义中国化的历史进程和伟大成果/4_思想路线与理论精髓/[1.4.1]--第一章马克思主义中国化两大理论成果第四节.mp4',43955843,'2020-05-14 23:39:23'),(16,'[2.1.1]--第二章新民主主义革命理论第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/1_新民主主义革命理论形成的依据/[2.1.1]--第二章新民主主义革命理论第一节.mp4',235095545,'2020-05-15 18:08:07'),(17,'[2.2.1]--第二章新民主主义革命理论第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/2_新民主主义革命的总路线和基本纲领/[2.2.1]--第二章新民主主义革命理论第二节.mp4',265984903,'2020-05-15 18:12:45'),(18,'[2.3.1]--第二章新民主主义革命理论第三节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/2_新民主主义革命理论/3_新民主主义革命的道路和基本经验/[2.3.1]--第二章新民主主义革命理论第三节.mp4',394232062,'2020-05-15 18:12:57'),(19,'[3.1.1]--第三章社会主义改造理论第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/1_从新民主主义到社会主义的转变/[3.1.1]--第三章社会主义改造理论第一节.mp4',121507148,'2020-05-15 18:24:05'),(20,'[3.2.1]--第三章社会主义改造理论第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/2_社会主义改造道路和历史经验/[3.2.1]--第三章社会主义改造理论第二节.mp4',299634148,'2020-05-15 18:24:20'),(21,'[3.3.1]--第三章社会主义改造理论第三节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/3_社会主义改造理论/3_社会主义制度在中国的确立/[3.3.1]--第三章社会主义改造理论第三节.mp4',197927784,'2020-05-15 18:24:32'),(22,'[4.1.1]--第四章社会主义建设道路初步探索的理论成果第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/1_社会主义建设道路初步探索的重要思想成果/[4.1.1]--第四章社会主义建设道路初步探索的理论成果第一节.mp4',129873947,'2020-05-15 18:26:03'),(23,'[4.2.1]--第四章社会主义建设道路初步探索的理论成果第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/4_社会主义建设道路初步探索的理论成果/2_社会主义建设道路初步探索的意义和经验教训/[4.2.1]--第四章社会主义建设道路初步探索的理论成果第二节.mp4',108652858,'2020-05-15 18:26:12'),(24,'[5.1.1]--第五章建设中国特色社会主义总依据.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/5_建设中国特色社会主义总依据/1_社会主义初级阶段理论/[5.1.1]--第五章建设中国特色社会主义总依据.mp4',272182641,'2020-05-15 18:28:02'),(25,'[6.1.1]--第六章社会主义本质和建设中国特色社会主义总任务（1）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/1_社会主义的本质/[6.1.1]--第六章社会主义本质和建设中国特色社会主义总任务（1）.mp4',85231803,'2020-05-15 18:30:07'),(26,'[6.2.1]--第六章社会主义本质和建设中国特色社会主义总任务（2）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/6_社会主义本质和建设中国特色社会主义总任务/2_社会主义的根本任务/[6.2.1]--第六章社会主义本质和建设中国特色社会主义总任务（2）.mp4',106777951,'2020-05-15 18:30:17'),(27,'[7.1.1]--第七章社会主义改革开放理论第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/1_改革开放是决定当代中国命运的关键抉择/[7.1.1]--第七章社会主义改革开放理论第一节.mp4',76374013,'2020-05-15 18:31:51'),(28,'[7.2.1]--第七章社会主义改革开放理论第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/2_全面深化改革/[7.2.1]--第七章社会主义改革开放理论第二节.mp4',106779224,'2020-05-15 18:31:58'),(29,'[7.3.1]--第七章社会主义改革开放理论第三节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/7_社会主义改革开放理论/3_扩大对外开放/[7.3.1]--第七章社会主义改革开放理论第三节.mp4',75874539,'2020-05-15 18:32:05'),(30,'[8.1.1]--第八章第一节建设中国特色社会主义经济.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/1_建设社会主义市场经济/[8.1.1]--第八章第一节建设中国特色社会主义经济.mp4',118947020,'2020-05-15 18:36:41'),(31,'[8.2.1]--第八章建设中国特色社会主义总布局.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/2_建设中国特色社会主义政治/[8.2.1]--第八章建设中国特色社会主义总布局.mp4',438029777,'2020-05-15 18:36:50'),(32,'[8.3.1]--第八章第三节建设中国特色社会主义文化（1）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/3_建设中国特色社会主义文化/[8.3.1]--第八章第三节建设中国特色社会主义文化（1）.mp4',78169197,'2020-05-15 18:37:08'),(33,'[8.4.1]--第八章第四节建设社会主义和谐社会.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/4_建设社会主义和谐社会/[8.4.1]--第八章第四节建设社会主义和谐社会.mp4',394472675,'2020-05-15 18:37:20'),(34,'[8.5.1]--第八章第五节建设社会主义生态文明（1）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/8_建设中国特色社会主义总布局/5_建设社会主义生态文明/[8.5.1]--第八章第五节建设社会主义生态文明（1）.mp4',95434412,'2020-05-15 18:37:29'),(35,'[9.1.1]--第九章实现祖国完全统一的理论.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/9_实现祖国完全统一理论/1_实现祖国完全统一是中华民族的根本利益/[9.1.1]--第九章实现祖国完全统一的理论.mp4',209932379,'2020-05-15 18:38:33'),(36,'[10.1.1]--第十章中国特色社会主义外交和国际战略第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/1_外交和国际战略理论的形成依据/[10.1.1]--第十章中国特色社会主义外交和国际战略第一节.mp4',72930053,'2020-05-15 19:23:51'),(37,'[10.2.1]--第十章中国特色社会主义外交和国际战略第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/10_中国特色社会主义外交和国际战略/2_坚持走和平发展道路/[10.2.1]--第十章中国特色社会主义外交和国际战略第二节.mp4',95680875,'2020-05-15 19:23:59'),(38,'[11.1.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第一节（2）.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/1_建设中国特色社会主义的根本目的/[11.1.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第一节（2）.mp4',18560546,'2020-05-15 19:30:36'),(39,'[11.2.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/2_建设中国特色社会主义的依靠力量/[11.2.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第二节.mp4',100368786,'2020-05-15 19:30:45'),(40,'[11.3.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第三节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/3_巩固和发展爱国统一战线/[11.3.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第三节.mp4',118789130,'2020-05-15 19:30:52'),(41,'[11.4.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第四节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/11_建设中国特色社会主义的根本目的和依靠力量理论/4_建设巩固国防和强大军队/[11.4.1]--第十一章建设中国特色社会主义的根本目的和依靠力量理论第四节.mp4',135527896,'2020-05-15 19:30:59'),(42,'[12.1.1]--第十二章建设中国特色社会主义的领导核心第一节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/1_党的领导是社会主义现代化建设的根本保证/[12.1.1]--第十二章建设中国特色社会主义的领导核心第一节.mp4',134761077,'2020-05-15 19:31:10'),(43,'[12.2.1]--第十二章建设中国特色社会主义的领导核心第二节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/2_全面提高党的建设科学化水平/[12.2.1]--第十二章建设中国特色社会主义的领导核心第二节.mp4',135458214,'2020-05-15 19:32:00'),(44,'[12.3.1]--第十二章建设中国特色社会主义的领导核心第三节.mp4','D://WebsiteResources/course/1_毛泽东思想和中国特色社会主义理论体系概论/12_建设中国特色社会主义的领导核心/3_全面从严治党/[12.3.1]--第十二章建设中国特色社会主义的领导核心第三节.mp4',129842480,'2020-05-15 19:32:08'),(46,'[1.1.1]--马克思主义的创立与发展.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/1_马克思主义的创立与发展1/[1.1.1]--马克思主义的创立与发展.mp4',88751406,'2020-05-16 02:32:32'),(47,'[1.1.2]--马克思（上）.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/2_马克思主义的创立与发展2/[1.1.2]--马克思（上）.mp4',5345640,'2020-05-16 02:32:49'),(48,'[1.1.3]--马克思（下）.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/3_马克思主义的创立与发展3/[1.1.3]--马克思（下）.mp4',7546913,'2020-05-16 02:33:12'),(49,'[1.1.4]--马克思的志向.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/[1.1.4]--马克思的志向.mp4',3056926,'2020-05-16 02:33:47'),(50,'[1.2.1]--马克思主义的鲜明特征.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/2_马克思主义的鲜明特征/1_马克思主义的鲜明特征/[1.2.1]--马克思主义的鲜明特征.mp4',119180036,'2020-05-16 02:35:13'),(51,'[1.2.2]--共产党宣言.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/2_马克思主义的鲜明特征/2_共产党宣言/[1.2.2]--共产党宣言.mp4',5704304,'2020-05-16 02:35:30'),(52,'[1.1.4]--马克思的志向.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/1_马克思主义的创立与发展/4_马克思主义的创立与发展4/[1.1.4]--马克思的志向.mp4',3056926,'2020-05-16 02:38:43'),(53,'[1.1]--一、学习《中国近现代史纲要》的现实价值与意义.mp4','D://WebsiteResources/course/3_中国近现代史纲要/1_引言/1_学习《中国近现代史纲要》的现实价值与意义/[1.1]--一、学习《中国近现代史纲要》的现实价值与意义.mp4',212019125,'2020-05-16 10:14:12'),(54,'[1.2]--二、鸦片战争前的中国与世界.mp4','D://WebsiteResources/course/3_中国近现代史纲要/1_引言/2_鸦片战争前的中国与世界/[1.2]--二、鸦片战争前的中国与世界.mp4',233907031,'2020-05-16 10:16:50'),(55,'[1.3.1]--马克思主义的当代价值.mp4','D://WebsiteResources/course/2_马克思主义基本原理概论/1_导论/3_马克思主义的当代价值/[1.3.1]--马克思主义的当代价值.mp4',103560097,'2020-05-16 10:21:44'),(58,'[1.1]--课程简介.mp4','D://WebsiteResources/course/4_思想道德与法律基础/1_绪论/1_绪论小节/[1.1]--课程简介.mp4',20432390,'2020-05-17 15:48:25'),(59,'[1.1]--课程简介.mp4','D://WebsiteResources/course/4_思想品德与法律基础/1_绪论/1_绪论小节/[1.1]--课程简介.mp4',20432390,'2020-05-17 16:05:27');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;