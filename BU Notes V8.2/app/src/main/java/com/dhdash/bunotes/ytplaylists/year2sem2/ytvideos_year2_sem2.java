package com.dhdash.bunotes.ytplaylists.year2sem2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.R;
import com.google.firebase.analytics.FirebaseAnalytics;


public class ytvideos_year2_sem2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ytvideos_year2_sem2);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "8"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "yt_viedo_yr2_sem2"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }

    public void YT_DAA(View view) {
        startActivity(new Intent(getApplicationContext(), yt_daa.class));
    }

    public void YT_CN(View view) {
        startActivity(new Intent(getApplicationContext(), yt_cn.class));
    }

    public void YT_OS(View view) {
        startActivity(new Intent(getApplicationContext(), yt_os.class));
    }


}