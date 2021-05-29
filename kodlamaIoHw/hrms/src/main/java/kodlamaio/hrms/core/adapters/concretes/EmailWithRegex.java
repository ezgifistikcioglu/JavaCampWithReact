package kodlamaio.hrms.core.adapters.concretes;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailWithRegex {
    public static boolean isEmailValid(String email) {
        String emailRegex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        boolean resultValidate = matcher.matches();
        if (!resultValidate) {
            System.out.println("Email address type not correct, please check your email address type: " + email);
        }
        return resultValidate;
    }

    public static boolean isPasswordValid(String password) {
        boolean resultValidate = password.length() >= 6;
        if (!resultValidate) {
            System.out.println("Password must not be less than 6 characters, please check your password: " + password);
            return false;
        }
        return true;
    }

    public static boolean isFirstAndLastNameValid(String name) {
        String nameAndSurnameRegex = "^[a-zA-Z0-9_.]{2,20}$";
        Pattern pattern3 = Pattern.compile(nameAndSurnameRegex);
        Matcher matcher = pattern3.matcher(name);

        boolean resultValidate = matcher.matches();

        if (!resultValidate) {
            System.out.println("Name and surname must be at least 2 chars, please check your name length: " + name);
        }
        return resultValidate;
    }
}
