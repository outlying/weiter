package com.antyzero.weiter.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.model.Order;
import com.antyzero.weiter.network.VendorSpaceService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by tornax on 18.10.14.
 */
public class OrderActivity extends BaseActivity {

    public static final String EXTRA_ORDERS = "EXTRA_ORDERS";
    @Inject
    VendorSpaceService vendorSpaceService;

    private List<Order> orders;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        if(!getIntent().hasExtra( EXTRA_ORDERS )){
            throw new IllegalArgumentException( "Missing orders extra" );
        }

        orders = getIntent().getParcelableArrayListExtra( EXTRA_ORDERS );

        if(orders.isEmpty()){
            Toast.makeText( this, "Lista zamówień nie może być pusta", Toast.LENGTH_SHORT ).show();
            finish();
        }

        VendSpaceApplication.get( this ).objectGraph().inject( this );
    }

    /**
     * Valid start
     *
     * @param context
     * @param orders
     */
    public static void start( Context context, ArrayList<Order> orders ){

        Intent intent = new Intent( context, OrderActivity.class );

        intent.putParcelableArrayListExtra( EXTRA_ORDERS, orders );

        context.startActivity( intent );
    }
}
