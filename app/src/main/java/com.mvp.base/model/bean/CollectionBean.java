package com.mvp.base.model.bean;

import java.io.Serializable;

import io.realm.RealmObject;

/**

 */
public class CollectionBean extends RealmObject implements Serializable {
    public String title;
    public String pic;
    public String tag;
    public int typeid ;


    public int getTypeid() {
        return typeid;
    }

    public void setTypeid(int typeid) {
        this.typeid = typeid;
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

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
