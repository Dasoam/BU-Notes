package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.year2.sem1.cpp;
import com.dhdash.bunotes.year2.sem1.ims_activity;
import com.dhdash.bunotes.year2.sem1.microprocessor;
import com.dhdash.bunotes.year2.sem1.probability_statistics;
import com.dhdash.bunotes.year2.sem1.software_engineering;
import com.google.firebase.analytics.FirebaseAnalytics;

public class subjects_year2_sem1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectsy2sem1);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "5"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Sub_yr2_sem1"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);    }
    public void cpp(View view){
        startActivity(new Intent(getApplicationContext(), cpp.class));
    }
    public void ims(View view){
        startActivity(new Intent(getApplicationContext(), ims_activity.class));
    }
    public void ps(View view){
        startActivity(new Intent(getApplicationContext(), probability_statistics.class));
    }
    public void st(View view){
        startActivity(new Intent(getApplicationContext(), software_engineering.class));
    }
    public void mp(View view){
        startActivity(new Intent(getApplicationContext(), microprocessor.class));
    }
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
}