package com.alinge.test;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-07 09:52
 * Describe:
 */

public class DragLayout extends FrameLayout {
    private final String TAG = "DragLayout";
    private ViewDragHelper helper;
    private TextView bottomTv;
    private ScrollView topSv;
    private double percent = 0.5;
    private int bottomHeight;
    private LinearLayout topLayout;
    private float velocity = 1500;
    private boolean isLoading = false;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            Log.i(TAG, "handleMessage");
            isLoading = false;
            helper.smoothSlideViewTo(topLayout, 0, -bottomHeight);
            invalidate();
        }
    };


    public DragLayout(@NonNull Context context) {
        this(context, null);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        helper = ViewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override
            public boolean tryCaptureView(View child, int pointerId) {
                return child == topLayout;
            }

            @Override
            public int clampViewPositionVertical(View child, int top, int dy) {
                int newTop = (int) (child.getTop() + percent * dy);
                return newTop;
            }

            @Override
            public void onViewPositionChanged(View changedView, int left, int top, int dx, int dy) {
                super.onViewPositionChanged(changedView, left, top, dx, dy);
                Log.i(TAG, "onViewPositionChanged");
                if (top >= 0) {
                    bottomTv.setText("释放刷新");
                } else {
                    bottomTv.setText("下拉刷新");
                }
            }

            @Override
            public void onViewReleased(View releasedChild, float xvel, float yvel) {
                super.onViewReleased(releasedChild, xvel, yvel);
                if (velocity <= yvel || releasedChild.getTop() >= 0) {
                    isLoading = true;
                    helper.settleCapturedViewAt(0, 0);
                } else {
                    helper.settleCapturedViewAt(0, -bottomHeight);
                }
                invalidate();
            }

        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        return super.dispatchTouchEvent(ev);
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        Log.i(TAG, "computeScroll");
        if (helper.continueSettling(true)) {
            invalidate();
        } else {
            if (isLoading) {
                bottomTv.setText("获取数据中");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(3000);
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                bottomTv.setText("获取数据成功");
                            }
                        });
                        SystemClock.sleep(300);
                        handler.sendEmptyMessage(0);
                    }
                }).start();
            }
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        topLayout.layout(left, -bottomHeight, right, bottom);
    }


    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(TAG,"onInterceptTouchEvent:"+ev.getAction());
     /*  if(ev.getAction()==MotionEvent.ACTION_MOVE && topSv.getScrollY()==0){

            return  true;
        }*/
        return helper.shouldInterceptTouchEvent(ev);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
       helper.processTouchEvent(event);
        return true;
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        bottomHeight = bottomTv.getMeasuredHeight();
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        topLayout = (LinearLayout) findViewById(R.id.topLayout);
        bottomTv = (TextView) findViewById(R.id.bottom);
        topSv = (ScrollView) findViewById(R.id.top);
    }
}
