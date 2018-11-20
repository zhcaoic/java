create database if not exists `saledb` default character set utf8;
use `saledb`;



-- WEB SITE DATABASE TABLES
--
-- Table structure for table `deal`
--

drop table if exists `deal`;
create table `deal`(
	`id` bigint(20) not null auto_increment,
    `area_id` bigint(20) not null,
    `area_name` varchar(64) not null,
    `sku_id` bigint(20) not null comment '商品ID',
    `deal_class` int(2) not null,
    `merchant_id` bigint(20) not null comment '厂商ID',
    `merchant_sku` bigint(20) not null,
    `deal_title` varchar(200) not null comment '商品标题',
    `deal_price` decimal(10,0) not null comment '商品价格',
    `merchant_price` decimal(10,0) not null comment '进货价',
    `market_price` decimal(10,0) not null comment '市场价',
    `settlement_price` decimal(10,0) not null,
    `settlement_price_max` decimal(10,0) default null comment '最大可接受结算价',
    `discount` int(3) default null comment '折扣',
    `bonus_points` int(5) default null comment '积分',
    `deal_type` int(3) not null comment '商品类型',
    `image_id` bigint(20) default '0' comment '对应商品图片',
    `deal_level` int(4) not null comment '商品优先级',
    `max_purchase_count` int(4) default null comment '商品最大可购买数量',
    `publish_status` int(2) not null comment '发布状态',
    `inventory_amount` int(4) not null comment '商品库存数量',
    `vendibility_amount` int(4) not null comment '商品可售数量',
    `oos_status` int(2) not null comment '商品售空标识',
    `start_time` datetime not null comment '销售开始时间',
    `end_time` datetime default null comment '销售结束时间',
    `publish_time` datetime default null comment '发布时间',
    `merchant_code` varchar(15) default null comment '商家编码',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    `category_id` bigint(20) unsigned not null comment '商品类别ID',
    primary key (`id`),
    unique key `deal_sku_UNIQUE` (`sku_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `deal_detail`
--

drop table if exists `deal_detail`;
create table `deal_detail` (
	`id` bigint(20) not null,
    `deal_id` bigint(20) not null,
    `deal_detail` varchar(8000) default null,
    primary key (`id`),
    unique key `detail_deal_id_UNIQUE` (`deal_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `deal_category`
--

drop table if exists `deal_category`;
create table `deal_category` (
	`id` bigint(20) not null auto_increment comment 'ID',
    `parent_id` bigint(20) not null comment '父ID',
    `name` varchar(100) not null comment '名称',
    `url_name` varchar(32) not null comment '分类URL名称',
    `publish_status` int(2) not null comment '发布状态',
    `create_time` datetime not null comment '创建时间',
    `order_num` int(10) unsigned not null comment '排序号码',
    `deep` int(10) unsigned not null comment '层次深度',
    primary key (`id`),
    unique key `deal_category_url_name_UNIQUE` (`url_name`),
    unique key `deal_category_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `area`
--

drop table if exists `area`;
create table `area` (
	`id` bigint(20) not null auto_increment,
    `name` varchar(32) not null comment '名称',
    `parent_id` bigint(20) not null,
    `common` int(4) not null,
    `type` varchar(16) not null comment '类型: 省，市',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `area_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `image_info`
--

drop table if exists `image_info`;
create table `image_info` (
	`id` bigint(20) not null auto_increment,
    `width` int(4) default null comment '图片的宽',
    `height` int(4) default null comment '图片的高',
    `source_path` varchar(100) default null comment '图片的资源路径',
    primary key (`id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `cart`
--

drop table if exists `cart`;
create table `cart` (
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `deal_id` bigint(20) not null,
    `deal_sku_id` bigint(20) not null,
    `count` int(4) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `cart_user_id` (`user_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `order_basic`
--

drop table if exists `order_basic`;
create table `order_basic`(
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null comment '用户ID',
    `order_status` int(4) not null comment '订单状态',
    `total_price` int(11) not null comment '订单总价',
    `total_settlement_price` int(11) not null comment '订单结算价格',
    `address` mediumtext comment '收货地址',
    `receiver` varchar(128) not null comment '收件人',
    `phone` varchar(20) not null comment '联系电话',
    `pay_type` int(2) default '0' comment '支付方式： 1--微信支付；2--支付宝支付；3--货到付款',
    `create_time` datetime not null,
    `update_time` datetime not null,
    primary key (`id`),
    key `order_user_INDEX` (`user_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `order_detail`
--

drop table if exists `order_detail`;
create table `order_detail` (
	`id` bigint(20) not null auto_increment,
    `order_id` bigint(20) not null comment '订单ID',
    `user_id` bigint(20) not null comment '用户ID',
    `merchant_sku` int(20) default null comment '商家商品sku',
    `merchant_id` bigint(20) default null comment '厂商ID',
	`merchant_code` varchar(32) default null comment '商家编码',
    `deal_id` bigint(20) not null comment '商品ID',
    `deal_sku_id` bigint(20) not null,
    `deal_img_id` bigint(20) not null,
    `deal_title` varchar(200) not null comment '商品标题',
    `deal_count` int(11) not null comment '商品数量',
    `deal_price` int(11) not null comment '商品单价',
    `total_price` int(11) not null comment '订单总价',
    `settlement_price` int(11) not null,
    `total_settlement_price` int(11) not null comment '订单结算价格',
    `detail_status` int(11) not null comment '订单详情状态',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `detail_user_id_INDEX` (`user_id`),
    key `detail_order_id_INDEX` (`order_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `user`
--

drop table if exists `user`;
create table `user` (
	`id` bigint(20) not null auto_increment,
    `name` varchar(45) not null,
    `password` varchar(45) not null,
    `login_time` datetime not null,
    `create_time` datetime not null,
    `update_time` datetime not null,
    primary key (`id`),
    unique key `user_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `user_basic_info`
--

drop table if exists `user_basic_info`;
create table `user_basic_info` (
	`id` int(10) not null,
    `nickname` varchar(32) not null comment '昵称',
    `real_name` varchar(32) not null,
    `mail` varchar(32) not null,
    `phone` varchar(16) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `address`
--

drop table if exists `address`;
create table `address` (
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `receiver` varchar(128) not null comment '收件人',
    `area` varchar(256) not null,
    `detail` varchar(256) not null,
    `type` varchar(8) not null,
    `phone` varchar(16) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `INDEX_USER_ID` (`user_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `favorite`
--

drop table if exists `favorite`;
create table `favorite` (
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `deal_id` bigint(20) not null comment '商品ID',
    `deal_sku_id` bigint(20) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `favorite_user_deal_id` (`user_id`, `deal_id`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `message`
--

drop table if exists `message`;
create table `message` (
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `title` varchar(64) not null,
    `content` varchar(256) not null,
    `readed` varchar(4) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `message_user_INDEX` (`user_id`)
) engine=InnoDB default charset=utf8;






-- MANAGE DATABASE TABLES
--
-- Table structure for table `admin_user`
--

drop table if exists `admin_user`;
create table `admin_user` (
	`id` bigint(20) not null auto_increment,
    `name` varchar(50) not null comment '管理员名称',
    `password` varchar(50) not null,
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `admin_user_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `admin_role`
--

drop table if exists `admin_role`;
create table `admin_role` (
	`id` bigint(20) not null auto_increment,
    `name` varchar(50) not null comment '角色名',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `admin_role_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `admin_function`
--

drop table if exists `admin_function`;
create table `admin_function` (
	`id` bigint(20) not null auto_increment,
    `name` varchar(32) not null,
    `state` varchar(10) not null default 'open' comment '状态， open/close',
    `parent_id` int(10) not null comment '父节点ID',
    `url` varchar(64) not null comment '链接',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `admin_func_url_UNIQUE` (`url`)
) engine=InnoDB default charset=utf8 comment='ERP菜单表';

--
-- Table structure for table `admin_role_function`
--

drop table if exists `admin_role_function`;
create table `admin_role_function` (
	`id` bigint(20) not null auto_increment,
    `admin_role_id` int(10) not null comment '角色ID',
    `admin_func_id` int(10) not null comment '功能ID',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `admin_role_func_UNIQUE` (`admin_role_id`, `admin_func_id`)
) engine=InnoDB default charset=utf8 comment='admin角色功能树对应关系表';

--
-- Table structure for table `admin_user_role`
--

drop table if exists `admin_user_role`;
create table `admin_user_role` (
	`id` bigint(20) not null auto_increment,
    `admin_user_id` int(10) not null comment '用户ID',
    `admin_role_id` int(10) not null comment '角色ID',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    key `admin_user_role_id` (`admin_user_id`, `admin_role_id`)
) engine=InnoDB default charset=utf8 comment='admin用户角色对应关系表';


--
-- Table structure for table `merchant`
--

drop table if exists `merchant`;
create table `merchant` (
	`id` bigint(20) not null auto_increment comment '商家ID',
    `name` varchar(32) not null default '' comment '商家名称',
    `description` varchar(200) not null default '' comment '商家描述',
    `image_id` bigint(20) not null comment '关联图片ID',
    `level` int(4) not null comment '商家级别',
    `hot_level` int(4) not null comment '热度等级',
    `status` int(2) not null comment '发布状态',
    `url` varchar(100) not null comment '商家URL',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `merchant_name_UNIQUE` (`name`)
) engine=InnoDB default charset=utf8;

--
-- Table structure for table `start_remind`
--

drop table if exists `start_remind`;
create table `start_remind` (
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `deal_id` varchar(64) not null,
    `deal_sku_id` varchar(64) not null,
    `deal_title` varchar(200) not null,
    `start_time` datetime not null comment '开团时间',
    `create_time` datetime not null comment '创建时间',
    `update_time` datetime not null comment '更新时间',
    primary key (`id`),
    unique key `remind_user_deal_UNIQUE` (`user_id`, `deal_id`),
    key `remind_user_INDEX` (`user_id`)
) engine=InnoDB default charset=utf8;




