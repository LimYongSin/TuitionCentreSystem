package Parent;

import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        ParentRegistrationService registrationService = new ParentRegistrationService();
        ParentLoginService loginService = new ParentLoginService();
        ParentServices parentServices = new ParentServices();
        TuitionFee tuitionFee = new TuitionFee();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Main Menu
            System.out.println("=== Tuition Centre Management ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int choice = getValidInteger(scanner, "Invalid input. Please enter a number.");

            if (choice == 1) {
                registerParent(scanner, registrationService);
            } else if (choice == 2) {
                loginParent(scanner, loginService, parentServices, tuitionFee);
            } else if (choice == 3) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void registerParent(Scanner scanner, ParentRegistrationService registrationService) {
        System.out.println("=== Parent Registration ===");

        String name = getNonEmptyInput(scanner, "Enter your name: ", "Name cannot be empty. Please try again.");

        String email = getNonEmptyInput(scanner, "Enter your email: ", "Email cannot be empty. Please try again.");
        while (!isValidEmail(email)) {
            System.out.println("Invalid email format. Please try again.");
            email = getNonEmptyInput(scanner, "Enter your email: ", "Email cannot be empty. Please try again.");
        }

        String password = getNonEmptyInput(scanner, "Enter your password: ", "Password cannot be empty. Please try again.");

        String phoneNumber = getNonEmptyInput(scanner, "Enter your phone number: ", "Phone number cannot be empty. Please try again.");
        while (!isValidPhoneNumber(phoneNumber)) {
            System.out.println("Invalid phone number format. Please enter a 10-digit number.");
            phoneNumber = getNonEmptyInput(scanner, "Enter your phone number: ", "Phone number cannot be empty. Please try again.");
        }

        Parent newParent = new Parent(name, email, password, phoneNumber);
        registrationService.registerParent(newParent);
    }

    private static void loginParent(Scanner scanner, ParentLoginService loginService, ParentServices parentServices, TuitionFee tuitionFee) {
        System.out.println("=== Parent Login ===");

        String email = getNonEmptyInput(scanner, "Enter your email: ", "Email cannot be empty. Please try again.");
        String password = getNonEmptyInput(scanner, "Enter your password: ", "Password cannot be empty. Please try again.");

        if (loginService.loginParent(email, password, ParentRegistrationService.registeredParents)) {
            postLoginMenu(scanner, parentServices, tuitionFee);
        } else {
            System.out.println("Login failed. Please check your email and password.");
        }
    }

    private static void postLoginMenu(Scanner scanner, ParentServices parentServices, TuitionFee tuitionFee) {
        while (true) {
            System.out.println("=== Parent Dashboard ===");
            System.out.println("1. Pay Fees Online");
            System.out.println("2. View Tuition Fee");
            System.out.println("3. Track Attendance");
            System.out.println("4. Access Study Materials");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");

            int choice = getValidInteger(scanner, "Invalid input. Please enter a number.");

            if (choice == 1) {
                parentServices.payFees();
            } else if (choice == 2) {
                tuitionFee.displayTuitionFee();
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

    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.matches(emailRegex, email);
    }

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches("\\d{10}");
    }

    private static String getNonEmptyInput(Scanner scanner, String prompt, String errorMessage) {
        String input;
        while (true) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (!input.isEmpty()) {
                break;
            }
            System.out.println(errorMessage);
        }
        return input;
    }

    private static int getValidInteger(Scanner scanner, String errorMessage) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }
}
