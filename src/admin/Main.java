package admin;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        WorkingHours workingHours = new WorkingHours(); // Create instance of WorkingHours
        SpecialExceptions specialException = new SpecialExceptions(); // Create instance of SpecialException

        // Display logo
        displayLogo();

        System.out.println("Welcome to the Tuition Center Management System (Admin Portal)");

        // Loop until exit
        while (true) {
            // Display the menu
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Admin");
            System.out.println("2. Admin Login");
            System.out.println("3. Set Working Hours");
            System.out.println("4. Exit");

            // Prompt the admin to enter an option
            System.out.print("Enter your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    admin.registerAdmin(scanner);
                    break;
                case 2:
                    admin.adminLogin(scanner);
                    break;
                case 3:
                    setWorkingHours(scanner, workingHours, specialException); // Pass specialException to handle
                    break;
                case 4:
                    System.out.println("Exiting the Admin System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to display logo
    private static void displayLogo() {
        System.out.println("**************************************************");
        System.out.println("*                                                *");
        System.out.println("*      TUITION CENTER MANAGEMENT SYSTEM          *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println();
    }

    // Method to handle setting working hours
    private static void setWorkingHours(Scanner scanner, WorkingHours workingHours, SpecialExceptions specialException) {
        while (true) {
            // Display the sub-menu for setting working hours
            System.out.println("\nChoose an option:");
            System.out.println("1. Set Working Hours");
            System.out.println("2. Set Special Exceptions");
            System.out.println("3. Go Back");

            // Prompt the admin to enter an option
            System.out.print("Enter your option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character

            switch (choice) {
                case 1:
                    setWorkingHoursInput(scanner, workingHours); // Call the method to set working hours
                    break;
                case 2:
                    setSpecialExceptions(scanner, specialException); // Call the method to set special exceptions
                    break;
                case 3:
                    return; // Go back to the main menu
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Method to set working hours
    private static void setWorkingHoursInput(Scanner scanner, WorkingHours workingHours) {
        // Set opening and closing hours
        String openingHour = getTimeFromUser(scanner, "opening");
        String closingHour = getTimeFromUser(scanner, "closing");
        workingHours.setOpeningClosingHours(openingHour, closingHour);

        // Add breaks (user presses Enter to finish input)
        addBreakTimes(scanner, workingHours);

        // Save to file
        workingHours.saveWorkingHoursToFile();

        // This message should only be printed after exiting the addBreakTimes method
        System.out.println("\nWorking hours and breaks have been saved.");
    }

    // Method to set special exceptions
    // Method to set special exceptions with validation
private static void setSpecialExceptions(Scanner scanner, SpecialExceptions specialException) {
    while (true) {
        // Prompt the user to enter special exceptions
        System.out.print("Enter special exceptions (e.g., holidays): ");
        String exception = scanner.nextLine().trim(); // Read and trim input to remove leading/trailing spaces

        // Validate if the input is blank
        if (exception.isEmpty()) {
            System.out.println("Invalid data. The special exception cannot be blank. Please try again.");
            continue; // Skip the rest and prompt again
        }

        // Validate if the input contains only letters and spaces (no numbers or special characters)
        if (!exception.matches("[a-zA-Z ]+")) {
            System.out.println("Invalid data. The special exception can only contain letters and spaces. Please try again.");
            continue; // Skip the rest and prompt again
        }

        // Add the special exception if it's valid and doesn't already exist
        specialException.addSpecialException(exception);

        // Display the special exceptions table immediately after adding the entry
        displaySpecialExceptionsTable(specialException);

        // Message indicating the special exceptions were saved
        System.out.println("\nSpecial exceptions have been saved.");
        break; // Exit the loop once the valid exception is added
    }
}


    // Method to get time input from user in hh:mm AM/PM format
    private static String getTimeFromUser(Scanner scanner, String timeType) {
        String time;
        while (true) {
            System.out.print("Enter " + timeType + " hour (e.g., 7:00 PM): ");
            time = scanner.nextLine();
            if (isValidTimeFormat(time)) {
                break;
            } else {
                System.out.println("Invalid format. Please enter the time in hh:mm AM/PM format with AM/PM in uppercase.");
            }
        }
        return time;
    }

    // Method to check if the time entered is in valid hh:mm AM/PM format with uppercase AM/PM
    private static boolean isValidTimeFormat(String time) {
        try {
            // Ensure AM/PM is in uppercase
            time = time.trim().toUpperCase();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            sdf.setLenient(false); // Prevent lenient parsing (e.g., "13:00 PM")
            Date date = sdf.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Method to add break times
    private static void addBreakTimes(Scanner scanner, WorkingHours workingHours) {
        boolean isFirstBreak = true;  // Flag to track if it's the first break entered
        String breakTime;

        // Loop to allow multiple entries for break times
        while (true) {
            System.out.print("Enter break times (e.g., 12:00 PM - 1:00 PM): ");
            breakTime = scanner.nextLine();  // Read the input directly

            if (breakTime.isEmpty()) {
                // If user presses Enter without typing anything, stop
                break;
            }

            // Validate break time format
            if (isValidBreakTimeFormat(breakTime)) {
                // Add the break time to the working hours
                workingHours.addBreak(breakTime);

                // If it's the first break time entered, show the table
                if (isFirstBreak) {
                    String openingHour = workingHours.getOpeningHour();
                    String closingHour = workingHours.getClosingHour();
                    displayWorkingHoursTable(openingHour, closingHour, workingHours, null); // Special exceptions not yet added here
                    isFirstBreak = false;  // Set flag to false after showing the table for the first time
                }
                break; // Exit the loop after valid input
            } else {
                System.out.println("Invalid break time format. Please enter in the format 'hh:mm AM - hh:mm PM' (AM/PM in uppercase).");
            }
        }
    }

    // Method to validate break time input (format 'hh:mm AM - hh:mm PM')
    private static boolean isValidBreakTimeFormat(String breakTime) {
        try {
            String[] times = breakTime.split(" - ");
            if (times.length == 2) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
                sdf.setLenient(false); // Prevent lenient parsing

                // Check if both start and end times are in correct format
                sdf.parse(times[0].trim().toUpperCase());
                sdf.parse(times[1].trim().toUpperCase());
                return true;
            }
        } catch (Exception e) {
            // Catch any parsing errors
        }
        return false;
    }

    // Method to display the working hours table
    private static void displayWorkingHoursTable(String openingHour, String closingHour, WorkingHours workingHours, SpecialExceptions specialException) {
        // Ensure valid working hours are set before displaying
        if (openingHour == null || closingHour == null || openingHour.isEmpty() || closingHour.isEmpty()) {
            return; // Don't display if hours are not yet set
        }

        System.out.println("\n------ Working Hours Setup Complete -------");
        System.out.println("|-----------------------------------------|");
        System.out.printf("| %-18s | %-18s |\n", "Attribute", "Working Hours");
        System.out.println("|-----------------------------------------|");

        // Display opening and closing hours
        System.out.printf("| %-18s | %-18s |\n", "Opening Hours", openingHour);
        System.out.printf("| %-18s | %-18s |\n", "Closing Hours", closingHour);

        // Display break times if available
        String breakTimeDisplay = workingHours.getBreaks().isEmpty() ? "None" : String.join(", ", workingHours.getBreaks());
        System.out.printf("| %-18s | %-18s |\n", "Break Hours", breakTimeDisplay);

        // Display special exceptions (if any)
        if (specialException != null) {
            String specialExceptionDisplay = specialException.getSpecialExceptions().isEmpty() ? "None" : String.join(", ", specialException.getSpecialExceptions());
            System.out.printf("| %-18s | %-18s |\n", "Special Exceptions", specialExceptionDisplay);
        }

        System.out.println("|-----------------------------------------|");
    }

    // Method to display the special exceptions table
    // Method to display the special exceptions table
private static void displaySpecialExceptionsTable(SpecialExceptions specialException) {
    System.out.println("\n---- Special Exceptions Setup Complete -----");
    System.out.println("|------------------------------------------|");
    System.out.printf("| %-18s | %-18s |\n", "Attribute", "Special Exceptions");
    System.out.println("|------------------------------------------|");

    // Display special exceptions, each on a new line
    if (!specialException.getSpecialExceptions().isEmpty()) {
        for (String exception : specialException.getSpecialExceptions()) {
            System.out.printf("| %-18s | %-18s |\n", "", exception);
        }
    } else {
        System.out.printf("| %-18s | %-18s |\n", "Special Exceptions", "None");
    }

    System.out.println("|------------------------------------------|");
}

}
