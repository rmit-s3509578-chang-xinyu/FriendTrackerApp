package com.example.xinyu.friendtracker;

import android.content.Context;

import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by bowen on 2017-08-27.
 */

public class ContactDetail {




    private UUID id;


    private String name;
    private String email;


    public ContactDetail() {
        id = UUID.randomUUID();
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }


    public void setId(UUID id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name;
    }



}




class DataContext {

    private ArrayList<ContactDetail> mPatients;
    private static DataContext sDataContext;
    private Context mAppContext;

    private DataContext(Context appContext) {
        mAppContext = appContext;
        mPatients = new ArrayList<ContactDetail>();
    }

    public static DataContext get(Context c) {
        if (sDataContext == null) {
            sDataContext = new DataContext(c.getApplicationContext());
        }
        return sDataContext;
    }

    public ArrayList<ContactDetail> getPatients() {
        return mPatients;
    }

    public ContactDetail getPatient(UUID id) {
        for (ContactDetail c : mPatients) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
}
