package com.bwie.text.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bwie.text.R;
import com.bwie.text.bean.MyBean;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by mabiao on 2017/8/30.
 */

public class NewsAdapter extends BaseAdapter{
    private Context context;
    private List<MyBean> list;
    private final int one=0;
    private final int two=1;
    private final int num=2;

    public NewsAdapter(Context context, List<MyBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%2==0){
            return one;
        }else{
            return two;
        }
    }

    @Override
    public int getViewTypeCount() {
        return num;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder h=null;
        ViewHolder1 h1=null;
        int itemViewType = getItemViewType(i);
        if(view==null){
            switch (itemViewType){
                case one:
                    h=new ViewHolder();
                    view= LayoutInflater.from(context).inflate(R.layout.item1,null);
                    h.img=view.findViewById(R.id.img);
                    h.tv_bt=view.findViewById(R.id.tv_bt);
                    h.tv_sj=view.findViewById(R.id.tv_sj);
                    h.tv_wz=view.findViewById(R.id.tv_wz);

                    h.tv_wz.setText(list.get(i).getAuthor_name());
                    h.tv_sj.setText(list.get(i).getDate());
                    h.tv_bt.setText(list.get(i).getTitle());
                    ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),h.img);

                    view.setTag(h);
                    break;
                case two:
                    h1=new ViewHolder1();
                    view= LayoutInflater.from(context).inflate(R.layout.item2,null);
                    h1.iv_img=view.findViewById(R.id.iv_img);
                    h1.bt_tv=view.findViewById(R.id.bt_tv);
                    h1.sj_tv=view.findViewById(R.id.sj_tv);
                    h1.wz_tv=view.findViewById(R.id.wz_tv);

                    h1.bt_tv.setText(list.get(i).getTitle());
                    h1.sj_tv.setText(list.get(i).getDate());
                    h1.wz_tv.setText(list.get(i).getAuthor_name());
                    ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),h1.iv_img);

                    view.setTag(h1);
                    break;

            }

        }else{
            switch (itemViewType){
                case one:
                      h= (ViewHolder) view.getTag();
                     h.tv_wz.setText(list.get(i).getAuthor_name());
                    h.tv_sj.setText(list.get(i).getDate());
                    h.tv_bt.setText(list.get(i).getTitle());
                    ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),h.img);
                    break;
                case two:
                     h1= (ViewHolder1) view.getTag();
                    h1.bt_tv.setText(list.get(i).getTitle());
                    h1.sj_tv.setText(list.get(i).getDate());
                    h1.wz_tv.setText(list.get(i).getAuthor_name());
                    ImageLoader.getInstance().displayImage(list.get(i).getThumbnail_pic_s(),h1.iv_img);
                    break;

            }

        }


        return view;
    }
    public class ViewHolder{
        public ImageView img;
        public TextView tv_bt,tv_wz,tv_sj;

    }
    public class ViewHolder1{
        public ImageView iv_img;
        public TextView bt_tv,wz_tv,sj_tv;

    }
}
