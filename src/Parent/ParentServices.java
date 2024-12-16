package Parent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    
    public ParentServices() {
        // Preset attendance records
        presetAttendanceRecords();
    }

    private void presetAttendanceRecords() {
        attendanceRecords.put("S12345", Arrays.asList(
                new AttendanceRecord("2024-12-01", true),
                new AttendanceRecord("2024-12-02", true),
                new AttendanceRecord("2024-12-03", false),
                new AttendanceRecord("2024-12-04", true),
                new AttendanceRecord("2024-12-05", false)
        ));

        attendanceRecords.put("S67890", Arrays.asList(
                new AttendanceRecord("2024-12-01", false),
                new AttendanceRecord("2024-12-02", true),
                new AttendanceRecord("2024-12-03", true),
                new AttendanceRecord("2024-12-04", true),
                new AttendanceRecord("2024-12-05", true)
        ));

        attendanceRecords.put("S54321", Arrays.asList(
                new AttendanceRecord("2024-12-01", true),
                new AttendanceRecord("2024-12-02", false),
                new AttendanceRecord("2024-12-03", true),
                new AttendanceRecord("2024-12-04", true),
                new AttendanceRecord("2024-12-05", true)
        ));

        attendanceRecords.put("S09876", Arrays.asList(
                new AttendanceRecord("2024-12-01", true),
                new AttendanceRecord("2024-12-02", true),
                new AttendanceRecord("2024-12-03", true),
                new AttendanceRecord("2024-12-04", false),
                new AttendanceRecord("2024-12-05", false)
        ));
    }
    
    private Map<String, List<AttendanceRecord>> attendanceRecords = new HashMap<>();


    // View attendance summary for a specific student
    public void viewAttendanceSummary(String studentId) {
        List<AttendanceRecord> records = attendanceRecords.get(studentId);
        if (records == null || records.isEmpty()) {
            System.out.println("No attendance records found for student ID: " + studentId);
            return;
        }

        int totalDays = records.size();
        long presentDays = records.stream().filter(AttendanceRecord::isPresent).count();
        long absentDays = totalDays - presentDays;
        double attendancePercentage = (presentDays * 100.0) / totalDays;

        System.out.println("\n=== Attendance Summary ===");
        System.out.println("Total Days: " + totalDays);
        System.out.println("Days Present: " + presentDays);
        System.out.println("Days Absent: " + absentDays);
        System.out.printf("Attendance Percentage: %.2f%%\n", attendancePercentage);
    }

    // View detailed attendance records
    public void viewDetailedAttendance(String studentId) {
        List<AttendanceRecord> records = attendanceRecords.get(studentId);
        if (records == null || records.isEmpty()) {
            System.out.println("No attendance records found for student ID: " + studentId);
            return;
        }

        System.out.println("\n=== Detailed Attendance Records ===");
        System.out.println("Date\t\t\tStatus");
        for (AttendanceRecord record : records) {
            System.out.println(record.getDate() + "\t" + (record.isPresent() ? "Present" : "Absent"));
        }
    }
   
    private Map<String, Integer> attendanceAlerts = new HashMap<>();

    // View Attendance Summary - Already exists
    // View Detailed Attendance - Already exists

    // Set alert for low attendance
    public void setAttendanceAlert(String studentId, int threshold) {
        if (!attendanceRecords.containsKey(studentId)) {
            System.out.println("No records found for student ID: " + studentId);
            return;
        }
        attendanceAlerts.put(studentId, threshold);
        System.out.println("Alert set! You will be notified if attendance falls below " + threshold + "%.");
    }

    // Simulate a notification for low attendance
    public void checkAttendanceAlerts() {
        for (String studentId : attendanceAlerts.keySet()) {
            List<AttendanceRecord> records = attendanceRecords.get(studentId);
            if (records == null || records.isEmpty()) continue;

            int totalDays = records.size();
            long presentDays = records.stream().filter(AttendanceRecord::isPresent).count();
            double attendancePercentage = (presentDays * 100.0) / totalDays;

            if (attendancePercentage < attendanceAlerts.get(studentId)) {
                System.out.println("ALERT: Student ID " + studentId +
                        " has low attendance (" + attendancePercentage + "%).");
            }
        }
    }


    public void accessStudyMaterials() {
        System.out.println("Accessing study materials...");
        System.out.println("Study Materials: Math - Chapter 1, Science - Chapter 3");
    }
    
}
    
    
