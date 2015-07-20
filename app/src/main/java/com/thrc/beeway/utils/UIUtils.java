package com.thrc.beeway.utils;

import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.util.DisplayMetrics;

import com.thrc.beeway.BaseApplication;

/**
 * com.thrc.beeway.utils
 * 创建日期： 2015/7/11.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：相关操作的帮助类
 */
public class UIUtils {

    /**
     * 上下文的获取
     *
     * @return
     */
    public static Context getContext()
    {
        return BaseApplication.getContext();
    }

    /**
     * 获取资源
     *
     *
     * @return
     */
    public static Resources getResources()
    {
        return getContext().getResources();
    }

    public static long getMainThreadId()
    {
        return BaseApplication.getMainThreadId();
    }

    public static Handler getMainThreadHandler()
    {

        return BaseApplication.getMainThreadHandler();
    }

    /**
     * 主线程中执行 任务
     *
     * @param task
     */
    public static void runOnUiThread(Runnable task)
    {
        long currentThreadId = android.os.Process.myTid();
        long mainThreadId = getMainThreadId();

        if (currentThreadId == mainThreadId)
        {
            // 如果在主线程中执行
            task.run();
        }
        else
        {
            // 需要转的主线程执行
            getMainThreadHandler().post(task);
        }
    }

    /**
     *dip转为 px
     * @param dip
     * @return
     */
    public static int dip2px(int dip)
    {
        // 公式 1: px = dp * (dpi / 160)
        // 公式 2: dp = px / denistity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        // metrics.densityDpi
        return (int) (dip * density + 0.5f);
    }

    /**
     * px 转为 dip
     * @param px
     * @return
     */

    public static int px2dip(int px)
    {
        // 公式 1: px = dp * (dpi / 160)
        // 公式 2: dp = px / denistity;
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float density = metrics.density;
        // metrics.densityDpi
        return (int) (px / density + 0.5f);
    }

    /**
     * 获取资源目录下的values中的String资源类型
     * @param resId
     * @return
     */
    public static String getString(int resId)
    {
        return getResources().getString(resId);
    }

    /**
     * 获取应用程序的包名
     * @return
     */
    public static String getPackageName()
    {
        return getContext().getPackageName();
    }

    /**
     * 获取资源目录中的array数组
     * @param resId
     * @return
     */
    public static String[] getStringArray(int resId)
    {
        return getResources().getStringArray(resId);
    }

    /**
     * 获取资源目录中的颜色资源
     * @param resId
     * @return
     */

    public static int getColor(int resId)
    {
        return getResources().getColor(resId);
    }
}
