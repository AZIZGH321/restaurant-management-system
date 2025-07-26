package com.restaurantManagement.restaurant.validations.validators;

import com.restaurantManagement.restaurant.validations.annotations.ValidPassword;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PasswordValidator implements ConstraintValidator<ValidPassword,String> {
    @Override
    public boolean isValid(String password, ConstraintValidatorContext constraintValidatorContext) {

        return password != null && password.matches("^(?=.*\\d)(?=.*[@#$%^&+=!]).*$") && password.length()>=8;

    }
}
