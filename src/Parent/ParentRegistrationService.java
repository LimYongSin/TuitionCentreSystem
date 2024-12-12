package Parent;

import java.util.ArrayList;
import java.util.List;

public class ParentRegistrationService {
    // A list to store registered parents
    public static List<Parent> registeredParents = new ArrayList<>();

    // Method to register a new parent
    public void registerParent(Parent parent) {
        // Add the parent to the list
        registeredParents.add(parent);
        System.out.println("Parent registered successfully.");
    }

    // Optional: Add a method to check if a student ID is already registered
    public boolean isStudentIdRegistered(String studentId) {
        for (Parent parent : registeredParents) {
            if (parent.getStudentId().equals(studentId)) {
                return true;
            }
        }
        return false;
    }
}
