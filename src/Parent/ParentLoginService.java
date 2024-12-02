package Parent;


import Parent.Parent;
import java.util.List;

public class ParentLoginService {

    // Method to log in a parent
    public boolean loginParent(String email, String password, List<Parent> registeredParents) {
        for (Parent parent : registeredParents) {
            if (parent.getEmail().equalsIgnoreCase(email) && parent.getPassword().equals(password)) {
                System.out.println("Login successful!");
                return true;
            }
        }
        System.out.println("Invalid email or password.");
        return false;
    }
}
