package admin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WorkingHours {

    private String openingHour;
    private String closingHour;
    private List<String> breaks = new ArrayList<>();
    private static final String FILE_PATH = "workingHours.txt";

    public void setOpeningClosingHours(String openingHour, String closingHour) {
        this.openingHour = openingHour;
        this.closingHour = closingHour;
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public void addBreak(String breakTime) {
        // Remove any existing break that matches the new one
        breaks.remove(breakTime); 
        breaks.add(breakTime); // Add the new break time
    }

    public List<String> getBreaks() {
        return breaks;
    }

    // Save working hours and breaks to file only if data has changed
    public void saveWorkingHoursToFile() {
        try {
            // Prepare the current data as a string
            StringBuilder currentData = new StringBuilder();
            currentData.append("Opening Hour: ").append(openingHour != null ? openingHour : "Not Set").append("\n");
            currentData.append("Closing Hour: ").append(closingHour != null ? closingHour : "Not Set").append("\n");

            // Convert the breaks list to a string and check for duplicates
            String breakData = breaks.isEmpty() ? "None" : String.join(", ", breaks);
            currentData.append("Break Times: ").append(breakData).append("\n");

            // Read existing data from the file
            String existingData = readFromFile(FILE_PATH);

            // Check if the current data is different from the existing data
            if (!currentData.toString().equals(existingData)) {
                // If data is different, write to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
                    writer.write(currentData.toString());
                }
                System.out.println("New data has been saved successfully.");
            } else {
                // If data is the same, display an invalid message
                System.out.println("Invalid: New data is the same as the old data. No changes saved.");
            }
        } catch (IOException e) {
            System.out.println("Error saving working hours to file: " + e.getMessage());
        }
    }

    // Read existing data from file
    private String readFromFile(String filePath) throws IOException {
        StringBuilder fileContent = new StringBuilder();
        File file = new File(filePath);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    fileContent.append(line).append("\n");
                }
            }
        }
        return fileContent.toString();
    }
}
