package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivityForResult(intent,0);
    }

    public void onLogin(View view) {
        //Check that the password and username are valid here
        boolean validData = true;

        if(!validData){
            validData = false;
        }


        if (validData) {
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            startActivityForResult(intent, 0);
        }
    }
}
