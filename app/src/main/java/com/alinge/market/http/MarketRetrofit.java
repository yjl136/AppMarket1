package com.alinge.market.http;

import android.content.Context;

import com.alinge.market.http.interceptor.HeaderInterceptor;
import com.alinge.market.http.interceptor.LogInterceptor;
import com.alinge.market.http.interceptor.RequestInterceptor;
import com.alinge.market.http.interceptor.ResponseInterceptor;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-25 10:49
 * Describe:
 */

public final class MarketRetrofit {
    private static Retrofit retrofit = null;
    public static Retrofit getRetrofit(Context context) {
        if (retrofit == null) {
            OkHttpClient client = getOkhttp(context);
            retrofit = new Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory( RxJavaCallAdapterFactory.create())
                    .client(client)
                    .baseUrl(Api.HOST)
                    .build();
        }
        return retrofit;
    }

    private static OkHttpClient getOkhttp(Context context) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.retryOnConnectionFailure(true);
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS);
        //添加应用和网络拦截器
        builder.addInterceptor(new LogInterceptor());
        builder.addInterceptor(new RequestInterceptor(context));
        builder.addInterceptor(new HeaderInterceptor());
        builder.addNetworkInterceptor(new ResponseInterceptor());

        //设置缓存
        File file = new File(context.getExternalCacheDir(), "response-cache");
        Cache cache = new Cache(file, 1024 * 10 * 1024);
        builder.cache(cache);
        OkHttpClient client = builder.build();
        return client;
    }



}
