package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;

public class register extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private EditText pass1,pass2,bentenmail,Name;
    private Button SignUp;
    private Spinner spinner,spinner2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();
        pass1=findViewById(R.id.passfield1);
        pass2=findViewById(R.id.passfield2);
        SignUp=findViewById(R.id.SignUp);
        bentenmail=findViewById(R.id.bemail);
        Name=findViewById(R.id.Name);
        spinner = findViewById(R.id.spinnercourse);

        spinner2 = findViewById(R.id.spinnercourseyear);


    }
    public void login2(View view) {

        Intent intent=getIntent();

        String email=intent.getStringExtra("Email");

        String name=Name.getText().toString();
        String course=spinner.getSelectedItem().toString();
        String year=spinner2.getSelectedItem().toString();
        String ben10mail=bentenmail.getText().toString().toLowerCase().trim();
        if (pass1.getText().toString().equals(pass2.getText().toString()) && pass1.getText().toString().length()!=0 && pass2.getText().toString().length()!=0
        && ben10mail.endsWith("@bennett.edu.in")){
            mAuth.createUserWithEmailAndPassword(email, pass1.getText().toString())
                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success, update UI with the signed-in user's information

                                FirebaseUser user = mAuth.getCurrentUser();
                                Toast.makeText(register.this, "Registered & Logged In",
                                        Toast.LENGTH_SHORT).show();

                                startActivity(new Intent(getApplicationContext(), Dashboard.class));
                                finish();
                                Vibrator vibrator=(Vibrator) getSystemService(VIBRATOR_SERVICE);
                                vibrator.vibrate(500);


                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(register.this, "Verification Email Sent",
                                                            Toast.LENGTH_LONG).show();

                                                }
                                            }
                                        });
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                HashMap<String,String> data=new HashMap<>();
                                data.put("Name",name);
                                data.put("Email",email);
                                data.put("Password",pass1.getText().toString());
                                data.put("Course",course);
                                data.put("Year",year);
                                data.put("Ben10 Mail",ben10mail);
                                db.document("Users/"+user.getEmail()).set(data);
                            } else {
                                // If sign in fails, display a message to the user.

                                Toast.makeText(register.this, "Some error occured,try again",
                                        Toast.LENGTH_SHORT).show();


                            }
                        }
                    });
        }
        else{

            if (ben10mail.isEmpty() || !ben10mail.endsWith("@bennett.edu.in")){
            bentenmail.setError("Invalid College ID");}
            else if(pass1.getText().toString().length()<8){
                pass1.setError("Should be at least 8 characters");
            }
            else if(!pass1.getText().toString().equals(pass2.getText().toString())){
                pass2.setError("Password does not match");
            }
        }
    }
}