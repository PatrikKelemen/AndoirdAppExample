package com.uottawa.project;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Rating extends  AppCompatActivity{
    private RecyclerView listClinic;
    private RecyclerView.LayoutManager layout;
    private RecyclerView.Adapter adapter;
    private ArrayList ClinicList = MainActivity.ClinicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);

        // initiate rating bar and a button
        final RatingBar simpleRatingBar = findViewById(R.id.ratingBar);
        final Button submitButton = findViewById(R.id.submitButton);

        submitButton.setEnabled(false);

        final EditText commentBox = (EditText)findViewById(R.id.commentBox);
        final String comment = commentBox.getText().toString();

            //dropdown list
            listClinic = (RecyclerView) findViewById(R.id.ListClinic);
            layout = new LinearLayoutManager(this);
            listClinic.setLayoutManager(layout);

            ClinicList = new ArrayList<Clinic>();
            //ClinicList = MainActivity.ClinicList;

            final Clinic sample = new Clinic("Clinic1");
            ClinicList.add(sample);

        adapter = new ratingAdapter(ClinicList, new ratingAdapter.ratingViewListener() {

            @Override
            public void onSelect(Clinic a) {
                System.out.println(a.getName());
                int index = ClinicList.indexOf(a);
                ClinicList.remove(a);
                Toast.makeText(getApplicationContext(), "Clinic selected: "+ a.getName(), Toast.LENGTH_LONG).show();
                adapter.notifyItemRemoved(index);
                submitButton.setEnabled(true);
            }

        });
            listClinic.setAdapter(adapter);

        // perform click event on button
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float rating = simpleRatingBar.getRating();
                sample.addCommentRating(comment, rating);

                //close rating
                submitButton.setEnabled(false);
                commentBox.getText().clear();
                commentBox.setFocusable(false);
                simpleRatingBar.setRating(((float)0.0));
                simpleRatingBar.setIsIndicator(true);

                // confirm submission to client
                String display =  new String("Submission completed successfully - rating: "+ rating + "- Clinic " + sample.getName());
                Toast.makeText(Rating.this, display, Toast.LENGTH_LONG).show();

            }
        });
    }

    //return to previous page patientSreen
    public void onPrevious(View view){

        //get activity about rating
        Intent myIntent = new Intent(this,PatientScreen.class);
        startActivity(myIntent);


    }

}
