package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static List<Subject> availableSubjects = new ArrayList<>();
    private static List<Student> registeredStudents = new ArrayList<>();

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
        System.out.println("1. Register for a Subject");
        System.out.println("2. Login to Tuition Center");
        System.out.println("**************************************************");
        System.out.print("Enter your choice: ");
    }

    private static void registerSubject(Scanner scanner) {
        System.out.println("\n** Subject Registration **");
        String name = getNonEmptyInput(scanner, "Enter your name: ");
        String phoneNumber = getValidPhoneNumber(scanner);
        String email = getValidEmail(scanner);
        String password = getValidPassword(scanner);

        // Generate short student ID
        String studentId = generateStudentId();
        System.out.println("Your Student ID: " + studentId);

        Student student = new Student(name, phoneNumber, email, studentId, password);

        System.out.println();
        // Select a subject
        System.out.println("Select a subject to register:");
        displayAvailableSubjects();

        int subjectChoice = -1;
        while (subjectChoice < 1 || subjectChoice > availableSubjects.size()) {
            System.out.print("Enter the subject number: ");
            try {
                subjectChoice = Integer.parseInt(scanner.nextLine().trim());
                if (subjectChoice >= 1 && subjectChoice <= availableSubjects.size()) {
                    Subject selectedSubject = availableSubjects.get(subjectChoice - 1);
                    student.registerSubject(selectedSubject);
                } else {
                    System.out.println("Invalid subject number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }

        // Store registered student
        registeredStudents.add(student);

        // Display student details, including ID
        System.out.println("\nRegistration complete!");
        System.out.println(student.getDetails());
        System.out.println("Student ID: " + student.getStudentId());

        // Save student details to file
        FileHandler.saveStudentDetails(student);

        System.out.println("\nReturning to home page...");
    }

    private static String generateStudentId() {
        Random random = new Random();
        int randomNumber = 100 + random.nextInt(900);
        return "STU" + randomNumber;
    }

    private static void displayAvailableSubjects() {
        for (int i = 0; i < availableSubjects.size(); i++) {
            Subject subject = availableSubjects.get(i);
            System.out.println((i + 1) + ". " + subject.getSubjectName() + " (" + subject.getSubjectCode() + ")");
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

    private static String getValidPhoneNumber(Scanner scanner) {
        while (true) {
            String phoneNumber = getNonEmptyInput(scanner, "Enter your phone number: ");
            if (phoneNumber.matches("\\d{8,}")) {
                return phoneNumber;
            } else {
                System.out.println("Invalid phone number. It must be at least 8 digits and contain only numeric numbers.");
            }
        }
    }

    private static String getValidEmail(Scanner scanner) {
        while (true) {
            String email = getNonEmptyInput(scanner, "Enter your email: ");
            if (email.matches("^[\\w.%+-]+@[\\w.-]+\\.com$")) {
                return email;
            } else {
                System.out.println("Invalid email format. Please enter a valid email (e.g., example@gmail.com).");
            }
        }
    }

    private static String getValidPassword(Scanner scanner) {
        while (true) {
            String password = getNonEmptyInput(scanner, "Set a password (5-10 characters, alphanumeric): ");
            if (password.matches("^(?=.*[a-zA-Z])(?=.*\\d)[a-zA-Z\\d]{5,10}$")) {
                return password;
            } else {
                System.out.println("Invalid password. It must be 5-10 characters long and contain both letters and numbers.");
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
