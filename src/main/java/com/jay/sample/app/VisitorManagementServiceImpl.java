package com.jay.sample.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 8/30/2015.
 */
public class VisitorManagementServiceImpl implements VisitorManagementService{

    @Autowired
    public VisitorDao visitorDao;

    @Override
    public boolean createVisitor(Map<String, String> visitorValueMap) {
        return visitorDao.createVisitor(visitorValueMap);
    }

    public boolean createMeeting(Map<String, Object> meetingValueMap, List<String> visitors, List<String> employees){
        int meetingId = visitorDao.createMeeting(meetingValueMap);
        for (String employee: employees){
            Map<String, Integer> meetingEmployeeValuesMap = new HashMap<>();
            meetingEmployeeValuesMap.put("meetingId", meetingId);
            meetingEmployeeValuesMap.put("empId", Integer.parseInt(employee));
            visitorDao.createMeetingEmployee(meetingEmployeeValuesMap);
        }
        for (String visitor: visitors){
            Map<String, Integer> meetingVisitorValuesMap = new HashMap<>();
            meetingVisitorValuesMap.put("meetingId",meetingId);
            meetingVisitorValuesMap.put("visitorId", Integer.parseInt(visitor));
            visitorDao.createMeetingVisitor(meetingVisitorValuesMap);

            String randomCodeString = generateVisitorMeetingTrackerCode();
            Map visitorMeetingTrackerValuesMap = new HashMap();
            visitorMeetingTrackerValuesMap.put("visitorMeetingCode",randomCodeString);
            visitorMeetingTrackerValuesMap.put("meetingId",meetingId);
            visitorMeetingTrackerValuesMap.put("visitorId",visitor);
            visitorDao.createMeetingTracker(visitorMeetingTrackerValuesMap);
        }
        return true;
    }

    private String generateVisitorMeetingTrackerCode(){
        int randonmCode = System.identityHashCode(new Object());
        String rendomCodeString = ""+randonmCode;
        return rendomCodeString;
    }

    public VisitorDetails getVisitorDetails(Map<String, String> meetingVisitorTrackerMap){
        List<VisitorDetails> visitorDetailsList = visitorDao.getVisitorDetails(meetingVisitorTrackerMap);
        if(!CollectionUtils.isEmpty(visitorDetailsList))return visitorDetailsList.get(0);
       return null;
    }
}
