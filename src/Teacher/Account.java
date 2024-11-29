package Teacher;
import java.io.*;
import java.util.Scanner;

public class Account {

   private static final String FILE_PATH = "account.txt"; // File to store credentials

   public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================");
        System.out.println("Welcome to the Tuition Center Management System");
        System.out.println("===============================================");
        while (true) {
            System.out.println("Wish to access the system?");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("___________________________________________");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle user registration
    private static void register(Scanner scanner) {
        System.out.print("\nEnter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        // Save the credentials to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + password);
            writer.newLine();
            System.out.println("Registration successful!");
            System.out.println("___________________________________________");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user information.");
        }
    }

    // Method to handle user login
    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate the credentials
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        System.out.println("Login successful! Welcome, " + username + ".");
                        System.out.println("___________________________________________");
                        accessFeatures(scanner); // Call feature menu after successful login
                        return;
                    }
                }
            }
            System.out.println("Invalid username or password. Please try again.");
        } catch (IOException e) {
            System.out.println("An error occurred while reading user information.");
        }
    }

    // Method to display and access system features
    private static void accessFeatures(Scanner scanner) {
        while (true) {
            System.out.println("\nTeachers' System:");
            System.out.println("1. View Class Schedule");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Record Student Performance");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Feature: View Class Schedule (To be implemented)");
                    break;
                case 2:
                    System.out.println("Feature: Mark Attendance (To be implemented)");
                    break;
                case 3:
                    System.out.println("Feature: Record Student Performance (To be implemented)");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
