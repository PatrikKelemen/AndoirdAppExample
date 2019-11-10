package com.uottawa.project;

public class Service {

    /*
     * The rate of the Service in canadian dollars.
     */
    private float rate;

    /*
     * The name of the Service.
     */
    private String name;

    // Role of the employee who performs the service
    private String role;

    //More will be added here when services are implemented.


    /**
     * Creates a new Service that a Clinic can offer.
     * @param rate a float with the price of the service in canadian dollars
     * @param name a string with the name of the service
     * @param role a string with the employee role who performs the service
     */
    public Service(float rate, String name) {
        this.rate = rate;
        this.name = name;
    }

    public Service(){}

    //Getters & Setters
    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
