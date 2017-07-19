package com.mvp.base.model.bean;


import java.util.List;

public class LoginResponse{

    private int code;
    List<WorkmateBean> results;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<WorkmateBean> getResults() {
        return results;
    }

    public void setResults(List<WorkmateBean> results) {
        this.results = results;
    }
}
