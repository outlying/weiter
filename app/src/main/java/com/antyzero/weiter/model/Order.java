package com.antyzero.weiter.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Simple order object
 */
public class Order implements Parcelable {

    private long vendorId;
    private long productId;

    private int amount;

    public Order( Parcel source ) {
        vendorId = source.readLong();
        productId = source.readLong();
        amount = source.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel( Parcel dest, int flags ) {
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