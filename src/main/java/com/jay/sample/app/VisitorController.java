package com.jay.sample.app;

import java.util.Date;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VisitorController {

    @Autowired
    VisitorManagementService visitorService;

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @RequestMapping("/greeting")
    public String greeting(@RequestParam(value="name") String name) {
        return String.format(template, name);
    }

    @RequestMapping("/createVisitor")
    public boolean createVisitor(
            @RequestParam(value="visitorName") String visitorName,
            @RequestParam(value="visitorEmail") String visitorEmail,
            @RequestParam(value="visitorMobileNo") String visitorMobileNo,
            @RequestParam(value="visitorLocation") String visitorAddress,
            @RequestParam(value="visitorIdentificationNo") String visitorIdNo,
            @RequestParam(value="visitorIdentificationType") String visitorIdType
    ) {
        Map<String, String> visitorValueMap = new HashMap<>();
        visitorValueMap.put("visitorName", visitorName);
        visitorValueMap.put("visitorEmail",visitorEmail);
        visitorValueMap.put("visitorMobileNo",visitorMobileNo);
        visitorValueMap.put("visitorAddress",visitorAddress);
        visitorValueMap.put("visitorIdNo",visitorIdNo);
        visitorValueMap.put("visitorIdType",visitorIdType);
        return visitorService.createVisitor(visitorValueMap);
    }

    @RequestMapping("/createMeeting")
    public boolean createMeeting(
            @RequestParam(value="visitors") List<String> visitors,
            @RequestParam(value="employees") List<String> employees,
            @RequestParam(value="meetingStartTime") String meetingStartTime,
            @RequestParam(value="meetingEndTime") String meetingEndTime,
            @RequestParam(value="meetingLocation") String meetingLocation,
            @RequestParam(value="meetingPurpose") String meetingPurpose,
            @RequestParam(value="meetingOrganizer") String meetingOrganizer
    ) {
        Map<String, Object> meetingValueMap = new HashMap<>();
        meetingValueMap.put("meetingStartTime",new Timestamp(new Date().getTime()));
        meetingValueMap.put("meetingEndTime",new Timestamp(new Date().getTime()));
        meetingValueMap.put("meetingLocation",meetingLocation);
        meetingValueMap.put("meetingPurpose",meetingPurpose);
        meetingValueMap.put("meetingOrganizer",meetingOrganizer);
        return visitorService.createMeeting(meetingValueMap, visitors, employees);
    }

    @RequestMapping("/getVisitorDetailsByMeetingVisitorTrackerCode")
    public VisitorDetails createMeeting(
            @RequestParam(value="meetingVisitorTrackerId") String meetingVisitorTrackerId
    ) {
        Map<String, String> meetingVisitorTrackerMap = new HashMap<>();
        meetingVisitorTrackerMap.put("meetingVisitorTrackerId",meetingVisitorTrackerId);
        return visitorService.getVisitorDetails(meetingVisitorTrackerMap);
    }

}
