package com.example.xinyu.friendtracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;



public class MeetingDetail {

    private String startTimeTime, startTimeDate, endTimeTime, endTimeDate;

    public String getStartTimeTime() {
        return startTimeTime;
    }

    public void setStartTimeTime(String startTimeTime) {
        this.startTimeTime = startTimeTime;
    }

    public String getStartTimeDate() {
        return startTimeDate;
    }

    public void setStartTimeDate(String startTimeDate) {
        this.startTimeDate = startTimeDate;
    }

    public String getEndTimeTime() {
        return endTimeTime;
    }

    public void setEndTimeTime(String endTimeTime) {
        this.endTimeTime = endTimeTime;
    }

    public String getEndTimeDate() {
        return endTimeDate;
    }

    public void setEndTimeDate(String endTimeDate) {
        this.endTimeDate = endTimeDate;
    }

    public ContactDetail getFriends() {
        return friends;
    }

    public void setFriends(ContactDetail friends) {
        this.friends = friends;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    private ContactDetail friends;
    private String location;
    private UUID id;
    private String title;







    @Override
    public String toString() {
        return title;
    }

}

class DataContextMeeting {

    private ArrayList<MeetingDetail> meeting;
    private static DataContextMeeting sDataContext;
    private Context mAppContext;

    private DataContextMeeting(Context appContext) {
        mAppContext = appContext;
        meeting = new ArrayList<MeetingDetail>();
    }

    public static DataContextMeeting get(Context c) {
        if (sDataContext == null) {
            sDataContext = new DataContextMeeting(c.getApplicationContext());
        }
        return sDataContext;
    }

    public ArrayList<MeetingDetail> getMeeting() {
        return meeting;
    }

    public MeetingDetail getMeeting(UUID id) {
        for (MeetingDetail c : meeting) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }

}
