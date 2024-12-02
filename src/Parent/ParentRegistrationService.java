package Parent;

import Parent.Parent;
import java.util.ArrayList;
import java.util.List;

public class ParentRegistrationService {

    // Static list to store registered parents
    public static List<Parent> registeredParents = new ArrayList<>();

    public boolean registerParent(Parent parent) {
        // Check if the email already exists
        for (Parent p : registeredParents) {
            if (p.getEmail().equalsIgnoreCase(parent.getEmail())) {
                System.out.println("Parent with this email already exists!");
                return false;
            }
        }
        // Add parent to the registered list
        registeredParents.add(parent);
        System.out.println("Parent registered successfully!");
        return true;
    }
}
