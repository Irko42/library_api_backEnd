package de.dhbw.tim21.library.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.*;
import de.dhbw.tim21.library.model.book;
import java.sql.*;


@RestController
@RequestMapping(value = "/api/v2")
public class librarycontroller {
    @GetMapping
    public String welcometext() {
        return "library is loaded and ready!";
    }
    @PutMapping(value = "/set_new_book/{title}/{author}/{description}/{price}/{isbn}")
    public String addbook(@PathVariable("title") String title, @PathVariable("author") String author,
                          @PathVariable("description") String description, @PathVariable("price") Double price,
                          @PathVariable("isbn") Long isbn) throws SQLException {
        book.bookprice = price;
        book.booktitle = title;
        book.bookauthor = author;
        book.bookdescription = description;
        book.bookisbn = isbn;
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql = "INSERT INTO BOOK VALUES('" + title + "','" + author + "','" + description + "','" + price + "','" + isbn + "')";
        st.executeUpdate(sql);
        return "Buch wurde hinzugefügt";
    }

    @PatchMapping(value = "/set_book_borrowed/{isbn}")
    public String lendbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "INSERT INTO AWAY_BOOK (BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN)\n" +
                "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN FROM BOOK WHERE BOOK_ISBN =" +
                "" + isbn + "";
        st.executeUpdate(sql2);
        String sql = "DELETE FROM BOOK WHERE BOOK_ISBN = " + isbn;
        st.executeUpdate(sql);
        return "Buch mit der ISBN:" + isbn + " wurde ausgeliehen!";
    }

    @PatchMapping(value = "/set_book_returned/{isbn}")
    public String returnbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "INSERT INTO BOOK(BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN)\n" +
                "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN FROM AWAY_BOOK WHERE BOOK_ISBN =" +
                "" + isbn + "";
        st.executeUpdate(sql2);
        String sql = "DELETE FROM AWAY_BOOK WHERE BOOK_ISBN = " + isbn;
        st.executeUpdate(sql);
        return "Buch mit der ISBN:" + isbn + " wurde zurückgegeben!";
    }

    @GetMapping(value = "/get_book/{isbn}")
    @JsonIgnore
    public String searchbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        String sql = ("SELECT * FROM BOOK WHERE BOOK_ISBN =" + isbn + "");
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("BOOK_TITLE");
                String author = rs.getString("BOOK_AUTHOR");
                String price = rs.getString("BOOK_PRICE");
                String description = rs.getString("BOOK_DESCRIPTION");
                String searchresult = ("Buchtitel: \n" +
                        "" + title + ", \n" +
                        "Buchautor: \n" +
                        "" + author + "\n" +
                        "Buchpreis: \n" +
                        "" + price + " Euro \n" +
                        "Beschreibung: \n" +
                        "" + description + "");
                return searchresult;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @PatchMapping(value = "/set_book_lost/{isbn}")
    public String reportlostbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "INSERT INTO LOST_BOOK(BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN)\n" +
                "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN FROM AWAY_BOOK WHERE " +
                "BOOK_ISBN =" + isbn + "";
        st.executeUpdate(sql2);
        String sql = "DELETE FROM AWAY_BOOK WHERE BOOK_ISBN = " + isbn;
        st.executeUpdate(sql);
        return "Buch mit der ISBN:" + isbn + " wurde als verloren vermerkt!!";
    }

    @PatchMapping(value = "/set_gifted_book/{isbn}")
    public String giftbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "INSERT INTO BOOK (BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN)\n" +
                "SELECT BOOK_TITLE, BOOK_AUTHOR, BOOK_DESCRIPTION, BOOK_PRICE, BOOK_ISBN FROM LOST_BOOK WHERE " +
                "BOOK_ISBN =" +
                "" + isbn + "";
        st.executeUpdate(sql2);
        String sql = "DELETE FROM LOST_BOOK WHERE BOOK_ISBN = " + isbn;
        st.executeUpdate(sql);
        return "Buch mit der ISBN:" + isbn + " wurde wieder gespendet!!";
    }

    @PatchMapping(value = "/set_book_attribute/{isbn}/{attr}/{val}")
    public String changebookattr(@PathVariable long isbn, String attr, String val) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        if (attr.equals("title") || attr.equals("isbn") || attr.equals("author") || attr.equals("description") ||
                attr.equals("price")) {
            if (attr.equals("isbn")) {
                return "cant change isbn!";
            } else {
                String sql = "UPDATE BOOK SET BOOK_" + attr.toUpperCase() + " = " + val + " WHERE BOOK_ISBN = " +
                        isbn + "";
                String sql2 = "SELECT * FROM BOOK WHERE BOOK_ISBN =" + isbn + "";
                st.executeUpdate(sql);
                ResultSet rs = st.executeQuery(sql2);
                rs.next();
                String value = rs.getString("BOOK_" + attr.toUpperCase());
                rs.next();
                return "Neuer Wert: " + value;
            }
        }
        return null;
    }

    @GetMapping(value = {"/get_book/{isbn}/{attr}"})
    public String display_attribute(@PathVariable Long isbn, @PathVariable String attr) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        if (attr.equals("isbn") || attr.equals("title") || attr.equals("author") || attr.equals("description") ||
                attr.equals("price")) {
            String sql = "SELECT BOOK_" + attr + " FROM BOOK WHERE BOOK_ISBN = " + isbn + "";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        } else {
            return "Error";
        }
    }
}
