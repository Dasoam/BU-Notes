package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class eceactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eceactivity);
    }
    public void ECEmod1(View view){
        startActivity(new Intent(eceactivity.this,ECEmod1.class));
    }
    public void ECEmod2(View view){
        startActivity(new Intent(eceactivity.this,ECEmod2.class));
    }
}