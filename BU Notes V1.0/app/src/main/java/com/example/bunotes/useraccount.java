package com.example.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

public class useraccount extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_useraccount);
    }
    public void Home(View view){
        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        startActivity(new Intent(getApplicationContext(),Dashboard.class));
        finish();
    }
    public void uccount(View view){
        startActivity(new Intent(getApplicationContext(),useraccount.class));
        finish();
    }
    public void download(View view){
        Toast.makeText(this, "No Downloads Found", Toast.LENGTH_LONG).show();
    }
    public void changepassword(View view){
        startActivity(new Intent(getApplicationContext(),changepassword.class));
    }
    public void support(View view){
        startActivity(new Intent(getApplicationContext(),suport.class));
        Toast.makeText(this, "You can also suggest us to add other notes", Toast.LENGTH_LONG).show();
    }
    public void playstore(View view){
        String url="";
        if (url.isEmpty()){
            Toast.makeText(this, "More apps Launching Soon", Toast.LENGTH_SHORT).show();}
        else{
            startActivity(new Intent(String.valueOf(getApplicationContext()), Uri.parse(url)));
            }

    }
    public void logout(View view){
        //code to logout//

        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_LONG).show();
    }
    public void verify(View view){
        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(1000);
        startActivity(new Intent(getApplicationContext(), Books.class));
        Toast.makeText(this, "verify to change password", Toast.LENGTH_LONG).show();
    }

}