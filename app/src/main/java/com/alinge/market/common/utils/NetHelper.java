package com.alinge.market.common.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-24 17:29
 * Describe:
 */

public class NetHelper {

    //判断当前的网络是否可用
    public static boolean isNetworkAvailable(Context context) {
        boolean isAvailable = false;
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = cm.getActiveNetworkInfo();
        if (info != null && info.isConnected() && info.isAvailable()) {
            isAvailable = true;
        }
        return isAvailable;
    }

}
