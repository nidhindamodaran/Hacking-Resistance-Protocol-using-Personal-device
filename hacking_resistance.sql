# MySQL-Front 3.2  (Build 8.0)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES latin1 */;

# Host: localhost    Database: hacking_resistance
# ------------------------------------------------------
# Server version 5.1.43-community

DROP DATABASE IF EXISTS `hacking_resistance`;
CREATE DATABASE `hacking_resistance` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `hacking_resistance`;

#
# Table structure for table login_info
#

CREATE TABLE `login_info` (
  `username` varchar(50) NOT NULL,
  `password` varchar(50) DEFAULT NULL,
  `imei` varchar(200) DEFAULT NULL,
  `one_time_key` blob,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table login_info
#

INSERT INTO `login_info` VALUES ('adrija','ad','1111111111111111','pgp98p22hej24qnsg8f9hthshi');
INSERT INTO `login_info` VALUES ('anoop','an','8989898989898989',NULL);
INSERT INTO `login_info` VALUES ('dunsten','dunsten','2321231231231133',NULL);
INSERT INTO `login_info` VALUES ('harris','harris','8989898989898989','1223');
INSERT INTO `login_info` VALUES ('jude','jude','5555555555555555','iukjhbkkj9t84cs6h12fs6e67r');
INSERT INTO `login_info` VALUES ('kanga','kanga','1212212121112112',NULL);
INSERT INTO `login_info` VALUES ('sadiq','as','8989898989898986',NULL);
INSERT INTO `login_info` VALUES ('thasneem','thasneem','358870050025265','uk1ut6vu5l3jcm8c5migpqn609');


#
# Table structure for table lost_imei
#

CREATE TABLE `lost_imei` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `imei` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

#
# Dumping data for table lost_imei
#

INSERT INTO `lost_imei` VALUES (2,'8989898989898986');
INSERT INTO `lost_imei` VALUES (3,'9999999999999999');
INSERT INTO `lost_imei` VALUES (4,'7777777777777777');
INSERT INTO `lost_imei` VALUES (5,'7777777777777788');


#
# Table structure for table user_info
#

CREATE TABLE `user_info` (
  `user_name` varchar(50) NOT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `phone` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Dumping data for table user_info
#

INSERT INTO `user_info` VALUES ('adrija','adrija','adrija@gmail.com','1234561234');
INSERT INTO `user_info` VALUES ('anoop','1231231231','','');
INSERT INTO `user_info` VALUES ('dunsten','dunsten','dunsten@gmail.com','1231231313');
INSERT INTO `user_info` VALUES ('harris','harris','harris@g','3232323232');
INSERT INTO `user_info` VALUES ('jude','jude','javavs2@gmail.com','2121212121');
INSERT INTO `user_info` VALUES ('kanga','kagnaew','javavfs14@gmail.com','1232132321');
INSERT INTO `user_info` VALUES ('sadiq','sadiq','sadiq@gmail.com','1231231231');
INSERT INTO `user_info` VALUES ('thasneem','Thasneem','javavfs3@gmail.com','1231231231');


#
#  Foreign keys for table user_info
#

ALTER TABLE `user_info`
  ADD FOREIGN KEY (`user_name`) REFERENCES `login_info` (`username`) ON DELETE CASCADE;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
