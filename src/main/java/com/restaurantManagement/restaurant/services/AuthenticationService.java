package com.restaurantManagement.restaurant.services;


import com.restaurantManagement.restaurant.dtos.login.LoginUserDto;
import com.restaurantManagement.restaurant.dtos.signup.RegisterUserDto;
import com.restaurantManagement.restaurant.dtos.signup.UserResponseDTO;
import com.restaurantManagement.restaurant.entities.Role;
import com.restaurantManagement.restaurant.enums.RoleEnum;
import com.restaurantManagement.restaurant.entities.User;
import com.restaurantManagement.restaurant.mappers.UserMapper;
import com.restaurantManagement.restaurant.repositories.RoleRepository;
import com.restaurantManagement.restaurant.repositories.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthenticationService {

    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;


    private final AuthenticationManager authenticationManager;


    public AuthenticationService(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder,
                                 AuthenticationManager authenticationManager,
                                 RoleRepository roleRepository,UserMapper userMapper){

        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.roleRepository = roleRepository;

    }

    public UserResponseDTO signup(RegisterUserDto userDto){

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.USER);

        if(optionalRole.isEmpty()){

            return null;
        }

        User user = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(optionalRole.get())
                .build();



        return userMapper.userToUserResponseDTO(this.userRepository.save(user));

    }

    public User authenticate(LoginUserDto userDto){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userDto.getEmail(),
                        userDto.getPassword()
                )
        );

        return userRepository.findByEmail(userDto.getEmail())
                .orElseThrow();
    }
}
