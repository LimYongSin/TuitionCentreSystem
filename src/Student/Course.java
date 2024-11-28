
package Student;

public class Course {
    private String courseName;
    private String courseCode;
    private int availableSeats;

    public Course(String courseName, String courseCode, int availableSeats) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.availableSeats = availableSeats;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public boolean registerStudent() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return courseCode + ": " + courseName + " (Available Seats: " + availableSeats + ")";
    }
}
