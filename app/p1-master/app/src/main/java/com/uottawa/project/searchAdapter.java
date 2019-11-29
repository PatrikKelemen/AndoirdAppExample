package com.uottawa.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class searchAdapter extends RecyclerView.Adapter<searchAdapter.searchViewHolder> {

    private List<Clinic> Clinics;
    private searchViewListener click;

    public static class searchViewHolder extends RecyclerView.ViewHolder {

        private TextView clinic;
        private Button select;

        public searchViewHolder(View itemView) {
            super(itemView);

            this.clinic = (TextView) itemView.findViewById(R.id.nameClinic);
            this.select = (Button) itemView.findViewById(R.id.selectButton);
        }
    }

    public interface searchViewListener {
        public void onSelect(Clinic a);
    }

    public searchAdapter(List<Clinic> Clinics, searchViewListener click ) {
        this.Clinics = Clinics;
        this.click = click;
    }

    @Override
    public searchAdapter.searchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.search_view, parent, false);
        searchAdapter.searchViewHolder vh = new searchAdapter.searchViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(searchAdapter.searchViewHolder holder, final int position) {
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
