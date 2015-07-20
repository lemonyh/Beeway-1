package com.thrc.beeway.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * com.thrc.beeway.activity
 * 创建日期： 2015/7/11.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：Activity的基类
 */
public class BaseActivity  extends ActionBarActivity{

    /**
     * 存储全局的activity
     */
    private static List<Activity> mActivities	= new LinkedList<Activity>();

    /**
     * 当前前台的activity
     */
    private static Activity			mForegroundActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        synchronized (mActivities)
        {
            mActivities.add(this);
        }

        initView();
        initActionBar();
        initData();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

        synchronized (mActivities)
        {
            mActivities.remove(this);
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        mForegroundActivity = this;
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        mForegroundActivity = null;
    }

    /**
     * 退出应用程序
     */
    public void exitApp()
    {

        // 线程安全的操作
        ListIterator<Activity> iterator = mActivities.listIterator();
        while (iterator.hasNext())
        {
            Activity next = iterator.next();
            next.finish();
        }

    }

    /**
     * 加载数据的方法，自己覆盖实现
     */
    protected void initData()
    {

    }

    /**
     * 初始化ActionBar的方法，子类如果有ActionBar的初始化，自己覆盖实现
     */
    protected void initActionBar()
    {

    }

    /**
     * 初始化View的方法，子类如果有View的初始化，自己覆盖实现
     */
    protected void initView()
    {

    }

    /**
     * 获取前台activity
     *
     * @return
     */
    public Activity getForegroundActivity()
    {
        return mForegroundActivity;
    }


}
