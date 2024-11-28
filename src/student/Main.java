package student;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // Create a list of courses
        ArrayList<Course> courses = new ArrayList<>();
        courses.add(new Course("Software Development", "CS101", 5));
        courses.add(new Course("Business", "CS102", 3));
        courses.add(new Course("Accounting", "CS103", 2));

        // Get student details
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String studentName = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Enter your IC number: ");
        String icNumber = scanner.nextLine();

        Student student = new Student(studentName, phoneNumber, icNumber);

        // Prompt student to select a course with retry logic
        int choice = -1;
        while (true) {
            System.out.print("\nEnter the number of the course you want to register for: ");
            // Display available courses
            System.out.println("\nAvailable Courses:");
            for (int i = 0; i < courses.size(); i++) {
                System.out.println((i + 1) + ". " + courses.get(i));
            }
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                if (choice >= 1 && choice <= courses.size()) {
                    break; // Valid choice, exit the loop
                }
            } else {
                scanner.next(); // Consume invalid input
            }
            System.out.println("Invalid choice. Please select a valid course number.");
        }

        // Register for the selected course
        Course selectedCourse = courses.get(choice - 1);
        student.registerForCourse(selectedCourse);

        // Save registration details to a file
        saveRegistration(student, selectedCourse);

        // Display student details
System.out.println("\nStudent Details:");
System.out.println("Name: " + student.getStudentName());
System.out.println("Phone Number: " + student.getPhoneNumber());
System.out.println("IC Number: " + student.getIcNumber());
System.out.println("You have registered course: " + selectedCourse.getCourseName() + " (" + selectedCourse.getCourseCode() + ")");


        scanner.close();
    }

    private static void saveRegistration(Student student, Course course) {
        String fileName = "registrations.txt";
        try (FileWriter writer = new FileWriter(fileName, true)) {
            writer.write("Student Name: " + student.getStudentName() + "\n");
            writer.write("Phone Number: " + student.getPhoneNumber() + "\n");
            writer.write("IC Number: " + student.getIcNumber() + "\n");
            writer.write("Registered Course: " + course.getCourseName() + " (" + course.getCourseCode() + ")\n");
            writer.write("-----------------------\n");
            System.out.println("\nRegistration details saved to " + fileName);
        } catch (IOException e) {
            System.out.println("Error saving registration details: " + e.getMessage());
        }
    }
}
