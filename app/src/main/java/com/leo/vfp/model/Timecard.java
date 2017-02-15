package com.leo.vfp.model;

import cn.bmob.v3.BmobObject;

/**
 * Created by Leo on 2017-01-12.
 */

public class Timecard extends BmobObject{
    private String userId;
    private String username;
    private String location;
    private int type;
    private boolean isOvertime;

    public boolean isOvertime() {
        return isOvertime;
    }

    public void setOvertime(boolean overtime) {
        isOvertime = overtime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
