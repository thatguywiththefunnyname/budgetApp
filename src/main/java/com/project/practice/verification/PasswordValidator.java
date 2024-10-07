package com.project.practice.verification;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final int MIN_LENGTH = 8;
    private static final Pattern SPECIAL_CHAR = Pattern.compile("[^a-zA-Z0-9 ]");;

    public static boolean validatePassword(String password){
        return password.length() >= MIN_LENGTH && SPECIAL_CHAR.matcher(password).find();
    }
    
}
