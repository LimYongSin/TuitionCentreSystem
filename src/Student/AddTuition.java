package Student;
import java.io.*;
import java.util.Scanner;

class AddTuition {
    private static final String FILE_NAME = "tuition_fee.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean continueAdding = true;

        while (continueAdding) {
            System.out.println("\n=== Add Tuition Fee ===");

            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine().trim();

            System.out.print("Enter Tuition Fee: ");
            String fee = scanner.nextLine().trim();

            // Allowing empty fee
            if (studentId.isEmpty()) {
                System.out.println("Error: Student ID is required. Please try again.");
                continue;
            }

            saveTuitionFee(studentId, fee); // Save fee even if it's empty
            System.out.println("Tuition fee added successfully!");

            System.out.print("Do you want to add another tuition fee? (y/n): ");
            String choice = scanner.nextLine().trim().toLowerCase();
            continueAdding = choice.equals("y");
        }

        System.out.println("Exiting tuition fee entry...");
        scanner.close();
    }

    private static void saveTuitionFee(String studentId, String fee) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true); BufferedWriter bw = new BufferedWriter(fw)) {
            bw.write(studentId + "," + fee); // Saving empty fee is allowed
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error saving tuition fee to file: " + e.getMessage());
        }
    }
}
