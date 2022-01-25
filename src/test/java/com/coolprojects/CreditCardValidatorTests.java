package com.coolprojects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.projects.cardvalidator.validator.CreditCardValidator;

public class CreditCardValidatorTests {
    
    private CreditCardValidator cardValidator = new CreditCardValidator();

    @Nested
    @DisplayName("checkLength()")
    class CheckLength{

        @Test
        void checkLength_ShouldReturnFalse_WhenStringIsEmpty() {
            String emptyString = "";
            boolean actual = cardValidator.checkLength(emptyString);
            assertEquals(false, actual);

        }

        @Test
        void checkLength_ShouldReturnFalse_WhenStringContainsOnlyWhiteSpaces() {
            String whiteSpaceString = "    ";
            boolean actual = cardValidator.checkLength(whiteSpaceString);
            assertEquals(false, actual);

        }

        @Test
        void checkLength_ShouldReturnFalse_WhenLengthIsLessThan16() {
            String lessThan16Text = "1234";
            boolean actual = cardValidator.checkLength(lessThan16Text);
            assertEquals(false,actual);
        }

        @Test
        void checkLength_ShouldReturnFalse_WhenLengthIsGreaterThan16() {
            String moreThan16 = "1234 1234 1234 1234 1234";
            boolean actual = cardValidator.checkLength(moreThan16);
            assertEquals(false, actual);
        }

        @Test
        void checkLength_ShouldReturnTrue_WhenLengthIs16() {
            String equalTo16 = "1234 1234 1234 1234";
            boolean actual = cardValidator.checkLength(equalTo16);
            assertEquals(true, actual);
        }

        @Test
        void checkLength_ShouldReturnTrue_WhenLengthIs16AndStringHasIrregularSpacing() {
            String equalTo16 = "1 2 34 12 3 4 12 3 4 12 34";
            boolean actual = cardValidator.checkLength(equalTo16);
            assertEquals(true, actual);
        }
    }

    @Nested
    @DisplayName("checkDigits()")
    class CheckDigits{

        @Test
        void checkDigits_ShouldReturnTrue_WhenStringIsEmpty() {
            String emptyString = "";
            boolean actual = cardValidator.checkDigits(emptyString);
            assertEquals(true, actual);

        }

        @Test
        void checkDigits_ShouldReturnTrue_WhenStringContainsOnlyWhiteSpaces() {
            String whiteSpaceString = "    ";
            boolean actual = cardValidator.checkDigits(whiteSpaceString);
            assertEquals(true, actual);

        }

        @Test
        void checkDigits_ShouldReturnFalse_WhenDigitsAndNonDigitsAreFound() {
            String digitsAndNonDigits = "1234 abc";
            boolean actual = cardValidator.checkDigits(digitsAndNonDigits);
            assertEquals(false, actual);

        }

        @Test
        void checkDigits_ShouldReturnFalse_WhenOnlyNonDigitsAreFound() {
            String nonDigits = "abc abc abc";
            boolean actual = cardValidator.checkDigits(nonDigits);
            assertEquals(false, actual);
        }

        @Test
        void checkDigits_ShouldReturnTrue_WhenOnlyDigitsAreFound() {
            String onlyDigits = "1612 1732 3432 1312";
            String digitsOfDifferentLength = "19 59 13 12";
            boolean actual = cardValidator.checkDigits(onlyDigits);
            boolean actualWithDifferentLength = cardValidator.checkDigits(digitsOfDifferentLength);
            assertEquals(true, actual);
            assertEquals(true, actualWithDifferentLength);
        }

    }

    @Nested
    @DisplayName("checkControlSum()")
    class ControlSumCheck{

        @Test
        void checkControlSum_ShouldReturnFalse_WhenStringIsEmpty() {
            String emptyString = "";
            boolean actual = cardValidator.checkControlSum(emptyString);
            assertEquals(false, actual);

        }

        @Test
        void checkControlSum_ShouldReturnFalse_WhenStringContainsOnlyWhiteSpaces() {
            String whiteSpaceString = "    ";
            boolean actual = cardValidator.checkControlSum(whiteSpaceString);
            assertEquals(false, actual);

        }

        @Test
        void checkControlSum_ShouldReturnFalse_WhenControlSumIsInvalid() {
            String invalidLastDigit = "4234 1234 1234 1235";
            boolean actual = cardValidator.checkControlSum(invalidLastDigit);
            assertEquals(false, actual);
        }

        @Test
        void checkControlSum_ShouldReturnTrue_WhenControlSumIsValid() {
            String validLastDigit = "4234 1234 1234 1232";
            boolean actual = cardValidator.checkControlSum(validLastDigit);
            assertEquals(true, actual);
        }

    }

    @Nested
    @DisplayName("checkPaymentSystem()")
    class CheckPaymentSystem{

        @Test
        void checkPaymentSystem_ShouldReturnFalse_WhenStringIsEmpty() {
            String emptyString = "";
            boolean actual = cardValidator.checkPaymentSystem(emptyString);
            assertEquals(false, actual);

        }

        @Test
        void checkPaymentSystem_ShouldReturnFalse_WhenStringContainsOnlyWhiteSpaces() {
            String whiteSpaceString = "    ";
            boolean actual = cardValidator.checkPaymentSystem(whiteSpaceString);
            assertEquals(false, actual );

        }

        @Test
        void checkPaymentSystem_ShouldReturnFalse_WhenPrefixIsInvalid() {
            String invalidPrefix = "1234 5678 1234 5678";
            boolean actual = cardValidator.checkPaymentSystem(invalidPrefix);
            assertEquals(false, actual);
        }

        @Test
        void checkPaymentSystem_ShouldReturnTrue_WhenPrefixIsValid() {
            String validNumbers = "4673";
            String validLength16 = "3683 1584 3844 4959";
            boolean actual = cardValidator.checkPaymentSystem(validNumbers);
            boolean actualLength16 = cardValidator.checkPaymentSystem(validLength16);
            assertEquals(true, actual);
            assertEquals(true, actualLength16);
        }
    }

    @Nested
    @DisplayName("validate()")
    class Validate{

        @Test
        void validate_ShouldReturnListOfSize3_WhenStringIsEmpty() {
            int actualSize = cardValidator.validate("").size();
            int expectedSize  = 3;
            assertEquals(expectedSize, actualSize );

        }

        @Test
        void validate_ShouldReturnListOfSize3_WhenStringContainsOnlyWhiteSpaces() {
            int actualSize = cardValidator.validate("       ").size();
            int expectedSize  = 3;
            assertEquals(expectedSize, actualSize);

        }


        @Test
        void validate_ShouldReturnListOfSize1_WhenControlSumIsInvalid() {
            String invalidControlSum = "4234 1234 1234 1235";
            int actualSize = cardValidator.validate(invalidControlSum).size();
            int expectedSize = 1;
            assertEquals(expectedSize, actualSize);
        }

        @Test
        void validate_ShouldReturnListOfSize2_WhenLengthIsInvalid(){
            String invalidLength = "4123";
            int actualSize = cardValidator.validate(invalidLength).size();
            int expectedSize = 2;
            assertEquals(expectedSize, actualSize);
        }

        @Test
        void validate_ShouldReturnListOfSize3_WhenNonDigitsAreFound(){
            String nonDigitText = "4234 bbbb aaaa 1235";
            int actualSize = cardValidator.validate(nonDigitText).size();
            int expectedSize = 3;
            assertEquals(expectedSize, actualSize);
        }


        @Test
        void validate_ShouldReturnEmptyList_WhenCardNumberIsValid() {
            String validCardNumber = "4234 1234 1234 1232";
            int actualSize = cardValidator.validate(validCardNumber).size();
            int expectedSize = 0;
            assertEquals(expectedSize, actualSize);
        }

    }

}
