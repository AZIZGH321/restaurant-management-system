package com.restaurantManagement.restaurant.validations.annotations;


import com.restaurantManagement.restaurant.validations.validators.PasswordValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {

    String message() default "Password must be at least 8 characters long, contain a digit and a special character";
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default {};
}
