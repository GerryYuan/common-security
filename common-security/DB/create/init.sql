CREATE TABLE `common_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id自增主键',
  `name` varchar(45) NOT NULL COMMENT '角色名称',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '角色状态，0：正常，1：冻结',
  `create_time` datetime NOT NULL COMMENT '更新时间',
  `update_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

CREATE TABLE `common_permission` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键Id',
  `name` varchar(50) NOT NULL COMMENT '权限名称',
  `url` varchar(80) NOT NULL COMMENT '权限标识',
  `method` varchar(20) NOT NULL COMMENT '请求方法类型',
  `parent_id` int(20) NOT NULL DEFAULT '0' COMMENT '父级权限Id',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `url_method` (`url`,`method`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='权限表';
