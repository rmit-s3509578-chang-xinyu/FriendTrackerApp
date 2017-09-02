package com.example.xinyu.friendtracker;

import android.content.Context;
import android.widget.DatePicker;

import java.util.ArrayList;
import java.util.UUID;



public class MeetingDetail {

    private DatePicker startTime, endTime;
    private ContactDetail friends;
    private String location;
    private UUID id;
    private String title;





    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public DatePicker getStartTime() {
        return startTime;
    }

    public void setStartTime(DatePicker startTime) {
        this.startTime = startTime;
    }

    public DatePicker getEndTime() {
        return endTime;
    }

    public void setEndTime(DatePicker endTime) {
        this.endTime = endTime;
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


    public MeetingDetail() {
        id = UUID.randomUUID();
    }


    public UUID getId() {
        return id;
    }



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
    public MeetingDetail getMeeting(String title) {
        for (MeetingDetail c : meeting) {
            if (c.getTitle().equals(title))
                return c;
        }
        return null;
    }
}
