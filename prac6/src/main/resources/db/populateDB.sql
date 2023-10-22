INSERT INTO public.users(login, password) VALUES
('admin', '$2a$12$xmQNl72q8ryViec59QO9wev9MEz8DZ/KibZ3/e0LubfpvGWVV/5i.'),
('user', '$2a$12$bHCm8ofecur1YbAYZ2n7V.xv1omdmSrdCS/dFtJJyCAmGsKFQIrzy');

INSERT INTO public.basket(id) VALUES
 (1);

 INSERT INTO public.products(
 	product_type, name, seller_number, price, storage_amount, accum_capacity, tank_capacity, author, manufacture) VALUES
 	('BOOK', 'Sevastopol Sketches', 'IBS304851', 99, 10, null, null, 'Lev Tolstoy', null),
 	('BOOK', 'The Kreutzer Sonata', 'IBS304851', 68, 10, null, null, 'Lev Tolstoy', null),
 	('BOOK', 'A Confession', 'IBS304851', 84, 10, null, null, 'Lev Tolstoy', null),
 	('PHONE', 'Samsung A300', 'SAM2541', 208, 10, 4200, null, null, 'Samsung Inc.'),
 	('PHONE', 'Huawei Mate X3', 'HUA8457', 754, 10, 5800, null, null, 'Huawei Inc.'),
 	('WMACHINE', 'LG Smart Wash 5514', 'LGA2147', 538, 10, null, 5, null, 'LG Inc.');





