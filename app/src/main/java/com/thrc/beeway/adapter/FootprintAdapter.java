package com.thrc.beeway.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.thrc.beeway.R;

import java.text.SimpleDateFormat;

/**
 * 项目名称：Beeway-master
 * 类描述：足迹适配器
 * 创建人：谢庆华
 * 创建时间：2015/7/16 10:53
 * 修改人：Administrator
 * 修改时间：2015/7/16 10:53
 * 修改备注：
 */
public class FootprintAdapter extends BaseAdapter {
    private Context context;

    int[] user_img = {R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo, R.drawable.logo};
    int[] footprint_img = {R.drawable.memberbg, R.drawable.memberbg2, R.drawable.memberbg3, R.drawable.memberbg2, R.drawable.memberbg3,};
    String[] user_iss_address = {"橘子洲头1", "橘子洲头2", "橘子洲头3", "橘子洲头4", "橘子洲头5",};
    String[] footprint_text = {"百年修得同船渡，千年修得优衣库1", "百年修得同船渡，千年修得优衣库2", "百年修得同船渡，千年修得优衣库3", "百年修得同船渡，千年修得优衣库4", "百年修得同船渡，千年修得优衣库5",};
    String[] biaoqian = {"美食", "风景", "购物", "感慨", "吐槽"};
    SimpleDateFormat sDateFormat = new SimpleDateFormat("MM月dd日hh:mm");
    String date = sDateFormat.format(new java.util.Date());

    String[] user_name = {"张三", "李四", "王五", "赵六", "田七"};
//    String[] shoucang = {"收藏1", "收藏2", "收藏3", "收藏4", "收藏5"};
//    String[] pinglun = {"评论1", "评论2", "评论3", "评论4", "评论5",};
//    String[] dianzan = {"赞1", "赞2", "赞3", "赞4", "赞5",};

    public FootprintAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return user_img.length;
    }

    @Override
    public Object getItem(int position) {
        return user_img[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    //渲染控件iv_user_img,iv_footprint_img,tv_user_iss_address,tv_footprint_test,tv_biaoqian,tv_date,tv_user_name,tv_shoucang,tv_pinglun,tv_dianzan
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //   FootprintInfo footprintInfo=list.get(position);
        View view = View.inflate(context, R.layout.item_footprint, null);


        ImageView iv_user_img = (ImageView) view.findViewById(R.id.iv_user_img);
        ImageView iv_footprint_img = (ImageView) view.findViewById(R.id.iv_footprint_img);
        TextView tv_user_iss_address = (TextView) view.findViewById(R.id.tv_user_iss_address);
        TextView tv_footprint_text = (TextView) view.findViewById(R.id.tv_footprint_text);
        TextView tv_biaoqian = (TextView) view.findViewById(R.id.tv_biaoqian);
        TextView tv_date = (TextView) view.findViewById(R.id.tv_date);
        TextView tv_user_name = (TextView) view.findViewById(R.id.tv_user_name);
        TextView tv_shoucang = (TextView) view.findViewById(R.id.tv_shoucang);
        TextView tv_pinglun = (TextView) view.findViewById(R.id.tv_pinglun);
        TextView tv_dianzan = (TextView) view.findViewById(R.id.tv_dianzan);

        iv_user_img.setImageResource(user_img[position]);
        iv_footprint_img.setImageResource(footprint_img[position]);
        tv_user_iss_address.setText(user_iss_address[position]);
        tv_footprint_text.setText(footprint_text[position]);
        tv_biaoqian.setText(biaoqian[position]);
        tv_date.setText(date);
        tv_shoucang.setText("收藏");
        tv_pinglun.setText("评论");
        tv_dianzan.setText("赞");
        tv_user_name.setText(user_name[position]);
        return view;
    }
}
