package com.uottawa.project;

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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class DbHandler {


    // Initialize context



    private SQLiteDatabase db;
    public DbHandler (){
        String filePath = new File("").getAbsolutePath();

        db = SQLiteDatabase.openDatabase(filePath +"appDatabase.db", null, SQLiteDatabase.OPEN_READWRITE);

    }



    public boolean dbSearch(String dataColumn, String accountType, String target){
        return searchAllData(dataColumn, accountType).contains(target);
    }

    public void dbAdd (String username, String firstName, String lastName, String email, String password, String userType) {
    }

    public String matchData (String type, String name, String column ){
        ArrayList<Account> userData;

        int found = usernames.indexOf(name);
        if (found == -1){
            return "";
        }
        else{
            return targetColumn.get(found);
        }

    }

    public ArrayList<String> searchAllData(String column, String profile) {
        ArrayList<String> array_list = new ArrayList<String>();

        Cursor res =  db.rawQuery( "select * from " + profile, null );
        res.moveToFirst();

        while(!res.isAfterLast()){
            array_list.add(res.getString(res.getColumnIndex(column)));
            res.moveToNext();
        }
        return array_list;
    }


}
