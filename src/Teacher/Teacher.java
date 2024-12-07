package Teacher;
import java.io.*;
import java.util.Scanner;

public class Teacher {

    private static final String FILE_PATH = "teacher.txt"; // File to store credentials
    private static final String ATTENDANCE_FILE = "attendance.txt"; // File to store attendance records

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===============================================");
        System.out.println("Welcome to the Tuition Center Management System");
        System.out.println("===============================================");
        while (true) {
            System.out.println("Wish to access the system?");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.println("___________________________________________");
            System.out.print("Option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    register(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    System.out.println("Exiting the system. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to handle user registration
    private static void register(Scanner scanner) {
        System.out.print("\nEnter a username: ");
        String username = scanner.nextLine();

        System.out.print("Enter a password: ");
        String password = scanner.nextLine();

        String accountStatus = "active";

        // Save the credentials to the text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(username + ":" + password + ":" + accountStatus);
            writer.newLine();
            System.out.println("Registration successful!");
            System.out.println("___________________________________________");
        } catch (IOException e) {
            System.out.println("An error occurred while saving user information.");
        }
    }

    // Method to handle user login
    private static void login(Scanner scanner) {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();

        System.out.print("Enter your password: ");
        String password = scanner.nextLine();

        // Validate the credentials
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 3) {
                    String storedUsername = parts[0];
                    String storedPassword = parts[1];
                    String accountStatus = parts[2]; // Extract account status

                    if (storedUsername.equals(username) && storedPassword.equals(password)) {
                        if (accountStatus.equals("active")) {
                            System.out.println("Login successful! Welcome, " + username + ".");
                            System.out.println("___________________________________________");
                            accessFeatures(scanner); // Call feature menu after successful login
                            return;
                        } else {
                            System.out.println("Your account is inactive. Please contact support 012-2345679.\n");
                            return; // Exit the login method if account is inactive
                        }
                    }
                }
            }
            System.out.println("Invalid username or password. Please try again.");
            System.out.println("___________________________________________");
        } catch (IOException e) {
            System.out.println("An error occurred while reading user information.");
            System.out.println("___________________________________________");
        }
    }

    // Method to display and access system features
    private static void accessFeatures(Scanner scanner) {
        while (true) {
            System.out.println("\nTeachers' System:");
            System.out.println("1. View Class Schedule");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Record Student Performance");
            System.out.println("4. Logout");
            System.out.println("___________________________________________");
            System.out.print("Option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    System.out.println("Feature: View Class Schedule (To be implemented)");
                    break;
                case 2:
                    markAttendance(scanner);
                    break;
                case 3:
                    System.out.println("Feature: Record Student Performance (To be implemented)");
                    break;
                case 4:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
    
    //Sprint 2
    // Method to mark student attendance
    private static void markAttendance(Scanner scanner) {
    String selectedClass = selectClass(scanner);

    if (selectedClass == null) {
        System.out.println("No classes available. Please contact the admin.");
        return;
    }

    String studentName;

    while (true) {
        System.out.print("\nEnter the student's name: ");
        studentName = scanner.nextLine().trim();

        // Check if the student name is blank or whitespace-only
        if (studentName.isEmpty()) {
            System.out.println("Error: Student name cannot be blank or contain only whitespace.");
            continue;
        }

        // Validate the student name against the database (students.txt)
        if (!isStudentInDatabase(selectedClass, studentName)) {
            System.out.println("Error: Student name not found in the database for class " + selectedClass + ". Please try again.");
            continue;
        }

        // If the name is valid, break the loop
        break;
    }
    System.out.println("___________________________________________");
    System.out.println("Attendance Status Options:");
    System.out.println("1. Present");
    System.out.println("2. Absent");
    System.out.print("Enter the attendance status: ");
    
    String statusInput = scanner.nextLine().trim();

    // Check if the status input is blank or whitespace-only
    if (statusInput.isEmpty()) {
        System.out.println("Error: Attendance status cannot be blank or contain only whitespace.");
        System.out.println("___________________________________________");
        return;
    }

    String status = "";
    switch (statusInput) {
        case "1":
            status = "Present";
            break;
        case "2":
            status = "Absent";
            break;
        default:
            System.out.println("Error: Invalid attendance status option. Please try again.");
            return;
    }

    // Save attendance to the file
    try (BufferedWriter writer = new BufferedWriter(new FileWriter("attendance.txt", true))) {
        writer.write("Class: " + selectedClass + ", Student: " + studentName + ", Status: " + status);
        writer.newLine();
        System.out.println("Attendance marked successfully for " + studentName + " in class " + selectedClass + " as " + status + ".");
        System.out.println("___________________________________________");
    } catch (IOException e) {
        System.out.println("An error occurred while saving attendance.");
    }
}

// Helper method to allow class selection
private static String selectClass(Scanner scanner) {
    System.out.println("\nAvailable Classes:");
    try (BufferedReader reader = new BufferedReader(new FileReader("classes.txt"))) {
        String line;
        int count = 0;

        while ((line = reader.readLine()) != null) {
            count++;
            System.out.println(count + ". " + line);
        }

        if (count == 0) {
            System.out.println("Error: No classes available. Please contact the admin.");
            System.out.println("___________________________________________");
            return null; // No classes to select
        }

        while (true) {
            System.out.print("Class no.: ");
            String choiceInput = scanner.nextLine().trim();

            if (choiceInput.isEmpty()) {
                System.out.println("Error: Class selection cannot be blank. Please enter a valid class number.");
                System.out.println("___________________________________________");
                continue; // Prompt the user again
            }

            try {
                int choice = Integer.parseInt(choiceInput);

                if (choice > 0 && choice <= count) {
                    // Reset the reader to find the chosen class
                    reader.close();
                    try (BufferedReader reader2 = new BufferedReader(new FileReader("classes.txt"))) {
                        for (int i = 0; i < choice; i++) {
                            line = reader2.readLine();
                        }
                        return line; // Return the selected class name
                    }
                } else {
                    System.out.println("Error: Invalid class number. Please enter a number between 1 and " + count + ".");
                    System.out.println("___________________________________________");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid numeric class number.");
                System.out.println("___________________________________________");
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while reading the class database.");
        System.out.println("___________________________________________");
    }
    return null;
}


// Helper method to check if a student name exists in the database for a specific class
private static boolean isStudentInDatabase(String selectedClass, String studentName) {
    try (BufferedReader reader = new BufferedReader(new FileReader("name_list.txt"))) {
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(":");
            if (parts.length == 2) {
                String className = parts[0];
                String student = parts[1];

                if (className.equalsIgnoreCase(selectedClass) && student.equalsIgnoreCase(studentName)) {
                    return true; // Student name exists for the selected class
                }
            }
        }
    } catch (IOException e) {
        System.out.println("An error occurred while accessing the student database.");
        System.out.println("___________________________________________");
    }
    return false; // Student name not found
}

}