package com.restaurantManagement.restaurant.repositories;

import com.restaurantManagement.restaurant.entities.Restaurant;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RestaurantRepository extends CrudRepository<Restaurant,Integer> {

    Optional<Restaurant> findByName(String name);
}
