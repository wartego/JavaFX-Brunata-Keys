package pl.wartego.javafxtest;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordValidation {

        public static String HashPasswordUnderLogin(String password){
        String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt(10));
            if(BCrypt.checkpw(password,hashedPassword)){
                System.out.println("Password Match");
                return hashedPassword;
            } else {
                System.out.println("No match");
                return "x";
            }
        }

        public static String HashPasswordUnderRegistryInDatabase(String password){
            return BCrypt.hashpw(password,BCrypt.gensalt(10));
        }


    public static boolean ifHashMatchToPassword(String Hashedpassword, String password ){
        return BCrypt.checkpw(password, Hashedpassword);
    }

}
