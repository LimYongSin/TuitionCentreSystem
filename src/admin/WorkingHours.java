package admin;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WorkingHours {

    private String openingHour;
    private String closingHour;
    private List<String> breaks = new ArrayList<>();
    private static final String FILE_PATH = "workingHours.txt";

    public void setOpeningClosingHours(String openingHour, String closingHour) {
        this.openingHour = openingHour.toUpperCase();  // Ensure it's in uppercase
        this.closingHour = closingHour.toUpperCase();  // Ensure it's in uppercase
    }

    public String getOpeningHour() {
        return openingHour;
    }

    public String getClosingHour() {
        return closingHour;
    }

    public boolean addBreak(String breakTime) {
        breakTime = breakTime.toUpperCase();  // Ensure it's in uppercase

        if (!isBreakWithinRange(breakTime, openingHour, closingHour)) {
            return false;
        }

        breaks.clear();  // Reset to one break
        breaks.add(breakTime);
        return true;
    }

    private boolean isBreakWithinRange(String breakTime, String openingHour, String closingHour) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm a");
            Date opening = sdf.parse(openingHour);
            Date closing = sdf.parse(closingHour);

            String[] times = breakTime.split(" - ");
            if (times.length == 2) {
                Date breakStart = sdf.parse(times[0].trim());
                Date breakEnd = sdf.parse(times[1].trim());

                return !breakStart.before(opening) && !breakEnd.after(closing);
            }
        } catch (Exception e) {
            System.out.println("Error parsing break time: " + e.getMessage());
        }
        return false;
    }

    public List<String> getBreaks() {
        return breaks;
    }

    public void saveWorkingHoursToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            writer.write("Opening Hour: " + (openingHour != null ? openingHour : "Not Set") + "\n");
            writer.write("Closing Hour: " + (closingHour != null ? closingHour : "Not Set") + "\n");
            writer.write("Break Times: " + (breaks.isEmpty() ? "None" : String.join(", ", breaks)) + "\n");
        } catch (IOException e) {
            System.out.println("Error saving working hours to file: " + e.getMessage());
        }
    }
}
