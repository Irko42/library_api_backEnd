package de.dhbw.tim21.library.controller;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.web.bind.annotation.*;
import de.dhbw.tim21.library.model.book;
import java.sql.*;


@RestController
@RequestMapping(value = "/api/v2")
public class librarycontroller {
    @GetMapping
    public String startup_message() {
        return "library is ready!";
    }
    @PostMapping(value = "/book/{title}/{author}/{description}/{price}/{state}/{available}/{borrowed}/{lost}/{isbn}")
    public String addbook(@PathVariable("title") String title, @PathVariable("author") String author,
                          @PathVariable("description") String description, @PathVariable("price") Double price,
                          @PathVariable("state") String state, @PathVariable("available") Integer available,
                          @PathVariable("borrowed") Integer borrowed, @PathVariable("lost") Integer lost,
                          @PathVariable("isbn") Long isbn) throws SQLException {
        book.price = price;
        book.title = title;
        book.author = author;
        book.description = description;
        book.isbn = isbn;
        book.state = state;
        book.available = available;
        book.borrowed = borrowed;
        book.lost = lost;
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql = "INSERT INTO BOOK VALUES('" + title + "','" + author + "','" + description + "','" + price + "'," +
                "'" + state + "','" + available + "','" + borrowed + "','" + lost + "','" + isbn + "')";
        st.executeUpdate(sql);
        return "Buch wurde hinzugefügt";
    }

    @PutMapping(value = "/borrow_book/{isbn}")
    public String lendbook(@PathVariable Long isbn) throws SQLException {
        // checkup availability !
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "UPDATE BOOK SET borrowed = borrowed + 1 WHERE ISBN = " + isbn;
        String sql3 = "UPDATE BOOK SET available = available - 1 WHERE ISBN = " + isbn;
        st.executeUpdate(sql2);
        st.executeUpdate(sql3);
        return "Buch mit der ISBN:" + isbn + " wurde ausgeliehen!";
    }

    @PutMapping(value = "/return_book/{isbn}")
    public String returnbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "UPDATE BOOK SET borrowed = borrowed - 1 WHERE ISBN =" + isbn;
        String sql3 = "UPDATE BOOK SET available = available + 1 WHERE ISBN =" + isbn;
        st.executeUpdate(sql2);
        st.executeUpdate(sql3);
        return "Buch mit der ISBN:" + isbn + " wurde zurückgegeben!";
    }

    @GetMapping(value = "/book/{isbn}")
    @JsonIgnore
    public String searchbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        String sql = ("SELECT * FROM BOOK WHERE ISBN =" + isbn + "");
        try (Statement st = conn.createStatement()) {
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                String title = rs.getString("TITLE");
                String author = rs.getString("AUTHOR");
                String price = rs.getString("PRICE");
                String description = rs.getString("DESCRIPTION");
                String state = rs.getString("STATE");
                String available = rs.getString("AVAILABLE");
                String borrowed = rs.getString("BORROWED");
                String lost = rs.getString("LOST");
                String searchresult = ("Buchtitel: \n" +
                        "" + title + ", \n" +
                        "Buchautor: \n" +
                        "" + author + "\n" +
                        "Buchpreis: \n" +
                        "" + price + " Euro \n" +
                        "Verfügbare Bücher: \n" +
                        "" + available + "\n" +
                        "Ausgeliehene Bücher: \n" +
                        "" + borrowed + "\n" +
                        "Verlorene Bücher: \n" +
                        "" + lost + "\n" +
                        "Beschreibung: \n" +
                        "" + description + "");
                return searchresult;
            }
        } catch (SQLException e) {
            return null;
        }
        return null;
    }

    @PutMapping(value = "/lose_book/{isbn}")
    public String reportlostbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "UPDATE BOOK SET LOST = LOST + 1 WHERE ISBN =" + isbn;
        String sql3 = "UPDATE BOOK SET BORROWED = BORROWED - 1 WHERE ISBN =" + isbn;
        st.executeUpdate(sql2);
        st.executeUpdate(sql3);
        return "Buch mit der ISBN:" + isbn + " wurde als verloren vermerkt!!";
    }

    @PutMapping(value = "/gift_book/{isbn}")
    public String giftbook(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql2 = "UPDATE BOOK SET LOST = LOST - 1 WHERE ISBN = "+ isbn;
        String sql3 = "UPDATE BOOK SET AVAILABLE = AVAILABLE + 1 WHERE ISBN = "+ isbn;
        st.executeUpdate(sql2);
        st.executeUpdate(sql3);
        return "Buch mit der ISBN:" + isbn + " wurde wieder gespendet!!";
    }

    @PutMapping(value = "/book_attribute/{isbn}/{attr}/{val}")
    public String changebookattr(@PathVariable long isbn, String attr, String val) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        if (attr.equals("title") || attr.equals("isbn") || attr.equals("author") || attr.equals("description") ||
                attr.equals("price")) {
            if (attr.equals("isbn")) {
                return "cant change isbn!";
            } else {
                String sql = "UPDATE BOOK SET " + attr.toUpperCase() + " = " + val + " WHERE ISBN = " + isbn;
                String sql2 = "SELECT * FROM BOOK WHERE ISBN = " + isbn;
                st.executeUpdate(sql);
                ResultSet rs = st.executeQuery(sql2);
                rs.next();
                String value = rs.getString(attr.toUpperCase());
                rs.next();
                return "Neuer Wert: " + value;
            }
        }
        return null;
    }

    @GetMapping(value = {"/book_single_attr/{isbn}/{attr}"})
    public String display_attribute(@PathVariable Long isbn, @PathVariable String attr) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        if (attr.equals("isbn") || attr.equals("title") || attr.equals("author") || attr.equals("description") ||
                attr.equals("price") || attr.equals("state") || attr.equals("available")|| attr.equals("borrowed")||
                attr.equals("lost")) {
            String sql = "SELECT " + attr + " FROM BOOK WHERE ISBN = " + isbn + "";
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            return rs.getString(1);
        } else {
            return "Error";
        }
    }
    @DeleteMapping(value = {"/book/{isbn}"})
    public void del_book(@PathVariable Long isbn) throws SQLException {
        String url = "jdbc:h2:~/library";
        Connection conn = DriverManager.getConnection(url, "sa", "password");
        Statement st = conn.createStatement();
        String sql = "DELETE FROM BOOK WHERE ISBN = "+ isbn;
        st.executeUpdate(sql);
        System.out.println(" Buch wurde gelöscht!");
    }
}
