package com.thrc.beeway.pager;

import android.content.Context;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.thrc.beeway.R;
import com.thrc.beeway.adapter.FootprintAdapter;
import com.thrc.beeway.base.BasePager;

/**
 * com.thrc.beeway.pager
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class FootPager extends BasePager {
    private ListView lv_footprint_item;

    private TextView tv_title_center;
    private TextView tv_title_right;

    private FootprintAdapter mFootprintAdapter;


    public FootPager(Context context) {
        super(context);
    }


    @Override
    public void initTitleBar() {


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

        view = View.inflate(context, R.layout.frag_footprint, null);
        lv_footprint_item = (ListView) view.findViewById(R.id.lv_footprint_item);
        tv_title_center = (TextView) view.findViewById(R.id.tv_title_center);
        tv_title_right = (TextView) view.findViewById(R.id.tv_title_right);
        tv_title_center.setText("全部·足迹");
        tv_title_right.setText("···");

        mFootprintAdapter = new FootprintAdapter(this.context);
        lv_footprint_item.setAdapter(mFootprintAdapter);
        return view;
    }
}
