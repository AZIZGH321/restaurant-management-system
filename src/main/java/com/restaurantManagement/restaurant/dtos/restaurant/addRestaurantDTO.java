package com.restaurantManagement.restaurant.dtos.restaurant;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.restaurantManagement.restaurant.enums.Country;
import com.restaurantManagement.restaurant.enums.CuisineType;
import com.restaurantManagement.restaurant.enums.PriceRange;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;

@Getter
@Setter
@Builder
public class addRestaurantDTO {


    @NotBlank(message = "please enter a name")
    private String name;

    @NotBlank(message = "please enter an address")
    private String address;


    @NotBlank(message = "please enter a city")
    private String city;


    @NotBlank(message = "please enter a valid latitude")
    private Double latitude;


    @NotBlank(message = "please enter a valid longitude")
    private Double longitude;


    @NotBlank(message = "please enter a valid country name")
    private Country country;


    private CuisineType cuisineType;

    @Pattern(regexp = "^\\+?[0-9]{1,4}?[-.\\s]?(\\(?\\d{1,4}\\)?)[-.\\s]?\\d{1,4}[-.\\s]?\\d{1,9}$\n",
    message = "please enter a valid phoneNumber")
    private String phoneNumber;

    @Email(message = "please enter a valid email address")
    private String email;



    private PriceRange priceRange;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime openHour;

    @JsonFormat(pattern = "HH:mm")
    private LocalTime closeHour;
}
