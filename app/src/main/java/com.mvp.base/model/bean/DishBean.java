package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/7/7.
 */

public class DishBean implements Serializable {

    public String objectId ;
    public String picurl ;
    public String dishname ;
    public String title ;
    public int spicylevel ; // 0,1,2
    public int dishid ;
    public boolean isopen ; // 开启时间 yyyyMMdd
    public int type ; // 0,1,2 早中晚

    public boolean hasTitle=false;

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    public boolean isopen() {
        return isopen;
    }

    public void setIsopen(boolean isopen) {
        this.isopen = isopen;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPicurl() {
        return picurl;
    }

    public void setPicurl(String picurl) {
        this.picurl = picurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getSpicylevel() {
        return spicylevel;
    }

    public void setSpicylevel(int spicylevel) {
        this.spicylevel = spicylevel;
    }

    public int getDishid() {
        return dishid;
    }

    public void setDishid(int dishid) {
        this.dishid = dishid;
    }
}
