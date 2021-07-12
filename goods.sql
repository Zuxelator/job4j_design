create table category (
	id serial primary key,
	name varchar(100)
);

create table goods (
	id serial primary key,
	name varchar(250),
	category_id int references category(id)
);

insert into category (name) values ('CPU');
insert into category (name) values ('RAM');
insert into category (name) values ('HDD');
insert into category (name) values ('MB');

insert into goods (name, category_id) values ('Intel i5', 1);
insert into goods (name, category_id) values ('AMD Ryzen 5', 1);
insert into goods (name, category_id) values ('HyperX 16 Gb', 2);
insert into goods (name, category_id) values ('HyperX 8 Gb', 2);
insert into goods (name, category_id) values ('WD 1Tb', 3);
insert into goods (name, category_id) values ('Gigabyte B450M', 4);

Select * from goods as g inner join category as c ON g.category_id = c.id;
Select g.name, category_id, c.name from goods as g inner join category as c ON g.category_id = c.id;
Select g.name, category_id, c.name from goods as g inner join category as c ON g.category_id = c.id order by c.name desc limit 4;


