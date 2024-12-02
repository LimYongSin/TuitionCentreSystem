package Student;

import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private String phoneNumber;
    private int age;
    private String studentId;
    private String password;
    private Course registeredCourse;

    public Student(String name, String phoneNumber, int age, String studentId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
        this.studentId = studentId;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Course getRegisteredCourse() {
        return registeredCourse;
    }

    public void setRegisteredCourse(Course registeredCourse) {
        this.registeredCourse = registeredCourse;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public void registerCourse(Course course) {
        this.registeredCourse = course;
    }

    public String getDetails() {
        return "Name: " + name + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Age: " + age + "\n"
                + "Registered Course: " + (registeredCourse != null
                        ? registeredCourse.getCourseName() + " (" + registeredCourse.getCourseCode() + ")"
                        : "None");
    }
}
