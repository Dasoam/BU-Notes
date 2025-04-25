package com.dhdash.bunotes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import org.checkerframework.checker.nullness.qual.NonNull;

public class changepassword extends AppCompatActivity {
    private EditText CurrentPassword,NewPassword,ConfirmPassword;
    private Button ChangePassword;
    private FirebaseUser user;
    private FirebaseFirestore db;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_changepassword);
        CurrentPassword=findViewById(R.id.CurrentPassword);
        NewPassword=findViewById(R.id.NewPassword);
        ConfirmPassword=findViewById(R.id.ConfirmPassword);
        ChangePassword=findViewById(R.id.ChangePassword);
        user = FirebaseAuth.getInstance().getCurrentUser();
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();
    }
    public void changepass(View view){
        String currentpassword=CurrentPassword.getText().toString().trim();
        String newpassword=NewPassword.getText().toString();
        String confirmpassword=ConfirmPassword.getText().toString();





        if(currentpassword.length()==0){
            CurrentPassword.setError("Cannot be Empty");
        } else if (newpassword.length()==0) {
            NewPassword.setError("Cannot be Empty");
        } else if (newpassword.length()<8) {
            NewPassword.setError("Length should greater than 8 characters");
        } else if (confirmpassword.length()==0) {
            ConfirmPassword.setError("Cannot be Empty");
        } else if (!newpassword.equals(confirmpassword)) {
            ConfirmPassword.setError("Does not match New Password");
        }


        else{
            mAuth.signInWithEmailAndPassword(user.getEmail().trim(), currentpassword)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                user.updatePassword(newpassword)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {

                                                    Toast.makeText(changepassword.this, "Password updated successfully", Toast.LENGTH_LONG).show();
                                                    startActivity(new Intent(getApplicationContext(),useraccount.class));
                                                    finish();
                                                    DocumentReference dr= db.document("Users/"+user.getEmail().trim());
                                                    dr.update("Password",newpassword).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                        @Override
                                                        public void onSuccess(Void unused) {

                                                        }
                                                    });
                                                }
                                                else{
                                                    Toast.makeText(changepassword.this, task.getException()+"", Toast.LENGTH_LONG).show();
                                                }
                                            }
                                        });
                            } else {

                                CurrentPassword.setError("Wrong Password");

                            }
                        }
                    });


        }
    }
}