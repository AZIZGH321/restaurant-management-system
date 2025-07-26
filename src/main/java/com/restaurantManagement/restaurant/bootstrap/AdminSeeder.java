package com.restaurantManagement.restaurant.bootstrap;


import com.restaurantManagement.restaurant.dtos.signup.RegisterUserDto;
import com.restaurantManagement.restaurant.entities.Role;
import com.restaurantManagement.restaurant.enums.RoleEnum;
import com.restaurantManagement.restaurant.entities.User;
import com.restaurantManagement.restaurant.repositories.RoleRepository;
import com.restaurantManagement.restaurant.repositories.UserRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AdminSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;


    public AdminSeeder(
            RoleRepository roleRepository,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ){
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {


        this.createSuperAdministrator();
    }

    private void createSuperAdministrator(){

        RegisterUserDto userDto = new RegisterUserDto();

        userDto.setFullName("Super Admin");
        userDto.setEmail("superadmin@email.com");
        userDto.setPassword("mypassword");


        Optional<Role> optionalRole = roleRepository.findByName(RoleEnum.SUPER_ADMIN);
        Optional<User> optionalUser = userRepository.findByEmail(userDto.getEmail());

        if(optionalRole.isEmpty() || optionalUser.isPresent()){

            return;
        }

        User user  = User.builder()
                .fullName(userDto.getFullName())
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword()))
                .role(optionalRole.get())
                .build();

        userRepository.save(user);
    }
}
