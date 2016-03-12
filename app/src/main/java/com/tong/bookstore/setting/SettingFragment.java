package com.tong.bookstore.setting;

import android.os.Handler;
import android.os.Message;
import android.sax.RootElement;
import android.view.View;

import com.tong.bookstore.R;
import com.tong.bookstore.database.DataOperator;
import com.tong.bookstore.database.SQLiteHelper;
import com.tong.bookstore.netview.NetworkFragment;
import com.tong.bookstore.util.ToastUtil;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 18:36
 * @since 5.3.0
 */
public class SettingFragment extends NetworkFragment {

    private View clearView;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_setting;
    }

    @Override
    protected void findViewByIds(View layout) {
        super.findViewByIds(layout);
        clearView = layout.findViewById(R.id.clear_btn);
        clearView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (R.id.clear_btn == v.getId()) {
            clearDataBase();

        }
    }

    private static final int DELETE_DATABASE = 10;

    private void clearDataBase() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                boolean result = new DataOperator(SQLiteHelper.getDB(getContext().getApplicationContext())).delete();
                handler.obtainMessage(DELETE_DATABASE, result).sendToTarget();
            }
        }).start();
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (DELETE_DATABASE == msg.what) {
                boolean result = (boolean) msg.obj;
                String str;
                if (result) {
                    str = "data base deleted success!";
                } else {
                    str = "data base deleted fail!";
                }
                ToastUtil.showToast(getContext().getApplicationContext(), str);
            }
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
    }
}
