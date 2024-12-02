package Parent;

import Parent.Parent;
import Parent.ParentRegistrationService;
import Parent.ParentLoginService;
import Parent.ParentServices;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ParentRegistrationService registrationService = new ParentRegistrationService();
        ParentLoginService loginService = new ParentLoginService();
        ParentServices parentServices = new ParentServices();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Main Menu
            System.out.println("=== Tuition Centre Management ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                // Registration
                System.out.println("=== Parent Registration ===");
                System.out.print("Enter your name: ");
                String name = scanner.nextLine();

                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                System.out.print("Enter your phone number: ");
                String phoneNumber = scanner.nextLine();

                Parent newParent = new Parent(name, email, password, phoneNumber);
                registrationService.registerParent(newParent);

            } else if (choice == 2) {
                // Login
                System.out.println("=== Parent Login ===");
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                // Authenticate
                boolean loginSuccessful = loginService.loginParent(email, password, ParentRegistrationService.registeredParents);

                if (loginSuccessful) {
                    // Show post-login menu
                    postLoginMenu(scanner, parentServices);
                }

            } else if (choice == 3) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void postLoginMenu(Scanner scanner, ParentServices parentServices) {
        while (true) {
            // Post-login Menu
            System.out.println("=== Welcome to Your Parent Dashboard ===");
            System.out.println("1. Pay Fees Online");
            System.out.println("2. View Class Timings");
            System.out.println("3. Track Attendance");
            System.out.println("4. Access Study Materials");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                parentServices.payFees();
            } else if (choice == 2) {
                parentServices.viewClassTimings();
            } else if (choice == 3) {
                parentServices.trackAttendance();
            } else if (choice == 4) {
                parentServices.accessStudyMaterials();
            } else if (choice == 5) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
