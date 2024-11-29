package admin;

import java.io.*;
import java.util.Scanner;

public class Admin {

    private static final String FILE_PATH = "admin.txt"; // File to store admin credentials

    // Method to handle admin registration
    public void registerAdmin(Scanner scanner) {
        System.out.print("Enter a new admin username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a new admin password: ");
        String password = scanner.nextLine();

        // Save the admin credentials to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + password);
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

        // Validate the admin credentials
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        System.out.println("Login successful! Welcome, Admin " + username + ".");
                        return;
                    }
                }
            }
            System.out.println("Invalid admin username or password. Please try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading admin information.");
        }
    }
}