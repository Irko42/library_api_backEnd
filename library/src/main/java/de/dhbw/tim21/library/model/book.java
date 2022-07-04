package de.dhbw.tim21.library.model;

import org.json.*;
import org.springframework.context.annotation.Configuration;

@Configuration
public class book {
    /**
    Class of single book
    Attributes: isbn,title,author,description,price
    Function: createNewbook, creates JSONObject newbook, appends it with single attributes given by function

    */
    public static Long bookisbn;
    public static String booktitle;
    public static String bookauthor;
    public static String bookdescription;
    public static Double bookprice;

    public static JSONObject createNewbook(String booktitle, String bookauthor, String bookdescription, Integer bookprice,
                                    Long bookisbn) {
        /***
         *
         *Constructor for book object, as JSONObject
         */
        JSONObject newbook = new JSONObject();
        newbook.append("Title", booktitle);
        newbook.append("Author", bookauthor);
        newbook.append("Description", bookdescription);
        newbook.append("Price", bookprice);
        newbook.append("ISBN", bookisbn);
        return newbook;
    }
}
