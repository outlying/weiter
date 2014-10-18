package com.antyzero.weiter.ui.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.util.LongSparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.antyzero.weiter.R;
import com.antyzero.weiter.VendSpaceApplication;
import com.antyzero.weiter.model.Order;
import com.antyzero.weiter.network.model.Product;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by tornax on 18.10.14.
 */
public class ProductsAdapter extends BaseAdapter {

    private final Resources resources;

    private final long vendorId;

    @Inject
    Picasso picasso;

    private final List<Product> productList;
    private final LayoutInflater layoutInflater;

    private final LongSparseArray<Integer> orderCount = new LongSparseArray<>();

    /**
     * @param context
     * @param productList
     */
    public ProductsAdapter( Context context, long vendorId, List<Product> productList ) {
        this.vendorId = vendorId;

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

        if( convertView == null ) {
            convertView = layoutInflater.inflate( R.layout.adapter_item_product_order, parent, false );

            viewHolder = new ViewHolder( convertView );

            convertView.setTag( viewHolder );
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        Product product = getItem( position );

        picasso.load( product.getImageUrl() ).into( viewHolder.imageView );

        viewHolder.textViewTitle.setText( product.getName() );

        final long productId = product.getId();

        if( orderCount.indexOfKey( productId ) < 0 ) {
            viewHolder.textViewCounter.setText( "0" );
        } else {
            viewHolder.textViewCounter.setText( String.valueOf( orderCount.get( productId ) ) );
        }

        viewHolder.buttonDown.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                Integer oldValue = orderCount.get( productId );

                if( oldValue == null ){
                    oldValue = 0;
                }

                if( oldValue > 0 ) {
                    orderCount.put( productId, oldValue - 1 );
                }

                notifyDataSetChanged();
            }
        } );

        viewHolder.buttonUp.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {

                Integer oldValue = orderCount.get( productId );

                if(oldValue == null){
                    oldValue = 0;
                }

                orderCount.put( productId, oldValue + 1 );

                notifyDataSetChanged();
            }
        } );

        return convertView;
    }

    public Order[] collectOrder(){

        Order[] orders = new Order[orderCount.size()];

        for( int i = 0; i < orderCount.size(); i++ ){
            long productId = orderCount.keyAt( i );
            Integer amount = orderCount.valueAt( i );
            orders[i] = new Order(vendorId, productId, amount );
        }

        return orders;
    }

    /**
     * ViewHolder pattern
     */
    private static class ViewHolder {

        private final TextView textViewTitle;
        private final TextView textViewCounter;

        private final ImageView imageView;

        private final View buttonDown;
        private final View buttonUp;

        private ViewHolder( View view ) {

            textViewTitle = (TextView) view.findViewById( R.id.textViewTitle );
            textViewCounter = (TextView) view.findViewById( R.id.textViewCounter );

            imageView = (ImageView) view.findViewById( R.id.imageView );

            buttonDown = view.findViewById( R.id.buttonDown );
            buttonUp = view.findViewById( R.id.buttonUp );
        }
    }
}
