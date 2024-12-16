package admin;

import java.io.*;
import java.util.*;

public class FeeStructure {
    private Map<String, CourseFee> feeStructure;
    private List<String> courseList;

    public FeeStructure() {
        feeStructure = new HashMap<>();
        courseList = new ArrayList<>();
        loadCoursesFromFile();
    }

    public void setFeeStructure(Scanner scanner) {
        while (true) {
            System.out.println("\n--- Set Fee Structure ---");
            displayCourses();
            System.out.println("Select a course by number (or type 'exit' to go back):");

            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            int courseIndex;
            try {
                courseIndex = Integer.parseInt(input);
                if (courseIndex < 1 || courseIndex > courseList.size()) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid course number.");
                continue;
            }

            String courseName = courseList.get(courseIndex - 1);

            // Input fee structure details
            System.out.print("Enter tuition fee: ");
            double tuitionFee = getValidFee(scanner);

            System.out.print("Enter lab fee (if any, or enter 0): ");
            double labFee = getValidFee(scanner);

            System.out.print("Enter resource fee (if any, or enter 0): ");
            double resourceFee = getValidFee(scanner);

            // Input discount percentage and validate it
            double discount = getValidDiscount(scanner);

            // Calculate the final fee after discount
            double totalFee = (tuitionFee + labFee + resourceFee) * (1 - (discount / 100));
            CourseFee courseFee = new CourseFee(tuitionFee, labFee, resourceFee, discount, totalFee);

            feeStructure.put(courseName, courseFee);

            System.out.println("\nFee structure for '" + courseName + "' has been updated:");
            displayCourseFee(courseName, courseFee);

            saveToCourseFeeFile(courseName, courseFee);

            System.out.println("Fee structure saved successfully!");
        }
    }

    private double getValidFee(Scanner scanner) {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                double fee = Double.parseDouble(input);
                if (fee < 0) {
                    throw new NumberFormatException();
                }
                return fee;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a non-negative number.");
            }
        }
    }

    private double getValidDiscount(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Enter discount percentage (0-100): ");
                String input = scanner.nextLine().trim();
                double discount = Double.parseDouble(input);

                // Validate if the discount is between 0 and 100
                if (discount < 0 || discount > 100) {
                    System.out.println("Invalid input. Please enter a valid discount percentage between 0 and 100.");
                    continue;
                }

                return discount;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            }
        }
    }

    private void displayCourses() {
        System.out.println("Available Courses:");
        for (int i = 0; i < courseList.size(); i++) {
            System.out.println((i + 1) + ". " + courseList.get(i));
        }
    }

    private void loadCoursesFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader("course.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                courseList.add(line.trim());
            }
        } catch (IOException e) {
            System.out.println("Error loading courses from file: " + e.getMessage());
        }
    }

    private void saveToCourseFeeFile(String courseName, CourseFee courseFee) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("courseFee.txt", true))) {
            writer.write(courseName + "|RM" + courseFee.getTuitionFee() + "|RM" + courseFee.getLabFee() + "|RM" + courseFee.getResourceFee() + "|" + courseFee.getDiscount() + "%|RM" + courseFee.getTotalFee() + "\n");
        } catch (IOException e) {
            System.out.println("Error saving to course fee file: " + e.getMessage());
        }
    }

    private void displayCourseFee(String courseName, CourseFee courseFee) {
        System.out.println("---------------------------------------------------");
        System.out.printf("| %-15s | %-15s |\n", "Attribute", "Details");
        System.out.println("---------------------------------------------------");
        System.out.printf("| %-15s | %-15s |\n", "Course Name", courseName);
        System.out.printf("| %-15s | RM%-14.2f |\n", "Tuition Fee", courseFee.getTuitionFee());
        System.out.printf("| %-15s | RM%-14.2f |\n", "Lab Fee", courseFee.getLabFee());
        System.out.printf("| %-15s | RM%-14.2f |\n", "Resource Fee", courseFee.getResourceFee());
        System.out.printf("| %-15s | %-14.2f%% |\n", "Discount", courseFee.getDiscount());
        System.out.printf("| %-15s | RM%-14.2f |\n", "Total Fee", courseFee.getTotalFee());
        System.out.println("---------------------------------------------------");
    }
}

class CourseFee {
    private double tuitionFee;
    private double labFee;
    private double resourceFee;
    private double discount;
    private double totalFee;

    public CourseFee(double tuitionFee, double labFee, double resourceFee, double discount, double totalFee) {
        this.tuitionFee = tuitionFee;
        this.labFee = labFee;
        this.resourceFee = resourceFee;
        this.discount = discount;
        this.totalFee = totalFee;
    }

    public double getTuitionFee() {
        return tuitionFee;
    }

    public double getLabFee() {
        return labFee;
    }

    public double getResourceFee() {
        return resourceFee;
    }

    public double getDiscount() {
        return discount;
    }

    public double getTotalFee() {
        return totalFee;
    }
}
