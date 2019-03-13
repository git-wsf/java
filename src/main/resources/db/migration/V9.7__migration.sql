CREATE TABLE `tb_visit` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shopId` varchar(20) DEFAULT NULL,
  `userId` int(11) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTIme` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4250 DEFAULT CHARSET=utf8mb4;