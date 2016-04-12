package com.tong.bookstore.service;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.tong.bookstore.BookStoreNotification;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-04-12 14:10
 * @since 5.3.0
 */
public class BookService extends Service {

    private static final String INSERT_PARAM = "insert_param";

    public static void startBookService(Context context, String value) {
        Intent service = new Intent(context, BookService.class);
        service.putExtra(INSERT_PARAM, value);
        context.startService(service);
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if (intent != null && intent.hasExtra(INSERT_PARAM)) {
            String extra = intent.getStringExtra(INSERT_PARAM);
            Context context = getApplicationContext();
            String title = " insert success!";
            BookStoreNotification.notify(context, title, extra);
        }


        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
