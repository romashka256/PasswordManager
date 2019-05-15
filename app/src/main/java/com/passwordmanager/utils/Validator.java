package com.passwordmanager.utils;

public class Validator {
    public static boolean valideLogin(String text) {
        if (text != null && !text.trim().isEmpty() && text.trim().contains("@"))
            return true;
        else
            return false;
    }

    public static boolean validePassword(String text) {
        if (text != null && !text.trim().isEmpty() && text.trim().length() > 5)
            return true;
        else
            return false;
    }

    public static boolean valideName(String text) {
        if (text != null && !text.trim().isEmpty())
            return true;
        else
            return false;
    }
}
