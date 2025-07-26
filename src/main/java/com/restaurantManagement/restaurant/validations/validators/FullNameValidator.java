package com.restaurantManagement.restaurant.validations.validators;

import com.restaurantManagement.restaurant.validations.annotations.ValidFullName;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class FullNameValidator implements ConstraintValidator<ValidFullName,String> {

    private static final String FULL_NAME_REGEX = "^[a-zA-Z\\s'-]{2,50}$";


    @Override
    public boolean isValid(String fullName, ConstraintValidatorContext constraintValidatorContext) {

        if(fullName == null || fullName.trim().isEmpty()){
            return false;
        }

        return fullName.matches(FULL_NAME_REGEX);
    }
}
