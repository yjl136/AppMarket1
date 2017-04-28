package com.alinge.market.jni;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-03-16 16:50
 * Describe:
 */
public class JniTest {
    static {
        System.loadLibrary("libapp");
    }
    public native int getVersion( );
}
