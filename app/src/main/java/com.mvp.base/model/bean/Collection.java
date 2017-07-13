package com.mvp.base.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Description: 收藏
 * Creator: yxc
 * date: 2016/9/23 11:29
 */
public class Collection extends RealmObject implements Serializable {
    String id;
    long time;
    public String title;
    public String type = "google";
    public String pic;
    public String airTime;
    public String score;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getAirTime() {
        return airTime;
    }

    public void setAirTime(String airTime) {
        this.airTime = airTime;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
