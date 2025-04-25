package com.dhdash.bunotes.books;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.year1.sem1.Calculus;
import com.dhdash.bunotes.R;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.firebase.analytics.FirebaseAnalytics;

public class Books extends AppCompatActivity {
    //private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "11"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Books"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);

    }
    public void PythonBooks(View view){
        startActivity(new Intent(Books.this, PythonBooks.class));
    }
    public void ElectromagneticsBooks(View view){
        startActivity(new Intent(Books.this, ElectromagneticsBooks.class));
    }
    public void ECEBooks(View view){
        startActivity(new Intent(Books.this, ECEBooks.class));
    }
    public void CalculusBooks(View view){
        startActivity(new Intent(Books.this, Calculus.class));
    }
}