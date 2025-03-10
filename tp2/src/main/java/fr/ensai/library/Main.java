package fr.ensai.library;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Bibliothèque municipale", new ArrayList<>());
        
        // Charger les livres à partir d'un fichier CSV
        library.loadBooksFromCSV("books.csv");

        Magazine Magazine1 = new Magazine("1515", "12", "mon premier j'aime lire", 2003, 25);
        Magazine Magazine2 = new Magazine("1516", "13", "mon deuxième j'aime lire", 2003, 25);

        library.addItem(Magazine1);
        library.addItem(Magazine2);
        // Afficher les livres chargés
        System.out.println(library.displayItems());
    }
}
