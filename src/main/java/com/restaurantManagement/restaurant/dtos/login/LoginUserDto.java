package com.restaurantManagement.restaurant.dtos.login;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUserDto {

    @NotBlank(message = "please enter your email")
    @Email(message = "please enter valid email format")
    private String email;


    @NotBlank(message = "please enter your password")
    private String password;
}
