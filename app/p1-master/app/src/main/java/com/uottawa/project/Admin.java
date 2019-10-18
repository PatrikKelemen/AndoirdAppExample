package com.uottawa.project;

public class Admin extends Account {

    /*
     * Holds an Array of services to be implemented when Admin is supported.
     */
    private Service[] services;

    /**
     * Creates a new Admin account.
     * @param password a string with the password
     * @param username a string with the username
     * @param firstName a string with the first name of the user
     * @param lastName a string with the last name of the user
     */
    public Admin(String password, String username, String firstName, String lastName) {
        super(password, username, firstName, lastName);
    }


    //These methods will be implemented when Admin support is added.


    /**
     * Removes a Service that Clinics can offer.
     */
    public void deleteService(Service service) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Creates a new Service that Clinics can offer.
     */
    public void createService(Service service) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Deletes the given ccount.
     */
    public void deleteUser(Account user) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * Deletes the given clinic.
     */
    public void deleteClinic(Clinic clinic) {
        throw new UnsupportedOperationException("Not implemented yet.");
    }
}
