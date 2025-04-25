package com.dhdash.bunotes.ytplaylists.year1sem2;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

import com.dhdash.bunotes.R;

public class yt_digital extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yt_digital);

        webView = findViewById(R.id.wv_yt_digital);
        loadYouTubePlaylist();
    }

    @SuppressLint("SetJavaScriptEnabled")
    private void loadYouTubePlaylist() {
        webView.setWebViewClient(new WebViewClient());
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true); // Enable JavaScript

        // Load YouTube playlist URL
        webView.loadUrl("https://youtube.com/playlist?list=PLXjv5Gt7zWOR5Z-CoFr-SXpRIIb2ZTuRG&si=kc2CF39O4U24LLI9");
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
