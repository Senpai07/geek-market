create table customers
(
    id   bigserial,
    name varchar(255) not null,
    primary key (id)
);

create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);

create table orders
(
    id          bigserial primary key,
    customer_id bigint references customers (id),
    price       int,
    name        varchar(255),
    address     varchar(255),
    phone       varchar(50)
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

insert into customers (name)
values ('Вася'),
       ('Джон'),
       ('Гена');

insert into products (title, price)
values ('Хлеб', 21),
       ('Яйца', 22)
--        ('Молоко', 23),
--        ('Печенье', 24),
--        ('Лук', 25),
--        ('Картошка', 26),
--        ('Огурцы', 27),
--        ('Бананы', 28),
--        ('Апельсины', 29),
--        ('Лимоны', 31),
--        ('Ананасы', 32),
--        ('Арбузы', 33),
--        ('Персики', 34),
--        ('Хурма', 35),
--        ('Виноград', 36),
--        ('Перец чили', 37),
--        ('Перец болгарский', 38),
--        ('Чеснок', 39),
--        ('Имбирь', 40);
