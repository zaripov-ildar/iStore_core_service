-- Creating
create table categories
(
    id         bigserial primary key,
    title      varchar(255),
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);
create table products
(
    id          bigserial primary key,
    title       varchar(255),
    price       numeric(8, 2) not null,
    category_id bigint references categories (id),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);

create table orders
(
    id          bigserial primary key,
    username    varchar(255),
    total_price numeric(8, 2),
    created_at  timestamp default current_timestamp,
    updated_at  timestamp default current_timestamp
);
create table order_items
(
    id                bigserial primary key,
    price             numeric(8, 2),
    price_per_product numeric(8, 2),
    quantity          int,
    order_id          bigint references orders (id),
    product_id        bigint references products (id),
    created_at        timestamp default current_timestamp,
    updated_at        timestamp default current_timestamp
);
-- Filling
insert into categories(title)
values ('Product'),
       ('Hats');

insert into products(title, price, category_id)
values ('Quark', 70.89, 1),
       ('Soy meat', 119.75, 1),
       ('Curry wurst', 250.33, 1),
       ('Tiara', 20000.01, 2),
       ('Sock hat', 450.45, 2),
       ('Fez', 654.34, 2),
       ('Fez2', 654.34, 2),
       ('Fez3', 654.34, 2);