package com.tong.bookstore;

import android.os.Handler;
import android.os.Message;

import com.tong.bookstore.netview.NetworkFragment;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 18:36
 * @since 5.3.0
 */
public class MarketFragment extends NetworkFragment {
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_market;
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
            showFail();
        }
    };
}
