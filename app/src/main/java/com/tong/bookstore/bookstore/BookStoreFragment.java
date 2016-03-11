package com.tong.bookstore.bookstore;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.tong.bookstore.R;
import com.tong.bookstore.netview.NetworkFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 18:36
 * @since 5.3.0
 */
public class BookStoreFragment extends NetworkFragment {

    private RecyclerView recycleView;
    private MyRecycleViewAdapter myRecycleViewAdapter;

    private static final int[] ids = {R.drawable.book1, R.drawable.book2, R.drawable.book3,
            R.drawable.book4, R.drawable.book5, R.drawable.book6};

    private static final String[] desc = {"Math", "English", "Hadoop",
            "C++", "Java", "Android"};

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_bookstore;
    }

    @Override
    protected void findViewByIds(View layout) {
        super.findViewByIds(layout);
        showLoading();
        FragmentActivity activity = getActivity();
        List<MyRecycleViewAdapter.BookBean> bookList = getData();
        recycleView = (RecyclerView) layout.findViewById(R.id.recycle_view);
        recycleView.setLayoutManager(new GridLayoutManager(activity, 3));
        myRecycleViewAdapter = new MyRecycleViewAdapter(activity);
        myRecycleViewAdapter.setData(bookList);
        recycleView.setAdapter(myRecycleViewAdapter);
        showContent();
    }

    public List<MyRecycleViewAdapter.BookBean> getData() {
        ArrayList<MyRecycleViewAdapter.BookBean> beanArrayList = new ArrayList<>();
        int size = ids.length;
        MyRecycleViewAdapter.BookBean bookBean = null;
        for (int i = 0; i < size; i++) {
            bookBean = new MyRecycleViewAdapter.BookBean();
            bookBean.imageId = ids[i];
            bookBean.desc = desc[i];
            beanArrayList.add(bookBean);
        }

        return beanArrayList;
    }
}
