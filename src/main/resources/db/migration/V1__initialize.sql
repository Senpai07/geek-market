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

insert into products (title, price)
values ('Хлеб', 21),
       ('Яйца', 22),
       ('Молоко', 23),
       ('Печенье', 24),
       ('Лук', 25),
       ('Картошка', 26),
       ('Огурцы', 27),
       ('Бананы', 28),
       ('Апельсины', 29),
       ('Лимоны', 31),
       ('Ананасы', 32),
       ('Арбузы', 33),
       ('Персики', 34),
       ('Хурма', 35),
       ('Виноград', 36),
       ('Перец чили', 37),
       ('Перец болгарский', 38),
       ('Чеснок', 39),
       ('Имбирь', 40);
