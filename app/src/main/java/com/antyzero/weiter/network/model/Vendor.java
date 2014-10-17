package com.antyzero.weiter.network.model;

/**
 * Represents single vendor
 */
public class Vendor {

    private long id;

    private String name;
    private String productCount;
    private String imageUrl;

    private float latitude;
    private float longitude;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getProductCount() {
        return productCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public float getLatitude() {
        return latitude;
    }

    public float getLongitude() {
        return longitude;
    }
}
