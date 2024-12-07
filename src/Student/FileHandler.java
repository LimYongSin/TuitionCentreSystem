package Student;

import java.io.*;
import java.util.*;

public class FileHandler {
    // register
    public static void saveAllStudents(List<Student> students) {
        String fileName = "student_registration.txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write("Name: " + student.getName());
                writer.newLine();
                writer.write("Phone Number: " + student.getPhoneNumber());
                writer.newLine();
                writer.write("Email: " + student.getEmail());
                writer.newLine();
                writer.write("Student ID: " + student.getStudentId());
                writer.newLine();
                writer.write("Password: " + student.getPassword());
                writer.newLine();
                writer.write("Registered Subject: " +
                        (student.getRegisteredSubject() != null ? student.getRegisteredSubject().getSubjectName() +
                                " (" + student.getRegisteredSubject().getSubjectCode() + ")"
                                : "None"));
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }

        } catch (IOException e) {
            System.out.println("Error saving all students: " + e.getMessage());
        }
    }

    public static List<Student> loadStudentDetails() {
        List<Student> students = new ArrayList<>();
        String fileName = "student_registration.txt";

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            Student student = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("Name: ")) {
                    if (student != null) {
                        students.add(student);
                    }
                    student = new Student();
                    student.setName(line.substring(6).trim());
                } else if (line.startsWith("Phone Number: ")) {
                    student.setPhoneNumber(line.substring(14).trim());
                } else if (line.startsWith("Email: ")) {
                    student.setEmail(line.substring(7).trim());
                } else if (line.startsWith("Student ID: ")) {
                    student.setStudentId(line.substring(12).trim());
                } else if (line.startsWith("Password: ")) {
                    student.setPassword(line.substring(10).trim());
                } else if (line.startsWith("Registered Subject: ")) {
                    String subjectInfo = line.substring(20).trim();
                    if (!subjectInfo.equals("None")) {
                        String[] parts = subjectInfo.split(" \\(");
                        String subjectName = parts[0];
                        String subjectCode = parts[1].replace(")", "");
                        student.setRegisteredSubject(new Subject(subjectName, subjectCode));
                    }
                } else if (line.equals("----------------------------------")) {
                    students.add(student);
                    student = null;
                }
            }
            if (student != null) {
                students.add(student);
            }
        } catch (IOException e) {
            System.out.println("Error loading student details: " + e.getMessage());
        }

        return students;
    }

    //update
    public static void saveStudentDetails(Student updatedStudent) {
        String fileName = "student_registration.txt";
        List<Student> students = loadStudentDetails();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).getStudentId().equals(updatedStudent.getStudentId())) {
                students.set(i, updatedStudent);
                break;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (Student student : students) {
                writer.write("Name: " + student.getName());
                writer.newLine();
                writer.write("Phone Number: " + student.getPhoneNumber());
                writer.newLine();
                writer.write("Email: " + student.getEmail());
                writer.newLine();
                writer.write("Student ID: " + student.getStudentId());
                writer.newLine();
                writer.write("Password: " + student.getPassword());
                writer.newLine();
                writer.write("Registered Subject: " +
                        (student.getRegisteredSubject() != null ? student.getRegisteredSubject().getSubjectName() +
                                " (" + student.getRegisteredSubject().getSubjectCode() + ")"
                                : "None"));
                writer.newLine();
                writer.write("----------------------------------");
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving student details: " + e.getMessage());
        }
    }
}
