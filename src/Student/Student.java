
package Student;

import java.util.ArrayList;
import java.util.List;

public class Student {
    private String name;
    private String phoneNumber;
    private int age;
    private Course registeredCourse;

    public Student(String name, String phoneNumber, int age) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.age = age;
    }

    public void registerCourse(Course course) {
        this.registeredCourse = course;
    }

    public String getDetails() {
        return "Name: " + name + "\n" +
               "Phone Number: " + phoneNumber + "\n" +
               "Age: " + age + "\n" +
               "Registered Course: " + (registeredCourse != null 
                                        ? registeredCourse.getCourseName() + " (" + registeredCourse.getCourseCode() + ")" 
                                        : "None");
    }
}
