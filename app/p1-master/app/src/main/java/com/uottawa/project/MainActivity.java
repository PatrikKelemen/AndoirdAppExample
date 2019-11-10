package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.security.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class MainActivity extends AppCompatActivity {

    // Initialize context
    protected Button b;
    String s= "Username/password is incorrect";
    DatabaseReference database;
    List<Account> users;
    private DbHandler mydb = new DbHandler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        users = new ArrayList<>();
        setContentView(R.layout.activity_main);
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
        Intent intent = new Intent(getApplicationContext(), Register.class);
        startActivityForResult(intent,0);

    }

    public void onLogin(View view) {
        mydb.addAdmin(database,users);

        //Check that the password and username are valid here
        String accountType ="";
        String stringUsername = ((TextView)findViewById(R.id.username)).getText().toString();
        String stringPassword = ((TextView)findViewById(R.id.password)).getText().toString();
        boolean validData = true;

        if (mydb.exists(stringUsername, "Username",users)){
            //(add toast here)
            b=(Button)findViewById(R.id.login);
            b.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View v) {
                                         Toast.makeText(getApplicationContext(), "You are successfully logged in!", Toast.LENGTH_LONG).show();
                                     }
                                 }
            );
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


        if (validData) {
            Account dbUser = mydb.getData(stringUsername, users);
            String dbpassword = dbUser.getPassword();


            if (!hex.equals( dbpassword)){
                //((TextView)findViewById(R.id.password)).setText("username or password is wrong"); (replace with toast)
                validData = false;

            } else {
                Intent intent;
                //Admin user
                System.out.println((dbUser.getClass()));
                if (dbUser.getClass().equals(Admin.class)) {
                    intent = new Intent(getApplicationContext(), AdminScreen.class);

                //Employee user
                } else if (dbUser.getClass().equals(Employee.class)) {
                    intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                    intent.putExtra("accountType","Employee");
                //Patient (or is an error, the least amount of damage can be done with a Patient Account)
                } else {
                    intent = new Intent(getApplicationContext(), WelcomeScreen.class);
                    intent.putExtra("accountType","Patient");
                }

                intent.putExtra("username",stringUsername);
                startActivityForResult(intent, 0);
            }
        }

        /*if (validData){
            Intent intent;
            if (dbUser.getClass().equals(Admin.class) )
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            intent.putExtra("username",stringUsername);
            intent.putExtra("accoutType",accountType);
            startActivityForResult(intent, 0);
        }*/
    }
}
