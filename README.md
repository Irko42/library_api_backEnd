Bibliothek
---
Erstelle eine Web-Applikation für die Präsentation von Büchern in einer Bibliothek. Stellen Sie insbesondere Details dar wie Titel, Autor und ISBN der Bücher.

Decken sie weitere Funktionen ab, wie z.B. : das Ausleihen von Büchern, das Zurückgeben jener Bücher, Bücher komplett Anzeigen.

Funktionsbeispiel:
(zu finden unter: https://github.com/Irko42/library_api_backEnd/blob/main/library/src/main/java/de/dhbw/tim21/library/controller/librarycontroller.java)
/set_new_book/{title}/{author}/{description}/{price}/{isbn} Fügt der DB ein neues Buch mit den Attributen der Funktion hinzu.

/set_book_borrowed/{isbn} Setzt ein Buch anhand seiner ISBN als ausgeliehen.

/set_book_attribute/{isbn}/{attr}/{val} Ändert ein bestimmtes (angegebenes Attribut) eines Buches

WIP 
(Work in Progress)
