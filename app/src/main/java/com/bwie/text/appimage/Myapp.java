package com.bwie.text.appimage;

import android.app.Application;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import org.xutils.x;

/**
 * Created by mabiao on 2017/8/30.
 */

public class Myapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
          initxUtils();
         initImageLoader();
    }

    private void initImageLoader() {
        DisplayImageOptions op=new DisplayImageOptions.Builder()
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        ImageLoaderConfiguration con=new ImageLoaderConfiguration.Builder(this)
                .defaultDisplayImageOptions(op)
                .build();
        ImageLoader.getInstance().init(con);
    }

    private void initxUtils() {
        x.Ext.init(this);
    }
}
