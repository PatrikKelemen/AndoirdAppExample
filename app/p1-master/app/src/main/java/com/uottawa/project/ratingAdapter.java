package com.uottawa.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ratingAdapter extends RecyclerView.Adapter<ratingAdapter.rViewHolder> {

    private List<Clinic> Clinics;
    private ratingViewListener click;

    public static class rViewHolder extends RecyclerView.ViewHolder {

        private TextView clinic;
        private Button select;

        public rViewHolder(View itemView) {
            super(itemView);

            this.clinic = (TextView) itemView.findViewById(R.id.ClinicName);
            this.select = (Button) itemView.findViewById(R.id.Selected);
        }
    }

    public interface ratingViewListener {
        public void onSelect(Clinic a);
    }

    public ratingAdapter(List<Clinic> Clinics, ratingViewListener click ) {
        this.Clinics = Clinics;
        this.click = click;
    }

    @Override
    public ratingAdapter.rViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.clinic_view, parent, false);
        ratingAdapter.rViewHolder vh = new ratingAdapter.rViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ratingAdapter.rViewHolder holder, final int position) {
        final Clinic a = this.Clinics.get(position);
        holder.clinic.setText(a.getName());
        holder.select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onSelect(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Clinics.size();
    }


}

