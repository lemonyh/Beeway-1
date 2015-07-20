package com.thrc.beeway.activity;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.thrc.beeway.R;
import com.thrc.beeway.adapter.ViewPagerAdapter;
import com.thrc.beeway.utils.UIUtils;
import com.thrc.beeway.widgets.DepthPageTransformer;

import java.util.ArrayList;

/**
 * com.thrc.beeway.activity
 * 创建日期： 2015/7/11.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：引导页面
 */
public class GuideActivity  extends  BaseActivity implements View.OnClickListener{

    //    定义viewpager
    private ViewPager mViewPager;

    private Button bt_Enter;

    //引导图片资源
    private static final int[] pics = {R.drawable.guide1, R.drawable.guide2, R.drawable.guide3};

    //定义一个list来存放view的集合
    private ArrayList<View> views;

    //记录当前选中位置
    private int currentIndex;

    //底部小点的图片
    private ImageView[] points;

    private ViewPagerAdapter mAdapter;


    /**
     * 初始化数据
     */
    protected void initData() {

        initGuidePager();


    }

    /**
     * 引导页面
     */
    private void initGuidePager() {
        //定义一个布局并设置参数
        RelativeLayout.LayoutParams mParams = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.MATCH_PARENT);

        //初始化引导页面的图片
        for (int i = 0; i < pics.length; i++) {
            ImageView iv = new ImageView(UIUtils.getContext());
            iv.setLayoutParams(mParams);
            iv.setBackgroundResource(pics[i]);
            views.add(iv);
        }
        //设置数据
        mViewPager.setAdapter(mAdapter);
        //设置viewpager的动画效果
        mViewPager.setPageTransformer(true, new DepthPageTransformer());


        //设置监听
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
                if (i == pics.length - 1) {
                    bt_Enter.setVisibility(View.VISIBLE);
                    bt_Enter.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(GuideActivity.this, MainActivity.class);
                            startActivity(intent);
                            //注意这里应该做清空操作。
                            // SharedPreferencesUtils.setParam(UIUtils.getContext(), "is_first", false);

                            finish();
                        }
                    });
                } else {
                    bt_Enter.setVisibility(View.GONE);
                }
            }

            /*页面选择的时候会调用*/
            @Override
            public void onPageSelected(int i) {
                //设置底部小点选中状态
                setCurDot(i);


            }

            @Override
            public void onPageScrollStateChanged(int i) {


            }
        });


        //初始化底部小点
        initPoint();
    }

    /**
     * 初始化底部的小点
     */
    private void initPoint() {
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.ll_point);

        points = new ImageView[pics.length];


        //循环取得小点图片
        for (int i = 0; i < pics.length; i++) {
            //得到一个LinearLayout下面的每一个子元素
            points[i] = (ImageView) linearLayout.getChildAt(i);
            //默认都设为灰色
            points[i].setEnabled(true);
            //给每个小点设置监听
            points[i].setOnClickListener(this);
            //设置位置tag，方便取出与当前位置对应
            points[i].setTag(i);
        }

        //设置当面默认的位置
        currentIndex = 0;
        //设
        // 置为白色，即选中状态
        points[currentIndex].setEnabled(false);
    }

    /**
     * 初始化界面
     */


    protected void initView() {
        setContentView(R.layout.activity_guide);
        views = new ArrayList<View>();
        mViewPager = (ViewPager) findViewById(R.id.vp_guide);

        bt_Enter = (Button) findViewById(R.id.bt_enter);

        //实例化Adapter
        mAdapter = new ViewPagerAdapter(views);


    }

    @Override
    public void onClick(View v) {
        int position = (Integer) v.getTag();
        setCurView(position);
        setCurDot(position);
    }

    /*
     * 设置当前的小点的位置
     */
    private void setCurDot(int position) {
        if (position < 0 || position > pics.length - 1 || currentIndex == position) {
            return;
        }
        points[position].setEnabled(false);
        points[currentIndex].setEnabled(true);

        currentIndex = position;
    }

    /*设置当前页面的位置*/
    private void setCurView(int position) {
        if (position < 0 || position >= pics.length) {
            return;
        }
        mViewPager.setCurrentItem(position);
    }

}
