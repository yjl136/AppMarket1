package com.alinge.market.brand.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-06 10:44
 * Describe:
 */

public class DividerItemDecorator extends RecyclerView.ItemDecoration{
    public DividerItemDecorator() {
        super();
    }

    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        Paint paint=new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.GRAY);
        for(int index = 0;index < childCount;index++){
            View child = parent.getChildAt(index);
            int left = 0;
            int top= child.getBottom();
            int right = parent.getWidth();
            int bottom=top+1;
            c.drawRect(left,top,right,bottom,paint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 10;
        outRect.right=10;
        outRect.bottom=2;
    }
}
