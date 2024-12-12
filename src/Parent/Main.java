package Parent;

import Parent.Parent;
import Parent.ParentRegistrationService;
import Parent.ParentLoginService;
import Parent.ParentServices;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.Arrays;

public class Main {
    // Hardcoded list of valid student IDs
    private static final String[] VALID_STUDENT_IDS = {"S12345", "S67890", "S54321", "S09876"};

    public static void main(String[] args) {
        ParentRegistrationService registrationService = new ParentRegistrationService();
        ParentLoginService loginService = new ParentLoginService();
        ParentServices parentServices = new ParentServices();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Main Menu
            System.out.println("=== Tuition Centre Management ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                // Registration
                System.out.println("=== Parent Registration ===");

                // Check if the student ID is valid
                String studentId;
                while (true) {
                    System.out.print("Enter your student ID: ");
                    studentId = scanner.nextLine();
                    if (studentId.isEmpty()) {
                        System.out.println("Error: Student ID cannot be empty. Please try again.");
                    } else if (!isValidStudentId(studentId)) {
                        System.out.println("Error: Invalid Student ID. Please enter a valid ID.");
                    } else {
                        break;
                    }
                }

                // Check if the name is blank
                String name;
                while (true) {
                    System.out.print("Enter your name: ");
                    name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("Error: Name cannot be empty. Please try again.");
                    } else {
                        break;
                    }
                }

                // Email validation
                String email;
                while (true) {
                    System.out.print("Enter your email: ");
                    email = scanner.nextLine();
                    if (email.isEmpty()) {
                        System.out.println("Error: Email cannot be empty. Please try again.");
                    } else if (!isValidEmail(email)) {
                        System.out.println("Error: Invalid email format. Please enter a valid email.");
                    } else {
                        break;
                    }
                }

               // Check if the password meets validation criteria
String password;
while (true) {
    System.out.print("Enter your password (min 8 characters, including uppercase, digit, special char): ");
    password = scanner.nextLine();
    if (password.isEmpty()) {
        System.out.println("Error: Password cannot be empty. Please try again.");
    } else if (!isValidPassword(password)) {
        System.out.println("Error: Password must be at least 8 characters long and include:");
        System.out.println("- At least one uppercase letter");
        System.out.println("- At least one lowercase letter");
        System.out.println("- At least one digit");
        System.out.println("- At least one special character (@$!%*?&)");
    } else {
        break;
    }
}


                // Phone number validation
                String phoneNumber;
                while (true) {
                    System.out.print("Enter your phone number (e.g., 1234567890): ");
                    phoneNumber = scanner.nextLine();
                    if (phoneNumber.isEmpty()) {
                        System.out.println("Error: Phone number cannot be empty. Please try again.");
                    } else if (!isValidPhoneNumber(phoneNumber)) {
                        System.out.println("Error: Invalid phone number format. Please enter a valid 10-digit number.");
                    } else {
                        break;
                    }
                }

                // Create the Parent object with the provided information
                Parent newParent = new Parent(name, email, password, phoneNumber, studentId);

                // Register the parent using the registration service
                registrationService.registerParent(newParent);

                // Display the parent's details on the confirmation page
                System.out.println("\n=== Registration Successful ===");
                System.out.println("Thank you for registering! Here are your details:");
                System.out.println("Name: " + newParent.getName());
                System.out.println("Email: " + newParent.getEmail());
                System.out.println("Phone Number: " + newParent.getPhoneNumber());
                System.out.println("Student ID: " + newParent.getStudentId());
                System.out.println("Password: ********"); // Password should not be shown for security purposes
                System.out.println("\nPlease keep your details safe and log in to continue.");

                // Simulate sending a confirmation email
                sendConfirmationEmail(newParent.getEmail());
            } else if (choice == 2) {
                // Login
                System.out.println("=== Parent Login ===");
                System.out.print("Enter your email: ");
                String email = scanner.nextLine();

                System.out.print("Enter your password: ");
                String password = scanner.nextLine();

                // Authenticate
                boolean loginSuccessful = loginService.loginParent(email, password, ParentRegistrationService.registeredParents);

                if (loginSuccessful) {
                    // Show post-login menu
                    postLoginMenu(scanner, parentServices);
                }
            } else if (choice == 3) {
                System.out.println("Exiting system...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    private static void sendConfirmationEmail(String email) {
        // Simulate the sending of a confirmation email
        System.out.println("\n=== Sending Confirmation Email ===");
        System.out.println("A confirmation email has been sent to: " + email);
        System.out.println("Please check your inbox and follow the instructions to complete your registration.");
    }

    // Email validation method using regex
    public static boolean isValidEmail(String email) {
        // Regular expression for basic email validation
        String emailRegex = "^[a-zA-Z0-9_+&-]+(?:\\.[a-zA-Z0-9_+&-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    // Phone number validation method using regex
    public static boolean isValidPhoneNumber(String phoneNumber) {
        // Regular expression for validating a 10-digit phone number
        String phoneRegex = "^[0-9]{10}$";  // This ensures exactly 10 digits
        Pattern pattern = Pattern.compile(phoneRegex);
        return pattern.matcher(phoneNumber).matches();
    }

    // Validate student ID
    public static boolean isValidStudentId(String studentId) {
        return Arrays.asList(VALID_STUDENT_IDS).contains(studentId);
    }
    
    public static boolean isValidPassword(String password) {
    // Regular expression for a password with:
    // - Minimum 8 characters
    // - At least one uppercase letter
    // - At least one lowercase letter
    // - At least one digit
    // - At least one special character (optional)
    String passwordRegex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
    Pattern pattern = Pattern.compile(passwordRegex);
    return pattern.matcher(password).matches();
}

    private static void postLoginMenu(Scanner scanner, ParentServices parentServices) {
        TuitionFee tuitionFee = new TuitionFee();  // Make sure this is initialized

        while (true) {
            // Post-login Menu
            System.out.println("=== Welcome to Your Parent Dashboard ===");
            System.out.println("1. Pay Fees Online");
            System.out.println("2. View Tuition Fee");
            System.out.println("3. Track Attendance");
            System.out.println("4. Access Study Materials");
            System.out.println("5. Logout");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine();  // Consume newline

            if (choice == 1) {
                parentServices.payFees();
            } else if (choice == 2) {
                tuitionFee.displayTuitionFee();  // This is where we call the display method
            } else if (choice == 3) {
                parentServices.trackAttendance();
            } else if (choice == 4) {
                parentServices.accessStudyMaterials();
            } else if (choice == 5) {
                System.out.println("Logging out...");
                break;
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
