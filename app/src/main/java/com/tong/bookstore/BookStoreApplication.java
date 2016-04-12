package com.tong.bookstore;

import android.app.Application;
import android.content.Intent;

import com.tong.bookstore.service.BookService;

/**
 * @author tong.zhang
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
        initLog();
        initNetwork();
        initService();
    }


    private void initService() {
        startService(new Intent(getApplicationContext(), BookService.class));
    }

    private void initLog() {
    }

    private void initNetwork() {
    }
}
