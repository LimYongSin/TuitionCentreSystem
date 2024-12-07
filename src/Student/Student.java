package Student;

public class Student {

    private String name;
    private String phoneNumber;
    private String email; 
    private String studentId;
    private String password;
    private Subject registeredSubject; 

    // Constructor with parameters
    public Student(String name, String phoneNumber, String email, String studentId, String password) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.studentId = studentId;
        this.password = password;
    }

    // No-argument constructor
    public Student() {
        this.name = "";
        this.phoneNumber = "";
        this.email = "";
        this.studentId = "";
        this.password = "";
        this.registeredSubject = null;
    }

    // Getter and Setter methods
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Subject getRegisteredSubject() {
        return registeredSubject;
    }

    public void setRegisteredSubject(Subject registeredSubject) {
        this.registeredSubject = registeredSubject;
    }

    public void registerSubject(Subject subject) {
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
