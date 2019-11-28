package com.uottawa.project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AppointmentAdapter extends RecyclerView.Adapter<AppointmentAdapter.AppointmentViewHolder> {

    private List<Appointment> appointments;
    private AppointmentViewListener click;

    public static class AppointmentViewHolder extends RecyclerView.ViewHolder {

        private TextView clinic;
        private TextView time;
        private Button cancel;
        private Button checkIn;

        public AppointmentViewHolder(View itemView) {
            super(itemView);

            this.clinic = (TextView) itemView.findViewById(R.id.appointClinic);
            this.time = (TextView) itemView.findViewById(R.id.appointTime);
            this.cancel = (Button) itemView.findViewById(R.id.cancelAppointBtn);
            this.checkIn = (Button) itemView.findViewById(R.id.checkInBtn);
        }
    }

    public interface AppointmentViewListener {
        public void onCancel(Appointment a);
        public void onCheckIn(Appointment a);
    }

    public AppointmentAdapter(List<Appointment> appointments, AppointmentViewListener click) {
        this.appointments = appointments;
        this.click = click;
    }

    @Override
    public AppointmentAdapter.AppointmentViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = (View) LayoutInflater.from(parent.getContext()).inflate(R.layout.appointment_view_v2, parent, false);
        AppointmentAdapter.AppointmentViewHolder vh = new AppointmentAdapter.AppointmentViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(AppointmentViewHolder holder, final int position) {
        final Appointment a = this.appointments.get(position);
        holder.clinic.setText(a.getClinic().getName());
        holder.time.setText(a.getDate()+" at "+a.getTime());

        holder.checkIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onCheckIn(a);
            }
        });

        holder.cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                click.onCancel(a);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }
}
