package com.antyzero.weiter.network.model;

/**
 *
 */
public class Product {

    private String name;
    private String currency;
    private String imageUrl;

    private float price;

    private long id;

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
