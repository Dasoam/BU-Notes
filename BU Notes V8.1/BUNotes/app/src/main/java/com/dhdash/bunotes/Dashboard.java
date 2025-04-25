package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.dhdash.bunotes.books.Books;
import com.dhdash.bunotes.books.books2y;
import com.dhdash.bunotes.pyqb.pyqac;
import com.dhdash.bunotes.ytplaylists.year1sem2.ytvideos_year1_sem2;
import com.dhdash.bunotes.ytplaylists.year2sem2.ytvideos_year2_sem2;
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
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Dashboard extends AppCompatActivity {
    //private InterstitialAd mInterstitialAd;
    private FirebaseFirestore db;
    private FirebaseAnalytics mFirebaseAnalytics;
    private FirebaseUser user;
    private String email,year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);


        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Dashboard"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
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

            DocumentReference dr= db.document("Users/"+email);
            dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if(task.isSuccessful()){
                        DocumentSnapshot document=task.getResult();
                        if(document.exists()){
                            year=document.getString("Year").trim();
                            if(year.equals("1")) {
                                startActivity(new Intent(getApplicationContext(), subjects_year1_sem1.class));
                                //finish();
                            } else if (year.equals("2")) {
                                startActivity(new Intent(getApplicationContext(), subjects_year2_sem1.class));
                                //finish();
                            }
                        }
                    }
                }
            });
        //}

    }
    public void Sem2subjects(View view){
        DocumentReference dr= db.document("Users/"+email);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        year=document.getString("Year").trim();
                        if(year.equals("1")) {
                            startActivity(new Intent(getApplicationContext(), subjects_year1_sem2.class));
                            //finish();
                        } else if (year.equals("2")) {
                            startActivity(new Intent(getApplicationContext(), subjects_year2_sem2.class));
                            //finish();
                        }
                    }
                }
            }
        });
        //startActivity(new Intent(getApplicationContext(), subjects_year1_sem2.class));
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
        //}

    }
    public void pyq(View view){
        startActivity(new Intent(getApplicationContext(), pyqac.class));

    }
    public void lmsopen(View view){
        startActivity(new Intent("android.intent.action.VIEW",
                Uri.parse("https://lms.bennett.edu.in/login/index.php")));
    }


    public void ytvideos(View view) {
        DocumentReference dr= db.document("Users/"+email);
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        year=document.getString("Year").trim();
                        if(year.equals("1")) {
                            startActivity(new Intent(getApplicationContext(), ytvideos_year1_sem2.class));
                            //finish();
                        } else if (year.equals("2")) {
                            startActivity(new Intent(getApplicationContext(), ytvideos_year2_sem2.class));
                            //finish();
                        }
                    }
                }
            }
        });
        //startActivity(new Intent(getApplicationContext(), ytvideos_year2_sem2.class));
        //startActivity(new Intent(getApplicationContext(), ytvideos_year1_sem2.class));
    }
}