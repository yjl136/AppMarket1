package com.alinge.market.http.api;


import com.alinge.market.brand.entity.BrandEntity;
import com.alinge.market.common.entity.SoftwareListEntity;
import com.alinge.market.home.entity.HomeEntity;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;
import rx.Observable;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-02-28 10:17
 * Describe:
 */
public interface ApiService {

    @GET("API/Market/MarketIndex")
    Call<HomeEntity> getHome(@Query("token") String token,@Query("productId") String productId, @Query("bannerCount") String bannerCount, @Query("count") String count, @Query("machineName") String machineName);

    @GET("API/Market/GetBrand")
    Call<BrandEntity> getBrand(@Query("token") String token);

    @HTTP(method = "GET", path = "API/Market/UpdateList", hasBody = false)
    Call<SoftwareListEntity> getUpdateList(@QueryMap Map<String,Object> option);

  /*  @HTTP(method = "GET", path = "API/Market/GetSoftListByBrand", hasBody = false)
    Call<SoftwareListEntity> getSoftListByBrand(@QueryMap Map<String,Object> option);*/
    @HTTP(method = "GET", path = "API/Market/GetSoftListByBrand", hasBody = false)
    Observable<SoftwareListEntity> getSoftListByBrand(@QueryMap Map<String,Object> option);

    /*断点续传下载接口*/
    @Streaming/*大文件需要加入这个判断，防止下载过程中写入到内存中*/
    @GET
    Observable<ResponseBody> download(@Header("RANGE") String start, @Url String url);



}
