package com.uottawa.project;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

import javax.xml.transform.Result;

public class Search extends AppCompatActivity {

    Button search;
    EditText SearchBox;
    static List<Clinic> ClinicList;
    List<Clinic> ResultClinic;
    int[] openHours = {6,7,8,9};
    int[] closingHours = {3,4,5,6};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        SearchBox = (EditText) findViewById(R.id.text_input);
        search = (Button) findViewById(R.id.button);

        ResultClinic = new ArrayList<>();

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
                            break breakLoop;
                        }
                    }
                }



            }
        });
    }
    public void onBack(View view) {
        Intent myIntent = new Intent(this,PatientScreen.class);
        startActivity(myIntent);
    }

    public void onSearch(View view) {
        Intent myIntent = new Intent(this,SearchResult.class);
        startActivity(myIntent);
    }

}
