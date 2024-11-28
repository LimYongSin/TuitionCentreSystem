
package student;

public class Student {
    private String studentName;
    private String phoneNumber;
    private String icNumber;

    public Student(String studentName, String phoneNumber, String icNumber) {
        this.studentName = studentName;
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getIcNumber() {
        return icNumber;
    }

    public void registerForCourse(Course course) {
        if (course.registerStudent()) {
            System.out.println("Success! " + studentName + " has registered for " + course.getCourseName() + ".");
        } else {
            System.out.println("Sorry, " + course.getCourseName() + " is fully booked.");
        }
    }
}

