create table category (
	id serial primary key,
	description varchar(50)
);

create table state (
	id serial primary key,
	description varchar(50)
);

create table role (
	id serial primary key,
	description varchar(50)
);

create table rules (
	id serial primary key,
	description varchar(50)
);

create table role_rules (
	id serial primary key,
	role_id int references role(id),
	rules_id int references rules(id)
);

create table users (
	id serial primary key,
	name varchar(50),
	role_id int references role(id)
);

create table item (
	id serial primary key,
	description varchar(50),
	user_id int references users(id),
	category_id int references category(id),
	state_id int references state(id)
);

create table comments (
	id serial primary key,
	description varchar(50),
	item_id int references item(id)
);

create table attach (
	id serial primary key,
	typeOfFile varchar (10),
	item_id int references item(id)
);