create table departments (
	id serial primary key,
	name varchar(50)
);

create table emploees (
	id serial primary key,
	name varchar(50),
	dep_id int references departments(id)
);

insert into departments (name) values ('Отдел продаж');
insert into departments (name) values ('Отдел закупок');
insert into departments (name) values ('Бухгалтерия');
insert into departments (name) values ('Производство');
insert into departments (name) values ('Дизайнеры');

insert into emploees (name, dep_id) values ('Вася', 1);
insert into emploees (name, dep_id) values ('Петя', 1);
insert into emploees (name, dep_id) values ('Вика', 2);
insert into emploees (name, dep_id) values ('Маша', 3);
insert into emploees (name, dep_id) values ('Игорь', 4);
insert into emploees (name, dep_id) values ('Дима', 4);

select * from emploees e left join departments d on e.dep_id = d.id;
select * from emploees e right join departments d on e.dep_id = d.id;
select * from emploees e full join departments d on e.dep_id = d.id;
select * from emploees e cross join departments d;
select d.name from departments d left join emploees e on d.id = e.dep_id where e.name is null;
select e.name from emploees e left join departments d on e.dep_id = d.id;
select e.name from departments d right join emploees e on e.dep_id = d.id;

create table teens (
	id serial primary key,
	name varchar(50),
	gender varchar(10)
);

insert into teens (name, gender) values ('Вася', 'М');
insert into teens (name, gender) values ('Петя', 'М');
insert into teens (name, gender) values ('Дима', 'М');
insert into teens (name, gender) values ('Вова', 'М');
insert into teens (name, gender) values ('Маша', 'Ж');
insert into teens (name, gender) values ('Оля', 'Ж');
insert into teens (name, gender) values ('Даша', 'Ж');
insert into teens (name, gender) values ('Вика', 'Ж');
insert into teens (name, gender) values ('Наташа', 'Ж');

select m.name, w.name from teens as m cross join (select name from teens where gender = 'Ж') as w where m.gender = 'М';
