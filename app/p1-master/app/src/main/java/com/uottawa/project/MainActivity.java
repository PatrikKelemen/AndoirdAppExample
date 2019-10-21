package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
import java.security.*;
import java.nio.charset.StandardCharsets;


public class MainActivity extends AppCompatActivity {

    private DbHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb = new DbHandler();
    }

    public void onRegister(View view) {
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivityForResult(intent,0);
    }

    public void onLogin(View view) {
        //Check that the password and username are valid here
        String accountType ="";
        String stringUsername = ((TextView)findViewById(R.id.username)).getText().toString();
        String stringPassword = ((TextView)findViewById(R.id.password)).getText().toString();
        boolean validData = true;

        if (stringUsername == "admin"){
            accountType = "ADMIN";
        }

        else if (mydb.dbSearch("username","userInfoEmployees",stringUsername)) {
            accountType = "userInfoEmployees";
        }

        else if (mydb.dbSearch("username","userInfoPatients",stringUsername)){
            accountType = "userInfoPatients";
        }

        else{
            validData = false;
        }


        String hex = "";
        try{
            //hashing the password to SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = digest.digest(stringPassword.getBytes(StandardCharsets.UTF_8));

            //convertting to hexadecimal

            for (int i =0; i < passwordHash.length; i++) {
                hex = hex + String.format("%02x", passwordHash[i]);
            }

        }
        catch(Exception e){
            hex = "";
        }






        if (hex == ""){
            ((TextView)findViewById(R.id.password)).setText("username or password is wrong");
        }


        if (!validData){
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            intent.putExtra("username",stringUsername);
            intent.putExtra("accoutType",accountType);
            startActivityForResult(intent, 0);
        }
    }
}
