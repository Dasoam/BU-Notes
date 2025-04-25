package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.year1.sem2.digital_design;
import com.dhdash.bunotes.year1.sem2.discrete;
import com.dhdash.bunotes.year1.sem2.java;
import com.dhdash.bunotes.year1.sem2.linear_algebra;
import com.dhdash.bunotes.year1.sem2.mechanics;
import com.google.firebase.analytics.FirebaseAnalytics;

public class subjects_year1_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_year1_sem2);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "6"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Sub_yr1_sem2"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);    }
    public void Java(View view){
        startActivity(new Intent(getApplicationContext(), java.class));
    }
    public void Linear(View view){
        startActivity(new Intent(getApplicationContext(), linear_algebra.class));
    }
    public void Discrete(View view){
        startActivity(new Intent(getApplicationContext(), discrete.class));
    }
    public void Mechanics(View view){
        startActivity(new Intent(getApplicationContext(), mechanics.class));
    }
    public void DD(View view){
        startActivity(new Intent(getApplicationContext(), digital_design.class));
    }

}