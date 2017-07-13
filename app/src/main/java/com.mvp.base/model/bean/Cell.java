package com.mvp.base.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**
 * Created by lml on 17/5/9.
 */

public class Cell extends RealmObject implements Serializable {

    String id;
    long time;
    public String title ;
    public String path ;
    public String npath ;

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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getNpath() {
        return npath;
    }

    public void setNpath(String npath) {
        this.npath = npath;
    }
}
