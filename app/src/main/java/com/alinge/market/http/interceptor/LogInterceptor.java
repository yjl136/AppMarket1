package com.alinge.market.http.interceptor;
import com.alinge.market.common.log.Log;
import java.io.IOException;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

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
        String url = request.url().toString();
        Log.info("LogInterceptor request url:"+url);
        Response response = chain.proceed(request);
        Log.info("LogInterceptor response:"+response.toString());
        return response;
    }
}
