package com.restaurantManagement.restaurant.services;


import com.restaurantManagement.restaurant.dtos.signup.RegisterUserDto;
import com.restaurantManagement.restaurant.entities.Role;
import com.restaurantManagement.restaurant.enums.RoleEnum;
import com.restaurantManagement.restaurant.entities.User;
import com.restaurantManagement.restaurant.repositories.RoleRepository;
import com.restaurantManagement.restaurant.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    public UserService(UserRepository userRepository,
                       RoleRepository roleRepository,
                       PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers(){

        List<User> users = new ArrayList<>();

        userRepository.findAll().forEach(users::add);
        return users;
    }

    public User createAdministrator(RegisterUserDto input){

        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.ADMIN);

        if(optionalRole.isEmpty()){
            return null;
        }

        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .role(optionalRole.get())
                .build();

        return userRepository.save(user);
    }
}
