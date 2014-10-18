package com.antyzero.weiter.network;

import com.antyzero.weiter.network.model.NewOrder;
import com.antyzero.weiter.network.model.NewOrderConfirm;
import com.antyzero.weiter.network.model.Product;
import com.antyzero.weiter.network.model.Vendor;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

/**
 * ...
 */
public interface VendorSpaceService {

    @GET( "/vendor" )
    void listVendors( @Query( "lat" ) float lat, @Query("lng") float lng, Callback<List<Vendor>> callback );

    @GET( "/vendor/{id}" )
    void vendor( @Path("id") long vendorId, Callback<Vendor> callback );

    @GET( "/vendor/{id}/product" )
    void vendorProducts( @Path("id") long vendorId, Callback<List<Product>> callback );

    @POST( "/order" )
    void order( @Body NewOrder newOrder, Callback<NewOrderConfirm> callback );
}
