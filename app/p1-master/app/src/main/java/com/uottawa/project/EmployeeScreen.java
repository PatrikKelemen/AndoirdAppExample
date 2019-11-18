package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class EmployeeScreen extends AppCompatActivity {

    private DbHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen);

        Intent welcome = this.getIntent();

        String stringUsername = welcome.getStringExtra("username");
        mydb = new DbHandler();
        String dbName = stringUsername;
        ((TextView)findViewById(R.id.welcomeMsg)).setText("Welcome "+dbName+". You are logged in as an Employee.");
    }

    public void onManageServices(View view) {
        //add changing services here
    }

    public void onManageHours(View view) {
        //add changing hours here
        Intent intent = new Intent(getApplicationContext(), EmployeeHours.class);
        startActivityForResult(intent, 0);
    }

    public void onLogout(View view) {
        Intent returnIntent = new Intent();

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
