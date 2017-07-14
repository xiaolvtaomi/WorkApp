package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/7/14.
 */

public class BatchBean implements Serializable {
    String method ; // POST, PUT, DELETE
    String path ; // /1/classes/TableName
    Object body ;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }
}
