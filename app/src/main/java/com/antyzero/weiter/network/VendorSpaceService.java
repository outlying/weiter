package com.antyzero.weiter.network;

import com.antyzero.weiter.network.model.Vendor;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Query;

/**
 * ...
 */
public interface VendorSpaceService {

    @GET( "/vendors" )
    List<Vendor> listVendors( @Query("lng") float lng, @Query( "lat" ) float lat );
}
