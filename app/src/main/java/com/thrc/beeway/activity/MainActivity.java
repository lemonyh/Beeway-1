package com.thrc.beeway.activity;

import android.os.Bundle;
import android.view.Window;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.thrc.beeway.R;
import com.thrc.beeway.fragment.HomeFragment;
import com.thrc.beeway.fragment.MenuFragment;

public class MainActivity extends SlidingFragmentActivity {
    private SlidingMenu slidingMenu;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 设置改Activity的样式
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.content);
        /*
         * 给SlidingMenu设定布局文件，该布局文件就是一个FrameLayout，等待着被替换，FrameLayout这个控件生来命就苦，
		 * 基本用到最多的 就是作为Fragment的布局替身，或者其他View来替换，一直扮演着占位的角色
		 */
        setBehindContentView(R.layout.menu_frame);

        // 因为我们当前Activity已经继承了SlidingFragmentActivity，所有我们可以直接获取SlidingMenu对象
        slidingMenu = getSlidingMenu();
        /*
         * 给SlidingMenu设置模式，这里我们采用左侧的模式 还有右侧，左右同时存在等模式
		 * 所谓的左侧模式其实就是菜单居于当前Activity的左侧
		 */
        slidingMenu.setMode(SlidingMenu.LEFT);
        /*
         * 设置SlidingMenu的宽度 这里是通过dimens.xml文件中的值来设定，其这样的做法应该是为了适配不同分辨率的手机考虑的
		 * dimens.xml中的对应值为： <dimen name="slidingmenu_offset">180dp</dimen>
		 */
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        /*
         * 给SlidingMenu设置一个阴影背景，所谓的阴影背景其实我们可以理解为菜单边缘处的颜色，一般都是渐变的色彩， 这里设置背景 也是需要在xml文件中设置
		 * shadow.xml文件清单如下，该文件是渐变色
		 * <?xml version="1.0" encoding="utf-8"?>
		 * <shape xmlns:android="http://schemas.android.com/apk/res/android" >
		 * <gradient android:endColor="#5A000000"
		 * android:centerColor="#2D000000" android:startColor="#00000000" />
		 * </shape>
		 */
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        /*
		 * 给菜单设置阴影的宽度，其值如下
		 *
		 * <dimen name="shadow_width">5dp</dimen>
		 */
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
		/*
		 * 给SlidingMenu设置触摸模式
		 * 因为SlidingMenu默认是隐藏（上面已经设置为隐藏在左侧）的，如果想滑动出来，我们需要在当前界面在哪个位置滑动呢？
		 * SlidingMenu给我们提供了2中模式，或者说3中如下：
		 * SlidingMenu.TOUCHMODE_MARGIN
		 * SlidingMenu.TOUCHMODE_FULLSCREEN
		 * SlidingMenu.TOUCHMODE_NONE
		 * 如果英语还可以，其实每一个类名，每一个方法名，每一个参数名都是最好的注释
		 * 第一种是边缘，也就是说我们从左侧边缘往右滑动手指才可以滑出菜单
		 * 第二种是全屏幕，在屏幕的任何位置往右滑动都可以滑出菜单
		 * 第三种是不能滑出菜单，就是不让你把菜单滑出
		 */
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
        /*
		 * 创建住Fragment，HomeFragment
		 * 该Fragment用的是MainActivity的布局文件，将当前Activity替换成该Fragment
		 */
        MenuFragment menuFragment = new MenuFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.menu, menuFragment, "MENU").commit();


        HomeFragment homeFragment=new HomeFragment();

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame,homeFragment,"HOME").commit();



    }

    /*
      * 对外提供的方法，这样外部其他类只要拥有content（也就是当前Activity）就可以获取该类中的Fragment
      * 这两个方法对外暴漏了MainActivity中的2个Fragment
      * 查询Fragment时根据Tag来查找的，如果我们把两个Fragment当做成员变量也是可以的。
      * 你可能会问，为何能拿到Activity，就能拿到这个Activity种的Fragment呢？
      * 这是因为Fragment的生命周期本身就是绑定在Activity的，也就说Fragment本省就是Activity的附属
      * 这一点从Fragment生命周的onAttach（绑定）onDetach（解绑也能看得出）
      */
    public MenuFragment switchMenuFragment() {
        return (MenuFragment) getSupportFragmentManager().findFragmentByTag("MENU");
    }

    public HomeFragment switchHomeFragment() {
        return (HomeFragment) getSupportFragmentManager().findFragmentByTag("HOME");
    }



}
