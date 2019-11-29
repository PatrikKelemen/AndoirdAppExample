package com.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class BookAppointment extends AppCompatActivity implements BookAppointmentDialog.BookingDialogListener {

    private long selectedDate;
    private Hours currentClinicHours;
    private int start;
    private int end;
    private RecyclerView possible;
    private LinearLayoutManager layout;
    private ArrayList<String> appointmentHours;
    private BookingAdapter adapter;
    private OnBookingClick click;
    private String username;
    private String clinic;

    public interface OnBookingClick {
        void clicked(String time);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);

        //add these to the intent
        clinic = getIntent().getStringExtra("clinic");
        username = getIntent().getStringExtra("username");

        //get the clinic hours
        currentClinicHours = new Hours("marty");

        CalendarView cal = (CalendarView) findViewById(R.id.calendar);
        selectedDate = cal.getDate();
        updateScreen();

        click = new OnBookingClick() {
            @Override
            public void clicked(String time) {
                BookAppointmentDialog dialog = new BookAppointmentDialog(time);
                dialog.show(getSupportFragmentManager(), "book appointment");
            }
        };

        possible = (RecyclerView) findViewById(R.id.possibleAppointments);

        layout = new LinearLayoutManager(this);
        possible.setLayoutManager(layout);

        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                Calendar c = Calendar.getInstance();
                c.set(year, month, dayOfMonth);
                selectedDate = c.getTimeInMillis();
                updateScreen();
            }
        });

        adapter = new BookingAdapter(appointmentHours, click);
        possible.setAdapter(adapter);
    }

    public void onCancel(View view){
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onBook(String time) {
        //check that the time hasn't already been taken
        //will need to get stuff out of the database for this

        Appointment a = new Appointment(DateFormat.getDateInstance(DateFormat.MEDIUM).format(selectedDate), time, clinic, this.username);
        //add appointment to database

        Toast.makeText(getApplicationContext(),"Appointment Booked",Toast.LENGTH_LONG).show();
    }

    //reads from the database
    private void updateScreen() {
        //get the updated hours from the database

        /*
            CalendarView cal = (CalendarView) findViewById(R.id.calendar);

         	This may be useful, IDK?:

         	cal.getDate();

            Gets the selected date in milliseconds since January 1, 1970 00:00:00 in TimeZone#getDefault() time zone.
            Returns a long.
         */

        int[] hours = currentClinicHours.getHours(selectedDate);

        Boolean isOpen; //if the clinic is open that day
        if (hours == null) {
            isOpen = false;
        } else {
            isOpen = true;
            start = hours[0]; //the time the shift starts at
            end = hours[1]; //the time the shift ends at
        }

        appointmentHours = new ArrayList<>();

        if (!isOpen){
            ((TextView)findViewById(R.id.selectTitle)).setText("Clinic is Closed: No Appointments Available");
        } else {
            ((TextView)findViewById(R.id.selectTitle)).setText("Select an Appointment:");

            int currentHour = start;

            while (currentHour < end) {
                appointmentHours.add(currentHour+":00");
                appointmentHours.add(currentHour+":15");
                appointmentHours.add(currentHour+":30");
                appointmentHours.add(currentHour+":45");
                currentHour++;
            }
        }
    }
}
