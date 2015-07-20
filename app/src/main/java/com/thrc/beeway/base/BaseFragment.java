package com.thrc.beeway.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thrc.beeway.activity.MainActivity;

/**
 * com.thrc.beeway.base
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：  项目中其他Fragment的基类，包含了inteView和initData两个抽象方法，因此该类也是抽象的
 */
public abstract class BaseFragment extends Fragment{
    public View view;
    public Context context;
    public SlidingMenu slidingMenu;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		/*
		 * 获取Context 为何是getActivity呢？
		 * 很简单因为Activity是Context的子类呗
		 * 为什么在Fragment中可以获取到Activity（Context）呢？
		 * 以还是那句话因为Fragment是绑定在Activity中的，因此Fragment提供了
		 * getActivity方法，可以让我们方便的获取到当前Fragment绑定的Activity对象
		 */
        context = getActivity();
		/*
		 * 获取SlidingMenu对象
		 * 为什么在context中能获取到SlidingMenu对象呢，因为我们的MainActivity
		 * 是继承了SlidingFragmentActivity类的。
		 */
        if (context.getClass()==MainActivity.class) {
            slidingMenu = ((MainActivity)context).getSlidingMenu();
        }


    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //将初始化View的方法房子抽象函数中，具体创建什么样的View交给子类来实现
        view = initView(inflater);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        //初始化数据也方法也声明为抽象的，让子类来实现
        initData(savedInstanceState);
        super.onActivityCreated(savedInstanceState);
    }
    //声明两个抽象方法  一个用来初始化布局，一个用来初始化数据
    public abstract void initData(Bundle savedInstanceState);
    public abstract View initView(LayoutInflater inflater);
}
