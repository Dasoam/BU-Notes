package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;

public class subjectsy2sem1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjectsy2sem1);
    }
    public void cpp(View view){
        startActivity(new Intent(getApplicationContext(), cpp.class));
    }
    public void ims(View view){
        startActivity(new Intent(getApplicationContext(), imsactivity.class));
    }
    public void ps(View view){
        startActivity(new Intent(getApplicationContext(), ps.class));
    }
    public void st(View view){
        startActivity(new Intent(getApplicationContext(),sengin.class));
    }
    public void mp(View view){
        startActivity(new Intent(getApplicationContext(), mipp.class));
    }
}