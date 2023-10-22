INSERT INTO public.users(name, email, login, password) VALUES
('Sophie Smith', 'sophie@mail.ru', 'admin', '$2a$12$xmQNl72q8ryViec59QO9wev9MEz8DZ/KibZ3/e0LubfpvGWVV/5i.'),
('Tesh Turner', 'tesh@mail.ru','user', '$2a$12$bHCm8ofecur1YbAYZ2n7V.xv1omdmSrdCS/dFtJJyCAmGsKFQIrzy');

INSERT INTO public.books(author, seller_num, pr_type, price, name) VALUES
('Lev Tolstoy', 100, 'BOOK', 1500, 'Anna Karenina'),
('Oscar Wild', 101, 'BOOK', 1200, 'The Picture of Dorian Gray');

INSERT INTO public.telephones(producer, battery_capacity, seller_num, pr_type, price, name) VALUES
('Apple', 4352, 200, 'ELECTRONICS', 150000, 'Iphone 15'),
('Samsung', 5000, 201, 'ELECTRONICS', 30000, 'Galaxy A54');

INSERT INTO public.washing_machines(producer, tank_capacity, seller_num, pr_type, price, name) VALUES
('Bosch', 5, 301, 'PLUMBING', 45000, 'WLG20060'),
('Samsung', 7, 300, 'PLUMBING', 41000, 'WW70A6S23AW');