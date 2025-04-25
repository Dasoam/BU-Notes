package com.dhdash.bunotes;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class logoscrap extends AppCompatActivity {
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE); // hide the title
        getSupportActionBar().hide(); // hide the title bar
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN); //enable full screen


        setContentView(R.layout.activity_logoscrap);
        db = FirebaseFirestore.getInstance();
        if(check_conn()) {
            loding();
        }
        else {
            Toast.makeText(this.getApplicationContext(),"No Internet Found! Please Restart the App.",Toast.LENGTH_LONG).show();
        }
    }
    private void loding(){
        DocumentReference dr=db.document("Check/checkfor");
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        String flag=document.getString("flag").trim();
                        Long app_open=document.getLong("app_open");
                        int app_open_cnt=app_open.intValue();
                        ++app_open_cnt;
                        dr.update("app_open",app_open_cnt);
                        if (flag.equals("1")){
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(getApplicationContext(), loginsingup.class));
                                    finish();
                                }
                            },200);
                        }
                        else {
                            startActivity(new Intent(getApplicationContext(), Serverpermission.class));
                            finish();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    }
                }
                else {
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

    }
    public boolean check_conn(){
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        return false;
    }
}