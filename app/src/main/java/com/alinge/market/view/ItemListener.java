package com.alinge.market.view;

import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 15:16
 * Describe:
 */

public class ItemListener extends RecyclerView.SimpleOnItemTouchListener {
    private ItemClickListener mItemClickListener;
    private ItemLongClickListener mItemLongClickListener;
    private GestureDetectorCompat mGestureDetector;
    public ItemListener(final RecyclerView mRecyclerView) {
        mGestureDetector = new GestureDetectorCompat(mRecyclerView.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child != null && mItemClickListener!=null){
                    mItemClickListener.onItemClick(mRecyclerView,child,mRecyclerView.getChildAdapterPosition(child));
                }
                return true;
            }
            @Override
            public void onLongPress(MotionEvent e) {
                View child = mRecyclerView.findChildViewUnder(e.getX(),e.getY());
                if(child != null && mItemLongClickListener!=null){
                    mItemLongClickListener.onItemLongClick(mRecyclerView,child,mRecyclerView.getChildAdapterPosition(child));
                }
            }
        });
    }
    public void setItemClickListener(ItemClickListener listener){
        this.mItemClickListener = listener;
    }
    public void setmItemLongClickListener(ItemLongClickListener listener){
        this.mItemLongClickListener=listener;
    }
    @Override
    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
        return mGestureDetector.onTouchEvent(e);
    }
   public  interface ItemClickListener {
        public void onItemClick(ViewGroup parent, View child, int postion);
    }
    public  interface ItemLongClickListener{
        public void onItemLongClick(ViewGroup parent, View child, int postion);
    }

}
