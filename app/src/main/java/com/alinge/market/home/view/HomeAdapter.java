package com.alinge.market.home.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.alinge.market.R;
import com.alinge.market.brand.entity.BrandItemEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-25 14:21
 * Describe:
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ItemViewHolder> {
    public final static int NOVALID_POSITION = -1;
    private List<BrandItemEntity> lists = new ArrayList<>();
    private Context context;

    public HomeAdapter(Context context) {
        this.context = context;
    }

    public void setLists(List<BrandItemEntity> lists) {
        this.lists = lists;
        notifyDataSetChanged();
    }

    public int getBrandID(int position){
        BrandItemEntity entity = lists.get(position);
        if(entity == null){
            return NOVALID_POSITION;
        }
        return entity.getId();
    }
    public String getBrandImg(int position){
        BrandItemEntity entity = lists.get(position);
        if(entity == null){
            return " ";
        }
        return entity.getBrandImg();
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        BrandItemEntity entity = lists.get(position);
        Glide.with(context)
                .load(entity.getBrandImg())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .placeholder(R.drawable.apk_screen)
                .into(holder.brandIv);
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_brand, parent, false);
        ButterKnife.bind(this, view);
        ItemViewHolder vh = new ItemViewHolder(view);
        return vh;
    }

    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.brandIv)
        public ImageView brandIv;

        public ItemViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
