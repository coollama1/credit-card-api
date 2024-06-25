package com.projects.cardvalidator.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projects.cardvalidator.enums.CreditCardType;
import com.projects.cardvalidator.response.CreditCardValidatorResponse;
import com.projects.cardvalidator.validator.PaymentSystemEvaluator;
import com.projects.cardvalidator.validator.Validator;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class CreditCardValidatorController {

    @Autowired
    private Validator validator;
    
    @Autowired
    private PaymentSystemEvaluator evaluator;
    
    
    @Operation(summary = "Perform Credit Card Validation and retrieve results")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", content = {@Content(mediaType = MediaType.APPLICATION_JSON_VALUE)
                                                                        }
                                        ,description = "Credit Card validation performed successfully"
                                        )
                          ,@ApiResponse(responseCode = "404", description = "Error occured when attempting to validate credit card")
                          }
                  )   
    @GetMapping(value="/validate-card")
    public CreditCardValidatorResponse validateCard(@RequestParam(value="cardNumber",
            defaultValue = "") String cardNumber) {
        List<String> errorMessages = validator.validate(cardNumber);
        CreditCardType cardType = evaluator.evaluateSystem(cardNumber);
        return new CreditCardValidatorResponse(errorMessages,cardType);
    }
    
}
