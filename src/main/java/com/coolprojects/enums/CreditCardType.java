package com.coolprojects.enums;

import java.util.List;

public enum CreditCardType  {
    
    VISA("VISA", List.of("4")),
    MASTERCARD("Mastercard", List.of("51", "52", "53", "54", "55")),
    DINERS_CLUB("Diner's Club", List.of("36", "38")),
    DISCOVER("Discover", List.of("6011", "65")),
    JCB("JCB", List.of("35")),
    AMERICAN_EXPRESS("American Express", List.of("34", "37")),
    INVALID_CARD("Invalid", List.of());

    private final String paymentSystem;
    private final List<String> prefixValues;

    CreditCardType(String paymentSystem, List<String> prefixValues) {
        this.paymentSystem = paymentSystem;
        this.prefixValues = prefixValues;
    }

    public String getPaymentSystem() {
        return paymentSystem;
    }

    public List<String> getPrefixValues() {
        return prefixValues;
    }
}
