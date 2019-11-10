package com.uottawa.project;

import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ManageServices extends AppCompatActivity {

    EditText editTextName;
    EditText editTextRate;
    EditText editTextRole;
    Button buttonAddService;
    ListView listViewServices;
    Button buttonExit;

    List<Service> services;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_services);
        editTextName = (EditText) findViewById(R.id.ServiceName);
        editTextRate = (EditText) findViewById(R.id.ServiceRate);
        editTextRole =(EditText) findViewById(R.id.ServiceJob);
        listViewServices = (ListView) findViewById(R.id.ServiceList);
        buttonAddService = (Button) findViewById(R.id.addService);
        buttonExit = (Button) findViewById(R.id.ExitService);

        services = new ArrayList<>();

        listViewProducts.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Product service = services.get(i);
                showUpdateDeleteDialog(service.getId(), service.getName(), service.getRate(), service.getRole());
                return true;
            }
        });

    }



    public void onExit(View view){

    }
    public void onAdd(View view){
        Service newService = new Service();
        newService.setName(editTextName);
        newService.setRate(editTextRate);
        newService.setRole(editTextRole);
    }



}
