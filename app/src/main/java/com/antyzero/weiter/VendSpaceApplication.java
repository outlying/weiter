package com.antyzero.weiter;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

/**
 * Project application
 */
public class VendSpaceApplication extends Application {

    private ObjectGraph objectGraph;

    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create( new VendSpaceModule( this ) );
        objectGraph.inject( this );
    }

    /**
     *
     *
     * @param modules
     * @return
     */
    public ObjectGraph createScopedGraph( Object... modules ) {
        return objectGraph.plus( modules );
    }

    /**
     * ...
     *
     * @return
     */
    public ObjectGraph objectGraph(){
        return objectGraph;
    }

    /**
     * Access VendSpaceApplication instance
     *
     * @param context for accessing
     * @return VendSpaceApplication object
     */
    public static VendSpaceApplication get(Context context) {
        return (VendSpaceApplication) context.getApplicationContext();
    }
}
