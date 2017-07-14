package com.mvp.base.model.bean;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by lml on 17/7/14.
 * bmob批量操作时post的格式
 */

public class BatchRequestBean implements Serializable{

    ArrayList<BatchBean> requests = new ArrayList<>();


    public ArrayList<BatchBean> getRequests() {
        return requests;
    }

    public void setRequests(ArrayList<BatchBean> requests) {
        this.requests = requests;
    }
}
