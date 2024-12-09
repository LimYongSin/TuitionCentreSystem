package admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpecialExceptions {

    private List<String> specialExceptions = new ArrayList<>();
    private static final String FILE_PATH = "specialExceptions.txt";

    public void addSpecialException(String exception) {
    // Ensure the exception is not wrapped in OffDay(...) already
    if (!exception.startsWith("OffDay(\"")) {
        exception = "OffDay(\"" + exception + "\")";
    }

    // Check if the exception is already in the list
    if (!specialExceptions.contains(exception)) {
        specialExceptions.add(exception); // Add the exception only if it is not already in the list
    } else {
        System.out.println("The entered special exception is the same as the previous one.");
    }
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
