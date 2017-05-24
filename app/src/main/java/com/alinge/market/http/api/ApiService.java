package com.alinge.market.http.api;


import com.alinge.market.brand.entity.BrandEntity;
import com.alinge.market.home.entity.HomeEntity;
import com.alinge.market.update.UpdateListEntity;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-02-28 10:17
 * Describe:
 */
public interface ApiService {

    @GET("API/Market/MarketIndex")
    Call<HomeEntity> getHome(@Query("token") String token, @Query("productId") String productId, @Query("bannerCount") String bannerCount, @Query("count") String count, @Query("machineName") String machineName);


    @GET("API/Market/GetBrand")
    Call<BrandEntity> getBrand(@Query("token") String token);

    @Headers("Cache-Control: public, max-age=3600")
    @HTTP(method = "GET", path = "API/Market/UpdateList", hasBody = false)
    Call<UpdateListEntity> getUpdateList(@QueryMap Map<String,Object> option);




}
