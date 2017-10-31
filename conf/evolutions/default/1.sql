# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table tbl_users (
  id                        bigint auto_increment not null,
  username                  varchar(255),
  email                     varchar(255),
  password                  varchar(255),
  mobile                    varchar(255),
  constraint pk_tbl_users primary key (id))
;




# --- !Downs

SET FOREIGN_KEY_CHECKS=0;

drop table tbl_users;

SET FOREIGN_KEY_CHECKS=1;

