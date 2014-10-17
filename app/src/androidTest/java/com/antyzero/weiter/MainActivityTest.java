package com.antyzero.weiter;

import android.test.ActivityInstrumentationTestCase2;

import com.antyzero.weiter.ui.MainActivity;

public class MainActivityTest extends ActivityInstrumentationTestCase2<MainActivity> {

    public MainActivityTest() {
        super( MainActivity.class );
    }

    public void testCreation() {
        getActivity();
    }
}