package com.uottawa.project;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;


public class AppointmentTest {

    private Appointment a = new Appointment("Jan. 12, 2020", "17:00", "Moe's", "Sally23");

    @Test
    public void getValues() {
        Assert.assertEquals(a.getClinic(), "Moe's");
        Assert.assertEquals(a.getPatient(), "Sally23");
        Assert.assertEquals(a.getDate(), "Jan. 12, 2020");
        Assert.assertEquals(a.getTime(), "17:00");
        a.setClinic("Mike's");
        a.setDate("Feb. 23, 2021");
        a.setPatient("Millie");
        a.setTime("5:00");
        a.setID("ABCDEFGHIJ");
        Assert.assertEquals(a.getClinic(), "Mike's");
        Assert.assertEquals(a.getPatient(), "Millie");
        Assert.assertEquals(a.getDate(), "Feb. 23, 2021");
        Assert.assertEquals(a.getTime(), "5:00");
        Assert.assertEquals(a.getID(), "ABCDEFGHIJ");
    }

    @Test
    public void testCheckIn() {
        Assert.assertEquals(a.isCheckedIn(), false);
        a.checkIn();
        Assert.assertEquals(a.isCheckedIn(), true);
    }

    @Test
    public void testEquals() {
        Appointment b = new Appointment("Jan. 13, 2020", "17:00", "Moe's", "Sally23");
        Assert.assertEquals(a.equals(b), false);
        b.setDate("Jan. 13, 2020");
        Assert.assertEquals(a.equals(b), true);
    }
}
