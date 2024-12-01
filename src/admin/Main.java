package admin;

import java.util.Scanner;

/**
 * Main class for the Tuition Center Management System (Admin Portal)
 * Handles user interaction and navigation
 */
public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        // Display logo
        displayLogo();

        System.out.println("Welcome to the Tuition Center Management System (Admin Portal)");

        // Loop until exit
        while (true) {
            // Display the menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Admin");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");

            // Prompt the admin to enter an option
            System.out.print("Enter your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    admin.registerAdmin(scanner);
                    break;
                case 2:
                    admin.adminLogin(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the Admin System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display logo
    private static void displayLogo() {
        System.out.println("**************************************************");
        System.out.println("*                                                *");
        System.out.println("*      TUITION CENTER MANAGEMENT SYSTEM          *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println();
    }
}