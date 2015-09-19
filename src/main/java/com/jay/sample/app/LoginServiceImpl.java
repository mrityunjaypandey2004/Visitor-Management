package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

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
}
