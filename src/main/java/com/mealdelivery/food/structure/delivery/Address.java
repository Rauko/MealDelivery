package com.mealdelivery.food.structure.delivery;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String country;
    private String province;
    private String city;
    private String street;
    private String house;
    private String apartment;
}