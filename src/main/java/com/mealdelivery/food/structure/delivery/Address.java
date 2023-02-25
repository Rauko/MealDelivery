package com.mealdelivery.food.structure.delivery;

public class Address {
    String country;
    String province;
    String city;
    String street;
    String house;
    String apartment;


    public void address(String country, String province, String city, String street, String house, String apartment) {
        this.country = country;
        this.province = province;
        this.city = city;
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public void setGeneralAddressSettings(String country, String province, String city) {
        this.country = country;
        this.province = province;
        this.city = city;
    }

    public void setInCityAddress(String street, String house, String apartment) {
        this.street = street;
        this.house = house;
        this.apartment = apartment;
    }

    public String[] getInCityAddress(String street, String house, String apartment) {
        return new String[]{street, house, apartment};
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
