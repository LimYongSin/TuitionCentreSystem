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
        try (BufferedReader reader = new BufferedReader(new FileReader("subject.txt"))) {
            String line;
            int index = 1;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) { // Validate format
                    System.out.println("Invalid line format in file: " + line);
                    continue;
                }

                String subject = parts[0].trim();
                int fee;
                try {
                    fee = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Invalid fee value in line: " + line);
                    continue;
                }

                subjectMap.put(index, subject);
                feeMap.put(subject, fee);
                index++;
            }
        } catch (IOException e) {
            System.out.println("Error reading subject file: " + e.getMessage());
        }

        // Debugging: Verify loaded data
        System.out.println("Loaded Subjects: " + subjectMap);
        System.out.println("Loaded Fees: " + feeMap);
    }

    // Method to display tuition fee details
    public void displayTuitionFee() {
        loadSubjects(); // Load subject data from the file
        if (subjectMap.isEmpty() || feeMap.isEmpty()) {
            System.out.println("No subjects or fees found. Please check the subject.txt file.");
            return;
        }

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

            int choice = getValidIntegerInput(scanner, "Please enter a valid option (1-6):");

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
        System.out.printf("%-20s %-15s\n", "Subject", "Fee (RM)"); // Table header
        System.out.println("--------------------------------------------");

        int totalFee = 0;
        for (Map.Entry<String, Integer> entry : feeMap.entrySet()) {
            String subject = entry.getKey();
            int fee = entry.getValue();
            System.out.printf("%-20s %-15d\n", subject, fee);
            totalFee += fee;
        }

        System.out.println("--------------------------------------------");
        System.out.printf("%-20s %-15d\n", "Total Fee", totalFee); // Total fee row
    }

    // Method to validate integer input
    private int getValidIntegerInput(Scanner scanner, String errorMessage) {
        int input;
        while (true) {
            try {
                input = Integer.parseInt(scanner.nextLine());
                return input;
            } catch (NumberFormatException e) {
                System.out.println(errorMessage);
            }
        }
    }

    public static void main(String[] args) {
        TuitionFee tuitionFee = new TuitionFee();
        tuitionFee.displayTuitionFee();
    }
}
