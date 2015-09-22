package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Jay on 9/5/2015.
 */
@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

   private ConcurrentHashMap<String, HttpSession> sessionMap = new ConcurrentHashMap<>();

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public @ResponseBody Boolean authenticate(@RequestBody final LoginDetails loginDetails){
        Boolean response = loginService.authenticate(loginDetails.getUsername(), loginDetails.getPassword());
        return response;
    }


    @RequestMapping(value = "/createUser", method = RequestMethod.POST)
    public @ResponseBody Boolean createUser(@RequestBody final User user) {
        String userName = loginService.createUser(user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getPhoneNumber());
        return true;
    }

    @RequestMapping(value = "/getUser/{username}", method = RequestMethod.GET)
    public @ResponseBody User getUser(@PathVariable final String username) {
        User user = loginService.getUser(username);
        return user;
    }


    @RequestMapping(value = "/getAllUsers ", method = RequestMethod.GET)
    public @ResponseBody List<User> getAllUsers() {
        List<User> userList = loginService.getAllUsers();
        return userList;
    }

}
