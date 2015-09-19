package com.jay.sample.app;

import java.util.List;
import java.util.Map;

/**
 * Created by Jay on 8/30/2015.
 */
public interface VisitorManagementService {

    public boolean createVisitor(Map<String, String> visitorValueMap);

    public boolean createMeeting(Map<String, Object> meetingValueMap, List<String> visitors, List<String> employees);

    public VisitorDetails getVisitorDetails(Map<String, String> meetingVisitorTrackerMap);

}
