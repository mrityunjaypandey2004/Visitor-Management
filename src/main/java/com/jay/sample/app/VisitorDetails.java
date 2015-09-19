package com.jay.sample.app;

import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Created by Jay on 8/30/2015.
 */
public class VisitorDetails {
    private String visitorName;
    private String visitorEmail;
    private String visitorMobileNo;
    private String visitorAddress;
    private String visitorIdNo;
    private String visitorIdType;

    String meetingPurpose ;
    String meetingLocation ;
    Timestamp meetingStartTime ;
    Timestamp meetingEndTime ;
    String meetingOrganizer ;
    String meetingOrganizerPhoneNumber ;

    SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");

    public VisitorDetails(String visitorName, String visitorEmail, String visitorMobileNo, String visitorAddress,
                          String visitorIdNo, String visitorIdType, String meetingPurpose, String meetingLocation,
                          Timestamp meetingStartTime, Timestamp meetingEndTime, String meetingOrganizer,
                          String meetingOrganizerPhoneNumber) {
        this.visitorName = visitorName;
        this.visitorEmail = visitorEmail;
        this.visitorMobileNo = visitorMobileNo;
        this.visitorAddress = visitorAddress;
        this.visitorIdNo = visitorIdNo;
        this.visitorIdType = visitorIdType;
        this.meetingPurpose = meetingPurpose;
        this.meetingLocation = meetingLocation;
        this.meetingStartTime = meetingStartTime;
        this.meetingEndTime = meetingEndTime;
        this.meetingOrganizer = meetingOrganizer;
        this.meetingOrganizerPhoneNumber = meetingOrganizerPhoneNumber;
    }

    public String getVisitorName() {
        return visitorName;
    }

    public String getVisitorEmail() {
        return visitorEmail;
    }

    public String getVisitorMobileNo() {
        return visitorMobileNo;
    }

    public String getVisitorAddress() {
        return visitorAddress;
    }

    public String getVisitorIdNo() {
        return visitorIdNo;
    }

    public String getVisitorIdType() {
        return visitorIdType;
    }

    public String getMeetingPurpose() {
        return meetingPurpose;
    }

    public String getMeetingLocation() {
        return meetingLocation;
    }

    public String getMeetingStartTime() {
        return sf.format(new Date(meetingStartTime.getTime()));
    }

    public String getMeetingEndTime() {
        return sf.format(new Date(meetingEndTime.getTime()));
    }

    public String getMeetingOrganizer() {
        return meetingOrganizer;
    }

    public String getMeetingOrganizerPhoneNumber() {
        return meetingOrganizerPhoneNumber;
    }
}
