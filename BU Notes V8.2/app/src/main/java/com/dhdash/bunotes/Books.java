package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;

public class Books extends AppCompatActivity {
    private AdView adView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {
            }
        });
        adView = findViewById(R.id.bookad);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);
    }
    public void PythonBooks(View view){
        startActivity(new Intent(Books.this,PythonBooks.class));
    }
    public void ElectromagneticsBooks(View view){
        startActivity(new Intent(Books.this,ElectromagneticsBooks.class));
    }
    public void ECEBooks(View view){
        startActivity(new Intent(Books.this,ECEBooks.class));
    }
    public void CalculusBooks(View view){
        startActivity(new Intent(Books.this,Calculus.class));
    }
}