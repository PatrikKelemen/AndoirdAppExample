package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void onRegister(View view) {
        //Check that data is valid here
        boolean validData = true;

        //get strings
        String stringPassword = (TextView)findViewById(password).getText().toString();
        String stringConfirmPassword = (TextView)findViewById(comfirmpassword).getText().toString();
        String stringUsername = (TextView)findViewById(username).getText().toString();
        String stringEmail = (TextView)findViewById(email).getText().toString();
        String stringFirst = (TextView)findViewById(firstname).getText().toString();
        String stringLast = (TextView)findViewById(lastname).getText().toString();


        //check if all fields filled
        if (stringPassword.isEmpty()
                || stringConfirmPassword.isEmpty()
                || stringEmail.isEmpty()
                || stringFirst.isEmpty()
                || stringLast.isEmpty()
                || stringUsername.isEmpty()) {
            validData = false;
        }

        //check if username taken

        // check if email taken

        //check if password matches confirm password

        if (!stringPassword.equals(stringConfirmPassword)){
            validData = false;
        }

        if (validData) {
            //create new account here

            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            startActivityForResult(intent, 0);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
