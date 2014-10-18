package com.antyzero.weiter.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * Created by tornax on 18.10.14.
 */
public class OrderAdapter extends BaseAdapter {

    private final LayoutInflater layoutInflater;

    public OrderAdapter( Context context ) {

        layoutInflater = LayoutInflater.from( context );
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem( int position ) {
        return null;
    }

    @Override
    public long getItemId( int position ) {
        return 0;
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {
        return null;
    }
}
