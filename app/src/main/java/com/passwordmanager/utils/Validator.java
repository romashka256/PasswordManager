package com.passwordmanager.utils;

public class Validator {
    public static boolean valideLogin(String text) {
        return text != null && !text.trim().isEmpty() && text.trim().contains("@");
    }

    public static boolean validePassword(String text) {
        return text != null && !text.trim().isEmpty() && text.trim().length() > 5;
    }

    public static boolean valideName(String text) {
        return text != null && !text.trim().isEmpty();
    }
}
