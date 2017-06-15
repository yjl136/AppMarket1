package com.alinge.market.home.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-05 14:08
 * Describe:
 */

public class HomeItemDecoration extends RecyclerView.ItemDecoration {
    private final  int TAGWIDTH = 50;
    private final int LEFT = 10;
    private final int RIGHT = 10;
    private final int TOP = 0;
    private final int BOTTOM = 15;
    private RecyclerView.Adapter adapter;
    private Paint paint;
    public HomeItemDecoration(RecyclerView.Adapter adapter) {
        super();
        this.adapter = adapter;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.WHITE);
    }


    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int count = parent.getChildCount();
        int left = parent.getPaddingLeft()+LEFT;
        int right = parent.getWidth()-parent.getPaddingRight()-RIGHT;
        for(int index=0;index < count;index++){
            View view=parent.getChildAt(index);
            int top = view.getBottom()+TOP;
            int bottom = top+BOTTOM;
            c.drawRect(left,top,right,bottom,paint);
        }

    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int childCount = parent.getChildCount();
        for(int index=0;index < childCount;index++){
            View child=parent.getChildAt(index);
            int position = parent.getChildLayoutPosition(child);
            if(position%2 == 0){
                Paint leftPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
                leftPaint.setColor(Color.GRAY);
                int left = child.getLeft();
                int top  = child.getTop();
                int right = left + TAGWIDTH;
                int bottom= top + TAGWIDTH;
                c.drawRect(left,top,right,bottom,leftPaint);
               float text = paint.measureText(""+position);
                leftPaint.setColor(Color.GREEN);
                c.drawText(""+position,left+TAGWIDTH/2-text/2,top+TAGWIDTH/2+text/2,leftPaint);
            }

        }

    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.bottom = BOTTOM;
        outRect.right = RIGHT;
        outRect.left = LEFT;
        outRect.top = TOP;

    }
}
