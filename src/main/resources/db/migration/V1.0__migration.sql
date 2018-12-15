CREATE TABLE `file_info` (
  `id` varchar(32) NOT NULL,
  `contentType` varchar(128) NOT NULL,
  `size` int(11) NOT NULL,
  `path` varchar(255) NOT NULL,
  `url` varchar(1024) NOT NULL,
  `type` int(1) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `sys_logs` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userId` int(11) NOT NULL,
  `module` varchar(50) DEFAULT NULL,
  `flag` tinyint(4) NOT NULL DEFAULT '1',
  `remark` text,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `createTime` (`createTime`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `parentId` int(11) NOT NULL,
  `name` varchar(50) NOT NULL,
  `css` varchar(30) DEFAULT NULL,
  `href` varchar(1000) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `permission` varchar(50) DEFAULT NULL,
  `sort` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_permission` (`id`, `parentId`, `name`, `css`, `href`, `type`, `permission`, `sort`)
VALUES
	(1,0,'用户管理','fa-users','',1,'',1),
	(2,1,'用户查询','fa-user','pages/user/userList.html',1,'',2),
	(3,2,'查询','','',2,'sys:user:query',100),
	(4,2,'新增','','',2,'sys:user:add',100),
	(6,0,'修改密码','fa-pencil-square-o','pages/user/changePassword.html',1,'sys:user:password',4),
	(7,0,'系统设置','fa-gears','',1,'',5),
	(8,7,'菜单','fa-cog','pages/menu/menuList.html',1,'',6),
	(9,8,'查询','','',2,'sys:menu:query',100),
	(10,8,'新增','','',2,'sys:menu:add',100),
	(11,8,'删除','','',2,'sys:menu:del',100),
	(12,7,'角色','fa-user-secret','pages/role/roleList.html',1,'',7),
	(13,12,'查询','','',2,'sys:role:query',100),
	(14,12,'新增','','',2,'sys:role:add',100),
	(15,12,'删除','','',2,'sys:role:del',100),
	(16,0,'文件管理','fa-folder-open','pages/file/fileList.html',1,'',8),
	(17,16,'查询','','',2,'sys:file:query',100),
	(18,16,'删除','','',2,'sys:file:del',100),
	(22,0,'公告管理','fa-book','pages/notice/noticeList.html',1,'',12),
	(23,22,'查询','','',2,'notice:query',100),
	(24,22,'添加','','',2,'notice:add',100),
	(25,22,'删除','','',2,'notice:del',100),
	(26,0,'日志查询','fa-reorder','pages/log/logList.html',1,'sys:log:query',13),
	(37,0,'字典管理','fa-reddit','pages/dict/dictList.html',1,'',17),
	(38,37,'查询','','',2,'dict:query',100),
	(39,37,'新增','','',2,'dict:add',100),
	(40,37,'删除','','',2,'dict:del',100);

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `description` varchar(100) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


INSERT INTO `sys_role` (`id`, `name`, `description`, `createTime`, `updateTime`)
VALUES
	(1,'ADMIN','管理员','2017-05-01 13:25:39','2018-09-26 20:45:31');


CREATE TABLE `sys_role_permission` (
  `roleId` int(11) NOT NULL,
  `permissionId` int(11) NOT NULL,
  PRIMARY KEY (`roleId`,`permissionId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_role_permission` (`roleId`, `permissionId`) VALUES (1,1), (1,2), (1,3), (1,4), (1,6), (1,7), (1,8), (1,9), (1,10), (1,11), (1,12), (1,13), (1,14), (1,15), (1,16), (1,17), (1,18), (1,22), (1,23), (1,24), (1,25), (1,26), (1,37), (1,38), (1,39), (1,40);

CREATE TABLE `sys_role_user` (
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_role_user` (`userId`, `roleId`)
VALUES
	(1,1);

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(60) DEFAULT '',
  `nickname` varchar(255) DEFAULT NULL,
  `headImgUrl` varchar(255) DEFAULT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `telephone` varchar(30) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `sys_user` (`id`, `username`, `password`, `nickname`, `headImgUrl`, `phone`, `telephone`, `email`, `birthday`, `sex`, `status`, `createTime`, `updateTime`)
VALUES
	(1,'admin','$2a$10$iYM/H7TrSaLs7XyIWQdGwe1xf4cdmt3nwMja6RT0wxG5YY1RjN0EK','管理员',NULL,'','','','1998-07-01',0,1,'2017-04-10 15:21:38','2017-07-06 09:20:19');

CREATE TABLE `tb_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL,
  `k` varchar(16) NOT NULL,
  `val` varchar(64) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `type` (`type`,`k`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `tb_dict` (`id`, `type`, `k`, `val`, `createTime`, `updateTime`)
VALUES
	(1,'sex','0','女','2017-11-17 09:58:24','2017-11-18 14:21:05'),
	(2,'sex','1','男','2017-11-17 10:03:46','2017-11-17 10:03:46'),
	(3,'userStatus','0','无效','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(4,'userStatus','1','正常','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(5,'userStatus','2','锁定','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(6,'noticeStatus','0','草稿','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(7,'noticeStatus','1','发布','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(8,'isRead','0','未读','2017-11-17 16:26:06','2017-11-17 16:26:09'),
	(9,'isRead','1','已读','2017-11-17 16:26:06','2017-11-17 16:26:09');

CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) NOT NULL,
  `content` text NOT NULL,
  `status` tinyint(1) NOT NULL DEFAULT '1',
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tb_notice_read` (
  `noticeId` int(11) NOT NULL,
  `userId` int(11) NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`noticeId`,`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `tb_token` (
  `id` varchar(36) NOT NULL,
  `val` text NOT NULL,
  `expireTime` datetime NOT NULL,
  `createTime` datetime DEFAULT NULL,
  `updateTime` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;