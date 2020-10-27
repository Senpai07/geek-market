create table users
(
    id       bigserial,
    username varchar(30) not null,
    password varchar(80) not null,
    email    varchar(50) unique,
    primary key (id)
);

create table roles
(
    id   serial,
    name varchar(50) not null,
    primary key (id)
);

CREATE TABLE users_roles
(
    id      bigserial primary key,
    user_id bigint not null,
    role_id int    not null,
    unique (user_id, role_id),
    foreign key (user_id) references users (id),
    foreign key (role_id) references roles (id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN'),
       ('SOMETHING');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'user@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (1, 2);

create table categories
(
    id    bigserial primary key,
    title varchar(255)
);

create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       int,
    category_id bigint references categories (id)
);

create table orders
(
    id            bigserial primary key,
    user_id       bigint references users (id),
    price         int,
    address       varchar(255),
    phone         varchar(50),
    receiver_name varchar(255)
);

create table order_items
(
    id                bigserial primary key,
    product_id        bigint references products (id),
    order_id          bigint references orders (id),
    quantity          int,
    price             int,
    price_per_product int
);

insert into categories (title)
values ('Овощи'),
       ('Фрукты'),
       ('Выпечка'),
       ('Молочка');

insert into products (title, price, category_id)
values ('Хлеб', 21, 3),
       ('Сыр', 22, 4),
       ('Молоко', 23, 4),
       ('Печенье', 24, 3),
       ('Лук', 25, 1),
       ('Картошка', 26, 1),
       ('Огурцы', 27, 1),
       ('Бананы', 28, 2),
       ('Апельсины', 29, 2),
       ('Лимоны', 31, 2),
       ('Ананасы', 32, 2),
       ('Арбузы', 33, 2),
       ('Персики', 34, 2),
       ('Хурма', 35, 2),
       ('Виноград', 36, 2),
       ('Перец чили', 37, 1),
       ('Перец болгарский', 38, 1),
       ('Чеснок', 39, 1),
       ('Имбирь', 40, 1);
