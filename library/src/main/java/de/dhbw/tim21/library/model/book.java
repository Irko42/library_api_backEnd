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
    public static Long isbn;
    public static String title;
    public static String author;
    public static String description;
    public static Double price;

    public static String state;

    public static Integer available;

    public static Integer borrowed;

    public static Integer lost;

    public static JSONObject createNewbook(String title, String author, String description, Integer price, String state,
                                    Integer available, Integer borrowed, Integer lost, Long isbn) {
        /***
         *
         *Constructor for book object, as JSONObject
         */
        JSONObject newbook = new JSONObject();
        newbook.append("Title", title);
        newbook.append("Author", author);
        newbook.append("Description", description);
        newbook.append("Price", price);
        newbook.append("ISBN", isbn);
        newbook.append("State", state);
        return newbook;
    }
}
