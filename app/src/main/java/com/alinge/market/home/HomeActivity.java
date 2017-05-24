package com.alinge.market.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alinge.market.R;
import com.alinge.market.common.log.Log;
import com.alinge.market.http.api.ApiService;
import com.alinge.market.http.interceptor.RequestInterceptor;
import com.alinge.market.http.tools.NetUtils;
import com.alinge.market.update.UpdateListEntity;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-02-28 10:14
 * Describe:
 */
public class HomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_layout);
        OkHttpClient.Builder builder= new OkHttpClient.Builder();
        builder.connectTimeout(5000, TimeUnit.MILLISECONDS);
        builder.addInterceptor(new RequestInterceptor());
       // builder.addNetworkInterceptor(new RequestInterceptor());
        File file=new File(getExternalCacheDir(),"response_cache");
        Cache cache=new Cache(file,1024*10*1024);
        builder.cache(cache);
        OkHttpClient client = builder.build();

        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
               .client(client)
                .baseUrl(NetUtils.HOST)
                .build();

        ApiService service = retrofit.create(ApiService.class);
        String token = NetUtils.getAppKey(this);
        String productId = NetUtils.PRODUCT_ID;
        String count = NetUtils.ITEM_COUNT;
        String machineName = NetUtils.getMachineType();
        String bannerCount = NetUtils.BANNER_COUNT;
        Map<String,Object> options=new HashMap<>();
        options.put("token",token);
        options.put("productId",productId);
        options.put("machineName",machineName);
        //Log.info("token:"+token+"  productId:"+productId+"  count:"+count+"  machineName:"+machineName+"  bannerCount"+bannerCount);
       //Call<HomeEntity> call = service.getHome(token, productId, bannerCount, count, machineName);
       //Call<BrandEntity> call= service.getBrand(token);
        Call<UpdateListEntity> call = service.getUpdateList(options);


       call.enqueue(new Callback<UpdateListEntity>() {
          @Override
          public void onResponse(Call<UpdateListEntity> call, Response<UpdateListEntity> response) {
              Log.info("response", response.body().getResult().getReturnMessage());
          }

          @Override
          public void onFailure(Call<UpdateListEntity> call, Throwable throwable) {
              Log.error("message:"+throwable.getMessage()+"  cause:"+throwable.getCause());
          }
      });


    }
}
