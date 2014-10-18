package com.antyzero.weiter.network.model;

import java.util.List;

/**
 *
 */
public class NewOrder {

    private String customer;
    private String comment;

    private long vendorId;

    private List<ProductOrdered> products;

    /**
     * Creates request
     *
     * @param customer
     * @param comment
     * @param vendorId
     * @return
     */
    public static NewOrder create(String customer, String comment, long vendorId ){

        NewOrder newOrder = new NewOrder();

        // TODO

        return newOrder;
    }

    /**
     *
     */
    public static final class ProductOrdered {

        private long productId;

        private int amount;
    }
}
