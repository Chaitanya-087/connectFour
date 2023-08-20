use connect4;

create table moves(
	id int primary key auto_increment,
	player varchar(1) not null,
	`row` int not null,
	col intnot null
);