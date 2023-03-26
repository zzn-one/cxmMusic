/*
 Navicat Premium Data Transfer

 Source Server         : 1.12.54.183
 Source Server Type    : MySQL
 Source Server Version : 80030 (8.0.30)
 Source Host           : 1.12.54.183:3306
 Source Schema         : cxm_music

 Target Server Type    : MySQL
 Target Server Version : 80030 (8.0.30)
 File Encoding         : 65001

 Date: 22/03/2023 13:32:28
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dict_account
-- ----------------------------
DROP TABLE IF EXISTS `dict_account`;
CREATE TABLE `dict_account`  (
  `id` int NOT NULL,
  `account` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dict_account
-- ----------------------------
INSERT INTO `dict_account` VALUES (0, 1000000);

-- ----------------------------
-- Table structure for dict_gender
-- ----------------------------
DROP TABLE IF EXISTS `dict_gender`;
CREATE TABLE `dict_gender`  (
  `id` int NOT NULL,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌手类型',
  `deleted` tinyint NULL DEFAULT 1,
  `version` int NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `id` int NOT NULL AUTO_INCREMENT,
  `text` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `text`(`text` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 522 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

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
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌手名称',
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '性别 外键',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '类型  单人（男/女） 或 组合  外键',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg' COMMENT '头像url',
  `song_number` int NOT NULL DEFAULT 0 COMMENT '歌曲数量',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌手简介',
  `deleted` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT 0 COMMENT '乐观锁',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `gender`(`gender` ASC) USING BTREE,
  INDEX `type`(`type` ASC) USING BTREE,
  CONSTRAINT `singer_ibfk_1` FOREIGN KEY (`gender`) REFERENCES `dict_gender` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `singer_ibfk_2` FOREIGN KEY (`type`) REFERENCES `dict_singer_type` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 58 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (41, '焦迈奇', '男', '男', 'jiaomaiqi.png', 0, '焦迈奇，1996年10月4日出生于山东省潍坊市，中国内地流行乐男歌手、词曲作者、演员，毕业于沈阳音乐学院。', 1, 0);
INSERT INTO `singer` VALUES (42, '邓紫棋', '女', '女', 'dengziqi.png', 0, '邓紫棋（G.E.M.），本名邓诗颖，1991年8月16日出生于上海市，中国香港流行乐女歌手、词曲作者、音乐制作人。', 1, 0);
INSERT INTO `singer` VALUES (43, '李荣浩', '男', '男', 'lironghao.png', 0, '李荣浩（外文名：Youngho Lee，1985年7月11日-），出生于安徽蚌埠，中国内地男歌手、音乐制作人。', 1, 0);
INSERT INTO `singer` VALUES (44, 'Cascada', '女', '女', 'Cascada.png', 0, 'Cascada，德国乐队，成员包括主音Natalie Horler（1981年出生），以及DJ Manian和DJ Yanou三人。当中DJ Manian和DJYanou亦是唱片制作人。虽然Natalie Horler也会负责录音室的幕后工作，但卡丝卡达的作品仍是以DJ Manian和Yanou的制作为主。而由于卡丝卡达有两位DJ的关系，他们除了自行制作歌曲外，也会邀请其他DJ参与混音工作，以及帮其他歌手（例如舞动精灵乐团）混音。', 1, 0);
INSERT INTO `singer` VALUES (45, 'BLACKPINK', '女', '组合', 'BLACKPINK.png', 0, '韩国女子演唱组合，2016年8月8日由YG Entertainment推出，由金智秀（JISOO）、金珍妮（JENNIE）、朴彩英（ROSÉ）、LISA四名成员组成。组合名“BLACKPINK”在看起来很美的粉色中稍微加入了否定的意义，旨在传达出“不要只看漂亮的部分”、“看到的并不是全部”的意思。', 1, 0);
INSERT INTO `singer` VALUES (46, '宋雨琦', '女', '女', 'songyuqi.jpg', 2, '宋雨琦（송우기），出生于中国北京市，在中韩两地活动的中国女歌手。现为Cube娱乐旗下女子组合(G)I-DLE成员，担任领舞和副唱。2018年5月，随组合发行首张专辑《I am》，并在首尔举行出道Showcase，正式出道。', 1, 0);
INSERT INTO `singer` VALUES (47, '911', '男', '组合', '911.png', 0, '911乐队是英国的流行音乐团体，成立于1995年，1996年5月公布了他们的首张单曲。他们在2000年解散，但是在2005年重新组合。', 1, 0);
INSERT INTO `singer` VALUES (48, '周深', '男', '男', 'zhoushen.png', 0, '周深，成长于贵阳 ，中国内地男歌手，2016年6月毕业于乌克兰利沃夫国立音乐学院美声专业。', 1, 0);
INSERT INTO `singer` VALUES (49, '一只白羊', '女', '女', 'asheep.jpg', 0, '一只白羊，华语女歌手，代表作《赐我》、《夜不能寐》、《来不及告别》', 1, 0);
INSERT INTO `singer` VALUES (50, '许靖韵', '女', '女', 'xujingyun.png', 0, '许靖韵，中国香港女歌手。 2010年许靖韵参加英皇新秀歌唱大赛并取得亚军。', 1, 0);
INSERT INTO `singer` VALUES (55, '郭顶', '男', '男', 'guoding.png', 1, '郭顶，1985年8月16日出生于湖南省怀化市，当代唱作人、音乐制作人、华语男歌手。', 1, 0);
INSERT INTO `singer` VALUES (56, '陈奕迅', '男', '男', 'chenyixun.jpg', 1, '陈奕迅（Eason Chan），1974年7月27日出生于香港，中国香港男歌手、演员，毕业于英国金斯顿大学。', 1, 0);

-- ----------------------------
-- Table structure for singer_own_song
-- ----------------------------
DROP TABLE IF EXISTS `singer_own_song`;
CREATE TABLE `singer_own_song`  (
  `singerId` int NOT NULL COMMENT '歌手的id',
  `songId` int NOT NULL COMMENT '歌曲的id',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `singerId`(`singerId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `singer_own_song_ibfk_1` FOREIGN KEY (`singerId`) REFERENCES `singer` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `singer_own_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of singer_own_song
-- ----------------------------
INSERT INTO `singer_own_song` VALUES (55, 17, 1, 0);
INSERT INTO `singer_own_song` VALUES (56, 25, 1, 0);
INSERT INTO `singer_own_song` VALUES (46, 27, 1, 0);
INSERT INTO `singer_own_song` VALUES (46, 28, 1, 0);

-- ----------------------------
-- Table structure for song
-- ----------------------------
DROP TABLE IF EXISTS `song`;
CREATE TABLE `song`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌曲名称',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌曲封面',
  `duration` bigint NOT NULL COMMENT '歌曲时长（毫秒）',
  `source_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌曲资源路径',
  `play_number` bigint NOT NULL DEFAULT 0 COMMENT '播放量',
  `star_number` bigint NOT NULL DEFAULT 0 COMMENT '收藏量',
  `deleted` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除 ',
  `version` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (17, '幸运大门', 'xinyundamen', 200000, 'http://music.163.com/song/media/outer/url?id=85569.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (25, '富士山下', 'chenyixun1.jpg', 267000, 'http://music.163.com/song/media/outer/url?id=1411495698.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (27, 'Flowers', 'songyuqi01.jpg', 217000, 'http://ws.stream.qqmusic.qq.com/C400001jxcf50Qv2xE.m4a?guid=897197879&vkey=313C379CE347A00150C87236D68D26277FD43A873FB8430DCDF3302A6F35C5490C53ECA613DE4023760E91279444472CC669B630A0203C1D&uin=&fromtag=120032', 0, 0, 1, 0);
INSERT INTO `song` VALUES (28, 'Giants', 'songyuqi01.jpg', 211000, 'http://ws.stream.qqmusic.qq.com/C400001iWP1j3n8GsC.m4a?guid=805453566&vkey=085160EAA3C159FB8C6B40723239CAE73F892BCE813337CA6C7CD074F63ECEB37004E70D7F67A18716267EFF210023FCC0E1F5989A5AFDC4&uin=&fromtag=120032', 0, 0, 1, 0);

-- ----------------------------
-- Table structure for song_tag
-- ----------------------------
DROP TABLE IF EXISTS `song_tag`;
CREATE TABLE `song_tag`  (
  `songId` int NOT NULL COMMENT '歌曲id',
  `tagId` int NOT NULL COMMENT '标签id',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `tagId`(`tagId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `song_tag_ibfk_2` FOREIGN KEY (`tagId`) REFERENCES `dict_tag` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `song_tag_ibfk_3` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of song_tag
-- ----------------------------
INSERT INTO `song_tag` VALUES (17, 101, 1, 0);
INSERT INTO `song_tag` VALUES (17, 507, 1, 0);
INSERT INTO `song_tag` VALUES (25, 102, 1, 0);
INSERT INTO `song_tag` VALUES (25, 308, 1, 0);
INSERT INTO `song_tag` VALUES (25, 303, 1, 0);
INSERT INTO `song_tag` VALUES (25, 311, 1, 0);
INSERT INTO `song_tag` VALUES (25, 401, 1, 0);
INSERT INTO `song_tag` VALUES (25, 402, 1, 0);
INSERT INTO `song_tag` VALUES (25, 403, 1, 0);
INSERT INTO `song_tag` VALUES (25, 405, 1, 0);
INSERT INTO `song_tag` VALUES (25, 504, 1, 0);
INSERT INTO `song_tag` VALUES (25, 508, 1, 0);
INSERT INTO `song_tag` VALUES (27, 103, 1, 0);
INSERT INTO `song_tag` VALUES (27, 306, 1, 0);
INSERT INTO `song_tag` VALUES (27, 309, 1, 0);
INSERT INTO `song_tag` VALUES (27, 403, 1, 0);
INSERT INTO `song_tag` VALUES (27, 404, 1, 0);
INSERT INTO `song_tag` VALUES (27, 501, 1, 0);
INSERT INTO `song_tag` VALUES (27, 509, 1, 0);
INSERT INTO `song_tag` VALUES (28, 306, 1, 0);
INSERT INTO `song_tag` VALUES (28, 309, 1, 0);
INSERT INTO `song_tag` VALUES (28, 101, 1, 0);
INSERT INTO `song_tag` VALUES (28, 406, 1, 0);
INSERT INTO `song_tag` VALUES (28, 409, 1, 0);
INSERT INTO `song_tag` VALUES (28, 507, 1, 0);
INSERT INTO `song_tag` VALUES (28, 509, 1, 0);

-- ----------------------------
-- Table structure for songlist
-- ----------------------------
DROP TABLE IF EXISTS `songlist`;
CREATE TABLE `songlist`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '歌单名称',
  `author_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者名称',
  `author_account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '作者的账号  外键',
  `tag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '标签  外键',
  `img_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '歌单封面',
  `introduction` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '简介',
  `song_number` int NOT NULL COMMENT '歌单的歌曲数量',
  `play_number` bigint NOT NULL DEFAULT 0 COMMENT '播放量',
  `star_number` bigint NOT NULL DEFAULT 0 COMMENT '收藏量',
  `created_time` datetime NOT NULL COMMENT '创建时间',
  `deleted` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除 0删除 1未被删除',
  `version` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `tag`(`tag` ASC) USING BTREE,
  INDEX `author_account`(`author_account` ASC) USING BTREE,
  CONSTRAINT `songlist_ibfk_1` FOREIGN KEY (`tag`) REFERENCES `dict_tag` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlist_ibfk_2` FOREIGN KEY (`author_account`) REFERENCES `user` (`account`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of songlist
-- ----------------------------

-- ----------------------------
-- Table structure for songlist_own_song
-- ----------------------------
DROP TABLE IF EXISTS `songlist_own_song`;
CREATE TABLE `songlist_own_song`  (
  `songListId` int NOT NULL COMMENT '歌单id',
  `songId` int NOT NULL COMMENT '歌曲id',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `songListId`(`songListId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `songlist_own_song_ibfk_1` FOREIGN KEY (`songListId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `songlist_own_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of songlist_own_song
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号昵称',
  `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '账号',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg' COMMENT '头像的url',
  `gender` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT '保密' COMMENT '性别  外键',
  `signature` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名',
  `register_time` datetime NOT NULL COMMENT '注册时间',
  `lasted_login_time` datetime NULL DEFAULT NULL COMMENT '最近登录时间',
  `deleted` tinyint NOT NULL DEFAULT 1 COMMENT '逻辑删除',
  `version` int NOT NULL DEFAULT 0,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `account`(`account` ASC) USING BTREE,
  INDEX `gender`(`gender` ASC) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`gender`) REFERENCES `dict_gender` (`text`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 49 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (47, '法院玩也', '1000001', '$2a$10$NXDmbn6xi7hIK2kJOsF2EOsa3rQV71E5RVYvZ9I8fFX99hVw7S4jW', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', NULL, '2023-03-19 15:15:45', NULL, 1, 0);
INSERT INTO `user` VALUES (48, 'dasd', '1000002', '$2a$10$t91f97.7IElVV2SWlH6CfOxck6qWcHmxPVsW0bgejIGIVOW/Kq.OG', 'https://fuss10.elemecdn.com/e/5d/4a731a90594a4af544c0c25941171jpeg.jpeg', '保密', NULL, '2023-03-19 15:23:05', NULL, 1, 0);

-- ----------------------------
-- Table structure for user_comment_song
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_song`;
CREATE TABLE `user_comment_song`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songId` int NOT NULL COMMENT '歌曲id',
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '用户对歌曲的评论内容',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_comment_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_comment_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_comment_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_comment_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_comment_songlist`;
CREATE TABLE `user_comment_songlist`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songlistId` int NOT NULL COMMENT '歌单id',
  `text` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL COMMENT '评论内容',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_comment_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_comment_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_comment_songlist
-- ----------------------------

-- ----------------------------
-- Table structure for user_play_song
-- ----------------------------
DROP TABLE IF EXISTS `user_play_song`;
CREATE TABLE `user_play_song`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songId` int NOT NULL COMMENT '歌曲id',
  `times` bigint NULL DEFAULT 0 COMMENT '用户播放该歌曲的次数',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_play_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_play_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_play_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_play_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_play_songlist`;
CREATE TABLE `user_play_songlist`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songlistId` int NOT NULL COMMENT '歌单id',
  `times` bigint NULL DEFAULT 0 COMMENT '用户播放该歌单的次数',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_play_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_play_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_play_songlist
-- ----------------------------

-- ----------------------------
-- Table structure for user_star_song
-- ----------------------------
DROP TABLE IF EXISTS `user_star_song`;
CREATE TABLE `user_star_song`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songId` int NOT NULL COMMENT '歌曲id',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songId`(`songId` ASC) USING BTREE,
  CONSTRAINT `user_star_song_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_star_song_ibfk_2` FOREIGN KEY (`songId`) REFERENCES `song` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_star_song
-- ----------------------------

-- ----------------------------
-- Table structure for user_star_songlist
-- ----------------------------
DROP TABLE IF EXISTS `user_star_songlist`;
CREATE TABLE `user_star_songlist`  (
  `userId` int NOT NULL COMMENT '用户id',
  `songlistId` int NOT NULL COMMENT '歌单id',
  `deleted` tinyint NOT NULL DEFAULT 1,
  `version` int NOT NULL DEFAULT 0,
  INDEX `userId`(`userId` ASC) USING BTREE,
  INDEX `songlistId`(`songlistId` ASC) USING BTREE,
  CONSTRAINT `user_star_songlist_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_star_songlist_ibfk_2` FOREIGN KEY (`songlistId`) REFERENCES `songlist` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_star_songlist
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
