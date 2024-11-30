
package Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Course> availableCourses = new ArrayList<>();

    public static void main(String[] args) {
        displayLogo();
        initializeCourses();

        Scanner scanner = new Scanner(System.in);

        // Collect student information with error handling
        String name = getNonEmptyInput(scanner, "Enter your name: ");
        String phoneNumber = getNonEmptyInput(scanner, "Enter your phone number: ");
        int age = getValidAge(scanner);

        Student student = new Student(name, phoneNumber, age);

        // Select a single course
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

        // Display and save student details
        System.out.println("\nRegistration complete!");
        System.out.println(student.getDetails());
        FileHandler.saveStudentDetails(student);

        scanner.close();
    }

    private static void initializeCourses() {
        availableCourses.add(new Course("Software Development", "SD101"));
        availableCourses.add(new Course("Business", "BUS102"));
        availableCourses.add(new Course("Accounting", "ACC103"));
    }

    private static void displayAvailableCourses() {
        for (int i = 0; i < availableCourses.size(); i++) {
            Course course = availableCourses.get(i);
            System.out.println((i + 1) + ". " + course.getCourseName() + " (" + course.getCourseCode() + ")");
        }
    }

   private static void displayLogo() {
    System.out.println("**************************************************");
    System.out.println("*                                                *");
    System.out.println("*        TARUMT COURSE REGISTRATION FORM         *");
    System.out.println("*                                                *");
    System.out.println("**************************************************");
    System.out.println();
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

    private static int getValidAge(Scanner scanner) {
        int age = -1;
        while (age <= 0) {
            String input = getNonEmptyInput(scanner, "Enter your age: ");
            try {
                age = Integer.parseInt(input);
                if (age <= 0) {
                    System.out.println("Age must be a positive number. Please try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
        return age;
    }
}
