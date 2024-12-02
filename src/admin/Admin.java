package admin;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

/**
 * Admin class handles admin operations like registration, login, and authentication.
 */
public class Admin {

    private static final String FILE_PATH = "admin.txt"; // File to store admin credentials

    // Method to handle admin registration
    public void registerAdmin(Scanner scanner) {
        System.out.print("Enter a new admin username: ");
        String username = scanner.nextLine();

        // Check if username already exists
        if (isUsernameTaken(username)) {
            System.out.println("Username is already taken. Please choose another.");
            return;
        }

        System.out.print("Enter a new admin password: ");
        String password = scanner.nextLine();

        // Hash the password before saving it
        String hashedPassword = hashPassword(password);

        // Save the admin credentials to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + hashedPassword);
            writer.newLine();
            System.out.println("Admin registration successful!");
        } catch (IOException e) {
            System.out.println("An error occurred while saving admin information.");
        }
    }

    // Method to handle admin login
    public void adminLogin(Scanner scanner) {
        System.out.print("Enter admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter admin password: ");
        String password = scanner.nextLine();

        // Hash the entered password to compare with stored hash
        String hashedPassword = hashPassword(password);

        // Validate the admin credentials
        if (validateLogin(username, hashedPassword)) {
            System.out.println("Login successful! Welcome, Admin " + username + ".");
            showAdminMenu(scanner);  // Show the admin menu after successful login
        } else {
            System.out.println("Invalid admin username or password. Please try again.");
        }
    }

    // Method to display admin tasks after login
    private void showAdminMenu(Scanner scanner) {
        while (true) {
            // Admin menu options
            System.out.println("\nAdmin Dashboard:");
            System.out.println("1. Manage Fee Structure");
            System.out.println("2. Manage Class Schedule");
            System.out.println("3. Manage Teachers");
            System.out.println("4. Logout");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    System.out.println("Managing Fee Structure...");
                    // Add logic for managing fees here
                    break;
                case 2:
                    System.out.println("Managing Class Schedule...");
                    // Add logic for managing schedule here
                    break;
                case 3:
                    System.out.println("Managing Teachers...");
                    // Add logic for managing teachers here
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;  // Exit to login menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to check if the username is already taken
    private boolean isUsernameTaken(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true; // Username found
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading admin information.");
        }
        return false; // Username not found
    }

    // Method to hash the password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashedBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString(); // Return hashed password as a hex string
        } catch (NoSuchAlgorithmException e) {
            System.out.println("Error while hashing password.");
            return null;
        }
    }

    // Method to validate the login credentials
    private boolean validateLogin(String username, String hashedPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(hashedPassword)) {
                    return true; // Valid login credentials
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while reading admin information.");
        }
        return false; // Invalid credentials
    }
}