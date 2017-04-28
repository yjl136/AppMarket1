package com.alinge.market.http;


import com.alinge.market.brand.entity.BrandEntity;
import com.alinge.market.home.entity.HomeEntity;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;

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



}
