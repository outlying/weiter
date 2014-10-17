package com.antyzero.weiter;

import com.antyzero.weiter.config.Config;
import com.antyzero.weiter.config.ConfigImpl;
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

        Config config = providesConfig();

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint( config.getWebserviceUrl() )
                .build();

        return restAdapter.create(VendorSpaceService.class);
    }

    @Provides
    public Config providesConfig(){
        return new ConfigImpl();
    }

    @Provides
    public BeaconManager providesBeaconManager(){
        return BeaconManager.newInstance( vendSpaceApplication );
    }
}
