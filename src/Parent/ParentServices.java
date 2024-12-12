package Parent;

public class ParentServices {

    // Method to simulate fee payment
    public void payFees() {
        System.out.println("Paying fees online...");
        System.out.println("Payment successful! Thank you for your payment.");
    }

    // Method to view class timings
    public void viewClassTimings() {
        System.out.println("Class Timings:");
        System.out.println("Monday: 9:00 AM - 11:00 AM");
        System.out.println("Wednesday: 2:00 PM - 4:00 PM");
        System.out.println("Friday: 10:00 AM - 12:00 PM");
    }

    // Method to track attendance
    public void trackAttendance() {
        System.out.println("Tracking attendance...");
        System.out.println("Attendance for this month: 90%");
    }

    // Method to access study materials
    public void accessStudyMaterials() {
        System.out.println("Accessing study materials...");
        System.out.println("Study Materials: Math - Chapter 1, Science - Chapter 3");
    }
    
    public void viewTuitionFee() {
    TuitionFee tuitionFee = new TuitionFee();
    tuitionFee.displayTuitionFee();
}
}