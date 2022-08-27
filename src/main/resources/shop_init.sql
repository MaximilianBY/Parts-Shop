DROP SCHEMA IF EXISTS PARTS_SHOP;
CREATE SCHEMA IF NOT EXISTS PARTS_SHOP COLLATE UTF8MB4_0900_AI_CI;

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

# INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
#                               BALANCE)
#     VALUE (
#            'Max',
#            '123456',
#            'Maximilian',
#            'Poltorzhickiy',
#            '1990.01.01',
#            'max@gmail.com',
#            '+375259457864',
#            32500.23
#     );
# INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
#                               BALANCE)
#     VALUE (
#            'Anna',
#            '123456',
#            'Anna',
#            'Kovrizhnih',
#            '1992.07.14',
#            'anna@gmail.com',
#            '+375252542354',
#            500.01
#     );
# INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
#                               BALANCE)
#     VALUE (
#            'Alia',
#            '123456',
#            'Alina',
#            'Poltorzhickaya',
#            '1988.10.15',
#            'alina.box@gmail.com',
#            '+375293569784',
#            120.35
#     );
# INSERT INTO PARTS_SHOP.USERS (LOGIN, PASSWORD, NAME, SURNAME, BIRTHDAY, EMAIL, PHONE_NUMBER,
#                               BALANCE)
#     VALUE (
#            'Vas',
#            '123456',
#            'Vasya',
#            'Ivanov',
#            '1986.02.21',
#            'vasya.boss@gmail.com',
#            '+375337854312',
#            325.98
#     );

DROP TABLE IF EXISTS PARTS_SHOP.CARS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.CARS
(
    ID              SERIAL UNIQUE,
    CAR_INDEX       VARCHAR(10) UNIQUE NOT NULL,
    BRAND           VARCHAR(20),
    MODEL           VARCHAR(30),
    BODY            VARCHAR(100),
    TRANSMISSION    VARCHAR(25),
    ENGINE_ID       VARCHAR(30),
    TYPE_FUEL       VARCHAR(10),
    ENGINE_CC       VARCHAR(5),
    ENGINE_FEATURES VARCHAR(30),
    VIN             VARCHAR(20) UNIQUE,
    YEAR            SMALLINT UNSIGNED,
    COLOR           VARCHAR(15),
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_CARS_CAR_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_CARS_CAR_INDEX_UNIQUE (CAR_INDEX ASC)
);

DROP TABLE IF EXISTS PARTS_SHOP.PARTS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.PARTS
(
    ID                  SERIAL UNIQUE,
    PART_INDEX          BIGINT UNSIGNED UNIQUE NOT NULL,
    PART_TYPE           VARCHAR(40)            NOT NULL,
    CONSTRUCTION_NUMBER VARCHAR(100),
    DESCRIPTION         VARCHAR(1000),
    PRICE               NUMERIC(7, 2) UNSIGNED,
    CONSTRAINT COMP_KEY_ID_PART_INDEX PRIMARY KEY (ID, PART_INDEX),
    UNIQUE INDEX IDX_PARTS_PART_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_PARTS_PART_INDEX_UNIQUE (PART_INDEX ASC)
);

DROP TABLE IF EXISTS PARTS_SHOP.IMAGES;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.IMAGES
(
    ID          SERIAL UNIQUE,
    CAR_ID      BIGINT UNSIGNED,
    PART_INDEX  BIGINT UNSIGNED UNIQUE,
    IMAGE_PATHS VARCHAR(300) UNIQUE NOT NULL,
    PRIMARY KEY (ID),
    FOREIGN KEY (CAR_ID) REFERENCES PARTS_SHOP.CARS (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PART_INDEX) REFERENCES PARTS_SHOP.PARTS (PART_INDEX) ON DELETE CASCADE ON UPDATE CASCADE
);