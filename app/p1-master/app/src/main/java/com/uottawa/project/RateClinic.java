package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class RateClinic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_clinic);

        Intent intent = getIntent();

        TextView title = (TextView) findViewById(R.id.rateClinicTitle);
        title.setText("Write a review for "+intent.getStringExtra("clinic")+":");
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
        setResult(RESULT_OK, returnIntent);
        finish();
    }
}
