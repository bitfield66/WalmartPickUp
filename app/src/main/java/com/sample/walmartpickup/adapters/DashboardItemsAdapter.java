package com.sample.walmartpickup.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.sample.walmartpickup.R;
import com.sample.walmartpickup.WalmartPickupBaseActivity;

/**
 * Created by Anil.Jain on 4/30/2016.
 */
public class DashboardItemsAdapter extends RecyclerView.Adapter<DashboardItemsAdapter.WalmartItemsViewHolder> {

    @Override
    public WalmartItemsViewHolder onCreateViewHolder(final ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.dashboard_list_item_cell, viewGroup, false);
        WalmartItemsViewHolder walmartItemsViewHolder = new WalmartItemsViewHolder(v);
        walmartItemsViewHolder.btnAddToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WalmartPickupBaseActivity)viewGroup.getContext()).setCartCount();
            }
        });
        return walmartItemsViewHolder;
    }

    @Override
    public void onBindViewHolder(WalmartItemsViewHolder personViewHolder, int position) {
        personViewHolder.tvItemPrice.setText("Item " + position);
        personViewHolder.tvItemDesc.setText("Item " + position);
    }

    @Override
    public int getItemCount() {
        return 30;
    }

    public static class WalmartItemsViewHolder extends RecyclerView.ViewHolder {
        TextView tvItemPrice, tvItemDesc;
        ImageView ivItemImage;
        Button btnAddToCart;

        WalmartItemsViewHolder(View itemView) {
            super(itemView);
            tvItemPrice = (TextView) itemView.findViewById(R.id.tvItemPrice);
            tvItemDesc = (TextView) itemView.findViewById(R.id.tvItemDesc);
            ivItemImage = (ImageView) itemView.findViewById(R.id.ivItemImage);
            btnAddToCart = (Button) itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
