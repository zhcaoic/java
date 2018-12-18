use TianDogDB;

drop table if exists `user`;
create table `user`(
	`id` bigint(20) not null auto_increment,
    `name` varchar(45) not null,
    `password` varchar(45) not null,
    `login_time` datetime default null,
    `create_time` datetime default null,
    `update_time` datetime default null,
    primary key (`id`),
    unique key `UNIQUE_KEY_name` (`name`)
) engine=InnoDB default charset=utf8;

drop table if exists `user_basic_info`;
create table `user_basic_info`(
	`id` bigint(20) not null auto_increment,
    `user_id` bigint(20) not null,
    `real_name` varchar(45) default null,
    `email` varchar(45) default null,
    `phone` varchar(45) default null,
    `age` int(11) default null,
    `address` varchar(45) default null,
    `occupation` varchar(45) default null comment '职业',
    primary key (`id`),
    foreign key (`user_id`) references user(`id`) on update cascade on delete cascade
)engine=InnoDB default charset=utf8;