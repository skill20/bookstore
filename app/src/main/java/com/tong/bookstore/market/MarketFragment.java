package com.tong.bookstore.market;

import android.graphics.Bitmap;
import android.view.View;
import android.view.ViewStub;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.tong.bookstore.R;
import com.tong.bookstore.netview.NetworkFragment;

/**
 * @author tong.zhang
 * @version 1.0.0
 * @date 2016-03-11 18:36
 * @since 5.3.0
 */
public class MarketFragment extends NetworkFragment {

    private static final int ERROR_CODE = 0;
    private WebView webView;
    private View loadView;
    private View failView;
    private int responseCode;
    private static final String AMAZE_URL = "http://www.amazon.cn/%E8%BF%9B%E5%8F%A3%E5%8E%9F%E7%89%88%E5%9B%BE%E4%B9%A6/b/ref=sd_allcat_books_l2_b2045366051?ie=UTF8&node=2045366051";


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_market;
    }

    @Override
    protected void findViewByIds(View layout) {
        super.findViewByIds(layout);
        webView = (WebView) layout.findViewById(R.id.web_view);
        loadView = layout.findViewById(R.id.loading);
        ViewStub stubFail = (ViewStub) layout.findViewById(R.id.net_fail_stub);
        failView = stubFail.inflate();
        failView.setVisibility(View.GONE);
        startWebView();
    }

    private void startWebView() {
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                super.onPageStarted(view, url, favicon);
                loadView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webView.setVisibility(View.VISIBLE);
                failView.setVisibility(View.GONE);
                loadView.setVisibility(View.GONE);
                if (responseCode != ERROR_CODE) {
                    failView.setVisibility(View.VISIBLE);
                    webView.setVisibility(View.GONE);
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                super.onReceivedError(view, errorCode, description, failingUrl);
                responseCode = errorCode;
            }
        });
        WebSettings webSettings = webView.getSettings();
        // webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);

        webView.loadUrl(AMAZE_URL);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        webView.destroy();
    }
}
