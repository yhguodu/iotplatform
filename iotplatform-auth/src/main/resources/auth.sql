create database iot_users;

DROP TABLE if EXISTS 'user_permission'

CREATE TABLE 'user_permission'(
  id int(11),
  permission_name varchar(32) not null,
  role_id int(11) not null
);

DROP TABLE  if EXISTS 'user'
CREATE TABLE 'user'(
  id int(11) not null auto_increment,
  name varchar(32) not null,
  password varchar(32) not null,
  mail varchar(255) not null,
  PRIMARY key(id)
)ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP  table if EXISTS 'role'
CREATE TABLE  'role'(
  id int(11) not null,
  role_name varchar(32) not null
)ENGINE = InnoDB
  DEFAULT CHARSET = utf8;

DROP TABLE  if EXISTS 'user_role'
CREATE TABLE 'user_role'(
  uid int(11) not null,
  rid int(11) not null
)ENGINE = InnoDB
  DEFAULT CHARSET = utf8;




