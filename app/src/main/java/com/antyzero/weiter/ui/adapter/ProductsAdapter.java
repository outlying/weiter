package com.antyzero.weiter.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.network.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import static android.view.View.GONE;

/**
 * Created by tornax on 18.10.14.
 */
public class ProductsAdapter extends BaseAdapter {

    private final Resources resources;

    @Inject
    Picasso picasso;

    private final List<Product> productList;
    private final LayoutInflater layoutInflater;

    public ProductsAdapter( Context context, List<Product> productList ) {

        if( productList == null ) {
            throw new IllegalArgumentException( "List cannot be null" );
        }

        VendSpaceApplication.get( context ).objectGraph().inject( this );

        this.productList = productList;
        this.layoutInflater = LayoutInflater.from( context );
        this.resources = context.getResources();
    }

    @Override
    public int getCount() {
        return productList.size();
    }

    @Override
    public Product getItem( int position ) {
        return productList.get( position );
    }

    @Override
    public long getItemId( int position ) {
        return getItem( position ).getId();
    }

    @Override
    public View getView( int position, View convertView, ViewGroup parent ) {

        ViewHolder viewHolder;

        if(convertView == null){
            convertView = layoutInflater.inflate( R.layout.adapter_item_image, parent, false );

            viewHolder = new ViewHolder( convertView );

            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem( position );

        /*viewHolder.textViewProducts.setText( resources.getString( R.string.products, product.getProductCount() ) );
        viewHolder.textViewTitle.setText( product.getName() );

        picasso.load( product.getImageUrl() ).into( viewHolder.imageView );*/

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

            textViewProducts.setVisibility( GONE );
        }
    }
}
