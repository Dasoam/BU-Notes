package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class loginsingup extends AppCompatActivity implements  AdapterView.OnItemSelectedListener {

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    private EditText Email,Password;
    private Button Login;
    private String email,password;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(getApplicationContext(), Dashboard.class));
            finish();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginsingup);
        db = FirebaseFirestore.getInstance();

        Email=findViewById(R.id.Email);
        Password=findViewById(R.id.Password);
        Login=findViewById(R.id.logn);


    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
    }

    public void login(View view) {


    email=Email.getText().toString().toLowerCase();
    password=Password.getText().toString();


    if (email.length()==0) {
        Email.setError("Cannot be Empty");
    } else if ( email.length()<10|| !email.substring(email.length() -10).equals("@gmail.com")) {
        Email.setError("Enter valid Gmail id");
    } else if (password.length()==0) {
        Password.setError("Cannot be Empty");
    } else if (password.length()<8) {
        Password.setError("Length should greater than 8 characters");
    }
    else{

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information

                            DocumentReference dr= db.document("Users/"+mAuth.getCurrentUser().getEmail().trim());
                            dr.update("Password",password).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                        finish();
                                        Toast.makeText(loginsingup.this,"Login Successful",
                                                Toast.LENGTH_SHORT).show();
                                        Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                                        vibrator.vibrate(500);
                                    }
                                    else{
                                        Toast.makeText(loginsingup.this,task.getException()+"",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                        } else {
                            // If sign in fails, display a message to the user.

                            String ex=task.getException().toString().trim();
                            if (ex.equals("com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted.")){
                                Intent intent=new Intent(getApplicationContext(), register.class);

                                intent.putExtra("Email",email);

                                startActivity(intent);
                                finish();
                                Toast.makeText(loginsingup.this,"User Not Registered",
                                        Toast.LENGTH_LONG).show();
                            }
                            else{
                                Toast.makeText(loginsingup.this,"Authentication Failed",
                                        Toast.LENGTH_LONG).show();

                            }

                        }
                    }
                });
    }
    }

    public void passwordredirect(View view){
        EditText resetMail = new EditText(view.getContext());
        final AlertDialog.Builder resetDialogBuilder = new AlertDialog.Builder(view.getContext());
        resetDialogBuilder.setTitle("Password Reset");
        resetDialogBuilder.setMessage("Enter your registered Email id");
        resetDialogBuilder.setView(resetMail);

// Positive button is created to have more control over its behavior
        resetDialogBuilder.setPositiveButton("Reset Password", null);

        final AlertDialog resetDialog = resetDialogBuilder.create();

        resetDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialogInterface) {
                Button resetButton = resetDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                resetButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String email = resetMail.getText().toString().trim();

                        if (email.isEmpty()) {
                            resetMail.setError("Field cannot be empty");
                        } else {
                            mAuth.sendPasswordResetEmail(email)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {

                                                Toast.makeText(loginsingup.this, "Reset Link Sent to Registered Mail",
                                                        Toast.LENGTH_LONG).show();
                                            } else {
                                                Toast.makeText(loginsingup.this, "User does not exist or is no longer valid",
                                                        Toast.LENGTH_LONG).show();
                                            }
                                            resetDialog.dismiss(); // Dismiss the dialog after processing
                                        }
                                    });
                        }
                    }
                });
            }
        });

        resetDialog.show();


    }
}