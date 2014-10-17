package com.antyzero.weiter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.antyzero.weiter.network.model.Vendor;

import java.util.List;

/**
 * Created by tornax on 18.10.14.
 */
public class VendorsAdapter extends BaseAdapter {

    private final List<Vendor> vendorList;
    private final LayoutInflater layoutInflater;

    public VendorsAdapter(Context context, List<Vendor> vendorList) {

        if(vendorList == null){
            throw new IllegalArgumentException( "List cannot be null" );
        }

        this.vendorList = vendorList;
        this.layoutInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return vendorList.size();
    }

    @Override
    public Vendor getItem( int position ) {
        return vendorList.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return getItem( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        // TODO ...

        return null;
    }
}
