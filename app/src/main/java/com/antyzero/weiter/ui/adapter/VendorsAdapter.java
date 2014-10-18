package com.antyzero.weiter.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.LongSparseArray;
import android.util.SparseLongArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.model.Vendor;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Display vendors list
 */
public class VendorsAdapter extends BaseAdapter {

    private final Resources resources;

    @Inject
    Picasso picasso;

    private final List<Vendor> vendorList;
    private final LayoutInflater layoutInflater;

    /**
     * ...
     *
     * @param context
     * @param vendorList
     */
    public VendorsAdapter( Context context, List<Vendor> vendorList ) {

        if( vendorList == null ) {
            throw new IllegalArgumentException( "List cannot be null" );
        }

        VendSpaceApplication.get( context ).objectGraph().inject( this );

        this.vendorList = vendorList;
        this.layoutInflater = LayoutInflater.from( context );
        this.resources = context.getResources();
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

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate( R.layout.adapter_item_vendor, parent, false );

            viewHolder = new ViewHolder( convertView );

            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Vendor vendor = getItem( position );

        viewHolder.textViewProducts.setText( resources.getString( R.string.products, vendor.getProductCount() ) );
        viewHolder.textViewTitle.setText( vendor.getName() );

        picasso.load( vendor.getImageUrl() ).into( viewHolder.imageView );

        return convertView;
    }

    /**
     * ViewHolder pattern
     */
    private static class ViewHolder {

        private final TextView textViewTitle;
        private final TextView textViewProducts;
        private final ImageView imageView;

        private ViewHolder( View view ) {
            textViewTitle = (TextView) view.findViewById( R.id.textViewTitle );
            textViewProducts = (TextView) view.findViewById( R.id.textViewProducts );
            imageView = (ImageView) view.findViewById( R.id.imageView );
        }
    }
}
