/*
 Navicat Premium Data Transfer

 Source Server         : localMysql
 Source Server Type    : MySQL
 Source Server Version : 80013 (8.0.13)
 Source Host           : localhost:3306
 Source Schema         : cxm_music

 Target Server Type    : MySQL
 Target Server Version : 80013 (8.0.13)
 File Encoding         : 65001

 Date: 16/03/2023 23:03:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_gender
-- ----------------------------
DROP TABLE IF EXISTS `dict_gender`;
CREATE TABLE `dict_gender`  (
  `id` int(11) NOT NULL,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_gender
-- ----------------------------
INSERT INTO `dict_gender` VALUES (0, '保密', 1, 0);
INSERT INTO `dict_gender` VALUES (1, '男', 1, 0);
INSERT INTO `dict_gender` VALUES (2, '女', 1, 0);

-- ----------------------------
-- Table structure for dict_singer_type
-- ----------------------------
DROP TABLE IF EXISTS `dict_singer_type`;
CREATE TABLE `dict_singer_type`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌手类型',
  `deleted` tinyint(4) NULL DEFAULT 1,
  `version` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_singer_type
-- ----------------------------
INSERT INTO `dict_singer_type` VALUES (1, '男', 1, 0);
INSERT INTO `dict_singer_type` VALUES (2, '女', 1, 0);
INSERT INTO `dict_singer_type` VALUES (3, '组合', 1, 0);

-- ----------------------------
-- Table structure for dict_tag
-- ----------------------------
DROP TABLE IF EXISTS `dict_tag`;
CREATE TABLE `dict_tag`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 521 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_tag
-- ----------------------------
INSERT INTO `dict_tag` VALUES (0, '为你推荐', 1, 0);
INSERT INTO `dict_tag` VALUES (101, '华语', 1, 0);
INSERT INTO `dict_tag` VALUES (102, '粤语', 1, 0);
INSERT INTO `dict_tag` VALUES (103, '欧美', 1, 0);
INSERT INTO `dict_tag` VALUES (104, '韩语', 1, 0);
INSERT INTO `dict_tag` VALUES (105, '日语', 1, 0);
INSERT INTO `dict_tag` VALUES (201, '猜你喜欢', 1, 0);
INSERT INTO `dict_tag` VALUES (202, '官方歌单', 1, 0);
INSERT INTO `dict_tag` VALUES (203, 'AI歌单', 1, 0);
INSERT INTO `dict_tag` VALUES (204, '热榜歌单', 1, 0);
INSERT INTO `dict_tag` VALUES (301, '综艺', 1, 0);
INSERT INTO `dict_tag` VALUES (302, '影视原声', 1, 0);
INSERT INTO `dict_tag` VALUES (303, '校园', 1, 0);
INSERT INTO `dict_tag` VALUES (304, '游戏', 1, 0);
INSERT INTO `dict_tag` VALUES (305, '儿童', 1, 0);
INSERT INTO `dict_tag` VALUES (306, '网络热歌', 1, 0);
INSERT INTO `dict_tag` VALUES (307, 'KTV金曲', 1, 0);
INSERT INTO `dict_tag` VALUES (308, '经典老歌', 1, 0);
INSERT INTO `dict_tag` VALUES (309, '翻唱', 1, 0);
INSERT INTO `dict_tag` VALUES (310, '榜单', 1, 0);
INSERT INTO `dict_tag` VALUES (311, '情歌', 1, 0);
INSERT INTO `dict_tag` VALUES (312, '背景音乐', 1, 0);
INSERT INTO `dict_tag` VALUES (401, '学习', 1, 0);
INSERT INTO `dict_tag` VALUES (402, '工作', 1, 0);
INSERT INTO `dict_tag` VALUES (403, '休息', 1, 0);
INSERT INTO `dict_tag` VALUES (404, '驾车', 1, 0);
INSERT INTO `dict_tag` VALUES (405, '休闲时间', 1, 0);
INSERT INTO `dict_tag` VALUES (406, '运动', 1, 0);
INSERT INTO `dict_tag` VALUES (407, '旅行', 1, 0);
INSERT INTO `dict_tag` VALUES (408, '约会', 1, 0);
INSERT INTO `dict_tag` VALUES (409, '跳舞', 1, 0);
INSERT INTO `dict_tag` VALUES (501, '快乐', 1, 0);
INSERT INTO `dict_tag` VALUES (502, '伤感', 1, 0);
INSERT INTO `dict_tag` VALUES (503, '感动', 1, 0);
INSERT INTO `dict_tag` VALUES (504, '思念', 1, 0);
INSERT INTO `dict_tag` VALUES (505, '安静', 1, 0);
INSERT INTO `dict_tag` VALUES (506, '孤独', 1, 0);
INSERT INTO `dict_tag` VALUES (507, '兴奋', 1, 0);
INSERT INTO `dict_tag` VALUES (508, '怀旧', 1, 0);
INSERT INTO `dict_tag` VALUES (509, '流行', 1, 0);
INSERT INTO `dict_tag` VALUES (510, '摇滚', 1, 0);
INSERT INTO `dict_tag` VALUES (511, '舞曲', 1, 0);
INSERT INTO `dict_tag` VALUES (512, '民谣', 1, 0);
INSERT INTO `dict_tag` VALUES (513, '说唱', 1, 0);
INSERT INTO `dict_tag` VALUES (514, '民族', 1, 0);
INSERT INTO `dict_tag` VALUES (515, '古典', 1, 0);
INSERT INTO `dict_tag` VALUES (516, '金属', 1, 0);
INSERT INTO `dict_tag` VALUES (517, '古风', 1, 0);
INSERT INTO `dict_tag` VALUES (518, '拉丁', 1, 0);
INSERT INTO `dict_tag` VALUES (519, '爵士', 1, 0);
INSERT INTO `dict_tag` VALUES (520, '乡村', 1, 0);

-- ----------------------------
-- Table structure for singer
-- ----------------------------
DROP TABLE IF EXISTS `singer`;
CREATE TABLE `singer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌手名称',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别 外键',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型  单人（男/女） 或 组合  外键',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '头像url',
  `song_number` int(11) NOT NULL DEFAULT 0 COMMENT '歌曲数量',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌手简介',
  `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gender`(`gender` ASC) USING BTREE,
  INDEX `type`(`type` ASC) USING BTREE,
  CONSTRAINT `singer_ibfk_1` FOREIGN KEY (`gender`) REFERENCES `dict_gender` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `singer_ibfk_2` FOREIGN KEY (`type`) REFERENCES `dict_singer_type` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (1, 'Sean Powell', '女', '男', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 443, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (2, 'Chad Robertson', '保密', '女', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 803, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (3, 'Jane Kennedy', '男', '男', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 999, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (4, '常岚', '保密', '女', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 995, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (5, 'Rachel Johnson', '保密', '组合', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 92, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (6, 'Joseph Mendez', '女', '组合', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 155, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (7, '吴嘉伦', '女', '女', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 217, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (8, '萧子异', '保密', '组合', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 763, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (9, 'Paul Herrera', '保密', '男', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 286, '歌手简介', 1, 0);
INSERT INTO `singer` VALUES (31, '彭詩涵', '女', '女', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', 86, '歌手简介', 1, 0);

-- ----------------------------
-- Table structure for singer_own_song
-- ----------------------------
DROP TABLE IF EXISTS `singer_own_song`;
CREATE TABLE `singer_own_song`  (
  `singerId` int(11) NOT NULL COMMENT '歌手的id',
  `songId` int(11) NOT NULL COMMENT '歌曲的id',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `singerId`(`singerId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `singer_own_song_ibfk_1` FOREIGN KEY (`singerId`) REFERENCES `singer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `singer_own_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of singer_own_song
-- ----------------------------

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌曲名称',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲封面',
  `duration` bigint(20) NOT NULL COMMENT '歌曲时长（毫秒）',
  `source_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌曲资源路径',
  `play_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '播放量',
  `star_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏量',
  `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除 ',
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song
-- ----------------------------

-- ----------------------------
-- Table structure for song_tag
-- ----------------------------
DROP TABLE IF EXISTS `song_tag`;
CREATE TABLE `song_tag`  (
  `songId` int(11) NOT NULL COMMENT '歌曲id',
  `tagId` int(11) NOT NULL COMMENT '标签id',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NULL DEFAULT 0,
  PRIMARY KEY (`songId`) USING BTREE,
  INDEX `tagId`(`tagId` ASC) USING BTREE,
  CONSTRAINT `song_tag_ibfk_1` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `song_tag_ibfk_2` FOREIGN KEY (`tagId`) REFERENCES `dict_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of song_tag
-- ----------------------------

-- ----------------------------
-- Table structure for songlist
-- ----------------------------
DROP TABLE IF EXISTS `songlist`;
CREATE TABLE `songlist`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌单名称',
  `author_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者名称',
  `author_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者的账号  外键',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签  外键',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌单封面',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `song_number` int(11) NOT NULL COMMENT '歌单的歌曲数量',
  `play_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '播放量',
  `star_number` bigint(20) NOT NULL DEFAULT 0 COMMENT '收藏量',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除 0删除 1未被删除',
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag`(`tag` ASC) USING BTREE,
  INDEX `author_account`(`author_account` ASC) USING BTREE,
  CONSTRAINT `songlist_ibfk_1` FOREIGN KEY (`tag`) REFERENCES `dict_tag` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlist_ibfk_2` FOREIGN KEY (`author_account`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of songlist
-- ----------------------------

-- ----------------------------
-- Table structure for songlist_own_song
-- ----------------------------
DROP TABLE IF EXISTS `songlist_own_song`;
CREATE TABLE `songlist_own_song`  (
  `songListId` int(11) NOT NULL COMMENT '歌单id',
  `songId` int(11) NOT NULL COMMENT '歌曲id',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `songListId`(`songListId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `songlist_own_song_ibfk_1` FOREIGN KEY (`songListId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlist_own_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of songlist_own_song
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号昵称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '头像的url',
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '0' COMMENT '性别  外键',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `lasted_login_time` datetime NULL DEFAULT NULL COMMENT '最近登录时间',
  `deleted` tinyint(4) NOT NULL DEFAULT 1 COMMENT '逻辑删除',
  `version` int(11) NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `account`(`account` ASC) USING BTREE,
  INDEX `gender`(`gender` ASC) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`gender`) REFERENCES `dict_gender` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '龚晓明', '10000380', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '女', '个性签名飞走啦~', '2023-03-16 22:32:09', NULL, 1, 0);
INSERT INTO `user` VALUES (2, '武杰宏', '10000244', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', '个性签名飞走啦~', '2023-03-12 01:18:04', NULL, 1, 0);
INSERT INTO `user` VALUES (3, '熊致远', '10000918', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', '个性签名飞走啦~', '2023-03-11 03:38:17', NULL, 1, 0);
INSERT INTO `user` VALUES (4, '毛秀英', '10000475', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '男', '个性签名飞走啦~', '2023-03-13 13:53:35', NULL, 1, 0);
INSERT INTO `user` VALUES (5, '叶璐', '10000103', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '女', '个性签名飞走啦~', '2023-03-15 03:32:54', NULL, 1, 0);
INSERT INTO `user` VALUES (6, '叶致远', '10000085', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '男', '个性签名飞走啦~', '2023-03-13 00:41:28', NULL, 1, 0);
INSERT INTO `user` VALUES (7, '常晓明', '10000284', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', '个性签名飞走啦~', '2023-03-16 00:26:10', NULL, 1, 0);
INSERT INTO `user` VALUES (8, '戴宇宁', '10000165', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', '个性签名飞走啦~', '2023-03-09 18:02:33', NULL, 1, 0);
INSERT INTO `user` VALUES (9, '黄岚', '10000039', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '男', '个性签名飞走啦~', '2023-03-09 04:46:46', NULL, 1, 0);
INSERT INTO `user` VALUES (10, '曾子异', '10000607', '123', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '女', '个性签名飞走啦~', '2023-03-13 13:28:44', NULL, 1, 0);

-- ----------------------------
-- Table structure for user_comment_song
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_song`;
CREATE TABLE `user_comment_song`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songId` int(11) NOT NULL COMMENT '歌曲id',
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用户对歌曲的评论内容',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_comment_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_comment_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_comment_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_comment_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_songlist`;
CREATE TABLE `user_comment_songlist`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songlistId` int(11) NOT NULL COMMENT '歌单id',
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_comment_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_comment_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_comment_songlist
-- ----------------------------

-- ----------------------------
-- Table structure for user_play_song
-- ----------------------------
DROP TABLE IF EXISTS `user_play_song`;
CREATE TABLE `user_play_song`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songId` int(11) NOT NULL COMMENT '歌曲id',
  `times` bigint(20) NULL DEFAULT 0 COMMENT '用户播放该歌曲的次数',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_play_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_play_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_play_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_play_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_play_songlist`;
CREATE TABLE `user_play_songlist`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songlistId` int(11) NOT NULL COMMENT '歌单id',
  `times` bigint(20) NULL DEFAULT 0 COMMENT '用户播放该歌单的次数',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_play_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_play_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_play_songlist
-- ----------------------------

-- ----------------------------
-- Table structure for user_star_song
-- ----------------------------
DROP TABLE IF EXISTS `user_star_song`;
CREATE TABLE `user_star_song`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songId` int(11) NOT NULL COMMENT '歌曲id',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_star_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_star_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_star_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_star_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_star_songlist`;
CREATE TABLE `user_star_songlist`  (
  `userId` int(11) NOT NULL COMMENT '用户id',
  `songlistId` int(11) NOT NULL COMMENT '歌单id',
  `deleted` tinyint(4) NOT NULL DEFAULT 1,
  `version` int(11) NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_star_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_star_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_star_songlist
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
