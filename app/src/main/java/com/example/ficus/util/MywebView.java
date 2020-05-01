package com.example.ficus.util;

import android.content.Context;
import android.net.http.SslError;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MywebView extends WebView {
    private String address;
    private WebView webView;

    public MywebView(Context context, WebView webView, String address) {
        super(context);
        this.address=address;
        this.webView=webView;
    }

    public void loadUrl()
    {
        WebSettings webSettings = webView.getSettings();
        webSettings.setUseWideViewPort(true); // 缩放至屏幕的大小 webSettings
        webSettings.setLoadWithOverviewMode(true); //设置默认编码
        webSettings .setDefaultTextEncodingName("utf-8"); ////设置自动加载图片
        webSettings .setLoadsImagesAutomatically(true);
        webSettings.setJavaScriptEnabled(true);// 设置可以运行JS脚本
        webSettings.setSupportZoom(false);// 用于设置webview放大
        webSettings.setBuiltInZoomControls(false);
        webView.getSettings().setDomStorageEnabled(true);

        webView.getSettings().setAppCacheMaxSize(1024*1024*8);

        webView.getSettings().setAllowFileAccess(true);

        webView.getSettings().setAppCacheEnabled(true);
        // 直接创建WebViewClient,用于加载H5
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
            }
            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                if (error.getPrimaryError() == android.net.http.SslError.SSL_DATE_INVALID
                        || error.getPrimaryError() == android.net.http.SslError.SSL_EXPIRED
                        || error.getPrimaryError() == android.net.http.SslError.SSL_INVALID
                        || error.getPrimaryError() == android.net.http.SslError.SSL_UNTRUSTED) {
                    handler.proceed();
                } else {
                    handler.cancel();
                }
            }
        });
        webView.loadUrl(address);
    }
}
