package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class subjects extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);
    }
    public void Python(View view){
        startActivity(new Intent(getApplicationContext(),pythonactivity.class));
    }
    public void calculas(View view){
        startActivity(new Intent(getApplicationContext(),calculasactivity.class));
    }
    public void electro(View view){
        startActivity(new Intent(getApplicationContext(),emactivity.class));
    }
    public void circuit(View view){
        startActivity(new Intent(getApplicationContext(),eceactivity.class));
    }
    public void newl(View view){
        startActivity(new Intent(getApplicationContext(), newactivity.class));
    }
    public void fondation(View view){
        startActivity(new Intent(getApplicationContext(),fondaactivity.class));
    }
    public void environ(View view){
        startActivity(new Intent(getApplicationContext(),environmentactivity.class));
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
}