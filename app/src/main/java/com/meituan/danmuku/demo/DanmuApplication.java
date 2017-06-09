package com.meituan.danmuku.demo;

import android.app.Application;

import com.facebook.fresco.helper.Phoenix;

/**
 * Created by zhangmeng on 2017/6/9.
 */

public class DanmuApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Phoenix.init(this);


    }
}
