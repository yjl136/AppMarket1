package com.alinge.market.http.interceptor;


import com.alinge.market.common.log.Log;
import com.alinge.market.common.utils.NetHelper;

import java.io.IOException;
import java.util.List;

import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-23 16:29
 * Describe:
 */

public class RequestInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        boolean hasNetwork = NetHelper.hashNetwork();
        Request request = chain.request();
        Log.info("---------------RequestInterceptor-----------start--------");
        printRequestHeaders(request);
        Log.info("---------------RequestInterceptor-----------end----------");
        Response response = null;
        if (hasNetwork) {
            //有网
            response = chain.proceed(request);
            Log.info("network");
        } else {
            //无网
            Request newRequest = request.newBuilder()
                    .cacheControl(CacheControl.FORCE_NETWORK)
                    .build();
            response = chain.proceed(newRequest);
            Log.info(" not network");
        }
        return response;
    }

    private void printRequestHeaders(Request request) {
        Headers headers = request.headers();
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < headers.size(); index++) {
            String name = headers.name(index);
            List<String> values = headers.values(name);
            builder.append("[");
            for (String value : values) {
                builder.append(value);
                builder.append("  ");
            }
            builder.append("]");
            Log.info("RequestInterceptor-----" + name + " " + builder.toString());
            builder.delete(0, builder.length() - 1);
        }
    }
}
