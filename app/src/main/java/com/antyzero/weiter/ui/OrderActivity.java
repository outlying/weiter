package com.antyzero.weiter.ui;

import android.os.Bundle;

import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.VendorSpaceService;

import javax.inject.Inject;

/**
 * Created by tornax on 18.10.14.
 */
public class OrderActivity extends BaseActivity {

    @Inject
    VendorSpaceService vendorSpaceService;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        VendSpaceApplication.get( this ).objectGraph().inject( this );
    }
}
