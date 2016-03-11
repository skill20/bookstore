package com.tong.bookstore.mybook;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.tong.bookstore.QueryAsyncTask;
import com.tong.bookstore.R;
import com.tong.bookstore.bookstore.MyRecycleViewAdapter;
import com.tong.bookstore.database.DataOperator;
import com.tong.bookstore.database.SQLiteHelper;
import com.tong.bookstore.netview.NetworkFragment;
import com.tong.bookstore.util.ToastUtil;

import java.util.List;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 17:37
 * @since 5.3.0
 */
public class MyBookFragment extends NetworkFragment {


    private Button bookQuery;
    private QueryAsyncTask queryAsyncTask;
    private TextView textView;
    private StringBuilder stringBuilder;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_mybook;
    }


    @Override
    protected void findViewByIds(View layout) {
        super.findViewByIds(layout);
        bookQuery = (Button) layout.findViewById(R.id.book_query);
        textView = (TextView) layout.findViewById(R.id.text_view);
        stringBuilder = new StringBuilder();
    }

    @Override
    protected void initViews() {
        showContent();
        bookQuery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queryAsyncTask = new QueryAsyncTask(getContext().getApplicationContext());
                queryAsyncTask.setResultLister(new QueryAsyncTask.ResultInterface<List<MyRecycleViewAdapter.BookBean>>() {
                    @Override
                    public void result(List<MyRecycleViewAdapter.BookBean> bookBeanList) {
                        int size = bookBeanList.size();
                        if (size == 0) {
                            ToastUtil.showToast(getContext().getApplicationContext(),"no book in database,please add book first!");
                            return;
                        }
                        stringBuilder.delete(0, stringBuilder.length());
                        for (int i = 0; i < size; i++) {
                            if (i == size - 1) {
                                stringBuilder.append(bookBeanList.get(i).desc);
                            } else {
                                stringBuilder.append(bookBeanList.get(i).desc);
                                stringBuilder.append("-");
                            }
                        }
                        textView.setText(stringBuilder.toString());
                    }
                });
                queryAsyncTask.execute();
            }
        });
    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            showContent();
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacksAndMessages(null);
        queryAsyncTask.cancel(true);
    }
}
