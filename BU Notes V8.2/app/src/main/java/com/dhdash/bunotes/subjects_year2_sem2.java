package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.year1.sem2.mechanics;
import com.dhdash.bunotes.year2.sem2.computer_network;
import com.dhdash.bunotes.year2.sem2.daa;
import com.dhdash.bunotes.year2.sem2.operating_system;
import com.google.firebase.analytics.FirebaseAnalytics;

public class subjects_year2_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects_year2_sem2);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "4"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Sub_yr2_sem2"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }

    public void DAA(View view){
        startActivity(new Intent(getApplicationContext(), daa.class));
    }
    public void CN(View view){
        startActivity(new Intent(getApplicationContext(), computer_network.class));
    }
    public void OS(View view){
        startActivity(new Intent(getApplicationContext(), operating_system.class));
    }
}