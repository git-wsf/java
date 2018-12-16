CREATE TABLE `tb_users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nickname` varchar(50) DEFAULT '',
  `openId` varchar(32) NOT NULL,
  `sex` varchar(32) DEFAULT '',
  `province` varchar(32) DEFAULT '',
  `city` varchar(32) DEFAULT '',
  `country` varchar(32) DEFAULT '',
  `headimgurl` varchar(200) DEFAULT '',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  `createIp` varchar(20) DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `LOGIN_INDEX` (`openId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;