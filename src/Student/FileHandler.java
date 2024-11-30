
package Student;

import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    public static void saveStudentDetails(Student student) {
        try (FileWriter writer = new FileWriter("student_registration.txt", true)) {
            writer.write(student.getDetails());
            writer.write("\n-----------------------------------------------\n");
            System.out.println("Student details saved to 'student_registration.txt'.");
        } catch (IOException e) {
            System.out.println("An error occurred while saving the student details: " + e.getMessage());
        }
    }
}

