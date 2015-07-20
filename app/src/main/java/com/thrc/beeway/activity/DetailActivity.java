package com.thrc.beeway.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.thrc.beeway.R;
import com.thrc.beeway.fragment.RewardFragment;

/**
 * com.thrc.beeway.activity
 * 创建日期： 2015/7/15.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史：
 */
public class DetailActivity extends BaseActivity {
    private ImageView iv_back, iv_more,iv_example;

    //拍照
    private static int CAMERA_REQUEST_CODE=1;
    //选择图库中的图片
    private static int GALLERY_REQUEST_CODE = 2;

    /**
     * 初始化View的方法，子类如果有View的初始化，自己覆盖实现
     */
    @Override
    protected void initView() {
        super.initView();
        setContentView(R.layout.activity_detail);
        iv_back = (ImageView) findViewById(R.id.iv_back);
        iv_more=(ImageView)findViewById(R.id.iv_more);
        iv_example= (ImageView) findViewById(R.id.iv_example);
        RewardFragment rewardFragment = new RewardFragment();

        getSupportFragmentManager().beginTransaction().
                replace(R.id.fl_detail, rewardFragment, "REWARD")
        .commit()
        ;
    }

    @Override
    protected void initData() {
        super.initData();

        /**
         * 返回键的处理 。以后可以抽取到基类中处理
         */
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        /**
         * 发布新数据的处理
         */
        iv_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(UIUtils.getContext(), "发送新消息", Toast.LENGTH_SHORT).show();
                //模拟用户的图片上传
               /* Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST_CODE);*/
                Intent intent=new Intent(Intent.ACTION_GET_CONTENT);


                intent.setType("image/*");//设置选择的类型为图像类型
                //打开图库按钮
                startActivityForResult(intent, GALLERY_REQUEST_CODE);

            }
        });
    }

    /**
     * Dispatch incoming result to the correct fragment.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==CAMERA_REQUEST_CODE){
            //如果用户取消了点击按钮，不会进行其他的处理
            if (null==data){
                return;
            }
            else{
                  Bundle extras=data.getExtras();
                if (null!=extras){
                    Bitmap bitmap=extras.getParcelable("data");
                    iv_example.setImageBitmap(bitmap);
                }
            }
        }
    }
}
