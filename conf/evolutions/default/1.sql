# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table category (
  id                        bigint auto_increment not null,
  name                      varchar(255),
  description               varchar(255),
  constraint pk_category primary key (id))
;

create table item (
  id                        bigint auto_increment not null,
  catg_id                   varchar(255),
  item_name                 varchar(255),
  item_desc                 varchar(255),
  constraint pk_item primary key (id))
;

create table tbl_users (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  email                     varchar(255) not null,
  password                  varchar(255),
  mobile                    varchar(255),
  pin                       integer,
  constraint uq_tbl_users_email unique (email),
  constraint pk_tbl_users primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table category;

drop table item;

drop table tbl_users;

SET FOREIGN_KEY_CHECKS=1;

