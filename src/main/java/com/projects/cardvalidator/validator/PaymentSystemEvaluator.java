package com.projects.cardvalidator.validator;

import com.projects.cardvalidator.enums.CreditCardType;
import com.projects.cardvalidator.utilities.Utilities;

public class PaymentSystemEvaluator {
    /**
     * Returns the proper card type depending on the format of the card number parameter
     *
     * @param cardNumber credit card number to be checked for proper length
     * @return returns proper card type
     */
    public CreditCardType evaluateSystem(String cardNumber) {
        for (CreditCardType cardType : CreditCardType.values()) {
            for (String currentPrefix : cardType.getPrefixValues()) {
                String cardPrefix = Utilities.getPrefixDigits(cardNumber, currentPrefix.length());
                if (cardPrefix.equals(currentPrefix)) {
                    return cardType;
                }
            }
        }
        return CreditCardType.INVALID_CARD;

    }
}
