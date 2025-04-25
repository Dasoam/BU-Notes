package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.year1.sem1.calculasactivity;
import com.dhdash.bunotes.year1.sem1.eceactivity;
import com.dhdash.bunotes.year1.sem1.emactivity;
import com.dhdash.bunotes.year1.sem1.environmentactivity;
import com.dhdash.bunotes.year1.sem1.fondaactivity;
import com.dhdash.bunotes.year1.sem1.new_age_lif_skills;
import com.dhdash.bunotes.year1.sem1.pythonactivity;
import com.google.firebase.analytics.FirebaseAnalytics;

public class subjects_year1_sem1 extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
        FirebaseAnalytics firebaseAnalytics = FirebaseAnalytics.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "7"); // Replace with a unique identifier for your activity
        bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "Sub_yr1_sem1"); // Replace with the name of your activity
        bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE, "activity");
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN,bundle);
    }
    public void Python(View view){
        startActivity(new Intent(getApplicationContext(), pythonactivity.class));
    }
    public void calculas(View view){
        startActivity(new Intent(getApplicationContext(), calculasactivity.class));
    }
    public void electro(View view){
        startActivity(new Intent(getApplicationContext(), emactivity.class));
    }
    public void circuit(View view){
        startActivity(new Intent(getApplicationContext(), eceactivity.class));
    }
    public void newl(View view){
        startActivity(new Intent(getApplicationContext(), new_age_lif_skills.class));
    }
    public void fondation(View view){
        startActivity(new Intent(getApplicationContext(), fondaactivity.class));
    }
    public void environ(View view){
        startActivity(new Intent(getApplicationContext(), environmentactivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
}