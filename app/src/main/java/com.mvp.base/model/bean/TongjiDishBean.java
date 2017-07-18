package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/7/7.
 */

public class TongjiDishBean implements Serializable {

    public String picurl ;
    public String dishname ;
    public String title ;
    public int spicylevel ; // 0,1,2
    public int dishid ;
    public int countnum ;

    public boolean hasTitle=false;

    public TongjiDishBean(DishBean dish){
        setPicurl(dish.getPicurl());
        setCountnum(1);
        setTitle(dish.getTitle());
        setHasTitle(dish.isHasTitle());
        setDishid(dish.getDishid());
        setDishname(dish.getDishname());
        setSpicylevel(dish.getSpicylevel());
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

    public int getCountnum() {
        return countnum;
    }

    public void setCountnum(int countnum) {
        this.countnum = countnum;
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
