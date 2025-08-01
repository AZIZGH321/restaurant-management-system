package com.restaurantManagement.restaurant.repositories;

import com.restaurantManagement.restaurant.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface UserRepository extends CrudRepository<User,Integer> {

    Optional<User> findByEmail(String email);

    Optional<Boolean> existsByEmail(String email);
}
