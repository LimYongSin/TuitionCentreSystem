package Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {

    public static void saveStudentDetails(Student student) {
        String fileName = "student_registration.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write("Name: " + student.getName());
            writer.newLine();
            writer.write("Phone Number: " + student.getPhoneNumber());
            writer.newLine();
            writer.write("Email: " + student.getEmail());
            writer.newLine();
            writer.write("Student ID: " + student.getStudentId());
            writer.newLine();
            writer.write("Registered Course: " + 
                         (student.getRegisteredCourse() != null 
                         ? student.getRegisteredCourse().getCourseName() + 
                           " (" + student.getRegisteredCourse().getCourseCode() + ")" 
                         : "None"));
            writer.newLine();
            writer.write("----------------------------------");
            writer.newLine();
            System.out.println("Student details saved to '" + fileName + "'.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving student details: " + e.getMessage());
        }
    }
}
