package Student;

import java.util.List;
import java.util.Scanner;

public class Login {

    public static void login(List<Student> registeredStudents, Scanner scanner) {
        System.out.println("\nLogin to the Tuition Center");
        String studentId = getNonEmptyInput(scanner, "Enter your Student ID: ").toLowerCase();
        String password = getNonEmptyInput(scanner, "Enter your password: ");

        boolean isLoggedIn = false;

        for (Student student : registeredStudents) {
            if (student.getStudentId().toLowerCase().equals(studentId) && student.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + student.getName());
                isLoggedIn = true;
                accessTuitionCenter(student, scanner);
                break;
            }
        }

        if (!isLoggedIn) {
            System.out.println("Invalid Student ID or Password. Please try again.");
        }
    }

    private static void accessTuitionCenter(Student student, Scanner scanner) {
        System.out.println("\nTuition Center Dashboard");

        // Dashboard menu
        while (true) {
            System.out.println("\n1. View Your Details");
            System.out.println("2. Update Your Personal Information");
            System.out.println("3. Log Out");

            int choice = getValidChoice(scanner, 1, 3);

            if (choice == 1) {
                System.out.println("\nYour Details:");
                System.out.println(student.getDetails());
            } else if (choice == 2) {
                UpdateInfo.updateStudentDetails(student, scanner); 
            } else if (choice == 3) {
                System.out.println("Logging out...");
                break; 
            }
        }
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
