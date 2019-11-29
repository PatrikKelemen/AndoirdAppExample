package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PatientScreen extends AppCompatActivity {

    private RecyclerView currentAppointments;
    private RecyclerView.LayoutManager layout;
    private RecyclerView.Adapter adapter;
    private ArrayList appointments;
    DatabaseReference database;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        intent = getIntent();

        TextView welcome = (TextView) findViewById(R.id.welcomeMsg);
        welcome.setText("Welcome "+intent.getStringExtra("username")+". You are logged in as a Patient.");

        currentAppointments = (RecyclerView) findViewById(R.id.currentAppointments);

        layout = new LinearLayoutManager(this);
        currentAppointments.setLayoutManager(layout);

        appointments = new ArrayList<Appointment>();

        database = FirebaseDatabase.getInstance().getReference("");
        appointments.add(new Appointment("Nov. 11, 2019","9:30", new Clinic(), new Patient("","d","","","")));

        appointments.add(new Appointment("Nov. 12, 2019","10:30", new Clinic(), new Patient("","d","","","")));

        adapter = new AppointmentAdapter(appointments, new AppointmentAdapter.AppointmentViewListener() {
            @Override
            public void onCancel(Appointment a) {
                System.out.println(a.getDate());
                int index = appointments.indexOf(a);
                appointments.remove(a);
                //remove from database?
                //should we notify the staff?

                Toast.makeText(getApplicationContext(), "Appointment Cancelled", Toast.LENGTH_LONG).show();

                adapter.notifyItemRemoved(index);
            }

            @Override
            public void onCheckIn(Appointment a) {
                System.out.println(a.getTime());
            }
        });
        currentAppointments.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        database.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot){
                appointments.clear();

                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                    Appointment appointment = postSnapshot.getValue(Appointment.class);
                    if (appointment.getPatient().getUsername().equals(intent.getStringExtra("username")))
                        appointments.add(appointment);
                }



                currentAppointments.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError){

            }
        });


    }


    public void onSearchClinics(View view) {

    }
    public void onLogout(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
    /*public void onCancel(View view) {
        System.out.println("cancel");
    }
    public void onCheckIn(View view) {
        System.out.println("check in");
    }*/
}
