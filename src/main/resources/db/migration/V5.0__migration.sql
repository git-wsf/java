CREATE TABLE `tb_reserve` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shop` varchar(20) DEFAULT NULL,
  `product` varchar(50) DEFAULT NULL,
  `name` varchar(11) DEFAULT NULL,
  `mobile` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;