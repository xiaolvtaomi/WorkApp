package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/7/7.
 */

public class WorkmateBean implements Serializable {
    String picurl ;
    String name ;
    String mobile ;
    String avatar;
    String objectId;
    int userid;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    int role ;

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public int getUserid() {
        return userid;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
