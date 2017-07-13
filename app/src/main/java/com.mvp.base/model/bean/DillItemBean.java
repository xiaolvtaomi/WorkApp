package com.mvp.base.model.bean;

import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;
import com.mvp.base.utils.GsonUtil;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public class DillItemBean implements Serializable {

    int year ;
    int month ;
    int day ;
    String workmate_name ;
    String workmate_creator ;
    String title ;
    String dishpic ;
    String dishname ;
    int dishid ;
    int dishtype ;
    String avatar ;

    String dishesJson ;

    boolean hasTitle = false ;

    public boolean isHasTitle() {
        return hasTitle;
    }

    public void setHasTitle(boolean hasTitle) {
        this.hasTitle = hasTitle;
    }

    public String getDishesJson() {
        return dishesJson;
    }

    public ArrayList<DishBean> getDishes(){
        if(TextUtils.isEmpty(dishesJson)){
            return null ;
        }else{
            Type dataType = new TypeToken<List<DishBean>>(){}.getType();
            return GsonUtil.getGson().fromJson(dishesJson, dataType );
        }
    }

    public DishBean getDish(){
        DishBean temp = new DishBean();
        temp.setDishname(dishname);
        temp.setDishid(dishid);
        temp.setTitle(title);
        temp.setPicurl(dishpic);
        temp.setType(dishtype);
        return temp ;
    }

    public void setDishesJson(String dishesJson) {
        this.dishesJson = dishesJson;
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

    public String getWorkmate_name() {
        return workmate_name;
    }

    public void setWorkmate_name(String workmate_name) {
        this.workmate_name = workmate_name;
    }

    public String getWorkmate_creator() {
        return workmate_creator;
    }

    public void setWorkmate_creator(String workmate_creator) {
        this.workmate_creator = workmate_creator;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDishpic() {
        return dishpic;
    }

    public void setDishpic(String dishpic) {
        this.dishpic = dishpic;
    }

    public String getDishname() {
        return dishname;
    }

    public void setDishname(String dishname) {
        this.dishname = dishname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }


}
