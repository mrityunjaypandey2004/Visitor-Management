package com.jay.sample.app;

/**
 * Created by Jay on 9/13/2015.
 */
public class LoginDetails {

    String username;
    String password;

    public LoginDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public LoginDetails() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
