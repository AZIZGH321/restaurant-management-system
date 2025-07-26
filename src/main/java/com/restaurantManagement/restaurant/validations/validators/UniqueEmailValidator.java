package com.restaurantManagement.restaurant.validations.validators;

import com.restaurantManagement.restaurant.repositories.UserRepository;
import com.restaurantManagement.restaurant.validations.annotations.UniqueEmail;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail,String> {


    private final UserRepository userRepository;


    public UniqueEmailValidator(UserRepository userRepository){

        this.userRepository = userRepository;
    }
    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {


        if(email == null || email.trim().isEmpty()){
            return false;
        }

        return userRepository.findByEmail(email).isEmpty();
    }
}
