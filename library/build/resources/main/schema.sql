DROP TABLE IF EXISTS BOOK;
    CREATE TABLE BOOK (
    book_title VARCHAR(50) NOT NULL,
    book_author VARCHAR(25) NOT NULL,
    book_description VARCHAR(1500) NOT NULL,
    book_price INT(25) NOT NULL,
    book_isbn INT(50) NOT NULL PRIMARY KEY
    );
CREATE AWAY_BOOK(
    book_title VARCHAR(50) NOT NULL,
    book_author VARCHAR(25) NOT NULL,
    book_description VARCHAR(1500) NOT NULL,
    book_price INT(25) NOT NULL,
    book_isbn INT(50) NOT NULL PRIMARY KEY
)
CREATE LOST_BOOK(
    book_title VARCHAR(50) NOT NULL,
    book_author VARCHAR(25) NOT NULL,
    book_description VARCHAR(1500) NOT NULL,
    book_price INT(25) NOT NULL,
    book_isbn INT(50) NOT NULL PRIMARY KEY
)

CREATE TABLE BOOK (
    title VARCHAR(50) NOT NULL,
    author VARCHAR(25) NOT NULL,
    description VARCHAR(1500),
    price INT(25) NOT NULL,
    state VARCHAR(15),
    available INT(10),
    borrowed INT(10),
    lost INT(10),
    isbn INT(50) NOT NULL PRIMARY KEY
    );