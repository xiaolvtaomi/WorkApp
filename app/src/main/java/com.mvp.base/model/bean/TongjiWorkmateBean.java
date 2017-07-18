package com.mvp.base.model.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lml on 17/7/7.
 */

public class TongjiWorkmateBean implements Serializable {

    public WorkmateBean workmate ;
    public List<DishBean> dishes ;


    public WorkmateBean getWorkmate() {
        return workmate;
    }

    public void setWorkmate(WorkmateBean workmate) {
        this.workmate = workmate;
    }

    public List<DishBean> getDishes() {
        return dishes;
    }

    public void setDishes(List<DishBean> dishes) {
        this.dishes = dishes;
    }
}
