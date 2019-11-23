package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class PatientScreen extends AppCompatActivity {

    private RecyclerView currentAppointments;
    private RecyclerView.LayoutManager layout;
    private RecyclerView.Adapter adapter;
    private ArrayList appointments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_screen);

        Intent intent = getIntent();

        TextView welcome = (TextView) findViewById(R.id.welcomeMsg);
        welcome.setText("Welcome "+intent.getStringExtra("username")+". You are logged in as a Patient.");

        currentAppointments = (RecyclerView) findViewById(R.id.currentAppointments);

        layout = new LinearLayoutManager(this);
        currentAppointments.setLayoutManager(layout);

        appointments = new ArrayList<Appointment>();
        appointments.add(new Appointment("Nov. 11, 2019","9:30", new Clinic(), new Patient("","d","","","")));

        adapter = new AppointmentAdapter(appointments);
        currentAppointments.setAdapter(adapter);
    }

    public void onSearchClinics(View view) {

    }
    public void onLogout(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
