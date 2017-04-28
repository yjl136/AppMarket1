package com.alinge.market.brand.convert;

import com.squareup.okhttp.ResponseBody;

import java.io.IOException;

import retrofit.Converter;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-03 09:22
 * Describe:     将responsebody直接转换为String
 */
public class ResponseBody2String implements Converter<ResponseBody,String>{
    @Override
    public String convert(ResponseBody responseBody) throws IOException {
       String result;
        try {
            result=responseBody.string();
        }finally {
            responseBody.close();
        }
        return result;
    }
}