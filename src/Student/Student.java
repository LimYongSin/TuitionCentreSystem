package Student;

public class Student {

    private String name;
    private String phoneNumber;
    private String email; // Added email field
    private String studentId;
    private String password;
    private Subject registeredSubject; // Changed to Subject

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

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getPassword() {
        return password;
    }

    public Subject getRegisteredSubject() { // Changed to Subject
        return registeredSubject;
    }

    public void setRegisteredSubject(Subject registeredSubject) { // Changed to Subject
        this.registeredSubject = registeredSubject;
    }

    public void registerSubject(Subject subject) { // Changed to Subject
        this.registeredSubject = subject;
    }

    public String getDetails() {
        return "Name: " + name + "\n"
                + "Phone Number: " + phoneNumber + "\n"
                + "Email: " + email + "\n"
                + "Registered Subject: " + (registeredSubject != null
                        ? registeredSubject.getSubjectName() + " (" + registeredSubject.getSubjectCode() + ")"
                        : "None");
    }
}
