package com.example.miniproiect2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.miniproiect2.Utils.DialogUtil;

public class WebNew extends AppCompatActivity {                                                     //
    private WebView webView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.web_view);
        initView();

    }

    private void initView() {
        webView = findViewById(R.id.wv_View);

        String link;
        Intent intent = getIntent();
        link = intent.getStringExtra(MainActivity.EXTRA_LINK);

        webView.setWebViewClient(new WebViewClient());                                              //tất cẩ các thao tác sau đó sẽ thao tác hết trên web View
        webView.loadUrl(link);
        DialogUtil.dismiss();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
    }



    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
