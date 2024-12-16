package Parent;

import java.util.*;

public class TuitionFee {
    // Store student ID and their tuition fees
    private Map<String, Double> tuitionFees;

    // Store student ID and the list of enrolled classes
    private Map<String, List<String>> enrolledClasses;

    // Store fees for each class
    private Map<String, Double> classFees;

    // Additional data for payments, due dates, discounts, and penalties
    private Map<String, Double> paymentStatus;
    private Map<String, Date> dueDates;
    private Map<String, Double> discounts;
    private Map<String, Double> penalties;
    private Map<String, List<String>> paymentHistory;

    public TuitionFee() {
        // Initialize tuition fee records
        tuitionFees = new HashMap<>();
        tuitionFees.put("S12345", 1500.00);
        tuitionFees.put("S67890", 1600.00);
        tuitionFees.put("S54321", 1400.00);
        tuitionFees.put("S09876", 1700.00);

        // Initialize class fees
        classFees = new HashMap<>();
        classFees.put("Math", 200.00);
        classFees.put("Science", 250.00);
        classFees.put("English", 180.00);
        classFees.put("History", 150.00);

        // Initialize enrolled classes
        enrolledClasses = new HashMap<>();
        enrolledClasses.put("S12345", new ArrayList<>());
        enrolledClasses.put("S67890", new ArrayList<>());
        enrolledClasses.put("S54321", new ArrayList<>());
        enrolledClasses.put("S09876", new ArrayList<>());

        // Initialize additional data
        paymentStatus = new HashMap<>();
        dueDates = new HashMap<>();
        discounts = new HashMap<>();
        penalties = new HashMap<>();
        paymentHistory = new HashMap<>();

        // Initialize dummy data
        paymentStatus.put("S12345", 1000.00); // Paid amount
        dueDates.put("S12345", new GregorianCalendar(2024, Calendar.DECEMBER, 31).getTime());
        discounts.put("S12345", 100.0); // Scholarship
        penalties.put("S12345", 50.0);  // Late fee
        paymentHistory.put("S12345", new ArrayList<>(List.of("Paid $500 on 2024-12-01", "Paid $500 on 2024-12-15")));
    }

    // Method to enroll a student in a class
    public void enrollClass(String studentId, String className) {
        if (!classFees.containsKey(className)) {
            System.out.println("Error: Invalid class name.");
            return;
        }
        if (!enrolledClasses.containsKey(studentId)) {
            System.out.println("Error: Invalid Student ID.");
            return;
        }
        enrolledClasses.get(studentId).add(className);
        System.out.println("Class " + className + " enrolled successfully for Student ID " + studentId);
    }

    // Display tuition fee and enrolled classes
    public void displayTuitionFee(String studentId) {
        Double baseFee = tuitionFees.get(studentId);
        if (baseFee == null) {
            System.out.println("Error: Invalid Student ID. Please try again.");
            return;
        }

        // Get enrolled classes and calculate total class fees
        List<String> classes = enrolledClasses.get(studentId);
        double totalClassFee = 0;

        System.out.println("\nTuition Fee for Student ID " + studentId + ":");
        System.out.println("Base Fee: $" + baseFee);

        System.out.println("Enrolled Classes:");
        if (classes.isEmpty()) {
            System.out.println("  No classes enrolled.");
        } else {
            for (String className : classes) {
                double classFee = classFees.get(className);
                totalClassFee += classFee;
                System.out.println("  - " + className + ": $" + classFee + " (Schedule: TBD, Instructor: TBD)");
            }
        }

        // Additional charges, discounts, and payment details
        double additionalCharges = 50.00; // Example charge
        double discount = discounts.getOrDefault(studentId, 0.0);
        double penalty = penalties.getOrDefault(studentId, 0.0);
        double amountPaid = paymentStatus.getOrDefault(studentId, 0.0);
        double totalFee = baseFee + totalClassFee + additionalCharges - discount;

        System.out.println("Additional Charges: $" + additionalCharges);
        System.out.println("Discounts: -$" + discount);
        System.out.println("Total Fee (Base + Classes + Charges - Discounts): $" + totalFee);
        System.out.println("Amount Paid: $" + amountPaid);
        System.out.println("Remaining Balance: $" + (totalFee - amountPaid));

        // Show payment due date
        Date dueDate = dueDates.get(studentId);
        if (dueDate != null) {
            System.out.println("Payment Due Date: " + dueDate);
        }

        // Show penalties if applicable
        if (totalFee - amountPaid > 0 && dueDate != null && new Date().after(dueDate)) {
            System.out.println("Penalty for Late Payment: $" + penalty);
        }

        // Show payment history
        System.out.println("Payment History:");
        List<String> history = paymentHistory.get(studentId);
        if (history != null && !history.isEmpty()) {
            for (String record : history) {
                System.out.println("  - " + record);
            }
        } else {
            System.out.println("  No payment history available.");
        }
    }
}
