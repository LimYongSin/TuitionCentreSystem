package Student;

import java.util.*;

public class UpdateInfo {

    public static void updateStudentDetails(Student student, Scanner scanner) {
        System.out.println("\n** Update Your Personal Information **");
        System.out.println("1. Update Name");
        System.out.println("2. Update Phone Number");
        System.out.println("3. Update Email");
        System.out.println("4. Update Password");
        System.out.println("5. Back to Dashboard");

        int choice = getValidChoice(scanner, 1, 5);
        boolean isUpdated = false;

        switch (choice) {
            case 1:
                String newName = getNonEmptyInput(scanner, "Enter your new name: ");
                student.setName(newName);
                System.out.println("Name updated successfully!");
                isUpdated = true;
                break;
            case 2:
                String newPhoneNumber = getValidPhoneNumber(scanner);
                student.setPhoneNumber(newPhoneNumber);
                System.out.println("Phone number updated successfully!");
                isUpdated = true;
                break;
            case 3:
                String newEmail = getValidEmail(scanner);
                student.setEmail(newEmail);
                System.out.println("Email updated successfully!");
                isUpdated = true;
                break;
            case 4:
                updatePassword(student, scanner);
                return;
            case 5:
                return;
        }

        if (isUpdated) {
            FileHandler.saveStudentDetails(student);
            System.out.println("\nUpdated Details:");
            System.out.println(student.getDetails());
        }
    }

    private static void updatePassword(Student student, Scanner scanner) {
        List<Student> registeredStudents = FileHandler.loadStudentDetails();
    
        String email = getNonEmptyInput(scanner, "Enter your email to verify: ");
        String studentId = getNonEmptyInput(scanner, "Enter your Student ID to verify: ");
    
        for (Student registeredStudent : registeredStudents) {
            // Use equalsIgnoreCase for both email and student ID comparison
            if (registeredStudent.getEmail().equalsIgnoreCase(email) && 
                registeredStudent.getStudentId().equalsIgnoreCase(studentId)) {
                String newPassword = getValidPassword(scanner);
                registeredStudent.setPassword(newPassword);
                FileHandler.saveAllStudents(registeredStudents);
                System.out.println("Password updated successfully!");
                return;
            }
        }
        System.out.println("Verification failed. Email or Student ID is incorrect.");
    }
    

    private static String getNonEmptyInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("Input cannot be empty.");
        }
    }

    private static String getValidPhoneNumber(Scanner scanner) {
        while (true) {
            String phoneNumber = getNonEmptyInput(scanner, "Enter your new phone number: ");
            if (phoneNumber.matches("\\d{8,}")) return phoneNumber;
            System.out.println("Invalid phone number. It must be at least 8 digits.");
        }
    }

    private static String getValidEmail(Scanner scanner) {
        while (true) {
            String email = getNonEmptyInput(scanner, "Enter your new email: ");
            if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.com$")) return email;
            System.out.println("Invalid email format.");
        }
    }

    private static String getValidPassword(Scanner scanner) {
        while (true) {
            String password = getNonEmptyInput(scanner, "Enter your new password (5-10 characters, alphanumeric): ");
            if (password.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{5,10}$")) return password;
            System.out.println("Invalid password format.");
        }
    }

    private static int getValidChoice(Scanner scanner, int min, int max) {
        while (true) {
            try {
                int choice = Integer.parseInt(scanner.nextLine().trim());
                if (choice >= min && choice <= max) return choice;
            } catch (NumberFormatException ignored) {}
            System.out.println("Invalid input. Enter a number between " + min + " and " + max + ".");
        }
    }
}
