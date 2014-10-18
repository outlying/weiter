package com.antyzero.weiter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple order object
 */
public class Order implements Parcelable {

    private String name;

    private long vendorId;
    private long productId;

    private int amount;

    public Order( Parcel source ) {
        name = source.readString();
        vendorId = source.readLong();
        productId = source.readLong();
        amount = source.readInt();
    }

    /**
     *
     *
     * @param vendorId
     * @param productId
     * @param amount
     */
    public Order( long vendorId, long productId, int amount, String name ) {

        this.vendorId = vendorId;
        this.productId = productId;
        this.amount = amount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
        dest.writeString( name );
        dest.writeLong( vendorId );
        dest.writeLong( productId );
        dest.writeInt( amount );
    }

    public static final Creator<Order> CREATOR = new Creator<Order>() {

        @Override
        public Order createFromParcel( Parcel source ) {
            return new Order(source);
        }

        @Override
        public Order[] newArray( int size ) {
            return new Order[size];
        }
    };
}
