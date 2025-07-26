package com.restaurantManagement.restaurant.enums;

public enum PriceRange {

    BUDGET("budget"),
    MID("mid"),
    PREMIUM("premium");

    private final String displayName;


    PriceRange(String displayName){

        this.displayName = displayName;
    }


}
