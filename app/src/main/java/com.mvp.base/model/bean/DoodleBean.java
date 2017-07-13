package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/6/16.
 */

public class DoodleBean implements Serializable {
    public String objectId ;
    String doodleid ;
    String img_big ;
    String img_mid ;
    String img_small ;
    String info_long ;
    String info_long_en ;
    String info_short ;
    String title ;
    String type ;
    int year ;
    int month ;
    int day ;
    int level ;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInfo_long_en() {
        return info_long_en;
    }

    public void setInfo_long_en(String info_long_en) {
        this.info_long_en = info_long_en;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getDoodleid() {
        return doodleid;
    }

    public void setDoodleid(String doodleid) {
        this.doodleid = doodleid;
    }

    public String getImg_big() {
        return "http://cdn.sinacloud.net/doodle/"+type+"/highlevel/"+img_big;
    }

    public void setImg_big(String img_big) {
        this.img_big = img_big;
    }

    public String getImg_mid() {
        return img_mid;
    }

    public void setImg_mid(String img_mid) {
        this.img_mid = img_mid;
    }

    public String getImg_small() {
        if(type.equals("google")){
            if((img_small.endsWith(".jpg") || img_small.endsWith(".png") || img_small.endsWith(".gif"))){
                return "http://cdn.sinacloud.net/doodle/"+type+"/"+img_small;
            }else {
                return "http://cdn.sinacloud.net/doodle/"+type+"/"+img_small+".jpg";
            }
        }else {
            return "http://cdn.sinacloud.net/doodle/"+type+"/"+img_small;
        }
    }

    public void setImg_small(String img_small) {
        this.img_small = img_small;
    }

    public String getInfo_long() {
        return info_long;
    }

    public void setInfo_long(String info_long) {
        this.info_long = info_long;
    }

    public String getInfo_short() {
        return info_short;
    }

    public void setInfo_short(String info_short) {
        this.info_short = info_short;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }


}
