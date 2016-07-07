package com.skystars.library.utils;

import android.os.Build;

/**
 * 文 件 名: VersionUtils
 * 创 建 人: TOO15
 * 创建日期: 2016/7/7 14:10
 * 修改时间：
 * 修改备注：
 */
public class VersionUtils {
    /**
     * 判斷叛本是否為5.0以上
     */
    public static boolean chedkVersion(){
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP;
    }
}