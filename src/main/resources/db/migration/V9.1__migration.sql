alter table tb_lottery modify column createTime datetime default null after mobile;
alter table tb_lottery modify column updateTime datetime default null after createTime;