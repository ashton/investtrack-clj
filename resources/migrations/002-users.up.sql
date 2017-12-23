CREATE TABLE users (
    "id"            serial,
    "name"          varchar(50)  NOT NULL,
    "email"         varchar(50)  NOT NULL,
    "password"      varchar(250) NOT NULL,
    "created-at"    timestamp    NOT NULL,
    PRIMARY KEY ("id")
);

