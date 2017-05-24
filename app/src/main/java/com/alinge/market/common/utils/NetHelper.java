package com.alinge.market.common.utils;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-05-24 17:29
 * Describe:
 */

public class NetHelper {
    public static  boolean hashNetwork(){
       int value  = (int)(Math.random()*100);
        return value%2==0;
    }

}
