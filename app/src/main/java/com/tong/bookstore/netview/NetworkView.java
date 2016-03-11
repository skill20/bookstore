package com.tong.bookstore.netview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewStub;
import android.widget.FrameLayout;

import com.tong.bookstore.R;

/**
 * @author qingqing.wang
 * @version 1.0.0
 * @date 2016-03-11 14:15
 * @since 5.3.0
 */
public class NetworkView extends FrameLayout {

    private View loadView;
    private ViewStub failStub;
    private View failView;
    private View rootView;

    private OnClickListener onClickListener;

    public NetworkView(Context context) {
        this(context, null);
    }

    public NetworkView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public NetworkView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        rootView = LayoutInflater.from(context).inflate(R.layout.include_net_view, null);
        loadView = rootView.findViewById(R.id.net_loading);
        addView(rootView);
    }

    public void loading() {
        loadView.setVisibility(VISIBLE);
    }

    public void fail() {
        if (failStub == null) {
            failStub = (ViewStub) findViewById(R.id.net_fail_stub);
            failView = failStub.inflate();
            View netClick = failView.findViewById(R.id.net_click);
            if (onClickListener != null) {
                netClick.setOnClickListener(onClickListener);
            }
        }
        failView.setVisibility(VISIBLE);
        loadView.setVisibility(GONE);
    }

    public void setOnNetFailClickLister(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
