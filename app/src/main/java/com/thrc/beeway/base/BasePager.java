package com.thrc.beeway.base;

import android.content.Context;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.thrc.beeway.activity.MainActivity;

/**
 * com.thrc.beeway.base
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：抽象类BasePager
 */
public abstract  class BasePager {

    public Context context;
    public View view;
    public SlidingMenu slidingMenu;
    //这个属性是页面的标题，这个也作为基础类是因为多有页面都有标题
    public TextView txt_title;
    //头部按钮
    private ImageButton imgbtn_text;
    private ImageButton imgbtn_right;
    private ImageButton btn_right;

    public BasePager(Context context) {
        //在构造函数中获取Context，目的是为了获取Context中的SlidingMenu以及Context中的Fragment
        this.context = context;
        slidingMenu = ((MainActivity)context).getSlidingMenu();
        view = initView();
    }
    /*
	 * 返回initView方法中创建的View对象
	 * 这里这个方法显得很多余，因为我们的View对象已经声明为public了子类可以直接使用
	 * 而不需调用该方法，但是从架构的思想来说通过调用期对外暴漏的方法也直接暴漏属性要好
	 */
    public View getRootView(){
        return view;
    }

    /*
	 * 初始化标题栏
	 */
    public void initTitleBar(){

    }

    /**
     * 初始化数据
     */
    public abstract void initData();

    /**
     * 初始化布局文件
     * @return   布局
     */
    public abstract View initView();
}
