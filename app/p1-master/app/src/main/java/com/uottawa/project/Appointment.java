package com.uottawa.project;

public class Appointment {

    /*
     * Stores the date of the appointment.
     */
    private String date;

    /*
     * Stores the time for the appointment.
     */
    private String time;

    /*
     * Stores the clinic the appointment is booked at.
     */
    private Clinic clinic;

    /*
     * Stores the Patient the appointment is booked for.
     */
    private Patient patient;

    public Appointment(String date, String time, Clinic clinic, Patient patient) {
        this.date = date;
        this.time = time;
        this.clinic = clinic;
        this.patient = patient;
    }

    public Appointment() {}

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
