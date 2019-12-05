package com.uottawa.project;

import android.widget.SeekBar;
import android.widget.Switch;

import androidx.annotation.UiThread;
import androidx.test.annotation.UiThreadTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.testing.AssertJUnit;


public class ClinicHoursTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule= new ActivityTestRule(ClinicHours.class);
    private  MainActivity  clinicHours = null;



    @Before
    public void setUp() throws Exception {
        clinicHours = mActivityTestRule.getActivity();
    }

    @Test
    @UiThread
    public void checkOpeningHours() {
        SeekBar open = (SeekBar) clinicHours.findViewById(R.id.opening);
        Assert.assertEquals(open.getProgress(),8);

        open.setProgress(17);
        Assert.assertEquals(open.getProgress(),17);
    }

    @Test
    @UiThread
    public void checkClosingHours() throws  Exception{
        SeekBar close = (SeekBar) clinicHours.findViewById(R.id.closing);
        Assert.assertEquals(close.getProgress(),8);

        close.setProgress(17);
        Assert.assertEquals(close.getProgress(),17);
    }

    @Test
    @UiThread
    public void checkOpenSwitch() {
        Switch isOpen = (Switch) clinicHours.findViewById(R.id.isOpen);

        isOpen.setChecked(true);
        Assert.assertEquals(isOpen.isChecked(), true);

        isOpen.setChecked(false);
        Assert.assertEquals(isOpen.isChecked(), false);
    }
}
