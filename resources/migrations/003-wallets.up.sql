CREATE TABLE wallets (
    "id"            serial,
    "name"          varchar NOT NULL,
    "description"   text,
    "user_id"       integer    references users,
    PRIMARY KEY ("id")
);
