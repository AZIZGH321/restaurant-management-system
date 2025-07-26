package com.restaurantManagement.restaurant.validations.annotations;


import com.restaurantManagement.restaurant.validations.validators.FullNameValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = FullNameValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidFullName {

    String message() default "Full Name must only containt Alphabetic Characters";
    Class<?>[] groups() default {};
    Class<?extends Payload>[] payload() default {};
}
