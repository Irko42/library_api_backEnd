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