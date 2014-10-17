package com.antyzero.weiter;

import dagger.Module;

/**
 * ...
 */
@Module(
        injects = VendSpaceApplication.class
)
public class VendSpaceModule {

    private final VendSpaceApplication vendSpaceApplication;

    public VendSpaceModule(VendSpaceApplication vendSpaceApplication) {
        this.vendSpaceApplication = vendSpaceApplication;
    }
}
