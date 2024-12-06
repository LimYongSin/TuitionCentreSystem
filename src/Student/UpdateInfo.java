package Student;

import java.util.Scanner;

public class UpdateInfo {

    public static void updateStudentDetails(Student student, Scanner scanner) {
        System.out.println("\n** Update Your Personal Information **");
        
        System.out.println("1. Update Name");
        System.out.println("2. Update Phone Number");
        System.out.println("3. Update Email");
        System.out.println("4. Back to Dashboard");

        int choice = getValidChoice(scanner, 1, 4);

        switch (choice) {
            case 1:
                String newName = getNonEmptyInput(scanner, "Enter your new name: ");
                student.setName(newName);  // Update the name
                System.out.println("Name updated successfully!");
                break;
            case 2:
                String newPhoneNumber = getValidPhoneNumber(scanner);
                student.setPhoneNumber(newPhoneNumber);  // Update the phone number
                System.out.println("Phone number updated successfully!");
                break;
            case 3:
                String newEmail = getValidEmail(scanner);
                student.setEmail(newEmail);  // Update the email
                System.out.println("Email updated successfully!");
                break;
            case 4:
                return;  // Return to the dashboard
            default:
                System.out.println("Invalid choice.");
        }
        
        // Optionally, you could save the updated details to the file here if required
        // FileHandler.saveStudentDetails(student);

        // After update, show the updated details
        System.out.println("\nUpdated Details:");
        System.out.println(student.getDetails());
    }

    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        String input = "";
        while (input.isEmpty()) {
            System.out.print(prompt);
            input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please try again.");
            }
        }
        return input;
    }

    private static String getValidPhoneNumber(Scanner scanner) {
        while (true) {
            String phoneNumber = getNonEmptyInput(scanner, "Enter your new phone number: ");
            if (phoneNumber.matches("\\d{8,}")) {
                return phoneNumber;
            } else {
                System.out.println("Invalid phone number. It must be at least 8 digits and contain only numeric numbers.");
            }
        }
    }

    private static String getValidEmail(Scanner scanner) {
        while (true) {
            String email = getNonEmptyInput(scanner, "Enter your new email: ");
            if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.com$")) {
                return email;
            } else {
                System.out.println("Invalid email format. Please enter a valid email (e.g., example@gmail.com).");
            }
        }
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        int choice = -1;
        while (choice < min || choice > max) {
            try {
                choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice < min || choice > max) {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return choice;
    }
}
