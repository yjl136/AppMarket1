package com.alinge.market.http.cache;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.ExternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;

/**
 * Project Name:   AppMarket
 * Create By:      aLinGe
 * Create Time:    2017-06-01 11:01
 * Describe:
 */

public class MarketGlideModule implements GlideModule {
    /**
     * Lazily apply options to a {@link GlideBuilder} immediately before the Glide singleton is
     * created.
     * <p>
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param builder The {@link GlideBuilder} that will be used to create Glide.
     */
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        String imgCacheDir = "image-cache";
        int cacheSize = 200*1024*1024;
        builder.setDiskCache(new ExternalCacheDiskCacheFactory(context,imgCacheDir,cacheSize));
        int memorySize = 10*1024*1024;
        builder.setMemoryCache(new LruResourceCache(memorySize));

    }

    /**
     * Lazily register components immediately after the Glide singleton is created but before any requests can be
     * started.
     * <p>
     * <p>
     * This method will be called once and only once per implementation.
     * </p>
     *
     * @param context An Application {@link Context}.
     * @param glide   The newly created Glide singleton.
     */
    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
