CREATE TABLE `tb_shop` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `shopId` varchar(20) DEFAULT NULL,
  `shopName` varchar(20) DEFAULT NULL,
  `level` varchar(11) DEFAULT NULL,
  `address` varchar(50) DEFAULT NULL,
  `day` int(11) DEFAULT NULL,
  `dayCountryCount` int(11) DEFAULT NULL,
  `dayProvinceCount` int(11) DEFAULT NULL,
  `week` int(11) DEFAULT NULL,
  `weekCountryCount` int(11) DEFAULT NULL,
  `weekProvinceCount` int(11) DEFAULT NULL,
  `weekLevelCount` int(11) DEFAULT NULL,
  `spring` int(11) DEFAULT NULL,
  `springCountryCount` int(11) DEFAULT NULL,
  `springProvinceCount` int(11) DEFAULT NULL,
  `springLevelCount` int(11) DEFAULT NULL,
  `percent` varchar(10) DEFAULT NULL,
  `ddd` varchar(20) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTIme` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;