CREATE TABLE `users` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `userpwd` varchar(50) DEFAULT NULL,
  `version` bigint(11) DEFAULT NULL,
  `create_time` bigint(11) DEFAULT NULL,
  `update_time` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
CREATE TABLE `users_version` (
  `id` bigint(11) unsigned NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `userpwd` varchar(50) DEFAULT NULL,
  `version` bigint(11) DEFAULT NULL,
  `create_time` bigint(11) DEFAULT NULL,
  `update_time` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`id`,`version`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;