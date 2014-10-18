package com.antyzero.weiter.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.model.Order;
import com.antyzero.weiter.network.VendorSpaceService;
import com.antyzero.weiter.network.model.NewOrder;
import com.antyzero.weiter.network.model.NewOrderConfirm;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by tornax on 18.10.14.
 */
public class OrderActivity extends BaseActivity implements View.OnClickListener, Callback<NewOrderConfirm> {

    public static final String EXTRA_ORDERS = "EXTRA_ORDERS";
    public static final String EXTRA_VENDOR_ID = "EXTRA_VENDOR_ID";

    private EditText editTextCustomer;
    private EditText editTextComment;

    private Button button;

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

        editTextComment = (EditText) findViewById( R.id.editTextComment );
        editTextCustomer = (EditText) findViewById( R.id.editTextCustomer );

        button = (Button) findViewById( R.id.button );
        button.setOnClickListener( this );
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

    @Override
    public void onClick( View v ) {

        vendorSpaceService.order(
                NewOrder.create(
                        editTextCustomer.getText().toString(),
                        editTextComment.getText().toString(),
                        vendorId,
                        orders),
                this );
    }

    @Override
    public void success( NewOrderConfirm newOrderConfirm, Response response ) {
        Toast.makeText( this, "Twoje zamówienie zostało przyjęte", Toast.LENGTH_SHORT ).show();
        finish();
    }

    @Override
    public void failure( RetrofitError error ) {
        Toast.makeText( this, "Zamówienie nie zostało przyjęte", Toast.LENGTH_SHORT ).show();
    }
}
