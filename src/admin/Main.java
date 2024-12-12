package admin;

import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin();
        WorkingHours workingHours = new WorkingHours();
        SpecialExceptions specialException = new SpecialExceptions();

        // Display logo
        displayLogo();

        System.out.println("Welcome to the Tuition Center Management System (Admin Portal)");

        // Loop until exit
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Register Admin");
            System.out.println("2. Admin Login");
            System.out.println("3. Set Working Hours");
            System.out.println("4. Exit");

            System.out.print("Enter your option: ");
            String input = scanner.nextLine();

            if (input.isEmpty() || !input.matches("[1-4]")) {
                System.out.println("Invalid input. Please choose a valid option.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    admin.registerAdmin(scanner);
                    break;
                case 2:
                    admin.adminLogin(scanner);
                    break;
                case 3:
                    setWorkingHours(scanner, workingHours, specialException);
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

    private static void displayLogo() {
        System.out.println("**************************************************");
        System.out.println("*                                                *");
        System.out.println("*      TUITION CENTER MANAGEMENT SYSTEM          *");
        System.out.println("*                                                *");
        System.out.println("**************************************************");
        System.out.println();
    }

    private static void setWorkingHours(Scanner scanner, WorkingHours workingHours, SpecialExceptions specialException) {
        while (true) {
            System.out.println("\nChoose an option:");
            System.out.println("1. Set Working Hours");
            System.out.println("2. Set Special Exceptions");
            System.out.println("3. Go Back");

            System.out.print("Enter your option: ");
            String input = scanner.nextLine();

            if (input.isEmpty() || !input.matches("[1-3]")) {
                System.out.println("Invalid input. Please choose a valid option.");
                continue;
            }

            int choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    setWorkingHoursInput(scanner, workingHours, specialException);
                    break;
                case 2:
                    setSpecialExceptions(scanner, workingHours, specialException);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void setWorkingHoursInput(Scanner scanner, WorkingHours workingHours, SpecialExceptions specialException) {
    String openingHour = getTimeFromUser(scanner, "opening");
    String closingHour = getTimeFromUser(scanner, "closing");

    // Check if the new opening and closing hours are the same as the old ones
    if (openingHour.equals(workingHours.getOpeningHour()) && closingHour.equals(workingHours.getClosingHour())) {
        System.out.println("Invalid input. The new working hours cannot be the same as the old ones.");
        return; // Return without saving the hours if they are the same
    }

    workingHours.setOpeningClosingHours(openingHour, closingHour);
    addBreakTimes(scanner, workingHours);

    displayOverallSetupTable(workingHours, specialException);
    workingHours.saveWorkingHoursToFile();
}

    private static void setSpecialExceptions(Scanner scanner, WorkingHours workingHours, SpecialExceptions specialException) {
    while (true) {
        System.out.print("Enter special exceptions (e.g., holidays): ");
        String exception = scanner.nextLine().trim();

        if (exception.isEmpty()) {
            System.out.println("Invalid input. Special exception cannot be blank.");
            continue;
        }

        // Check if the new special exception is the same as the old one
        if (specialException.getSpecialExceptions().contains(exception)) {
            System.out.println("The entered special exception is the same as the previous one.");
            break; // Skip adding the duplicate exception
        }

        // Add the new special exception
        specialException.addSpecialException(exception);

        // Save the updated list to the file
        specialException.saveSpecialExceptionsToFile();

        // Display the updated table
        displayOverallSetupTable(workingHours, specialException);

        break; // Exit after one input
    }
}


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

    private static boolean isValidTimeFormat(String time) {
        try {
            time = time.trim().toUpperCase();
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            sdf.setLenient(false);
            sdf.parse(time);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private static void addBreakTimes(Scanner scanner, WorkingHours workingHours) {
    while (true) {
        System.out.print("Enter break times (e.g., 12:00 PM - 1:00 PM): ");
        String breakTime = scanner.nextLine();

        // Check for blank input
        if (breakTime.isEmpty()) {
            System.out.println("Invalid input. Break time cannot be blank.");
            continue;
        }

        // Check for valid time format
        if (!isValidTimeFormat(breakTime)) {
            System.out.println("Invalid format. Please enter break time in the format: hh:mm AM/PM - hh:mm AM/PM.");
            continue;
        }

        // Check if the break time is a duplicate
        if (workingHours.getBreaks().contains(breakTime)) {
            System.out.println("The entered break time is the same as the previous one.");
            break;  // Exit the loop if it's a duplicate
        }

        // Check if the break time is within the range of working hours
        if (!workingHours.addBreak(breakTime)) {
            System.out.println("Invalid break time. Break time must be within the range of opening and closing hours.");
            continue;
        }

        // If all checks pass, add the break time
        System.out.println("Break time has been set successfully.");
        break;  // Exit after adding the break time
    }
}


    private static void displayOverallSetupTable(WorkingHours workingHours, SpecialExceptions specialException) {
    System.out.println("\n----------- Overall Setup Complete -----------");
    System.out.println("|-------------------------------------------|");
    System.out.printf("| %-18s | %-18s |\n", "Attribute", "Details");
    System.out.println("|-------------------------------------------|");

    String openingHour = workingHours.getOpeningHour() != null ? workingHours.getOpeningHour() : "Not Set";
    String closingHour = workingHours.getClosingHour() != null ? workingHours.getClosingHour() : "Not Set";
    System.out.printf("| %-18s | %-18s |\n", "Opening Hours", openingHour);
    System.out.printf("| %-18s | %-18s |\n", "Closing Hours", closingHour);

    String breakTimeDisplay = workingHours.getBreaks().isEmpty() ? "None" : String.join(", ", workingHours.getBreaks());
    System.out.printf("| %-18s | %-18s |\n", "Break Hours", breakTimeDisplay);

    if (specialException.getSpecialExceptions().isEmpty()) {
        System.out.printf("| %-18s | %-18s |\n", "Special Exceptions", "None");
    } else {
        // Display all special exceptions on new lines
        boolean isFirst = true;
        for (String exception : specialException.getSpecialExceptions()) {
            if (isFirst) {
                // Print the first special exception
                System.out.printf("| %-18s | %-18s |\n", "Special Exceptions", exception);
                isFirst = false;
            } else {
                // Print subsequent exceptions on new lines
                System.out.printf("| %-18s | %-18s |\n", "", exception);
            }
        }
    }

    System.out.println("|-------------------------------------------|");
}


}
