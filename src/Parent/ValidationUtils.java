package Parent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidationUtils {

    // Validate email format
    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean isValidPhoneNumber(String phoneNumber) {
    String phoneRegex = "^[0-9]{10}$";  // Exactly 10 digits
    Pattern pattern = Pattern.compile(phoneRegex);
    return pattern.matcher(phoneNumber).matches();
}

    
}
