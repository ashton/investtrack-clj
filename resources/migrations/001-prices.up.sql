CREATE EXTENSION IF NOT EXISTS timescaledb CASCADE;

CREATE TABLE "prices" (
 "id"             serial            NOT NULL,
 "code"           VARCHAR(20)       NOT NULL,
 "market-type"    VARCHAR(5)        NOT NULL,
 "name"           TEXT              NOT NULL,
 "opening-price"  DOUBLE PRECISION  NOT NULL,
 "max-price"      DOUBLE PRECISION  NOT NULL,
 "min-price"      DOUBLE PRECISION  NOT NULL,
 "avg-price"      DOUBLE PRECISION  NOT NULL,
 "last-price"     DOUBLE PRECISION  NOT NULL,
 "best-buy"       DOUBLE PRECISION  NOT NULL,
 "best-sell"      DOUBLE PRECISION  NOT NULL,
 "trade-count"    BIGINT            NOT NULL,
 "trade-amount"   BIGINT            NOT NULL,
 "date"           TIMESTAMP         NOT NULL,
 PRIMARY KEY ("date", "id")
);

SELECT create_hypertable('prices', 'date');