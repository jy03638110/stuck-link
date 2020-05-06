DROP DATABASE IF EXISTS stuck_link;

CREATE TABLE IF NOT EXISTS `link_url_config` (
  `code` varchar(10) NOT NULL,
  `short_url` varchar(50) DEFAULT NULL COMMENT '短链地址',
  `mulit_clients` int(11) NOT NULL DEFAULT '0',
  `default_url` text COMMENT '默认URL',
  `pc_url` text COMMENT 'PC机URL',
  `apple_url` text COMMENT '苹果URL',
  `create_user_code` varchar(20) NOT NULL COMMENT '-1 游客',
  `create_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `invalid_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '失效时间',
  PRIMARY KEY (`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS `link_message` (
  `visit_time` datetime NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `message_type` tinyint(4) DEFAULT NULL COMMENT '消息类型，1:短链访问，2:二维码访问，3:匿名访问',
  `request_url` varchar(255) DEFAULT NULL,
  `query_string` text COMMENT '查询字符串',
  `referer` varchar(255) DEFAULT NULL COMMENT '来源url',
  `remote_addr` varchar(30) DEFAULT NULL COMMENT '远程地址',
  `remote_host` varchar(20) DEFAULT NULL COMMENT '远程IP',
  `local_addr` varchar(30) DEFAULT NULL COMMENT '本地IP',
  `local_name` varchar(30) DEFAULT NULL COMMENT '本机名',
  `user_agent` varchar(255) DEFAULT NULL COMMENT 'UserAgent',
  `remote_user` varchar(30) DEFAULT NULL COMMENT '远程用户',
  `os_family` varchar(20) DEFAULT NULL COMMENT '操作系统家族',
  `os_name` varchar(30) DEFAULT NULL COMMENT '操作系统详细名称',
  `ua_name` varchar(30) DEFAULT NULL COMMENT '浏览器名称和版本',
  `ua_type` varchar(20) DEFAULT NULL COMMENT '类型',
  `ua_family` varchar(20) DEFAULT NULL COMMENT '浏览器名称',
  `browser_version` varchar(30) DEFAULT NULL COMMENT '浏览器版本',
  `device_type` varchar(30) DEFAULT NULL COMMENT '设备类型',
  KEY `p_index` (`visit_time`,`remote_addr`,`local_addr`) USING HASH
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
