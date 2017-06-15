package com.alinge.market.download.adapter;

import android.content.Context;
import android.view.ViewGroup;
import com.alinge.http.downlaod.DownInfo;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;

/**
 * Created by WZG on 2016/10/21.
 */

public class DownAdapter extends RecyclerArrayAdapter<DownInfo> {

    public DownAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new DownHolder(parent);
    }

}