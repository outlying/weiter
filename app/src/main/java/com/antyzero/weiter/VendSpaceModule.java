package com.antyzero.weiter;

import com.kontakt.sdk.android.manager.BeaconManager;

import dagger.Module;
import dagger.Provides;

/**
 * ...
 */
@Module(
        injects = VendSpaceApplication.class,
        library = true
)
public class VendSpaceModule {

    private final VendSpaceApplication vendSpaceApplication;

    public VendSpaceModule(VendSpaceApplication vendSpaceApplication) {
        this.vendSpaceApplication = vendSpaceApplication;
    }

    @Provides
    public BeaconManager providesBeaconManager(){
        return BeaconManager.newInstance( vendSpaceApplication );
    }
}
