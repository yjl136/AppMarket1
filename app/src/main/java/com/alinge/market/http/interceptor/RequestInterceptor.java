package com.alinge.market.http.interceptor;


import android.content.Context;

import com.alinge.market.common.log.Log;
import com.alinge.market.common.utils.NetHelper;

import java.io.IOException;

import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-23 16:29
 * Describe:根据请求头是去获取缓存还是请求服务器获取新的数据
 */

public class RequestInterceptor implements Interceptor {
    private Context context;
    public RequestInterceptor(Context context) {
        this.context = context;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean hasNetwork = NetHelper.isNetworkAvailable(context);
        Log.info("newwork state:"+hasNetwork);
        Request request = chain.request();
        Request.Builder builder = request.newBuilder();
      //  builder.addHeader("token", NetUtils.getAppKey(context));
        if (hasNetwork) {
            //有网统一设置请求头
            builder.addHeader("Cache-Control","public,max-age=300");
        } else {
            //无网
            builder.cacheControl(CacheControl.FORCE_CACHE);

        }
        request = builder.build();
        Response response= chain.proceed(request);
        return response;
    }


}
