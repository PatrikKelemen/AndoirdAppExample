package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class EmployeeScreenWithoutClinic extends AppCompatActivity {

    private DbHandler mydb;
    DatabaseReference database;
    List<Clinic> ClinicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_screen_without_clinic);

        Intent welcome = this.getIntent();

        String stringUsername = welcome.getStringExtra("username");
        mydb = new DbHandler();
        String dbName = stringUsername;
        ((TextView)findViewById(R.id.welcomeMsg)).setText("Welcome "+dbName+". You are logged in as an Employee.");
        ClinicList = new ArrayList<>();
        database = FirebaseDatabase.getInstance().getReference("Clinics");
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange (DataSnapshot dataSnapshot){
                ClinicList.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Clinic clinic = postSnapshot.getValue(Clinic.class);
                    ClinicList.add(clinic);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });
    }


    public void onCreateClinic(View view) {
        String stringUsername = ((TextView)findViewById(R.id.ClinicName)).getText().toString();

    }

    public void onJoinClinic(View view) {
        //add joining clinics here
    }

    public void onLogout(View view) {
        Intent returnIntent = new Intent();

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
