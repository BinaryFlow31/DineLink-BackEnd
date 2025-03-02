drop database dinelinkdb;

create database dinelinkdb;

use dinelinkdb;

show tables;

desc categories;
desc customers;
desc feedbacks;
desc food_items;
desc moderators;
desc order_items;
desc orders;
desc payments;
desc sub_categories;

select * from moderators;
select * from customers;
select * from categories;
select * from sub_categories;

delete from sub_categories;

insert into moderators (email, name, password, role, created_at) values ('admin1234@dinelink.com', 'Admin', '$2a$10$X8Vt7HuReHtOEo82lmOh4uy7913bApfeazyUhI5baabLv2hPM9Rde', 'ADMIN', '2024-02-21 12:34:56.123456');
-- password is : admin1234
-- for swagger ui :

INSERT INTO categories (name) VALUES ('BreakFast'), ('Lunch'), ('Dinner'), ('Starters'), ('Desserts');

-- Dessert Sub-Categories
INSERT INTO sub_categories (name, category_id) VALUES 
('Cakes', 5),
('Ice Cream', 5),
('Pastries', 5),
('Pudding', 5),
('Chocolate', 5);

-- Steak Sub-Categories
INSERT INTO sub_categories (name, category_id) VALUES 
('Ribeye', 2),
('Sirloin', 2),
('T-Bone', 2),
('Filet Mignon', 2),
('Wagyu', 2);

-- Coffee Sub-Categories
INSERT INTO sub_categories (name, category_id) VALUES 
('Espresso', 3),
('Cappuccino', 3),
('Latte', 3),
('Mocha', 3),
('Cold Brew', 3);

-- Burger Sub-Categories
INSERT INTO sub_categories (name, category_id) VALUES 
('Cheeseburger', 4),
('Veggie Burger', 4),
('Chicken Burger', 4),
('BBQ Burger', 4),
('Double Patty', 4);
