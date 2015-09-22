package com.jay.sample.app;


import java.util.List;

/**
 * Created by Jay on 9/12/2015.
 */
public interface LoginService {

    public String createUser(String userName,
                             String password,
                             String firstName,
                             String lastName,
                             String email,
                             String phoneNumber);

    public boolean authenticate(String username,
                             String password);


    public User getUser(String username);

    public List<User> getAllUsers();
}
