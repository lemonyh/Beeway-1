package com.thrc.beeway.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.thrc.beeway.R;
import com.thrc.beeway.base.BaseFragment;
import com.thrc.beeway.base.BasePager;
import com.thrc.beeway.pager.FootPager;
import com.thrc.beeway.pager.FuctionPager;
import com.thrc.beeway.pager.MessagePager;
import com.thrc.beeway.pager.MySelfPager;
import com.thrc.beeway.pager.addMsgPager;
import com.thrc.beeway.widgets.LazyViewPager;
import com.thrc.beeway.widgets.MyViewPager;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * com.thrc.beeway.Fragment
 * 创建日期： 2015/7/13.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class HomeFragment extends BaseFragment
{
    @Bind(R.id.main_tab_bar)
    RadioGroup radioGroup;
    @Bind(R.id.layout_content)
    MyViewPager viewPagerlayout_content;
    /**存放几个分页面布局的子view对象**/
    private List<BasePager> pagersList;
    @Override
    public void initData(Bundle savedInstanceState) {
        //RadioGroup总得有个默认选中项吧，那好，我们就把第一个子RadioButton，首页设定为默认选中项
        radioGroup.check(R.id.main_tab_home);
        //创建一个ArrayList，用于存储我们5个tab对应的pager，这个list最后还是会作为myViewPager的数据来源
        pagersList = new ArrayList<BasePager>();

        /*
		 * 给list添加pager
		 * 这些Pager，每一个都是对应tab的一个选项
		 * 他们继承自BasePager
		 */
        pagersList.add(new FuctionPager(getActivity()));//首页
        pagersList.add(new FootPager(getActivity()));//足迹
        pagersList.add(new addMsgPager(getActivity()));//发布消息
        pagersList.add(new MessagePager(getActivity()));//消息
        pagersList.add(new MySelfPager(getActivity()));//我的

        /*
		 * 给MyViewPager的对象viewPagerlayout_content设置适配器
		 * ViewPager的使用在自定义控件课程中学过，因此不多解释这句代码
		 */

        viewPagerlayout_content.setAdapter(new MyAdapter());
        /*
		 * 给MyViewPager对象viewPagerlayout_content设置页面更改监听事件，就是当ViewPager切换
		 * 页面的的时候调用的方法
		 * 在该方法中我会做哪些操作呢？
		 * 页面切换了，是不是要给当前切换到页面初始化数据呢？对的，就是给切换到的页面初始化数据
		 */
        viewPagerlayout_content.setOnPageChangeListener(new LazyViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //获取当前页对应的pager，从list中拿出来然后进行初始化数据的操作
                BasePager basePager = pagersList.get(position);
                basePager.initData();
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        /*
		 * 给RadioGroup设置选中监听事件
		 * 当某一个RadioButton被选中时回调该方法
		 */
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
				/* 判断当前选中的是哪个RadioButton
				 * 然后给MyViewPager对象设置当前的Pager页
				 */
                switch (checkedId) {
                    case R.id.main_tab_home:
                        viewPagerlayout_content.setCurrentItem(0);
                        break;
                    case R.id.main_tab_foot:
                        viewPagerlayout_content.setCurrentItem(1);
                        break;
                    case R.id.main_tab_add:
                        viewPagerlayout_content.setCurrentItem(2);
                        break;
                    case R.id.main_tab_message:
                        viewPagerlayout_content.setCurrentItem(3);
                        break;
                    case R.id.main_tab_myself:
                        viewPagerlayout_content.setCurrentItem(4);
                        break;
                }
            }
        });
        //因为我们的RadioGroup默认把第一页当做默认页，因此我们给第一页初始化数据
        BasePager basePager = pagersList.get(0);
        basePager.initData();
    }

    @Override
    public View initView(LayoutInflater inflater) {
        /*
		 * 初始化视图，填充布局文件
		 */
        view=inflater.inflate(R.layout.fragment_main,null);
        /*
		 * 这里使用ButterKnife
		 * 通过将view注入给ButterKnife，然后我们就能通过注解的形式获取view中的子控件，原理其实很简单，
		 * 首先通过反射获取当前类（this）,然后获取该类的属性（暴力反射）上的注解，然后从view中根据属性上的注解id拿到子控件，
		 * 然后反射findViewByID（）方法，将生成的对象暴力反射给该类的属性，OK了，我们就可以直接使用子控件了，就这么简单。
		 */
        ButterKnife.bind(this,view);
        return view;
    }

    /*
	 * 自定义ViewPager的适配器
	 */
    private  class MyAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            return pagersList.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }
        /*
         * 	初始化ViewPager要展示的内容
         * 	ViewPager要展示的每一个Pager都是我们initData方法中放到集合中的
         * 这里需要从集合中拿出来，然后获取其要展示的View对象
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            ((MyViewPager) container).addView(pagersList.get(position).getRootView());
            return pagersList.get(position).getRootView();
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            ((MyViewPager) container).removeView((View) object);
        }
    }
}
