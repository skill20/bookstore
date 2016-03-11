package com.tong.bookstore;

import com.tong.bookstore.netview.NetworkFragment;

/**
 * @author qingqing.wang
 * @version 1.0.0
 * @date 2016-03-11 18:36
 * @since 5.3.0
 */
public class BookStoreFragment extends NetworkFragment {
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_bookstore;
    }
}
