package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 9/12/2015.
 */
public class LoginDao {

    @Autowired
    DriverManagerDataSource dataSource;

    private static final String createUserSql = "INSERT INTO LOGIN (" +
            "user_id,user_password,first_name,last_name,email,phone_number) " +
            "VALUES (:userName, :password, :firstName, :lastName, :email, :phoneNumber)";

    private static final String getUserDetails = "select * from LOGIN where user_id = :username and user_password = :password";

    private static final String getUserDetailsByUserName = "select * from LOGIN where user_id = :username";

    private static final String getAllUsers = "select * from LOGIN";

    public boolean createUser(Map<String, String> userValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(createUserSql,userValueMap);
        return true;
    }

    public List<Map<String, Object>> getLoginDetails(Map<String, String> userValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template.queryForList(getUserDetails,userValueMap);
    }

    public List<Map<String, Object>> getUserDetails(Map<String, String> userValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template.queryForList(getUserDetailsByUserName,userValueMap);
    }

    public List<Map<String, Object>> getAllUsers(){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template.queryForList(getAllUsers,new HashMap<String, Object>());
    }
}
