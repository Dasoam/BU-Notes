package com.dhdash.bunotes.pyqb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.R;
import com.google.firebase.analytics.FirebaseAnalytics;

public class pyqac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyqac);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "10"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "pyqac"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }
    public void sem1midsem(View view){
        startActivity(new Intent(getApplicationContext(),sem1pyq1.class));
    }
    public void sem1endsem(View view){
        startActivity(new Intent(getApplicationContext(),sem1pyq2.class));
    }
}