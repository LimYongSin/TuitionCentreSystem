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

            // If fee is zero or empty, notify that no payment is required
            if (fee.equals("0") || fee.isEmpty()) {
                System.out.println("Hi " + studentId + ", there is no tuition fee to pay.");
                return; // End the payment process
            }

            System.out.println("Tuition Fee for Student ID " + studentId + ": RM" + fee);
            System.out.println(); // Add space

            // Proceed with payment
            String cardNumber, expirationDate, cvv;

            // Card Number Validation
            while (true) {
                System.out.print("Enter Debit Card Number (16 digits): ");
                cardNumber = scanner.nextLine().trim();
                if (cardNumber.isEmpty()) {
                    System.out.println("Error: Card number cannot be blank.");
                    System.out.println(); // Add space
                } else if (!isValidCardNumber(cardNumber)) {
                    if (cardNumber.matches(".*[a-zA-Z].*")) {
                        System.out.println("Error: Card number cannot contain letters.");
                        System.out.println(); // Add space
                    } else {
                        System.out.println("Error: Card number must be exactly 16 digits.");
                        System.out.println(); // Add space
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

    private static boolean isValidCardNumber(String cardNumber) {
        // Ensure the card number is exactly 16 digits and contains only numbers (no letters or special characters)
        return cardNumber.matches("\\d{16}");
    }

    private static boolean isValidExpirationDate(String expirationDate) {
        // Ensure the expiration date is in MM/YY format
        return expirationDate.matches("(0[1-9]|1[0-2])/\\d{2}");
    }

    private static boolean isValidCVV(String cvv) {
        // Ensure CVV is exactly 3 digits
        return cvv.matches("\\d{3}");
    }
}
