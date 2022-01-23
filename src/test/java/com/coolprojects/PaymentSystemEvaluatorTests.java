package com.coolprojects;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import com.coolprojects.enums.CreditCardType;
import com.coolprojects.validator.PaymentSystemEvaluator;

public class PaymentSystemEvaluatorTests {
    private PaymentSystemEvaluator paymentSystemEvaluator = new PaymentSystemEvaluator();

    @Nested
    @DisplayName("evaluateSystem()")
    class EvaluateSystem{

        @Test
        void evaluateSystem_ShouldReturnINVALIDCARD_WhenStringIsEmpty() {
            String emptyString = "";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(emptyString);;
            assertEquals(actual, CreditCardType.INVALID_CARD);

        }

        @Test
        void evaluateSystem_ShouldReturnINVALIDCARD_WhenStringContainsOnlyWhiteSpaces() {
            String whiteSpaceString = "    ";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(whiteSpaceString);;
            assertEquals(actual, CreditCardType.INVALID_CARD);

        }

        @Test
        void evaluateSystem_ShouldReturnAMERICANEXPRESS_WhenPrefixContainsAmericanExpressValues(){
            String americanExpressCheck = "3411 7673 5452 1235";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(americanExpressCheck);
            assertEquals(actual, CreditCardType.AMERICAN_EXPRESS);
        }

        @Test
        void evaluateSystem_ShouldReturnJCB_WhenPrefixContainsJCBValues(){
            String jcbCheck = "3511 7673 5452 1235";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(jcbCheck);
            assertEquals(actual, CreditCardType.JCB);
        }

        @Test
        void evaluateSystem_ShouldReturnDINERSCLUB_WhenPrefixContainsDinersClubValues(){
            String dinersClubCheck = "3611 1234 1234 1235";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(dinersClubCheck);
            assertEquals(actual, CreditCardType.DINERS_CLUB);
        }

        @Test
        void evaluateSystem_ShouldReturnDiscover_WhenPrefixContainsDiscoverValues(){
            String dinersClubCheck = "6011 1234 1234 1235";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(dinersClubCheck);
            assertEquals(actual, CreditCardType.DISCOVER);
        }

        @Test
        void evaluateSystem_ShouldReturnVISA_WhenPrefixContainsVisaValues() {
            String visaCardCheck = "4234 1234 1234 1235";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(visaCardCheck);
            assertEquals(actual, CreditCardType.VISA);
        }

        @Test
        void evaluateSystem_ShouldReturnMASTERCARD_WhenPrefixContainsMastercardValues() {
            String mastercardCheck = "5134 1234 1234 1234";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(mastercardCheck);
            assertEquals(actual, CreditCardType.MASTERCARD);
        }


        @Test
        void evaluateSystem_ShouldReturnINVALIDCARD_WhenPrefixContainsNoMatchingValues() {
            String invalidCardCheck = "1414 1234 1234 1234";
            CreditCardType actual = paymentSystemEvaluator.evaluateSystem(invalidCardCheck);
            assertEquals(actual, CreditCardType.INVALID_CARD);
        }

    }
}
