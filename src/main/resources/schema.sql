CREATE TABLE IF NOT EXISTS "users" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "name" VARCHAR(100) NOT NULL,
    "email" VARCHAR(100) NOT NULL,
    "password" VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS "phones" (
    "id" SERIAL NOT NULL PRIMARY KEY,
    "number" VARCHAR(20) NOT NULL,
    "user_id" INT REFERENCES "users"("id")
);