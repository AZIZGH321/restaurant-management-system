package com.restaurantManagement.restaurant.dtos.login;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class LoginResponse {

    private String token;

    private long expiresIn;

}
