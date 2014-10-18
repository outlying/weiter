package com.antyzero.weiter.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.VendorSpaceService;
import com.antyzero.weiter.network.model.Product;
import com.antyzero.weiter.network.model.Vendor;
import com.antyzero.weiter.ui.adapter.ProductsAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

import static android.view.View.GONE;

/**
 * Created by tornax on 18.10.14.
 */
public class VendorActivity extends BaseActivity {

    public static final String EXTRA_VENDOR_ID = "EXTRA_VENDOR_ID";

    private long vendorId;

    private ImageView imageView;

    private TextView textViewTitle;
    private TextView textViewProducts;

    private ListView listView;

    private List<Product> productList = new ArrayList<>();

    private ProductsAdapter productsAdapter;

    @Inject
    Picasso picasso;

    @Inject
    VendorSpaceService vendorSpaceService;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );

        if(!getIntent().hasExtra( EXTRA_VENDOR_ID )){
            throw new IllegalArgumentException( "Missing vendor ID" );
        }

        vendorId = getIntent().getLongExtra( EXTRA_VENDOR_ID, -1l );

        productsAdapter = new ProductsAdapter( this, productList );

        VendSpaceApplication.get( this ).objectGraph().inject( this );

        setContentView( R.layout.activity_vendor );

        imageView = (ImageView) findViewById( R.id.imageView );

        textViewTitle = (TextView) findViewById( R.id.textViewTitle );
        textViewProducts = (TextView) findViewById( R.id.textViewProducts );
        textViewProducts.setVisibility( GONE );

        listView = (ListView) findViewById( R.id.listView );
        listView.setAdapter( productsAdapter );
    }

    @Override
    protected void onStart() {
        super.onStart();

        vendorSpaceService.vendor( vendorId, new VendorCallback() );
        vendorSpaceService.vendorProducts( vendorId, new ProductListCallback() );
    }

    /**
     * Base method to start activity
     *
     * @param context ...
     * @param vendorId identifies vendor
     */
    public static void start( Context context, long vendorId){

        Intent intent = new Intent(context, VendorActivity.class);

        intent.putExtra( EXTRA_VENDOR_ID, vendorId );

        context.startActivity( intent );
    }

    /**
     * Vendor details
     */
    private class VendorCallback implements Callback<Vendor> {

        @Override
        public void success( Vendor vendor, Response response ) {
            textViewTitle.setText( vendor.getName() );
            picasso.load( vendor.getImageUrl() ).into( imageView );
        }

        @Override
        public void failure( RetrofitError error ) {
            Toast.makeText( VendorActivity.this, "Dane sprzedawcy niedostępne", Toast.LENGTH_SHORT ).show();
        }
    }

    /**
     * Products for vendor
     */
    private class ProductListCallback implements Callback<List<Product>> {

        @Override
        public void success( List<Product> products, Response response ) {
            productList.clear();
            productList.addAll( products );
            productsAdapter.notifyDataSetChanged();
        }

        @Override
        public void failure( RetrofitError error ) {
            Toast.makeText( VendorActivity.this, "Nie można pobrać produktów dla sprzedawcy", Toast.LENGTH_SHORT ).show();
        }
    }
}
