package com.coolprojects.validator;

import java.util.ArrayList;
import java.util.List;

import com.coolprojects.enums.CreditCardType;
import com.coolprojects.utilities.Utilities;

public class CreditCardValidator implements Validator {
    
    
    private final static String LENGTH_ERROR_MESSAGE = "Length should be 16 symbols";
    private final static String DIGITS_ERROR_MESSAGE = "Number should contain only digits";
    private final static String PAYMENT_SYSTEM_ERROR_MESSAGE = "Payment System can't be determined";
    private final static String LAST_DIGIT_ERROR_MESSAGE = "Control sum is invalid";

    private final int PROPER_CREDIT_CARD_LENGTH = 16;

    /**
     * Returns true if there are 16 digits within the String, and returns false otherwise
     *
     * @param cardNumber credit card number to be checked for proper length
     * @return returns true if there are 16 digits in the string
     */
    public boolean checkLength(String cardNumber) {
        int digitCount = 0;

        for (char currentChar : cardNumber.toCharArray()) {
            if (Character.isDigit(currentChar)) {
                digitCount++;
            }
        }
        return digitCount == PROPER_CREDIT_CARD_LENGTH;
    }

    /**
     * Returns true if there are only digits contained within the cred card number string,
     * and returns false otherwise
     *
     * @param cardNumber credit card number to be checked to contain only digits
     * @return returns true if there are only digits contained in the string
     */
    public boolean checkDigits(String cardNumber) {
        for (char currentChar : cardNumber.toCharArray()) {
            if (!Character.isDigit(currentChar) && !Character.isWhitespace(currentChar)) {
                return false;
            }
        }
        return true;

    }


    /**
     * returns true if the last digit is valid according to The Luhn Algorithm
     *
     * @param cardNumber card number string to validate
     * @return true if the last digit is valid, false otherwise
     */
    public boolean checkControlSum(String cardNumber) {
        if (!checkDigits(cardNumber) || !checkLength(cardNumber)) {
            return false;
        }
        
        String trimmedString = Utilities.getPrefixDigits(cardNumber, cardNumber.length());
        String lastDigitAsString = trimmedString.substring(trimmedString.length() - 1);
        int lastDigit = Integer.parseInt(lastDigitAsString);

        int totalSum = 0;
        for (int c = 0; c < trimmedString.length() - 1; c++) {
            int currentNum = Integer.parseInt(trimmedString.substring(c, c + 1));
            int evenNum = currentNum * 2;
            if (c % 2 == 0) {
                if (evenNum >= 10) {
                    int firstDigit = evenNum / 10;
                    int secondDigit = evenNum % 10;
                    totalSum += firstDigit + secondDigit;
                } else {
                    totalSum += evenNum;
                }
            } else {
                totalSum += currentNum;
            }
        }

        boolean isSumControlValid = (10 - (totalSum % 10)) == lastDigit;

        return isSumControlValid;

    }

    /**
     * Returns true if the card falls under a proper payment system format
     *
     * @param cardNumber credit card number string to be checked
     * @return returns true if the the card correlates with a payment system
     */
    public boolean checkPaymentSystem(String cardNumber) {
        PaymentSystemEvaluator evaluator = new PaymentSystemEvaluator();
        CreditCardType cardType = evaluator.evaluateSystem(cardNumber);
        boolean isPaymentSystemValid = cardType != CreditCardType.INVALID_CARD;

        return isPaymentSystemValid;
    }

    /**
     * returns a list of errors as strings for each part of the credit card validation stages
     * returns an empty list if credit card number is valid
     *
     * @param cardNumber card number string to validate
     * @return list of error strings
     */
    public List<String> validate(String cardNumber) {

        List<String> errors = new ArrayList<>();

        if (!checkLength(cardNumber)) {
            errors.add(LENGTH_ERROR_MESSAGE);
        }
        if (!checkDigits(cardNumber)) {
            errors.add(DIGITS_ERROR_MESSAGE);
        }
        if (!checkPaymentSystem(cardNumber)) {
            errors.add(PAYMENT_SYSTEM_ERROR_MESSAGE);
        }
        if (!checkControlSum(cardNumber)) {
            errors.add(LAST_DIGIT_ERROR_MESSAGE);
        }

        return errors;
    }
}
