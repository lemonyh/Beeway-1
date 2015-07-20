package com.thrc.beeway.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * com.thrc.beeway.widgets
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 * 这里之所以继承LazyViewPager
 * 是因为我们需要覆写父类的onInterceptTouchEvent方法
 * 该方法返回false就是让子控件的触摸事件分发出去
 * 返回true则对子控件的触摸事件进行拦截
 *
 */

public class MyViewPager extends  LazyViewPager {

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyViewPager(Context context) {
        super(context);
    }
    //返回false则
    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }
}
