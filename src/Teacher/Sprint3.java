package Teacher;

import java.io.*;
import java.util.Scanner;

public class Sprint3 {

    private static final String STUDENTS_FILE = "sprint3.txt"; // File to store student database
    private static final String REMARKS_FILE = "remarks.txt";  // File to store remarks

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================================");
        System.out.println("   Welcome to the Remarks Management System");
        System.out.println("==============================================");

        while (true) {
            System.out.println("\nMain Menu:");
            System.out.println("1. Add Remarks for Students");
            System.out.println("2. Exit");
            System.out.print("Option*: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addRemarks(scanner);
                    break;
                case 2:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void addRemarks(Scanner scanner) {
        String selectedClass = selectClass(scanner);

        if (selectedClass == null) {
            System.out.println("No classes available. Please contact the admin.");
            return;
        }

        String studentName;

        while (true) {
            System.out.print("\nEnter the student's name*: ");
            studentName = scanner.nextLine().trim();

            if (studentName.isEmpty()) {
                System.out.println("Error: Student name cannot be blank or contain only whitespace.");
                continue;
            }

            if (!isStudentInDatabase(selectedClass, studentName)) {
                System.out.println("Error: Student name not found in the database for class " + selectedClass + ". Please try again.");
                continue;
            }

            break;
        }

        System.out.print("Enter remarks for " + studentName + " *: ");
        String remarks = scanner.nextLine().trim();

        if (remarks.isEmpty()) {
            System.out.println("Error: Remarks cannot be blank or contain only whitespace.");
            return;
        }
        
        // Check for offensive or inappropriate language
        String[] bannedWords = {"tmd", "md", "fuck", "fuckyou", "niama"}; // Replace with actual banned words
        for (String banned : bannedWords) {
            if (remarks.toLowerCase().contains(banned.toLowerCase())) {
                System.out.println("Error: Remarks contain inappropriate or offensive language.");
                return;
            }
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(REMARKS_FILE, true))) {
            writer.write("Class: " + selectedClass + ", Student: " + studentName + ", Remarks: " + remarks);
            writer.newLine();
            System.out.println("Remarks added successfully for " + studentName + " in " + selectedClass + ".");
        } catch (IOException e) {
            System.out.println("An error occurred while saving remarks.");
        }
    }

    private static String selectClass(Scanner scanner) {
        System.out.println("\nAvailable Classes:");

        // List available classes (hardcoded for now)
        String[] classes = {"Math", "Science", "English"};

        if (classes.length == 0) {
            return null;
        }

        for (int i = 0; i < classes.length; i++) {
            System.out.println((i + 1) + ". " + classes[i]);
        }

        while (true) {
            System.out.print("Select a class by number*: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                System.out.println("Error: Class selection cannot be blank. Please enter a valid number.");
                continue;
            }

            try {
                int classChoice = Integer.parseInt(input);

                if (classChoice < 1 || classChoice > classes.length) {
                    System.out.println("Invalid class selection. Please try again.");
                } else {
                    return classes[classChoice - 1];
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for class selection.");
            }
        }
    }

    private static boolean isStudentInDatabase(String selectedClass, String studentName) {
        try (BufferedReader reader = new BufferedReader(new FileReader(STUDENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String className = parts[0].trim();
                    String name = parts[1].trim();

                    if (className.equalsIgnoreCase(selectedClass) && name.equalsIgnoreCase(studentName)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred while accessing the student database.");
        }

        return false;
    }
}
