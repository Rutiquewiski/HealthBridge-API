create table user_auth(

    id bigint not null auto_increment,
    name varchar(100) not null,
    username varchar(100) not null unique,
    password varchar(100) not null,

    primary key(id)

);