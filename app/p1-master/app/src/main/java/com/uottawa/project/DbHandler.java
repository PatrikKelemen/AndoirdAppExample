package com.uottawa.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.database.DatabaseUtils;

public class DbHandler {

    SQLiteDatabase db = new SQLiteDatabase.openDatabase(context.getDatabasePath(appDatabase.db).getPath(), null, SQLiteDatabase.OPEN_READWRITE);

    public boolean dbSearch(String dataColumn, String accountType, String target){
        return searchAllData(dataColumn, accountType).contains(target);
    }

    public void dbAdd (String username, String firstName, String lastName, String email, String password, String userType){
        ContentValues input = new ContentValues();
        this.input.put("name", username);
        this.input.put("password", password);
        this.input.put("nameFirst", firstName);
        this.input.put("nameLast", lastName);
        this.input.put("email", email);
        db.insert(userType, null, input);
    }

    public void matchPassword (String name, String pass){
        // Add code for searching for password related to username
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
