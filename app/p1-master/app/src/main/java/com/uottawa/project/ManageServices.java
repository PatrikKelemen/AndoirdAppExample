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
                Service service = services.get(i);
                showUpdateDeleteDialog(service.getId(), service.getName(), service.getRate(), service.getRole());
                return true;
            }
        });

    }

    private void showUpdateDeleteDialog(final String serviceId, String serviceName, float serviceRate, String serviceRole) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextRate  = (EditText) dialogView.findViewById(R.id.editTextPrice);
        final EditText editTextRole = (EditText) dialogView.findViewById(R.id.editTextRole);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateProduct);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteProduct);

        dialogBuilder.setTitle(serviceName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                double price = Double.parseDouble(String.valueOf(editTextPrice.getText().toString()));
                if (!TextUtils.isEmpty(name)) {
                    updateService(serviceId, serviceName, serviceRate, serviceRole);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteService(serviceId);
                b.dismiss();
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
