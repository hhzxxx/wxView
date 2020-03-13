/*
Navicat MySQL Data Transfer

Source Server         : hhz
Source Server Version : 80013
Source Host           : localhost:3306
Source Database       : qxt_bysj

Target Server Type    : MYSQL
Target Server Version : 80013
File Encoding         : 65001

Date: 2020-03-13 17:56:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for tab_tag
-- ----------------------------
DROP TABLE IF EXISTS `tab_tag`;
CREATE TABLE `tab_tag` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagname` varchar(255) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3199 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tab_tagxuser
-- ----------------------------
DROP TABLE IF EXISTS `tab_tagxuser`;
CREATE TABLE `tab_tagxuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `hot` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tab_tagxvideo
-- ----------------------------
DROP TABLE IF EXISTS `tab_tagxvideo`;
CREATE TABLE `tab_tagxvideo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `tagId` int(11) DEFAULT NULL,
  `videoId` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23836 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tab_user
-- ----------------------------
DROP TABLE IF EXISTS `tab_user`;
CREATE TABLE `tab_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `openid` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `nickName` varchar(255) DEFAULT NULL,
  `province` varchar(255) DEFAULT NULL,
  `avatarUrl` varchar(2000) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tab_video
-- ----------------------------
DROP TABLE IF EXISTS `tab_video`;
CREATE TABLE `tab_video` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uuid` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `title` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `typeid` int(11) DEFAULT NULL,
  `avid` int(11) DEFAULT NULL,
  `owner` varchar(255) DEFAULT NULL,
  `ownerId` int(11) DEFAULT NULL,
  `pic` varchar(1000) DEFAULT NULL,
  `remark` varchar(1000) DEFAULT NULL,
  `hot` int(11) DEFAULT '0',
  `cid` int(11) DEFAULT NULL COMMENT '视频Id',
  `good` int(11) DEFAULT '0' COMMENT '点赞',
  `bad` int(11) DEFAULT '0' COMMENT '踩',
  `collection` int(11) DEFAULT '0' COMMENT '收藏',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9639 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for tab_videoxuser
-- ----------------------------
DROP TABLE IF EXISTS `tab_videoxuser`;
CREATE TABLE `tab_videoxuser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态',
  `createTime` datetime NOT NULL COMMENT '创建时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `videoId` int(11) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `isGood` int(11) DEFAULT NULL,
  `isBad` int(11) DEFAULT NULL,
  `isCollection` int(11) DEFAULT NULL,
  `collectionTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
