import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Login {
    public static void main(String[] args) {
        HashMap<String, String> userDatabase = loadUserDatabase("../data/user_hashpwd.csv");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Entrez votre nom d'utilisateur: ");
            String username = scanner.nextLine().trim();

            // Vérifier si l'utilisateur existe
            if (userDatabase.containsKey(username)) {
                int attempts = 0;

                // Permettre à l'utilisateur de tenter de se connecter avec son mot de passe
                // jusqu'à 3 fois
                while (attempts < 3) {
                    System.out.print("Entrez votre mot de passe: ");
                    String password = scanner.nextLine().trim();

                    // Vérifier si le mot de passe est correct
                    if (checkPassword(password, userDatabase.get(username))) {
                        System.out.println("Login successful!");
                        return; // Connexion réussie, sortie du programme
                    } else {
                        System.out.println("Mot de passe incorrect. Essayez à nouveau.");
                        attempts++;
                    }
                }

                System.out.println("Trop de tentatives échouées. Veuillez entrer un nom d'utilisateur.");
            } else {
                System.out.println("Nom d'utilisateur non trouvé. Essayez à nouveau.");
            }
        }
    }

    /**
     * Vérifie si le mot de passe entré correspond au mot de passe haché dans la
     * base de données.
     * 
     * @param enteredPassword Le mot de passe entré par l'utilisateur.
     * @param storedHash      Le mot de passe haché stocké dans la base de données.
     * @return true si les mots de passe correspondent, false sinon.
     */
    public static boolean checkPassword(String enteredPassword, String storedHash) {
        // Hacher le mot de passe entré et comparer avec le hachage stocké
        String hashedEnteredPassword = hashPassword(enteredPassword);
        return hashedEnteredPassword.equals(storedHash);
    }

    }

    /**
     * Loads a user database from a CSV file.
     * The CSV file is expected to have two columns: username and hashed password.
     * 
     * @param filename The path to the CSV file containing user data.
     * @return A HashMap where keys are usernames and values are hashed passwords.
     * 
     * @throws IOException If an error occurs while reading the file.
     */
    public static HashMap<String, String> loadUserDatabase(String filename) {
        HashMap<String, String> userDatabase = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    userDatabase.put(parts[0].trim(), parts[1].trim());
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
        return userDatabase;
    }
}
