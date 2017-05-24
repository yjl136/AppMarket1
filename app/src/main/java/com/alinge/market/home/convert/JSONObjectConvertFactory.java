package com.alinge.market.home.convert;
import retrofit2.Converter;

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


}
