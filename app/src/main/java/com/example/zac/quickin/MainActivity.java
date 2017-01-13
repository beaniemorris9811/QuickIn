package com.example.zac.quickin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Login(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }


}
