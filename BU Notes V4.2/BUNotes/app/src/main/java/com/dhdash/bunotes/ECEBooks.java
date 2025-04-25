package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class ECEBooks extends AppCompatActivity {
    private WebView wv;
    private int fc=0;
    private AdView adView;
    private String dcUrl="https://drive.google.com/drive/folders/1X0sqL6FWuCLnGfNFzI01tLPs27UTDoGJ?usp=drive_link";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ecebooks);
        // initialising the web view
        wv = (WebView) findViewById(R.id.webviewece);

        // add your link here
        wv.loadUrl(dcUrl);
        wv.setWebViewClient(new Client());
        WebSettings ws = wv.getSettings();

        // Enabling javascript
        ws.setJavaScriptEnabled(true);
        wv.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        wv.clearCache(true);
        wv.clearHistory();
        wv.addJavascriptInterface(new JavaScriptInterface(), "AndroidInterface");

        // download manager is a service that can be used to handle downloads
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });


        adView = findViewById(R.id.webad4);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

    }
    @Override
    public void onBackPressed() {

        String javascript = "javascript:(function() { " +
                "var documentElement = document.querySelector('[role=\"document\"]'); " +
                "var isOpen = (documentElement !== null && documentElement.style.display !== 'none'); " +
                "AndroidInterface.onDocumentElementStatusChanged(isOpen); " +
                "})();";

        wv.evaluateJavascript(javascript, null);
        try{
            Thread.sleep(500);
        }
        catch (Exception ex){

        }
        if(fc==1){
            dcUrl=wv.getUrl();
            wv.loadUrl(dcUrl);
        }
        else if (wv.isFocused() && wv.canGoBack()) {


            wv.goBack();

        }
        else {
            super.onBackPressed();

        }
    }
    class JavaScriptInterface {
        @JavascriptInterface
        public void onDocumentElementStatusChanged(boolean isOpen) {
            // Handle document element status change here
            if (isOpen) {
                // Document element is open
                // Add your code here
                fc=1;

            } else {
                // Document element is not open
                // Add your code here
                fc=0;

            }
        }
    }
    private class Client extends WebViewClient {
        // on page started load start loading the url
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);

        }

        // load the url of our drive
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            // JavaScript code to remove specific div elements
            String javascriptCode = "var elementsToRemove = document.querySelectorAll('.a-s-tb-sc-Ja.a-s-tb-Pe.a-N-Mf-Ec.a-s-tb-sc-Ja-fk, .a-N-s-uq');" +
                    "for (var i = 0; i < elementsToRemove.length; i++) {" +
                    "    elementsToRemove[i].remove();" +
                    "}";

            // Execute the JavaScript code
            wv.evaluateJavascript(javascriptCode, null);
        }

        public void onReceivedError(WebView webView, int errorCode, String description, String failingUrl) {
            // if stop loading
            try {
                webView.stopLoading();
            } catch (Exception e) {
            }

            if (webView.canGoBack()) {
                webView.goBack();
            }

            // if loaded blank then show error
            // to check internet connection using
            // alert dialog
            webView.loadUrl("about:blank");
            AlertDialog alertDialog = new AlertDialog.Builder(ECEBooks.this).create();
            alertDialog.setTitle("Error");
            alertDialog.setMessage("Check your internet connection and Try again.");
            alertDialog.setButton(DialogInterface.BUTTON_POSITIVE, "Try Again", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                    startActivity(getIntent());
                }
            });

            alertDialog.show();
            super.onReceivedError(webView, errorCode, description, failingUrl);
        }
    }
}