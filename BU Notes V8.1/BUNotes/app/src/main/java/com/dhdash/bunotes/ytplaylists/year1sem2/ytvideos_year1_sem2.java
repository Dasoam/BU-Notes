package com.dhdash.bunotes.ytplaylists.year1sem2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.R;
import com.dhdash.bunotes.ytplaylists.year2sem2.yt_daa;
import com.google.firebase.analytics.FirebaseAnalytics;

public class ytvideos_year1_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytvideos_year1_sem2);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "9"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Yt_viedo_yr1_sem2"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }

    public void YT_Java(View view) {
        startActivity(new Intent(getApplicationContext(), yt_java.class));
    }

    public void YT_Linear(View view) {
        startActivity(new Intent(getApplicationContext(), yt_linear.class));
    }

    public void YT_Discrete(View view) {
        startActivity(new Intent(getApplicationContext(), yt_discrete.class));
    }

    public void YT_Mechanics(View view) {
        startActivity(new Intent(getApplicationContext(), yt_mechanics.class));
    }

    public void YT_Digital(View view) {
        startActivity(new Intent(getApplicationContext(), yt_digital.class));
    }
}