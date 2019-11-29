package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

public class RateClinic extends AppCompatActivity {

    private String clinic;
    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic);

        Intent intent = getIntent();

        TextView title = (TextView) findViewById(R.id.rateClinicTitle);
        title.setText("Write a review for "+intent.getStringExtra("clinic")+":");

        clinic = intent.getStringExtra("clinic");
        username = intent.getStringExtra("username");
    }

    public void onCancel(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "cancel");
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void onPost(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", "post");

        Review review = new Review(clinic, username, ((EditText) findViewById(R.id.clinicComments)).getText().toString(), ((RatingBar) findViewById(R.id.clinicRating)).getRating());
        //add review to database

        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
