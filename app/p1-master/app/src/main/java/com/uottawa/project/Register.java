package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    private DbHandler mydb;
    DatabaseReference database;
    List<Account> users;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        users = new ArrayList<>();
        mydb = new DbHandler();
        database = FirebaseDatabase.getInstance().getReference("users");


    }

    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                users.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Account user = postSnapshot.getValue(Account.class);
                    users.add(user);
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }

    public void onRegister(View view) {



        //Check that data is valid here
        boolean validData = true;
        Button b;
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
        else  {
            stringUserType = "employee";
        }

        //check if username taken
        if(mydb.exists(stringUsername, "Username",users)||
                stringUsername == "admin"){
            validData = false;
            b=(Button)findViewById(R.id.register);
            b.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(getApplicationContext(), "Username taken", Toast.LENGTH_LONG).show();
                                     }
                                 }
            );

        }
        // check if email taken
        if (mydb.exists(stringEmail, "Email",users)){
            validData = false;
            b=(Button)findViewById(R.id.register);
            b.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(getApplicationContext(), "Email taken", Toast.LENGTH_LONG).show();
                                     }
                                 }
            );
        }
        //check if password matches confirm password

        if (!stringPassword.equals(stringConfirmPassword)){
            validData = false;
            b=(Button)findViewById(R.id.register);
            b.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(getApplicationContext(),"password do not match", Toast.LENGTH_LONG).show();
                                     }
                                 }
            );
        }

        if (validData) {
            //create new account here
            //check password
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

            Account newAccount;
            if (stringUserType.equals("employee")){
                newAccount = new Employee(hex, stringUsername, stringFirst, stringLast, stringEmail);
            } else {
                newAccount = new Patient(hex, stringUsername, stringFirst, stringLast, stringEmail);
            }

            mydb.add (newAccount,database);

            Intent intent;
            if (newAccount.getClass().equals(Employee.class)) {
                intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                intent.putExtra("accountType",stringUserType);
                //Patient (or is an error, the least amount of damage can be done with a Patient Account)
            } else {
                intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                intent.putExtra("accountType",stringUserType);
            }

            intent.putExtra("username",stringUsername);
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
