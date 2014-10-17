package com.antyzero.weiter.network;

import com.antyzero.weiter.network.model.Vendor;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Query;

/**
 * ...
 */
public interface VendorSpaceService {

    @GET( "/vendors" )
    void listVendors( @Query("lng") float lng, @Query( "lat" ) float lat, Callback<List<Vendor>> callback );
}
