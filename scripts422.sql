create table person
(
    id             serial primary key,
    name           text,
    age            smallint check ( age > 0),
    driver_license boolean default false,
    car_id         varchar references car(number)
);

create table car
(
    number varchar primary key,
    brand  text,
    model  text,
    price  numeric(9, 2)
);