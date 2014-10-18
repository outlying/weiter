package com.antyzero.weiter.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.antyzero.weiter.R;
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
    public static final String EXTRA_VENDOR_ID = "EXTRA_VENDOR_ID";

    @Inject
    VendorSpaceService vendorSpaceService;

    private List<Order> orders;

    private long vendorId;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        Intent intent = getIntent();

        if(!intent.hasExtra( EXTRA_ORDERS )){
            throw new IllegalArgumentException( "Missing orders extra" );
        }

        if(!intent.hasExtra( EXTRA_VENDOR_ID )){
            throw new IllegalArgumentException( "Missing vendor ID" );
        }

        orders = intent.getParcelableArrayListExtra( EXTRA_ORDERS );
        vendorId = intent.getLongExtra( EXTRA_VENDOR_ID, Long.MIN_VALUE );

        if(orders.isEmpty()){
            Toast.makeText( this, "Lista zamówień nie może być pusta", Toast.LENGTH_SHORT ).show();
            finish();
        }

        VendSpaceApplication.get( this ).objectGraph().inject( this );

        setContentView( R.layout.activity_order );
    }

    /**
     * Valid start
     *
     * @param context
     * @param orders
     */
    public static void start( Context context, ArrayList<Order> orders, long vendorId ){

        Intent intent = new Intent( context, OrderActivity.class );

        intent.putExtra( EXTRA_VENDOR_ID, vendorId );
        intent.putParcelableArrayListExtra( EXTRA_ORDERS, orders );

        context.startActivity( intent );
    }
}
