package com.antyzero.weiter.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.VendorSpaceService;
import com.antyzero.weiter.network.model.Vendor;
import com.antyzero.weiter.ui.adapter.VendorsAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import dagger.ObjectGraph;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * This is activity user will see most of the time
 */
public class MainActivity extends BaseActivity implements Callback<List<Vendor>>, AdapterView.OnItemClickListener {

    @Inject
    VendorSpaceService vendorSpaceService;

    private ObjectGraph activityGraph;

    private final List<Vendor> vendorList = new ArrayList<>();

    private VendorsAdapter vendorsAdapter;

    private ListView listView;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        VendSpaceApplication.get( this ).objectGraph().inject( this );

        vendorsAdapter = new VendorsAdapter( this, vendorList );

        listView = (ListView) findViewById( R.id.listView );
        listView.setAdapter( vendorsAdapter );
        listView.setOnItemClickListener( this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onStart() {
        super.onStart();
        vendorSpaceService.listVendors( 50.048289f, 19.959646f, this );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void success( List<Vendor> vendors, Response response ) {
        vendorList.clear();
        vendorList.addAll( vendors );
        vendorsAdapter.notifyDataSetChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void failure( RetrofitError error ) {
        Toast.makeText( this, "Brak sprzedawców w okolicy", Toast.LENGTH_SHORT ).show();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void onItemClick( AdapterView<?> parent, View view, int position, long id ) {
        VendorActivity.start( this, id );
    }
}