create table ComicCartoon(
id int primary key auto_increment,
sno int not null unique key comment '当前集号',
count int not null comment '本集漫画数',
url varchar(255) comment '漫画zip包地址',
icon varchar(255) comment '漫画封皮',
size int comment '文件大小',
date date
)