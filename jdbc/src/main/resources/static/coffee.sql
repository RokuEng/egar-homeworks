DROP TABLE IF EXISTS Provider CASCADE;
CREATE TABLE Provider
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 4096)
        CONSTRAINT Provider_pk PRIMARY KEY,
    "name" VARCHAR(64) NOT NULL,
    "phone" VARCHAR(64) NOT NULL,
    "margin" NUMERIC(6, 2) NOT NULL
);

DROP TABLE IF EXISTS Coffee CASCADE;
CREATE TABLE Coffee
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 4096)
        CONSTRAINT Coffee_pk PRIMARY KEY,
    "name" VARCHAR(64) NOT NULL,
    "type" SMALLINT NOT NULL
);

DROP TABLE IF EXISTS Provider_Coffee CASCADE;
CREATE TABLE Provider_Coffee
(
    provider_id INTEGER NOT NULL,
    coffee_id INTEGER NULL,
    PRIMARY KEY (provider_id, coffee_id)
);

DROP TABLE IF EXISTS Customer CASCADE;
CREATE TABLE Customer
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 4096)
        CONSTRAINT Customer_pk PRIMARY KEY,
    "name" VARCHAR(64) NOT NULL,
    "phone" VARCHAR(64) NOT NULL,
    favorite_coffee_id INTEGER NOT NULL
        CONSTRAINT Coffee_fk REFERENCES Coffee (id)
        ON DELETE CASCADE
);

DROP TABLE IF EXISTS Organisation CASCADE;
CREATE TABLE Organisation
(
    id INTEGER GENERATED ALWAYS AS IDENTITY (MAXVALUE 4096)
        CONSTRAINT Organisation_pk PRIMARY KEY,
    "email" VARCHAR(64) NOT NULL,
    customer_id INTEGER NOT NULL
        CONSTRAINT Customer_fk REFERENCES Customer (id)
        ON DELETE CASCADE
);

INSERT INTO Coffee(name, type) VALUES ('Java' , 0);
INSERT INTO Coffee(name, type) VALUES ('Java' , 1);
INSERT INTO Coffee(name, type) VALUES ('Espresso' , 0);
INSERT INTO Coffee(name, type) VALUES ('Espresso' , 1);
INSERT INTO Coffee(name, type) VALUES ('Arabica' , 1);

INSERT INTO Provider(name, phone, margin) VALUES ('John', '+0 000 000 00 00', 1.1);

INSERT INTO Provider_Coffee VALUES (1, 1);
INSERT INTO Provider_Coffee VALUES (1, 2);
INSERT INTO Provider_Coffee VALUES (1, 3);

INSERT INTO Customer(name, phone, favorite_coffee_id) VALUES ('Alex', '+0 000 000 00 00', 1);
INSERT INTO Customer(name, phone, favorite_coffee_id) VALUES ('Orgo Ltd', '+0 000 000 00 00', 3);

INSERT INTO Organisation(email, customer_id) VALUES ('email@mail.mail', 2);