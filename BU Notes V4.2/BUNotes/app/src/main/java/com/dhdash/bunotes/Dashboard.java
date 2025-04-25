package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
    private InterstitialAd mInterstitialAd;
    private FirebaseFirestore db;
    private FirebaseUser user;
    private String email,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
        db = FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        email=user.getEmail().trim();
        DocumentReference dr= db.document("Users/"+email);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        year=document.getString("Year").trim();
                    }
                }
            }
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

                    DocumentReference dr= db.document("Users/"+email);
                    dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot document=task.getResult();
                                if(document.exists()){
                                    year=document.getString("Year").trim();
                                    if(year.equals("1")) {
                                        startActivity(new Intent(getApplicationContext(), subjects.class));
                                        mInterstitialAd = null;
                                    } else if (year.equals("2")) {
                                        startActivity(new Intent(getApplicationContext(), subjectsy2sem1.class));
                                        mInterstitialAd = null;
                                    }
                                }
                            }
                        }
                    });
//
                }


            });
        } else {

            DocumentReference dr= db.document("Users/"+email);
            dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document=task.getResult();
                        if(document.exists()){
                            year=document.getString("Year").trim();
                            if(year.equals("1")) {
                                startActivity(new Intent(getApplicationContext(), subjects.class));

                            } else if (year.equals("2")) {
                                startActivity(new Intent(getApplicationContext(), subjectsy2sem1.class));

                            }
                        }
                    }
                }
            });
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

                    DocumentReference dr= db.document("Users/"+email);
                    dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                            if(task.isSuccessful()){
                                DocumentSnapshot document=task.getResult();
                                if(document.exists()){
                                    year=document.getString("Year").trim();
                                    if(year.equals("1")) {
                                        startActivity(new Intent(getApplicationContext(), Books.class));
                                        mInterstitialAd = null;
                                    } else if (year.equals("2")) {
                                        startActivity(new Intent(getApplicationContext(), books2y.class));
                                        mInterstitialAd = null;
                                    }
                                }
                            }
                        }
                    });
            }
            });
        } else {

            DocumentReference dr= db.document("Users/"+email);
            dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document=task.getResult();
                        if(document.exists()){
                            year=document.getString("Year").trim();
                            if(year.equals("1")) {
                                startActivity(new Intent(getApplicationContext(), Books.class));
                                //mInterstitialAd = null;
                            } else if (year.equals("2")) {
                                startActivity(new Intent(getApplicationContext(), books2y.class));
                                //mInterstitialAd = null;
                            }
                        }
                    }
                }
            });
        }

    }
    public void pyq(View view){
        startActivity(new Intent(getApplicationContext(),pyqac.class));

    }
    public void lmsopen(View view){
        startActivity(new Intent("android.intent.action.VIEW",
                Uri.parse("https://lms.bennett.edu.in/login/index.php")));
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