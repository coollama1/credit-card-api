package com.projects.cardvalidator.utilities;

public class Utilities {

    /**
     * Returns the first n digits of the input string, where n = numberOfDigits
     *
     * @param input string to extract digits from
     * @return returns the first digits according to numberOfDigits
     */
    public static String getPrefixDigits(String input, int numberOfDigits) {
        StringBuilder output = new StringBuilder();

        for (char currentChar : input.toCharArray()) {
            if (output.length() >= numberOfDigits) {
                break;
            }
            if (Character.isDigit(currentChar)) {
                output.append(currentChar);
            }
        }

        return output.toString();
    }

    public static double formatDouble(double numberToFormat, int decimalPlaces) {
        double decimalShift = Math.pow(10, decimalPlaces);
        return ((int) Math.round(numberToFormat * decimalShift)) / decimalShift;
    }
}