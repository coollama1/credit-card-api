package com.projects.cardvalidator.validator;

import java.util.List;

public interface Validator {

    List<String> validate(String validationString);
}