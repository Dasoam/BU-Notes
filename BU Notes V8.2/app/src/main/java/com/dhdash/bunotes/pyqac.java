package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class pyqac extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pyqac);
    }
    public void sem1midsem(View view){
        startActivity(new Intent(getApplicationContext(),sem1pyq1.class));
    }
    public void sem1endsem(View view){
        startActivity(new Intent(getApplicationContext(),sem1pyq2.class));
    }
}