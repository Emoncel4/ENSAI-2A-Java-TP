package fr.ensai.library;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Library library = new Library("Bibliothèque municipale", new ArrayList<>());
        
        // Charger les livres à partir d'un fichier CSV
        library.loadBooksFromCSV("books.csv");

        // Afficher les livres chargés
        System.out.println(library.displayBooks());
    }
}
