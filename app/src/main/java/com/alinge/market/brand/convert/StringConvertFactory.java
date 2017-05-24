package com.alinge.market.brand.convert;

import retrofit2.Converter;

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


}
