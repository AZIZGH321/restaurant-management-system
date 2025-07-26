package com.restaurantManagement.restaurant.repositories;


import com.restaurantManagement.restaurant.entities.Role;
import com.restaurantManagement.restaurant.enums.RoleEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role,Integer> {

    Optional<Role> findByName(RoleEnum name);


}
