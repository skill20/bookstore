package com.tong.bookstore;

import android.app.Application;

import com.tong.bookstore.util.NetworkHelper;

/**
 * @author qingqing.wang
 * @version 1.0.0
 * @date 2016-03-11 19:22
 * @since 5.3.0
 */
public class BookStoreApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initAll();
    }

    private void initAll() {
        initNetwork();
    }

    private void initNetwork() {
        NetworkHelper.sharedHelper().registerNetworkSensor(this);
    }
}
