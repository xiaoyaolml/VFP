package com.leo.vfp;

import android.app.Application;

import com.uuzuche.lib_zxing.activity.ZXingLibrary;

import cn.bmob.v3.Bmob;

public class App extends Application {
    private static final String APPID = "259d4840de48ed219dd476601613cf13";

    @Override
    public void onCreate() {
        super.onCreate();
        Bmob.initialize(this, APPID);
        ZXingLibrary.initDisplayOpinion(this);
    }
}
