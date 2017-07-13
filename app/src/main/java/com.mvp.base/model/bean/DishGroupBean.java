package com.mvp.base.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public class DishGroupBean implements Serializable {

    public int id;
    public String name;
    public List<DishBean> group;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<DishBean> getGroup() {
        return group;
    }

    public void setGroup(List<DishBean> group) {
        this.group = group;
    }
}
