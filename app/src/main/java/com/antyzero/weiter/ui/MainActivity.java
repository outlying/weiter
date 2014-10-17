package com.antyzero.weiter.ui;

import android.os.Bundle;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.model.Vendor;
import com.kontakt.sdk.android.manager.BeaconManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * This is activity user will see most of the time
 */
public class MainActivity extends BaseActivity {

    @Inject
    BeaconManager beaconManager;

    private ObjectGraph activityGraph;

    private final List<Vendor> vendorList = new ArrayList<>();

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        activityGraph = VendSpaceApplication.get( this ).createScopedGraph( new UiModule( this ) );
        activityGraph.inject( this );


    }
}