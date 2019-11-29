package com.uottawa.project;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SearchResult extends AppCompatActivity {
    private RecyclerView listClinic;
    private RecyclerView.LayoutManager layout;
    private RecyclerView.Adapter adapter;
    private List<Clinic> ClinicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchresult);

        ClinicList = Search.ClinicList;

        //dropdown list
        listClinic = (RecyclerView) findViewById(R.id.listSearchResult);
        layout = new LinearLayoutManager(this);
        listClinic.setLayoutManager(layout);

        adapter = new searchAdapter(ClinicList, new searchAdapter.searchViewListener() {

            @Override
            public void onSelect(Clinic a) {
                System.out.println(a.getName());
                int index = ClinicList.indexOf(a);
                ClinicList.remove(a);
                Toast.makeText(getApplicationContext(), "Clinic selected: "+ a.getName(), Toast.LENGTH_LONG).show();
                adapter.notifyItemRemoved(index);
            }

    });
       listClinic.setAdapter(adapter);

    }
}

