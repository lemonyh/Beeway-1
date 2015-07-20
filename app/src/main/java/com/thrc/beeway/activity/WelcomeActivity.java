package com.thrc.beeway.activity;

import android.content.Intent;

import com.thrc.beeway.R;
import com.thrc.beeway.utils.SharedPreferencesUtil;
import com.thrc.beeway.utils.UIUtils;

import butterknife.ButterKnife;

/**
 * com.thrc.beeway.activity
 * 创建日期： 2015/7/11.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：欢迎界面的Activity
 */
public class WelcomeActivity extends BaseActivity {

    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);


    }

    @Override
    protected void initData() {
        super.initData();
        boolean b = (boolean) SharedPreferencesUtil.getData(UIUtils.getContext(), "is_first", true);
        Intent intent;
        if (b){
            // 第一次进入应用，进入引导页面
            intent=new Intent(this,GuideActivity.class);
            startActivity(intent);
            SharedPreferencesUtil.saveData(UIUtils.getContext(),"is_first",false);
            finish();
        }else{
            // 不是第一次进入，进入应用程序的主页面
            intent=new Intent(this,MainActivity.class);
            startActivity(intent);
            finish();
        }


    }
}
