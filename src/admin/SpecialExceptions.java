package admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialExceptions {

    private List<String> specialExceptions = new ArrayList<>();
    private static final String FILE_PATH = "specialExceptions.txt";

    public void addSpecialException(String exception) {
        // Check if the new exception already exists in the list
        if (specialExceptions.contains("OffDay(\"" + exception + "\")")) {
            System.out.println("Invalid data. The special exception already exists.");
            return; // Do not add if it's the same as an old entry
        }

        // Add the new exception to the list
        specialExceptions.add("OffDay(\"" + exception + "\")");

        // Save the updated list to file
        saveSpecialExceptionsToFile();
    }

    public List<String> getSpecialExceptions() {
        return specialExceptions;
    }

    // Save special exceptions to file
    public void saveSpecialExceptionsToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (String exception : specialExceptions) {
                writer.write(exception);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving special exceptions to file: " + e.getMessage());
        }
    }

    // Load special exceptions from file
    public void loadSpecialExceptionsFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                specialExceptions.add(line);
            }
        } catch (IOException e) {
            System.out.println("Error loading special exceptions from file: " + e.getMessage());
        }
    }
}
