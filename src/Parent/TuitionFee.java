package Parent;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class TuitionFee {

    private Map<Integer, String> subjectMap = new HashMap<>();
    private Map<String, Integer> feeMap = new HashMap<>();

    // Method to load subject data
    private void loadSubjects() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("subject.txt"));
            String line;
            int index = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String subject = parts[0].trim();
                int fee = Integer.parseInt(parts[1].trim());

                subjectMap.put(index, subject);
                feeMap.put(subject, fee);
                index++;
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Error reading subject file: " + e.getMessage());
        }
    }

    // Method to display tuition fee details
    public void displayTuitionFee() {
    System.out.println("Entering displayTuitionFee method..."); // Debug statement

    loadSubjects(); // Load subject data from the file
    Scanner scanner = new Scanner(System.in);

    while (true) {
        System.out.println("\n=== Tuition Fee Details ===");
        System.out.println("Unpaid Subjects:");
        for (Map.Entry<Integer, String> entry : subjectMap.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.println("5. Total Fee");
        System.out.println("6. Back to Dashboard");
        System.out.print("\nEnter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        if (choice >= 1 && choice <= subjectMap.size()) {
            String selectedSubject = subjectMap.get(choice);
            System.out.println("Selected Subject: " + selectedSubject);
            System.out.println("Tuition Fee: RM " + feeMap.get(selectedSubject));
        } else if (choice == 5) {
            displayTotalFee();
        } else if (choice == 6) {
            break; // Exit to dashboard
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }
}

    // Method to calculate and display the total fee with a table format
    private void displayTotalFee() {
        System.out.println("\n=== Total Unpaid Fee Details ===");
        System.out.printf("%-20s %-15s\n", "Subject", "Fee (RM)");  // Table header
        System.out.println("--------------------------------------------");

        int totalFee = 0;
        for (Map.Entry<String, Integer> entry : feeMap.entrySet()) {
            String subject = entry.getKey();
            int fee = entry.getValue();
            System.out.printf("%-20s %-15d\n", subject, fee);
            totalFee += fee;
        }

        System.out.println("--------------------------------------------");
        System.out.printf("%-20s %-15d\n", "Total Fee", totalFee);  // Total fee row
    }
}
