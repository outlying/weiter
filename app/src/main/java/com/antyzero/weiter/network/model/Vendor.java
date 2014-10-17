package com.antyzero.weiter.network.model;

import java.util.List;

import javax.inject.Inject;

/**
 * Represents single vendor
 */
public class Vendor {

    private long id;

    private String name;
    private String imageUrl;

    private float latitude;
    private float longitude;

    private Integer productCount;

    private List<Product> products;

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getProductCount() {
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
