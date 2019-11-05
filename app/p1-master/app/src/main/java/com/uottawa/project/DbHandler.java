package com.uottawa.project;




import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class DbHandler {






    public DbHandler (){
                }


    public Account getData(String username, List<Account> users){


            for (int i = 0; i< users.size(); i++){
                if (users.get(i).getUsername().equals(username)){
                    return users.get(i);
                }
            }


        return null;
    }

    public boolean exists(String data, String dataToSerach,List<Account> users){

        if (dataToSerach.equals("Username")){
            for (int i = 0; i< users.size(); i++){
                if (users.get(i).getUsername().equals(data)){
                    return true;
                }
            }
        }
        else if (dataToSerach.equals("Email")){
            for (int i = 0; i< users.size(); i++){
                if (users.get(i).getEmail().equals(data)){
                    return true;
                }
            }
        }
        return false;
    }


    public void add(Account account, DatabaseReference database ) {


        String id = database.push().getKey();



        database.child(id).setValue(account);
    }

    public void test(DatabaseReference database ) {




        Account newAccount = new Account("pass","username","first","last");
        this.add(newAccount,database);


    }
}
