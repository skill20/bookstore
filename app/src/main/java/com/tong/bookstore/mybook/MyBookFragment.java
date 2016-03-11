package com.tong.bookstore.mybook;

import android.os.Handler;
import android.os.Message;

import com.tong.bookstore.R;
import com.tong.bookstore.netview.NetworkFragment;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 17:37
 * @since 5.3.0
 */
public class MyBookFragment extends NetworkFragment {


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_mybook;
    }


    @Override
    protected void initViews() {
        showLoading();
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.sendEmptyMessageDelayed(0, 3000);
            }
        }).start();
    }

     Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            showContent();
        }
    };
}
