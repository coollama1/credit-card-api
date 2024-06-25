package com.projects.cardvalidator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.projects.cardvalidator.validator.CreditCardValidator;
import com.projects.cardvalidator.validator.PaymentSystemEvaluator;
import com.projects.cardvalidator.validator.Validator;

@SpringBootApplication
public class CreditCardApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CreditCardApiApplication.class, args);
	}
	
	@Bean
	public Validator validator() {
	    return new CreditCardValidator();
	}
	
	@Bean
	public PaymentSystemEvaluator evaluator() {
	    return new PaymentSystemEvaluator();
	}

}
