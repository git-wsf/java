alter table tb_shop add column province VARCHAR(20) default null after address;
alter table tb_shop drop column weekLevelCount;
alter table tb_shop drop column springLevelCount;