create table carbody (
	id serial primary key,
	type varchar(30),
	color varchar(50)
);

create table engine (
	id serial primary key,
	type varchar(50),
	volume int
);

create table transmission (
	id serial primary key,
	type varchar(50),
	gears int
);

create table car (
	id serial primary key,
	model varchar(50),
	carbody_id int references carbody(id),
	engine_id int references engine(id),
	transmission_id int references transmission(id)
);

insert into carbody (type, color) values ('седан', 'синий');
insert into carbody (type, color) values ('седан', 'желтый');
insert into carbody (type, color) values ('седан', 'красный');
insert into carbody (type, color) values ('хетчбек', 'черный');
insert into carbody (type, color) values ('хетчбек', 'серый');
insert into carbody (type, color) values ('хетчбек', 'красный');
insert into carbody (type, color) values ('универсал', 'черный');
insert into carbody (type, color) values ('универсал', 'синий');

insert into engine (type, volume) values ('disel', 1600);
insert into engine (type, volume) values ('disel', 2000);
insert into engine (type, volume) values ('petrol', 2600);
insert into engine (type, volume) values ('petrol', 1400);
insert into engine (type, volume) values ('electric', 0);

insert into transmission (type, gears) values ('manual', 5);
insert into transmission (type, gears) values ('manual', 6);
insert into transmission (type, gears) values ('manual', 4);
insert into transmission (type, gears) values ('auto', 5);
insert into transmission (type, gears) values ('auto', 6);
insert into transmission (type, gears) values ('auto', 7);
insert into transmission (type, gears) values ('robot', 7);

insert into car (model, carbody_id, engine_id, transmission_id) values ('приора', 4, 4, 1);
insert into car (model, carbody_id, engine_id, transmission_id) values ('мазда 3', 5, 2, 2);
insert into car (model, carbody_id, engine_id, transmission_id) values ('приус', 6, 5, 7);
insert into car (model, carbody_id, engine_id, transmission_id) values ('модель1', 4, 4, 1);
insert into car (model, carbody_id, engine_id, transmission_id) values ('модель2', 8, 3, 4);

select * from car c join carbody cb on c.carbody_id = cb.id join transmission t on c.transmission_id = t.id join engine e on c.engine_id = e.id;
select cb.id, cb.type, cb.color from carbody cb left join car c on cb.id = c.carbody_id where model is null; 
select e.id, e.type, e.volume from engine e left join car c on e.id = c.engine_id where model is null; 
select t.id, t.type, t.gears from transmission t left join car c on t.id = c.transmission_id where model is null; 
