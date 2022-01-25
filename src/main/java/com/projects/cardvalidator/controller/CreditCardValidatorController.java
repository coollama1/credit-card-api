package com.projects.cardvalidator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cardvalidator.enums.CreditCardType;
import com.projects.cardvalidator.response.CreditCardValidatorResponse;
import com.projects.cardvalidator.validator.CreditCardValidator;
import com.projects.cardvalidator.validator.PaymentSystemEvaluator;
import com.projects.cardvalidator.validator.Validator;

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
