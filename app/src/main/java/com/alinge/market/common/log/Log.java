package com.alinge.market.common.log;

import android.text.TextUtils;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-03 09:47
 * Describe:
 */
public class Log {
    private static  boolean isDebug = true;
    private static  String TAG = "market_lin";

    public static void info(String msg){
        if(isDebug){
            if(!TextUtils.isEmpty(msg)){
                android.util.Log.i(TAG,msg);
            }else{
                android.util.Log.i(TAG,"msg==null");
            }
        }
    }
    public static void info(String prefix,String msg){
        if(isDebug){
            if(TextUtils.isEmpty(prefix)){
                throw new NullPointerException("prefix is null");
            }
            if(!TextUtils.isEmpty(msg)){
                android.util.Log.i(TAG,prefix+": "+msg);
            }else{
                android.util.Log.i(TAG,"msg==null");
            }
        }
    }

    public static void warn(String msg){
        if(isDebug){
            if(!TextUtils.isEmpty(msg)){
                android.util.Log.w(TAG,msg);
            }else{
                android.util.Log.w(TAG,"msg==null");
            }
        }
    }


    public static void error(String msg){
        if(isDebug){
            if(!TextUtils.isEmpty(msg)){
                android.util.Log.e(TAG,msg);
            }else{
                android.util.Log.e(TAG,"msg==null");
            }
        }
    }
}
