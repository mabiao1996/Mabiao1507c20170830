package com.bwie.text;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bwie.text.adapter.NewsAdapter;
import com.bwie.text.api.NewsApi;
import com.bwie.text.bean.MyBean;
import com.bwie.text.bean.NewsBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;

import view.xlistview.XListView;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements XListView.IXListViewListener{
@ViewInject(R.id.xlv)     XListView xlv;
    private List<MyBean> list;
    private NewsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        initList();
        initData();


    }

    private void initList() {
        list = new ArrayList<>();
    }

    private void initData() {
        RequestParams params=new RequestParams(NewsApi.NEWSURL);
        params.addQueryStringParameter("key",NewsApi.NEWSKEY);
        x.http().post(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                Gson gson=new Gson();
                NewsBean newsBean = gson.fromJson(result, NewsBean.class);
                NewsBean.ResultBean result1 = newsBean.getResult();
                List<NewsBean.ResultBean.DataBean> data = result1.getData();
                if(data!=null&&data.size()>0){
                    for (int i = 0; i <data.size() ; i++) {
                        NewsBean.ResultBean.DataBean dataBean = data.get(i);
                        MyBean bean=new MyBean();
                        String title = dataBean.getTitle();
                        String date = dataBean.getDate();
                        String author_name = dataBean.getAuthor_name();
                        String thumbnail_pic_s = dataBean.getThumbnail_pic_s();
                        bean.setTitle(title);
                        bean.setDate(date);
                        bean.setAuthor_name(author_name);
                        bean.setThumbnail_pic_s(thumbnail_pic_s);
                        list.add(bean);
                    }
                }
                Xianshi();
            }
            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
            }
            @Override
            public void onCancelled(CancelledException cex) {
            }
            @Override
            public void onFinished() {
            }
        });
    }

    private void Xianshi() {
        adapter = new NewsAdapter(MainActivity.this,list);
        xlv.setAdapter(adapter);
        xlv.stopLoadMore();
        xlv.stopRefresh();

    }

    private void initView() {
        x.view().inject(this);
        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(this);
    }
    @Override
    public void onRefresh() {
    }
    @Override
    public void onLoadMore() {

    }


}
