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
    private int phone;

    public ContactDetail() {
        id = UUID.randomUUID();
    }


    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
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

    private ArrayList<ContactDetail> mFriend;
    private static DataContext sDataContext;
    private Context mAppContext;

    private DataContext(Context appContext) {
        mAppContext = appContext;
        mFriend = new ArrayList<ContactDetail>();
    }

    public static DataContext get(Context c) {
        if (sDataContext == null) {
            sDataContext = new DataContext(c.getApplicationContext());
        }
        return sDataContext;
    }

    public ArrayList<ContactDetail> getFriend() {
        return mFriend;
    }

    public ContactDetail getFriend(UUID id) {
        for (ContactDetail c : mFriend) {
            if (c.getId().equals(id))
                return c;
        }
        return null;
    }
    public ContactDetail getFriend(String name) {
        for (ContactDetail c : mFriend) {
            if (c.getName().equals(name))
                return c;
        }
        return null;
    }
}
