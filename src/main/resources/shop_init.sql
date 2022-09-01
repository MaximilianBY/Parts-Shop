DROP SCHEMA IF EXISTS PARTS_SHOP;
CREATE SCHEMA IF NOT EXISTS PARTS_SHOP;

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
    UNIQUE INDEX IDX_USERS_LOGIN_UNIQUE (LOGIN ASC),
    UNIQUE INDEX IDX_USERS_EMAIL_UNIQUE (EMAIL ASC),
    UNIQUE INDEX IDX_USERS_PHONE_NUMBER_UNIQUE (PHONE_NUMBER ASC)
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
    UNIQUE INDEX IDX_CARS_CAR_INDEX_UNIQUE (CAR_INDEX ASC),
    UNIQUE INDEX IDX_CARS_CAR_VIN_UNIQUE (VIN ASC)
);

DROP TABLE IF EXISTS PARTS_SHOP.PART_TYPE;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.PART_TYPE
(
    ID   SERIAL UNIQUE,
    TYPE VARCHAR(100) UNIQUE NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_PART_TYPE_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_PART_TYPE_TYPE_UNIQUE (TYPE ASC)
);

DROP TABLE IF EXISTS PARTS_SHOP.PART_TYPE_ADDITIONAL;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.PART_TYPE_ADDITIONAL
(
    ID           SERIAL UNIQUE,
    PART_TYPE_ID BIGINT UNSIGNED NOT NULL,
    DESCRIPTION  VARCHAR(20),
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_PART_TYPE_ADDITIONAL_ID_UNIQUE (ID ASC),
    FOREIGN KEY (PART_TYPE_ID) REFERENCES PARTS_SHOP.PART_TYPE (ID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS PARTS_SHOP.PARTS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.PARTS
(
    ID                  SERIAL UNIQUE,
    PART_INDEX          BIGINT UNSIGNED UNIQUE NOT NULL,
    CAR_ID              BIGINT UNSIGNED        NOT NULL,
    PART_TYPE           BIGINT UNSIGNED        NOT NULL,
    ADDITIONAL_ID       BIGINT UNSIGNED,
    CONSTRUCTION_NUMBER VARCHAR(100),
    DESCRIPTION         VARCHAR(500),
    PRICE               BIGINT UNSIGNED,
    IN_STOCK            BIT                    NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_PARTS_PART_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_PARTS_PART_INDEX_UNIQUE (PART_INDEX ASC),
    FOREIGN KEY (CAR_ID) REFERENCES PARTS_SHOP.CARS (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PART_TYPE) REFERENCES PARTS_SHOP.PART_TYPE (ID) ON UPDATE CASCADE,
    FOREIGN KEY (ADDITIONAL_ID) REFERENCES PARTS_SHOP.PART_TYPE_ADDITIONAL (ID) ON UPDATE CASCADE
);

DROP TABLE IF EXISTS PARTS_SHOP.IMAGES;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.IMAGES
(
    ID          SERIAL UNIQUE,
    CAR_ID      BIGINT UNSIGNED UNIQUE,
    PART_IDX    BIGINT UNSIGNED UNIQUE,
    IMAGE_PATHS VARCHAR(300) UNIQUE NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_IMAGES_ID_UNIQUE (ID ASC),
    UNIQUE INDEX IDX_IMAGES_PART_IDX_UNIQUE (PART_IDX ASC),
    UNIQUE INDEX IDX_IMAGES_IMAGE_PATHS_UNIQUE (IMAGE_PATHS ASC),
    FOREIGN KEY (CAR_ID) REFERENCES PARTS_SHOP.CARS (ID) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (PART_IDX) REFERENCES PARTS_SHOP.PARTS (PART_INDEX) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS PARTS_SHOP.ORDERS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.ORDERS
(
    ID          SERIAL UNIQUE,
    USER_ID     BIGINT UNSIGNED NOT NULL,
    ORDER_DATE  DATE            NOT NULL,
    ORDER_PRICE BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (ID),
    UNIQUE INDEX IDX_ORDERS_ID_UNIQUE (ID ASC),
    CONSTRAINT FK_USER_ID_ORDERS_USER_ID
        FOREIGN KEY (USER_ID) REFERENCES PARTS_SHOP.USERS (ID) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS PARTS_SHOP.ORDER_DETAILS;

CREATE TABLE IF NOT EXISTS PARTS_SHOP.ORDER_DETAILS
(
    ORDER_ID BIGINT UNSIGNED NOT NULL,
    PART_IDX BIGINT UNSIGNED NOT NULL,
    PRIMARY KEY (ORDER_ID, PART_IDX),
    CONSTRAINT FK_ORDER_PARTS_PART_IDX_PART_INDEX
        FOREIGN KEY (PART_IDX) REFERENCES PARTS_SHOP.PARTS (PART_INDEX) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_ORDERS_ORDER_ID_ORDERS_ID
        FOREIGN KEY (ORDER_ID) REFERENCES PARTS_SHOP.ORDERS (ID) ON DELETE CASCADE ON UPDATE CASCADE
);