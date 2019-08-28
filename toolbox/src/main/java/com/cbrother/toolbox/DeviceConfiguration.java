package com.cbrother.toolbox;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.DisplayMetrics;

/**
 * @author hmc
 * @date 2018/9/12 10:57
 */
public class DeviceConfiguration {


    /**
     * 获取屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getWindowWidth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.widthPixels;
    }


    /**
     * 获取屏幕高度
     *
     * @param context
     * @return
     */
    public static int getWindowHeigth(Context context) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return displayMetrics.heightPixels;
    }

    /**
     * 获取版本名字
     *
     * @param context
     * @return
     */
    public static String getVersionName(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "1.0";
    }

    /**
     * 获取版本号
     *
     * @param context 上下文
     * @return 版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }


    /**
     * 获取包名
     *
     * @param context 上下文
     * @return 包名
     */
    public static String packageName(Context context) {
        String packgename = "unknow";
        try {
            packgename = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).packageName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return packgename;
    }


    /**
     * 获取状态栏的高度
     *
     * @param context 上下文
     * @return 状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    /**
     * 获取导航栏高度
     *
     * @param context
     * @return
     */
    public static int getNavBarHeight(Context context) {
        int resourceId = 0;
        int rid = context.getResources().getIdentifier("config_showNavigationBar", "bool", "android");
        if (rid != 0) {
            resourceId = context.getResources().getIdentifier("navigation_bar_height", "dimen", "android");
            return context.getResources().getDimensionPixelSize(resourceId);
        } else {
            return 0;
        }
    }

    /**
     * 屏幕的像素密度
     *
     * @param context 上下文
     * @return 像素密度
     */
    public static float displayDenisty(Context context) {
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.density;
    }


    /**
     * 获取设备信息配置
     *
     * @param context
     * @return
     */
    public static String deviceConfig(Context context) {
        return "\n设置的配置信息:  " +
                "\n屏幕分辨率    " + getWindowWidth(context) + "×" + (getWindowHeigth(context) + getNavBarHeight(context))+
                "\n 像素密度    " + displayDenisty(context) +
                "\n 系统版本    " + Build.VERSION.RELEASE +
                "\n SDK版本   " + Build.VERSION.SDK_INT +
                "\n 设备品牌    " + Build.BRAND +
                "\n 设备型号    " + Build.MODEL +
                "\n 设备版本号   " + Build.ID +
                "\n 产品的名称   " + Build.PRODUCT
                ;
    }

}
