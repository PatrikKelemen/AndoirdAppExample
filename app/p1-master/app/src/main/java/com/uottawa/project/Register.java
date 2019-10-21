package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.RadioButton;
import android.widget.TextView;

public class Register extends AppCompatActivity {

    private DbHandler mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mydb = new DbHandler();
    }

    public void onRegister(View view) {



        //Check that data is valid here
        boolean validData = true;

        //get strings
        String stringPassword = ((TextView)findViewById(R.id.password)).getText().toString();
        String stringConfirmPassword = ((TextView)findViewById(R.id.comfirmpassword)).getText().toString();
        String stringUsername = ((TextView)findViewById(R.id.username)).getText().toString();
        String stringEmail = ((TextView)findViewById(R.id.email)).getText().toString();
        String stringFirst = ((TextView)findViewById(R.id.firstname)).getText().toString();
        String stringLast = ((TextView)findViewById(R.id.lastname)).getText().toString();
        String stringUserType;

        RadioButton patientButton = (RadioButton) findViewById(R.id.patient); // initiate a radio button
        RadioButton employeeButton = (RadioButton) findViewById(R.id.employee); // initiate a radio button

        Boolean patientState = patientButton.isChecked();
        Boolean employeeState = employeeButton.isChecked();


        //check if all fields filled
        if (stringPassword.isEmpty()
                || stringConfirmPassword.isEmpty()
                || stringEmail.isEmpty()
                || stringFirst.isEmpty()
                || stringLast.isEmpty()
                || stringUsername.isEmpty()
                || !(patientState || employeeState)) {
            validData = false;
            ((TextView)findViewById(R.id.username)).setText("please fill in all fields");
        }

        //get usertype
        if (patientState){
            stringUserType = "patient";
        }
        else (employeeState) {
            stringUserType = "employee";
        }

        //check if username taken
        if(mydb.dbSearch("username","userInfoPatients",stringUsername)||
                mydb.dbSearch("username","userInfoEmployees",stringUsername)){
            validData = false;
            ((TextView)findViewById(R.id.username)).setText("username taken");

        }
        // check if email taken
        if (mydb.dbSearch("email","userInfoPatients",stringEmail)||
                mydb.dbSearch("email","userInfoEmployees",stringEmail)){
            validData = false;
            ((TextView)findViewById(R.id.email)).setText("email taken");
        }
        //check if password matches confirm password

        if (!stringPassword.equals(stringConfirmPassword)){
            validData = false;
            ((TextView)findViewById(R.id.comfirmpassword)).setText("password do not match");
        }

        if (validData) {
            //create new account here
            mydb.dbAdd (stringUsername, stringFirst, stringLast, stringEmail, stringPassword, stringUserType);



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
