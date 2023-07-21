package pl.wartego.javafxtest;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordValidation {

    private static String password = "ss";
    private static String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));

    public static void checkPass(String password){
        if(BCrypt.checkpw(password,hashedPassword)){
            System.out.println("Password Match");
        } else {
            System.out.println("No match");
        }
    }
}
