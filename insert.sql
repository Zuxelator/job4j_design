insert into state (description) values ('Открыта');
insert into state (description) values ('В работе');
insert into state (description) values ('Закрыта');

insert into category (description) values ('Очень срочно');
insert into category (description) values ('Cрочно');
insert into category (description) values ('Не срочно');

insert into role (description) values ('Администратор');
insert into role (description) values ('Пользователь');

insert into rules (description) values ('Создать');
insert into rules (description) values ('Редактировать');
insert into rules (description) values ('Удалить');

insert into role_rules (role_id, rules_id) values (1, 1);
insert into role_rules (role_id, rules_id) values (1, 2);
insert into role_rules (role_id, rules_id) values (1, 3);
insert into role_rules (role_id, rules_id) values (2, 1);

insert into users (name, role_id) values ('Вася', 1);
insert into users (name, role_id) values ('Петя', 2);

insert into item (description, user_id, category_id, state_id) values ('Проблема 1', 1, 1, 1);
insert into item (description, user_id, category_id, state_id) values ('Проблема 2', 1, 2, 3);

insert into comments (description, item_id) values ('Text1', 1);
insert into comments (description, item_id) values ('Text2', 2);

insert into attach (typeOfFile, item_id) values ('jpg', 1);
insert into attach (typeOfFile, item_id) values ('zip', 1);
insert into attach (typeOfFile, item_id) values ('doc', 2);