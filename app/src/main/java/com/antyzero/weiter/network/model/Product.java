package com.antyzero.weiter.network.model;

/**
 *
 */
public class Product {

    private long id;
    private String name;
    private float price;
    private String currency;

    private String imageUrl;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public String getCurrency() {
        return currency;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
