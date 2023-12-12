CREATE TABLE user_auth(

    id bigint NOT NULL auto_increment,
    name VARCHAR(100) NOT NULL,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    active TINYINT NOT NULL,

    PRIMARY KEY(id)

);