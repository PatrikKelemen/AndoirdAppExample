package com.uottawa.project;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class ServiceList extends ArrayAdapter<Service> {
    private Activity context;
    List<Service> services;

    public ServiceList(Activity context, List<Service> services) {
        super(context, R.layout.layout_service_list, services);
        this.context = context;
        this.services = services;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_service_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewServiceName);
        TextView textViewRate = (TextView) listViewItem.findViewById(R.id.textViewServiceRate);
        TextView textViewRole = (TextView) listViewItem.findViewById(R.id.textViewServiceRole);

        Service service = services.get(position);
        textViewName.setText(service.getName());
        textViewRate.setText(String.valueOf(service.getRate()));
        textViewRole.setText(String.valueOf(service.getRole()));
        return listViewItem;
    }



}
