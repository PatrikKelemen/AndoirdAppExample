package com.uottawa.project;




import java.util.List;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


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



    private void add(Account account) {


        String id = database.push().getKey();



        database.child(id).setValue(account);
    }
}
