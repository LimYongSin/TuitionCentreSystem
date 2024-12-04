package Student;

public class Student {

    private String name;
    private String phoneNumber;
    private String email; // Added email field
    private String studentId;
    private String password;
    private Course registeredCourse;

    public Student(String name, String phoneNumber, String email, String studentId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
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

    public String getEmail() { // Getter for email
        return email;
    }

    public void setEmail(String email) { // Setter for email
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public Course getRegisteredCourse() {
        return registeredCourse;
    }

    public void setRegisteredCourse(Course registeredCourse) {
        this.registeredCourse = registeredCourse;
    }

    public void registerCourse(Course course) {
        this.registeredCourse = course;
    }

    public String getDetails() {
        return "Name: " + name + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email: " + email + "\n"
                + "Registered Course: " + (registeredCourse != null
                        ? registeredCourse.getCourseName() + " (" + registeredCourse.getCourseCode() + ")"
                        : "None");
    }
}
