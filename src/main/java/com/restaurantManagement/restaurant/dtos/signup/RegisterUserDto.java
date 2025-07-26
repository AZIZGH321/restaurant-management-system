package com.restaurantManagement.restaurant.dtos.signup;


import com.restaurantManagement.restaurant.validations.annotations.UniqueEmail;
import com.restaurantManagement.restaurant.validations.annotations.ValidFullName;
import com.restaurantManagement.restaurant.validations.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterUserDto {

    @NotBlank(message = "email must be not empty.")
    @Email(message = "please enter a valid email.")
    @UniqueEmail
    private String email;


    @NotBlank(message = "password must be not empty.")
    @ValidPassword
    private String password;

    @NotBlank(message = "password must be not empty.")
    @ValidFullName
    private String fullName;
}
