//package com.example.bunotes;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class sem1pyq1 extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_sem1pyq1);
//    }
//}
package com.dhdash.bunotes;

        import android.app.AlertDialog;
        import android.content.DialogInterface;
        import android.graphics.Bitmap;
        import android.os.Bundle;
        import android.view.WindowManager;
        import android.webkit.JavascriptInterface;
        import android.webkit.WebSettings;
        import android.webkit.WebView;
        import android.webkit.WebViewClient;

        import androidx.appcompat.app.AppCompatActivity;

public class sem1pyq1 extends AppCompatActivity {
    private WebView wv;
    private int fc=0;
    private String dcUrl="https://drive.google.com/drive/folders/1ulCRhegvptCOcftbXInPhhqVaHWaw1P-?usp=drive_link";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_sem1pyq1);
        // initialising the web view
        wv = (WebView) findViewById(R.id.webviewsem1pyq1);

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
            //dcUrl=url;
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
            AlertDialog alertDialog = new AlertDialog.Builder(sem1pyq1.this).create();
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