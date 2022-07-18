WIP (Work in Progress)

Bibliothek
---
Erstelle eine Web-Applikation für die Präsentation von Büchern in einer Bibliothek. Stellen Sie insbesondere Details dar wie Titel, Autor und ISBN der Bücher.

UI mit Swagger:         http://localhost:8080/swagger-ui/index.html

Console der H2-DB:      http://localhost:8080/h2

Decken sie weitere Funktionen ab, wie z.B. : das Ausleihen von Büchern, das Zurückgeben jener Bücher, Bücher komplett Anzeigen.

Funktionsbeispiel:
(zu finden unter: https://github.com/Irko42/library_api_backEnd/blob/main/library/src/main/java/de/dhbw/tim21/library/controller/librarycontroller.java)
/set_new_book/{title}/{author}/{description}/{price}/{isbn} Fügt der DB ein neues Buch mit den Attributen der Funktion hinzu.

/set_book_borrowed/{isbn} Setzt ein Buch anhand seiner ISBN als ausgeliehen.

/set_book_attribute/{isbn}/{attr}/{val} Ändert ein bestimmtes (angegebenes Attribut) eines Buches

Wie die Datenbank installiert wird findet man hier: 

https://github.com/Irko42/library_api_backEnd/blob/main/database/READ%20ME%20DATABASE%20!

Model der einzelnen Bücher:

https://github.com/Irko42/library_api_backEnd/blob/main/library/src/main/java/de/dhbw/tim21/library/model/book.java

WIP 
(Work in Progress)
