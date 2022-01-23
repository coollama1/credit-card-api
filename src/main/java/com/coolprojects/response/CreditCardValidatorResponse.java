package com.coolprojects.response;

import java.util.List;

import com.coolprojects.enums.CreditCardType;

public class CreditCardValidatorResponse {

    public boolean isCardValid;
    public List<String> errorMessages;
    public String cardType;
    
    public CreditCardValidatorResponse(List<String> errorMessages, CreditCardType cardType) {
        isCardValid = errorMessages.size() == 0;
        this.errorMessages = errorMessages;
        this.cardType = cardType.getPaymentSystem();
        
    }
}
