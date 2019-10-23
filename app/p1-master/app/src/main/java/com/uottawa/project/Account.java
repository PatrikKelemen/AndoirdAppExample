package com.uottawa.project;

import java.security.*;
import java.nio.charset.StandardCharsets;

public class Account {

    /*
     * Store's the account's password in SHA-256.
     */
    private String password;

    /*
     * Stores the account's username.
     */
    private String username;

    /*
     * Stores the user's first name.
     */
    private String firstName;

    /*
     * Stores the user's last name.
     */
    private String lastName;

    /*
     * Stores the user's email.
     */
    private String email;


    /**
     * Creates a new Account.
     * @param password a string with the password
     * @param username a string with the username
     * @param firstName a string with the first name of the user
     * @param lastName a string with the last name of the user
     */
    public Account(String password, String username, String firstName, String lastName) {
        this.password = hashPassword(password);
        if (this.password == null) {
            throw new IllegalArgumentException("Password could not be stored properly.");
        }
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;


    }

    /*
     * Method to hash password to SHA-256
     */
    public String hashPassword(String pass){

        try{
            //hashing the password to SHA-256
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] passwordHash = digest.digest(pass.getBytes(StandardCharsets.UTF_8));

            //convertting to hexadecimal
            String hex = "";
            for (int i =0; i < passwordHash.length; i++) {
                hex = hex + String.format("%02x", passwordHash[i]);
            }
            return hex;
        }
        catch(Exception e){
            return null;
        }
    }



    //Getters & Setters
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = hashPassword(password);
        if (this.password == null) {
            throw new IllegalArgumentException("Password could not be stored properly.");
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}