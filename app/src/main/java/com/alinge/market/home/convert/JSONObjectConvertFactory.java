package com.alinge.market.home.convert;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-03 10:32
 * Describe:
 */
public class JSONObjectConvertFactory extends Converter.Factory {
    public static JSONObjectConvertFactory create(){
        return new JSONObjectConvertFactory();
    }
    private JSONObjectConvertFactory() {
        super();
    }

    @Override
    public Converter<ResponseBody, ?> fromResponseBody(Type type, Annotation[] annotations) {
        return new ResponseBody2JSONObject();
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return super.toRequestBody(type, annotations);
    }
}
