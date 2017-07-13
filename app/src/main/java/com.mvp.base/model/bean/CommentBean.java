package com.mvp.base.model.bean;

import java.io.Serializable;

/**
 * Created by lml on 17/6/16.
 */

public class CommentBean implements Serializable {
    public String objectId ;
    String doodleId ;
    String creatorName ;
    String creatorContent ;
    String creatorPic;
    String time ;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getCreatorPic() {
        return creatorPic;
    }

    public void setCreatorPic(String creatorPic) {
        this.creatorPic = creatorPic;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getDoodleId() {
        return doodleId;
    }

    public void setDoodleId(String doodleId) {
        this.doodleId = doodleId;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreatorContent() {
        return creatorContent;
    }

    public void setCreatorContent(String creatorContent) {
        this.creatorContent = creatorContent;
    }
}
