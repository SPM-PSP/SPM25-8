/*
 Navicat Premium Data Transfer

 Source Server         : stu
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : personal_health

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001

 Date: 18/05/2025 13:47:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for evaluations
-- ----------------------------
DROP TABLE IF EXISTS `evaluations`;
CREATE TABLE `evaluations`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` int NULL DEFAULT NULL COMMENT '父级评论ID',
  `commenter_id` int NULL DEFAULT NULL COMMENT '评论者ID',
  `replier_id` int NULL DEFAULT NULL COMMENT '回复者ID',
  `content_type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '内容类型',
  `content_id` int NULL DEFAULT NULL COMMENT '内容ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '评论内容',
  `upvote_list` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '点赞列表，以\",\"分割',
  `create_time` datetime NULL DEFAULT NULL COMMENT '评论时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 209 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of evaluations
-- ----------------------------
INSERT INTO `evaluations` VALUES (157, NULL, 3, NULL, 'NEWS', 12, '就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息', '3,7,8', '2025-04-05 18:51:07');
INSERT INTO `evaluations` VALUES (163, 157, 3, 17, 'NEWS', 12, '回复你', NULL, '2025-04-05 19:00:05');
INSERT INTO `evaluations` VALUES (166, 158, 3, 17, 'NEWS', 12, '快继续', NULL, '2025-04-05 19:17:10');
INSERT INTO `evaluations` VALUES (167, 157, 17, NULL, 'NEWS', 12, '你说的是什么', '3', '2025-04-05 19:18:14');
INSERT INTO `evaluations` VALUES (169, 157, 3, 17, 'NEWS', 12, '就是说', NULL, '2025-04-05 19:20:38');
INSERT INTO `evaluations` VALUES (170, 157, 3, 17, 'NEWS', 12, '大把样子', NULL, '2025-04-06 13:29:10');
INSERT INTO `evaluations` VALUES (171, 157, 3, 17, 'NEWS', 12, '给你发', NULL, '2025-04-06 13:29:57');
INSERT INTO `evaluations` VALUES (172, NULL, 17, NULL, 'NEWS', 12, '补肽，顾名思义，就是补充身体所需的肽类物质', '3', '2025-04-06 13:31:25');
INSERT INTO `evaluations` VALUES (173, 172, 3, NULL, 'NEWS', 12, '一级评论了了了', NULL, '2025-04-06 13:31:47');
INSERT INTO `evaluations` VALUES (174, 172, 3, NULL, 'NEWS', 12, '你接收到了吗？？？？', NULL, '2025-04-06 14:23:38');
INSERT INTO `evaluations` VALUES (175, 172, 17, 3, 'NEWS', 12, 'k可以了', NULL, '2025-04-06 14:23:54');
INSERT INTO `evaluations` VALUES (176, 172, 3, 17, 'NEWS', 12, '就是说', NULL, '2025-04-06 14:24:11');
INSERT INTO `evaluations` VALUES (177, 172, 17, 3, 'NEWS', 12, '还好', NULL, '2025-04-06 14:24:22');
INSERT INTO `evaluations` VALUES (178, 157, 3, 17, 'NEWS', 12, '现在呢', NULL, '2025-04-06 14:25:00');
INSERT INTO `evaluations` VALUES (179, 157, 3, 17, 'NEWS', 12, '现在呢1212', NULL, '2025-04-06 14:25:57');
INSERT INTO `evaluations` VALUES (180, 157, 3, 17, 'NEWS', 12, '现在呢1212432432432', NULL, '2025-04-06 14:31:48');
INSERT INTO `evaluations` VALUES (181, 157, 3, 17, 'NEWS', 12, '现在是测试的数据', '3', '2025-04-06 14:34:43');
INSERT INTO `evaluations` VALUES (182, 157, 3, 17, 'NEWS', 12, '现在是测试的数据21', NULL, '2025-04-06 14:57:37');
INSERT INTO `evaluations` VALUES (183, 157, 3, NULL, 'NEWS', 12, '你好', NULL, '2025-04-06 15:01:55');
INSERT INTO `evaluations` VALUES (184, 172, 3, NULL, 'NEWS', 12, '就是', '3', '2025-04-06 15:02:29');
INSERT INTO `evaluations` VALUES (185, 172, 17, 3, 'NEWS', 12, 'd是电', NULL, '2025-04-06 15:02:39');
INSERT INTO `evaluations` VALUES (186, 157, 3, 17, 'NEWS', 12, '43243243', NULL, '2025-04-06 15:03:28');
INSERT INTO `evaluations` VALUES (187, 172, 3, 17, 'NEWS', 12, '432432433232', '3', '2025-04-06 15:04:00');
INSERT INTO `evaluations` VALUES (188, 172, 17, 3, 'NEWS', 12, '432432', NULL, '2025-04-06 15:04:06');
INSERT INTO `evaluations` VALUES (189, 157, 3, 17, 'NEWS', 12, '就是这个效果', NULL, '2025-04-06 15:04:30');
INSERT INTO `evaluations` VALUES (190, 157, 3, 17, 'NEWS', 12, '就是这个效果111', NULL, '2025-04-06 15:05:39');
INSERT INTO `evaluations` VALUES (191, 172, 3, 17, 'NEWS', 12, '就是这个效果1113', NULL, '2025-04-06 15:05:54');
INSERT INTO `evaluations` VALUES (192, 157, 3, 17, 'NEWS', 12, '就是这个效果1113', NULL, '2025-04-06 15:06:44');
INSERT INTO `evaluations` VALUES (193, 157, 17, 3, 'NEWS', 12, '3213123', NULL, '2025-04-06 15:06:51');
INSERT INTO `evaluations` VALUES (194, 157, 3, 17, 'NEWS', 12, '太多的东西了', NULL, '2025-04-06 15:07:27');
INSERT INTO `evaluations` VALUES (195, 157, 17, 3, 'NEWS', 12, '怎么回事啊', NULL, '2025-04-06 15:07:37');
INSERT INTO `evaluations` VALUES (196, 157, 3, 17, 'NEWS', 12, '给你回复', '', '2025-04-06 15:07:59');
INSERT INTO `evaluations` VALUES (197, 157, 17, 3, 'NEWS', 12, '我在消息中收到了', NULL, '2025-04-06 15:08:14');
INSERT INTO `evaluations` VALUES (198, 172, 3, NULL, 'NEWS', 12, '一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', NULL, '2025-04-06 15:08:28');
INSERT INTO `evaluations` VALUES (199, 172, 17, 3, 'NEWS', 12, '随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视', '3', '2025-04-06 15:08:38');
INSERT INTO `evaluations` VALUES (200, 172, 3, 17, 'NEWS', 12, '太好了', '3', '2025-04-06 15:10:13');
INSERT INTO `evaluations` VALUES (201, 157, 17, 3, 'NEWS', 12, '怎么说', NULL, '2025-04-06 17:56:46');
INSERT INTO `evaluations` VALUES (202, 157, 3, 17, 'NEWS', 12, 'hahahha', NULL, '2025-04-06 18:26:33');
INSERT INTO `evaluations` VALUES (203, NULL, 13, NULL, 'NEWS', 2, '宝宝嘟嘟打雷了\n', '3', '2025-04-23 15:28:43');
INSERT INTO `evaluations` VALUES (204, 203, 3, NULL, 'NEWS', 2, '6', '3', '2025-04-23 15:30:18');
INSERT INTO `evaluations` VALUES (205, NULL, 13, NULL, 'NEWS', 13, 'fujdn', NULL, '2025-05-07 15:24:51');
INSERT INTO `evaluations` VALUES (206, NULL, 54, NULL, 'NEWS', 10, 'nihaonihaonihao123123', '54', '2025-05-17 14:56:08');
INSERT INTO `evaluations` VALUES (207, 206, 54, NULL, 'NEWS', 10, '啊啊啊啊啊宝宝你是一个一个一个香香软软的草莓小蛋糕啊啊啊啊啊啊', NULL, '2025-05-17 14:56:35');
INSERT INTO `evaluations` VALUES (208, 206, 54, 54, 'NEWS', 10, '啊啊啊啊啊啊啊', NULL, '2025-05-17 14:56:42');

-- ----------------------------
-- Table structure for health_model_config
-- ----------------------------
DROP TABLE IF EXISTS `health_model_config`;
CREATE TABLE `health_model_config`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '健康模型自增主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置名',
  `detail` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置简介',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置图标',
  `unit` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置单位',
  `symbol` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '配置符号',
  `is_global` tinyint(1) NULL DEFAULT NULL COMMENT '是否是全局模型',
  `value_range` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '正常值范围',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '健康模型表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of health_model_config
-- ----------------------------
INSERT INTO `health_model_config` VALUES (1, 1, '身高', '身高模型，选择该模型可记录身高', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=d9f4677cb406cbc8379f0身高.png', 'cm', 'Height', 1, '195,459');
INSERT INTO `health_model_config` VALUES (2, 1, '体重', '该模型为体重参数，记录时选中即可', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=b0652b2cb8b74fb2888c7体重.png', 'KG', 'Weight', 1, '126,548');
INSERT INTO `health_model_config` VALUES (4, 1, '谷丙转氨酶', '专属记录转氨酶', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=48b7aea1adac5504d7bcf转氨酶.png', 'U/L', 'ALT', 1, '108,258');
INSERT INTO `health_model_config` VALUES (7, 1, '运动心率', '运动时心率', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=a4a39d86bbdba88db294f心率.png', '次/分', 'Bpm', 1, '75,197');
INSERT INTO `health_model_config` VALUES (8, 1, '夜间血压', '夜间血压测量值', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=72002b125bfff58f79f84ec252b8血压.png', '毫米汞柱', 'mmHg', 1, '72,145');
INSERT INTO `health_model_config` VALUES (9, 1, '夜跑步数', '记录夜跑步数', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=1fc1de88b36fb91cd680d步数.png', '步', '暂无', 1, '422,17990');
INSERT INTO `health_model_config` VALUES (10, 1, '晨跑步数', '记录晨跑时的步数', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=56f42181cd680d步数.png', '步', '暂无', 1, '3000,10000');
INSERT INTO `health_model_config` VALUES (11, 3, '晨间血糖浓度', '这是测试的', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=25bfff58f79f84ec252b8血压.png', 'mol', '摩尔/升', 0, '10,50');
INSERT INTO `health_model_config` VALUES (12, 3, '夜间平均心率', '测试数据', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=12a2e326bbdba88db294f心率.png', '次', '暂无', 0, '80,110');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '消息表的自增主键ID',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '消息内容（消息体）',
  `message_type` int NULL DEFAULT NULL COMMENT '消息类型（指标数据、互动、系统消息...）',
  `receiver_id` int NULL DEFAULT NULL COMMENT '接收者ID',
  `sender_id` int NULL DEFAULT NULL COMMENT '发送者ID',
  `is_read` tinyint(1) NULL DEFAULT NULL COMMENT '消息是否已经阅读',
  `content_id` int NULL DEFAULT NULL COMMENT '关联的内容ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '消息的发送时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 78 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES (4, '157;12;你说的是什么', 1, 3, 17, 1, 12, '2025-01-05 19:18:14');
INSERT INTO `message` VALUES (5, '172;12;一级评论了了了', 1, 17, 3, 1, 12, '2025-01-06 13:31:47');
INSERT INTO `message` VALUES (6, '172;12;你接收到了吗？？？？', 1, 17, 3, 1, 12, '2025-01-06 14:23:38');
INSERT INTO `message` VALUES (7, '172;12;就是说', 1, 17, 3, 1, 12, '2025-01-06 14:24:11');
INSERT INTO `message` VALUES (8, '172;12;就是', 1, 17, 3, 1, 12, '2025-01-06 15:02:29');
INSERT INTO `message` VALUES (9, '172;12;432432433232', 1, 17, 3, 1, 12, '2025-01-06 15:04:00');
INSERT INTO `message` VALUES (10, '172;12;就是这个效果1113', 1, 17, 3, 1, 12, '2025-01-06 15:05:55');
INSERT INTO `message` VALUES (11, '157;12;就是这个效果1113', 1, 17, 3, 1, 12, '2025-01-06 15:06:44');
INSERT INTO `message` VALUES (12, '157;12;3213123', 1, 3, 17, 1, 12, '2025-01-06 15:06:51');
INSERT INTO `message` VALUES (13, '157;12;太多的东西了', 1, 17, 3, 1, 12, '2025-01-06 15:07:27');
INSERT INTO `message` VALUES (14, '157;12;怎么回事啊', 1, 3, 17, 1, 12, '2025-01-06 15:07:37');
INSERT INTO `message` VALUES (15, '157;12;给你回复', 1, 17, 3, 1, 12, '2025-01-06 15:07:59');
INSERT INTO `message` VALUES (16, '157;12;我在消息中收到了', 1, 3, 17, 1, 12, '2025-01-06 15:08:14');
INSERT INTO `message` VALUES (17, '172;12;一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 1, 17, 3, 1, 12, '2025-01-06 15:08:28');
INSERT INTO `message` VALUES (18, '172;12;随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视', 1, 3, 17, 1, 12, '2025-01-06 15:08:38');
INSERT INTO `message` VALUES (19, '172;12;太好了', 1, 17, 3, 1, 12, '2025-01-06 15:10:13');
INSERT INTO `message` VALUES (38, '点赞了你的评论【还好】', 2, 17, 3, 1, 177, '2025-01-06 17:46:00');
INSERT INTO `message` VALUES (39, '点赞了你的评论【d是电】', 2, 17, 3, 1, 185, '2025-01-06 17:47:48');
INSERT INTO `message` VALUES (40, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 21, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (41, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 20, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (42, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 19, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (43, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 18, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (44, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 17, NULL, 1, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (45, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 15, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (46, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 13, NULL, 1, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (47, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 12, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (48, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 10, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (49, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 8, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (50, '成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。', 4, 3, NULL, 0, NULL, '2025-01-06 17:48:40');
INSERT INTO `message` VALUES (51, '你记录的【晨跑步数】超标了，正常值范围:[3000,10000]，请注意休息。必要时请就医!', 3, 17, NULL, 0, NULL, '2025-01-06 17:49:08');
INSERT INTO `message` VALUES (52, '157;12;怎么说', 1, 3, 17, 0, 12, '2025-01-06 17:56:46');
INSERT INTO `message` VALUES (53, '157;12;hahahha', 1, 17, 3, 0, 12, '2025-01-06 18:26:33');
INSERT INTO `message` VALUES (65, '你记录的【夜跑步数】超标了，正常值范围:[422,17990]，请注意休息。必要时请就医!', 3, 13, NULL, 1, NULL, '2025-04-30 11:05:32');
INSERT INTO `message` VALUES (66, '你记录的【夜间血压】超标了，正常值范围:[72,145]，请注意休息。必要时请就医!', 3, 13, NULL, 1, NULL, '2025-05-07 15:36:58');
INSERT INTO `message` VALUES (67, '', 4, 21, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (68, '', 4, 20, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (69, '', 4, 19, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (70, '', 4, 18, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (71, '', 4, 17, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (72, '', 4, 15, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (73, '', 4, 13, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (74, '', 4, 12, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (75, '', 4, 10, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (76, '', 4, 8, NULL, 0, NULL, '2025-05-17 10:46:52');
INSERT INTO `message` VALUES (77, '', 4, 3, NULL, 0, NULL, '2025-05-17 10:46:52');

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '健康资讯ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标题',
  `content` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '内容',
  `tag_id` int NULL DEFAULT NULL COMMENT '标签ID',
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '图片封面',
  `reader_ids` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL COMMENT '阅读者ID列表，用“,”分割',
  `is_top` tinyint(1) NULL DEFAULT NULL COMMENT '是否推荐',
  `create_time` datetime NULL DEFAULT NULL COMMENT '发布时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '健康资讯表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '成年男性平均身高接近1米7，女性1米58', '<p>中国人长高了！</p><p style=\"text-align: justify;\">成年男性平均身高接近1米7，女性1米58</p><p style=\"text-align: justify;\">据介绍，2015年至2019年，国家卫健委组织中国疾控中心、国家癌症中心、国家心血管病中心开展了新一轮中国居民慢性病与营养监测，覆盖全国31个省(区、市)近6亿人口，现场调查人数超过60万，具有国家和省级代表性，根据监测结果编写形成《中国居民营养与慢性病状况报告(2020年)》。</p><p style=\"text-align: justify;\">报告显示，中国居民体格发育与营养不足问题持续改善，城乡差异逐步缩小。中国城乡居民膳食能量和蛋白质、脂肪、碳水化合物三大宏量营养素摄入充足，优质蛋白摄入不断增加。</p><p style=\"text-align: justify;\">另外，近年来，中国人还“长高了”！</p><p style=\"text-align: justify;\">数据显示，中国成人平均身高继续增长，18-44岁男性和女性的平均身高分别为169.7厘米和158.0厘米，与2015年发布结果相比分别增加1.2厘米和0.8厘米。</p><p>儿童青少年生长发育水平持续改善，6-17岁男孩和女孩各年龄组身高均有增加，平均增加值分别为1.6厘米和1.0厘米，6岁以下儿童生长迟缓率降至7%以下，低体重率降至5%以下，均已实现2020年国家规划目标。</p>', 2, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=cd431a3yundong1.png', NULL, 0, '2025-04-20 15:40:04');
INSERT INTO `news` VALUES (2, '疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击', '<p><br></p><p style=\"text-align: justify;\">在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。<br></p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p>所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 2, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=330c66cxinlinjiankang.png', NULL, 1, '2025-04-19 15:48:00');
INSERT INTO `news` VALUES (3, '身体健康的十项指标，你知道几个？', '<p>随着人们健康意识的提高。有关健康的话题越来越引起重视。不生病就是健康吗？这样说，有点太狭隘了。健康的准确定义应该是身体健康，心理健康，具有良好的社会适应性。今天我们就来详细了解身体健康的十项指标。</p><h4 style=\"text-align: justify;\"><strong>1.饮食指标</strong></h4><p style=\"text-align: justify;\">一般来说成人每天应该吃500克左右的食物，而老人的话，因为肠胃功能减弱，则不应该超过350克。如果出现多食多饮，而且体重下降的情况，就要考虑是否得了糖尿病或者甲亢；如果食量减少 ，每天摄取的食物不到250克，持续时间比较长，就要考虑是否患有炎症或者恶性肿瘤了，要及时检查。</p><p style=\"text-align: justify;\">2.体重指标</p><p style=\"text-align: justify;\">体重并不是越轻越好。这里教给大家一个公式，你可以算下你的正常体重应该是多少。世界卫生组织推荐的计算方法：男性：(身高cm－80)×70﹪=标准体重；女性：(身高cm－70)×60﹪=标准体重。如果短时间内，体重下降过快，人明显消瘦，这种情况多见于糖尿病、甲亢、恶性肿瘤、胃肠系统等疾病；如果短时间内，体重急剧增长，一方面和你的不健康饮食有关，另一方面，也可能和高血脂、甲减、糖尿病有关。国内外大量研究显示，约有60%—80%的成年糖尿病患者在发病前体重超标。</p><p style=\"text-align: justify;\">主食和油</p><p style=\"text-align: justify;\">糖尿病人这样吃更安全</p><p style=\"text-align: justify;\">3.体温指标</p><p style=\"text-align: justify;\">正常人的体温应该为36℃～37℃，超过37℃就可以确诊为发烧。除了发烧外，还有一种情况，就是体温低于正常体温，称为“低体温”。“低体温”常出现在高龄老人，长期营养不足的患者身上，甲减或者时常休克的病人，也会有这种情况。</p><p style=\"text-align: justify;\">4.脉搏指标（心跳指标）</p><p style=\"text-align: justify;\">成人标准脉搏应该为每分钟60～100次（注：运动员的脉搏可能低于这个标准）。如果出现脉搏过速、过缓、有间歇、脉搏较弱、时快时慢，这均表示，可能心脏有问题，需要及时就医。老人心跳一般会略低于标准数，不过，只要能保持不低于55次/分钟，就算是正常的。如果平时心跳较慢，突然增至80～90次每分钟，就要考虑是不是得了潜在疾病，需要马上就医检查。</p><p style=\"text-align: justify;\">5.呼吸指标</p><p style=\"text-align: justify;\">健康人的呼吸应该是平稳，有规律的。每分钟16～18次左右。如果呼吸急促、大喘气、或者气不够用，时快时慢，胸闷，憋气等现象都是不正常的表现。呼吸急促、喘不过气多见于哮喘疾病；胸闷、憋气多见于心脏疾病。对于老人来说，心肺功能减弱，运动后也可能会有心悸气短的症状，不过，如果休息后，症状缓解，就是正常的，并不是疾病的表现。</p><p style=\"text-align: justify;\">6.排便指标</p><p style=\"text-align: justify;\">健康人的排便次数每天或者隔天一次。正常的大便应该是黄色软状。高龄老人，饮食少、运动少的，2～3天排便一次也是正常的。只要排便畅通，大便不干，就不是便秘。对于普通人来说，如果超过3天未排便，而且排便困难、排不尽、大便发干，这就是得了便秘；如果每天排便超过三次，并且大便不成形，便稀，这种情况一般就是得了腹泻了。结肠炎也可以通过大便检查出来。一般来说，患有结肠炎的病人，会有便血、便秘或者腹泻等症状。</p><p style=\"text-align: justify;\">7.排尿指标</p><p style=\"text-align: justify;\">成人的排尿量每天应该在1～2升左右，2～4小时就要排尿一次（夜间除外）。健康人的尿液应该是淡黄色，透明、少许泡沫。如果小便黄中带红，就要考虑尿路感染的情况；小便呈蓝绿色，多见于绿脓杆菌感染；血尿常见于泌尿生殖系统疾病、肿瘤或者结石等；尿频、尿急，这种情况就不用说了，是前列腺疾病的典型症状。</p><p style=\"text-align: justify;\">8.血压指标</p><p style=\"text-align: justify;\">这个大家应该都比较熟悉。成人的血压应该是不超过140/80毫米汞柱。对于老人来说，血管壁弹性降低，体内垃圾积累过多，需要心脏加大压力，才能保证正常的血氧供应，所以血压会上升。不过要注意，老人的收缩压不应超过160毫米汞柱，如果超过这个标准，就可以判定为高血压，不管有没有不舒服的症状都应吃药。如果单纯的是舒张压过高，需要去医院检查，切忌私自用药。</p><p style=\"text-align: justify;\"><img src=\"https://p3-sign.toutiaoimg.com/tos-cn-i-tjoges91tu/T1cRrWvC8OWZ77~noop.image?_iz=58558&amp;from=article.pc_detail&amp;lk3s=953192f4&amp;x-expires=1721543150&amp;x-signature=%2F5yaAFAWB6ZB75WWQN%2BcrulPDns%3D\" alt=\"\" data-href=\"\" style=\"height: auto;\"></p><p style=\"text-align: justify;\">9.睡眠指标</p><p style=\"text-align: justify;\">一般来说，成人的睡眠时间应该在6～8小时，睡足8小时不是标准，而是应该以起床时精神状态是否饱满作为标准。而且每个年龄段的人群，睡眠时间是不一样的。10～18岁的人群，要保证每天8小时睡眠时间；18～50岁的人群，保证每天7小时睡眠即可；50～70岁，每天只需要5～6小时睡觉就够了。对于老人来说，睡眠质量会有所下降，所以不能硬性规定每天要睡多久。只要睡醒后，白天有精神，无疲劳感就可以。另外，建议老人要养成午休的习惯，有利于身体健康。</p><p style=\"text-align: justify;\">10.精神指标</p><p style=\"text-align: justify;\">世界卫生组织把人体精神健康的标准概括为“三良好”：</p><p style=\"text-align: justify;\">良好的个性人格——情绪稳定，性格温和，一直坚强，感情丰富，豁达乐观。</p><p style=\"text-align: justify;\">良好的处世能力——观察问题客观，有较强的自我控制能力，能适应复杂的社会环境。</p><p style=\"text-align: justify;\">良好的人际关系——待人接物大度和善，助人为乐，与人为善。</p><p style=\"text-align: justify;\">健康人应该有饱满的精神状态，丰富的情感、敏捷的行动能力。如果不具备这些以及上面的“三良好”，就需要考虑是不是身体有隐疾了。</p><p>大家快来对照看看自己的这些指标是否合格吧！有了健康上的小问题要及时发现治疗，否则可能形成大隐患！</p>', 2, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=0653bd6yundong2.png', NULL, 1, '2025-04-18 14:27:51');
INSERT INTO `news` VALUES (4, '健康的7大准则，做到2个就很厉害', '<p>有人说，打败一个年轻人，只需要一张体检报告单。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">中年青年报社社会调查中心做过一项调查，45.2%的受访青年表示害怕参加体检，其中95后受访者表示害怕体检的比例最高，为52.3%。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">害怕体检，68.1%的受访者是担心会查出身体有问题。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\"><strong>的确，不少疾病都在呈现年轻化的趋势，以前认为六七十岁才患的病，如今很多二三十岁的已经深受困扰。</strong></p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">比如骨质疏松、中风、二型糖尿病等等，一档关注认知障碍的电视节目《忘不了餐厅》，曾邀请过一位嘉宾，她在36岁时就确诊了阿尔茨海默病。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">如果说造成疾病的是不良的饮食习惯和结构、不规律的作息、缺乏运动等生活方式，那么，又是什么导致我们过上了这种带来疾病的生活方式？</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">《运动改造大脑》的作者、哈佛医学院教授约翰·瑞迪，有一本书叫《医生最想让你做的事》。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">他指出，文明发展迅速，但我们的身体并没有跟上时代的步伐。这种失配影响到我们生活的每个领域，包括身体健康，还有情感健康。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\"><strong>瑞迪教授认为，疾病不是源于人身体结构上的不足或缺陷，而是源于生活方式带来的自我损伤。</strong></p><p style=\"text-align: justify;\"><img src=\"https://p3-sign.toutiaoimg.com/tos-cn-i-qvj2lq49k0/1ddd7fb50276407cb6f7c5f6cfefbf33~noop.image?_iz=58558&amp;from=article.pc_detail&amp;lk3s=953192f4&amp;x-expires=1721543305&amp;x-signature=yaW6sAE2E5qxjedMaYsFdnJiBI4%3D\" alt=\"\" data-href=\"\" style=\"height: auto;\"></p><p><br></p><p style=\"text-align: justify;\">人类在进化过程中，本身积累了三个核心优势，但是现代生活，让这些优势的作用正在渐渐失效。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\"><strong>首先，人类在运动方面很全能。</strong></p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">猿类都能跑，但跑不快，也跑不远，但人类可以。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">跑的前提是直立行走，同时需要调动大脑来进行协调和规划，在运动方面，人类就是个全能选手。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">而今，很多人一坐就是几个小时，运动量只有从家到地铁站的步行距离。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\"><strong>其次是人类饮食的多样化。</strong></p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">人是杂食动物，肠道中有成千上万种菌群，它们分解食物后给食物增加的价值也远远超过了我们的想象。</p><p style=\"text-align: justify;\">饮食多样性，能确保人类摄取种类足够多的微量营养素，以支撑复杂的人体运转。</p><p style=\"text-align: justify;\">但是今天的人类，食物中大量的是能快速提供能量的精加工制品，丧失了食物本身的完整性。</p><p style=\"text-align: justify;\"><img src=\"https://p3-sign.toutiaoimg.com/tos-cn-i-qvj2lq49k0/450f91c6160849e0ba8e2a07756f240f~noop.image?_iz=58558&amp;from=article.pc_detail&amp;lk3s=953192f4&amp;x-expires=1721543305&amp;x-signature=ooUPVyq%2FJJWHFdwakIVOjBT4uMw%3D\" alt=\"\" data-href=\"\" style=\"width: 50%;\"></p><p><br></p><p style=\"text-align: justify;\"><strong>最后，人类富有同理心。</strong></p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">20世纪八九十年代，意大利科学家团队发现了一种名为“镜像神经元”的细胞。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">研究人员在猴子吃花生时对其大脑进行监测，观察到一组神经元在放电。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">如果让一只猴子看另一只猴子吃花生，它脑内的同一组神经元也会放电，就好像它自己就是那只吃花生的猴子，那就是镜像神经元在放电。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">人不仅仅能察觉到他人的感受，甚至还会感同身受，这就是同理心。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">同理心是人类存在的基础，有同理心，人类才能凝聚在一起，才能合作。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">而今天的人，患上了社交恐惧症，不愿与人相处，更喜欢独自面对世界。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">比尔及梅琳达·盖茨基金会曾经进行过一项覆盖全世界187个国家的大规模研究，对死亡、身体虚弱及生活质量下降的成因进行了调查。</p><p style=\"text-align: justify;\"><br></p><p><strong>研究认为，全球范围内，造成疾病和身体虚弱的12个危险因素分别是：高血压、吸烟、酒精、油烟污染、水果摄入较少、体重指数高、高血糖、体重低、空气污染、缺乏活动、盐摄入量高、坚果和籽类食物摄入量低。</strong></p>', 6, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=f121525yangsheng.png', NULL, 1, '2025-04-17 14:29:09');
INSERT INTO `news` VALUES (5, '10条身体健康的“金标准”', '<p>一个人的身体是否健康</p><p style=\"text-align: justify;\">各项身体常见指标指数是最直接的判断</p><p style=\"text-align: justify;\">以下这些身体指标的正常值</p><p style=\"text-align: justify;\">以及维持指标正常的健康生活方式</p><p style=\"text-align: justify;\">每个人都应该知道！</p><p style=\"text-align: justify;\">赶紧自己对照一下！</p><p style=\"text-align: justify;\">10条身体健康的“金标准”</p><p style=\"text-align: justify;\">血压标准</p><p style=\"text-align: justify;\">收缩压＜120mmHg，舒张压＜80mmHg</p><p style=\"text-align: justify;\">根据中华医学会心血管病学分会等机构联合发布的《中国高血压防治指南(2018年修订版)》，我国成年人血压值的正常范围为：收缩压＜120mmHg，舒张压＜80mmHg。</p><p style=\"text-align: justify;\">血压不正常不仅会影响血管健康，还会影响心脏、大脑。《中国高血压患者教育指南》指出，高血压是脑卒中最重要的危险因素，我国70%的脑卒中患者有高血压。</p><p style=\"text-align: justify;\"><img src=\"https://p3-sign.toutiaoimg.com/pgc-image/SUnEYkXADWQXzc~noop.image?_iz=58558&amp;from=article.pc_detail&amp;lk3s=953192f4&amp;x-expires=1721543649&amp;x-signature=RuB5B1ApqxK6xu2fLl%2Bvs%2Bt4m%2BM%3D\" alt=\"\" data-href=\"\" style=\"height: auto;\"></p><p>图源：《中国高血压防治指南（2018年修订版）》</p>', 3, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=7d8b1cbshentijiankang.png', NULL, 0, '2025-04-16 14:34:58');
INSERT INTO `news` VALUES (6, '睡眠质量对健康的影响及改善方法', '<p>30年前……</p><p style=\"text-align: justify;\">癌症和心脏病离我们很远</p><p style=\"text-align: justify;\">很少听说糖尿病</p><p style=\"text-align: justify;\">医学教科书里没有“脂肪肝”</p><p style=\"text-align: justify;\">极少有精神和心理疾病</p><p style=\"text-align: justify;\">很少有人喝奶和吃奶粉</p><p style=\"text-align: justify;\">从来没有听说过补钙</p><p style=\"text-align: justify;\">只有逢年过节才喝酒助兴</p><p style=\"text-align: justify;\">几乎看不到胖子</p><p style=\"text-align: left;\"><br></p><p style=\"text-align: justify;\">现在……</p><p style=\"text-align: justify;\">每5个人就有一个患慢性病</p><p style=\"text-align: justify;\">每年增加1200万慢性病人</p><p style=\"text-align: justify;\">每5个成年人就有一个心血管病</p><p style=\"text-align: justify;\">每10秒就有一人死于心血管疾病</p><p style=\"text-align: left;\"><br></p><p style=\"text-align: justify;\">现在……</p><p style=\"text-align: justify;\">肺癌死亡率增加了5倍</p><p style=\"text-align: justify;\">地球上每3个肺癌就有一个在中国</p><p style=\"text-align: justify;\">烟民人数世界第一</p><p style=\"text-align: justify;\">喝酒人数世界第一</p><p style=\"text-align: justify;\">精神和心理疾病人数世界排名第一</p><p style=\"text-align: justify;\">糖尿病人数世界排名第一</p><p style=\"text-align: justify;\">所以我们今天整理了一篇健康大数据数据，以下是各大年龄段的健康数据情况！</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">中国人健康大数据概况</p><p style=\"text-align: justify;\">中国高血压人口有1.6——1.7亿人</p><p style=\"text-align: justify;\">高血脂的有将１亿多人</p><p style=\"text-align: justify;\">糖尿病患者达到9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症7000万——２亿人</p><p style=\"text-align: justify;\">血脂异常的1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者约1.2２亿人</p><p style=\"text-align: justify;\">平均每30秒就有一个人罹患癌症</p><p style=\"text-align: justify;\">平均每30秒就有一个人罹患糖尿病</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病</p><p style=\"text-align: justify;\">中国城市白领的健康堪忧</p><p style=\"text-align: justify;\">目前我国主流城市的白领亚健康比例高达76%，处于过劳状态的白领接近六成，<strong>真正意义上的健康人比例不足3%。</strong></p><p style=\"text-align: justify;\">白领女性更容易受到妇科、心脑血管疾病的威胁，男性则面临猝死、过劳、癌症等问题！</p><p style=\"text-align: justify;\">中国社科院《人才发展报告》：七种职业有过劳死的危险，如果中国知识分子不注意调整亚健康状态，不久的将来这些人中的2/3死于心脑血管疾病！</p><p style=\"text-align: justify;\"><img src=\"https://p3-sign.toutiaoimg.com/pgc-image/aa3e04dcf48349e290d9975aa17e431a~noop.image?_iz=58558&amp;from=article.pc_detail&amp;lk3s=953192f4&amp;x-expires=1721722113&amp;x-signature=wRHPZWa1UKZ7UI6UFFaakvp9Elk%3D\" alt=\"\" data-href=\"\" style=\"height: auto;\"></p><p><br></p><p style=\"text-align: justify;\">健康和医生治疗没有直接关系</p><p style=\"text-align: justify;\">美国研究证实：与美国人健康寿命相关的因素中，只有10%跟医疗相关！</p><p style=\"text-align: justify;\">导致美国人健康寿命延长30年中，有25年与医学没有关系。</p><p style=\"text-align: justify;\">普通疾病的误诊率高达27%左右，重大疾病的误诊率高达40%左右。</p><p style=\"text-align: justify;\">美国研究证实：高度发达的现代医学体系与人的健康没有太大关系。</p><p style=\"text-align: justify;\">英国研究证实：有85%的药品是无效的，对病人最好的措施就是尽量减少医疗干预。</p><p style=\"text-align: justify;\">美国研究证实：有30%——40%的手术根本不需要做！</p><p style=\"text-align: justify;\">药物的不良反应是对健康的重大威胁</p><p style=\"text-align: justify;\">《千手观音》23位主要演员中19位都是因药物不良反应导致聋哑。</p><p style=\"text-align: justify;\">著名的美国医学协会杂志（JAMA）曾经发布过一篇令人震惊的报告,在1994年因为严重的药物副作用而需要住院治疗的人数也超过了220万人。</p><p style=\"text-align: justify;\">更令人吃惊的是，这其中有超过10．6万人死于药物副作用。</p><p style=\"text-align: justify;\">这份报告在医学界一石激起千层浪。我们以前从不知道，会有如此之多的患者深受药物副作用的危害。</p><p style=\"text-align: justify;\">2006年9月12日是我国首个“预防出生缺陷日”，同年监测显示，我国每天有720个缺陷儿出生。其中很多是药物的不良反应造成的。</p><p style=\"text-align: justify;\"><br></p><p style=\"text-align: justify;\">第四次国家卫生服务调查结果显示</p><p style=\"text-align: justify;\">2008年，慢性病患病率就已达20%，死亡数已占总死亡数的83%。</p><p style=\"text-align: justify;\">过去十一年，平均每年新增慢性病例接近了２倍。心脏病和恶性肿瘤病例增加了近1倍！</p><p style=\"text-align: justify;\">美国《保健事物》杂志报告，中国人的腰围增长速度将成为世界之最。肥胖人口将达到3.25亿，未来20年将会增长一倍，腰围只要增长一英寸（2.54厘米），血管就会增长４英里，患癌风险高８倍！</p><p style=\"text-align: justify;\">中国青少年健康大数据</p><p style=\"text-align: justify;\">80%学生早餐营养质量较差</p><p style=\"text-align: justify;\">青春期贫血的发病率达38%</p><p style=\"text-align: justify;\">全国肥胖儿中脂肪肝发生率40—50%</p><p style=\"text-align: justify;\">小学生近视率32.5%</p><p style=\"text-align: justify;\">初中生59.4%</p><p style=\"text-align: justify;\">高中生77.3%</p><p>大学生80%</p>', 5, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=dccfac8shuimian.png', NULL, 0, '2025-04-17 16:09:06');
INSERT INTO `news` VALUES (7, '快节奏的现代生活，如同一只无形的手', '<p> </p><p style=\"text-align: justify;\">在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p>所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 7, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=227f53e83571caSnipaste_2024-11-16_18-44-44.png', NULL, NULL, '2025-04-18 16:10:35');
INSERT INTO `news` VALUES (8, '我们对健康的重视程度仍然远远不够', '<p> </p><p style=\"text-align: justify;\">在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p>所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 3, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=bce71dffc87fe3f5f0494jiankang.png', NULL, NULL, '2025-04-22 16:11:31');
INSERT INTO `news` VALUES (9, '慢性疾病的阴影逐渐笼罩在我们的生活之上', '<p> </p><p style=\"text-align: justify;\">在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p>所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 2, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=4937931shentijiankang.png', NULL, NULL, '2025-04-20 16:12:23');
INSERT INTO `news` VALUES (10, '如何建立健康的生活方式：有效的健康管理技巧', '<p> </p><p style=\"text-align: justify;\">在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p>所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 3, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=c1fdaf2f0890964.jpg', NULL, 0, '2025-04-19 16:12:56');
INSERT INTO `news` VALUES (11, '为了家人，为了亲朋，保健养生从现在做起！', '<p> 在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p style=\"text-align: justify;\">所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 8, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=fc87fe3f5f0494jiankang.png', NULL, 0, '2025-04-20 16:13:40');
INSERT INTO `news` VALUES (12, '探讨健康饮食对身体的重要性', '<p> 在过去，随着中国经济的高速发展，许多人的生活水平和物质条件得到了很大的改善。然而，这种快速发展的背后，却隐藏着一些被忽视的问题，其中较为突出的就是健康问题。当经济增速放缓，社会逐渐趋向和谐时，人们开始意识到，健康才是生活中更为重要的一部分。</p><p><br></p><p style=\"text-align: justify;\"><strong>中国人的健康数据，触目惊心！</strong></p><p style=\"text-align: justify;\">经过精心搜集与整理，我们获得了一组关于中国人健康状况的详尽数据。它们不仅仅是冷冰冰的统计结果，更像是一个个鲜活的生命在向我们诉说着健康的宝贵。</p><p style=\"text-align: justify;\">高血压——1.6-1.7亿人</p><p style=\"text-align: justify;\">高血脂——1亿多人</p><p style=\"text-align: justify;\">糖尿病患者——9240万人</p><p style=\"text-align: justify;\">超重或者肥胖症——7000万-----2亿人</p><p style=\"text-align: justify;\">血脂异常——1.6亿人</p><p style=\"text-align: justify;\">脂肪肝患者——1.2亿人</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患癌症，</p><p style=\"text-align: justify;\">平均每30秒，就有一个人罹患糖尿病，</p><p style=\"text-align: justify;\">平均每30秒，至少有一个人死于心脑血管疾病。</p><p style=\"text-align: justify;\">这些冰冷的数字背后，是无数家庭的痛苦和无奈。它们无声地诉说着一个残酷的真相：在繁忙的生活中，我们往往为了追逐金钱和物质享受，而忽略了身体的健康。当健康问题如暗流涌动，悄然而至时，我们才如梦初醒，惊觉已为时晚矣！</p><p style=\"text-align: justify;\"><strong>树立良好的健康理念</strong></p><p style=\"text-align: justify;\">快节奏的现代生活，如同一只无形的手，推动着我们不断前行，却也在不经意间，让我们生活方式和饮食习惯发生了很大的变化。作息不规律、高油高盐的饮食等，悄然侵蚀着我们的健康，使得慢性疾病的阴影逐渐笼罩在我们的生活之上。</p><p style=\"text-align: justify;\">更为严峻的是，我们对健康的重视程度仍然远远不够。在过去的日子里，人们紧紧盯着经济的发展和物质条件的改善，却忘记了健康才是生命的根基。我们需要的，不仅仅是医疗技术的日新月异，更需要的是健康理念的深入人心，健康行为的自觉养成。</p><p style=\"text-align: justify;\">健康对于每个人来说都是刻不容缓的。我们要珍惜自己的身体，我们要时刻提醒自己，健康才是重要的，不要等到失去健康之后才后悔莫及。 </p><p style=\"text-align: justify;\"><strong>补肽刻不容缓</strong></p><p style=\"text-align: justify;\">疾病，就像一位潜藏在暗处的狡猾猎手，随时准备向我们发动攻击。它的存在无声无息，却又无比强大，一旦被我们忽视，便有可能迅速蔓延，成为威胁我们生命安全的熊熊烈火。而补肽，这一健康管理的利器，正是我们对抗疾病、保持健康的重要武器。</p><p style=\"text-align: justify;\">补肽，顾名思义，就是补充身体所需的肽类物质。人体本身就含有小分子肽，但随着年龄的增长，肽类物质会逐渐减少，导致身体各项功能逐渐衰退。因此，及时补充肽类物质，对于保持身体健康具有重要意义。</p><p style=\"text-align: justify;\">小分子肽，如同一位贴心的守护者，为我们的身体健康筑起一道坚实的屏障，让我们在面对外界病邪时更有抵抗力，还能帮助身体更好地吸收营养，预防慢性疾病的发生。</p><p style=\"text-align: justify;\">记住，健康是我们宝贵的财富，只有拥有健康的身体，我们才能更好地享受生活、追求梦想。因为没有了健康，我们就无法享受生活的美好，无法陪伴家人，无法实现自己的梦想。</p><p style=\"text-align: justify;\">所以，为了自己，为了家人，为了亲朋，保健养生从现在做起！在这个充满挑战和机遇的时代里，让我们一起携手前行，用补肽这一健康管理利器守护我们的健康之路。</p>', 1, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=1a9e227shiwu.png', NULL, 1, '2025-04-20 16:14:27');
INSERT INTO `news` VALUES (13, '中医健康指导', '<p><br></p>', 4, 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=fb50f0fzhongyi.png', NULL, NULL, '2025-04-20 15:22:39');

-- ----------------------------
-- Table structure for news_save
-- ----------------------------
DROP TABLE IF EXISTS `news_save`;
CREATE TABLE `news_save`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '资讯的收藏ID',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `news_id` int NULL DEFAULT NULL COMMENT '资讯ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '收藏的时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 54 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '健康资讯收藏表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of news_save
-- ----------------------------
INSERT INTO `news_save` VALUES (40, 8, 3, '2024-10-05 14:46:48');
INSERT INTO `news_save` VALUES (41, 8, 1, '2024-10-05 14:46:54');
INSERT INTO `news_save` VALUES (45, 3, 8, '2024-11-18 18:32:57');
INSERT INTO `news_save` VALUES (47, 3, 2, '2024-11-18 20:29:04');
INSERT INTO `news_save` VALUES (51, 13, 12, '2025-05-10 18:00:36');
INSERT INTO `news_save` VALUES (52, 54, 10, '2025-05-17 14:55:56');

-- ----------------------------
-- Table structure for tags
-- ----------------------------
DROP TABLE IF EXISTS `tags`;
CREATE TABLE `tags`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '标签分类名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '标签分类表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of tags
-- ----------------------------
INSERT INTO `tags` VALUES (1, '心理健康');
INSERT INTO `tags` VALUES (2, '养生');
INSERT INTO `tags` VALUES (3, '运动');
INSERT INTO `tags` VALUES (4, '中医');
INSERT INTO `tags` VALUES (5, '身体健康');
INSERT INTO `tags` VALUES (6, '环境健康');
INSERT INTO `tags` VALUES (7, '职业健康');
INSERT INTO `tags` VALUES (8, '精神健康');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `user_account` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户账号',
  `user_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `user_pwd` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户密码',
  `user_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像',
  `user_email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `user_role` int NULL DEFAULT NULL COMMENT '用户角色',
  `is_login` tinyint(1) NULL DEFAULT NULL COMMENT '可登录状态(0：可用，1：不可用)',
  `is_word` tinyint(1) NULL DEFAULT NULL COMMENT '禁言状态(0：可用，1：不可用)',
  `create_time` datetime NULL DEFAULT NULL COMMENT '用户注册时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'aasss', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=d55e2e07b3d5ffpic_6.jpg', '1234567@qq.com', 1, 0, 0, '2024-07-09 12:53:05');
INSERT INTO `user` VALUES (3, 'yangshu', '健康系统', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=f63dce84b544d97.jpg', '1134123@qq.com', 2, 0, 0, '2024-10-07 23:59:31');
INSERT INTO `user` VALUES (8, 'aiqin', '深海', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=b024d50Snipaste_2024-11-29_18-23-12.png', '123@qq.coom', 2, 0, 1, '2024-10-07 23:59:31');
INSERT INTO `user` VALUES (9, 'wanghai', '大春', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=b40ef5dSnipaste_2024-11-29_18-23-18.png', '1243@qq.com', 1, 1, 0, '2024-10-07 23:59:31');
INSERT INTO `user` VALUES (10, 'meihua', '梅花', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=2c6fa89猫头鹰.png', '156456@qq.com', 2, 0, 0, '2024-10-07 23:59:31');
INSERT INTO `user` VALUES (11, 'hupeng', '胡鹏', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=a778bc4Snipaste_2024-11-29_18-23-12.png', '789789@qq.com', 1, 1, 0, '2024-11-18 13:00:14');
INSERT INTO `user` VALUES (12, 'zhanglan', '张兰', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=42bcf20Snipaste_2024-11-29_18-23-18.png', '43443@qq.com', 2, 0, 1, '2024-11-18 13:00:14');
INSERT INTO `user` VALUES (13, 'chenhao', '陈浩', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=b2b19b9熊猫.png', '12346578@qq.com', 2, 0, 0, '2024-10-25 16:16:40');
INSERT INTO `user` VALUES (14, 'liran', '李冉', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=f9275d7熊猫.png', '32323@qq.com', 1, 0, 0, '2024-11-18 13:00:14');
INSERT INTO `user` VALUES (15, 'liran1', 'liran1', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=907317eSnipaste_2024-11-22_00-46-48.png', '2121@qq.com', 2, 0, 0, '2024-11-18 13:00:30');
INSERT INTO `user` VALUES (16, 'zhenli', '陈立', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=3a7ef99Snipaste_2024-11-22_00-46-36.png', '32312@qq.com', 1, 0, 0, '2024-11-18 13:00:50');
INSERT INTO `user` VALUES (17, 'guihua', '桂花', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=5f522992c6fa89猫头鹰.png', '1212@qq.com', 2, 0, 0, '2024-11-18 13:01:18');
INSERT INTO `user` VALUES (18, 'chenghua', '橙黄橘', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=127ddf0pic_4.jpg', '432432@qq.com', 2, 0, 0, '2024-11-18 13:01:34');
INSERT INTO `user` VALUES (19, 'lineng', '力能', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=f93892142bcf20Snipaste_2024-11-29_18-23-18.png', '1232@qq.com', 2, 0, 0, '2024-11-18 14:15:19');
INSERT INTO `user` VALUES (20, '1221gthjhg', 'yonghu', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=4cdb8142fca8577b3d5ffpic_6.jpg', '124354@qq.com', 2, 0, 0, '2024-11-18 14:16:24');
INSERT INTO `user` VALUES (21, 'yingzi11', '张帆', '14e1b600b1fd579f47433b88e8d85291', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=feb79413a7ef99Snipaste_2024-11-22_00-46-36.png', '12432@qq.com', 2, 0, 0, '2024-11-18 14:17:35');
INSERT INTO `user` VALUES (54, 'q', 'q', '66eee9ace7508c154d02022000e1cf86', 'http://myimgs.openbase.store:21090/api/personal-heath/v1.0/file/getFile?fileName=99af8fcDSC_0386(1.JPG', NULL, 2, 0, 0, '2025-05-17 14:54:37');

-- ----------------------------
-- Table structure for user_health
-- ----------------------------
DROP TABLE IF EXISTS `user_health`;
CREATE TABLE `user_health`  (
  `id` int UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户健康记录主键',
  `user_id` int NULL DEFAULT NULL COMMENT '用户ID',
  `health_model_config_id` int NULL DEFAULT NULL COMMENT '健康模型ID',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT NULL COMMENT '输入的值',
  `create_time` datetime NULL DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 106 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '用户健康记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user_health
-- ----------------------------
INSERT INTO `user_health` VALUES (1, 3, 1, '23', '2024-07-13 13:13:42');
INSERT INTO `user_health` VALUES (2, 3, 2, '200', '2024-07-13 13:13:56');
INSERT INTO `user_health` VALUES (3, 3, 1, '43', '2024-07-03 13:14:05');
INSERT INTO `user_health` VALUES (5, 3, 2, '110', '2024-07-15 14:15:36');
INSERT INTO `user_health` VALUES (6, 3, 1, '167', '2024-07-15 14:15:36');
INSERT INTO `user_health` VALUES (8, 3, 4, '99', '2024-07-15 14:15:36');
INSERT INTO `user_health` VALUES (11, 3, 7, '76', '2024-07-15 14:43:32');
INSERT INTO `user_health` VALUES (12, 3, 4, '110', '2024-07-15 14:43:32');
INSERT INTO `user_health` VALUES (13, 3, 2, '110', '2024-07-15 14:43:32');
INSERT INTO `user_health` VALUES (14, 3, 1, '170', '2024-07-15 14:43:32');
INSERT INTO `user_health` VALUES (17, 3, 8, '71', '2024-07-16 16:15:39');
INSERT INTO `user_health` VALUES (18, 3, 7, '70', '2024-07-16 16:15:39');
INSERT INTO `user_health` VALUES (19, 3, 8, '188', '2024-07-16 16:16:07');
INSERT INTO `user_health` VALUES (20, 3, 7, '188', '2024-07-16 16:16:07');
INSERT INTO `user_health` VALUES (21, 3, 8, '66', '2024-07-16 16:16:19');
INSERT INTO `user_health` VALUES (22, 3, 7, '66', '2024-07-16 16:16:19');
INSERT INTO `user_health` VALUES (23, 3, 8, '73', '2024-07-16 16:16:45');
INSERT INTO `user_health` VALUES (24, 3, 7, '34', '2024-07-16 16:16:45');
INSERT INTO `user_health` VALUES (25, 3, 8, '201', '2024-07-16 16:17:04');
INSERT INTO `user_health` VALUES (26, 3, 7, '111', '2024-07-16 16:17:04');
INSERT INTO `user_health` VALUES (27, 3, 8, '88', '2024-07-16 16:17:32');
INSERT INTO `user_health` VALUES (28, 3, 7, '88', '2024-07-16 16:17:32');
INSERT INTO `user_health` VALUES (30, 3, 7, '70', '2024-07-16 16:30:34');
INSERT INTO `user_health` VALUES (31, 3, 8, '32', '2024-10-04 16:42:38');
INSERT INTO `user_health` VALUES (32, 3, 7, '32', '2024-10-04 16:42:38');
INSERT INTO `user_health` VALUES (33, 3, 8, '132', '2024-10-05 15:19:24');
INSERT INTO `user_health` VALUES (34, 3, 7, '111', '2024-10-05 15:19:24');
INSERT INTO `user_health` VALUES (35, 3, 9, '309', '2024-11-18 17:16:01');
INSERT INTO `user_health` VALUES (36, 3, 10, '4000', '2024-11-10 17:16:01');
INSERT INTO `user_health` VALUES (37, 3, 4, '120', '2024-11-18 17:16:01');
INSERT INTO `user_health` VALUES (38, 3, 7, '88', '2024-11-18 17:16:01');
INSERT INTO `user_health` VALUES (39, 3, 1, '166.3', '2024-11-18 17:16:01');
INSERT INTO `user_health` VALUES (40, 3, 11, '66', '2024-11-18 17:17:45');
INSERT INTO `user_health` VALUES (41, 3, 10, '4000', '2024-11-16 17:17:45');
INSERT INTO `user_health` VALUES (42, 3, 12, '100', '2024-11-18 17:18:45');
INSERT INTO `user_health` VALUES (43, 3, 11, '40', '2024-11-18 17:18:45');
INSERT INTO `user_health` VALUES (44, 3, 10, '8000', '2024-11-18 17:27:18');
INSERT INTO `user_health` VALUES (45, 3, 12, '99', '2024-11-12 17:28:34');
INSERT INTO `user_health` VALUES (46, 3, 12, '99', '2024-11-14 17:28:51');
INSERT INTO `user_health` VALUES (47, 3, 12, '69', '2024-11-17 17:29:04');
INSERT INTO `user_health` VALUES (48, 3, 12, '74', '2024-11-10 17:29:51');
INSERT INTO `user_health` VALUES (49, 3, 12, '52', '2024-11-07 17:30:31');
INSERT INTO `user_health` VALUES (50, 3, 12, '44', '2024-11-06 17:30:31');
INSERT INTO `user_health` VALUES (51, 3, 12, '66', '2024-11-04 17:30:31');
INSERT INTO `user_health` VALUES (52, 3, 12, '89', '2024-11-02 17:30:31');
INSERT INTO `user_health` VALUES (53, 3, 12, '55', '2024-10-21 17:30:31');
INSERT INTO `user_health` VALUES (54, 3, 8, '160', '2024-11-18 20:33:02');
INSERT INTO `user_health` VALUES (55, 17, 7, '60', '2024-11-18 20:33:02');
INSERT INTO `user_health` VALUES (56, 17, 10, '5000', '2024-11-18 20:33:02');
INSERT INTO `user_health` VALUES (57, 17, 11, '5', '2024-11-18 20:33:02');
INSERT INTO `user_health` VALUES (58, 17, 12, '45', '2024-11-18 20:34:01');
INSERT INTO `user_health` VALUES (60, 17, 12, '80', '2024-11-18 20:58:37');
INSERT INTO `user_health` VALUES (61, 17, 10, '5000', '2024-12-15 19:23:34');
INSERT INTO `user_health` VALUES (62, 17, 9, '500', '2024-12-15 19:23:34');
INSERT INTO `user_health` VALUES (63, 17, 8, '123', '2024-12-15 19:23:34');
INSERT INTO `user_health` VALUES (64, 17, 10, '10000', '2024-12-15 19:23:56');
INSERT INTO `user_health` VALUES (65, 17, 10, '4554', '2024-12-15 19:39:53');
INSERT INTO `user_health` VALUES (66, 17, 9, '4354', '2024-12-15 19:39:53');
INSERT INTO `user_health` VALUES (67, 17, 8, '132', '2024-12-15 19:39:53');
INSERT INTO `user_health` VALUES (68, 17, 12, '102', '2024-12-15 19:43:41');
INSERT INTO `user_health` VALUES (69, 17, 11, '90', '2024-12-15 19:43:41');
INSERT INTO `user_health` VALUES (70, 17, 12, '99', '2024-12-15 19:44:01');
INSERT INTO `user_health` VALUES (71, 17, 10, '4000', '2024-12-15 19:44:01');
INSERT INTO `user_health` VALUES (72, 17, 9, '500', '2024-12-15 19:44:01');
INSERT INTO `user_health` VALUES (73, 17, 8, '90', '2024-12-15 19:44:01');
INSERT INTO `user_health` VALUES (74, 17, 10, '5000', '2025-01-05 16:46:09');
INSERT INTO `user_health` VALUES (75, 17, 9, '500', '2025-01-05 16:46:09');
INSERT INTO `user_health` VALUES (76, 17, 8, '80', '2025-01-05 16:46:09');
INSERT INTO `user_health` VALUES (77, 17, 10, '7000', '2025-01-05 16:46:27');
INSERT INTO `user_health` VALUES (78, 17, 9, '500', '2025-01-05 16:46:27');
INSERT INTO `user_health` VALUES (79, 17, 8, '90', '2025-01-05 16:46:27');
INSERT INTO `user_health` VALUES (80, 17, 10, '13000', '2025-01-06 17:49:08');
INSERT INTO `user_health` VALUES (81, 17, 9, '500', '2025-01-06 17:49:08');
INSERT INTO `user_health` VALUES (82, 17, 10, '8000', '2025-01-06 17:53:05');
INSERT INTO `user_health` VALUES (83, 3, 8, '75', '2025-01-06 17:53:05');
INSERT INTO `user_health` VALUES (84, 3, 10, '7000', '2025-01-06 17:53:15');
INSERT INTO `user_health` VALUES (85, 3, 8, '80', '2025-01-06 17:53:15');
INSERT INTO `user_health` VALUES (86, 13, 10, '3000', '2025-04-20 15:17:41');
INSERT INTO `user_health` VALUES (87, 13, 10, '5000', '2025-04-20 15:17:57');
INSERT INTO `user_health` VALUES (88, 13, 10, '6000', '2025-04-20 15:18:08');
INSERT INTO `user_health` VALUES (89, 13, 10, '7000', '2025-04-20 15:18:33');
INSERT INTO `user_health` VALUES (90, 13, 10, '4000', '2025-04-20 15:18:47');
INSERT INTO `user_health` VALUES (91, 13, 7, '80', '2025-04-20 15:18:59');
INSERT INTO `user_health` VALUES (92, 13, 7, '90', '2025-04-20 15:19:12');
INSERT INTO `user_health` VALUES (93, 13, 7, '100', '2025-04-20 15:19:24');
INSERT INTO `user_health` VALUES (94, 13, 7, '120', '2025-04-20 15:19:35');
INSERT INTO `user_health` VALUES (95, 13, 7, '140', '2025-04-20 15:19:49');
INSERT INTO `user_health` VALUES (96, 13, 7, '120', '2025-04-20 15:19:58');
INSERT INTO `user_health` VALUES (97, 3, 10, '6789', '2025-04-23 15:37:22');
INSERT INTO `user_health` VALUES (98, 13, 9, '345', '2025-04-30 11:05:32');
INSERT INTO `user_health` VALUES (99, 13, 8, '999', '2025-05-07 15:36:58');
INSERT INTO `user_health` VALUES (100, 13, 10, '4555', '2025-05-10 17:59:25');
INSERT INTO `user_health` VALUES (101, 13, 10, '5555', '2025-05-10 18:00:04');
INSERT INTO `user_health` VALUES (102, 13, 9, '5555', '2025-05-10 18:00:04');
INSERT INTO `user_health` VALUES (103, 13, 8, '89', '2025-05-10 18:00:04');
INSERT INTO `user_health` VALUES (104, 13, 7, '88', '2025-05-10 18:00:04');
INSERT INTO `user_health` VALUES (105, 13, 10, '6666', '2025-05-10 18:58:42');

SET FOREIGN_KEY_CHECKS = 1;
