package com.restaurantManagement.restaurant.entities;


import com.restaurantManagement.restaurant.enums.Country;
import com.restaurantManagement.restaurant.enums.CuisineType;
import com.restaurantManagement.restaurant.enums.PriceRange;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "restaurant")
public class Restaurant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false)
    private Country Country;

    @Enumerated(EnumType.STRING)
    private CuisineType cuisineType;


    @Column(nullable = false)
    private String phoneNumber;


    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Boolean isActive;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PriceRange priceRange;


    @Column(nullable = false)
    private LocalTime openingHour;

    @Column(nullable = false)
    private LocalTime closeHour;


    private String restaurantThumbnail;

}
