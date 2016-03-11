package com.tong.bookstore;

import android.os.Handler;
import android.os.Message;

import com.tong.bookstore.netview.NetworkFragment;

/**
 * @author qingqing.wang
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
