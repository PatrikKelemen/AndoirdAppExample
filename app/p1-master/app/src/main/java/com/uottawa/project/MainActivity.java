package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private DBHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DBHandler();
    }

    public void onRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivityForResult(intent,0);
    }

    public void onLogin(View view) {
        //Check that the password and username are valid here
        String accountType;
        String stringUsername = (TextView)findViewById(username).getText().toString();
        String stringPassword = (TextView)findViewById(password).getText().toString();
        boolean validData = true;

        if (mydb.dbSearch("username","userInfoEmployees",stringUsername)) {
            accountType = "userInfoEmployees";
        }

        else if (mydb.dbSearch("username","userInfoPatients",stringUsername)){
            accountType = "userInfoPatients";
        }

        else{
            validData = false;
        }
        if (){
            (TextView)findViewById(password).setText("username or password is wrong");
        }


        if (!validData){
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            startActivityForResult(intent, 0);
        }
    }
}
