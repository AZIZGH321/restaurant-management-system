package com.restaurantManagement.restaurant.bootstrap;


import com.restaurantManagement.restaurant.entities.Role;
import com.restaurantManagement.restaurant.enums.RoleEnum;
import com.restaurantManagement.restaurant.repositories.RoleRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

@Component
public class RoleSeeder implements ApplicationListener<ContextRefreshedEvent> {

    private final RoleRepository roleRepository;

    public RoleSeeder(RoleRepository roleRepository){

        this.roleRepository = roleRepository;
    }


    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        this.loadRoles();

    }

    private void loadRoles(){
        RoleEnum[] roleNames = new RoleEnum[] {RoleEnum.USER,RoleEnum.ADMIN,RoleEnum.SUPER_ADMIN};

        Map<RoleEnum,String> roleDescriptionMap = Map.of(
                RoleEnum.USER,"Default user role",
                RoleEnum.ADMIN,"Administrator role",
                RoleEnum.SUPER_ADMIN,"Super Administrator role"
        );

        Arrays.stream(roleNames).forEach((roleName)->{
            Optional<Role> optionalRole = roleRepository.findByName(roleName);

            optionalRole.ifPresentOrElse(System.out::println,()->{
                Role roleToCreate = Role.builder().name(roleName)
                        .description(roleDescriptionMap.get(roleName)).build();

                roleRepository.save(roleToCreate);
            });
        });
    }
}
