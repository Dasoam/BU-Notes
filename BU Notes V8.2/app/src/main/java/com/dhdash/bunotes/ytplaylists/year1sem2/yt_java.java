package com.dhdash.bunotes.ytplaylists.year1sem2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.dhdash.bunotes.R;

public class yt_java extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_java);

        webView = findViewById(R.id.wv_yt_java);
        loadYouTubePlaylist();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadYouTubePlaylist() {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        // Load YouTube playlist URL
        webView.loadUrl("https://youtu.be/bSrm9RXwBaI?si=gyXd5ksQ5DVJOPVR");
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
