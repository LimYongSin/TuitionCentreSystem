package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static List<Subject> availableSubjects = new ArrayList<>();
    private static List<Student> registeredStudents = FileHandler.loadStudentDetails();  // Load students from file

    public static void main(String[] args) {
        initializeSubjects();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayHomePage();
            int choice = getValidChoice(scanner, 1, 2);

            if (choice == 1) {
                registerSubject(scanner);
            } else if (choice == 2) {
                Login.login(registeredStudents, scanner);  // Login function
            } else {
                System.out.println("Invalid option. Exiting...");
                break;
            }
        }
        scanner.close();
    }

    private static void initializeSubjects() {
        availableSubjects.add(new Subject("Math", "M1"));
        availableSubjects.add(new Subject("Science", "S1"));
        availableSubjects.add(new Subject("Sejarah", "S2"));
    }

    private static void displayHomePage() {
        System.out.println("\n**************************************************");
        System.out.println("*                                                *");
        System.out.println("*            TARUMT SUBJECT SYSTEM              *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println("1. Register for Subject");
        System.out.println("2. Login");
        System.out.print("Enter your choice: ");
    }

    private static void registerSubject(Scanner scanner) {
        System.out.println("Choose the subject to register: ");
        for (int i = 0; i < availableSubjects.size(); i++) {
            System.out.println((i + 1) + ". " + availableSubjects.get(i).getSubjectName());
        }
    
        int subjectChoice = getValidChoice(scanner, 1, availableSubjects.size());
        Subject selectedSubject = availableSubjects.get(subjectChoice - 1);
    
        // Register the student with the selected subject
        String name = getNonEmptyInput(scanner, "Enter your name: ");
        String phoneNumber = getValidPhoneNumber(scanner);
        String email = getValidUniqueEmail(scanner); // Ensure the email is unique
        String studentId = "STU" + new Random().nextInt(1000);
        String password = getValidPassword(scanner);
    
        Student newStudent = new Student(name, phoneNumber, email, studentId, password);
        newStudent.setRegisteredSubject(selectedSubject);
    
        registeredStudents.add(newStudent); // Add to the in-memory list
    
        // Save all students to the file
        FileHandler.saveAllStudents(registeredStudents);
    
        System.out.println("Registration successful!");
        System.out.println("Your Student ID: " + studentId);
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

private static String getValidPassword(Scanner scanner) {
    while (true) {
        String password = getNonEmptyInput(scanner, "Enter your new password (must be at least 7 characters and contain both letters and numbers): ");
        if (password.length() < 7) {
            System.out.println("Password must be at least 7 characters.");
        } else if (!password.matches(".*[a-zA-Z].*")) {
            System.out.println("Password must contain at least one letter.");
        } else if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one number.");
        } else {
            return password; // Valid password
        }
        
    }
}

private static String getValidUniqueEmail(Scanner scanner) {
    while (true) {
        String email = getValidEmail(scanner); // Get email with basic validation
        if (!isEmailExists(email)) {
            return email; // Email is unique, return it
        } else {
            System.out.println("This email is already registered. Please enter a different email.");
        }
    }
}

private static boolean isEmailExists(String email) {
    for (Student student : registeredStudents) {
        if (student.getEmail().equalsIgnoreCase(email)) {
            return true; // Email already exists
        }
    }
    return false; // Email is unique
}

public static void refreshRegisteredStudents() {
    registeredStudents = FileHandler.loadStudentDetails(); // Reload data from the file
}

}
