package com.uottawa.project;

<<<<<<< Updated upstream
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
import java.io.File;
import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.Hashtable;
//import android.database.DatabaseUtils;
=======

import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
>>>>>>> Stashed changes

public class DbHandler {


    // Initialize context
    DatabaseReference database;
    List<Account> users;



    public DbHandler (){
        database = FirebaseDatabase.getInstance().getReference();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
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

<<<<<<< Updated upstream
    public void dbAdd (String username, String firstName, String lastName, String email, String password, String userType){
        ContentValues input = new ContentValues();
        input.put("name", username);
        input.put("password", password);
        input.put("nameFirst", firstName);
        input.put("nameLast", lastName);
        input.put("email", email);
        db.insert(userType, null, input);
    }

    public String matchData (String type, String name, String column ){
        ArrayList<String> usernames = searchAllData("name", type);
        ArrayList<String> targetColumn = searchAllData(column, type);
=======

>>>>>>> Stashed changes








    private void add(Account account) {


        String id = database.push().getKey();



        database.child(id).setValue(account);
    }
}
