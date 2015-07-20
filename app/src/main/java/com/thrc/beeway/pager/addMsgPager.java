package com.thrc.beeway.pager;

import android.content.Context;
import android.view.View;

import com.thrc.beeway.R;
import com.thrc.beeway.base.BasePager;

/**
 * com.thrc.beeway.pager
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class addMsgPager extends BasePager {
    public addMsgPager(Context context) {
        super(context);
    }

    /**
     * 初始化数据
     */
    @Override
    public void initData() {

    }

    /**
     * 初始化布局文件
     *
     * @return 布局
     */
    @Override
    public View initView() {
        view = View.inflate(context, R.layout.frag_iss_footprint, null);
        return view;
    }
}
