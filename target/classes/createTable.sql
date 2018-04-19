  #分组表
  create table demo_group(
     id int primary key auto_increment,#编号 整形 主键 自增长
     demo_group_name varchar(18),#名称
     demo_group_description varchar(100),#描述
     demo_group_parentId int, #父节点
     demo_group_adminId  int
  );

  #用户分组关联表
  create table demo_user_group(
     id int primary key auto_increment,#编号 整形 主键 自增长
     demo_user_id int,#用户id
     demo_group_id int #分组id
  );