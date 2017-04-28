package com.alinge.market.http;

import com.alinge.market.common.log.Log;
import com.squareup.okhttp.Interceptor;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-04-28 15:46
 * Describe:
 */

public class LogInterceptor  implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        //获取请求request
        Request request=chain.request();
        String url = request.urlString();
        Log.info("request url:"+url);
        Response response = chain.proceed(request);
        return response;
    }
}
