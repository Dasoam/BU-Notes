package com.dhdash.bunotes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.FullScreenContentCallback;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class useraccount extends AppCompatActivity {
    private FirebaseFirestore db;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    private String usermail,password;
    private TextView username;
    private InterstitialAd mInterstitialAd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_useraccount);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        user = mAuth.getCurrentUser();
        username=findViewById(R.id.Username);
        usermail=user.getEmail().trim();
        DocumentReference dr=db.document("Users/"+usermail);
        dr.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                String name= documentSnapshot.getString("Name").trim();
                password=documentSnapshot.getString("Password").trim();
                String namearray[]=name.split(" ");
                if(namearray[0].length()<10) {
                    username.setText("Hi " + namearray[0]);
                }
                else{
                    username.setText("Hi " + namearray[0].substring(0,10)+"...");
                }


            }
        });

        MobileAds.initialize(this, new OnInitializationCompleteListener() {
            @Override
            public void onInitializationComplete(InitializationStatus initializationStatus) {}
        });
    }
    public void Home(View view){
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
        //System.out.println(usermail+"\n"+password);
        /*try {
            Thread.sleep(2000);
        }
        catch (Exception ex){

        }*/
        if (password==null){
            DocumentReference dr=db.document("Users/"+usermail);
            dr.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            // Handle the document data here
                            // For example, if you want to get a specific field:
                            String repassword = document.get("Password").toString().trim();
                            System.out.println(repassword);

                            // ... (process the data further or set it to a variable)
                            FirebaseAuth.getInstance().signOut();
                            mAuth.signInWithEmailAndPassword(usermail, repassword)
                                    .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                                        @Override
                                        public void onComplete(@NonNull Task<AuthResult> task) {
                                            if (task.isSuccessful()) {
                                                mAuth.getCurrentUser().reload();
                                                user=mAuth.getCurrentUser();
                                                if(user.isEmailVerified()) {
                                                    if (mInterstitialAd != null) {
                                                        mInterstitialAd.show(useraccount.this);
                                                        mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                                                            @Override
                                                            public void onAdDismissedFullScreenContent() {
                                                                // Called when ad is dismissed.
                                                                // Set the ad reference to null so you don't show the ad a second time.

                                                                startActivity(new Intent(getApplicationContext(), changepassword.class));
                                                                mInterstitialAd = null;
                                                            }
                                                        });
                                                    } else {

                                                        startActivity(new Intent(getApplicationContext(), changepassword.class));
                                                    }

                                                }
                                                else {
                                                    Toast.makeText(useraccount.this, "Verify your email to proceed", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {

                                                Toast.makeText(useraccount.this, "some error occured.Please try again.",
                                                        Toast.LENGTH_SHORT).show();

                                            }
                                        }
                                    });
                        } else {
                            // Document not found
                            Toast.makeText(useraccount.this, "data not found", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Error occurred while fetching data
                        Toast.makeText(useraccount.this, "Error while fetching data", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }else{

        FirebaseAuth.getInstance().signOut();
        mAuth.signInWithEmailAndPassword(usermail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().reload();
                            user=mAuth.getCurrentUser();
                            if(user.isEmailVerified()) {
                                if (mInterstitialAd != null) {
                                    mInterstitialAd.show(useraccount.this);
                                    mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
                                        @Override
                                        public void onAdDismissedFullScreenContent() {
                                            // Called when ad is dismissed.
                                            // Set the ad reference to null so you don't show the ad a second time.

                                            startActivity(new Intent(getApplicationContext(), changepassword.class));
                                            mInterstitialAd = null;
                                        }
                                    });
                                } else {

                                    startActivity(new Intent(getApplicationContext(), changepassword.class));
                                }

                            }
                            else {
                                Toast.makeText(useraccount.this, "Verify your email to proceed", Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(useraccount.this, "some error occured.Please try again.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
        }


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
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),loginsingup.class));
        finish();

        Toast.makeText(this, "Logout Successfully", Toast.LENGTH_LONG).show();
    }
    public void verify(View view){
        FirebaseAuth.getInstance().signOut();
        mAuth.signInWithEmailAndPassword(usermail, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            mAuth.getCurrentUser().reload();
                            user=mAuth.getCurrentUser();
                            if(!user.isEmailVerified()) {
                                user.sendEmailVerification()
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Toast.makeText(useraccount.this, "Verification Email Sent",
                                                            Toast.LENGTH_SHORT).show();

                                                } else if (task.getException().toString().trim().equals("com.google.firebase.FirebaseTooManyRequestsException: We have blocked all requests from this device due to unusual activity. Try again later.")) {
                                                    Toast.makeText(useraccount.this, "Too Many attempts try again in 5 minutes",
                                                            Toast.LENGTH_SHORT).show();
                                                } else{
                                                    Toast.makeText(useraccount.this, task.getException()+"",
                                                            Toast.LENGTH_SHORT).show();
                                                    System.out.println(task.getException());
                                                }
                                            }
                                        });
                            }
                            else {
                                Toast.makeText(useraccount.this, "Already verified",
                                        Toast.LENGTH_SHORT).show();
                            }
                        } else {

                            Toast.makeText(useraccount.this, "some error occured.Please try again.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });


    }
    @Override
    protected void onStart() {
        super.onStart();
        AdRequest adRequest = new AdRequest.Builder().build();

        InterstitialAd.load(this,getString(R.string.changead), adRequest,
                new InterstitialAdLoadCallback() {
                    @Override
                    public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
                        // The mInterstitialAd reference will be null until
                        // an ad is loaded.
                        mInterstitialAd = interstitialAd;

                    }

                    @Override
                    public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
                        // Handle the error

                        mInterstitialAd = null;
                    }
                });
    }
}