CREATE TABLE if not exists trips
(
    id          BIGINT       NOT NULL,
    start_date  date         NOT NULL,
    end_date    date         NOT NULL,
    description VARCHAR(255) NOT NULL,
    CONSTRAINT pk_trips PRIMARY KEY (id)
);
