DROP SCHEMA IF EXISTS PARTS_SHOP;
CREATE SCHEMA IF NOT EXISTS PARTS_SHOP COLLATE UTF8_GENERAL_CI;

DROP TABLE IF EXISTS PARTS_SHOP.USERS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.USERS
(
    ID           SERIAL UNIQUE,
    LOGIN        VARCHAR(20) NOT NULL UNIQUE,
    PASSWORD     VARCHAR(20) NOT NULL,
    NAME         VARCHAR(20) NOT NULL,
    SURNAME      VARCHAR(20) NOT NULL,
    BIRTHDAY     DATE,
    EMAIL        VARCHAR(30) NOT NULL UNIQUE,
    PHONE_NUMBER VARCHAR(13) NOT NULL UNIQUE,
    BALANCE      NUMERIC(7, 2) UNSIGNED,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_USERS_USER_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_USERS_LOGIN_UNIQUE (LOGIN ASC)
);

INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
                              BALANCE)
    VALUE (
           'Max',
           '123456',
           'Maximilian',
           'Poltorzhickiy',
           '1990.01.01',
           'max@gmail.com',
           '+375259457864',
           32500.23
    );
INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
                              BALANCE)
    VALUE (
           'Anna',
           '123456',
           'Anna',
           'Kovrizhnih',
           '1992.07.14',
           'anna@gmail.com',
           '+375252542354',
           500.01
    );
INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
                              BALANCE)
    VALUE (
           'Alia',
           '123456',
           'Alina',
           'Poltorzhickaya',
           '1988.10.15',
           'alina.box@gmail.com',
           '+375293569784',
           120.35
    );
INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
                              BALANCE)
    VALUE (
           'Vas',
           '123456',
           'Vasya',
           'Ivanov',
           '1986.02.21',
           'vasya.boss@gmail.com',
           '+375337854312',
           325.98
    );