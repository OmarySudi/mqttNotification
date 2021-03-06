create table if not exists  notifications (
  id int(11) not null auto_increment,
  notification varchar(1024) not null,
  userID varchar(100) null,
  user_id int(255) null,
  is_read tinyint(4) default 0,
  topic varchar(1024) not null,
  role varchar(100) not null,
  icons varchar(100) null,
  entityID varchar(100) null,
  entity_id int(255) null,
  category varchar(100) null,
  primary key (id)
) engine=innodb ;