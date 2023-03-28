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

 Date: 28/03/2023 22:10:58
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
INSERT INTO `dict_account` VALUES (0, 1000003);

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
) ENGINE = InnoDB AUTO_INCREMENT = 71 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of singer
-- ----------------------------
INSERT INTO `singer` VALUES (41, '焦迈奇', '男', '男', 'jiaomaiqi.png', 9, '焦迈奇，1996年10月4日出生于山东省潍坊市，中国内地流行乐男歌手、词曲作者、演员，毕业于沈阳音乐学院。', 1, 0);
INSERT INTO `singer` VALUES (42, '邓紫棋', '女', '女', 'dengziqi.png', 12, '邓紫棋（G.E.M.），本名邓诗颖，1991年8月16日出生于上海市，中国香港流行乐女歌手、词曲作者、音乐制作人。', 1, 0);
INSERT INTO `singer` VALUES (43, '李荣浩', '男', '男', 'lironghao.png', 8, '李荣浩（外文名：Youngho Lee，1985年7月11日-），出生于安徽蚌埠，中国内地男歌手、音乐制作人。', 1, 0);
INSERT INTO `singer` VALUES (44, 'Cascada', '女', '女', 'Cascada.png', 0, 'Cascada，德国乐队，成员包括主音Natalie Horler（1981年出生），以及DJ Manian和DJ Yanou三人。当中DJ Manian和DJYanou亦是唱片制作人。虽然Natalie Horler也会负责录音室的幕后工作，但卡丝卡达的作品仍是以DJ Manian和Yanou的制作为主。而由于卡丝卡达有两位DJ的关系，他们除了自行制作歌曲外，也会邀请其他DJ参与混音工作，以及帮其他歌手（例如舞动精灵乐团）混音。', 1, 0);
INSERT INTO `singer` VALUES (45, 'BLACKPINK', '女', '组合', 'BLACKPINK.png', 7, '韩国女子演唱组合，2016年8月8日由YG Entertainment推出，由金智秀（JISOO）、金珍妮（JENNIE）、朴彩英（ROSÉ）、LISA四名成员组成。组合名“BLACKPINK”在看起来很美的粉色中稍微加入了否定的意义，旨在传达出“不要只看漂亮的部分”、“看到的并不是全部”的意思。', 1, 0);
INSERT INTO `singer` VALUES (46, '宋雨琦', '女', '女', 'songyuqi.jpg', 5, '宋雨琦（송우기），出生于中国北京市，在中韩两地活动的中国女歌手。现为Cube娱乐旗下女子组合(G)I-DLE成员，担任领舞和副唱。2018年5月，随组合发行首张专辑《I am》，并在首尔举行出道Showcase，正式出道。', 1, 0);
INSERT INTO `singer` VALUES (47, '911', '男', '组合', '911.jpg', 6, '911乐队是英国的流行音乐团体，成立于1995年，1996年5月公布了他们的首张单曲。他们在2000年解散，但是在2005年重新组合。', 1, 0);
INSERT INTO `singer` VALUES (48, '周深', '男', '男', 'zhoushen.png', 9, '周深，成长于贵阳 ，中国内地男歌手，2016年6月毕业于乌克兰利沃夫国立音乐学院美声专业。', 1, 0);
INSERT INTO `singer` VALUES (49, '一只白羊', '女', '女', 'asheep.jpg', 3, '一只白羊，华语女歌手，代表作《赐我》、《夜不能寐》、《来不及告别》', 1, 0);
INSERT INTO `singer` VALUES (50, '许靖韵', '女', '女', 'xujingyun.png', 6, '许靖韵，中国香港女歌手。 2010年许靖韵参加英皇新秀歌唱大赛并取得亚军。', 1, 0);
INSERT INTO `singer` VALUES (55, '郭顶', '男', '男', 'guoding.png', 8, '郭顶，1985年8月16日出生于湖南省怀化市，当代唱作人、音乐制作人、华语男歌手。', 1, 0);
INSERT INTO `singer` VALUES (56, '陈奕迅', '男', '男', 'chenyixun.jpg', 1, '陈奕迅（Eason Chan），1974年7月27日出生于香港，中国香港男歌手、演员，毕业于英国金斯顿大学。', 1, 0);
INSERT INTO `singer` VALUES (58, '周杰伦', '男', '男', 'zhoujielun.png', 0, '周杰伦（Jay Chou），1979年1月18日出生于台湾省新北市，华语流行男歌手、演员、词曲创作人、MV及电影导演、编剧及制作人。', 1, 0);
INSERT INTO `singer` VALUES (59, '林俊杰', '男', '男', 'linjunjie.jpg', 0, '林俊杰（JJ Lin），1981年3月27日出生于新加坡，祖籍中国福建省厦门市同安区，华语流行乐男歌手、音乐人、潮牌主理人。', 1, 0);
INSERT INTO `singer` VALUES (60, '薛之谦', '男', '男', 'xuezhiqian.jpg', 0, '薛之谦，1983年7月17日出生于上海市，中国内地流行乐男歌手、音乐制作人、影视演员，毕业于格里昂酒店管理学院。', 1, 0);
INSERT INTO `singer` VALUES (61, '王源', '男', '男', 'wangyuan.jpg', 0, '王源，2000年11月8日出生于重庆，中国内地男歌手、演员、主持人，TFBOYS成员 。 2011年底成为TF家族练习生，2013年8月6日以组合形式出道。', 1, 0);
INSERT INTO `singer` VALUES (62, '王俊凯', '男', '男', 'wangjunkai.png', 0, '王俊凯，1999年9月21日生于中国重庆，中国内地流行乐男歌手、影视演员、TFBOYS队长。', 1, 0);
INSERT INTO `singer` VALUES (63, '易烊千玺', '男', '男', 'yiyangqianxi.jpg', 0, '易烊千玺，中国内地男歌手、舞者、演员，TFBOYS成员。 2005年，首登电视荧屏，开始参演各类综艺节目 。', 1, 0);
INSERT INTO `singer` VALUES (64, '鹿晗', '男', '男', 'luhan.jpg', 0, '鹿晗（Lu Han），1990年4月20日出生于北京，中国男演员、歌手。', 1, 0);
INSERT INTO `singer` VALUES (65, '胡歌', '男', '男', 'huge.png', 0, '胡歌，1982年9月20日出生于上海市徐汇区，中国内地演员、歌手、制片人。', 1, 0);
INSERT INTO `singer` VALUES (66, '张碧晨', '女', '女', 'zhangbichen.png', 0, '张碧晨，华语流行乐女歌手，毕业于天津外国语大学。', 1, 0);
INSERT INTO `singer` VALUES (67, '任然', '女', '女', 'renran.png', 0, '任然，1989年7月3日出生于四川省成都市，中国内地流行乐女歌手。 代表作品有《空空如也》《那年初夏》《你好陌生人》《疑心病》《唇语》《外愈》《凉城》《山外小楼夜听雨》《后继者》《无人之岛》《飞鸟和蝉》', 1, 0);
INSERT INTO `singer` VALUES (68, '周笔畅', '女', '女', 'zhoubichang.jpg', 0, '周笔畅，1985年7月26日出生于湖南省长沙市，中国内地女歌手、词曲创作人、演员。', 1, 0);
INSERT INTO `singer` VALUES (69, '王靖雯', '女', '女', 'wangjingwen.png', 0, '王靖雯，曾用艺名王靖雯不胖，2001年4月5日出生于黑龙江省。歌手、原创音乐人、00后实力唱将。《中国好声音2021》全国亚军。', 1, 0);
INSERT INTO `singer` VALUES (70, '郎朗', '男', '男', 'langlang.jpg', 0, '郎朗，1982年6月14日出生于辽宁省沈阳市沈河区，国际著名钢琴家。他是第一位受聘于世界顶级的柏林爱乐乐团和美国五大交响乐团的中国钢琴家，获得古典音乐类多项权威奖项，包括德国古典回声大奖，全英古典音乐奖，伯恩斯坦艺术成就大奖，国际门德尔松大奖等。', 1, 0);

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
INSERT INTO `singer_own_song` VALUES (41, 29, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 30, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 33, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 34, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 35, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 36, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 37, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 38, 1, 0);
INSERT INTO `singer_own_song` VALUES (41, 39, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 40, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 41, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 42, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 43, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 44, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 45, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 46, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 47, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 48, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 49, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 50, 1, 0);
INSERT INTO `singer_own_song` VALUES (42, 51, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 52, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 53, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 54, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 55, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 56, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 57, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 58, 1, 0);
INSERT INTO `singer_own_song` VALUES (43, 59, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 60, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 61, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 62, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 63, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 64, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 65, 1, 0);
INSERT INTO `singer_own_song` VALUES (45, 66, 1, 0);
INSERT INTO `singer_own_song` VALUES (46, 67, 1, 0);
INSERT INTO `singer_own_song` VALUES (46, 68, 1, 0);
INSERT INTO `singer_own_song` VALUES (46, 69, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 70, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 71, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 72, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 73, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 74, 1, 0);
INSERT INTO `singer_own_song` VALUES (47, 75, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 76, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 77, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 79, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 80, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 81, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 82, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 83, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 84, 1, 0);
INSERT INTO `singer_own_song` VALUES (48, 85, 1, 0);
INSERT INTO `singer_own_song` VALUES (49, 86, 1, 0);
INSERT INTO `singer_own_song` VALUES (49, 87, 1, 0);
INSERT INTO `singer_own_song` VALUES (49, 88, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 89, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 90, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 91, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 92, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 93, 1, 0);
INSERT INTO `singer_own_song` VALUES (50, 94, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 95, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 96, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 97, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 98, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 99, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 100, 1, 0);
INSERT INTO `singer_own_song` VALUES (55, 101, 1, 0);

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
) ENGINE = InnoDB AUTO_INCREMENT = 102 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of song
-- ----------------------------
INSERT INTO `song` VALUES (17, '幸运大门', 'xinyundamen', 200000, 'http://music.163.com/song/media/outer/url?id=85569.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (25, '富士山下', 'chenyixun1.jpg', 267000, 'http://music.163.com/song/media/outer/url?id=1411495698.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (27, 'Flowers', 'songyuqi01.jpg', 217000, 'http://ws.stream.qqmusic.qq.com/C400001jxcf50Qv2xE.m4a?guid=897197879&vkey=313C379CE347A00150C87236D68D26277FD43A873FB8430DCDF3302A6F35C5490C53ECA613DE4023760E91279444472CC669B630A0203C1D&uin=&fromtag=120032', 0, 0, 1, 0);
INSERT INTO `song` VALUES (28, 'Giants', 'songyuqi01.jpg', 211000, 'http://ws.stream.qqmusic.qq.com/C400001iWP1j3n8GsC.m4a?guid=805453566&vkey=085160EAA3C159FB8C6B40723239CAE73F892BCE813337CA6C7CD074F63ECEB37004E70D7F67A18716267EFF210023FCC0E1F5989A5AFDC4&uin=&fromtag=120032', 0, 0, 1, 0);
INSERT INTO `song` VALUES (29, '青春纪念册', 'jiaomaiqi01.jpg', 233000, 'http://music.163.com/song/media/outer/url?id=2031098403.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (30, '我的名字', 'jiaomaiqi02.jpg', 251000, 'http://music.163.com/song/media/outer/url?id=554241732.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (33, '今天也想见到你', 'jiaomaiqi02.jpg', 240000, 'http://music.163.com/song/media/outer/url?id=1353194608.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (34, '你曾是少年(Live)', 'jiaomaiqi03.jpg', 147000, 'https://cc-sycdn.kuwo.cn/f8efa52462248c0f1107d042393408ae/6422c62a/resource/n2/92/16/2713996887.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (35, '哗啦啦少年再见', 'jiaomaiqi02.jpg', 231000, 'http://music.163.com/song/media/outer/url?id=1323301155.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (36, '你要的爱', 'jiaomaiqi04.jpg', 254000, 'http://music.163.com/song/media/outer/url?id=1446828582.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (37, '少年行', 'jiaomaiqi05.jpg', 215000, 'http://music.163.com/song/media/outer/url?id=1306496744.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (38, '只身', 'jiaomaiqi06.jpg', 208000, 'http://music.163.com/song/media/outer/url?id=1845460977.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (39, '暑夏', 'jiaomaiqi07.jpg', 212000, 'http://music.163.com/song/media/outer/url?id=1961500032.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (40, '多远都要在一起(Live)', 'dengziqi01.jpg', 187000, 'https://np01-sycdn.kuwo.cn/6e533e1e684c7a4e2cf1e5d7617ccb40/641ebe95/resource/n2/86/6/1187204220.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (41, '桃花诺', 'dengziqi02.jpg', 219000, 'http://music.163.com/song/media/outer/url?id=480579393.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (42, '泡沫', 'dengziqi03.jpg', 258000, 'http://music.163.com/song/media/outer/url?id=233931.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (43, '光年之外', 'dengziqi04.jpg', 235000, 'http://music.163.com/song/media/outer/url?id=449818741.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (44, '再见', 'dengziqi01.jpg', 206000, 'http://music.163.com/song/media/outer/url?id=36024806.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (45, '喜欢你', 'dengziqi05.jpg', 239000, 'http://music.163.com/song/media/outer/url?id=28949444.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (46, '来自天堂的魔鬼', 'dengziqi01.jpg', 245000, 'http://music.163.com/song/media/outer/url?id=36270426.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (47, '我的秘密', 'dengziqi06.jpg', 252000, 'http://music.163.com/song/media/outer/url?id=26117675.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (48, '画 (Live Piano Session II)', 'dengziqi07.jpg', 168000, 'http://music.163.com/song/media/outer/url?id=412911436.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (49, '句号 (Live)', 'dengziqi08.jpg', 233000, 'http://ws.stream.qqmusic.qq.com/C4000008GPu73DAwjr.m4a?guid=492905430&vkey=1DB9E5C9AC8C6D09ED22DE207508BCE26503B96C6D1F39F9E3817B9E0816AB55E2E1666121779436943BE9C715B9D288643123C5C3E7A245&uin=&fromtag=120032', 0, 0, 1, 0);
INSERT INTO `song` VALUES (50, '你把我灌醉', 'dengziqi09.jpg', 285000, 'http://music.163.com/song/media/outer/url?id=26550057.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (51, 'Where Did U Go', 'dengziqi01.jpg', 234000, 'http://music.163.com/song/media/outer/url?id=234064.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (52, '年少有为', 'lironghao01.jpg', 279000, 'http://music.163.com/song/media/outer/url?id=1293886117.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (53, '不将就', 'lironghao02.png', 313000, 'http://music.163.com/song/media/outer/url?id=31654343.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (54, '模特', 'lironghao03.jpg', 306000, 'http://music.163.com/song/media/outer/url?id=27731176.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (55, '老街', 'lironghao04.jpg', 318000, 'http://music.163.com/song/media/outer/url?id=133998.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (56, '麻雀', 'lironghao05.jpg', 252000, 'http://music.163.com/song/media/outer/url?id=1407551413.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (57, '李白', 'lironghao06.jpg', 273000, 'http://music.163.com/song/media/outer/url?id=27678655.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (58, '爸爸妈妈', 'lironghao07.jpg', 284000, 'http://music.163.com/song/media/outer/url?id=407450223.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (59, '戒烟', 'lironghao08.jpg', 293000, 'http://music.163.com/song/media/outer/url?id=519250023.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (60, 'DDU-DU DDU-DU', 'BLACKPINK01.jpg', 209000, 'http://music.163.com/song/media/outer/url?id=1325896374.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (61, 'Kill This Love', 'BLACKPINK02.png', 189000, 'http://music.163.com/song/media/outer/url?id=1397226690.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (62, 'BOOMBAYAH', 'BLACKPINK03.png', 240000, 'http://music.163.com/song/media/outer/url?id=1325711419.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (63, 'AS IF IT’S YOUR LAST', 'BLACKPINK04.jpg', 237000, 'http://music.163.com/song/media/outer/url?id=1325897389.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (64, 'WHISTLE', 'BLACKPINK05.jpg', 211000, 'http://music.163.com/song/media/outer/url?id=1325898350.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (65, 'AS IF IT’S YOUR LAST', 'BLACKPINK06.jpg', 212000, 'http://music.163.com/song/media/outer/url?id=1325898352.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (66, 'STAY', 'BLACKPINK07.jpg', 230000, 'http://music.163.com/song/media/outer/url?id=1325711421.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (67, 'Bonnie & Clyde', 'songyuqi02.jpg', 174000, 'http://music.163.com/song/media/outer/url?id=1844442265.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (68, 'Beggin’ (Live)', 'songyuqi03.jpg', 170000, 'http://music.163.com/song/media/outer/url?id=1983612906.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (69, 'Easy On Me (Cover)', 'songyuqi04.jpg', 218000, 'http://music.163.com/song/media/outer/url?id=1908081475.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (70, 'I Do', '91101.jpg', 207000, 'http://music.163.com/song/media/outer/url?id=28256115.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (71, 'Nothing Stops The Rain', '91102.jpg', 214000, 'http://music.163.com/song/media/outer/url?id=22533373.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (72, 'All I Want Is You', '91103.jpg', 250000, 'http://music.163.com/song/media/outer/url?id=22533365.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (73, 'The Day We Find Love', '91104.jpg', 292000, 'http://music.163.com/song/media/outer/url?id=22533296.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (74, 'A Night To Remember', '91105.jpg', 240000, 'http://music.163.com/song/media/outer/url?id=22533298.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (75, 'Moving On', '91106.jpg', 204000, 'http://music.163.com/song/media/outer/url?id=22533366.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (76, '兰亭序 （Live）', 'zhoushen01.jpg', 248000, 'http://music.163.com/song/media/outer/url?id=1974403336.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (77, '大鱼（Live）', 'zhoushen02.jpg', 226000, 'http://music.163.com/song/media/outer/url?id=1491495234.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (79, '起风了', 'zhoushen03.jpg', 311000, 'http://music.163.com/song/media/outer/url?id=1475596788.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (80, '化身孤岛的鲸', 'zhoushen04.jpg', 265000, 'http://music.163.com/song/media/outer/url?id=1465313631.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (81, '亲爱的旅人啊', 'zhoushen05.jpg', 244000, 'http://music.163.com/song/media/outer/url?id=1371939273.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (82, '光亮', 'zhoushen06.jpg', 288000, 'http://music.163.com/song/media/outer/url?id=1899994040.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (83, '望', 'zhoushen07.jpg', 250000, 'http://music.163.com/song/media/outer/url?id=1814080666.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (84, '漂洋过海来看你', 'zhoushen08.jpg', 183000, 'http://music.163.com/song/media/outer/url?id=30903117.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (85, '匆匆那年', 'zhoushen09.jpg', 240000, 'http://music.163.com/song/media/outer/url?id=29759371.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (86, '芒种', 'asheep01.jpg', 216000, 'http://music.163.com/song/media/outer/url?id=1385822037.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (87, '何以歌', 'asheep01.jpg', 278000, 'http://music.163.com/song/media/outer/url?id=1317765468.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (88, '来不及告别', 'asheep01.jpg', 248000, 'http://music.163.com/song/media/outer/url?id=2012200258.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (89, 'K歌之王', 'xujingyun01.jpg', 248000, 'http://music.163.com/song/media/outer/url?id=865040705.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (90, '想见你想见你想见你 (Cover)', 'xujingyun02.jpg', 214000, 'http://music.163.com/song/media/outer/url?id=1473442003.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (91, '岁月如歌', 'xujingyun02.jpg', 232000, 'http://music.163.com/song/media/outer/url?id=27646992.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (92, '我们都错', 'xujingyun03.jpg', 260000, 'http://music.163.com/song/media/outer/url?id=1462352587.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (93, '情歌之后', 'xujingyun04.jpg', 241000, 'http://music.163.com/song/media/outer/url?id=1846532659.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (94, '红色高跟鞋 (《今晚唱饮歌2》version)', 'xujingyun02.jpg', 212000, 'http://music.163.com/song/media/outer/url?id=1897950002.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (95, '凄美地', 'guoding01.jpg', 250000, 'http://music.163.com/song/media/outer/url?id=436346833.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (96, '保留', 'guoding01.jpg', 270000, 'http://music.163.com/song/media/outer/url?id=442869203.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (97, '水星记', 'guoding01.jpg', 325000, 'http://music.163.com/song/media/outer/url?id=441491828.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (98, '我们俩', 'guoding02.jpg', 193000, 'http://music.163.com/song/media/outer/url?id=85571.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (99, '想着你', 'guoding01.jpg', 255000, 'http://music.163.com/song/media/outer/url?id=442867218.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (100, '每个眼神都只身荒野', 'guoding01.jpg', 205000, 'http://music.163.com/song/media/outer/url?id=442869204.mp3', 0, 0, 1, 0);
INSERT INTO `song` VALUES (101, '在云端', 'guoding01.jpg', 244000, 'http://music.163.com/song/media/outer/url?id=442869201.mp3', 0, 0, 1, 0);

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
INSERT INTO `song_tag` VALUES (29, 309, 1, 0);
INSERT INTO `song_tag` VALUES (29, 101, 1, 0);
INSERT INTO `song_tag` VALUES (29, 306, 1, 0);
INSERT INTO `song_tag` VALUES (29, 509, 1, 0);
INSERT INTO `song_tag` VALUES (29, 404, 1, 0);
INSERT INTO `song_tag` VALUES (29, 401, 1, 0);
INSERT INTO `song_tag` VALUES (29, 514, 1, 0);
INSERT INTO `song_tag` VALUES (30, 101, 1, 0);
INSERT INTO `song_tag` VALUES (30, 303, 1, 0);
INSERT INTO `song_tag` VALUES (30, 401, 1, 0);
INSERT INTO `song_tag` VALUES (30, 402, 1, 0);
INSERT INTO `song_tag` VALUES (30, 403, 1, 0);
INSERT INTO `song_tag` VALUES (30, 404, 1, 0);
INSERT INTO `song_tag` VALUES (30, 405, 1, 0);
INSERT INTO `song_tag` VALUES (30, 406, 1, 0);
INSERT INTO `song_tag` VALUES (30, 408, 1, 0);
INSERT INTO `song_tag` VALUES (30, 505, 1, 0);
INSERT INTO `song_tag` VALUES (33, 101, 1, 0);
INSERT INTO `song_tag` VALUES (33, 303, 1, 0);
INSERT INTO `song_tag` VALUES (33, 310, 1, 0);
INSERT INTO `song_tag` VALUES (33, 504, 1, 0);
INSERT INTO `song_tag` VALUES (33, 403, 1, 0);
INSERT INTO `song_tag` VALUES (33, 401, 1, 0);
INSERT INTO `song_tag` VALUES (33, 505, 1, 0);
INSERT INTO `song_tag` VALUES (33, 506, 1, 0);
INSERT INTO `song_tag` VALUES (34, 502, 1, 0);
INSERT INTO `song_tag` VALUES (34, 514, 1, 0);
INSERT INTO `song_tag` VALUES (34, 401, 1, 0);
INSERT INTO `song_tag` VALUES (34, 402, 1, 0);
INSERT INTO `song_tag` VALUES (34, 403, 1, 0);
INSERT INTO `song_tag` VALUES (34, 101, 1, 0);
INSERT INTO `song_tag` VALUES (34, 506, 1, 0);
INSERT INTO `song_tag` VALUES (34, 508, 1, 0);
INSERT INTO `song_tag` VALUES (34, 504, 1, 0);
INSERT INTO `song_tag` VALUES (35, 509, 1, 0);
INSERT INTO `song_tag` VALUES (35, 306, 1, 0);
INSERT INTO `song_tag` VALUES (35, 101, 1, 0);
INSERT INTO `song_tag` VALUES (35, 507, 1, 0);
INSERT INTO `song_tag` VALUES (35, 409, 1, 0);
INSERT INTO `song_tag` VALUES (35, 406, 1, 0);
INSERT INTO `song_tag` VALUES (36, 309, 1, 0);
INSERT INTO `song_tag` VALUES (36, 101, 1, 0);
INSERT INTO `song_tag` VALUES (36, 502, 1, 0);
INSERT INTO `song_tag` VALUES (36, 515, 1, 0);
INSERT INTO `song_tag` VALUES (36, 401, 1, 0);
INSERT INTO `song_tag` VALUES (36, 403, 1, 0);
INSERT INTO `song_tag` VALUES (36, 303, 1, 0);
INSERT INTO `song_tag` VALUES (36, 509, 1, 0);
INSERT INTO `song_tag` VALUES (36, 508, 1, 0);
INSERT INTO `song_tag` VALUES (36, 511, 1, 0);
INSERT INTO `song_tag` VALUES (37, 101, 1, 0);
INSERT INTO `song_tag` VALUES (37, 306, 1, 0);
INSERT INTO `song_tag` VALUES (37, 408, 1, 0);
INSERT INTO `song_tag` VALUES (37, 504, 1, 0);
INSERT INTO `song_tag` VALUES (37, 505, 1, 0);
INSERT INTO `song_tag` VALUES (37, 506, 1, 0);
INSERT INTO `song_tag` VALUES (37, 517, 1, 0);
INSERT INTO `song_tag` VALUES (37, 311, 1, 0);
INSERT INTO `song_tag` VALUES (37, 503, 1, 0);
INSERT INTO `song_tag` VALUES (37, 502, 1, 0);
INSERT INTO `song_tag` VALUES (37, 405, 1, 0);
INSERT INTO `song_tag` VALUES (37, 402, 1, 0);
INSERT INTO `song_tag` VALUES (37, 303, 1, 0);
INSERT INTO `song_tag` VALUES (38, 101, 1, 0);
INSERT INTO `song_tag` VALUES (38, 302, 1, 0);
INSERT INTO `song_tag` VALUES (38, 311, 1, 0);
INSERT INTO `song_tag` VALUES (38, 401, 1, 0);
INSERT INTO `song_tag` VALUES (38, 403, 1, 0);
INSERT INTO `song_tag` VALUES (38, 404, 1, 0);
INSERT INTO `song_tag` VALUES (38, 405, 1, 0);
INSERT INTO `song_tag` VALUES (38, 408, 1, 0);
INSERT INTO `song_tag` VALUES (38, 504, 1, 0);
INSERT INTO `song_tag` VALUES (38, 503, 1, 0);
INSERT INTO `song_tag` VALUES (38, 515, 1, 0);
INSERT INTO `song_tag` VALUES (38, 508, 1, 0);
INSERT INTO `song_tag` VALUES (39, 0, 1, 0);
INSERT INTO `song_tag` VALUES (39, 101, 1, 0);
INSERT INTO `song_tag` VALUES (39, 306, 1, 0);
INSERT INTO `song_tag` VALUES (39, 310, 1, 0);
INSERT INTO `song_tag` VALUES (39, 311, 1, 0);
INSERT INTO `song_tag` VALUES (39, 402, 1, 0);
INSERT INTO `song_tag` VALUES (39, 401, 1, 0);
INSERT INTO `song_tag` VALUES (39, 404, 1, 0);
INSERT INTO `song_tag` VALUES (39, 509, 1, 0);
INSERT INTO `song_tag` VALUES (39, 508, 1, 0);
INSERT INTO `song_tag` VALUES (39, 504, 1, 0);
INSERT INTO `song_tag` VALUES (39, 408, 1, 0);
INSERT INTO `song_tag` VALUES (39, 502, 1, 0);
INSERT INTO `song_tag` VALUES (39, 503, 1, 0);
INSERT INTO `song_tag` VALUES (39, 505, 1, 0);
INSERT INTO `song_tag` VALUES (40, 101, 1, 0);
INSERT INTO `song_tag` VALUES (40, 0, 1, 0);
INSERT INTO `song_tag` VALUES (40, 306, 1, 0);
INSERT INTO `song_tag` VALUES (40, 401, 1, 0);
INSERT INTO `song_tag` VALUES (40, 405, 1, 0);
INSERT INTO `song_tag` VALUES (40, 403, 1, 0);
INSERT INTO `song_tag` VALUES (40, 409, 1, 0);
INSERT INTO `song_tag` VALUES (40, 502, 1, 0);
INSERT INTO `song_tag` VALUES (40, 503, 1, 0);
INSERT INTO `song_tag` VALUES (40, 504, 1, 0);
INSERT INTO `song_tag` VALUES (40, 508, 1, 0);
INSERT INTO `song_tag` VALUES (40, 514, 1, 0);
INSERT INTO `song_tag` VALUES (40, 511, 1, 0);
INSERT INTO `song_tag` VALUES (40, 311, 1, 0);
INSERT INTO `song_tag` VALUES (40, 310, 1, 0);
INSERT INTO `song_tag` VALUES (41, 302, 1, 0);
INSERT INTO `song_tag` VALUES (41, 101, 1, 0);
INSERT INTO `song_tag` VALUES (41, 308, 1, 0);
INSERT INTO `song_tag` VALUES (41, 405, 1, 0);
INSERT INTO `song_tag` VALUES (41, 406, 1, 0);
INSERT INTO `song_tag` VALUES (41, 407, 1, 0);
INSERT INTO `song_tag` VALUES (41, 502, 1, 0);
INSERT INTO `song_tag` VALUES (41, 506, 1, 0);
INSERT INTO `song_tag` VALUES (41, 517, 1, 0);
INSERT INTO `song_tag` VALUES (41, 511, 1, 0);
INSERT INTO `song_tag` VALUES (42, 101, 1, 0);
INSERT INTO `song_tag` VALUES (42, 303, 1, 0);
INSERT INTO `song_tag` VALUES (42, 306, 1, 0);
INSERT INTO `song_tag` VALUES (42, 308, 1, 0);
INSERT INTO `song_tag` VALUES (42, 310, 1, 0);
INSERT INTO `song_tag` VALUES (42, 311, 1, 0);
INSERT INTO `song_tag` VALUES (42, 403, 1, 0);
INSERT INTO `song_tag` VALUES (42, 405, 1, 0);
INSERT INTO `song_tag` VALUES (42, 502, 1, 0);
INSERT INTO `song_tag` VALUES (42, 504, 1, 0);
INSERT INTO `song_tag` VALUES (42, 505, 1, 0);
INSERT INTO `song_tag` VALUES (42, 506, 1, 0);
INSERT INTO `song_tag` VALUES (42, 509, 1, 0);
INSERT INTO `song_tag` VALUES (42, 515, 1, 0);
INSERT INTO `song_tag` VALUES (43, 101, 1, 0);
INSERT INTO `song_tag` VALUES (43, 519, 1, 0);
INSERT INTO `song_tag` VALUES (43, 306, 1, 0);
INSERT INTO `song_tag` VALUES (43, 406, 1, 0);
INSERT INTO `song_tag` VALUES (43, 501, 1, 0);
INSERT INTO `song_tag` VALUES (43, 407, 1, 0);
INSERT INTO `song_tag` VALUES (43, 507, 1, 0);
INSERT INTO `song_tag` VALUES (43, 510, 1, 0);
INSERT INTO `song_tag` VALUES (43, 204, 1, 0);
INSERT INTO `song_tag` VALUES (44, 502, 1, 0);
INSERT INTO `song_tag` VALUES (44, 505, 1, 0);
INSERT INTO `song_tag` VALUES (44, 506, 1, 0);
INSERT INTO `song_tag` VALUES (44, 508, 1, 0);
INSERT INTO `song_tag` VALUES (44, 101, 1, 0);
INSERT INTO `song_tag` VALUES (44, 303, 1, 0);
INSERT INTO `song_tag` VALUES (44, 311, 1, 0);
INSERT INTO `song_tag` VALUES (44, 403, 1, 0);
INSERT INTO `song_tag` VALUES (44, 503, 1, 0);
INSERT INTO `song_tag` VALUES (44, 517, 1, 0);
INSERT INTO `song_tag` VALUES (45, 102, 1, 0);
INSERT INTO `song_tag` VALUES (45, 307, 1, 0);
INSERT INTO `song_tag` VALUES (45, 310, 1, 0);
INSERT INTO `song_tag` VALUES (45, 311, 1, 0);
INSERT INTO `song_tag` VALUES (45, 502, 1, 0);
INSERT INTO `song_tag` VALUES (45, 504, 1, 0);
INSERT INTO `song_tag` VALUES (45, 506, 1, 0);
INSERT INTO `song_tag` VALUES (45, 515, 1, 0);
INSERT INTO `song_tag` VALUES (45, 519, 1, 0);
INSERT INTO `song_tag` VALUES (45, 403, 1, 0);
INSERT INTO `song_tag` VALUES (45, 406, 1, 0);
INSERT INTO `song_tag` VALUES (45, 303, 1, 0);
INSERT INTO `song_tag` VALUES (46, 101, 1, 0);
INSERT INTO `song_tag` VALUES (46, 519, 1, 0);
INSERT INTO `song_tag` VALUES (46, 516, 1, 0);
INSERT INTO `song_tag` VALUES (46, 509, 1, 0);
INSERT INTO `song_tag` VALUES (46, 511, 1, 0);
INSERT INTO `song_tag` VALUES (46, 406, 1, 0);
INSERT INTO `song_tag` VALUES (46, 303, 1, 0);
INSERT INTO `song_tag` VALUES (46, 405, 1, 0);
INSERT INTO `song_tag` VALUES (46, 307, 1, 0);
INSERT INTO `song_tag` VALUES (46, 409, 1, 0);
INSERT INTO `song_tag` VALUES (46, 504, 1, 0);
INSERT INTO `song_tag` VALUES (46, 508, 1, 0);
INSERT INTO `song_tag` VALUES (46, 503, 1, 0);
INSERT INTO `song_tag` VALUES (46, 505, 1, 0);
INSERT INTO `song_tag` VALUES (46, 506, 1, 0);
INSERT INTO `song_tag` VALUES (47, 101, 1, 0);
INSERT INTO `song_tag` VALUES (47, 307, 1, 0);
INSERT INTO `song_tag` VALUES (47, 201, 1, 0);
INSERT INTO `song_tag` VALUES (47, 303, 1, 0);
INSERT INTO `song_tag` VALUES (47, 310, 1, 0);
INSERT INTO `song_tag` VALUES (47, 311, 1, 0);
INSERT INTO `song_tag` VALUES (47, 505, 1, 0);
INSERT INTO `song_tag` VALUES (47, 519, 1, 0);
INSERT INTO `song_tag` VALUES (47, 402, 1, 0);
INSERT INTO `song_tag` VALUES (47, 401, 1, 0);
INSERT INTO `song_tag` VALUES (47, 404, 1, 0);
INSERT INTO `song_tag` VALUES (47, 509, 1, 0);
INSERT INTO `song_tag` VALUES (47, 503, 1, 0);
INSERT INTO `song_tag` VALUES (47, 408, 1, 0);
INSERT INTO `song_tag` VALUES (47, 407, 1, 0);
INSERT INTO `song_tag` VALUES (48, 101, 1, 0);
INSERT INTO `song_tag` VALUES (48, 303, 1, 0);
INSERT INTO `song_tag` VALUES (48, 307, 1, 0);
INSERT INTO `song_tag` VALUES (48, 312, 1, 0);
INSERT INTO `song_tag` VALUES (48, 311, 1, 0);
INSERT INTO `song_tag` VALUES (48, 403, 1, 0);
INSERT INTO `song_tag` VALUES (48, 408, 1, 0);
INSERT INTO `song_tag` VALUES (48, 520, 1, 0);
INSERT INTO `song_tag` VALUES (48, 508, 1, 0);
INSERT INTO `song_tag` VALUES (48, 509, 1, 0);
INSERT INTO `song_tag` VALUES (48, 515, 1, 0);
INSERT INTO `song_tag` VALUES (49, 101, 1, 0);
INSERT INTO `song_tag` VALUES (49, 307, 1, 0);
INSERT INTO `song_tag` VALUES (49, 501, 1, 0);
INSERT INTO `song_tag` VALUES (49, 407, 1, 0);
INSERT INTO `song_tag` VALUES (49, 406, 1, 0);
INSERT INTO `song_tag` VALUES (49, 405, 1, 0);
INSERT INTO `song_tag` VALUES (49, 312, 1, 0);
INSERT INTO `song_tag` VALUES (49, 401, 1, 0);
INSERT INTO `song_tag` VALUES (49, 402, 1, 0);
INSERT INTO `song_tag` VALUES (49, 403, 1, 0);
INSERT INTO `song_tag` VALUES (49, 404, 1, 0);
INSERT INTO `song_tag` VALUES (49, 301, 1, 0);
INSERT INTO `song_tag` VALUES (49, 303, 1, 0);
INSERT INTO `song_tag` VALUES (49, 519, 1, 0);
INSERT INTO `song_tag` VALUES (49, 514, 1, 0);
INSERT INTO `song_tag` VALUES (50, 101, 1, 0);
INSERT INTO `song_tag` VALUES (50, 306, 1, 0);
INSERT INTO `song_tag` VALUES (50, 307, 1, 0);
INSERT INTO `song_tag` VALUES (50, 303, 1, 0);
INSERT INTO `song_tag` VALUES (50, 301, 1, 0);
INSERT INTO `song_tag` VALUES (50, 311, 1, 0);
INSERT INTO `song_tag` VALUES (50, 312, 1, 0);
INSERT INTO `song_tag` VALUES (50, 403, 1, 0);
INSERT INTO `song_tag` VALUES (50, 405, 1, 0);
INSERT INTO `song_tag` VALUES (50, 407, 1, 0);
INSERT INTO `song_tag` VALUES (50, 502, 1, 0);
INSERT INTO `song_tag` VALUES (50, 506, 1, 0);
INSERT INTO `song_tag` VALUES (50, 505, 1, 0);
INSERT INTO `song_tag` VALUES (50, 508, 1, 0);
INSERT INTO `song_tag` VALUES (50, 509, 1, 0);
INSERT INTO `song_tag` VALUES (50, 518, 1, 0);
INSERT INTO `song_tag` VALUES (50, 519, 1, 0);
INSERT INTO `song_tag` VALUES (51, 101, 1, 0);
INSERT INTO `song_tag` VALUES (51, 102, 1, 0);
INSERT INTO `song_tag` VALUES (51, 504, 1, 0);
INSERT INTO `song_tag` VALUES (51, 307, 1, 0);
INSERT INTO `song_tag` VALUES (51, 306, 1, 0);
INSERT INTO `song_tag` VALUES (51, 408, 1, 0);
INSERT INTO `song_tag` VALUES (51, 301, 1, 0);
INSERT INTO `song_tag` VALUES (51, 303, 1, 0);
INSERT INTO `song_tag` VALUES (51, 403, 1, 0);
INSERT INTO `song_tag` VALUES (51, 311, 1, 0);
INSERT INTO `song_tag` VALUES (51, 406, 1, 0);
INSERT INTO `song_tag` VALUES (51, 507, 1, 0);
INSERT INTO `song_tag` VALUES (51, 511, 1, 0);
INSERT INTO `song_tag` VALUES (51, 515, 1, 0);
INSERT INTO `song_tag` VALUES (52, 101, 1, 0);
INSERT INTO `song_tag` VALUES (52, 311, 1, 0);
INSERT INTO `song_tag` VALUES (52, 407, 1, 0);
INSERT INTO `song_tag` VALUES (52, 502, 1, 0);
INSERT INTO `song_tag` VALUES (52, 504, 1, 0);
INSERT INTO `song_tag` VALUES (52, 505, 1, 0);
INSERT INTO `song_tag` VALUES (52, 506, 1, 0);
INSERT INTO `song_tag` VALUES (52, 508, 1, 0);
INSERT INTO `song_tag` VALUES (52, 307, 1, 0);
INSERT INTO `song_tag` VALUES (52, 403, 1, 0);
INSERT INTO `song_tag` VALUES (52, 405, 1, 0);
INSERT INTO `song_tag` VALUES (52, 301, 1, 0);
INSERT INTO `song_tag` VALUES (53, 101, 1, 0);
INSERT INTO `song_tag` VALUES (53, 302, 1, 0);
INSERT INTO `song_tag` VALUES (53, 307, 1, 0);
INSERT INTO `song_tag` VALUES (53, 303, 1, 0);
INSERT INTO `song_tag` VALUES (53, 403, 1, 0);
INSERT INTO `song_tag` VALUES (53, 405, 1, 0);
INSERT INTO `song_tag` VALUES (53, 406, 1, 0);
INSERT INTO `song_tag` VALUES (53, 407, 1, 0);
INSERT INTO `song_tag` VALUES (53, 502, 1, 0);
INSERT INTO `song_tag` VALUES (53, 503, 1, 0);
INSERT INTO `song_tag` VALUES (53, 504, 1, 0);
INSERT INTO `song_tag` VALUES (53, 505, 1, 0);
INSERT INTO `song_tag` VALUES (53, 506, 1, 0);
INSERT INTO `song_tag` VALUES (53, 508, 1, 0);
INSERT INTO `song_tag` VALUES (53, 509, 1, 0);
INSERT INTO `song_tag` VALUES (53, 511, 1, 0);
INSERT INTO `song_tag` VALUES (53, 520, 1, 0);
INSERT INTO `song_tag` VALUES (53, 311, 1, 0);
INSERT INTO `song_tag` VALUES (54, 101, 1, 0);
INSERT INTO `song_tag` VALUES (54, 307, 1, 0);
INSERT INTO `song_tag` VALUES (54, 301, 1, 0);
INSERT INTO `song_tag` VALUES (54, 303, 1, 0);
INSERT INTO `song_tag` VALUES (54, 401, 1, 0);
INSERT INTO `song_tag` VALUES (54, 312, 1, 0);
INSERT INTO `song_tag` VALUES (54, 311, 1, 0);
INSERT INTO `song_tag` VALUES (54, 511, 1, 0);
INSERT INTO `song_tag` VALUES (54, 509, 1, 0);
INSERT INTO `song_tag` VALUES (54, 506, 1, 0);
INSERT INTO `song_tag` VALUES (54, 504, 1, 0);
INSERT INTO `song_tag` VALUES (54, 502, 1, 0);
INSERT INTO `song_tag` VALUES (54, 407, 1, 0);
INSERT INTO `song_tag` VALUES (54, 308, 1, 0);
INSERT INTO `song_tag` VALUES (55, 101, 1, 0);
INSERT INTO `song_tag` VALUES (55, 519, 1, 0);
INSERT INTO `song_tag` VALUES (55, 516, 1, 0);
INSERT INTO `song_tag` VALUES (55, 503, 1, 0);
INSERT INTO `song_tag` VALUES (55, 504, 1, 0);
INSERT INTO `song_tag` VALUES (55, 307, 1, 0);
INSERT INTO `song_tag` VALUES (55, 311, 1, 0);
INSERT INTO `song_tag` VALUES (55, 301, 1, 0);
INSERT INTO `song_tag` VALUES (55, 303, 1, 0);
INSERT INTO `song_tag` VALUES (55, 405, 1, 0);
INSERT INTO `song_tag` VALUES (55, 404, 1, 0);
INSERT INTO `song_tag` VALUES (55, 510, 1, 0);
INSERT INTO `song_tag` VALUES (55, 509, 1, 0);
INSERT INTO `song_tag` VALUES (56, 101, 1, 0);
INSERT INTO `song_tag` VALUES (56, 303, 1, 0);
INSERT INTO `song_tag` VALUES (56, 301, 1, 0);
INSERT INTO `song_tag` VALUES (56, 204, 1, 0);
INSERT INTO `song_tag` VALUES (56, 307, 1, 0);
INSERT INTO `song_tag` VALUES (56, 308, 1, 0);
INSERT INTO `song_tag` VALUES (56, 306, 1, 0);
INSERT INTO `song_tag` VALUES (56, 311, 1, 0);
INSERT INTO `song_tag` VALUES (56, 402, 1, 0);
INSERT INTO `song_tag` VALUES (56, 406, 1, 0);
INSERT INTO `song_tag` VALUES (56, 504, 1, 0);
INSERT INTO `song_tag` VALUES (56, 506, 1, 0);
INSERT INTO `song_tag` VALUES (56, 509, 1, 0);
INSERT INTO `song_tag` VALUES (56, 508, 1, 0);
INSERT INTO `song_tag` VALUES (56, 519, 1, 0);
INSERT INTO `song_tag` VALUES (56, 515, 1, 0);
INSERT INTO `song_tag` VALUES (57, 101, 1, 0);
INSERT INTO `song_tag` VALUES (57, 512, 1, 0);
INSERT INTO `song_tag` VALUES (57, 519, 1, 0);
INSERT INTO `song_tag` VALUES (57, 517, 1, 0);
INSERT INTO `song_tag` VALUES (57, 509, 1, 0);
INSERT INTO `song_tag` VALUES (57, 307, 1, 0);
INSERT INTO `song_tag` VALUES (57, 407, 1, 0);
INSERT INTO `song_tag` VALUES (57, 401, 1, 0);
INSERT INTO `song_tag` VALUES (57, 402, 1, 0);
INSERT INTO `song_tag` VALUES (57, 303, 1, 0);
INSERT INTO `song_tag` VALUES (57, 304, 1, 0);
INSERT INTO `song_tag` VALUES (57, 404, 1, 0);
INSERT INTO `song_tag` VALUES (57, 403, 1, 0);
INSERT INTO `song_tag` VALUES (57, 406, 1, 0);
INSERT INTO `song_tag` VALUES (57, 405, 1, 0);
INSERT INTO `song_tag` VALUES (58, 101, 1, 0);
INSERT INTO `song_tag` VALUES (58, 307, 1, 0);
INSERT INTO `song_tag` VALUES (58, 303, 1, 0);
INSERT INTO `song_tag` VALUES (58, 401, 1, 0);
INSERT INTO `song_tag` VALUES (58, 403, 1, 0);
INSERT INTO `song_tag` VALUES (58, 405, 1, 0);
INSERT INTO `song_tag` VALUES (58, 504, 1, 0);
INSERT INTO `song_tag` VALUES (58, 520, 1, 0);
INSERT INTO `song_tag` VALUES (58, 514, 1, 0);
INSERT INTO `song_tag` VALUES (58, 509, 1, 0);
INSERT INTO `song_tag` VALUES (58, 505, 1, 0);
INSERT INTO `song_tag` VALUES (59, 101, 1, 0);
INSERT INTO `song_tag` VALUES (59, 307, 1, 0);
INSERT INTO `song_tag` VALUES (59, 303, 1, 0);
INSERT INTO `song_tag` VALUES (59, 304, 1, 0);
INSERT INTO `song_tag` VALUES (59, 308, 1, 0);
INSERT INTO `song_tag` VALUES (59, 404, 1, 0);
INSERT INTO `song_tag` VALUES (59, 312, 1, 0);
INSERT INTO `song_tag` VALUES (59, 311, 1, 0);
INSERT INTO `song_tag` VALUES (59, 406, 1, 0);
INSERT INTO `song_tag` VALUES (59, 502, 1, 0);
INSERT INTO `song_tag` VALUES (59, 506, 1, 0);
INSERT INTO `song_tag` VALUES (59, 508, 1, 0);
INSERT INTO `song_tag` VALUES (59, 520, 1, 0);
INSERT INTO `song_tag` VALUES (59, 509, 1, 0);
INSERT INTO `song_tag` VALUES (60, 104, 1, 0);
INSERT INTO `song_tag` VALUES (60, 513, 1, 0);
INSERT INTO `song_tag` VALUES (60, 511, 1, 0);
INSERT INTO `song_tag` VALUES (60, 519, 1, 0);
INSERT INTO `song_tag` VALUES (60, 510, 1, 0);
INSERT INTO `song_tag` VALUES (60, 509, 1, 0);
INSERT INTO `song_tag` VALUES (60, 406, 1, 0);
INSERT INTO `song_tag` VALUES (60, 404, 1, 0);
INSERT INTO `song_tag` VALUES (60, 306, 1, 0);
INSERT INTO `song_tag` VALUES (60, 409, 1, 0);
INSERT INTO `song_tag` VALUES (60, 501, 1, 0);
INSERT INTO `song_tag` VALUES (61, 104, 1, 0);
INSERT INTO `song_tag` VALUES (61, 304, 1, 0);
INSERT INTO `song_tag` VALUES (61, 306, 1, 0);
INSERT INTO `song_tag` VALUES (61, 310, 1, 0);
INSERT INTO `song_tag` VALUES (61, 402, 1, 0);
INSERT INTO `song_tag` VALUES (61, 406, 1, 0);
INSERT INTO `song_tag` VALUES (61, 409, 1, 0);
INSERT INTO `song_tag` VALUES (61, 511, 1, 0);
INSERT INTO `song_tag` VALUES (61, 519, 1, 0);
INSERT INTO `song_tag` VALUES (61, 507, 1, 0);
INSERT INTO `song_tag` VALUES (61, 516, 1, 0);
INSERT INTO `song_tag` VALUES (62, 104, 1, 0);
INSERT INTO `song_tag` VALUES (62, 304, 1, 0);
INSERT INTO `song_tag` VALUES (62, 303, 1, 0);
INSERT INTO `song_tag` VALUES (62, 306, 1, 0);
INSERT INTO `song_tag` VALUES (62, 310, 1, 0);
INSERT INTO `song_tag` VALUES (62, 402, 1, 0);
INSERT INTO `song_tag` VALUES (62, 404, 1, 0);
INSERT INTO `song_tag` VALUES (62, 406, 1, 0);
INSERT INTO `song_tag` VALUES (62, 409, 1, 0);
INSERT INTO `song_tag` VALUES (62, 501, 1, 0);
INSERT INTO `song_tag` VALUES (62, 510, 1, 0);
INSERT INTO `song_tag` VALUES (62, 511, 1, 0);
INSERT INTO `song_tag` VALUES (62, 513, 1, 0);
INSERT INTO `song_tag` VALUES (62, 516, 1, 0);
INSERT INTO `song_tag` VALUES (63, 104, 1, 0);
INSERT INTO `song_tag` VALUES (63, 513, 1, 0);
INSERT INTO `song_tag` VALUES (63, 406, 1, 0);
INSERT INTO `song_tag` VALUES (63, 304, 1, 0);
INSERT INTO `song_tag` VALUES (63, 303, 1, 0);
INSERT INTO `song_tag` VALUES (63, 306, 1, 0);
INSERT INTO `song_tag` VALUES (63, 307, 1, 0);
INSERT INTO `song_tag` VALUES (63, 402, 1, 0);
INSERT INTO `song_tag` VALUES (63, 404, 1, 0);
INSERT INTO `song_tag` VALUES (63, 405, 1, 0);
INSERT INTO `song_tag` VALUES (63, 407, 1, 0);
INSERT INTO `song_tag` VALUES (63, 409, 1, 0);
INSERT INTO `song_tag` VALUES (63, 501, 1, 0);
INSERT INTO `song_tag` VALUES (63, 507, 1, 0);
INSERT INTO `song_tag` VALUES (63, 518, 1, 0);
INSERT INTO `song_tag` VALUES (63, 519, 1, 0);
INSERT INTO `song_tag` VALUES (63, 516, 1, 0);
INSERT INTO `song_tag` VALUES (64, 104, 1, 0);
INSERT INTO `song_tag` VALUES (64, 303, 1, 0);
INSERT INTO `song_tag` VALUES (64, 306, 1, 0);
INSERT INTO `song_tag` VALUES (64, 311, 1, 0);
INSERT INTO `song_tag` VALUES (64, 404, 1, 0);
INSERT INTO `song_tag` VALUES (64, 401, 1, 0);
INSERT INTO `song_tag` VALUES (64, 402, 1, 0);
INSERT INTO `song_tag` VALUES (64, 405, 1, 0);
INSERT INTO `song_tag` VALUES (64, 406, 1, 0);
INSERT INTO `song_tag` VALUES (64, 407, 1, 0);
INSERT INTO `song_tag` VALUES (64, 409, 1, 0);
INSERT INTO `song_tag` VALUES (64, 501, 1, 0);
INSERT INTO `song_tag` VALUES (64, 519, 1, 0);
INSERT INTO `song_tag` VALUES (64, 518, 1, 0);
INSERT INTO `song_tag` VALUES (64, 516, 1, 0);
INSERT INTO `song_tag` VALUES (64, 511, 1, 0);
INSERT INTO `song_tag` VALUES (64, 513, 1, 0);
INSERT INTO `song_tag` VALUES (64, 507, 1, 0);
INSERT INTO `song_tag` VALUES (65, 104, 1, 0);
INSERT INTO `song_tag` VALUES (65, 513, 1, 0);
INSERT INTO `song_tag` VALUES (65, 304, 1, 0);
INSERT INTO `song_tag` VALUES (65, 303, 1, 0);
INSERT INTO `song_tag` VALUES (65, 406, 1, 0);
INSERT INTO `song_tag` VALUES (65, 405, 1, 0);
INSERT INTO `song_tag` VALUES (65, 404, 1, 0);
INSERT INTO `song_tag` VALUES (65, 402, 1, 0);
INSERT INTO `song_tag` VALUES (65, 401, 1, 0);
INSERT INTO `song_tag` VALUES (65, 306, 1, 0);
INSERT INTO `song_tag` VALUES (65, 407, 1, 0);
INSERT INTO `song_tag` VALUES (65, 501, 1, 0);
INSERT INTO `song_tag` VALUES (65, 408, 1, 0);
INSERT INTO `song_tag` VALUES (65, 409, 1, 0);
INSERT INTO `song_tag` VALUES (65, 515, 1, 0);
INSERT INTO `song_tag` VALUES (65, 516, 1, 0);
INSERT INTO `song_tag` VALUES (65, 519, 1, 0);
INSERT INTO `song_tag` VALUES (65, 520, 1, 0);
INSERT INTO `song_tag` VALUES (65, 511, 1, 0);
INSERT INTO `song_tag` VALUES (65, 509, 1, 0);
INSERT INTO `song_tag` VALUES (65, 507, 1, 0);
INSERT INTO `song_tag` VALUES (65, 311, 1, 0);
INSERT INTO `song_tag` VALUES (66, 104, 1, 0);
INSERT INTO `song_tag` VALUES (66, 513, 1, 0);
INSERT INTO `song_tag` VALUES (66, 304, 1, 0);
INSERT INTO `song_tag` VALUES (66, 303, 1, 0);
INSERT INTO `song_tag` VALUES (66, 402, 1, 0);
INSERT INTO `song_tag` VALUES (66, 401, 1, 0);
INSERT INTO `song_tag` VALUES (66, 404, 1, 0);
INSERT INTO `song_tag` VALUES (66, 406, 1, 0);
INSERT INTO `song_tag` VALUES (66, 407, 1, 0);
INSERT INTO `song_tag` VALUES (66, 409, 1, 0);
INSERT INTO `song_tag` VALUES (66, 501, 1, 0);
INSERT INTO `song_tag` VALUES (66, 504, 1, 0);
INSERT INTO `song_tag` VALUES (66, 507, 1, 0);
INSERT INTO `song_tag` VALUES (66, 509, 1, 0);
INSERT INTO `song_tag` VALUES (66, 511, 1, 0);
INSERT INTO `song_tag` VALUES (66, 512, 1, 0);
INSERT INTO `song_tag` VALUES (66, 514, 1, 0);
INSERT INTO `song_tag` VALUES (66, 515, 1, 0);
INSERT INTO `song_tag` VALUES (66, 516, 1, 0);
INSERT INTO `song_tag` VALUES (66, 519, 1, 0);
INSERT INTO `song_tag` VALUES (66, 520, 1, 0);
INSERT INTO `song_tag` VALUES (67, 103, 1, 0);
INSERT INTO `song_tag` VALUES (67, 309, 1, 0);
INSERT INTO `song_tag` VALUES (67, 304, 1, 0);
INSERT INTO `song_tag` VALUES (67, 303, 1, 0);
INSERT INTO `song_tag` VALUES (67, 404, 1, 0);
INSERT INTO `song_tag` VALUES (67, 406, 1, 0);
INSERT INTO `song_tag` VALUES (67, 408, 1, 0);
INSERT INTO `song_tag` VALUES (67, 409, 1, 0);
INSERT INTO `song_tag` VALUES (67, 509, 1, 0);
INSERT INTO `song_tag` VALUES (67, 519, 1, 0);
INSERT INTO `song_tag` VALUES (67, 516, 1, 0);
INSERT INTO `song_tag` VALUES (68, 309, 1, 0);
INSERT INTO `song_tag` VALUES (68, 103, 1, 0);
INSERT INTO `song_tag` VALUES (68, 303, 1, 0);
INSERT INTO `song_tag` VALUES (68, 304, 1, 0);
INSERT INTO `song_tag` VALUES (68, 402, 1, 0);
INSERT INTO `song_tag` VALUES (68, 404, 1, 0);
INSERT INTO `song_tag` VALUES (68, 405, 1, 0);
INSERT INTO `song_tag` VALUES (68, 406, 1, 0);
INSERT INTO `song_tag` VALUES (68, 407, 1, 0);
INSERT INTO `song_tag` VALUES (68, 408, 1, 0);
INSERT INTO `song_tag` VALUES (68, 409, 1, 0);
INSERT INTO `song_tag` VALUES (68, 501, 1, 0);
INSERT INTO `song_tag` VALUES (68, 520, 1, 0);
INSERT INTO `song_tag` VALUES (68, 519, 1, 0);
INSERT INTO `song_tag` VALUES (68, 516, 1, 0);
INSERT INTO `song_tag` VALUES (68, 514, 1, 0);
INSERT INTO `song_tag` VALUES (68, 509, 1, 0);
INSERT INTO `song_tag` VALUES (69, 103, 1, 0);
INSERT INTO `song_tag` VALUES (69, 303, 1, 0);
INSERT INTO `song_tag` VALUES (69, 309, 1, 0);
INSERT INTO `song_tag` VALUES (69, 401, 1, 0);
INSERT INTO `song_tag` VALUES (69, 403, 1, 0);
INSERT INTO `song_tag` VALUES (69, 405, 1, 0);
INSERT INTO `song_tag` VALUES (69, 408, 1, 0);
INSERT INTO `song_tag` VALUES (69, 505, 1, 0);
INSERT INTO `song_tag` VALUES (69, 514, 1, 0);
INSERT INTO `song_tag` VALUES (69, 520, 1, 0);
INSERT INTO `song_tag` VALUES (69, 515, 1, 0);
INSERT INTO `song_tag` VALUES (70, 103, 1, 0);
INSERT INTO `song_tag` VALUES (70, 303, 1, 0);
INSERT INTO `song_tag` VALUES (70, 311, 1, 0);
INSERT INTO `song_tag` VALUES (70, 520, 1, 0);
INSERT INTO `song_tag` VALUES (70, 501, 1, 0);
INSERT INTO `song_tag` VALUES (70, 408, 1, 0);
INSERT INTO `song_tag` VALUES (70, 515, 1, 0);
INSERT INTO `song_tag` VALUES (70, 402, 1, 0);
INSERT INTO `song_tag` VALUES (70, 404, 1, 0);
INSERT INTO `song_tag` VALUES (71, 103, 1, 0);
INSERT INTO `song_tag` VALUES (71, 520, 1, 0);
INSERT INTO `song_tag` VALUES (71, 406, 1, 0);
INSERT INTO `song_tag` VALUES (71, 304, 1, 0);
INSERT INTO `song_tag` VALUES (71, 409, 1, 0);
INSERT INTO `song_tag` VALUES (71, 501, 1, 0);
INSERT INTO `song_tag` VALUES (71, 516, 1, 0);
INSERT INTO `song_tag` VALUES (71, 519, 1, 0);
INSERT INTO `song_tag` VALUES (71, 507, 1, 0);
INSERT INTO `song_tag` VALUES (71, 404, 1, 0);
INSERT INTO `song_tag` VALUES (71, 405, 1, 0);
INSERT INTO `song_tag` VALUES (71, 407, 1, 0);
INSERT INTO `song_tag` VALUES (72, 303, 1, 0);
INSERT INTO `song_tag` VALUES (72, 103, 1, 0);
INSERT INTO `song_tag` VALUES (72, 311, 1, 0);
INSERT INTO `song_tag` VALUES (72, 408, 1, 0);
INSERT INTO `song_tag` VALUES (72, 401, 1, 0);
INSERT INTO `song_tag` VALUES (72, 505, 1, 0);
INSERT INTO `song_tag` VALUES (72, 520, 1, 0);
INSERT INTO `song_tag` VALUES (72, 515, 1, 0);
INSERT INTO `song_tag` VALUES (72, 508, 1, 0);
INSERT INTO `song_tag` VALUES (72, 403, 1, 0);
INSERT INTO `song_tag` VALUES (72, 501, 1, 0);
INSERT INTO `song_tag` VALUES (73, 103, 1, 0);
INSERT INTO `song_tag` VALUES (73, 408, 1, 0);
INSERT INTO `song_tag` VALUES (73, 407, 1, 0);
INSERT INTO `song_tag` VALUES (73, 306, 1, 0);
INSERT INTO `song_tag` VALUES (73, 311, 1, 0);
INSERT INTO `song_tag` VALUES (73, 312, 1, 0);
INSERT INTO `song_tag` VALUES (73, 401, 1, 0);
INSERT INTO `song_tag` VALUES (73, 403, 1, 0);
INSERT INTO `song_tag` VALUES (73, 405, 1, 0);
INSERT INTO `song_tag` VALUES (73, 504, 1, 0);
INSERT INTO `song_tag` VALUES (73, 509, 1, 0);
INSERT INTO `song_tag` VALUES (73, 520, 1, 0);
INSERT INTO `song_tag` VALUES (73, 519, 1, 0);
INSERT INTO `song_tag` VALUES (73, 514, 1, 0);
INSERT INTO `song_tag` VALUES (73, 503, 1, 0);
INSERT INTO `song_tag` VALUES (74, 510, 1, 0);
INSERT INTO `song_tag` VALUES (74, 519, 1, 0);
INSERT INTO `song_tag` VALUES (74, 515, 1, 0);
INSERT INTO `song_tag` VALUES (74, 507, 1, 0);
INSERT INTO `song_tag` VALUES (74, 407, 1, 0);
INSERT INTO `song_tag` VALUES (74, 408, 1, 0);
INSERT INTO `song_tag` VALUES (74, 409, 1, 0);
INSERT INTO `song_tag` VALUES (74, 501, 1, 0);
INSERT INTO `song_tag` VALUES (74, 306, 1, 0);
INSERT INTO `song_tag` VALUES (74, 103, 1, 0);
INSERT INTO `song_tag` VALUES (74, 311, 1, 0);
INSERT INTO `song_tag` VALUES (74, 404, 1, 0);
INSERT INTO `song_tag` VALUES (74, 405, 1, 0);
INSERT INTO `song_tag` VALUES (74, 406, 1, 0);
INSERT INTO `song_tag` VALUES (75, 103, 1, 0);
INSERT INTO `song_tag` VALUES (75, 510, 1, 0);
INSERT INTO `song_tag` VALUES (75, 520, 1, 0);
INSERT INTO `song_tag` VALUES (75, 519, 1, 0);
INSERT INTO `song_tag` VALUES (75, 516, 1, 0);
INSERT INTO `song_tag` VALUES (75, 409, 1, 0);
INSERT INTO `song_tag` VALUES (75, 501, 1, 0);
INSERT INTO `song_tag` VALUES (75, 311, 1, 0);
INSERT INTO `song_tag` VALUES (75, 404, 1, 0);
INSERT INTO `song_tag` VALUES (75, 406, 1, 0);
INSERT INTO `song_tag` VALUES (75, 306, 1, 0);
INSERT INTO `song_tag` VALUES (75, 407, 1, 0);
INSERT INTO `song_tag` VALUES (76, 101, 1, 0);
INSERT INTO `song_tag` VALUES (76, 511, 1, 0);
INSERT INTO `song_tag` VALUES (76, 514, 1, 0);
INSERT INTO `song_tag` VALUES (76, 517, 1, 0);
INSERT INTO `song_tag` VALUES (76, 515, 1, 0);
INSERT INTO `song_tag` VALUES (76, 520, 1, 0);
INSERT INTO `song_tag` VALUES (76, 506, 1, 0);
INSERT INTO `song_tag` VALUES (76, 504, 1, 0);
INSERT INTO `song_tag` VALUES (76, 502, 1, 0);
INSERT INTO `song_tag` VALUES (76, 401, 1, 0);
INSERT INTO `song_tag` VALUES (76, 403, 1, 0);
INSERT INTO `song_tag` VALUES (76, 405, 1, 0);
INSERT INTO `song_tag` VALUES (76, 309, 1, 0);
INSERT INTO `song_tag` VALUES (76, 303, 1, 0);
INSERT INTO `song_tag` VALUES (76, 306, 1, 0);
INSERT INTO `song_tag` VALUES (77, 101, 1, 0);
INSERT INTO `song_tag` VALUES (77, 302, 1, 0);
INSERT INTO `song_tag` VALUES (77, 301, 1, 0);
INSERT INTO `song_tag` VALUES (77, 511, 1, 0);
INSERT INTO `song_tag` VALUES (77, 520, 1, 0);
INSERT INTO `song_tag` VALUES (77, 517, 1, 0);
INSERT INTO `song_tag` VALUES (77, 515, 1, 0);
INSERT INTO `song_tag` VALUES (77, 514, 1, 0);
INSERT INTO `song_tag` VALUES (77, 504, 1, 0);
INSERT INTO `song_tag` VALUES (77, 506, 1, 0);
INSERT INTO `song_tag` VALUES (77, 502, 1, 0);
INSERT INTO `song_tag` VALUES (77, 306, 1, 0);
INSERT INTO `song_tag` VALUES (77, 401, 1, 0);
INSERT INTO `song_tag` VALUES (77, 403, 1, 0);
INSERT INTO `song_tag` VALUES (79, 101, 1, 0);
INSERT INTO `song_tag` VALUES (79, 303, 1, 0);
INSERT INTO `song_tag` VALUES (79, 302, 1, 0);
INSERT INTO `song_tag` VALUES (79, 307, 1, 0);
INSERT INTO `song_tag` VALUES (79, 310, 1, 0);
INSERT INTO `song_tag` VALUES (79, 311, 1, 0);
INSERT INTO `song_tag` VALUES (79, 401, 1, 0);
INSERT INTO `song_tag` VALUES (79, 403, 1, 0);
INSERT INTO `song_tag` VALUES (79, 404, 1, 0);
INSERT INTO `song_tag` VALUES (79, 405, 1, 0);
INSERT INTO `song_tag` VALUES (79, 407, 1, 0);
INSERT INTO `song_tag` VALUES (79, 502, 1, 0);
INSERT INTO `song_tag` VALUES (79, 504, 1, 0);
INSERT INTO `song_tag` VALUES (79, 506, 1, 0);
INSERT INTO `song_tag` VALUES (79, 520, 1, 0);
INSERT INTO `song_tag` VALUES (79, 517, 1, 0);
INSERT INTO `song_tag` VALUES (79, 409, 1, 0);
INSERT INTO `song_tag` VALUES (80, 101, 1, 0);
INSERT INTO `song_tag` VALUES (80, 520, 1, 0);
INSERT INTO `song_tag` VALUES (80, 517, 1, 0);
INSERT INTO `song_tag` VALUES (80, 515, 1, 0);
INSERT INTO `song_tag` VALUES (80, 502, 1, 0);
INSERT INTO `song_tag` VALUES (80, 401, 1, 0);
INSERT INTO `song_tag` VALUES (80, 403, 1, 0);
INSERT INTO `song_tag` VALUES (80, 405, 1, 0);
INSERT INTO `song_tag` VALUES (80, 404, 1, 0);
INSERT INTO `song_tag` VALUES (80, 306, 1, 0);
INSERT INTO `song_tag` VALUES (80, 307, 1, 0);
INSERT INTO `song_tag` VALUES (80, 303, 1, 0);
INSERT INTO `song_tag` VALUES (80, 509, 1, 0);
INSERT INTO `song_tag` VALUES (80, 504, 1, 0);
INSERT INTO `song_tag` VALUES (81, 302, 1, 0);
INSERT INTO `song_tag` VALUES (81, 303, 1, 0);
INSERT INTO `song_tag` VALUES (81, 101, 1, 0);
INSERT INTO `song_tag` VALUES (81, 312, 1, 0);
INSERT INTO `song_tag` VALUES (81, 401, 1, 0);
INSERT INTO `song_tag` VALUES (81, 403, 1, 0);
INSERT INTO `song_tag` VALUES (81, 405, 1, 0);
INSERT INTO `song_tag` VALUES (81, 407, 1, 0);
INSERT INTO `song_tag` VALUES (81, 515, 1, 0);
INSERT INTO `song_tag` VALUES (81, 517, 1, 0);
INSERT INTO `song_tag` VALUES (81, 514, 1, 0);
INSERT INTO `song_tag` VALUES (81, 511, 1, 0);
INSERT INTO `song_tag` VALUES (81, 505, 1, 0);
INSERT INTO `song_tag` VALUES (81, 408, 1, 0);
INSERT INTO `song_tag` VALUES (82, 101, 1, 0);
INSERT INTO `song_tag` VALUES (82, 302, 1, 0);
INSERT INTO `song_tag` VALUES (82, 303, 1, 0);
INSERT INTO `song_tag` VALUES (82, 514, 1, 0);
INSERT INTO `song_tag` VALUES (82, 520, 1, 0);
INSERT INTO `song_tag` VALUES (82, 515, 1, 0);
INSERT INTO `song_tag` VALUES (82, 511, 1, 0);
INSERT INTO `song_tag` VALUES (82, 509, 1, 0);
INSERT INTO `song_tag` VALUES (82, 508, 1, 0);
INSERT INTO `song_tag` VALUES (82, 505, 1, 0);
INSERT INTO `song_tag` VALUES (82, 504, 1, 0);
INSERT INTO `song_tag` VALUES (82, 403, 1, 0);
INSERT INTO `song_tag` VALUES (82, 401, 1, 0);
INSERT INTO `song_tag` VALUES (82, 312, 1, 0);
INSERT INTO `song_tag` VALUES (82, 306, 1, 0);
INSERT INTO `song_tag` VALUES (82, 407, 1, 0);
INSERT INTO `song_tag` VALUES (82, 502, 1, 0);
INSERT INTO `song_tag` VALUES (83, 101, 1, 0);
INSERT INTO `song_tag` VALUES (83, 302, 1, 0);
INSERT INTO `song_tag` VALUES (83, 301, 1, 0);
INSERT INTO `song_tag` VALUES (83, 303, 1, 0);
INSERT INTO `song_tag` VALUES (83, 307, 1, 0);
INSERT INTO `song_tag` VALUES (83, 310, 1, 0);
INSERT INTO `song_tag` VALUES (83, 311, 1, 0);
INSERT INTO `song_tag` VALUES (83, 401, 1, 0);
INSERT INTO `song_tag` VALUES (83, 403, 1, 0);
INSERT INTO `song_tag` VALUES (83, 404, 1, 0);
INSERT INTO `song_tag` VALUES (83, 407, 1, 0);
INSERT INTO `song_tag` VALUES (83, 502, 1, 0);
INSERT INTO `song_tag` VALUES (83, 504, 1, 0);
INSERT INTO `song_tag` VALUES (83, 505, 1, 0);
INSERT INTO `song_tag` VALUES (83, 509, 1, 0);
INSERT INTO `song_tag` VALUES (83, 520, 1, 0);
INSERT INTO `song_tag` VALUES (83, 515, 1, 0);
INSERT INTO `song_tag` VALUES (84, 302, 1, 0);
INSERT INTO `song_tag` VALUES (84, 403, 1, 0);
INSERT INTO `song_tag` VALUES (84, 404, 1, 0);
INSERT INTO `song_tag` VALUES (84, 405, 1, 0);
INSERT INTO `song_tag` VALUES (84, 407, 1, 0);
INSERT INTO `song_tag` VALUES (84, 502, 1, 0);
INSERT INTO `song_tag` VALUES (84, 506, 1, 0);
INSERT INTO `song_tag` VALUES (85, 312, 1, 0);
INSERT INTO `song_tag` VALUES (85, 403, 1, 0);
INSERT INTO `song_tag` VALUES (85, 401, 1, 0);
INSERT INTO `song_tag` VALUES (85, 405, 1, 0);
INSERT INTO `song_tag` VALUES (85, 502, 1, 0);
INSERT INTO `song_tag` VALUES (85, 504, 1, 0);
INSERT INTO `song_tag` VALUES (85, 506, 1, 0);
INSERT INTO `song_tag` VALUES (85, 101, 1, 0);
INSERT INTO `song_tag` VALUES (86, 309, 1, 0);
INSERT INTO `song_tag` VALUES (86, 101, 1, 0);
INSERT INTO `song_tag` VALUES (86, 511, 1, 0);
INSERT INTO `song_tag` VALUES (86, 409, 1, 0);
INSERT INTO `song_tag` VALUES (86, 501, 1, 0);
INSERT INTO `song_tag` VALUES (86, 515, 1, 0);
INSERT INTO `song_tag` VALUES (86, 517, 1, 0);
INSERT INTO `song_tag` VALUES (86, 512, 1, 0);
INSERT INTO `song_tag` VALUES (86, 404, 1, 0);
INSERT INTO `song_tag` VALUES (86, 406, 1, 0);
INSERT INTO `song_tag` VALUES (86, 407, 1, 0);
INSERT INTO `song_tag` VALUES (86, 303, 1, 0);
INSERT INTO `song_tag` VALUES (86, 306, 1, 0);
INSERT INTO `song_tag` VALUES (87, 101, 1, 0);
INSERT INTO `song_tag` VALUES (87, 520, 1, 0);
INSERT INTO `song_tag` VALUES (87, 515, 1, 0);
INSERT INTO `song_tag` VALUES (87, 517, 1, 0);
INSERT INTO `song_tag` VALUES (87, 514, 1, 0);
INSERT INTO `song_tag` VALUES (87, 511, 1, 0);
INSERT INTO `song_tag` VALUES (87, 509, 1, 0);
INSERT INTO `song_tag` VALUES (87, 409, 1, 0);
INSERT INTO `song_tag` VALUES (87, 306, 1, 0);
INSERT INTO `song_tag` VALUES (87, 401, 1, 0);
INSERT INTO `song_tag` VALUES (87, 311, 1, 0);
INSERT INTO `song_tag` VALUES (88, 309, 1, 0);
INSERT INTO `song_tag` VALUES (88, 101, 1, 0);
INSERT INTO `song_tag` VALUES (88, 504, 1, 0);
INSERT INTO `song_tag` VALUES (88, 502, 1, 0);
INSERT INTO `song_tag` VALUES (88, 303, 1, 0);
INSERT INTO `song_tag` VALUES (88, 401, 1, 0);
INSERT INTO `song_tag` VALUES (88, 404, 1, 0);
INSERT INTO `song_tag` VALUES (88, 509, 1, 0);
INSERT INTO `song_tag` VALUES (89, 102, 1, 0);
INSERT INTO `song_tag` VALUES (89, 301, 1, 0);
INSERT INTO `song_tag` VALUES (89, 311, 1, 0);
INSERT INTO `song_tag` VALUES (89, 307, 1, 0);
INSERT INTO `song_tag` VALUES (89, 402, 1, 0);
INSERT INTO `song_tag` VALUES (89, 404, 1, 0);
INSERT INTO `song_tag` VALUES (89, 406, 1, 0);
INSERT INTO `song_tag` VALUES (89, 408, 1, 0);
INSERT INTO `song_tag` VALUES (89, 407, 1, 0);
INSERT INTO `song_tag` VALUES (89, 504, 1, 0);
INSERT INTO `song_tag` VALUES (89, 505, 1, 0);
INSERT INTO `song_tag` VALUES (89, 509, 1, 0);
INSERT INTO `song_tag` VALUES (89, 514, 1, 0);
INSERT INTO `song_tag` VALUES (89, 520, 1, 0);
INSERT INTO `song_tag` VALUES (90, 102, 1, 0);
INSERT INTO `song_tag` VALUES (90, 309, 1, 0);
INSERT INTO `song_tag` VALUES (90, 301, 1, 0);
INSERT INTO `song_tag` VALUES (90, 303, 1, 0);
INSERT INTO `song_tag` VALUES (90, 401, 1, 0);
INSERT INTO `song_tag` VALUES (90, 403, 1, 0);
INSERT INTO `song_tag` VALUES (90, 405, 1, 0);
INSERT INTO `song_tag` VALUES (90, 407, 1, 0);
INSERT INTO `song_tag` VALUES (90, 502, 1, 0);
INSERT INTO `song_tag` VALUES (90, 506, 1, 0);
INSERT INTO `song_tag` VALUES (90, 505, 1, 0);
INSERT INTO `song_tag` VALUES (90, 509, 1, 0);
INSERT INTO `song_tag` VALUES (90, 520, 1, 0);
INSERT INTO `song_tag` VALUES (91, 102, 1, 0);
INSERT INTO `song_tag` VALUES (91, 307, 1, 0);
INSERT INTO `song_tag` VALUES (91, 301, 1, 0);
INSERT INTO `song_tag` VALUES (91, 407, 1, 0);
INSERT INTO `song_tag` VALUES (91, 503, 1, 0);
INSERT INTO `song_tag` VALUES (91, 508, 1, 0);
INSERT INTO `song_tag` VALUES (91, 509, 1, 0);
INSERT INTO `song_tag` VALUES (91, 505, 1, 0);
INSERT INTO `song_tag` VALUES (91, 311, 1, 0);
INSERT INTO `song_tag` VALUES (91, 401, 1, 0);
INSERT INTO `song_tag` VALUES (91, 404, 1, 0);
INSERT INTO `song_tag` VALUES (91, 514, 1, 0);
INSERT INTO `song_tag` VALUES (91, 519, 1, 0);
INSERT INTO `song_tag` VALUES (91, 520, 1, 0);
INSERT INTO `song_tag` VALUES (92, 102, 1, 0);
INSERT INTO `song_tag` VALUES (92, 302, 1, 0);
INSERT INTO `song_tag` VALUES (92, 307, 1, 0);
INSERT INTO `song_tag` VALUES (92, 311, 1, 0);
INSERT INTO `song_tag` VALUES (92, 404, 1, 0);
INSERT INTO `song_tag` VALUES (92, 406, 1, 0);
INSERT INTO `song_tag` VALUES (92, 502, 1, 0);
INSERT INTO `song_tag` VALUES (92, 504, 1, 0);
INSERT INTO `song_tag` VALUES (92, 509, 1, 0);
INSERT INTO `song_tag` VALUES (92, 520, 1, 0);
INSERT INTO `song_tag` VALUES (92, 506, 1, 0);
INSERT INTO `song_tag` VALUES (92, 505, 1, 0);
INSERT INTO `song_tag` VALUES (93, 102, 1, 0);
INSERT INTO `song_tag` VALUES (93, 303, 1, 0);
INSERT INTO `song_tag` VALUES (93, 309, 1, 0);
INSERT INTO `song_tag` VALUES (93, 311, 1, 0);
INSERT INTO `song_tag` VALUES (93, 403, 1, 0);
INSERT INTO `song_tag` VALUES (93, 404, 1, 0);
INSERT INTO `song_tag` VALUES (93, 405, 1, 0);
INSERT INTO `song_tag` VALUES (93, 406, 1, 0);
INSERT INTO `song_tag` VALUES (93, 502, 1, 0);
INSERT INTO `song_tag` VALUES (93, 506, 1, 0);
INSERT INTO `song_tag` VALUES (93, 509, 1, 0);
INSERT INTO `song_tag` VALUES (94, 101, 1, 0);
INSERT INTO `song_tag` VALUES (94, 301, 1, 0);
INSERT INTO `song_tag` VALUES (94, 309, 1, 0);
INSERT INTO `song_tag` VALUES (94, 311, 1, 0);
INSERT INTO `song_tag` VALUES (94, 405, 1, 0);
INSERT INTO `song_tag` VALUES (94, 406, 1, 0);
INSERT INTO `song_tag` VALUES (94, 408, 1, 0);
INSERT INTO `song_tag` VALUES (94, 409, 1, 0);
INSERT INTO `song_tag` VALUES (94, 501, 1, 0);
INSERT INTO `song_tag` VALUES (94, 509, 1, 0);
INSERT INTO `song_tag` VALUES (94, 519, 1, 0);
INSERT INTO `song_tag` VALUES (95, 101, 1, 0);
INSERT INTO `song_tag` VALUES (95, 303, 1, 0);
INSERT INTO `song_tag` VALUES (95, 306, 1, 0);
INSERT INTO `song_tag` VALUES (95, 402, 1, 0);
INSERT INTO `song_tag` VALUES (95, 404, 1, 0);
INSERT INTO `song_tag` VALUES (95, 406, 1, 0);
INSERT INTO `song_tag` VALUES (95, 407, 1, 0);
INSERT INTO `song_tag` VALUES (95, 409, 1, 0);
INSERT INTO `song_tag` VALUES (95, 509, 1, 0);
INSERT INTO `song_tag` VALUES (95, 515, 1, 0);
INSERT INTO `song_tag` VALUES (95, 520, 1, 0);
INSERT INTO `song_tag` VALUES (96, 101, 1, 0);
INSERT INTO `song_tag` VALUES (96, 515, 1, 0);
INSERT INTO `song_tag` VALUES (96, 509, 1, 0);
INSERT INTO `song_tag` VALUES (96, 505, 1, 0);
INSERT INTO `song_tag` VALUES (96, 506, 1, 0);
INSERT INTO `song_tag` VALUES (96, 504, 1, 0);
INSERT INTO `song_tag` VALUES (96, 502, 1, 0);
INSERT INTO `song_tag` VALUES (96, 306, 1, 0);
INSERT INTO `song_tag` VALUES (96, 311, 1, 0);
INSERT INTO `song_tag` VALUES (96, 401, 1, 0);
INSERT INTO `song_tag` VALUES (96, 403, 1, 0);
INSERT INTO `song_tag` VALUES (96, 405, 1, 0);
INSERT INTO `song_tag` VALUES (96, 303, 1, 0);
INSERT INTO `song_tag` VALUES (97, 101, 1, 0);
INSERT INTO `song_tag` VALUES (97, 307, 1, 0);
INSERT INTO `song_tag` VALUES (97, 502, 1, 0);
INSERT INTO `song_tag` VALUES (97, 505, 1, 0);
INSERT INTO `song_tag` VALUES (97, 506, 1, 0);
INSERT INTO `song_tag` VALUES (97, 508, 1, 0);
INSERT INTO `song_tag` VALUES (97, 509, 1, 0);
INSERT INTO `song_tag` VALUES (97, 519, 1, 0);
INSERT INTO `song_tag` VALUES (97, 303, 1, 0);
INSERT INTO `song_tag` VALUES (98, 101, 1, 0);
INSERT INTO `song_tag` VALUES (98, 303, 1, 0);
INSERT INTO `song_tag` VALUES (98, 304, 1, 0);
INSERT INTO `song_tag` VALUES (98, 307, 1, 0);
INSERT INTO `song_tag` VALUES (98, 306, 1, 0);
INSERT INTO `song_tag` VALUES (98, 308, 1, 0);
INSERT INTO `song_tag` VALUES (98, 311, 1, 0);
INSERT INTO `song_tag` VALUES (98, 401, 1, 0);
INSERT INTO `song_tag` VALUES (98, 403, 1, 0);
INSERT INTO `song_tag` VALUES (98, 404, 1, 0);
INSERT INTO `song_tag` VALUES (98, 402, 1, 0);
INSERT INTO `song_tag` VALUES (98, 405, 1, 0);
INSERT INTO `song_tag` VALUES (98, 406, 1, 0);
INSERT INTO `song_tag` VALUES (98, 407, 1, 0);
INSERT INTO `song_tag` VALUES (98, 504, 1, 0);
INSERT INTO `song_tag` VALUES (98, 508, 1, 0);
INSERT INTO `song_tag` VALUES (98, 509, 1, 0);
INSERT INTO `song_tag` VALUES (99, 101, 1, 0);
INSERT INTO `song_tag` VALUES (99, 303, 1, 0);
INSERT INTO `song_tag` VALUES (99, 307, 1, 0);
INSERT INTO `song_tag` VALUES (99, 306, 1, 0);
INSERT INTO `song_tag` VALUES (99, 312, 1, 0);
INSERT INTO `song_tag` VALUES (99, 311, 1, 0);
INSERT INTO `song_tag` VALUES (99, 403, 1, 0);
INSERT INTO `song_tag` VALUES (99, 404, 1, 0);
INSERT INTO `song_tag` VALUES (99, 502, 1, 0);
INSERT INTO `song_tag` VALUES (99, 504, 1, 0);
INSERT INTO `song_tag` VALUES (99, 509, 1, 0);
INSERT INTO `song_tag` VALUES (99, 508, 1, 0);
INSERT INTO `song_tag` VALUES (99, 520, 1, 0);
INSERT INTO `song_tag` VALUES (99, 516, 1, 0);
INSERT INTO `song_tag` VALUES (100, 101, 1, 0);
INSERT INTO `song_tag` VALUES (100, 516, 1, 0);
INSERT INTO `song_tag` VALUES (100, 519, 1, 0);
INSERT INTO `song_tag` VALUES (100, 509, 1, 0);
INSERT INTO `song_tag` VALUES (100, 510, 1, 0);
INSERT INTO `song_tag` VALUES (100, 409, 1, 0);
INSERT INTO `song_tag` VALUES (100, 407, 1, 0);
INSERT INTO `song_tag` VALUES (100, 405, 1, 0);
INSERT INTO `song_tag` VALUES (100, 404, 1, 0);
INSERT INTO `song_tag` VALUES (100, 406, 1, 0);
INSERT INTO `song_tag` VALUES (100, 303, 1, 0);
INSERT INTO `song_tag` VALUES (100, 306, 1, 0);
INSERT INTO `song_tag` VALUES (101, 101, 1, 0);
INSERT INTO `song_tag` VALUES (101, 303, 1, 0);
INSERT INTO `song_tag` VALUES (101, 311, 1, 0);
INSERT INTO `song_tag` VALUES (101, 307, 1, 0);
INSERT INTO `song_tag` VALUES (101, 403, 1, 0);
INSERT INTO `song_tag` VALUES (101, 401, 1, 0);
INSERT INTO `song_tag` VALUES (101, 405, 1, 0);
INSERT INTO `song_tag` VALUES (101, 502, 1, 0);
INSERT INTO `song_tag` VALUES (101, 504, 1, 0);
INSERT INTO `song_tag` VALUES (101, 505, 1, 0);
INSERT INTO `song_tag` VALUES (101, 509, 1, 0);
INSERT INTO `song_tag` VALUES (101, 520, 1, 0);

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
