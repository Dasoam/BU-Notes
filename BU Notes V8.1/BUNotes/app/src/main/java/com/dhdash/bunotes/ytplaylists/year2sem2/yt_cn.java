package com.dhdash.bunotes.ytplaylists.year2sem2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.dhdash.bunotes.R;

public class yt_cn extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_cn);

        webView = findViewById(R.id.wv_yt_cn);
        loadYouTubePlaylist();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadYouTubePlaylist() {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        // Load YouTube playlist URL
        webView.loadUrl("https://youtube.com/playlist?list=PLO25TVPUYF310Xbr8AUznsm21bjIj_OPj&si=7O7_E2Yk3d7ljCF7");
    }

    // Override onBackPressed to handle WebView navigation
    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }
}
