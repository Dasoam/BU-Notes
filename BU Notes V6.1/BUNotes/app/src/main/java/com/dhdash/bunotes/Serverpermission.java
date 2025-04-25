package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.security.PrivateKey;

public class Serverpermission extends AppCompatActivity {
    private TextView time;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_serverpermission);
        db = FirebaseFirestore.getInstance();
        time=findViewById(R.id.time);
        //time.setText("");
        maintain();
    }
    public void maintain(){
        DocumentReference dr=db.document("Check/checkfor");
        dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful()){
                    DocumentSnapshot document=task.getResult();
                    if(document.exists()){
                        String text=document.getString("Time").trim();
                        time.setText(text);
                    }
                }
            }
        });
    }
}