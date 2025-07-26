package com.restaurantManagement.restaurant.enums;

import java.util.Optional;

public enum CuisineType {


    ITALIAN("Italian"),
    MEXICAN("Mexican"),
    CHINESE("Chinese"),
    JAPANESE("Japanese"),
    INDIAN("Indian"),
    AMERICAN("American"),
    THAI("Thai"),
    FRENCH("French"),
    MEDITERRANEAN("Mediterranean"),
    VEGETARIAN("Vegetarian"),
    VEGAN("Vegan"),
    KOREAN("Korean"),
    VIETNAMESE("Vietnamese"),
    SPANISH("Spanish"),
    GREEK("Greek"),
    LEBANESE("Lebanese"),
    BRAZILIAN("Brazilian"),
    PIZZA("Pizza"),
    BURGER("Burger"),
    SEAFOOD("Seafood"),
    STEAKHOUSE("Steakhouse"),
    SUSHI("Sushi"),
    BBQ("BBQ"),
    FUSION("Fusion"),
    CAFE("Cafe"),
    BAKERY("Bakery"),
    DESSERT("Dessert"),
    BAR("Bar"),
    INTERNATIONAL("International"),
    FAST_FOOD("Fast Food");

    private final String displayName;

    CuisineType(String displayName){

        this.displayName = displayName;
    }


    public static Optional<CuisineType> fromString(String text) {
        for (CuisineType type : CuisineType.values()) {
            if (type.displayName.equalsIgnoreCase(text)) {
                return Optional.of(type);
            }
        }
        return Optional.empty();
    }
}
