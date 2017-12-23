CREATE TYPE transaction_type AS ENUM ('withdrawal', 'deposit', 'purchase', 'sale', 'revenue', 'expense');

CREATE TABLE transactions (
 id             serial,
 stock          VARCHAR(10)        NOT NULL,
 type           transaction_type   NOT NULL,
 price          DOUBLE PRECISION   NOT NULL,
 value          DOUBLE PRECISION   NOT NULL,
 amount         INTEGER            NOT NULL,
 date           DATE               NOT NULL,
 wallet_id      INTEGER            REFERENCES wallets ON DELETE CASCADE,
 user_id        INTEGER            REFERENCES users ON DELETE CASCADE,
 PRIMARY KEY ("id", "date")
);

SELECT create_hypertable('transactions', 'date');