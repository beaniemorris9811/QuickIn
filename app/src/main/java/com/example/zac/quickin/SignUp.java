package com.example.zac.quickin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Zac on 1/7/17.
 */
public class SignUp extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            public static final String TAG = "error";

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        Button mButton = (Button)findViewById(R.id.btnSignUp);


        mButton.setOnClickListener(
                new View.OnClickListener()
                {
                    public void onClick(View view)

                    {
                        EditText emailEdit = (EditText) findViewById(R.id.email);
                        String email = emailEdit.getText().toString();

                        EditText passEdit = (EditText) findViewById(R.id.password);
                        String password = passEdit.getText().toString();
                        signup(email,password);
                    }
                });
    }
    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }
    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signup(String email, String password){

        Log.d("TAG", "createUserWithEmail::" + email);
        Log.d("TAG", "createUserWithPassword:" + password);
//        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("TAG", "createUserWithEmail:onComplete:" + task.isSuccessful());
                        Log.d("TAG", "Failed to Register. Please try again. Error is " + task.getException());
                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.d("TAG", "Failed to Register. Please try again. Error is " + task.getException());
                            Toast.makeText(SignUp.this, "Failed to Register. Please try again. Error is " + task.getException(), Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }

}
