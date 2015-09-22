package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 9/12/2015.
 */
public class LoginServiceImpl implements LoginService {

    @Autowired
    public LoginDao loginDao;
    @Override
    public String createUser(String userName,
                             String password,
                             String firstName,
                             String lastName,
                             String email,
                             String phoneNumber) {
        Map<String, String> userValueMap = new HashMap<>();
        userValueMap.put("userName",userName);
        userValueMap.put("password",password);
        userValueMap.put("firstName",firstName);
        userValueMap.put("lastName",lastName);
        userValueMap.put("email",email);
        userValueMap.put("phoneNumber",phoneNumber);
        if(loginDao.createUser(userValueMap)){
            return "Error Whiel creating user:"+userName;
        }
        return "User Created:"+userName;
    }

    public boolean authenticate(String username,
                                String password){
        Map<String, String> userValueMap = new HashMap<>();
        userValueMap.put("username",username);
        userValueMap.put("password", password);
        List<Map<String, Object>> userLoginDetails = loginDao.getLoginDetails(userValueMap);
        if(!CollectionUtils.isEmpty(userLoginDetails) && userLoginDetails.size()==1){
            return true;
        }
        return false;
    }

    public User getUser(String username){
        Map<String, String> userValueMap = new HashMap<>();
        userValueMap.put("username",username);

        List<Map<String, Object>> userLoginDetails = loginDao.getUserDetails(userValueMap);
        if(!CollectionUtils.isEmpty(userLoginDetails) && userLoginDetails.size()==1){
            Map<String, Object> dataMap = userLoginDetails.get(0);
            User user = new User((String)dataMap.get("user_id"),
                    "", (String)dataMap.get("first_name"),
                    (String)dataMap.get("last_name"),
                    (String)dataMap.get("email"),
                    (String)dataMap.get("phone_number"));
            return user;
        }
        return null;
    }

    public List<User> getAllUsers(){
        List<User> userList = new ArrayList<>();
        List<Map<String, Object>> userLoginDetails = loginDao.getAllUsers();
        if(!CollectionUtils.isEmpty(userLoginDetails)){
            for (Map<String, Object> dataMap: userLoginDetails) {
                User user = new User((String) dataMap.get("user_id"),
                        "", (String) dataMap.get("first_name"),
                        (String) dataMap.get("last_name"),
                        (String) dataMap.get("email"),
                        (String) dataMap.get("phone_number"));
                userList.add(user);
            }
        }
        return userList;
    }
}
