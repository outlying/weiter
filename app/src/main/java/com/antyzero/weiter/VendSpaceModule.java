package com.antyzero.weiter;

import com.antyzero.weiter.config.Config;
import com.antyzero.weiter.config.ConfigImpl;
import com.antyzero.weiter.network.VendorSpaceService;
import com.antyzero.weiter.ui.MainActivity;
import com.antyzero.weiter.ui.OrderActivity;
import com.antyzero.weiter.ui.VendorActivity;
import com.antyzero.weiter.ui.adapter.ProductsAdapter;
import com.antyzero.weiter.ui.adapter.VendorsAdapter;
import com.squareup.picasso.Picasso;

import dagger.Module;
import dagger.Provides;
import retrofit.RestAdapter;

/**
 * ...
 */
@Module(
        injects = {
                VendSpaceApplication.class,

                VendorsAdapter.class,
                ProductsAdapter.class,

                MainActivity.class,
                VendorActivity.class,
                OrderActivity.class
        },
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

        return restAdapter.create( VendorSpaceService.class );
    }

    @Provides
    public Picasso providesPicasso(){
        return Picasso.with( vendSpaceApplication );
    }

    @Provides
    public Config providesConfig(){
        return new ConfigImpl();
    }
}
