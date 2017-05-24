package com.alinge.market.home.convert;

import com.alinge.market.common.log.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;


/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-03 10:35
 * Describe:
 */
public class ResponseBody2JSONObject implements Converter<ResponseBody,JSONObject> {
    @Override
    public JSONObject convert(ResponseBody responseBody) throws IOException {
        JSONObject obj = null;
        try{
            obj=new JSONObject(responseBody.string());
        }catch (JSONException e){
            Log.error(e.getMessage());
        }finally{
            responseBody.close();
        }
        return obj;
    }
}
