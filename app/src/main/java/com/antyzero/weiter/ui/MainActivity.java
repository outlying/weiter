package com.antyzero.weiter.ui;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.kontakt.sdk.android.configuration.ForceScanConfiguration;
import com.kontakt.sdk.android.configuration.MonitorPeriod;
import com.kontakt.sdk.android.connection.OnServiceBoundListener;
import com.kontakt.sdk.android.device.Beacon;
import com.kontakt.sdk.android.device.Region;
import com.kontakt.sdk.android.manager.BeaconManager;

import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;

/**
 * This is activity user will see most of the time
 */
public class MainActivity extends BaseActivity {

    private static final int REQUEST_CODE_ENABLE_BLUETOOTH = 1;

    @Inject
    BeaconManager beaconManager;

    private ObjectGraph activityGraph;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        activityGraph = VendSpaceApplication.get( this ).createScopedGraph( new UiModule( this ) );
        activityGraph.inject( this );

        beaconManager = BeaconManager.newInstance(this);
        beaconManager.setMonitorPeriod( MonitorPeriod.MINIMAL );
        beaconManager.setForceScanConfiguration( ForceScanConfiguration.DEFAULT );
        beaconManager.registerMonitoringListener( new BeaconManager.MonitoringListener() {

            @Override
            public void onMonitorStart() {}

            @Override
            public void onMonitorStop() {}

            @Override
            public void onBeaconsUpdated(final Region region, final List<Beacon> beacons) {}

            @Override
            public void onBeaconAppeared(final Region region, final Beacon beacon) {}

            @Override
            public void onRegionEntered(final Region region) {}

            @Override
            public void onRegionAbandoned(final Region region) {}
        });
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
        if(!beaconManager.isBluetoothEnabled()) {
            final Intent intent = new Intent( BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, REQUEST_CODE_ENABLE_BLUETOOTH);
        } else {
            connect();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStop() {
        super.onStop();
        beaconManager.stopMonitoring();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        beaconManager.disconnect();
        beaconManager = null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE_ENABLE_BLUETOOTH) {
            if(resultCode == Activity.RESULT_OK) {
                connect();
            } else {
                Toast.makeText( this, "Bluetooth not enabled", Toast.LENGTH_LONG ).show();
                getActionBar().setSubtitle("Bluetooth not enabled");
            }
            return;
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * Bind to bluetooth service
     */
    private void connect() {

        try {

            beaconManager.connect(new OnServiceBoundListener() {
                @Override
                public void onServiceBound() {
                    try {
                        beaconManager.startMonitoring(Region.EVERYWHERE);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}