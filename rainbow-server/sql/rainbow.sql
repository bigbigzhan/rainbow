-- ----------------------------
-- 1、部门表
-- ----------------------------
drop table if exists sys_dept;
create table sys_dept (
  dept_id           bigint(20)      not null auto_increment    comment '部门id',
  parent_id         bigint(20)      default 0                  comment '父部门id',
  ancestors         varchar(50)     default ''                 comment '祖级列表',
  dept_name         varchar(30)     default ''                 comment '部门名称',
  order_num         int(4)          default 0                  comment '显示顺序',
  leader            varchar(20)     default null               comment '负责人',
  phone             varchar(11)     default null               comment '联系电话',
  email             varchar(50)     default null               comment '邮箱',
  status            char(1)         default '0'                comment '部门状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (dept_id)
) engine=innodb auto_increment=200 comment = '部门表';

-- ----------------------------
-- 初始化-部门表数据
-- ----------------------------
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (100, 0, '0', 'XX科技', 0, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (101, 100, '0,100', '深圳总公司', 1, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (102, 100, '0,100', '长沙分公司', 2, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (103, 101, '0,100,101', '研发部门', 1, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (104, 101, '0,100,101', '市场部门', 2, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (105, 101, '0,100,101', '测试部门', 3, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (106, 101, '0,100,101', '财务部门', 4, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (107, 101, '0,100,101', '运维部门', 5, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (108, 102, '0,100,102', '市场部门', 1, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');
INSERT INTO  sys_dept (dept_id, parent_id, ancestors, dept_name, order_num, leader, phone, email, status, del_flag, create_by, create_time, update_by, update_time) VALUES (109, 102, '0,100,102', '财务部门', 2, 'GZ', '15888888888', 'ry@qq.com', '0', '0', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00');

-- ----------------------------
-- 2、用户信息表
-- ----------------------------
drop table if exists sys_user;
create table sys_user (
  user_id           bigint(20)      not null auto_increment    comment '用户ID',
  dept_id           bigint(20)      default null               comment '部门ID',
  login_name        varchar(30)     not null                   comment '登录账号',
  user_name         varchar(30)     default ''                 comment '用户昵称',
  user_type         varchar(2)      default '00'               comment '用户类型（00系统用户 01注册用户）',
  email             varchar(50)     default ''                 comment '用户邮箱',
  phonenumber       varchar(11)     default ''                 comment '手机号码',
  sex               char(1)         default '0'                comment '用户性别（0男 1女 2未知）',
  avatar            varchar(100)    default ''                 comment '头像路径',
  password          varchar(50)     default ''                 comment '密码',
  salt              varchar(20)     default ''                 comment '盐加密',
  status            char(1)         default '0'                comment '帐号状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  login_ip          varchar(50)     default ''                 comment '最后登陆IP',
  login_date        datetime                                   comment '最后登陆时间',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (user_id)
) engine=innodb auto_increment=100 comment = '用户信息表';

-- ----------------------------
-- 初始化-用户信息表数据
-- ----------------------------
INSERT INTO  sys_user (user_id, dept_id, login_name, user_name, user_type, email, phonenumber, sex, avatar, password, salt, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark) VALUES (1, 103, 'admin', 'rainbow', '00', 'G@163.com', '15888888888', '1', '/profile/avatar/2020/07/16/515cc40b8fd08d3bc78c9a74dc097f90.png', '29c67a30398638269fe600f73a054934', '111111', '0', '0', '127.0.0.1', '2020-07-19 12:08:40', 'admin', '2018-03-16 11:33:00', 'ry', '2020-07-19 04:08:40', '管理员');
INSERT INTO  sys_user (user_id, dept_id, login_name, user_name, user_type, email, phonenumber, sex, avatar, password, salt, status, del_flag, login_ip, login_date, create_by, create_time, update_by, update_time, remark) VALUES (100, 108, 'gz', 'gz', '00', 'yanbr@email.arizo', '17621276053', '0', '', '1affcfad61b42be47192718033092c5b', '00e7bb', '0', '0', '127.0.0.1', '2020-07-02 23:07:46', 'admin', '2020-07-02 15:07:23', '', '2020-07-02 15:07:43', null);

-- ----------------------------
-- 3、岗位信息表
-- ----------------------------
drop table if exists sys_post;
create table sys_post
(
  post_id       bigint(20)      not null auto_increment    comment '岗位ID',
  post_code     varchar(64)     not null                   comment '岗位编码',
  post_name     varchar(50)     not null                   comment '岗位名称',
  post_sort     int(4)          not null                   comment '显示顺序',
  status        char(1)         not null                   comment '状态（0正常 1停用）',
  create_by     varchar(64)     default ''                 comment '创建者',
  create_time   datetime                                   comment '创建时间',
  update_by     varchar(64)     default ''			       comment '更新者',
  update_time   datetime                                   comment '更新时间',
  remark        varchar(500)    default null               comment '备注',
  primary key (post_id)
) engine=innodb comment = '岗位信息表';

-- ----------------------------
-- 初始化-岗位信息表数据
-- ----------------------------
insert into sys_post values(1, 'ceo',  '董事长',    1, '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');
insert into sys_post values(2, 'se',   '项目经理',  2, '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');
insert into sys_post values(3, 'hr',   '人力资源',  3, '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');
insert into sys_post values(4, 'user', '普通员工',  4, '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');


-- ----------------------------
-- 4、角色信息表
-- ----------------------------
drop table if exists sys_role;
create table sys_role (
  role_id           bigint(20)      not null auto_increment    comment '角色ID',
  role_name         varchar(30)     not null                   comment '角色名称',
  role_key          varchar(100)    not null                   comment '角色权限字符串',
  role_sort         int(4)          not null                   comment '显示顺序',
  data_scope        char(1)         default '1'                comment '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
  status            char(1)         not null                   comment '角色状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0代表存在 2代表删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (role_id)
) engine=innodb auto_increment=100 comment = '角色信息表';

-- ----------------------------
-- 初始化-角色信息表数据
-- ----------------------------
insert into sys_role values('1', '系统管理员', 'admin',  1, 1, '0', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统管理员');
insert into sys_role values('2', '普通角色',   'common', 2, 2, '0', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '普通角色');


-- ----------------------------
-- 5、菜单权限表
-- ----------------------------
drop table if exists sys_menu;
create table sys_menu (
  menu_id           bigint(20)      not null auto_increment    comment '菜单ID',
  menu_name         varchar(50)     not null                   comment '菜单名称',
  parent_id         bigint(20)      default 0                  comment '父菜单ID',
  order_num         int(4)          default 0                  comment '显示顺序',
  url               varchar(200)    default '#'                comment '请求地址',
  target            varchar(20)     default ''                 comment '打开方式（menuItem页签 menuBlank新窗口）',
  menu_type         char(1)         default ''                 comment '菜单类型（M目录 C菜单 F按钮）',
  visible           char(1)         default 0                  comment '菜单状态（0显示 1隐藏）',
  perms             varchar(100)    default null               comment '权限标识',
  icon              varchar(100)    default '#'                comment '菜单图标',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default ''                 comment '备注',
  primary key (menu_id)
) engine=innodb auto_increment=2000 comment = '菜单权限表';

-- ----------------------------
-- 初始化-菜单信息表数据
-- ----------------------------
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1, '系统管理', 0, 1, '#', '', 'M', '0', '', 'fa fa-gear', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统管理目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2, '系统监控', 0, 2, '#', '', 'M', '0', '', 'fa fa-video-camera', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统监控目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (3, '系统工具', 0, 3, '#', '', 'M', '0', '', 'fa fa-bars', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统工具目录');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (100, '用户管理', 1, 1, '/system/user', '', 'C', '0', 'system:user:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '用户管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (101, '角色管理', 1, 2, '/system/role', '', 'C', '0', 'system:role:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '角色管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (102, '菜单管理', 1, 3, '/system/menu', '', 'C', '0', 'system:menu:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '菜单管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (103, '部门管理', 1, 4, '/system/dept', '', 'C', '0', 'system:dept:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '部门管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (104, '岗位管理', 1, 5, '/system/post', '', 'C', '0', 'system:post:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '岗位管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (105, '字典管理', 1, 6, '/system/dict', '', 'C', '0', 'system:dict:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '字典管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (106, '参数设置', 1, 7, '/system/config', '', 'C', '0', 'system:config:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '参数设置菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (107, '通知公告', 1, 8, '/system/notice', '', 'C', '0', 'system:notice:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '通知公告菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (108, '日志管理', 1, 9, '#', '', 'M', '0', '', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '日志管理菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (109, '在线用户', 2, 1, '/monitor/online', '', 'C', '0', 'monitor:online:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '在线用户菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (111, '数据监控', 2, 3, '/monitor/data', '', 'C', '0', 'monitor:data:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '数据监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (112, '服务监控', 2, 3, '/monitor/server', '', 'C', '0', 'monitor:server:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '服务监控菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (113, '表单构建', 3, 1, '/tool/build', '', 'C', '0', 'tool:build:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '表单构建菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (115, '系统接口', 3, 3, '/tool/swagger', '', 'C', '0', 'tool:swagger:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '系统接口菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (500, '操作日志', 108, 1, '/monitor/operlog', '', 'C', '0', 'monitor:operlog:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '操作日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (501, '登录日志', 108, 2, '/monitor/logininfor', '', 'C', '0', 'monitor:logininfor:view', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '登录日志菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1000, '用户查询', 100, 1, '#', '', 'F', '0', 'system:user:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1001, '用户新增', 100, 2, '#', '', 'F', '0', 'system:user:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1002, '用户修改', 100, 3, '#', '', 'F', '0', 'system:user:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1003, '用户删除', 100, 4, '#', '', 'F', '0', 'system:user:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1004, '用户导出', 100, 5, '#', '', 'F', '0', 'system:user:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1005, '用户导入', 100, 6, '#', '', 'F', '0', 'system:user:import', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1006, '重置密码', 100, 7, '#', '', 'F', '0', 'system:user:resetPwd', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1007, '角色查询', 101, 1, '#', '', 'F', '0', 'system:role:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1008, '角色新增', 101, 2, '#', '', 'F', '0', 'system:role:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1009, '角色修改', 101, 3, '#', '', 'F', '0', 'system:role:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1010, '角色删除', 101, 4, '#', '', 'F', '0', 'system:role:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1011, '角色导出', 101, 5, '#', '', 'F', '0', 'system:role:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1012, '菜单查询', 102, 1, '#', '', 'F', '0', 'system:menu:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1013, '菜单新增', 102, 2, '#', '', 'F', '0', 'system:menu:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1014, '菜单修改', 102, 3, '#', '', 'F', '0', 'system:menu:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1015, '菜单删除', 102, 4, '#', '', 'F', '0', 'system:menu:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1016, '部门查询', 103, 1, '#', '', 'F', '0', 'system:dept:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1017, '部门新增', 103, 2, '#', '', 'F', '0', 'system:dept:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1018, '部门修改', 103, 3, '#', '', 'F', '0', 'system:dept:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1019, '部门删除', 103, 4, '#', '', 'F', '0', 'system:dept:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1020, '岗位查询', 104, 1, '#', '', 'F', '0', 'system:post:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1021, '岗位新增', 104, 2, '#', '', 'F', '0', 'system:post:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1022, '岗位修改', 104, 3, '#', '', 'F', '0', 'system:post:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1023, '岗位删除', 104, 4, '#', '', 'F', '0', 'system:post:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1024, '岗位导出', 104, 5, '#', '', 'F', '0', 'system:post:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1025, '字典查询', 105, 1, '#', '', 'F', '0', 'system:dict:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1026, '字典新增', 105, 2, '#', '', 'F', '0', 'system:dict:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1027, '字典修改', 105, 3, '#', '', 'F', '0', 'system:dict:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1028, '字典删除', 105, 4, '#', '', 'F', '0', 'system:dict:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1029, '字典导出', 105, 5, '#', '', 'F', '0', 'system:dict:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1030, '参数查询', 106, 1, '#', '', 'F', '0', 'system:config:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1031, '参数新增', 106, 2, '#', '', 'F', '0', 'system:config:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1032, '参数修改', 106, 3, '#', '', 'F', '0', 'system:config:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1033, '参数删除', 106, 4, '#', '', 'F', '0', 'system:config:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1034, '参数导出', 106, 5, '#', '', 'F', '0', 'system:config:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1035, '公告查询', 107, 1, '#', '', 'F', '0', 'system:notice:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1036, '公告新增', 107, 2, '#', '', 'F', '0', 'system:notice:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1037, '公告修改', 107, 3, '#', '', 'F', '0', 'system:notice:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1038, '公告删除', 107, 4, '#', '', 'F', '0', 'system:notice:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1039, '操作查询', 500, 1, '#', '', 'F', '0', 'monitor:operlog:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1040, '操作删除', 500, 2, '#', '', 'F', '0', 'monitor:operlog:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1041, '详细信息', 500, 3, '#', '', 'F', '0', 'monitor:operlog:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1042, '日志导出', 500, 4, '#', '', 'F', '0', 'monitor:operlog:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1043, '登录查询', 501, 1, '#', '', 'F', '0', 'monitor:logininfor:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1044, '登录删除', 501, 2, '#', '', 'F', '0', 'monitor:logininfor:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1045, '日志导出', 501, 3, '#', '', 'F', '0', 'monitor:logininfor:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1046, '账户解锁', 501, 4, '#', '', 'F', '0', 'monitor:logininfor:unlock', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1047, '在线查询', 109, 1, '#', '', 'F', '0', 'monitor:online:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1048, '批量强退', 109, 2, '#', '', 'F', '0', 'monitor:online:batchForceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1049, '单条强退', 109, 3, '#', '', 'F', '0', 'monitor:online:forceLogout', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1050, '任务查询', 110, 1, '#', '', 'F', '0', 'monitor:job:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1051, '任务新增', 110, 2, '#', '', 'F', '0', 'monitor:job:add', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1052, '任务修改', 110, 3, '#', '', 'F', '0', 'monitor:job:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1053, '任务删除', 110, 4, '#', '', 'F', '0', 'monitor:job:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1054, '状态修改', 110, 5, '#', '', 'F', '0', 'monitor:job:changeStatus', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1055, '任务详细', 110, 6, '#', '', 'F', '0', 'monitor:job:detail', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1056, '任务导出', 110, 7, '#', '', 'F', '0', 'monitor:job:export', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1057, '生成查询', 114, 1, '#', '', 'F', '0', 'tool:gen:list', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1058, '生成修改', 114, 2, '#', '', 'F', '0', 'tool:gen:edit', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1059, '生成删除', 114, 3, '#', '', 'F', '0', 'tool:gen:remove', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1060, '预览代码', 114, 4, '#', '', 'F', '0', 'tool:gen:preview', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (1061, '生成代码', 114, 5, '#', '', 'F', '0', 'tool:gen:code', '#', 'admin', '2018-03-16 11:33:00', 'ry', '2018-03-16 11:33:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2000, 'Rainbow', 0, 10, '#', 'menuItem', 'M', '0', null, 'fa fa-heart', 'admin', '2020-06-30 14:46:07', '', null, '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2026, '应用信息', 2000, 1, '/system/application', '', 'C', '0', 'system:application:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '应用信息菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2027, '应用信息查询', 2026, 1, '#', '', 'F', '0', 'system:application:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2028, '应用信息新增', 2026, 2, '#', '', 'F', '0', 'system:application:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2029, '应用信息修改', 2026, 3, '#', '', 'F', '0', 'system:application:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2030, '应用信息删除', 2026, 4, '#', '', 'F', '0', 'system:application:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2031, '应用信息导出', 2026, 5, '#', '', 'F', '0', 'system:application:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2038, '环境信息', 2000, 1, '/system/environment', '', 'C', '0', 'system:environment:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '环境信息菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2039, '环境信息查询', 2038, 1, '#', '', 'F', '0', 'system:environment:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2040, '环境信息新增', 2038, 2, '#', '', 'F', '0', 'system:environment:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2041, '环境信息修改', 2038, 3, '#', '', 'F', '0', 'system:environment:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2042, '环境信息删除', 2038, 4, '#', '', 'F', '0', 'system:environment:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2043, '环境信息导出', 2038, 5, '#', '', 'F', '0', 'system:environment:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2044, 'rainbow配置组', 2000, 1, '/system/groups', '', 'C', '0', 'system:groups:view', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', 'rainbow配置组菜单');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2045, 'rainbow配置组查询', 2044, 1, '#', '', 'F', '0', 'system:groups:list', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2046, 'rainbow配置组新增', 2044, 2, '#', '', 'F', '0', 'system:groups:add', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2047, 'rainbow配置组修改', 2044, 3, '#', '', 'F', '0', 'system:groups:edit', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2048, 'rainbow配置组删除', 2044, 4, '#', '', 'F', '0', 'system:groups:remove', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
INSERT INTO sys_menu (menu_id, menu_name, parent_id, order_num, url, target, menu_type, visible, perms, icon, create_by, create_time, update_by, update_time, remark) VALUES (2049, 'rainbow配置组导出', 2044, 5, '#', '', 'F', '0', 'system:groups:export', '#', 'admin', '2018-03-01 00:00:00', 'ry', '2018-03-01 00:00:00', '');
DELETE FROM sys_menu WHERE menu_id IN (2026,2027,2028,2029,2030,2031);
-- ----------------------------
-- 6、用户和角色关联表  用户N-1角色
-- ----------------------------
drop table if exists sys_user_role;
create table sys_user_role (
  user_id   bigint(20) not null comment '用户ID',
  role_id   bigint(20) not null comment '角色ID',
  primary key(user_id, role_id)
) engine=innodb comment = '用户和角色关联表';

-- ----------------------------
-- 初始化-用户和角色关联表数据
-- ----------------------------
insert into sys_user_role values ('1', '1');
insert into sys_user_role values ('100', '2');


-- ----------------------------
-- 7、角色和菜单关联表  角色1-N菜单
-- ----------------------------
drop table if exists sys_role_menu;
create table sys_role_menu (
  role_id   bigint(20) not null comment '角色ID',
  menu_id   bigint(20) not null comment '菜单ID',
  primary key(role_id, menu_id)
) engine=innodb comment = '角色和菜单关联表';

-- ----------------------------
-- 初始化-角色和菜单关联表数据
-- ----------------------------
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 3);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 100);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 101);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 102);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 103);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 104);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 105);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 106);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 107);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 108);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 109);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 110);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 111);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 112);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 113);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 114);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 115);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 500);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 501);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1000);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1001);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1002);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1003);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1004);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1005);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1006);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1007);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1008);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1009);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1010);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1011);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1012);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1013);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1014);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1015);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1016);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1017);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1018);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1019);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1020);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1021);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1022);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1023);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1024);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1025);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1026);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1027);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1028);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1029);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1030);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1031);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1032);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1033);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1034);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1035);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1036);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1037);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1038);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1039);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1040);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1041);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1042);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1043);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1044);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1045);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1046);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1047);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1048);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1049);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1050);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1051);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1052);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1053);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1054);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1055);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1056);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1057);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1058);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1059);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1060);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 1061);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2000);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2001);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2002);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2003);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2004);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2005);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2006);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2007);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2008);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2009);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2010);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2011);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2012);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2013);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2014);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2015);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2016);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2017);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2018);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2019);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2020);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2021);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2022);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2023);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2024);
INSERT INTO  sys_role_menu (role_id, menu_id) VALUES (2, 2025);
-- ----------------------------
-- 8、角色和部门关联表  角色1-N部门
-- ----------------------------
drop table if exists sys_role_dept;
create table sys_role_dept (
  role_id   bigint(20) not null comment '角色ID',
  dept_id   bigint(20) not null comment '部门ID',
  primary key(role_id, dept_id)
) engine=innodb comment = '角色和部门关联表';

-- ----------------------------
-- 初始化-角色和部门关联表数据
-- ----------------------------
INSERT INTO  sys_role_dept (role_id, dept_id) VALUES (2, 100);
INSERT INTO  sys_role_dept (role_id, dept_id) VALUES (2, 101);
INSERT INTO  sys_role_dept (role_id, dept_id) VALUES (2, 105);

-- ----------------------------
-- 9、用户与岗位关联表  用户1-N岗位
-- ----------------------------
drop table if exists sys_user_post;
create table sys_user_post
(
  user_id   bigint(20) not null comment '用户ID',
  post_id   bigint(20) not null comment '岗位ID',
  primary key (user_id, post_id)
) engine=innodb comment = '用户与岗位关联表';

-- ----------------------------
-- 初始化-用户与岗位关联表数据
-- ----------------------------
INSERT INTO sys_user_post (user_id, post_id) VALUES (1, 1);
INSERT INTO sys_user_post (user_id, post_id) VALUES (2, 2);


-- ----------------------------
-- 10、操作日志记录
-- ----------------------------
drop table if exists sys_oper_log;
create table sys_oper_log (
  oper_id           bigint(20)      not null auto_increment    comment '日志主键',
  title             varchar(50)     default ''                 comment '模块标题',
  business_type     int(2)          default 0                  comment '业务类型（0其它 1新增 2修改 3删除）',
  method            varchar(100)    default ''                 comment '方法名称',
  request_method    varchar(10)     default ''                 comment '请求方式',
  operator_type     int(1)          default 0                  comment '操作类别（0其它 1后台用户 2手机端用户）',
  oper_name         varchar(50)     default ''                 comment '操作人员',
  dept_name         varchar(50)     default ''                 comment '部门名称',
  oper_url          varchar(255)    default ''                 comment '请求URL',
  oper_ip           varchar(50)     default ''                 comment '主机地址',
  oper_location     varchar(255)    default ''                 comment '操作地点',
  oper_param        varchar(2000)   default ''                 comment '请求参数',
  json_result       varchar(2000)   default ''                 comment '返回参数',
  status            int(1)          default 0                  comment '操作状态（0正常 1异常）',
  error_msg         varchar(2000)   default ''                 comment '错误消息',
  oper_time         datetime                                   comment '操作时间',
  primary key (oper_id)
) engine=innodb auto_increment=100 comment = '操作日志记录';


-- ----------------------------
-- 11、字典类型表
-- ----------------------------
drop table if exists sys_dict_type;
create table sys_dict_type
(
  dict_id          bigint(20)      not null auto_increment    comment '字典主键',
  dict_name        varchar(100)    default ''                 comment '字典名称',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_id),
  unique (dict_type)
) engine=innodb auto_increment=100 comment = '字典类型表';

insert into sys_dict_type values(1,  '用户性别', 'sys_user_sex',        '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '用户性别列表');
insert into sys_dict_type values(2,  '菜单状态', 'sys_show_hide',       '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '菜单状态列表');
insert into sys_dict_type values(3,  '系统开关', 'sys_normal_disable',  '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统开关列表');
insert into sys_dict_type values(4,  '任务状态', 'sys_job_status',      '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '任务状态列表');
insert into sys_dict_type values(5,  '任务分组', 'sys_job_group',       '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '任务分组列表');
insert into sys_dict_type values(6,  '系统是否', 'sys_yes_no',          '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统是否列表');
insert into sys_dict_type values(7,  '通知类型', 'sys_notice_type',     '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '通知类型列表');
insert into sys_dict_type values(8,  '通知状态', 'sys_notice_status',   '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '通知状态列表');
insert into sys_dict_type values(9,  '操作类型', 'sys_oper_type',       '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '操作类型列表');
insert into sys_dict_type values(10, '系统状态', 'sys_common_status',   '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '登录状态列表');


-- ----------------------------
-- 12、字典数据表
-- ----------------------------
drop table if exists sys_dict_data;
create table sys_dict_data
(
  dict_code        bigint(20)      not null auto_increment    comment '字典编码',
  dict_sort        int(4)          default 0                  comment '字典排序',
  dict_label       varchar(100)    default ''                 comment '字典标签',
  dict_value       varchar(100)    default ''                 comment '字典键值',
  dict_type        varchar(100)    default ''                 comment '字典类型',
  css_class        varchar(100)    default null               comment '样式属性（其他样式扩展）',
  list_class       varchar(100)    default null               comment '表格回显样式',
  is_default       char(1)         default 'N'                comment '是否默认（Y是 N否）',
  status           char(1)         default '0'                comment '状态（0正常 1停用）',
  create_by        varchar(64)     default ''                 comment '创建者',
  create_time      datetime                                   comment '创建时间',
  update_by        varchar(64)     default ''                 comment '更新者',
  update_time      datetime                                   comment '更新时间',
  remark           varchar(500)    default null               comment '备注',
  primary key (dict_code)
) engine=innodb auto_increment=100 comment = '字典数据表';

insert into sys_dict_data values(1,  1,  '男',       '0',       'sys_user_sex',        '',   '',        'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '性别男');
insert into sys_dict_data values(2,  2,  '女',       '1',       'sys_user_sex',        '',   '',        'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '性别女');
insert into sys_dict_data values(3,  3,  '未知',     '2',       'sys_user_sex',        '',   '',        'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '性别未知');
insert into sys_dict_data values(4,  1,  '显示',     '0',       'sys_show_hide',       '',   'primary', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '显示菜单');
insert into sys_dict_data values(5,  2,  '隐藏',     '1',       'sys_show_hide',       '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '隐藏菜单');
insert into sys_dict_data values(6,  1,  '正常',     '0',       'sys_normal_disable',  '',   'primary', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(7,  2,  '停用',     '1',       'sys_normal_disable',  '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '停用状态');
insert into sys_dict_data values(8,  1,  '正常',     '0',       'sys_job_status',      '',   'primary', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(9,  2,  '暂停',     '1',       'sys_job_status',      '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '停用状态');
insert into sys_dict_data values(10, 1,  '默认',     'DEFAULT', 'sys_job_group',       '',   '',        'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '默认分组');
insert into sys_dict_data values(11, 2,  '系统',     'SYSTEM',  'sys_job_group',       '',   '',        'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统分组');
insert into sys_dict_data values(12, 1,  '是',       'Y',       'sys_yes_no',          '',   'primary', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统默认是');
insert into sys_dict_data values(13, 2,  '否',       'N',       'sys_yes_no',          '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '系统默认否');
insert into sys_dict_data values(14, 1,  '通知',     '1',       'sys_notice_type',     '',   'warning', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '通知');
insert into sys_dict_data values(15, 2,  '公告',     '2',       'sys_notice_type',     '',   'success', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '公告');
insert into sys_dict_data values(16, 1,  '正常',     '0',       'sys_notice_status',   '',   'primary', 'Y', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(17, 2,  '关闭',     '1',       'sys_notice_status',   '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '关闭状态');
insert into sys_dict_data values(18, 99, '其他',     '0',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '其他操作');
insert into sys_dict_data values(19, 1,  '新增',     '1',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '新增操作');
insert into sys_dict_data values(20, 2,  '修改',     '2',       'sys_oper_type',       '',   'info',    'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '修改操作');
insert into sys_dict_data values(21, 3,  '删除',     '3',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '删除操作');
insert into sys_dict_data values(22, 4,  '授权',     '4',       'sys_oper_type',       '',   'primary', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '授权操作');
insert into sys_dict_data values(23, 5,  '导出',     '5',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '导出操作');
insert into sys_dict_data values(24, 6,  '导入',     '6',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '导入操作');
insert into sys_dict_data values(25, 7,  '强退',     '7',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '强退操作');
insert into sys_dict_data values(26, 8,  '生成代码', '8',       'sys_oper_type',       '',   'warning', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '生成操作');
insert into sys_dict_data values(27, 9,  '清空数据', '9',       'sys_oper_type',       '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '清空操作');
insert into sys_dict_data values(28, 1,  '成功',     '0',       'sys_common_status',   '',   'primary', 'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '正常状态');
insert into sys_dict_data values(29, 2,  '失败',     '1',       'sys_common_status',   '',   'danger',  'N', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '停用状态');


-- ----------------------------
-- 13、参数配置表
-- ----------------------------
drop table if exists sys_config;
create table sys_config (
  config_id         int(5)          not null auto_increment    comment '参数主键',
  config_name       varchar(100)    default ''                 comment '参数名称',
  config_key        varchar(100)    default ''                 comment '参数键名',
  config_value      varchar(500)    default ''                 comment '参数键值',
  config_type       char(1)         default 'N'                comment '系统内置（Y是 N否）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (config_id)
) engine=innodb auto_increment=100 comment = '参数配置表';

insert into sys_config values(1, '主框架页-默认皮肤样式名称',     'sys.index.skinName',       'skin-blue',     'Y', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '蓝色 skin-blue、绿色 skin-green、紫色 skin-purple、红色 skin-red、黄色 skin-yellow');
insert into sys_config values(2, '用户管理-账号初始密码',         'sys.user.initPassword',    '123456',        'Y', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '初始化密码 123456');
insert into sys_config values(3, '主框架页-侧边栏主题',           'sys.index.sideTheme',      'theme-dark',    'Y', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '深黑主题theme-dark，浅色主题theme-light，深蓝主题theme-blue');
insert into sys_config values(4, '账号自助-是否开启用户注册功能', 'sys.account.registerUser', 'false',         'Y', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '是否开启注册用户功能');


-- ----------------------------
-- 14、系统访问记录
-- ----------------------------
drop table if exists sys_logininfor;
create table sys_logininfor (
  info_id        bigint(20)     not null auto_increment   comment '访问ID',
  login_name     varchar(50)    default ''                comment '登录账号',
  ipaddr         varchar(50)    default ''                comment '登录IP地址',
  login_location varchar(255)   default ''                comment '登录地点',
  browser        varchar(50)    default ''                comment '浏览器类型',
  os             varchar(50)    default ''                comment '操作系统',
  status         char(1)        default '0'               comment '登录状态（0成功 1失败）',
  msg            varchar(255)   default ''                comment '提示消息',
  login_time     datetime                                 comment '访问时间',
  primary key (info_id)
) engine=innodb auto_increment=100 comment = '系统访问记录';


-- ----------------------------
-- 15、在线用户记录
-- ----------------------------
drop table if exists sys_user_online;
create table sys_user_online (
  sessionId         varchar(50)   default ''                comment '用户会话id',
  login_name        varchar(50)   default ''                comment '登录账号',
  dept_name         varchar(50)   default ''                comment '部门名称',
  ipaddr            varchar(50)   default ''                comment '登录IP地址',
  login_location    varchar(255)  default ''                comment '登录地点',
  browser           varchar(50)   default ''                comment '浏览器类型',
  os                varchar(50)   default ''                comment '操作系统',
  status            varchar(10)   default ''                comment '在线状态on_line在线off_line离线',
  start_timestamp   datetime                                comment 'session创建时间',
  last_access_time  datetime                                comment 'session最后访问时间',
  expire_time       int(5)        default 0                 comment '超时时间，单位为分钟',
  primary key (sessionId)
) engine=innodb comment = '在线用户记录';


-- ----------------------------
-- 16、定时任务调度表
-- ----------------------------
drop table if exists sys_job;
create table sys_job (
  job_id              bigint(20)    not null auto_increment    comment '任务ID',
  job_name            varchar(64)   default ''                 comment '任务名称',
  job_group           varchar(64)   default 'DEFAULT'          comment '任务组名',
  invoke_target       varchar(500)  not null                   comment '调用目标字符串',
  cron_expression     varchar(255)  default ''                 comment 'cron执行表达式',
  misfire_policy      varchar(20)   default '3'                comment '计划执行错误策略（1立即执行 2执行一次 3放弃执行）',
  concurrent          char(1)       default '1'                comment '是否并发执行（0允许 1禁止）',
  status              char(1)       default '0'                comment '状态（0正常 1暂停）',
  create_by           varchar(64)   default ''                 comment '创建者',
  create_time         datetime                                 comment '创建时间',
  update_by           varchar(64)   default ''                 comment '更新者',
  update_time         datetime                                 comment '更新时间',
  remark              varchar(500)  default ''                 comment '备注信息',
  primary key (job_id, job_name, job_group)
) engine=innodb auto_increment=100 comment = '定时任务调度表';

insert into sys_job values(1, '系统默认（无参）', 'DEFAULT', 'ryTask.ryNoParams',        '0/10 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');
insert into sys_job values(2, '系统默认（有参）', 'DEFAULT', 'ryTask.ryParams(\'ry\')',  '0/15 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');
insert into sys_job values(3, '系统默认（多参）', 'DEFAULT', 'ryTask.ryMultipleParams(\'ry\', true, 2000L, 316.50D, 100)',  '0/20 * * * * ?', '3', '1', '1', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '');


-- ----------------------------
-- 17、定时任务调度日志表
-- ----------------------------
drop table if exists sys_job_log;
create table sys_job_log (
  job_log_id          bigint(20)     not null auto_increment    comment '任务日志ID',
  job_name            varchar(64)    not null                   comment '任务名称',
  job_group           varchar(64)    not null                   comment '任务组名',
  invoke_target       varchar(500)   not null                   comment '调用目标字符串',
  job_message         varchar(500)                              comment '日志信息',
  status              char(1)        default '0'                comment '执行状态（0正常 1失败）',
  exception_info      varchar(2000)  default ''                 comment '异常信息',
  create_time         datetime                                  comment '创建时间',
  primary key (job_log_id)
) engine=innodb comment = '定时任务调度日志表';


-- ----------------------------
-- 18、通知公告表
-- ----------------------------
drop table if exists sys_notice;
create table sys_notice (
  notice_id         int(4)          not null auto_increment    comment '公告ID',
  notice_title      varchar(50)     not null                   comment '公告标题',
  notice_type       char(1)         not null                   comment '公告类型（1通知 2公告）',
  notice_content    varchar(2000)   default null               comment '公告内容',
  status            char(1)         default '0'                comment '公告状态（0正常 1关闭）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(255)    default null               comment '备注',
  primary key (notice_id)
) engine=innodb auto_increment=10 comment = '通知公告表';

-- ----------------------------
-- 初始化-公告信息表数据
-- ----------------------------
insert into sys_notice values('1', '温馨提醒：2018-07-01 若依新版本发布啦', '2', '新版本内容', '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '管理员');
insert into sys_notice values('2', '维护通知：2018-07-01 若依系统凌晨维护', '1', '维护内容',   '0', 'admin', '2018-03-16 11-33-00', 'ry', '2018-03-16 11-33-00', '管理员');


-- ----------------------------
-- 19、代码生成业务表
-- ----------------------------
drop table if exists gen_table;
create table gen_table (
  table_id             bigint(20)      not null auto_increment    comment '编号',
  table_name           varchar(200)    default ''                 comment '表名称',
  table_comment        varchar(500)    default ''                 comment '表描述',
  sub_table_name       varchar(64)     default null               comment '关联子表的表名',
  sub_table_fk_name    varchar(64)     default null               comment '子表关联的外键名',
  class_name           varchar(100)    default ''                 comment '实体类名称',
  tpl_category         varchar(200)    default 'crud'             comment '使用的模板（crud单表操作 tree树表操作 sub主子表操作）',
  package_name         varchar(100)                               comment '生成包路径',
  module_name          varchar(30)                                comment '生成模块名',
  business_name        varchar(30)                                comment '生成业务名',
  function_name        varchar(50)                                comment '生成功能名',
  function_author      varchar(50)                                comment '生成功能作者',
  options              varchar(1000)                              comment '其它生成选项',
  create_by            varchar(64)     default ''                 comment '创建者',
  create_time 	       datetime                                   comment '创建时间',
  update_by            varchar(64)     default ''                 comment '更新者',
  update_time          datetime                                   comment '更新时间',
  remark               varchar(500)    default null               comment '备注',
  primary key (table_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表';


-- ----------------------------
-- 20、代码生成业务表字段
-- ----------------------------
drop table if exists gen_table_column;
create table gen_table_column (
  column_id         bigint(20)      not null auto_increment    comment '编号',
  table_id          varchar(64)                                comment '归属表编号',
  column_name       varchar(200)                               comment '列名称',
  column_comment    varchar(500)                               comment '列描述',
  column_type       varchar(100)                               comment '列类型',
  java_type         varchar(500)                               comment 'JAVA类型',
  java_field        varchar(200)                               comment 'JAVA字段名',
  is_pk             char(1)                                    comment '是否主键（1是）',
  is_increment      char(1)                                    comment '是否自增（1是）',
  is_required       char(1)                                    comment '是否必填（1是）',
  is_insert         char(1)                                    comment '是否为插入字段（1是）',
  is_edit           char(1)                                    comment '是否编辑字段（1是）',
  is_list           char(1)                                    comment '是否列表字段（1是）',
  is_query          char(1)                                    comment '是否查询字段（1是）',
  query_type        varchar(200)    default 'EQ'               comment '查询方式（等于、不等于、大于、小于、范围）',
  html_type         varchar(200)                               comment '显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）',
  dict_type         varchar(200)    default ''                 comment '字典类型',
  sort              int                                        comment '排序',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time 	    datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  primary key (column_id)
) engine=innodb auto_increment=1 comment = '代码生成业务表字段';



-- 应用信息表
drop table if exists rainbow_application;
create table rainbow_application
(
    app_name    varchar(64)  not null comment '应用名称',
    description varchar(256) not null comment '应用说明',
    constraint application_name_UNIQUE
        unique (app_name)
)
    comment '应用信息表';
alter table rainbow_application
    add primary key (app_name);

-- 环境信息表
drop table if exists rainbow_environment;
create table rainbow_environment
(
    env         varchar(10)  not null comment '环境名称'
        primary key,
    description varchar(256) not null comment '环境显示说明'
)
    comment '环境信息表';


-- 配置组信息表
drop table if exists rainbow_groups;
create table rainbow_groups
(
    id          bigint auto_increment comment '主键id'
        primary key,
    env         varchar(10)  not null comment '环境名称',
    app_name    varchar(64)  not null comment '应用名称',
    group_name  varchar(64)  not null comment '组名称',
    group_desc  varchar(256) not null comment '组描述',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '修改时间',
    create_user varchar(64)  null comment '创建人',
    update_user varchar(64)  null comment '修改人'
);

-- 配置项表
drop table if exists rainbow_cells;
create table rainbow_cells
(
    id            bigint auto_increment comment '自增Id'
        primary key,
    group_id      bigint       default 0         not null comment '组ID',
    rainbow_key   varchar(128) default 'default' not null comment '配置项Key',
    rainbow_value longtext                       not null comment '配置项值',
    cell_desc     varchar(1024)                  null comment '描述',
    line_num      int(8)       default 0         null comment '行号',
    deleted       tinyint(1)   default 0         null comment '是否删除',
    create_time   datetime                       null comment '创建时间',
    update_time   datetime                       null comment '修改时间',
    create_user   varchar(64)                    null comment '创建人',
    update_user   varchar(64)                    null comment '修改人',
    group_name    varchar(64)                    null comment '组名称'
)
    comment '配置项目' charset = utf8mb4;



