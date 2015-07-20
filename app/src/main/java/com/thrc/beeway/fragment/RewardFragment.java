package com.thrc.beeway.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.thrc.beeway.R;
import com.thrc.beeway.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * com.thrc.beeway.fragment
 * 创建日期： 2015/7/15.
 * 版权：天合融创
 * 作者：余昊.
 * 版本号：1.0.
 * 修改历史： 打赏详情的fragment
 */
public class RewardFragment extends BaseFragment {
    private ListView lv_msg_item;
    private List<String> mDatas;

    @Override
    public void initData(Bundle savedInstanceState) {
        mDatas=new ArrayList<String>();
        for (int i=0;i<50;i++){
            mDatas.add("aaa"+i);
        }

    }

    @Override
    public View initView(LayoutInflater inflater) {
        view=inflater.inflate(R.layout.frag_reward,null);
        lv_msg_item= (ListView) view.findViewById(R.id.lv_msg_item);
        lv_msg_item.setAdapter(new MyRewardAdapter());
        return view;
    }



    private class MyRewardAdapter extends BaseAdapter{

        /**
         * How many items are in the data set represented by this Adapter.
         *
         * @return Count of items.
         */
        @Override
        public int getCount() {
            if (null!=mDatas){
                return mDatas.size();
            }
            return 0;
        }


        @Override
        public Object getItem(int position) {
            if (null!=mDatas){
                return mDatas.get(position);
            }
            return null;
        }


        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView==null){
                     convertView=View.inflate(context,R.layout.item_reward,null);
            }
            return convertView;
        }
    }

}
