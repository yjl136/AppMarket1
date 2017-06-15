package com.alinge.market.http;
import android.content.Context;
import com.alinge.market.brand.entity.BrandEntity;
import com.alinge.market.common.entity.SoftwareListEntity;
import com.alinge.market.home.entity.HomeEntity;
import com.alinge.market.http.api.ApiService;
import com.alinge.market.http.tools.NetUtils;
import java.util.HashMap;
import java.util.Map;
import retrofit2.Call;
import retrofit2.Retrofit;
import rx.Observable;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 11:42
 * Describe:
 */

public class Api {
    public static String HOST = "http://market.kyd2002.cn/";
    private static String TOKEN_PARM = "token";
    private static String PRODUCT_ID_PARM = "productId";
    private static String BRAND_ID_PARM = "brandId";
    private static String PAGE_INDEX_PARM = "pageIndex";
    private static String PAGE_SIZE_PARM = "pageSize";
    private static String MACHINE_NAME_PARM = "machineName";
    public static String PRODUCT_ID = "3";
    public static String ITEM_COUNT = "15";
    public static String BANNER_COUNT = "6";
    public static String PAGE_SIZE = "10";
    private static String MACHINE_NAME = NetUtils.getMachineType();

    public static ApiService getApiService(Context context) {
        Retrofit retrofit = MarketRetrofit.getRetrofit(context);
        ApiService service = retrofit.create(ApiService.class);
        return service;
    }

    public static Call<HomeEntity> getHome(Context context) {
        String token = NetUtils.getAppKey(context);
        ApiService service = getApiService(context);
        return service.getHome(token, PRODUCT_ID, BANNER_COUNT, ITEM_COUNT, MACHINE_NAME);
    }


    public static Call<BrandEntity> getBrand(Context context) {
        String token = NetUtils.getAppKey(context);
        ApiService service = getApiService(context);
        return service.getBrand(token);
    }

  /*  public static Call<SoftwareListEntity> getSoftListByBrand(Context context, int pageIndex, int brandId) {
        String token = NetUtils.getAppKey(context);
        ApiService service = getApiService(context);
        Map<String, Object> query = new HashMap<>();
        query.put(MACHINE_NAME_PARM, MACHINE_NAME);
        query.put(PRODUCT_ID_PARM, PRODUCT_ID);
        query.put(PAGE_SIZE_PARM, PAGE_SIZE);
        query.put(BRAND_ID_PARM, brandId);
        query.put(PAGE_INDEX_PARM, pageIndex);
        query.put(TOKEN_PARM, token);
        return service.getSoftListByBrand(query);
    }*/
    public static Observable<SoftwareListEntity> getSoftListByBrand(Context context, int pageIndex,int pageSize ,int brandId) {
        String token = NetUtils.getAppKey(context);
        ApiService service = getApiService(context);
        Map<String, Object> query = new HashMap<>();
        query.put(MACHINE_NAME_PARM, MACHINE_NAME);
        query.put(PRODUCT_ID_PARM, PRODUCT_ID);
        query.put(PAGE_SIZE_PARM, pageSize);
        query.put(BRAND_ID_PARM, brandId);
        query.put(PAGE_INDEX_PARM, pageIndex);
        query.put(TOKEN_PARM, token);
        return service.getSoftListByBrand(query);
    }
}
