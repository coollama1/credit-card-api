package com.coolprojects.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.coolprojects.enums.CreditCardType;
import com.coolprojects.response.CreditCardValidatorResponse;
import com.coolprojects.validator.CreditCardValidator;
import com.coolprojects.validator.PaymentSystemEvaluator;
import com.coolprojects.validator.Validator;

@RestController
public class CreditCardValidatorController {

    @GetMapping(value="/validate-card")
    public CreditCardValidatorResponse validateCard(@RequestParam(value="cardNumber",
            defaultValue = "") String cardNumber) {
        Validator validator = new CreditCardValidator();
        PaymentSystemEvaluator evaluator = new PaymentSystemEvaluator();
        List<String> errorMessages = validator.validate(cardNumber);
        CreditCardType cardType = evaluator.evaluateSystem(cardNumber);
        return new CreditCardValidatorResponse(errorMessages,cardType);
    }
    
}
