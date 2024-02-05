package org.der.weg.zum.java.profi.Refactoring;

import java.util.Objects;

public class IsNumberExample {
    public static boolean isNumber(final String value) {

        Objects.requireNonNull(value, "parameter 'value' must not be null");

        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
        /*
        if(value.isEmpty()) return false;
        String number = value;
        if(value.startsWith("-") || value.startsWith("+")){
            number = value.substring(1,value.length());
        }

            for (int i = 0, n =  number.length(); i < n; i++) {
                if(!Character.isDigit(number.charAt(i))){
                    return false;
                }
            }

        return  true;

         */
    }


    public static void main(String[] args) {

    }
}
