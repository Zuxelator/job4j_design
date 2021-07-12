create table type (
	id serial primary key,
	name varchar(50)
);

create table product (
	id serial primary key,
	name varchar(50),
	type_id int references type(id),
	expired_date timestamp,
	price float
);

insert into type (name) values ('Сыр');
insert into type (name) values ('Молоко');
insert into type (name) values ('Овощи');
insert into type (name) values ('Пиво');


insert into product (name, type_id, expired_date, price) values ('жигули', 4, '11.07.2021', 67.50);
insert into product (name, type_id, expired_date, price) values ('туборг', 4, '13.07.2021', 80.50);
insert into product (name, type_id, expired_date, price) values ('балтика', 4, '14.07.2021', 40.50);
insert into product (name, type_id, expired_date, price) values ('масдам', 1, '13.07.2021', 500.50);
insert into product (name, type_id, expired_date, price) values ('дружба', 1, '13.07.2021', 160.50);
insert into product (name, type_id, expired_date, price) values ('домик в деревне', 2, '11.07.2021', 110.20);
insert into product (name, type_id, expired_date, price) values ('зеленое село', 2, '15.07.2021', 120.20);
insert into product (name, type_id, expired_date, price) values ('помидоры', 3, '15.07.2021', 180.30);
insert into product (name, type_id, expired_date, price) values ('огурцы', 3, '11.07.2021', 90.30);
insert into product (name, type_id, expired_date, price) values ('мороженое пиво', 4, '13.07.2021', 190.30);

-- 1. Написать запрос получение всех продуктов с типом "СЫР"
select p.name from product p join type t on p.type_id = t.id where t.name ='Сыр';
-- 2. Написать запрос получения всех продуктов, у кого в имени есть слово "мороженое"
select p.name from product p join type t on p.type_id = t.id where p.name LIKE '%мороженое%';
-- 3. Написать запрос, который выводит все продукты, срок годности которых уже истек
select * from product p join type t on p.type_id = t.id where expired_date < current_date;
-- 4. Написать запрос, который выводит самый дорогой продукт.
Select name from product order by price desc limit 1;
-- 5. Написать запрос, который выводит для каждого типа количество продуктов к нему принадлежащих. В виде имя_типа, количество
select t.name, count(t.name) from product p join type t on p.type_id = t.id group by t.name;
-- 6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО"
select p.name from product p join type t on p.type_id = t.id where t.name = 'Молоко' OR t.name = 'Сыр';
-- 7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук. Под количеством подразумевается количество продуктов определенного типа. Например, если есть тип "СЫР" и продукты "Сыр плавленный" и "Сыр моцарелла", которые ему принадлежат, то количество продуктов типа "СЫР" будет 2.
select t.name, count(p.name) from product p join type t on p.type_id = t.id group by t.name having count(p.name) < 10;
-- 8. Вывести все продукты и их тип.
select p.name, t.name from product p join type t on p.type_id = t.id;