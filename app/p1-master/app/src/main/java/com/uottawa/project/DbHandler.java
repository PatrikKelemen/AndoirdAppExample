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

    private Context context =;

    private SQLiteDatabase db = SQLiteDatabase.openDatabase(context.getDatabasePath("appDatabase.db").getPath(), null, SQLiteDatabase.OPEN_READWRITE);

    public boolean dbSearch(String dataColumn, String accountType, String target){
        return searchAllData(dataColumn, accountType).contains(target);
    }

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
