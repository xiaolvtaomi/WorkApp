package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/6/16.
 */

public class NickBean implements Serializable {
    int nameid ;
    String nameenglish ;
    String namechinese ;

    public String getNameenglish() {
        return nameenglish;
    }

    public void setNameenglish(String nameenglish) {
        this.nameenglish = nameenglish;
    }

    public String getNamechinese() {
        return namechinese;
    }

    public void setNamechinese(String namechinese) {
        this.namechinese = namechinese;
    }

    public int getNameid() {
        return nameid;
    }

    public void setNameid(int nameid) {
        this.nameid = nameid;
    }
}
