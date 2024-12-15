package Student;

import java.io.*;
import java.util.*;

public class Payment {
    private static final String FILE_NAME = "tuition_fee.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Tuition Fee Payment ===");

        while (true) {
            System.out.print("Enter Student ID: ");
            String studentId = scanner.nextLine().trim();

            if (studentId.isEmpty()) {
                System.out.println("Error: Student ID cannot be blank. Please re-enter.");
                System.out.println(); // Add space
                continue;
            }

            String fee = getTuitionFee(studentId);
            if (fee == null) {
                System.out.println("Error: Invalid Student ID. Please re-enter.");
                System.out.println(); // Add space
                continue;
            }

          
            if (fee.equals("0") || fee.isEmpty()) {
                System.out.println("Hi " + studentId + ", there is no tuition fee to pay.");
                return; 
            }

            System.out.println("Tuition Fee for Student ID " + studentId + ": RM" + fee);
            System.out.println(); // Add space

            
            String cardNumber, expirationDate, cvv;

          
            while (true) {
                System.out.print("Enter Debit Card Number (16 digits): ");
                cardNumber = scanner.nextLine().trim();
                if (cardNumber.isEmpty()) {
                    System.out.println("Error: Card number cannot be blank.");
                    System.out.println(); 
                } else if (!isValidCardNumber(cardNumber)) {
                    if (cardNumber.matches(".*[a-zA-Z].*")) {
                        System.out.println("Error: Card number cannot contain letters.");
                        System.out.println(); 
                    } else {
                        System.out.println("Error: Card number must be exactly 16 digits.");
                        System.out.println(); 
                    }
                } else {
                    break;
                }
            }

            // Expiration Date Validation
            while (true) {
                System.out.print("Enter Expiration Date (MM/YY): ");
                expirationDate = scanner.nextLine().trim();
                if (expirationDate.isEmpty()) {
                    System.out.println("Error: Expiration date cannot be blank.");
                    System.out.println(); // Add space
                } else if (expirationDate.matches(".*[a-zA-Z].*")) {
                    System.out.println("Error: Expiration date cannot contain letters.");
                    System.out.println(); // Add space
                } else if (!isValidExpirationDate(expirationDate)) {
                    System.out.println("Error: Invalid expiration date. Use format MM/YY.");
                    System.out.println(); // Add space
                } else {
                    break;
                }
            }

            // CVV Validation
            while (true) {
                System.out.print("Enter CVV (3 digits): ");
                cvv = scanner.nextLine().trim();
                if (cvv.isEmpty()) {
                    System.out.println("Error: CVV cannot be blank.");
                    System.out.println(); // Add space
                } else if (!isValidCVV(cvv)) {
                    if (cvv.matches(".*[a-zA-Z].*")) {
                        System.out.println("Error: CVV cannot contain letters.");
                        System.out.println(); // Add space
                    } else {
                        System.out.println("Error: CVV must be exactly 3 digits.");
                        System.out.println(); // Add space
                    }
                } else {
                    break;
                }
            }

            // Successful payment
            System.out.println();
            System.out.println("Hi " + studentId + ", your tuition fee has been paid successfully.");
            System.out.println("=====================");
            System.out.println("Receipt");
            System.out.println("Student ID: " + studentId);
            System.out.println("RM: " + fee);
            System.out.println("=====================");

          
            updateTuitionFee(studentId);
            return;
        }
    }

    private static String getTuitionFee(String studentId) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data[0].equals(studentId)) {
                    return data[1]; // Return the fee
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading tuition fee file: " + e.getMessage());
        }
        return null;
    }

    private static void updateTuitionFee(String studentId) {
        try {
            // Read the original file into a list
            List<String> lines = new ArrayList<>();
            try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    // Check if the current line matches the student ID
                    if (data[0].equals(studentId)) {
                        // Update the fee to 0 for the matching student
                        lines.add(data[0] + ",0");
                    } else {
                        // Keep other entries unchanged
                        lines.add(line);
                    }
                }
            }

            // Write the updated lines back to the same file
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
                for (String updatedLine : lines) {
                    writer.write(updatedLine);
                    writer.newLine();
                }
            }

        } catch (IOException e) {
            System.out.println("Error updating tuition fee file: " + e.getMessage());
        }
    }

    private static boolean isValidCardNumber(String cardNumber) {
      
        return cardNumber.matches("\\d{16}");
    }

    private static boolean isValidExpirationDate(String expirationDate) {
     
        return expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}");
    }

    private static boolean isValidCVV(String cvv) {
     
        return cvv.matches("\\d{3}");
    }
}
