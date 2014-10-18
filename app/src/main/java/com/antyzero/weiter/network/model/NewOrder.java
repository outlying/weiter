package com.antyzero.weiter.network.model;

import com.antyzero.weiter.model.Order;

import java.util.ArrayList;
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
     * @param orders
     * @return
     */
    public static NewOrder create( String customer, String comment, long vendorId, List<Order> orders ){

        NewOrder newOrder = new NewOrder();

        newOrder.customer = customer;
        newOrder.comment = comment;
        newOrder.vendorId = vendorId;

        newOrder.products = new ArrayList<>();

        for(Order order : orders){

            ProductOrdered productOrdered = new ProductOrdered();

            productOrdered.productId = order.getProductId();
            productOrdered.amount = order.getAmount();

            newOrder.products.add( productOrdered );
        }

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
