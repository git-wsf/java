use test;
CREATE TABLE `tb_service_registration` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `kid_id` bigint(20) NOT NULL COMMENT '孩子',
  `service_type` varchar(16) NOT NULL COMMENT '课程类型',
  `mark` int(11) NOT NULL COMMENT '位操作，标记',
  `status` int(11) NOT NULL COMMENT '位操作，状态；001开启，010关闭',
  `version` int(11) NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_child_service` (`kid_id`,`service_type`)
) ENGINE=InnoDB AUTO_INCREMENT=3886 DEFAULT CHARSET=utf8mb4