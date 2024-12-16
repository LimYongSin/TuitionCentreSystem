/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Parent;

/**
 *
 * @author Blade
 */
public class AttendanceRecord {
    private String date;
    private boolean isPresent;

    public AttendanceRecord(String date, boolean isPresent) {
        this.date = date;
        this.isPresent = isPresent;
    }

    public String getDate() {
        return date;
    }

    public boolean isPresent() {
        return isPresent;
    }
}

