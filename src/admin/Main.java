/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package admin;

/**
 *
 * @author User
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();

        System.out.println("Welcome to the Tuition Center Management System (Admin Portal)");
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Admin");
            System.out.println("2. Admin Login");
            System.out.println("3. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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
}
