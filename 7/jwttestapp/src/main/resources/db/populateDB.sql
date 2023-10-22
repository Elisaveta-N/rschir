/*
insert into roles (name)
values
('ROLE_USER'), ('ROLE_ADMIN'), ('ROLE_SELLER');

insert into users (username, password, email)
values
('user', '$2a$12$ewUVY2N2KT/ONExsQ1WtE.ozFvoYezCBXdx7Ah1QU/BbwYWHDnhRW', 'user@gmail.com'),
('admin', '$2a$12$nK/Ebv9P03c7tYXwUFKSGOlWB.iPgPU3X0oe8LT8ZSLAxJkKN4WCG', 'admin@gmail.com'),
('seller', '$2a$12$HRPR86GAvZ4u2xJyriiRfu4JVvje4wfI5Ks/dzjFekXT6CWl9tGjG', 'seller@gmail.com');

insert into users_roles (user_id, role_id)
values
(1, 1),
(2, 2),
(3, 3);
*/