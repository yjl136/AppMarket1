package com.alinge.market.common.view;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.alinge.market.R;
import com.alinge.market.common.log.Log;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-06 16:47
 * Describe:下拉刷新
 */

public class PullLayout extends FrameLayout {

    public RecyclerView mRecyclerView;

    public TextView loadTv;
    private  boolean isTop;
    private int state;
    private ViewDragHelper helper;
    public PullLayout(@NonNull Context context) {
        this(context,null);

    }

    public PullLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }
    public PullLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
         helper= ViewDragHelper.create(this,1.0f,new  ViewDragHelper.Callback(){
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                Log.info("tryCaptureView:"+child);
                return true;
            }
        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.info("onInterceptTouchEvent:"+isTop);

        return helper.shouldInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       helper.processTouchEvent(event);
        return  true;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        mRecyclerView = (RecyclerView) findViewById(R.id.rv);
        loadTv = (TextView) findViewById(R.id.loadTV);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager lm = (LinearLayoutManager) recyclerView.getLayoutManager();
                int position =  lm.findFirstCompletelyVisibleItemPosition();
                if(newState == RecyclerView.SCROLL_STATE_DRAGGING && position==0){
                    isTop = true;
                }else{
                    isTop =false;
                }
                Log.info("newState:"+newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

               // Log.info("dx:"+dx+"  dy:"+dy);

            }
        });
    }
}
