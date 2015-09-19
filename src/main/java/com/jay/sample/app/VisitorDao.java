package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.*;
import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 8/30/2015.
 */
public class VisitorDao {

    @Autowired
    DriverManagerDataSource dataSource;

    private static final String insertVisitorSql = "INSERT INTO VISITOR (visitor_name," +
            "visitor_email,visitor_mobile_on,visitor_location,visitor_identification_no,visitor_identification_type) " +
            "VALUES (:visitorName, :visitorEmail, :visitorMobileNo, :visitorAddress, :visitorIdNo, :visitorIdType)";

    private static final String insertMeetingSql = "INSERT INTO MEETING (meeting_purpose,meeting_location,meeting_start_time,meeting_end_time,meeting_organizer) " +
            "VALUES (:meetingPurpose, :meetingLocation, :meetingStartTime, :meetingEndTime, :meetingOrganizer)";

    private static final String insertMeetingVisitorSql = "INSERT INTO MEETING_VISITOR (meeting_id, visitor_id) VALUES (:meetingId, :visitorId)";

    private static final String insertMeetingEmployeeSql = "INSERT INTO MEETING_EMPLOYEE (meeting_id, emp_id) VALUES (:meetingId, :empId)";

    private static final String insertVisitorMeetingTrackerSql = "INSERT INTO VISITOR_MEETING_TRACKER (visitor_meeting_code,meeting_id,visitor_id)" +
            " VALUES (:visitorMeetingCode, :meetingId, :visitorId)";


    private static final String selectVisitorMeetingDetails = " select v.visitor_name, v.visitor_email, v.visitor_mobile_on, " +
            "v.visitor_location, v.visitor_identification_no, v.visitor_identification_type, m.meeting_id, m.meeting_purpose, " +
            "m.meeting_location, m.meeting_start_time, m.meeting_end_time, e.emp_name as meeting_organizer, " +
            "e.emp_mobile_on as meeting_organizer_phone_no " +
            "from VISITOR v, VISITOR_MEETING_TRACKER vt, MEETING m, EMPLOYEE e  " +
            "where v.visitor_id = vt.visitor_id and m.meeting_id = vt.meeting_id and m.meeting_organizer = e.emp_id " +
            "and vt.visitor_meeting_code = :meetingVisitorTrackerId";


    public VisitorDao(){
        //connection = createConnection();
    }

    private Connection connection = null;

    private Connection createConnection(){
        System.out.println("-------- MySQL JDBC Connection Testing ------------");

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Where is your MySQL JDBC Driver?");
            e.printStackTrace();
            return null;
        }

        System.out.println("MySQL JDBC Driver Registered!");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection("jdbc:mysql://ec2-52-76-26-160.ap-southeast-1.compute.amazonaws.com:3306/visitor_schema", "root", "password");

        } catch (SQLException e) {
            System.out.println("Connection Failed! Check output console");
            e.printStackTrace();
            return null;
        }

        if (connection != null) {
            System.out.println("You made it, take control your database now!");
        } else {
            System.out.println("Failed to make connection!");
        }
        return connection;
    }

    public boolean createVisitor(Map<String, String> visitorValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(insertVisitorSql,visitorValueMap);
        return true;
    }

    public int createMeeting(Map<String, Object> meetingValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        return template.update(insertMeetingSql,meetingValueMap);
    }

    public boolean createMeetingVisitor(Map<String, Integer> meetingVisitorValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(insertMeetingVisitorSql,meetingVisitorValueMap);
        return true;
    }

    public boolean createMeetingEmployee(Map<String, Integer> meetingEmployeeValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(insertMeetingEmployeeSql,meetingEmployeeValueMap);
        return true;
    }

    public boolean createMeetingTracker(Map meetingVisitorTrackerValueMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        template.update(insertVisitorMeetingTrackerSql,meetingVisitorTrackerValueMap);
        return true;
    }

    public List<VisitorDetails> getVisitorDetails(Map<String, String> meetingVisitorTrackerMap){
        NamedParameterJdbcTemplate template = new NamedParameterJdbcTemplate(dataSource);
        RowMapper<VisitorDetails> visitorDetailsRowMapper = new RowMapper(){
            public VisitorDetails mapRow(ResultSet var1, int var2) throws SQLException{
                String visitorName = var1.getString("visitor_name");
                String visitorEmail = var1.getString("visitor_email");
                String visitorMobileNo = var1.getString("visitor_mobile_on");
                String visitorLocation = var1.getString("visitor_location");
                String visitorIdentificationNo = var1.getString("visitor_identification_no");
                String visitorIdentificationType = var1.getString("visitor_identification_type");
                String meetingPurpose = var1.getString("meeting_purpose");
                String meetingLocation = var1.getString("meeting_location");
                Timestamp meetingStartTime = var1.getTimestamp("meeting_start_time");
                Timestamp meetingEndTime = var1.getTimestamp("meeting_end_time");
                String meetingOrganizer = var1.getString("meeting_organizer");
                String meetingOrganizerPhoneNumber = var1.getString("meeting_organizer_phone_no");

                VisitorDetails visitorDetails = new VisitorDetails(visitorName, visitorEmail, visitorMobileNo, visitorLocation, visitorIdentificationNo,
                        visitorIdentificationType, meetingPurpose, meetingLocation, meetingStartTime, meetingEndTime, meetingOrganizer, meetingOrganizerPhoneNumber);
                return visitorDetails;
            }
        };
        return template.query(selectVisitorMeetingDetails, meetingVisitorTrackerMap,visitorDetailsRowMapper);
    }


}
