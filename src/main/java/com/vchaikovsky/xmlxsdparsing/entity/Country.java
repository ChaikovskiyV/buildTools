package com.vchaikovsky.xmlxsdparsing.entity;

public enum Country {
    BELARUS("Belarus"),
    RUSSIA("Russia"),
    POLAND("Poland"),
    UKRAINE("Ukraine"),
    USA("USA"),
    CHINA("China");

    private String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}