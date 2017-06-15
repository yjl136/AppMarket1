package com.alinge.market.http.interceptor;

import com.alinge.market.common.log.Log;

import java.io.IOException;
import java.util.List;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-25 09:24
 * Describe:
 */

public class HeaderInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        printRequestHeaders(request);
        Response response = chain.proceed(request);
        printResponseHeaders(response);
        return response;
    }


    private void printRequestHeaders(Request request){
        Log.error("----HeaderInterceptor-------request-------start-----");
        printHeaders(request.headers());
        Log.error("----HeaderInterceptor-------request-------end-------");
    }
    private void printResponseHeaders(Response response){
        Log.error("----HeaderInterceptor-------response-------start-----");
        printHeaders(response.headers());
        Log.error("----HeaderInterceptor-------response-------end-------");
    }

    private void printHeaders(Headers headers) {
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
            Log.info(name + " " + builder.toString());
            builder.delete(0, builder.length() - 1);
        }
    }
}
