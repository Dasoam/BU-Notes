package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class emactivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emactivity);
    }
    public void electromagneticsmod1(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod1.class));
    }
    public void electromagneticsmod2(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod2.class));
    }
    public void electromagneticsmod3(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod3.class));
    }
    public void electromagneticsmod4(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod4.class));
    }
    public void electromagneticsmod5(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod5.class));
    }
    public void electromagneticsmod6(View view){
        startActivity(new Intent(emactivity.this,Electromagneticsmod6.class));
    }
}