package com.thrc.beeway.pager;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.thrc.beeway.R;
import com.thrc.beeway.activity.DetailActivity;
import com.thrc.beeway.base.BasePager;
import com.thrc.beeway.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * com.thrc.beeway.pager
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class FuctionPager extends BasePager {

    private ViewPager viewPager; // android-support-v4中的滑动组件
    private List<ImageView> imageViews; // 滑动的图片集合

    private String[] titles; // 图片标题
    private int[] imageResId; // 图片ID
    private List<View> dots; // 图片标题正文的那些点

    private TextView tv_title;
    private int currentItem = 0; // 当前图片的索引号
    private ImageView iv_reward;
    private ImageView iv_add;


    // An ExecutorService that can schedule commands to run after a given delay,
    // or to execute periodically.
    private ScheduledExecutorService scheduledExecutorService;

    // 切换当前显示的图片
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            viewPager.setCurrentItem(currentItem);// 切换当前显示的图片
        }

        ;
    };


    public FuctionPager(Context context) {

        super(context);
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        // 当Activity显示出来后，每两秒钟切换一次图片显示
        scheduledExecutorService.scheduleAtFixedRate(new ScrollTask(), 1, 2, TimeUnit.SECONDS);
    }


    /**
     * 在initView方法中初始化了两个布局文件
     * 一个是轮播图
     * 一个是ListView
     *
     * @return 布局
     */
    @Override
    public View initView() {

        view = View.inflate(context, R.layout.frag_home, null);
        tv_title = (TextView) view.findViewById(R.id.tv_title);
        viewPager = (ViewPager) view.findViewById(R.id.vp);
        iv_reward= (ImageView) view.findViewById(R.id.iv_reward);
        iv_add=(ImageView) view.findViewById(R.id.iv_add);
        return view;
    }






    /**
     * 初始化数据
     */
    @Override
    public void initData() {
        //viewPager = (ViewPager) view.findViewById(R.id.vp);
        imageResId = new int[]{R.drawable.a, R.drawable.b, R.drawable.c,};
        titles = new String[imageResId.length];
        titles[0] = "长沙晨景";
        titles[1] = "长沙晚景";
        titles[2] = "洞庭美景";
        imageViews = new ArrayList<ImageView>();
        // 初始化图片资源
        for (int i = 0; i < imageResId.length; i++) {
            ImageView imageView = new ImageView(context);
            imageView.setImageResource(imageResId[i]);
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageViews.add(imageView);
        }


        dots = new ArrayList<View>();
        dots.add(view.findViewById(R.id.v_dot0));
        dots.add(view.findViewById(R.id.v_dot1));
        dots.add(view.findViewById(R.id.v_dot2));
        tv_title.setText(titles[0]);

        viewPager.setAdapter(new MyAdapter());// 设置填充ViewPager页面的适配器
        // 设置一个监听器，当ViewPager中的页面改变时调用
        viewPager.setOnPageChangeListener(new MyPageChangeListener());
        iv_reward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogUtils.i("进如打赏页面");
                Intent intent=new Intent(context, DetailActivity.class);
                context.startActivity(intent);
            }
        });


    }


    /**
     * 换行切换任务
     *
     * @author Administrator
     */
    private class ScrollTask implements Runnable {

        public void run() {
            synchronized (viewPager) {
                System.out.println("currentItem: " + currentItem);
                currentItem = (currentItem + 1) % imageViews.size();
                handler.obtainMessage().sendToTarget(); // 通过Handler切换图片
            }
        }

    }

    /**
     * 当ViewPager中页面的状态发生改变时调用
     *
     * @author Administrator
     */
    private class MyPageChangeListener implements ViewPager.OnPageChangeListener {
        private int oldPosition = 0;

        /**
         * This method will be invoked when a new page becomes selected.
         * position: Position index of the new selected page.
         */
        public void onPageSelected(int position) {
            currentItem = position;
            tv_title.setText(titles[position]);
            dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
            dots.get(position).setBackgroundResource(R.drawable.dot_focused);
            oldPosition = position;
        }

        public void onPageScrollStateChanged(int arg0) {

        }

        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }
    }

    /**
     * 填充ViewPager页面的适配器
     *
     * @author Administrator
     */
    private class MyAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return imageResId.length;
        }

        @Override
        public Object instantiateItem(View arg0, int arg1) {
            ((ViewPager) arg0).addView(imageViews.get(arg1));
            return imageViews.get(arg1);
        }

        @Override
        public void destroyItem(View arg0, int arg1, Object arg2) {
            ((ViewPager) arg0).removeView((View) arg2);
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public void restoreState(Parcelable arg0, ClassLoader arg1) {

        }

        @Override
        public Parcelable saveState() {
            return null;
        }

        @Override
        public void startUpdate(View arg0) {

        }

        @Override
        public void finishUpdate(View arg0) {

        }
    }

}
