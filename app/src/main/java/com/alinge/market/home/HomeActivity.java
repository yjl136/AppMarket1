package com.alinge.market.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.alinge.market.R;
import com.alinge.market.common.log.Log;
import com.alinge.market.home.convert.JSONObjectConvertFactory;
import com.alinge.market.home.entity.HomeEntity;
import com.alinge.market.http.ApiService;
import com.alinge.market.http.NetUtils;

import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

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



        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(JSONObjectConvertFactory.create())

                .baseUrl(NetUtils.HOST)
                .build();

        ApiService service = retrofit.create(ApiService.class);
        String token = NetUtils.getAppKey(this);
        String productId = NetUtils.PRODUCT_ID;
        String count = NetUtils.ITEM_COUNT;
        String machineName = NetUtils.getMachineType();
        String bannerCount = NetUtils.BANNER_COUNT;
        Log.info("token:"+token+"  productId:"+productId+"  count:"+count+" machineName"+machineName+" bannerCount"+bannerCount);

        Call<HomeEntity> call = service.getHome(token, productId, bannerCount, count, machineName);
       // Call<BrandEntity>  call= service.getBrand(token);

        call.enqueue(new Callback<HomeEntity>() {
            @Override
            public void onResponse(Response<HomeEntity> response, Retrofit retrofit) {
                Log.info("url", response.raw().request().url().toString());
                Log.info("response", response.body().getResult().getReturnMessage());
            }

            @Override
            public void onFailure(Throwable throwable) {
                Log.warn(throwable.getMessage()+"cause:"+throwable.getCause());
            }
        });


    }
}
