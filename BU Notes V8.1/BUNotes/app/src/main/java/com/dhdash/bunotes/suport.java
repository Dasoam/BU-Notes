package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.analytics.FirebaseAnalytics;

public class suport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suport);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "2"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Support"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }
}