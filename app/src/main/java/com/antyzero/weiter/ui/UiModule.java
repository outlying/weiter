package com.antyzero.weiter.ui;

import android.app.Activity;

import com.antyzero.weiter.VendSpaceModule;

import dagger.Module;

@Module(
        addsTo = VendSpaceModule.class,
        injects = {
                MainActivity.class,
        },
        library = true
)
public class UiModule {

    private final Activity activity;

    public UiModule( Activity activity ) {
        this.activity = activity;
    }
}

