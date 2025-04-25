package com.example.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

    }
    public void Sem1subjects(View view){
        startActivity(new Intent(getApplicationContext(),subjects.class));
    }
    public void Sem2subjects(View view){
        startActivity(new Intent(getApplicationContext(),subjectssem2.class));
    }
    public void Home(View view){
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        finish();
    }
    public void uccount(View view){
        startActivity(new Intent(getApplicationContext(),useraccount.class));
        finish();
    }
    public void books(View view){
        startActivity(new Intent(getApplicationContext(),Books.class));
    }
    public void pyq(View view){
        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        Toast.makeText(this, "Not Uploaded Yet", Toast.LENGTH_LONG).show();
    }
}