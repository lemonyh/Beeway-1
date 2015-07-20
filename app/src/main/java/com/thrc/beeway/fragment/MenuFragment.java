package com.thrc.beeway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.thrc.beeway.R;
import com.thrc.beeway.base.BaseFragment;

/**
 * com.thrc.beeway.Fragment
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class MenuFragment  extends BaseFragment{
    @Override
    public void initData(Bundle savedInstanceState) {

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.fragment_menu,null);
        return view;
    }
}
