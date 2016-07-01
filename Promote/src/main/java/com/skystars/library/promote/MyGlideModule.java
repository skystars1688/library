package com.skystars.library.promote;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskLruCacheWrapper;
import com.bumptech.glide.module.GlideModule;

import java.io.File;

/**
 * 文 件 名: MyGlideModel
 * 创 建 人: TOO15
 * 创建日期: 2016/6/30 11:06
 * 修改时间：
 * 修改备注：
 */
public class MyGlideModule implements GlideModule {
    @Override
    public void applyOptions(final Context context, GlideBuilder builder) {
        // Apply options to the builder here.

        builder.setDiskCache(new DiskCache.Factory() {
                    @Override
                    public DiskCache build() {
                        // Careful: the external cache directory doesn't enforce permissions
                        File cacheLocation = new File(context.getExternalCacheDir(), "cache_dir_name");
                        cacheLocation.mkdirs();
                        return DiskLruCacheWrapper.get(cacheLocation, 25000000);
                    }
                });
    }
    @Override
    public void registerComponents(Context context, Glide glide) {
        // register ModelLoaders here.
    }
}