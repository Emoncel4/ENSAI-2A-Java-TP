import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Password {
    /**
     * Hashes the provided password using the SHA-256 algorithm.
     * 
     * @param password the password to be hashed
     * @return a hexadecimal string representing the hashed password
     * @throws RuntimeException if the SHA-256 algorithm is not available
     */
    public static String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());

            StringBuilder hexString = new StringBuilder();

            for (byte b : hashedBytes) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1)
                    hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    /**
     * Attempts a brute-force attack to find the 6-digit number
     * 
     * @param targetHash the target hash to match
     * @return the 6-digit number that matches, or null if no match is found
     */
    public static String bruteForce6Digit(String targetHash) {
        String nombre_a_tester = null;
        for (int i = 0; i < 1000000; i++) {
            nombre_a_tester = String.format("%06d", i);
            if (hashPassword(nombre_a_tester).equals(targetHash)) {
                return nombre_a_tester;
            }
        }
        return null;
    }

    /**
     * Checks if the given password is strong according to the following criteria:
     * 
     * <ul>
     * <li>Minimum length of 12 characters</li>
     * <li>At least one uppercase letter</li>
     * <li>At least one lowercase letter</li>
     * <li>At least one digit</li>
     * <li>No whitespace characters</li>
     * </ul>
     * 
     * @param password the password to check
     * @return true if the password is strong, false otherwise
     */
    public class Main {
        public static boolean isStrongPassword(String password) {
            if (password.length() < 12) {
                return false; // Un mot de passe fort doit avoir au moins 12 caractères
            }

            boolean hasUpperCase = false;
            boolean hasDigit = false;
            boolean hasWhitespace = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (Character.isUpperCase(c)) {
                    hasUpperCase = true;
                }
                if (Character.isDigit(c)) {
                    hasDigit = true;
                }
                if (Character.isWhitespace(c)) {
                    hasWhitespace = true;
                }
            }

            // Un mot de passe est fort s'il contient au moins une majuscule, un chiffre, et
            // pas d'espace
            return hasUpperCase && hasDigit && !hasWhitespace;
        }

        /**
         * Checks the strength of multiple passwords and stores the results in a
         * HashMap.
         *
         * @param passwords An ArrayList of passwords to be checked.
         * @return A HashMap where each password is mapped to a Boolean value:
         *         true if the password is strong, false otherwise
         */
        public static HashMap<String, Boolean> checkPasswordsList(ArrayList<String> passwords) {
            HashMap<String, Boolean> liste_securite = new HashMap<>();

            for (String password : passwords) {
                liste_securite.put(password, isStrongPassword(password));
            }

            return liste_securite;
        }

        /**
         * Generates a secure random password with at least:
         * <ul>
         * <li>1 uppercase letter</li>
         * <li>1 lowercase letter</li>
         * <li>1 digit</li>
         * <li>1 special character</li>
         * </ul>
         * 
         * @param nbCar The desired length of the password (minimum 4).
         * @return A randomly generated password that meets the security criteria.
         */

        public static class LongueurException extends RuntimeException {
            public LongueurException(String message) {
                super(message);
            }
        }

        public static String generatePassword(int nbCar) {
            if (nbCar < 4)
                throw new LongueurException("Le mot de passe doit contenir au moins 4 caractères");

            List<Character> liste_charactere = new ArrayList<>();
            Random rand = new Random();
            char random_minuscule = (char) ('a' + rand.nextInt(26));
            liste_charactere.add(random_minuscule);
            char random_majuscule = (char) ('A' + rand.nextInt(26));
            liste_charactere.add(random_majuscule);
            char random_chiffre = (char) ('0' + rand.nextInt(10));
            liste_charactere.add(random_chiffre);
            char random_special = (char) (33 + rand.nextInt(94));
            liste_charactere.add(random_special);

            String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_=+[]{}|;:'\",.<>?/~";
            for (int i = 4; i < nbCar; i++) {
                liste_charactere.add(allChars.charAt(rand.nextInt(allChars.length())));
            }

            Collections.shuffle(liste_charactere);

            StringBuilder mot_de_passe = new StringBuilder();

            for (char c : liste_charactere) {
                mot_de_passe.append(c);
            }

            return mot_de_passe.toString();
        }

        public static void main(String[] args) {
            if (args.length == 0) {
                // No arguments provided, running all questions
                for (int i = 1; i <= 4; i++)
                    runQuestion(String.valueOf(i));
            } else {
                for (String arg : args) {
                    runQuestion(arg);
                }
            }
        }

        private static void runQuestion(String questionNumber) {

            System.out.println("\nQ" + questionNumber + "\n" + "-".repeat(20));
            switch (questionNumber) {
                case "1":
                    String HashedPwd = "a97755204f392b4d8787b38d898671839b4a770a864e52862055cdbdf5bc5bee";
                    String bruteForcedPwd = bruteForce6Digit(HashedPwd);
                    System.out.println(bruteForcedPwd != null ? bruteForcedPwd : "No result found");
                    break;

                case "2":
                    System.out.println("Abc5          -> " + isStrongPassword("1234"));
                    System.out.println("abcdef123456  -> " + isStrongPassword("abcdef123456"));
                    System.out.println("AbCdEf123456  -> " + isStrongPassword("AbCdEf123456"));
                    System.out.println("AbCdEf 123456 -> " + isStrongPassword("AbCdEf 123456"));
                    break;

                case "3":
                    ArrayList<String> passwords = new ArrayList<>(
                            List.of("Abc5", "abcdef123456", "AbCdEf123456", "AbCdEf 123456"));
                    HashMap<String, Boolean> password_map = checkPasswordsList(passwords);

                    if (password_map != null) {
                        for (Map.Entry<String, Boolean> entry : password_map.entrySet()) {
                            System.out.println(entry.getKey() + " -> " + entry.getValue());
                        }
                    }
                    break;

                case "4":
                    System.out.println("Generated password: " + generatePassword(12));
                    break;

                default:
                    System.out.println("Invalid question number: " + questionNumber);
            }
        }

    }
}
