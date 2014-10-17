package com.antyzero.weiter;

import com.antyzero.weiter.network.VendorSpaceService;
import com.kontakt.sdk.android.manager.BeaconManager;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

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
    public VendorSpaceService providesVendorSpaceService(){

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("https://api.github.com")
                .build();

        return restAdapter.create(VendorSpaceService.class);
    }

    @Provides
    public BeaconManager providesBeaconManager(){
        return BeaconManager.newInstance( vendSpaceApplication );
    }
}
