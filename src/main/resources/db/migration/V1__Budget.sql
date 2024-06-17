CREATE TYPE budget_type AS ENUM ('Приход', 'Расход');

CREATE TABLE budget
(
    id         SERIAL PRIMARY KEY,
    year       INT NOT NULL,
    month      INT NOT NULL,
    amount     INT NOT NULL,
    type       budget_type NOT NULL,
    author_id  INT,
    FOREIGN KEY (author_id) REFERENCES author(id)
);

CREATE TABLE author
(
    id           SERIAL PRIMARY KEY,
    fio          TEXT NOT NULL,
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);