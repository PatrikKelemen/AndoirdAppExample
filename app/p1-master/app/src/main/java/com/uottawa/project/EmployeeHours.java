package com.uottawa.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.CalendarView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

public class EmployeeHours extends AppCompatActivity {

    private int currentStart;
    private int currentEnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_hours);

        //updateScreen changes the display to mirror the working hours in the database
        //you will need to change it please
        updateScreen();

        CalendarView cal = (CalendarView) findViewById(R.id.calendar);
        cal.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                updateScreen();
            }
        });

        SeekBar start = (SeekBar) findViewById(R.id.startTimeSelection);
        start.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentStart = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (currentEnd < currentStart) {
                    Toast.makeText(getApplicationContext(), "Endtime of shift must be after start.", Toast.LENGTH_SHORT).show();
                } else if (currentEnd == currentStart) {
                    Toast.makeText(getApplicationContext(), "Endtime cannot be the same as the start time.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        SeekBar end = (SeekBar) findViewById(R.id.endTimeSelection);
        end.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                currentEnd = progress;

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                if (currentEnd < currentStart) {
                    Toast.makeText(getApplicationContext(), "Endtime of shift must be after start.", Toast.LENGTH_SHORT).show();
                } else if (currentEnd == currentStart) {
                    Toast.makeText(getApplicationContext(), "Endtime cannot be the same as the start time.", Toast.LENGTH_SHORT).show();
                }
                System.out.println("end");
            }
        });

        Switch workingSwitch = (Switch) findViewById(R.id.isWorking);
        workingSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    findViewById(R.id.endTimeSelection).setEnabled(true);
                    findViewById(R.id.startTimeSelection).setEnabled(true);
                } else {
                    findViewById(R.id.endTimeSelection).setEnabled(false);
                    findViewById(R.id.startTimeSelection).setEnabled(false);
                }
            }
        });
    }

    private void updateScreen() {
        //get the updated hours from the database
        Boolean isWorking = true; //if the person is working that day


        Switch workingSwitch = (Switch) findViewById(R.id.isWorking);
        if (!isWorking){
            workingSwitch.setChecked(false);
            SeekBar end = (SeekBar) findViewById(R.id.endTimeSelection);
            end.setEnabled(false);
            SeekBar start = (SeekBar) findViewById(R.id.startTimeSelection);
            start.setEnabled(false);
        } else {
            workingSwitch.setChecked(true);
        }
    }

    public void onDone(View view) {
        Intent returnIntent = new Intent();
        setResult(RESULT_OK, returnIntent);
        finish();
    }

    public void onUpdate(View view) {
        if (currentEnd < currentStart) {
            Toast.makeText(getApplicationContext(), "Unable to update hours.\nEndtime of shift must be after start.", Toast.LENGTH_SHORT).show();
            return;
        } else if (currentEnd == currentStart) {
            Toast.makeText(getApplicationContext(), "Unable to update hours.\nEndtime cannot be the same as the start time.", Toast.LENGTH_SHORT).show();
            return;
        }

        //update database hours here
        Switch workingSwitch = (Switch) findViewById(R.id.isWorking);
        CheckBox repeat = (CheckBox) findViewById(R.id.weeklyRepeat);
        if (workingSwitch.isChecked()) {
            //send currentEnd as the end of the shift and currentStart as the start of the shift
            if (repeat.isChecked()) {
                //set the given hours as happening every week
            } else {
                //set the given hours as the hours for this day only
            }
        } else {
            if (repeat.isChecked()) {
                //set them as not working on this day every week
            } else {
                //set them as not working on this day
            }
        }

        Toast.makeText(getApplicationContext(), "Hours Updated", Toast.LENGTH_SHORT).show();
    }
}
