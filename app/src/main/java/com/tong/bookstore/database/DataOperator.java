package com.tong.bookstore.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.tong.bookstore.bookstore.MyRecycleViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tong.zhang on 2016/3/12.
 */
public class DataOperator {

    private static final String TAG = "DataOperator";

    private SQLiteDatabase db = null;

    public DataOperator(SQLiteDatabase db) {
        this.db = db;
    }

    public void insert(int imageId, String desc) {
        String sql = "INSERT INTO " + SQLiteHelper.TABLE_NAME + " (imageId,desc)" + " VALUES(?,?)";
        Object args[] = new Object[]{imageId, desc};
        this.db.execSQL(sql, args);
    }

    public void update(int imageId, String desc) {
        String sql = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET imageId=? WHERE desc=?";
        Object args[] = new Object[]{imageId, desc};
        this.db.execSQL(sql, args);
    }

    public void delete(int imageId) {
        String sql = "DELETE FROM " + SQLiteHelper.TABLE_NAME + " WHERE imageId=?";
        Object args[] = new Object[]{imageId};
        this.db.execSQL(sql, args);
    }

    public List<MyRecycleViewAdapter.BookBean> find() {
        List<MyRecycleViewAdapter.BookBean> all = new ArrayList<MyRecycleViewAdapter.BookBean>();
        String sql = "SELECT * FROM " + SQLiteHelper.TABLE_NAME;
        Cursor result = this.db.rawQuery(sql, null);
        try {
            MyRecycleViewAdapter.BookBean bookBean = null;
            for (result.moveToFirst(); !result.isAfterLast(); result.moveToNext()) {
                bookBean = new MyRecycleViewAdapter.BookBean();
                bookBean.imageId = result.getInt(result.getColumnIndex("imageId"));
                bookBean.desc = result.getString(result.getColumnIndex("desc"));
                all.add(bookBean);
            }
        } catch (Exception e) {
            if (result != null)
                result.close();
            Log.e(TAG, "data base exception");
        }

        return all;
    }
}
