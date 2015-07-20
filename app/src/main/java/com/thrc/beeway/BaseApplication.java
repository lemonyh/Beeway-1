package com.thrc.beeway;

import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

/**
 * com.thrc.beeway
 * 创建日期： 2015/7/11.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class BaseApplication  extends Application {

    /**
     * 程序唯一的上下文context*
     */
    private static Context mContext;
    /**
     * 程序唯一的线程*
     */
    private static Thread mMainThread;
    /**
     * 程序线程的id*
     */
    private static long mMainThreadId;
    /**
     * 程序主线程的handler*
     */
    private static Handler mMainThreadHandler;
    /**
     * 程序住线程的Looper*
     */
    private static Looper mMainThreadLooper;


    @Override
    public void onCreate() {
        super.onCreate();
        // 在应用程序入口提供全局的工具

        // 上下文
        mContext = this;

        // 主线程和子线程
        mMainThread = Thread.currentThread();

        // 主线程id
        // mMainThreadId = mMainThread.getId();
        // android.os.Process.myPid();// 进程id
        mMainThreadId = android.os.Process.myTid();// 当前线程id
        // android.os.Process.myUid();//用户id

        // 主线程handler
        mMainThreadHandler = new Handler();

        //主线程looper
        mMainThreadLooper = getMainLooper();
    }


    /*获取程序的上下文*/
    public static Context getContext() {
        return mContext;
    }

    /*获取程序的主线程*/
    public static Thread getMainThread() {
        return mMainThread;
    }

    /*获取程序的主线程ID*/
    public static long getMainThreadId() {
        return mMainThreadId;
    }
    /*获取主线程的Handler*/
    public static Handler getMainThreadHandler() {
        return mMainThreadHandler;
    }
    /*获取主线程的Looper*/
    public static Looper getMainThreadLooper() {
        return mMainThreadLooper;
    }

}
