package com.uottawa.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class Search extends AppCompatActivity {

    Button search;
    EditText SearchBox;
    static List<Clinic> ClinicList;
    static List<Float> ratingsList;
    List<Clinic> ResultClinic;
    int[] openHours = {6,7,8,9};
    int[] closingHours = {3,4,5,6};
    Intent intent;
    DatabaseReference databaseProducts;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        intent = getIntent();
        setContentView(R.layout.activity_search);
        SearchBox = (EditText) findViewById(R.id.text_input);
        search = (Button) findViewById(R.id.button);

        ResultClinic = new ArrayList<>();
        ratingsList = new ArrayList<>();
        databaseProducts = FirebaseDatabase.getInstance().getReference("Clinics");
        //test clinicNameSearch
        ClinicList = new ArrayList<>();
        ClinicList.add(new Clinic("Clinic1"));
        ClinicList.add(new Clinic("Clinic2"));
        ClinicList.add(new Clinic("Clinic3"));



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent Intent = new Intent(this,SearchResult.class);
                //startActivity(Intent);

                String SearchContent = SearchBox.getText().toString();
                if (SearchBox.getText().toString().isEmpty() || (SearchBox.getText().toString().compareTo("Enter Search") == 0)) {
                    Toast.makeText(getApplicationContext(), "No search parameter entered", Toast.LENGTH_SHORT).show();
                }else{
                    breakLoop:
                    for (Clinic item : ClinicList ){
                        if ((SearchContent.compareTo(item.getName()) == 0)) {
                            ResultClinic.add(item);
                            //return item
                            Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_SHORT).show();
                            onSearch(v);
                            break breakLoop;
                        }
                    }

                }



            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        databaseProducts.addValueEventListener(new ValueEventListener() {
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


    public void onBack(View view) {
        finish();
    }

    public void onSearch(View view) {
        Intent myIntent = new Intent(this,SearchResult.class);
        myIntent.putExtra("username",intent.getStringExtra("username"));
        startActivity(myIntent);
    }

}
