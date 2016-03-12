package com.tong.bookstore;

import android.content.Context;
import android.os.AsyncTask;

import com.tong.bookstore.bookstore.MyRecycleViewAdapter;
import com.tong.bookstore.database.DataOperator;
import com.tong.bookstore.database.SQLiteHelper;

import java.util.List;

/**
 * Created by tong.zhang on 2016/3/12.
 */
public class QueryAsyncTask extends AsyncTask<Void, Void, List<MyRecycleViewAdapter.BookBean>> {

    private Context context;

    public QueryAsyncTask(Context context) {
        this.context = context;
    }

    @Override
    protected List<MyRecycleViewAdapter.BookBean> doInBackground(Void... params) {
        return new DataOperator(SQLiteHelper.getDB(context)).find();
    }

    @Override
    protected void onPostExecute(List<MyRecycleViewAdapter.BookBean> bookBeanList) {
        super.onPostExecute(bookBeanList);
        if (resultInterface != null) {
            resultInterface.result(bookBeanList);
        }
    }

    public interface ResultInterface<T> {
        void result(T t);
    }

    private ResultInterface resultInterface;

    public void setResultLister(ResultInterface resultInterface) {
        this.resultInterface = resultInterface;
    }
}
