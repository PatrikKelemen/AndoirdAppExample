package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.TextView;
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
        //Check that the password and username are valid here
        String accountType ="";
        String stringUsername = ((TextView)findViewById(R.id.username)).getText().toString();
        String stringPassword = ((TextView)findViewById(R.id.password)).getText().toString();
        boolean validData = true;

        if (mydb.exists(stringUsername, "Username",users)){
            System.out.println("exists");
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
        System.out.println(hex);

        if (validData) {
            Account dbUser = mydb.getData(stringUsername, users);
            String dbpassword = dbUser.getPassword();
            System.out.print(dbpassword);
            if (!hex.equals( dbpassword)){
                //((TextView)findViewById(R.id.password)).setText("username or password is wrong"); (replace with toast)
                validData = false;
            }
        }





        if (validData){
            Intent intent = new Intent(getApplicationContext(), WelcomeScreen.class);
            intent.putExtra("username",stringUsername);
            intent.putExtra("accoutType",accountType);
            startActivityForResult(intent, 0);
        }
    }
}
