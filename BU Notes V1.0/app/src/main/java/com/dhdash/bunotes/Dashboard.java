package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;

public class Dashboard extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });


    }
    public void Sem1subjects(View view){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(Dashboard.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                //@Override
//                public void onAdClicked() {
//                    // Called when a click is recorded for an ad.
//                    Log.d(TAG, "Ad was clicked.");
//                }

                @Override
                public void onAdDismissedFullScreenContent() {
                    // Called when ad is dismissed.
                    // Set the ad reference to null so you don't show the ad a second time.

                    startActivity(new Intent(getApplicationContext(),subjects.class));
                    mInterstitialAd = null;
                }

//                @Override
//                public void onAdFailedToShowFullScreenContent(AdError adError) {
//                    // Called when ad fails to show.
//                    Log.e(TAG, "Ad failed to show fullscreen content.");
//                    mInterstitialAd = null;
//                }

//                @Override
//                public void onAdImpression() {
//                    // Called when an impression is recorded for an ad.
//                    Log.d(TAG, "Ad recorded an impression.");
//                }

//                @Override
//                public void onAdShowedFullScreenContent() {
//                    // Called when ad is shown.
//                    Log.d(TAG, "Ad showed fullscreen content.");
//                }
            });
        } else {

            startActivity(new Intent(getApplicationContext(),subjects.class));
        }

    }
    public void Sem2subjects(View view){
        startActivity(new Intent(getApplicationContext(),subjectssem2.class));
    }
    public void Home(View view){
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
    public void uccount(View view){
        startActivity(new Intent(getApplicationContext(),useraccount.class));
        finish();
    }
    public void books(View view){
        if (mInterstitialAd != null) {
            mInterstitialAd.show(Dashboard.this);
            mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                @Override
            public void onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                // Set the ad reference to null so you don't show the ad a second time.

                startActivity(new Intent(getApplicationContext(),Books.class));
                mInterstitialAd = null;
            }
            });
        } else {

            startActivity(new Intent(getApplicationContext(),Books.class));
        }

    }
    public void pyq(View view){
        startActivity(new Intent(getApplicationContext(),pyqac.class));

    }

    @Override
    protected void onStart() {
        super.onStart();
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.changead), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });
    }
}