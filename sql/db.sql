use lightHouse_api;
-- 接口信息
create table if not exists lightHouse_api.`interface_info`
(
    `id`              bigint                             not null auto_increment comment '主键' primary key,
    `name`            varchar(256)                       not null comment '接口名称',
    `url`             varchar(512)                       not null comment '接口地址',
    `request_params`  text                               null comment '请求参数',
    `method`          varchar(256)                       not null comment '请求类型',
    `request_header`  text                               null comment '请求头',
    `response_header` text                               null comment '响应头',
    `user_id`         bigint                             not null comment '创建人id',
    `description`     varchar(512)                       null comment '描述',
    `status`          tinyint  default 0                 not null comment '接口状态 0-关闭 1-开启',
    `create_time`     datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    `update_time`     datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    `deleted`         tinyint  default 0                 not null comment '是否删除(0-未删, 1-已删)'
) comment '接口信息';

insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('LZ', 'www.lavern-funk.org', 'PUT', 1);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('S0kQr', 'www.wallace-bins.name', 'GET', 2);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('eqoe', 'www.shenita-bogisich.org', 'GET', 3);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('QeG', 'www.colby-heidenreich.biz', 'GET', 4);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('xibm4', 'www.raye-trantow.net', 'POST', 5);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('caG', 'www.sylvester-schinner.com', 'DELETE', 6);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('C3iD', 'www.jonah-rice.net', 'POST', 7);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('9E', 'www.bryan-mohr.biz', 'GET', 8);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('A05UW', 'www.valencia-connelly.name', 'DELETE', 9);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('iBPIG', 'www.broderick-skiles.net', 'DELETE', 10);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('alR', 'www.romeo-heathcote.net', 'POST', 11);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('KoxA', 'www.buford-tromp.org', 'POST', 12);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('7O', 'www.hye-roob.io', 'DELETE', 13);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('dmX', 'www.hilario-heller.io', 'DELETE', 14);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('6ziUu', 'www.shanika-kohler.co', 'GET', 15);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('vbIWP', 'www.randy-deckow.org', 'PUT', 16);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('hc', 'www.freeman-crist.co', 'GET', 17);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('NgMa', 'www.glynis-welch.biz', 'GET', 18);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('euw', 'www.danika-pfeffer.io', 'DELETE', 19);
insert into lightHouse_api.`interface_info` (`name`, `url`, `method`, `user_id`)
values ('9h0', 'www.devorah-bernier.com', 'POST', 20);


create table if not exists user
(
    id            bigint auto_increment comment 'id' primary key,
    user_account  varchar(256)                           not null comment '账号',
    user_password varchar(512)                           not null comment '密码',
    union_id      varchar(256)                           null comment '微信开放平台id',
    mp_open_id    varchar(256)                           null comment '公众号openId',
    user_name     varchar(256)                           null comment '用户昵称',
    user_avatar   varchar(1024)                          null comment '用户头像',
    user_profile  varchar(512)                           null comment '用户简介',
    user_role     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    access_key    varchar(512)                           not null comment 'accessKey',
    secret_key    varchar(512)                           not null comment 'secretKey',
    create_time   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    deleted       tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (union_id)
) comment '用户' collate = utf8mb4_unicode_ci;