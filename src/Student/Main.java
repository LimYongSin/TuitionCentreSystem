package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Main {

    private static List<Course> availableCourses = new ArrayList<>();
    private static List<Student> registeredStudents = new ArrayList<>();

    public static void main(String[] args) {
        initializeCourses();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            displayHomePage();
            int choice = getValidChoice(scanner, 1, 2);

            if (choice == 1) {
                registerCourse(scanner);
            } else if (choice == 2) {
                login(registeredStudents, scanner);
            } else {
                System.out.println("Invalid option. Exiting...");
                break;
            }
        }
        scanner.close();
    }

    private static void initializeCourses() {
        availableCourses.add(new Course("Software Development", "SD101"));
        availableCourses.add(new Course("Business", "BUS102"));
        availableCourses.add(new Course("Accounting", "ACC103"));
    }

    private static void displayHomePage() {
        System.out.println("\n**************************************************");
        System.out.println("*                                                *");
        System.out.println("*            TARUMT COURSE SYSTEM               *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println("1. Register for a Course");
        System.out.println("2. Login to Tuition Center");
        System.out.println("**************************************************");
        System.out.print("Enter your choice: ");
    }

    private static void registerCourse(Scanner scanner) {
        System.out.println("\n** Course Registration **");
        String name = getNonEmptyInput(scanner, "Enter your name: ");
        String phoneNumber = getValidPhoneNumber(scanner);
        String email = getValidEmail(scanner);
        String password = getValidPassword(scanner);

        // Generate short student ID
        String studentId = generateStudentId();
        System.out.println("Your Student ID: " + studentId);

        Student student = new Student(name, phoneNumber, email, studentId, password);

        System.out.println();
        // Select a course
        System.out.println("Select a course to register:");
        displayAvailableCourses();

        int courseChoice = -1;
        while (courseChoice < 1 || courseChoice > availableCourses.size()) {
            System.out.print("Enter the course number: ");
            try {
                courseChoice = Integer.parseInt(scanner.nextLine().trim());
                if (courseChoice >= 1 && courseChoice <= availableCourses.size()) {
                    Course selectedCourse = availableCourses.get(courseChoice - 1);
                    student.registerCourse(selectedCourse);
                } else {
                    System.out.println("Invalid course number. Please try again.");
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

    public static void login(List<Student> registeredStudents, Scanner scanner) {
        System.out.println("\nLogin to the Tuition Center");
        String studentId = getNonEmptyInput(scanner, "Enter your Student ID: ").toLowerCase();
        String password = getNonEmptyInput(scanner, "Enter your password: ");

        boolean isLoggedIn = false;

        for (Student student : registeredStudents) {
            if (student.getStudentId().toLowerCase().equals(studentId) && student.getPassword().equals(password)) {
                System.out.println("Login successful! Welcome, " + student.getName());
                isLoggedIn = true;
                accessTuitionCenter(student);
                break;
            }
        }

        if (!isLoggedIn) {
            System.out.println("Invalid Student ID or Password. Please try again.");
        }
    }

    private static void accessTuitionCenter(Student student) {
        System.out.println("\nTuition Center Dashboard");
        System.out.println("Your Enrolled Course:");
        System.out.println(student.getRegisteredCourse().getCourseName() + " (" + student.getRegisteredCourse().getCourseCode() + ")");
        System.out.println("\nEnjoy your studies at TARUMT!");
    }

    private static void displayAvailableCourses() {
        for (int i = 0; i < availableCourses.size(); i++) {
            Course course = availableCourses.get(i);
            System.out.println((i + 1) + ". " + course.getCourseName() + " (" + course.getCourseCode() + ")");
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
