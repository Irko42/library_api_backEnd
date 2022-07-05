package de.dhbw.tim21.library.model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


public class library {
    /**
     * Class of Library (Main Class)
     * Creates JSONArray bookcollection to save books in
     */
    public static JSONArray bookcollection = new JSONArray();

    public static JSONArray load_library() {

        /**Function to load the library with random books, to be used in /show_library
         for later use, with json file to load/save data
         */
        //    JSONObject book2 = book.createNewbook("Juergen Webers Kochtipss", "Michael Drevs",
        //             "Tolles Buch, kann man jedem empfehlen der Kochen mag!", 12,
        //             233243463456L);
        //     JSONObject book3 = book.createNewbook("Sportwissenschaft", "Stefn Wohl",
        //             "Sport ist mord!", 16,
        //             66894945656L);
        //     JSONObject book4 = book.createNewbook("Informatik für Anfänger", "Christian Dior",
        //             "10101000011100101", 8,
        //             849498846633L);
        //     JSONObject book5 =
        //             book.createNewbook("Mechaniker in der Ausbildung", "Andreas Schweizer",
        //             "Schrauben macht Spaß", 6,
        //              68423463456L);
        //     bookcollection.put(book2);
        //     bookcollection.put(book3);
        //     bookcollection.put(book4);
        //     bookcollection.put(book5);
        //     return bookcollection;
        // }

        return null;
    }
}