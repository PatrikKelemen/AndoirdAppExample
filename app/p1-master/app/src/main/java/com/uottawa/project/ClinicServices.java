package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ClinicServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinic_services);
    }

    public void onDone(View view) {
        Intent returnIntent = new Intent();

        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void onAddService(View view) {
        Intent intent = new Intent(getApplicationContext(), ClinicServiceOptions.class);
        startActivityForResult(intent, 0);
    }
}
