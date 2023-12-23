CREATE TABLE IF NOT EXISTS book
(
    id          SERIAL PRIMARY KEY,
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    price       DOUBLE PRECISION,
    author      VARCHAR(255)
);
