package com.restaurantManagement.restaurant.controllers;


import com.restaurantManagement.restaurant.dtos.login.LoginResponse;
import com.restaurantManagement.restaurant.dtos.login.LoginUserDto;
import com.restaurantManagement.restaurant.dtos.signup.RegisterUserDto;
import com.restaurantManagement.restaurant.dtos.signup.UserResponseDTO;
import com.restaurantManagement.restaurant.entities.User;
import com.restaurantManagement.restaurant.services.AuthenticationService;
import com.restaurantManagement.restaurant.services.JwtService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService,
                                    AuthenticationService authenticationService){

        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDTO> register(@RequestBody @Valid RegisterUserDto registerUserDto){

        UserResponseDTO registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody @Valid LoginUserDto loginUserDto){

        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        LoginResponse loginResponse = LoginResponse.builder().token(jwtToken).expiresIn(jwtService.getExpirationTime())
                .build();

        return ResponseEntity.ok(loginResponse);
    }
}
