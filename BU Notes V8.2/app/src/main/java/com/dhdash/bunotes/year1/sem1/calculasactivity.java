package com.dhdash.bunotes.year1.sem1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.dhdash.bunotes.R;

public class calculasactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculasactivity);
    }
    public void calculusmod1(View view){
        startActivity(new Intent(calculasactivity.this, Calculusmod1.class));
    }
    public void calculusmod2(View view){
        startActivity(new Intent(calculasactivity.this, Calculusmod2.class));
    }
    public void calculusmod3(View view){
        startActivity(new Intent(calculasactivity.this, Calculusmod3.class));
    }

}