create table devices(
    id serial primary key,
    name varchar(255),
    price float
);

create table people(
    id serial primary key,
    name varchar(255)
);

create table devices_people(
    id serial primary key,
    device_id int references devices(id),
    people_id int references people(id)
);

insert into devices (name, price) values ('phone', 30000.55);
insert into devices (name, price) values ('Tab-pc', 25000.55);
insert into devices (name, price) values ('pc', 70000.11);
insert into devices (name, price) values ('headphones',3000.60);
insert into devices (name, price) values ('mouse',1000.25);

insert into people (name) values ('Саша');
insert into people (name) values ('Петя');
insert into people (name) values ('Вася');
insert into people (name) values ('Даша');

insert into devices_people (device_id, people_id) values (1, 1);
insert into devices_people (device_id, people_id) values (1, 2);
insert into devices_people (device_id, people_id) values (1, 3);
insert into devices_people (device_id, people_id) values (1, 4);
insert into devices_people (device_id, people_id) values (2, 2);
insert into devices_people (device_id, people_id) values (3, 1);
insert into devices_people (device_id, people_id) values (3, 3);
insert into devices_people (device_id, people_id) values (4, 3);
insert into devices_people (device_id, people_id) values (4, 4);
insert into devices_people (device_id, people_id) values (5, 1);
insert into devices_people (device_id, people_id) values (5, 3);

select avg(price) from devices_people dp join people p on dp.people_id = p.id join devices d on dp.device_id = d.id;
select p.name, avg(price) from devices_people dp join people p on dp.people_id = p.id join devices d on dp.device_id = d.id
group by p.name;
select p.name, avg(price) from devices_people dp join people p on dp.people_id = p.id join devices d on dp.device_id = d.id
group by p.name having avg(price) > 5000;