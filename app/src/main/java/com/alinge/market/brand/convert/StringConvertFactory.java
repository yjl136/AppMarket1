package com.alinge.market.brand.convert;

import com.alinge.market.common.log.Log;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit.Converter;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-01 13:44
 * Describe:
 */
public class StringConvertFactory  extends Converter.Factory {
    public static StringConvertFactory create(){
        return new StringConvertFactory();
    }
    private StringConvertFactory() {
        super();
    }

    @Override
    public Converter<ResponseBody, String> fromResponseBody(Type type, Annotation[] annotations) {

        Log.info(type.toString());
        return new ResponseBody2String();
    }

    @Override
    public Converter<?, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return super.toRequestBody(type, annotations);
    }

}
